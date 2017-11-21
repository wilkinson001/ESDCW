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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Check and Approve Members</h1>
        
        <% 
            String data = (String) session.getAttribute("data");
            
            if(!data.trim().equals("null")){
                out.print("<h3>Membership applications</h3><br><br><br>");
                out.print(data);
            }
        
        %>
        <br><br>
        <h3>Enter Member ID to approve</h3><br><br>
        <form action="approve_member.do" method="POST">
            <input type="text" placeholder="Enter member id" name="username" required>
            <br><br>
            <button type="submit">Approve Member</button>
        </form>
        
        <form action="admin.do" method="POST"><br><br>
            <button type="submit">Return to Dashboard</button>
        </form>
        
    </body>
</html>
