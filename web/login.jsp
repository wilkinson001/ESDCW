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
        <link rel="stylesheet" type="text/css" href="pagecss.css" />
    </head>
    <body background="grd.jpg">
        <h1>Member Login</h1>
        
        <div id = "loginpage">
            <form action='login.do' method=POST>
                
                <div>
                    <div class = left><label><b>Username</b></label></div>
                    <div class = right><input type="text" placeholder="Enter Username" name="username" required></div>
                </div>
                
                 <div>
                     <div class = left<label><b>Password</b></label></div>
                     <div class = right><input type="password" placeholder="Enter Password" name="password" required></div>
                 </div>
                
                <div>
                 <button type="submit">Login</button>
                </div>
            </form>
        </div>
    </body>
</html>
