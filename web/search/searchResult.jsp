<%-- 
    Document   : listProduct
    Created on : Nov 25, 2015, 4:50:11 PM
    Author     : Anson
--%>
<%@page import="java.io.OutputStream"%>
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
                margin-right:10px;
            }
        </style>
        <script type="text/javascript">
            function SendAddProductRequest(id) {
              //  var qty = parseInt(document.getElementById(id).value);
              var qty=1;
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
        <!-- Alert  -->
        <div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:none">
            <button type="button" class="close"  >
                <span>&times;</span>
            </button>
            <strong>Product</strong> This products Added
        </div>

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










        <%
            try {

                DB db = new DB();
                ArrayList<CategoryBean> category = (ArrayList<CategoryBean>) db.queryCategory();
                ProductManufacurerBean productsManufacurer = (ProductManufacurerBean) request.getAttribute("productManufacurer");
                if (productsManufacurer != null) {
                    out.println("<h4>Product</h4>");
                    if (request.getParameter("action") != null) {

        %>

        <%-- Category Name --%>
        <%            String link = getServletContext().getContextPath() + "/HandleSearch?action=searchAll&item=product";
                String linkHref = "<a href=\"" + link + "\" >" + "All" + "</a>";
                if (request.getParameter("action").equals("searchAll")) {
                    out.println("      Category : <span style=\"background-color:LightGoldenRodYellow\">" + linkHref + "</span>");
                } else {
                    out.println("      Category : " + linkHref);
                }
            }
            for (int i = 0; i < category.size(); i++) {
                String link = getServletContext().getContextPath() + "/HandleSearch?action=searchByCatid&catid=" + category.get(i).getCatId() + "&item=gift";
                String linkHref = "<a href=\"" + link + "\" >" + category.get(i).getCatName() + "</a>";
                if (request.getParameter("catid") != null) {
                    if (request.getParameter("catid").equals(category.get(i).getCatId())) {
                        out.println("<span style=\"background-color:LightGoldenRodYellow\">" + linkHref + "</span>");
                    } else {
                        out.println(linkHref);
                    }
                } else {
                    out.println(linkHref + "");
                }
            }

        %>

        <%-- Product Table --%>
        <%            ArrayList<ManufacturerBean> manufacturer = (ArrayList<ManufacturerBean>) productsManufacurer.getManufacturer();
            ArrayList<ProductBean> products = (ArrayList<ProductBean>) productsManufacurer.getProducts();
            if (products.isEmpty()) {
                out.println("<br><br>No Such Product(s)<br>");
            }
            for (int j = 0; j < products.size(); j++) {
                ProductBean p = products.get(j);
        %>
        <%--   <ul class="thumbnails" id="hover-cap-4col" class="tableShow" data-toggle="modal" data-target="#myModal">  --%>
        <ul class="thumbnails" id="hover-cap-4col" class="tableShow"  > 
            <div class="thumbnail" onclick="javascript:SendAddProductRequest('<%=p.getpId()%>')">
                <div class="caption">
                    <i class="fa fa-cart-plus fa-3x"></i>
                </div>
                <%                    Random ran = new Random();
                    int round = ran.nextInt(5) + 5;
                    out.println("<div  style=\"background-color:yellow;text-align:center\" id=\"" + p.getpId() + "\"><img src=\"img/" + p.getProductPhoto() + "\" class=\"photo\"  title=\"" + p.getDescription() + "\"></img>"
                            + "<br><span style=\"text-align:center\"\">" + p.getpName() + "</span>" + "<br>" + "<span style=\"text-align:center;color:red;font-size:large\"\"> $" + p.getPrice() + "</span><br><span style=\"text-align:center;font-size:small;\"> $<strike>" + (int) (p.getPrice() * (1.0 + (double) (round / 10.0))) + ".0</strike>" + "/ " + (int) (100 - ((p.getPrice() / (p.getPrice() * (1.0 + (double) (round / 10.0)))) * 100)) + "% OFF " + "</span>" + "" + "<br > <br> </div>"
                    );

                    
                %>

            </div>
        </li>     
    </ul>        








    <%
            }

        }

    %>






    <%-- Gift Table --%>
    <%        ProductManufacurerBean productsManufacurer2 = (ProductManufacurerBean) request.getAttribute("productManufacurer2");

        if (productsManufacurer2
                != null) {
            out.println("<h4>Gift</h4>");
    %>

    <%      String link = getServletContext().getContextPath() + "/HandleSearch?action=searchAll&item=gift";
        String linkHref = "<a href=\"" + link + "\" >" + "All" + "</a>";
        if (request.getParameter("action").equals("searchAll")) {
            out.println("      Category : <span style=\"background-color:LightGoldenRodYellow\">" + linkHref + "</span>");
        } else {
            out.println("      Category : " + linkHref);
        }

        for (int i = 0; i < category.size(); i++) {
            link = getServletContext().getContextPath() + "/HandleSearch?action=searchByCatid&catid=" + category.get(i).getCatId() + "&item=gift";
            linkHref = "<a href=\"" + link + "\" >" + category.get(i).getCatName() + "</a>";
            if (request.getParameter("catid") != null) {
                if (request.getParameter("catid").equals(category.get(i).getCatId())) {
                    out.println("<span style=\"background-color:LightGoldenRodYellow\">" + linkHref + "</span>");
                } else {
                    out.println(linkHref);
                }
            } else {
                out.println(linkHref + "");
            }
        }
    %>

    <%        ArrayList<ManufacturerBean> manufacturer2 = (ArrayList<ManufacturerBean>) productsManufacurer2.getManufacturer();
        ArrayList<GiftBean> gifts = (ArrayList<GiftBean>) productsManufacurer2.getGifts();

        // loop through the customer array to display each customer record
        if (gifts.isEmpty()) {
            out.println("<br><br>No Such Product(s)<br>");
        }
        for (int k = 0; k < gifts.size(); k++) {
            GiftBean g = gifts.get(k);
    %>

    <ul class="thumbnails" id="hover-cap-4col" class="tableShow" > 
        <div class="thumbnail" >
            <div class="caption">
                <i class="fa fa-cart-plus fa-3x"></i>
            </div>
            <%                    Random ran = new Random();
                int round = ran.nextInt(5) + 5;
                out.println("<div  style=\"background-color:yellow;text-align:center\" id=\"" + "product" + k + "\"><img src=\"img/" + g.getGiftPhoto() + "\" class=\"photo\"  title=\"" + g.getDescription() + "\"></img>"
                        + "<br><span style=\"text-align:center\"\">" + g.getgName() + "</span>" + "<br>" + "<span style=\"text-align:center;color:red;font-size:large\"\"> $" + g.getPt() + "</span><br><span style=\"text-align:center;font-size:small;\">" + "</span>" + "" + "<br > <br> </div>"
                );
            %>
        </div>
    </li>     
</ul> 
<%
            }
        }

    } catch (Exception ex) {
        out.print(ex.getMessage());
    }


%>
<br><br>
<jsp:include page="/template/footer.jsp"/>  
</body>
</html>
