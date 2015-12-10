<%-- 
    Document   : listProducts
    Created on : 2015年11月25日, 下午11:35:15
    Author     : kwok1
--%>
<%@page import="ict.bean.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script type="text/javascript">
            function cbAll(obj, pid)
            {
                var checkboxs = document.getElementsByName(pid);
                for (var i = 0; i < checkboxs.length; i++) {
                    checkboxs[i].checked = obj.checked;
                }
            }
        </script> 
    </head>
    <body>
        <jsp:include page="/template/header.jsp"/>
        <%
            //if (request.getParameter("action").equals("lsit")) {
            ArrayList<ProductBean> product
                    = (ArrayList<ProductBean>) request.getAttribute("product");
            String deleted = (String) request.getAttribute("deleted");
            out.println("<form action=\"handleProduct\" method=\"get\">");
            out.println("<input type=\"hidden\" name=\"action\" value=\"delete\"/>");
            out.println("<h1>Product</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th><input type=\"checkbox\" name=\"all\" onClick=\"cbAll(this,'pid')\" id=\"all\"><label for=\"all\"  style=\"cursor:pointer\"></label></th><th>Product Id</th> <th> Product Name</th><th> Description</th><th> Manufacturer ID</th ><th> Category ID</th ><th> Price</th ><th> Stock Quantity</th ><th> Photo</th ><th></th>");
            out.println("</tr>");
            // loop through the customer array to display each customer record
            for (int i = 0; i < product.size(); i++) {
                ProductBean c = product.get(i);
                out.println("<tr>");
                out.println("<td><input type=\"checkbox\" name=\"pid\" value=" + c.getpId() + " id=\"" + c.getpId() + "\"><label for=\"" + c.getpId() + "\"  style=\"cursor:pointer\"></label></td>");
                out.println("<td>" + c.getpId() + "</td>");
                out.println("<td>" + c.getpName() + "</td>");
                out.println("<td>" + c.getDescription() + "</td>");
                out.println("<td>" + c.getmId() + "</td>");
                out.println("<td>" + c.getcId() + "</td>");
                out.println("<td> $" + c.getPrice() + "</td>");
                out.println("<td>" + c.getStockQty() + "</td>");
                out.println("<td><img src=\"img/" + c.getProductPhoto() + "\" height=\"100\"></td>");
                out.println("<td><button type=\"button\"><a href=\"handleProduct?action=ledit&pId=" + c.getpId() + "\" class=\"ed\">Edit</a></button></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<button type=\"button\"><a href=\"handleProduct?action=ledit&pId=\" class=\"ed\">Add</a></button>");
            out.println("<input type=\"submit\" value=\"Delete\"/>");
            out.println("</form>");
            if (!deleted.equals("")) {
                out.println("<br/>" + deleted);
            }
            out.println("<br/><button type=\"button\"><a href=\"maintain.jsp\" class=\"bk\">Back</a></button>");
            //}
%>
        <jsp:include page="/template/footer.jsp"/>
    </body>
</html>
