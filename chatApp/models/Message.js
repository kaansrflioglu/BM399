const mongoose = require("mongoose");
const Schema = mongoose.Schema;
const messageSchema = new Schema({
  content: String,
  roomId: String,
  username: String,
  createdDate: { type: Date, default: Date.now },
});

module.exports = mongoose.model("message", messageSchema);