<%-- 
    Document   : register
    Created on : Nov 27, 2015, 10:59:00 PM
    Author     : Anson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function checkPass()
            {
                //Store the password field objects into variables ...
                var pass1 = document.getElementById('pass1');
                var pass2 = document.getElementById('pass2');
                //Store the Confimation Message Object ...
                var message = document.getElementById('confirmMessage');
                //Set the colors we will be using ...
                var goodColor = "#66cc66";
                var badColor = "#ff6666";
                //Compare the values in the password field 
                //and the confirmation field
                if (pass1.value == "" || pass2.value == "") {
                    return false;
                } else {
                    if (pass1.value == pass2.value) {
                        //The passwords match. 
                        //Set the color to the good color and inform
                        //the user that they have entered the correct password 
                        pass1.style.backgroundColor = goodColor;
                        pass2.style.backgroundColor = goodColor;
                        message.style.color = goodColor;
                        message.innerHTML = "Passwords Match!"
                        return true;
                    } else {
                        //The passwords do not match.
                        //Set the color to the bad color and
                        //notify the user.
                        pass1.style.backgroundColor = badColor;
                        pass2.style.backgroundColor = badColor;
                        message.style.color = badColor;
                        message.innerHTML = "Passwords Do Not Match!"
                        return false;
                    }
                }
            }
            function validateEmail() {
                var email = document.getElementById("email");
                var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                if (email.value.match(mailformat)) {
                    return true;
                } else {
                    alert("You have entered an invalid email address!");
                    email.focus();
                    return false;
                }
            }


            function checkValidate() {
                var uname = document.getElementById('uname');
                var email = document.getElementById('email');
                var d_address = document.getElementById('d_address');
                var pass1 = document.getElementById('pass1');
                var pass2 = document.getElementById('pass2');

                if (pass1.value == "" || pass2.value == "") {
                    alert("Missing Password");
                    return false;
                } else if (uname.value == null || uname.value == "" || email.value == null || email.value == "" || d_address.value == null || d_address.value == "") {
                    alert("Missing Information");
                    return false;
                } else if (!checkPass()) {
                    alert("Passwords Do Not Match");
                    return false;
                } else if (!validateEmail()) {
                    alert("Email format is not validate");
                    return false;
                } else {
                    return true;
                }
            }
        </script>
    </head>
    <body>
        <h1>Welcome to Register Page</h1>
        <b>Please enter the information below</b>
        <form action="HandleRegister" onsubmit="return checkValidate()" method="post">
            <input type="hidden" name="action" value="register"/>
            <table>
                <tr><td>Username:</td><td><input type="text" name="uname" id="uname"/></td></tr>
                <tr><td>Password:</td><td><input type="password" name ="passwd" id="pass1"/></td></tr>
                <tr><td>Re-enter Password:</td><td><input type="password" id="pass2" onkeyup="checkPass();
                        return false;"/><span id="confirmMessage" class="confirmMessage"></span></td></tr>
                <tr><td>Email:</td><td><input type="text" name="email" id="email" onblur="validateEmail()"/></td></tr>
                <tr><td>Delivery address:</td><td><textarea name="d_address" rows="3" cols="40" id="d_address"></textarea></td></tr>
                <tr><td></td></tr>
            </table>

      
            <table>
                <tr>
                    <td>Card Type</td>
                    <td><input type="radio" name="cardType" value="MasterCard" checked>
                        MasterCard
                        <input type="radio" name="cardType" value="Visa">
                        Visa
                        <input type="radio" name="cardType" value="AmericanExpress">
                        AmericanExpress </td>
                </tr>
                <tr>
                    <td>Card Holder</td>
                    <td><input type="text" name="holder"></td>
                </tr>
                <tr>
                    <td>Card Number</td>
                    <td><input type="text" name="cardNo" placeholder="xxxx xxxx xxxx xxxx"></td>
                </tr>
                <tr>
                    <td>Card Security Code</td>
                    <td><input type="text" name="password" placeholder="Enter Four-Digit Number"</td>
                </tr>
                
                <tr>
                    <td>Expiry Date</td>


                    <td><input type="date" name="expiryDate"></td>
                </tr>
            </table>

            <input type="submit" value="Submit Information"/></td><td><input type="reset" value="Reset Information"/>
    </form>
</body>
</html>
