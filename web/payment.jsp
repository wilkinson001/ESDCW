<%-- 
    Document   : payment
    Created on : 21-Nov-2017, 13:04:29
    Author     : Ollie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
    </head>
    <body>
        <h1>Pay outstanding balance</h1><br>
        
        <%
                Object b = session.getAttribute("balance");
                String balance = b.toString();
                out.print("Current Balance: £" + balance);
            %>
            <br><br>
        <form action='payment.do' method=POST>
            <label><b>Amount to pay:   </b></label>
                <input type="number" placeholder="Amount" name="amount" required min="0.00">
                <br><br>
                <button type="submit">Make Payment</button>
        </form><br><br><br><br>
        <form action='member.do' method='POST'>
            <button type="submit">Return to dashboard</button>
        </form>
    </body>
</html>
