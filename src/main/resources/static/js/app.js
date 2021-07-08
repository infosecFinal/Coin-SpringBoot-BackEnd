var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

// 날짜
function onMessage(e){
    var chatMsg = event.data;
    var date = new Date();
    var dateInfo = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
    if(chatMsg.substring(0,6) === 'server'){
        var $chat = $("<div class='chat notice'>" + chatMsg + "</div>");
        $('#chat-container').append($chat);
    }else{
        var $chat = $("<div class='chat-box'><div class='chat'>" + chatMsg + "</div><div class='chat-info chat-box'>"+ dateInfo +"</div></div>");
        $('#chat-container').append($chat);
    }


    $('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
}
