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
    </head>
    <body background="grd.jpg">
        <h1>Please enter the details of your new claim</h1>
        <div class = registerpage>
            <form action='claim.do' method=POST>
                
                
                <label><b>Date</b></label>
                <input type="text" placeholder="Enter the date that your claim occurred" name="date" required>
                
                
                
                <label><b>Rationale</b></label>
                <input <textarea name="Text1" cols="40" rows="20"></textarea>
>
                
               
                
                <label><b>Value</b></label>
                <input type="text" placeholder="£" name="value" required>
                
                
            
                <button type="submit">Claim</button>
            </form>
        </div>
            
    </body>
</html>
