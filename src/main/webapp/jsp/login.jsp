
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>ChatApp</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/load.js"></script>
</head>
<body>
<div class="wrapper_index">

    <div class="login-form">
        <h2 class="login-form__head">Please login. Enter id chatroom and password</h2>
        <form action="j_security_check" method="post">
            <input type="text" name="j_username" placeholder="id">
            <input type="password" name="j_password" placeholder="password">
            <input type="submit" name="login-button" value="Send" />
        </form>
    </div>

    <div class="register-form">
        <h2 class="login-form__head">Register new chatroom</h2>
        <form action="${pageContext.request.contextPath}/registerServlet" method="get">
            <input type="text" name="password">
            <input type="submit" name="register-button" value="Register" />
        </form>
    </div>
</div>
</div>
</body>
</html>