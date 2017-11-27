<%-- 
    Document   : claims
    Created on : 27-Nov-2017, 17:58:38
    Author     : Kamil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make a Claim</title>
        <link rel="stylesheet" type="text/css" href="pagecss.css" />

    </head>

    <style>
        p {color:blue;}
    </style>

    <body background="grd.jpg">
        <h1>XYZ Claims Page</h1>


        <label><p>First Name</b></label>
        <input type="text" placeholder="Enter First Name" name="fname" required>

        <label><br>Last Name</b></label>
        <input type="text" placeholder="Enter Last Name" name="fname" required>




        <label><br> Tell us about your incident <br> <label>
                <textarea rows="10" cols="60"> </textarea>

                <label><br>Date of incident</b></label>
                <input type="date" placeholder="Enter Date Of Incident" name="doi" required>

                <label><br>Time of incident</b></label>
                <input type="time" placeholder="Enter time Of Incident(HH:MM)" name="toi" required>

                <label><br>Claim Amount(£)</b></label>
                <input type="number" placeholder="Enter in amount(£)" name="camount" required>

                <label><br> Your E-mail Address: <br><label> 
                        <input type="email" placeholder="user@example.com" name="email"> 

                        <br><button type="button" onclick="alert('Claim Submitted Succesfully')">Submit</button><br>
                        <br><button type="button" onclick="alert('Claim Cancelled')">Cancel</button><br>




                        <details>
                            <summary><font size="2" color="blue">All Claims with be replied within 24 hours.</font></summary>
                            <p> - XYZ Claims.</p>
                            <p>All Claims are checked by our staff quickly and efficiently, so you can have a peace of mind.</p>
                        </details>







                        </body>


                        </html>
