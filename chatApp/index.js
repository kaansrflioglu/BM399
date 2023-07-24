const mongoose = require("mongoose");
const express = require("express");
const bodyParser = require("body-parser");
const app = express();
const path = require("path");
const http = require("http");
const server = http.createServer(app);
const { Server } = require("socket.io");
const io = new Server(server);

app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "/views"));
const mongoDbConnection = mongoose.connect(
  "mongodb+srv://axicom:KPDuA3Za5OiuxDI1@cluster0.beglfy3.mongodb.net/ChatApp?retryWrites=true&w=majority"
);
mongoose.connection.on("open", () => {
  console.log("MongoDB Bağlantısı Kuruldu");
});
mongoose.connection.on("error", (err) => {
  console.log("MongoDB Bağlantısı Kurulamadı", err);
});
const TableMessage = require("./models/Message");
const User = require("./models/User");
app.use(bodyParser.json());
app.use(express.static("public"));
app.get("/", (req, res) => {
  res.render("login");
});
app.get("/chat", (req, res) => {
  res.render("index");
});
app.post("/", (req, res) => {
  const { username, password } = req.body;
  User.findOne({ username, password }, (err, user) => {
    if (err) {
      console.error(err);
      res.json({ success: false });
    } else {
      if (user) {
        res.json({ success: true });
      } else {
        res.json({ success: false });
      }
    }
  });
});
app.use(express.json());
const allUsers = [];
io.on("connection", (socket) => {
  allUsers.push({
    id: socket.id,
    username: "default",
    room_id: "0",
  });
  socket.on("join_room", (msg) => {
    const findIndex = allUsers.findIndex((item) => item.id === socket.id);
    allUsers[findIndex] = {
      id: socket.id,
      username: msg.username,
      room_id: msg.room_id,
    };
    socket.join(msg.room_id);
    io.in(msg.room_id).emit(
      "room_users",
      allUsers.filter((x) => x.room_id == msg.room_id)
    );
    TableMessage.find({
      roomId: msg.room_id,
    })
      .then((messages) => {
        io.to(socket.id).emit("old_messages", messages);
      })
      .catch((err) => {
        console.log(err);
      });
  });
  socket.on("send_message", (msg) => {
    io.in(msg.room_id).emit("send_message", msg);
    new TableMessage({
      content: msg.message,
      roomId: msg.room_id,
      username: msg.username,
    }).save();
  });
  socket.on("disconnect", () => {
    const removeIndex = allUsers.findIndex((item) => item.id === socket.id);
    const findRoomId = allUsers[removeIndex].room_id;
    allUsers.splice(removeIndex, 1);
    io.in(findRoomId).emit(
      "room_users",
      allUsers.filter((x) => x.room_id == findRoomId)
    );
  });
});

const PORT = 3000;
server.listen(PORT, () => {
  console.log(`Sunucu "http://localhost:${PORT}/" adresinde çalışacak.`);
});
