<%-- 
    Document   : listOrder
    Created on : 2015年11月26日, 上午10:50:27
    Author     : kwok1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ict.bean.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/template/header.jsp"/>
        <%
            ArrayList<OrderBean> order = (ArrayList<OrderBean>) request.getAttribute("order");
            ArrayList<String> om = (ArrayList<String>) request.getAttribute("om");
            ArrayList<String> os = (ArrayList<String>) request.getAttribute("os");
            String updated = (String)request.getAttribute("updated");
            out.println("<h1>Order</h1>");

            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>Order ID </th> <th> User ID</th><th>Order Mode</th><th>Order Status</th>"
                    + "<th>Order Date</th><th>Delivery Date</th>");
            out.println("</tr>");
            // loop through the customer array to display each customer record
            for (int i = 0; i < order.size(); i++) {
                out.println("<form action=\"handleOrder\" method=\"get\">");
                out.println("<input type=\"hidden\" name=\"action\" value=\"update\"/>");
                
                OrderBean c = order.get(i);
                out.println("<input type=\"hidden\" name=\"oId\" value=\""+c.getoId()+"\"/>");
                out.println("<input type=\"hidden\" name=\"uId\" value=\""+c.getuId()+"\"/>");
                out.println("<tr>");
                out.println("<td><a href=\"handleOrder?action=details&oId="+ c.getoId()+"\">" + c.getoId() + "</a></td>");
                out.println("<td>" + c.getuId() + "</td>");

                out.println("<td><select name=\"oMode\">");
                for (int x = 0; x < om.size(); x++) {
                    String mode = om.get(x);
                    if (mode.equals(c.getoMode())) {
                        out.println("<option value=\"" + mode + "\" selected>" + mode + "</option>");
                    } else {
                        out.println("<option value=\"" + mode + "\">" + mode + "</option>");
                    }
                }
                out.println("</select></td>");
                out.println("<td><select name=\"oStatus\">");
                for (int x = 0; x < os.size(); x++) {
                    String state = os.get(x);
                    if (state.equals(c.getoStatus())) {
                        out.println("<option value=\"" + state + "\" selected>" + state + "</option>");
                    } else {
                        out.println("<option value=\"" + state + "\">" + state + "</option>");
                    }
                }
                out.println("</select></td>");
                out.println("<input type=\"hidden\" name=\"oDate\" value=\""+c.getoDate()+"\"/>");
                out.println("<td>" + c.getoDate() + "</td>");
                out.println("<input type=\"hidden\" name=\"deliveryDate\" value=\""+c.getDeliveryDate()+"\"/>");
                out.println("<td>" + c.getDeliveryDate() + "</td>");
                out.println("<td><input type=\"submit\" value=\"Update\"/></td>");
                out.println("</tr>");

                out.println("</form>");
            }
            out.println("</table>");
            out.println("<br/>");
                out.println("<button type=\"button\"><a href=\"maintain.jsp\" class=\"bk\">Back</a></button>");
            if(!updated.equals(""))
                out.println("<br/>"+updated);


        %>
        <jsp:include page="/template/footer.jsp"/>
    </body>
</html>
