const socket = io();
const AREA_CHANGE_USERNAME = document.getElementById("areaJoinRoom");
const AREA_ROOM_SCREEN = document.getElementById("areaRoomScreen");
const TXT_USERNAME = document.getElementById("txtUsername");
const TXT_ROOM_ID = document.getElementById("txtRoomId");
const TXT_ROOM_USERS = document.getElementById("txtRoomUsers");
const TXT_WELCOME_ROOM = document.getElementById("txtWelcomeRoom");
const TXT_MESSAGE = document.getElementById("txtMessage");
const MESSAGE_LIST = document.getElementById("messageList");

socket.on("room_users", (msg) => {
  document.getElementById("txtRoomUserCount").innerHTML = msg.length;
  TXT_ROOM_USERS.innerHTML = "";
  msg.forEach((element) => {
    const item = document.createElement("li");
    item.textContent = element.username;
    item.className = "list-group-item";
    if (element.id == socket.id) {
      item.textContent += " (Siz)";
    }
    TXT_ROOM_USERS.appendChild(item);
  });
});
socket.on("send_message", (msg) => {
  const messageContent =
    " <p class='card'><b>" + msg.username + ": </b>" + msg.message + "</p>";
  MESSAGE_LIST.innerHTML = messageContent + MESSAGE_LIST.innerHTML;
});
socket.on("old_messages", (msg) => {
  msg.forEach((element) => {
    const messageContent =
      " <p class='card'><b>" +
      element.username +
      ": </b>" +
      element.content +
      "</p>";
    MESSAGE_LIST.innerHTML = messageContent + MESSAGE_LIST.innerHTML;
  });
});
function joinRoom() {
  if (TXT_ROOM_ID.value.length > 1 && TXT_USERNAME.value.length > 1) {
    socket.emit("join_room", {
      username: TXT_USERNAME.value,
      room_id: TXT_ROOM_ID.value,
    });
    AREA_CHANGE_USERNAME.style.display = "none";
    AREA_ROOM_SCREEN.style.display = "block";
    TXT_WELCOME_ROOM.style.color = "#DC3545";
    TXT_WELCOME_ROOM.innerHTML =
      "Oda Kodu: " +
      '<span style="color: ' +
      "black" +
      ';">' +
      TXT_ROOM_ID.value +
      "</span>";
  }
}
function sendMessage() {
  if (TXT_MESSAGE.value.length > 0) {
    socket.emit("send_message", {
      username: TXT_USERNAME.value,
      room_id: TXT_ROOM_ID.value,
      message: TXT_MESSAGE.value,
    });
    TXT_MESSAGE.value = "";
  }
}
Array.from(document.getElementsByClassName("badge")).forEach((element) => {
  element.addEventListener("click", function () {
    TXT_ROOM_ID.value = element.innerHTML;
  });
});
