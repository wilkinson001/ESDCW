<%-- 
    Document   : login
    Created on : 16-Nov-2017, 20:32:55
    Author     : Ollie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Please enter your login details</h1>
        <div>
            <form action='login.do' method=POST>
                <label><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" required>

                <label><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
