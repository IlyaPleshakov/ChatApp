document.addEventListener("DOMContentLoaded",function (){

    let socket = new WebSocket("ws://localhost:8080/ChatApp_war_exploded/websocket/chat");
    let message=$('input.chat-form__input').val();
    socket.onopen=function (e){
        alert('Connection successfull');
        alert('SEND MESSAGE');
        socket.send(message);
    }
    socket.onmessage=function (event){

        $('.chat-messages').append("<div class='chat-messages__message-me'>"+event.data+"</div>");
    }
    socket.onclose=function (event){
        alert("Connection was close");
    }
    socket.onerror=function (error){
        alert('ERROR');
    }
    function handlerButton (){
        let message = $('input.chat-form__input').val();
        if (message!=''){socket.send(message);
        $('input.chat-form__input').val('');
        return false;} else return false;
    }
    $('input.chat-form__submit').bind("click", handlerButton);
    $('html').keydown(function (e){
       if (e.keyCode==13){
           let message = $('input.chat-form__input').val();
           if (message!='') {socket.send(message);
           $('input.chat-form__input').val('');
           return false;} else return false;
       }
    });
});
