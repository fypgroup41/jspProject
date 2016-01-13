<%-- 
    Document   : maintain
    Created on : 2015年11月25日, 下午10:42:42
    Author     : kwok1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/template/header.jsp"/>
        <h1>Manager System</h1>
        
    <li><a href="/handleProduct?action=list">Product Maintain</a></li>
    <li><a href="/handleUser1?action=list">User Setting</a></li>
    <li><a href="/handleOrder?action=list">Order Maintain</a></li>
    <li><a href="/handleGift?action=list">Gift Maintain</a></li>
    <jsp:include page="/template/footer.jsp"/>
    </body>
</html>
