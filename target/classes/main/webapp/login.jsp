<%@ page import="main.webapp.MessagesDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>CHAT</title>
    <script src="jquery-3.6.0.min.js"></script>
    <script src="main.js"></script>
</head>

<body>
<div class="chat">
    <div class='chat-messages'>
        <div class='chat-messages__content' id='messages'>
            <div class="chat__message"></div>
        </div>
    </div>
    <div class='chat-input'>
        <form method='post' id='chat-form' action="${pageContext.request.contextPath}/messageServlet">
            <input type='text' id='message-text' class='chat-form__input' placeholder='Введите сообщение' name="message">
            <input type='submit' class='chat-form__submit' value='SEND'>
        </form>
    </div>

</div>
</body>
</html>
