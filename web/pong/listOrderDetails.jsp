<%-- 
    Document   : listOrderDetails
    Created on : 2015年11月27日, 下午11:42:44
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
            ArrayList<ProductOrderBean> pod = (ArrayList<ProductOrderBean>) request.getAttribute("pod");
            out.println("<h1>Order Details</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>Order ID</th><th colspan=\"2\"> " + pod.get(0).getoId() + "</th></tr>");
            for (int i = 0; i < pod.size(); i++) {
                ProductOrderBean c = pod.get(i);
                if (c.getpId() != null&&i==pod.size()-1) {
                    out.println("<tr><th colspan=\"3\">Product</th></tr>");
                    out.println("<tr><th> Product ID</th><th> Qty </th ><th> Price </th >");
                    out.println("</tr>");
                }
            }
            for (int i = 0; i < pod.size(); i++) {
                ProductOrderBean c = pod.get(i);
                if (c.getpId() != null) {
                    out.println("<tr>");
                    out.println("<td>" + c.getpId() + "</td>");
                    out.println("<td>" + c.getQty() + "</td>");
                    out.println("<td>$ " + c.getPrice() + "</td>");
                    out.println("</tr>");
                }
            }
            for (int i = 0; i < pod.size(); i++) {
                ProductOrderBean c = pod.get(i);

                if (c.getgId() != null&&i==pod.size()-1) {
                    out.println("<tr><th colspan=\"3\">Gift</th></tr>");
                    out.println("<tr><th> Gift ID</th><th> Qty </th ><th> Price </th >");
                    out.println("</tr>");
                }
            }
            for (int i = 0; i < pod.size(); i++) {
                ProductOrderBean c = pod.get(i);

                if (c.getgId() != null) {
                    out.println("<tr>");
                    out.println("<td>" + c.getgId() + "</td>");
                    out.println("<td>" + c.getQty() + "</td>");
                    out.println("<td>$ " + c.getPrice() + "</td>");
                    out.println("</tr>");
                }
            }
            double total=0;
            for (int i = 0; i < pod.size(); i++) {
                ProductOrderBean c = pod.get(i);
                total +=c.getPrice();
            }
            out.println("<tr><th colspan=\"2\">Total</th><th>$ "+total+"</th></tr>");
            out.println("</table>");
            out.println("</form><br/>");
                out.println("<button type=\"button\"><a href=\"handleOrder?action=list\" class=\"bk\">Back</a></button>");

        %>
        <jsp:include page="/template/footer.jsp"/>
    </body>
</html>
