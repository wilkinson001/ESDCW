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
        <link rel="stylesheet" type="text/css" href="pagecss.css" />
        
    </head>
    <body background="grd.jpg">
        <h1>Please enter your details to register</h1>
        <div class = registerpage>
            <form action='register.do' method=POST>
                
                
                <label><b>First Name</b></label>
                <input type="text" placeholder="Enter First Name" name="fname" required>
                
                
                
                <label><b>Surname</b></label>
                <input type="text" placeholder="Enter Surname" name="sname" required>
                
               
                
                <label><b>Address</b></label>
                <input type="text" placeholder="Enter Address" name="add" required>
                
                
                
                <label><b>Date of Birth</b></label>
                <input type="date" placeholder="Enter Date of Birth" name="dob" required>
                
            <%    
            String error = (String) request.getAttribute("error");
            
            if(error!=null){
                out.println(error+"<br>");
            }
            %>
            <br><button type="submit">Register</button>    
            </form>
        </div>
            
    </body>
</html>
