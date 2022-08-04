document.addEventListener("DOMContentLoaded",function (){
    function getTextInputForm(){
        return $('input.chat-form__input').val();
    }
    function setEmptyInputForm(){
        return $('input.chat-form__input').val('');
    }


    let roomNum= Math.floor(Math.random()*1000);
    let guestId=Math.floor(Math.random()*1000);
    var JSONmsg={
        id: guestId,
        message:getTextInputForm()
    }
    let socket = new WebSocket("ws://localhost:8080/ChatApp_war_exploded/websocket/chat/"+1);


    socket.onopen=function (e){
        let msg=JSON.stringify(JSONmsg);
        alert('Connection successfull. Message was send');
        socket.send(msg);
    }
    socket.onmessage=function (event) {

        try {
            let msg = JSON.parse(event.data);
            if (msg.message!='') {
                if (msg.id === guestId) {
                    $('.chat-messages').append("<div class='chat-messages__message-me'>" + msg.message + "</div>");
                } else {
                    $('.chat-messages').append("<div class='chat-messages__message-others'>" + msg.message + "</div>");
                }
            }

        }
        catch (e) {
            $('.chat-messages').append("<div class='chat-messages__message-me'>" + event.data + "</div>");
        }
    }
    socket.onclose=function (event){
        alert("Connection was close");
    }
    socket.onerror=function (error){
        alert('ERROR');
    }
    function handlerButton (){
        let text = getTextInputForm();
        let JSONmsg={
            id: guestId,
            message:text
        }
        let msg=JSON.stringify(JSONmsg);
        if (text!=''){
            socket.send(msg);
            setEmptyInputForm();
        return false;} else return false;
    }
    $('input.chat-form__submit').bind("click", handlerButton);
    $('html').keydown(function (e){
       if (e.keyCode==13){
           let text = getTextInputForm();
           let JSONmsg={
               id: guestId,
               message:text
           }
           let msg=JSON.stringify(JSONmsg);
           if (text!='') {
               socket.send(msg);
               setEmptyInputForm();
           return false;} else return false;
       }
    });
});
