<%-- 
    Document   : approve_claim
    Created on : 21-Nov-2017, 19:50:39
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
        <h1>Check and Approve Members</h1>
        
        <% 
            String data = (String) session.getAttribute("data");
            
            if(!data.trim().equals("<table border=\"3\"></table>")){
                out.print("<h3>Claims</h3><br><br><br>");
                out.print("<table border=\"3\"><tr><td>claim id</td><td>member id</td><td>date</td><td>rationale</td><td>status</td><td>amount</td><td>claim number</td></tr></table>");
                out.print(data);
            }else{
                out.print("<h3>No outstanding claims</h3>");
            }
        
        %>
        <br><br>
        <h3>Enter Claim ID to approve</h3><br><br>
        <form action="approve_claim.do" method="POST">
            <input type="text" placeholder="Enter claim id" name="claim" required>
            <br><br>
            <button type="submit">Approve Claim</button>
        </form>
        <h3>Enter Claim ID to reject</h3><br><br>
        <form action="approve_claim.do" method="POST">
            <input type="text" placeholder="Enter claim id" name="reject_claim" required>
            <br><br>
            <button type="submit">Reject Claim</button>
        </form>
        
        <form action="admin.do" method="POST"><br><br>
            <button type="submit">Return to Dashboard</button>
        </form>
    </body>
</html>
