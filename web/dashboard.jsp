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
        <title>Dashboard</title>
    </head>
    <body>
        <h1>Welcome <%
            String name = (String) request.getAttribute("user"); 
            out.print(name);
        %></h1>
    </body>
</html>
