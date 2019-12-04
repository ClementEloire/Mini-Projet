<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
    </head>
    <body>
        <div style="color:red">${errorMessage}</div>
        <form action="login" method="POST">
            login : <input name='loginParam'><br>
            password: <input name='passwordParam' type='password'><br>
            <input type='submit' name='action' value='login'>
        </form>
    </body>
</html>
