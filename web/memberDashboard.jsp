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
        <link rel="stylesheet" type="text/css" href="pagecss.css" />
    </head>
    <body background="grd.jpg">
        <h1>Welcome <%
            String name = (String) session.getAttribute("user");
            out.print(name);
            %>
        </h1>
        <div>

            <form action='payment.jsp' method=POST>              
                <button type = "submit">Make a payment</button><br><br>
                </form>
            <% 
                String type = (String) session.getAttribute("type");
                String button = "<form action='claim.jsp' method=POST><button type = \"submit\">Make a claim</button><br><br></form>";
                if(type.trim().equals("APPROVED".trim())){
                    out.println(button);
                }
            %>
            <label><b>Outstanding balance: </b></label>
            <%
                Object b = session.getAttribute("balance");
                String balance = b.toString();
                //String balance = (String) session.getAttribute("balance");
                out.print("£" + balance);
            %> <br><br>
            <%
                String claim = (String)session.getAttribute("claims");
                if(!claim.equals("<table border=\"3\"></table>")){
                    out.print("<label><b>Claims made: </b> </label>");
                    out.print(claim);
                } else {
                    out.print("<b>No Claims Made<b>");
                }
            %> <br><br>
            <form action="logout.do" method="POST">
                <button type="submit">Log Out</button>
            </form>
        </div>
    </body>
</html>
