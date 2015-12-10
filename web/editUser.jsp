<%-- 
    Document   : editUser
    Created on : Nov 30, 2015, 8:31:10 AM
    Author     : test
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:useBean id="userInfo" class="ict.bean.UserBean" scope="session"/>
        <jsp:include page="/template/header.jsp"/>

        <h3>User Information</h3>
        <form action="updateUser" method="post">
            <input type="hidden" name="input_type" value="info">
            <input type="hidden" name="uId" value="<%=userInfo.getuId()%>">

            <table>
                <tr><td>User Id</td></tr> 
                <tr><td>User Name</td><td><%=userInfo.getuName()%></td></tr> 
                <tr><td>Email</td><td><input type="text" name="email" value="<%=userInfo.getEmail()%>"></td></tr> 
                <tr><td>Delivery Address</td><td><input type="text" name="deliveryAddress" value="<%=userInfo.getDeliveryAddress()%>"></td></tr> 
            </table>
            <input type="submit" value="submit">
        </form>
        <h3>Password</h3>
        <form action="updateUser" method="post">
            <input type="hidden" name="input_type" value="password">
            <input type="hidden" name="uId" value="<%=userInfo.getuId()%>">
            <table>
                <tr><td>Old Password</td><td><input type="password" name="oldPassword"></td></tr> 
                <tr><td>New Password</td><td><input type="password" name="newPassword"></td></tr> 
                <tr><td>Re-Type New Password</td><td><input type="password" name="newPassword2"></td></tr> 
            </table>

            <input type="submit" value="submit">
        </form>
    </body>
    
            
        
        
        
    

    <jsp:include page="/template/footer.jsp"/>
</html>     