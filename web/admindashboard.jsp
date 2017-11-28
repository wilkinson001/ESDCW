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
        <link rel="stylesheet" type="text/css" href="pagecss.css" />
    </head>
    <body background="grd.jpg">
        
        <h1>Welcome, <%
            String name = (String) session.getAttribute("user"); 
            out.print(name);
            
%></h1>
<div class = admindash>
    <form action="approve_member.do" method="POST">
        <button type = "submit">Approve New Members</button><br><br>
    </form>
    <form action="approve_claim.do" method="POST">
        <button type = "submit">Approve Claims</button><br><br>
    </form>    
    <form action="check_balances.do" method="POST">
        <button type = "submit">Check Member Balances</button><br><br>
    </form>
    <form action="charge.do" method="POST">
        <button type = "submit">Calculate annual charge</button><br><br>
    </form>
    <form action="logout.do" method="POST">
        <button type="submit">Log Out</button>
    </form>
</div>
    </body>
</html>
