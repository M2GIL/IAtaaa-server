var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connectToWebSocket(playerID) {
    var socket = new SockJS('/game-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/api-webSocket/connectUserNotif',function (data) {
            refreshUserList();
            showInformation(data) ;
        });

        stompClient.subscribe('/api-webSocket/requestPlayNotif/'+ playerID, function (data) {
            requestToPlayGame();
            showInformation(data) ;
        });

        stompClient.subscribe('/api-webSocket/startGameNotif/'+ playerID, function (data) {
            startGame();
            showInformation(data) ;
        });


    });
}

function refreshUserList() {
    
    // TO Do  
}

function  requestToPlayGame() {
    // TO Do  
}


function  startGame(gameID) {
    stompClient.subscribe('/api-webSocket/playGameNotif/' + gameID, function (data) {
        refreshBoard(gameID);
        showInformation(data) ;
    });

    stompClient.subscribe('/api-webSocket/endGameNotif/' + gameID, function (data) {
        endGame(gameID);
        showInformation(data) ;
    });
    
     refreshBoard(gameID);

    // TO Do   
}


function  playShot(gameID) {
   stompClient.send('/playGame/'+gameID);
    
    // TO Do  
}


function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}


function requestToPlay() {
    stompClient.send("/app/requestPlay/" + $("#id_t").val(), {}, JSON.stringify({'token': $("#name").val()}));
}

function sendNotifConnection() {
    stompClient.send("/app/connectUser", {}, JSON.stringify({'name': $("#name").val()}));
}



function showInformation(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}



$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connectToWebSocket($("#name").val());
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        requestToPlay();
    });




});

