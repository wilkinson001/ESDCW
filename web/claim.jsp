<%-- 
    Document   : claim
    Created on : 27-Nov-2017, 11:45:32
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make a new Claim</title>
        <link rel="stylesheet" type="text/css" href="pagecss.css" />
    </head>
    <body background="grd.jpg">
        <h1>Please enter the details of your new claim</h1>
        <div class = registerpage>
            <form action='claim.do' method=POST>
                
                
                <label><b>Date</b></label>
                <input type="date" placeholder="Date that your claim occurred" name="date" required>
                
                
                
                <label><b>Rationale</b></label>
                <textarea name="rat" cols="40" rows="20"></textarea>
                
               
                
                <label><b>Value</b></label>
                <input type="number" placeholder="£" name="val" required>
                
                
                <br>
                <button type="submit">Claim</button>
            </form><br>
            <form action='member.do' method='POST'>
            <button type="submit">Return to dashboard</button>
        </form>
        </div>
            
    </body>
</html>
