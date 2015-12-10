<%-- 
    Document   : editProduct
    Created on : 2015年11月27日, 下午01:42:43
    Author     : kwok1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@page import="ict.bean.*"%>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ProductBean pb  = (ProductBean)request.getAttribute("pb");
            String type = pb.getpId() != null ? "edit" : "add";
            String pId = pb.getpId() != null ? pb.getpId() : "";
            String pName = pb.getpName() != null ? pb.getpName() : "";
            String description = pb.getDescription() != null ? pb.getDescription() : "";
            String mId = pb.getmId() != null ? pb.getmId() : "";
            String cId = pb.getcId() != null ? pb.getcId() : "";
            double price = pb != null ? pb.getPrice() : 0;
            int stockQty = pb != null ? pb.getStockQty() : 0;
            if (type.equals("add")) {
                out.println("<h1>Add Product</h1>");
            } else {
                out.println("<h1>Edit Product</h1>");
            }
        %>
        <form method="get" action="handleProduct">
            <input type="hidden" name="action" value="<%=type%>" />
            ID <input name="pId" type="text" value="<%out.print(pId);%>" <% if (pId.equals("")) {
                } else {
                    out.print("readonly=\"readonly\"");
                }%>/> <br>
            Name <input name="pName" type="text" value="<%=pName%>"/> <br>
            Description <input name="description" type="text" value="<%=description%>"/> <br>
            mId <input name="mId" type="text" value="<%=mId%>"/> <br>
            cId <input name="cId" type="text" value="<%=cId%>"/> <br>
            Price <input name="price" type="text" value="<%=price%>"/> <br>
            Stock Qty <input name="stockQty" type="text" value="<%=stockQty%>"/> <br>
            <td ><input type="submit" value="submit"/> <br>
        </form>
    </body>
</html>
