<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<% request.getSession().getId();

%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>ChatApp</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</head>

<body>
<div class="wrapper">
    <div class="contacts">
        <h1 class="contacts__header">Contacts list</h1>
        <h2 class="contacts__nick"><%= request.getUserPrincipal().getName()%></h2>
    </div>
    <div class="chat">
        <div class="chat-header">
            <button class="chat-header__button" type="submit">Exit from chatroom</button>
        </div>
        <div class='chat-messages'></div>
        <div class='chat-input'>
            <input type='text' class='chat-form__input' placeholder='Введите сообщение' name="message" maxlength="1000">
            <input type='submit' class='chat-form__submit' value='SEND'>
        </div>

    </div>

</div>
</body>
</html>
