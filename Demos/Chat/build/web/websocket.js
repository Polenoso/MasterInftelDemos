/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = init;
var socket = new WebSocket("ws://localhost:8080/Chat/actions");
socket.onmessage = onMessage;

function onMessage(event) {
    var mes = event.data;
    $("#chat").append("<p>" + mes + "</p>");
}

function sendMessage(){
    var message = document.getElementById("mensaje").value;
    socket.send(message);

}

function init(){
    
}

