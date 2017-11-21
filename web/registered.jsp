<%-- 
    Document   : registered
    Created on : 20-Nov-2017, 12:20:34
    Author     : owilkinson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String user = (String) session.getAttribute("user"); 
            String pword = (String) session.getAttribute("pword");
       %>
        <h1>Thank You for Registering</h1>
        <br><br>Your username is: <% out.println(user); %>
        <br><br>Your password is: <% out.println(pword); %>
        <br><br>
        <form action='member.do' method=POST>
            <button type="submit">Continue</button>
       </form>
    </body>
</html>
