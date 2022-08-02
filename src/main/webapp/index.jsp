<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>ChatApp</title>
    <script src="jquery-3.6.0.min.js"></script>
    <!--<script src="main.js"></script> -->
</head>
<body>
<div class="wrapper_index">

    <div class="login-form">
        <h2 class="login-form__head">Please login. Enter id chatroom and password</h2>
        <form action="${pageContext.request.contextPath}/loginServlet" method="get">
            <input type="text" name="id" placeholder="id">
            <input type="text" name="password" placeholder="password">
            <input type="submit" name="login-button" value="Send" />
        </form>
    </div>

    <div class="register-form">
        <h2 class="login-form__head">Register new chatroom</h2>
        <form action="${pageContext.request.contextPath}/registerServlet" method="get">
            <input type="text" name="password">
            <input type="text" name="mail" placeholder="mail@mail.ru">
            <input type="submit" name="register-button" value="Register" />
        </form>
    </div>
    </div>
</div>
</body>
</html>
