<!DOCTYPE html>
<html>
<body>
<div>
    <h1>Please enter your login</h1>
    <div>

        <form action="${pageContext.request.contextPath}/loginServlet" method="post">
            <input type="text" name="login" value="default_user">
            <input type="submit" name="button3" value="Send" />
        </form>

    </div>
    <footer><%= new java.util.Date()%></footer>
</div>
</body>
</html>
