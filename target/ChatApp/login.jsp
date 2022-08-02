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
        <h1 class="contacts__header">Contacts list</h1>
        <div class="contacts__items"> MAIN CHAT
        </div>
        <div class="contacts__items"> Friends
        </div>
    </div>
    <div class="chat">
        <div class="chat-header">
            <button class="chat-header__button" type="submit">Contacts list</button>
            <button class="chat-header__button" type="submit">Exit from chatroom</button>
        </div>
        <div class='chat-messages'>
            <div class="chat-messages__message-me">HELLO WORLD. THE TEST MESSAGE FROM ME</div>
            <div class="chat-messages__message-others">HELLO WORLD. THE TEST MESSAGE FROM OTHER CONTACTS</div>
        </div>
        <div class='chat-input'>
            <input type='text' class='chat-form__input' placeholder='Введите сообщение' name="message" maxlength="1000">
            <input type='submit' class='chat-form__submit' value='SEND'>
        </div>

    </div>

</div>
</body>
</html>
