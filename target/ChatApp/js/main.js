document.addEventListener("DOMContentLoaded", function () {
    function getTextInputForm() {
        return $('input.chat-form__input').val();
    }

    function setEmptyInputForm() {

        return $('input.chat-form__input').val('');
    }

    function getId(){
        return $('.contacts__nick').text();
    }


    let roomNum = Math.floor(Math.random() * 1000);
    var JSONmsg = {
        id: getId(),
        text: getTextInputForm(),
        type: "message"
    }
    let socket = new WebSocket("ws://localhost:8080/ChatApp_war_exploded/websocket/chat/" + 1);


    socket.onopen = function (e) {
        let msg = JSON.stringify(JSONmsg);
        alert('Connection successfull. Message was send');
        socket.send(msg);
    }
    socket.onmessage = function (event) {

        try {
            let msg = JSON.parse(event.data);
            if (msg.text != '' && msg.type === "message") {
                if (msg.id === getId()) {
                    $('.chat-messages').append("<div class='chat-messages__message-me'>" + msg.text + "</div>");
                } else {
                    $('.chat-messages').append("<div class='chat-messages__message-others'>" + msg.text + "</div>");
                }
            } else if (msg.type === "userslist") {
                $('.contacts__items').remove();
                for (var i = 0; i < msg.users.length; i++) {

                    $('.contacts').append("<div class='contacts__items'>" + msg.users[i] + "</div>");
                }
            }


        } catch (e) {
            $('.chat-messages').append("<div class='chat-messages__message-others'>" + event.data + "</div>");
        }
    }
    socket.onclose = function (event) {
        alert("Connection was close");
    }
    socket.onerror = function (error) {
        alert('ERROR');
    }

    function handlerSendButton() {

        let JSONmsg = {
            id: getId(),
            text: getTextInputForm(),
            type: "message"
        }
        let msg = JSON.stringify(JSONmsg);
        if (JSONmsg.text != '') {
            socket.send(msg);
            setEmptyInputForm();
            return false;
        } else return false;
    }


    $('input.chat-form__submit').bind("click", handlerSendButton);
    $('html').keydown(function (e) {
        if (e.keyCode == 13) {
            let JSONmsg = {
                id: getId(),
                text: getTextInputForm(),
                type: "message"
            }
            let msg = JSON.stringify(JSONmsg);
            if (JSONmsg.text != '') {
                socket.send(msg);
                setEmptyInputForm();
                return false;
            } else return false;
        }
    });

    $('.chat-header__button').bind("click", logout);

    function logout() {
        $.ajax({
            url: 'http://localhost:8080/ChatApp_war_exploded/logout',
            method: 'get',
            dataType: 'html',
            success: function () {
                window.location.href = '/ChatApp_war_exploded/jsp/login.jsp';
            },
            error: function () {
                window.location.href = '/ChatApp_war_exploded/jsp/error.jsp';
            }
        });
    }

});
