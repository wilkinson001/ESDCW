<%-- 
    Document   : memberDashboard
    Created on : 20-Nov-2017, 11:14:15
    Author     : Ashley
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member dashboard</title>
    </head>
    <body>
        <h1>Welcome <%
            String name = (String) request.getAttribute("user");
            out.print(name);
            %>
        </h1>
        <div>

            <form action='memberDashboard.do' method=POST>              
                <button type = "submit">Make a payment</button><br><br>
                <button type = "submit">Make a claim</button><br><br>
            </form>
            
            <label><b>Outstanding balance: </b></label>
            <%
                String balance = (String) request.getAttribute("balance");
                out.print("£" + balance);
            %> <br><br>
            <label><b>Claims made: </b> </label>
            <%
                String claim = (String) request.getAttribute("claim");
                out.print(claim);
            %> <br><br>
            <button type="submit">Log Out</button>
        </div>
    </body>
</html>
