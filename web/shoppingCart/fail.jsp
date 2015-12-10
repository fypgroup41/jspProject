<%-- 
    Document   : fail
    Created on : Nov 30, 2015, 7:43:00 AM
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
        <jsp:include page="/template/header.jsp"/>
        Transaction Fail!<br><br>
        <%=request.getAttribute("error")   %>
        <jsp:include page="/template/footer.jsp"/>

    </body>
</html>
