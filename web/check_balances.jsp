<%-- 
    Document   : approve_members
    Created on : 21-Nov-2017, 19:50:23
    Author     : Ollie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Member Balances</title>
        <link rel="stylesheet" type="text/css" href="pagecss.css" />
    </head>
    <body background="grd.jpg">
        <h1>Check Member Balances</h1>
        
        <% 
            String data = (String) session.getAttribute("data");
            out.print(data);
        %>
        <br><br>      
        <form action="admin.do" method="POST"><br><br>
            <button type="submit">Return to Dashboard</button>
        </form>
        
    </body>
</html>
