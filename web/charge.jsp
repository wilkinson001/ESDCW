<%-- 
    Document   : check_balances
    Created on : 21-Nov-2017, 19:50:54
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
        <h1>Charge Annual Fee</h1>
        
        <%
          String charge = (String) session.getAttribute("charge");
          String data = (String) session.getAttribute("data");
          String res = (String) session.getAttribute("res");
          out.print("<h3>Total claimed year to date: £"+charge+"</h3><br><br>");
          
          out.print("<h3>Charge each member : £"+data+"</h3><br><br>");
          if(res!=null){
              out.print("<h3>"+res+"</h3>");
          }
        %>
        
        <form action="charge.do" method="POST"><br><br>
            <input type="hidden" name="charge_members" value="1">
            <button type="submit">Charge Members Amount</button>
        </form>
        
        <form action="admin.do" method="POST"><br><br>
            <button type="submit">Return to Dashboard</button>
        </form>
    </body>
</html>
