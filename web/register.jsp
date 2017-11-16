<%-- 
    Document   : register
    Created on : 16-Nov-2017, 20:32:34
    Author     : Ollie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>XYZ Registration</title>
    </head>
    <body>
        <h1>Please enter your details to register</h1>
        <div>
            <form action='register.do' method=POST>
                <label><b>First Name</b></label>
                <input type="text" placeholder="Enter First Name" name="fname" required><br><br>
                
                <label><b>Surname</b></label>
                <input type="text" placeholder="Enter Surname" name="sname" required><br><br>

                <label><b>Address</b></label>
                <input type="text" placeholder="Enter Address" name="add" required><br><br>
                
                <label><b>Date of Birth</b></label>
                <input type="date" placeholder="Enter Date of Birth" name="dob" required><br><br>
                
                
                <button type="submit">Register</button>
            </form>
        </div>
    </body>
</html>
