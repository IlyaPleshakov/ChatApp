<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>ChatApp</title>
    <script src="jquery-3.6.0.min.js"></script>
    <script src="main.js"></script>
</head>

<body>
<div class="wrapper">
    <div class="contacts">
        <div class="contacts__items"> MAIN CHAT
        </div>
        <div class="contacts__items"> Friends
        </div>
    </div>
    <div class="chat">
        <div class='chat-messages'>
            <div class="chat-messages__message"></div>
        </div>
        <div class='chat-input'>
            <input type='text' class='chat-form__input' placeholder='Введите сообщение' name="message" maxlength="1000">
            <input type='submit' class='chat-form__submit' value='SEND'>
        </div>

    </div>

</div>
</body>
</html>
