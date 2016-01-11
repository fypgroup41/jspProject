<%-- 
    Document   : listProduct
    Created on : Nov 25, 2015, 4:50:11 PM
    Author     : Anson
--%>
<%@page import="java.net.InetAddress"%>
<%@page import="java.util.Random"%>
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

        <style>
            .tableShow{
                float: left;
                overflow:hidden;  
                text-align: center;
            }
            input[type="number"] {
                width: 35px;
            }
            .photo{
                width:200px;
                height:200px;
                padding:  10px 10px 10px 10px;
            }
            th{
                color:blue;
                text-decoration: underline; 
                cursor:pointer;
            }
            table{
                background-color: #FF9900;
            }
            .caption {
                display: none;
                position: absolute;
                top:0;
                left: 0;
                background: rgba(0,0,0,0.4);
                width:210px;
                height: 100%;
                color:#fff !important;

            }    
            i{
                position:absolute;
                top:50%;
                left:50%;
                margin: 0 auto;
                z-index: 2000;
                transform: translate(-50%, -50%);
            }
            #hover-cap-4col .thumbnail {
                position:relative;
                overflow:hidden;
                float:left;
                cursor:pointer;
            }
        </style>
        <script type="text/javascript">
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

        <%
            DB db = new DB();
            ArrayList<CategoryBean> category = (ArrayList<CategoryBean>) db.queryCategory();
            ProductManufacurerBean productsManufacurer = (ProductManufacurerBean) request.getAttribute("productManufacurer");
            if (productsManufacurer != null) {
                out.println("<h4>Product</h4>");
                if (request.getParameter("action") != null) {
                    if (request.getParameter("action").equals("searchAll")) {
                        out.println("      Category : <span style=\"background-color:LightGoldenRodYellow\"><a href=\"" + getServletContext().getContextPath() + "/HandleSearch?action=searchAll&item=product\" >" + "All" + "</a></span>");
                    } else {
                        out.println("      Category : <a href=\"" + getServletContext().getContextPath() + "/HandleSearch?action=searchAll&item=product\" >" + "All" + "</a>");

                    }

                }

                for (int i = 0; i < category.size(); i++) {
                    if (request.getParameter("catid") != null) {
                        if (request.getParameter("catid").equals(category.get(i).getCatId())) {
                            out.println("<span style=\"background-color:LightGoldenRodYellow\"><a href=\"" + getServletContext().getContextPath() + "/HandleSearch?action=searchByCatid&catid=" + category.get(i).getCatId() + "&item=gift\" >" + category.get(i).getCatName() + "</a></span>");
                        } else {
                            out.println("<a href=\"" + getServletContext().getContextPath() + "/HandleSearch?action=searchByCatid&catid=" + category.get(i).getCatId() + "&item=gift\" >" + category.get(i).getCatName() + "</a>");
                        }
                    } else {
                        out.println("<a href=\"" + getServletContext().getContextPath() + "/HandleSearch?action=searchByCatid&catid=" + category.get(i).getCatId() + "&item=gift\" >" + category.get(i).getCatName() + "</a>");
                    }
                }
                ArrayList<ManufacturerBean> manufacturer = (ArrayList<ManufacturerBean>) productsManufacurer.getManufacturer();
                ArrayList<ProductBean> products = (ArrayList<ProductBean>) productsManufacurer.getProducts();
                out.println("<h1>Products</h1>");
                // loop through the customer array to display each customer record
                if (products.isEmpty()) {
                    //Empty
                }
                for (int i = 0; i < products.size(); i++) {
                    ProductBean p = products.get(i);
        %>
        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog -sm">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modal Header</h4>   
                    </div>
                    <div class="modal-body">
                        <p>Some text in the modal.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>

        <ul class="thumbnails" id="hover-cap-4col" class="tableShow" data-toggle="modal" data-target="#myModal">

            <div class="thumbnail" >
                <div class="caption">
                    <i class="fa fa-cart-plus fa-3x"></i>



                </div>
                <%
                    Random ran = new Random();
                    int round = ran.nextInt(5) + 5;

                    out.println("<div  style=\"background-color:yellow;text-align:center\" id=\"" + "product" + i + "\"><img src=\"img/" + p.getProductPhoto() + "\" class=\"photo\"  title=\"" + p.getDescription() + "\"></img>"
                            + "<br><span style=\"text-align:center\"\">" + p.getpName() + "</span>" + "<br>" + "<span style=\"text-align:center;color:red;font-size:large\"\"> $" + p.getPrice() + "</span><br><span style=\"text-align:center;font-size:small;\"> $<strike>" + (int) (p.getPrice() * (1.0 + (double) (round / 10.0))) + ".0</strike>" + "/ " + (int) (100 - ((p.getPrice() / (p.getPrice() * (1.0 + (double) (round / 10.0)))) * 100)) + "% OFF " + "</span>" + "" + "<br > <br> </div>"
                    );
                %>
            </div>

            <%-- <input  type =\"number\" id=\"" + p.getpId() + "\" name=\"" + p.getpId() + "\" value=\"1\"/>"
                            + "<input type=\"button\"  onclick=\"javascript:SendAddProductRequest('" + p.getpId() + "')\" value=\"Add\"> --%>

        </li>     
    </ul>        
    <%
            }

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
    <br><br>

    <jsp:include page="/template/footer.jsp"/>
</body>
</html>
