<%-- 
    Document   : listProduct
    Created on : Nov 25, 2015, 4:50:11 PM
    Author     : Anson
--%>
<%@page import="ict.db.DB"%>
<%@page import="ict.bean.CategoryBean"%>
<%@page import="ict.bean.GiftBean"%>
<%@page import="ict.bean.ProductManufacurerBean"%>
<%@page import="ict.bean.ManufacturerBean"%>
<%@page import="ict.bean.ProductBean, java.util.ArrayList"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="<%=getServletContext().getContextPath() + "/"%>javascript/sorttable.js"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <style>



            input[type="number"] {
                width: 35px;
            }
            .photo{
                width:200px;
                height:200px;
            }
            th{
                color:blue;
                text-decoration: underline; 
                cursor:pointer;
            }
        </style>
        <script>
            function SendAddProductRequest(id) {

                var qty = parseInt(document.getElementById(id).value);
                if (qty !== "NaN" && qty > 0) {
                    window.location = "<%=getServletContext().getContextPath() + "/"%>Cart?item=product&action=add&pid=" + id + "&qty=" + qty;
                } else {
                    alert("Please enter a correct format");
                }


            }

            function SendAddGiftRequest(id) {

                var qty = parseInt(document.getElementById(id).value);
                if (qty !== "NaN" && qty > 0) {
                    window.location = "<%=getServletContext().getContextPath() + "/"%>Cart?item=gift&action=add&gid=" + id + "&qty=" + qty;
                } else {
                    alert("Please enter a correct format");
                }


            }
        </script>
    </head>
    <body>

        <%@ taglib uri="/tlds/table-taglib.tld" prefix="tableTag"%>
        <jsp:include page="/template/header.jsp"/>  
  <ul class="nav nav-tabs">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="<%=getServletContext().getContextPath() + "/"%>/HandleSearch?action=searchByCatid&catid=cat003&item=gift#" >Menu 1</a></li>
    <li><a href="#" data-toggle="pill">Menu 2</a></li>
    <li><a href="<%=getServletContext().getContextPath() + "/"%>/HandleSearch?action=searchByCatid&catid=cat002&item=gift#  " data-toggle="pill">Menu 3</a></li>
  </ul>
        <%
            DB db = new DB();
            ArrayList<CategoryBean> category = (ArrayList<CategoryBean>) db.queryCategory();

            ProductManufacurerBean productsManufacurer = (ProductManufacurerBean) request.getAttribute("productManufacurer");

            if (productsManufacurer != null) {

                out.println("<h4>Product</h4>");
                for (int i = 0; i < category.size(); i++) {
                    out.println("<a href=\"" + getServletContext().getContextPath() + "/HandleSearch?action=searchByCatid&catid=" + category.get(i).getCatId() + "&item=gift\" >" + category.get(i).getCatName() + "</a>");
                }
                ArrayList<ManufacturerBean> manufacturer = (ArrayList<ManufacturerBean>) productsManufacurer.getManufacturer();
                ArrayList<ProductBean> products = (ArrayList<ProductBean>) productsManufacurer.getProducts();

                out.println("<h1>Products</h1>");
                out.println("<table border='1' class=\"sortable\">");
                out.println("<tr>");
                out.println("<th>Product Photo</th><th>Product Name </th> <th>Product Description</th><th>Price</th><th>Stock Quantity</th ><th></th >");
                out.println("</tr>");
                // loop through the customer array to display each customer record
                if (products.isEmpty()) {
                    out.println("<td colspan = 4 align = center>No Such Product(s)</td>");
                }
                for (int i = 0; i < products.size(); i++) {
                    ProductBean p = products.get(i);
                    out.println("<tr>");
                    out.println("<td><img src=\"img/" + p.getProductPhoto() + "\" class=\"photo\"  title=\"" + p.getDescription() + "\"></img> </td>");
                    out.println("<td>" + p.getpName() + "</td>");
                    out.println("<td>" + manufacturer.get(i).getmName() + "</td>");
                    out.println("<td>" + p.getPrice() + "</td>");
                    out.println("<td>" + p.getStockQty() + "</td>");
                    out.println("<td>Quantity : <input  type=\"number\" id=\"" + p.getpId() + "\" name=\"" + p.getpId() + "\" value=\"1\"/>"
                            + "&nbsp&nbsp&nbsp&nbsp<input type=\"button\"  onclick=\"javascript:SendAddProductRequest('" + p.getpId() + "')\" value=\"Add\"></td>");

                    out.println("</tr>");
                }
                out.println("</table>");

            }

        %>




        <%            ProductManufacurerBean productsManufacurer2 = (ProductManufacurerBean) request.getAttribute("productManufacurer2");

            if (productsManufacurer2 != null) {
                out.println("<h4>Gift</h4>");
                for (int i = 0; i < category.size(); i++) {
                    out.println("<a href=\"" + getServletContext().getContextPath() + "/HandleSearch?action=searchByCatid&catid=" + category.get(i).getCatId() + "&item=product\" >" + category.get(i).getCatName() + "</a>");
                }

                ArrayList<ManufacturerBean> manufacturer2 = (ArrayList<ManufacturerBean>) productsManufacurer2.getManufacturer();
                ArrayList<GiftBean> gifts = (ArrayList<GiftBean>) productsManufacurer2.getGifts();

                out.println("<h1>Products</h1>");
                out.println("<table border='1' class=\"sortable\">");
                out.println("<tr>");
                out.println("<th>Product Photo</th><th>Product Name </th> <th>Product Description</th><th>Price</th><th>Stock Quantity</th ><th></th >");
                out.println("</tr>");
                // loop through the customer array to display each customer record
                if (gifts.isEmpty()) {
                    out.println("<td colspan = 4 align = center>No Such Product(s)</td>");
                }
                for (int i = 0; i < gifts.size(); i++) {
                    GiftBean g = gifts.get(i);
                    out.println("<tr>");
                    out.println("<td><img src=\"img/" + g.getGiftPhoto() + "\" width=\"200\" height=\"200\"></img> </td>");
                    out.println("<td>" + g.getgName() + "</td>");
                    out.println("<td>" + g.getDescription() + "</td>");
                    out.println("<td>" + g.getPt() + "pt</td>");
                    out.println("<td>" + g.getStockQty() + "</td>");
                    out.println("<td>Quantity : <input  type=\"number\" id=\"" + g.getgId() + "\" name=\"" + g.getgId() + "\" value=\"1\"/>"
                            + "&nbsp&nbsp&nbsp&nbsp<input type=\"button\"  onclick=\"javascript:SendAddGiftRequest('" + g.getgId() + "')\" value=\"Add\"></td>");

                    out.println("</tr>");
                }
                out.println("</table>");

            }
        %>
        <jsp:include page="/template/footer.jsp"/>
    </body>
</html>
