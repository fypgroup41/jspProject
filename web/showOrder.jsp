<%-- 
    Document   : showOrder
    Created on : Nov 30, 2015, 7:45:55 AM
    Author     : test
--%>

<%@page import="ict.bean.ProductOrderBean"%>
<%@page import="ict.bean.OrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.UserBean"%>
<%@page import="ict.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:useBean id="userInfo" class="ict.bean.UserBean" scope="session"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <tr><td></td></tr>
    <tr><td></td></tr>
    <tr><td></td></tr>
    <tr><td></td></tr>
    <tr><td></td></tr>
</table>
<jsp:include page="/template/header.jsp"/>
<h1>Order Order</h1>
<%
    
    DB db = new DB();
    ArrayList<OrderBean> ob = db.queryOrderByUID2(userInfo.getuId());
    out.println("<table>");
    for (int i = 0; i < ob.size(); i++) {
        ArrayList<ProductOrderBean> bob = db.queryProductOrderByID(ob.get(i).getoId());
        
        out.println("<tr>" + bob.size());
        out.println("<td>" + ob.get(i).getoId() + "<br></td>");
        out.println("<td>" + ob.get(i).getoMode() + "<br></td>");
        out.println("<td>" + ob.get(i).getoStatus() + "<br></td>");
        out.println("<td>" + ob.get(i).getoDate() + "<br></td>");
        out.println("<td>" + ob.get(i).getDeliveryDate() + "<br></td>");
        
        for (int j = 0; j < bob.size(); j++) {
            if (j == 0) {
                out.println("<td>" + bob.get(j).getoId() + "</td>");
                out.println("<td>" + bob.get(j).getpId() + "</td>");
                out.println("<td>" + bob.get(j).getgId() + "</td>");
                out.println("<td>" + bob.get(j).getQty() + "</td>");
                out.println("<td>" + bob.get(j).getPrice() + "</td>");
                out.println("</tr>");
            } else {
                
                out.println("<tr><td></td><td></td><td></td><td>" + bob.get(j).getoId() + "</td>");
                out.println("<td>" + bob.get(j).getpId() + "</td>");
                out.println("<td>" + bob.get(j).getgId() + "</td>");
                out.println("<td>" + bob.get(j).getQty() + "</td>");
                out.println("<td>" + bob.get(j).getPrice() + "</td>");
                out.println("</tr>");
                
            }
        }
        out.println("<td>");
    }
    out.println("</table>");
%>
<jsp:include page="/template/footer.jsp"/>
</body>
</html>
