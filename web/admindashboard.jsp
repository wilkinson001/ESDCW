<%-- 
    Document   : dashboard
    Created on : 16-Nov-2017, 20:14:15
    Author     : Ollie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
    </head>
    <body>
        <h1>Welcome <%
            String name = (String) request.getAttribute("user"); 
            out.print(name);
            
%></h1>
<div>
    <form action ="admin dashboard.do" method = POST>
        <button type = "submit">Approve New Members</button><br><br><br><br>
        <button type = "submit">Approve Claims</button><br><br><br><br>
        <button type = "submit">Check Member Balance</button>
    </form>
</div>
    </body>
</html>