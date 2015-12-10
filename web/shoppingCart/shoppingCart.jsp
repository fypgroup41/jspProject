<%-- 
    Document   : ShoppingCart
    Created on : Nov 27, 2015, 10:05:21 PM
    Author     : test
--%>
<%@page import="ict.bean.ProductCartBean"%>

<%@page import="ict.bean.ProductCartListBean"%>
<%@page import="ict.bean.GiftCartBean"%>
<%@page import="ict.bean.GiftCartListBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.UserInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            input[type="number"] {
                width: 55px;
            }
            .photo{
                width:150px;
                height: 150px
            }
        </style>
        <script>
            function SendUpdateProductRequest(id) {
                var qty = parseInt(document.getElementById(id).value);
                if (qty !== "NaN" && qty > 0) {
                    window.location = "<%=getServletContext().getContextPath() + "/"%>Cart?action=update&item=product&pid=" + id + "&qty=" + qty;
                } else {
                    alert("Please enter a correct format");
                }


            }
            function SendDeleteProductRequest(id) {
                if (confirm('Are you sure to delete Product ID  :' + id)) {
                    window.location = "<%=getServletContext().getContextPath() + "/"%>Cart?action=delete&item=product&pid=" + id;
                } else {

                }




            }


            function SendUpdateGiftRequest(id) {
                var qty = parseInt(document.getElementById(id).value);
                if (qty !== "NaN" && qty > 0) {
                    window.location = "<%=getServletContext().getContextPath() + "/"%>Cart?action=update&item=gift&gid=" + id + "&qty=" + qty;
                } else {
                    alert("Please enter a correct format");
                }


            }
            function SendDeleteGiftRequest(id) {
                if (confirm('Are you sure to delete Product ID  :' + id)) {
                    window.location = "<%=getServletContext().getContextPath() + "/"%>Cart?action=delete&item=gift&gid=" + id;
                } else {

                }




            }


            function SendConfirmRequest() {
                window.location = "<%=getServletContext().getContextPath() + "/"%>Cart?action=confirm";
            }
        </script>
    </head>
    <body>
        <jsp:include page="/template/header.jsp"/>
        <%
            if (session.getAttribute("userInfo") == null) {
                response.sendRedirect("login.jsp");
            }

            if (session.getAttribute("productSession") != null) {
                ProductCartListBean list = (ProductCartListBean) session.getAttribute("productSession");
                ArrayList<ProductCartBean> productCart = list.getList();

                out.println("<h1>Products</h1>");
                out.println("<table border='1' >");
                out.println("<tr>");
                out.println("<th>Product Name</th> <th>Product Description</th><th>Price</th><th>Quantity</th ><th></th ><th></th >    ");
                out.println("</tr>");
                // loop through the customer array to display each customer record

                double total = 0;
                for (int i = 0; i < productCart.size(); i++) {
                    out.println("<tr>");
                    out.println("<td><img src=\"" + request.getServletContext().getContextPath() + "/" + "img/" + productCart.get(i).getPb().getProductPhoto() + "\" class=\"photo\"></img> </td>");
                    out.println("<td>" + productCart.get(i).getPb().getDescription() + "</td>");
                    out.println("<td>$" + productCart.get(i).getPb().getPrice() + "</td>");
                    out.println("<td>" + productCart.get(i).getQty() + "</td>");
                    total += productCart.get(i).getPb().getPrice() * productCart.get(i).getQty();
                    out.println("<td>$" + (productCart.get(i).getPb().getPrice() * productCart.get(i).getQty()) + "</td>");
                    out.println("<td>");
                    out.println("Quantity : <input  type=\"number\" id=\"" + productCart.get(i).getPb().getpId() + "\" name=\"" + productCart.get(i).getPb().getpId() + "\" value=\"" + productCart.get(i).getQty() + "\"/>"
                            + "&nbsp&nbsp&nbsp&nbsp<br><input type=\"button\"  onclick=\"javascript:SendUpdateProductRequest('" + productCart.get(i).getPb().getpId() + "')\" value=\"Update\"><input type=\"button\"  onclick=\"javascript:SendDeleteProductRequest('" + productCart.get(i).getPb().getpId() + "')\" value=\"Delete\"></td>");
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td>Total</td>");

                out.println("<td>$" + (total) + "</td>");

                out.println("<td><input type=\"button\"  onclick=\"javascript:SendConfirmRequest()\" value=\"Next\"></td>");
                out.println("</tr>");

                out.println("</table>");

            } else {
                out.println("The Product Cart Is Empty!");
            }

            if (session.getAttribute("giftSession") != null) {

                GiftCartListBean list = (GiftCartListBean) session.getAttribute("giftSession");
                ArrayList<GiftCartBean> giftList = list.getList();

                out.println("<h1>Gift</h1>");
                out.println("<table border='1' >");
                out.println("<tr>");
                out.println("<th>Gift Name</th> <th>Gift Description</th><th>Bouns Point</th><th>Quantity</th ><th></th ><th></th >    ");
                out.println("</tr>");
                // loop through the customer array to display each customer record

                double total = 0;
                for (int i = 0; i < giftList.size(); i++) {
                    out.println("<tr>");
                    out.println("<td><img src=\"" + request.getServletContext().getContextPath() + "/" + "img/" + giftList.get(i).getGb().getGiftPhoto() + "\" class=\"photo\"></img> </td>");
                    out.println("<td>" + giftList.get(i).getGb().getDescription() + "</td>");
                    out.println("<td>" + giftList.get(i).getGb().getPt() + "</td>");
                    out.println("<td>" + giftList.get(i).getQty() + "</td>");
                    total += giftList.get(i).getGb().getPt() * giftList.get(i).getQty();
                    out.println("<td>$" + (giftList.get(i).getGb().getPt() * giftList.get(i).getQty()) + "</td>");
                    out.println("<td>");
                    out.println("Quantity : <input  type=\"number\" id=\"" + giftList.get(i).getGb().getgId() + "\" name=\"" + giftList.get(i).getGb().getgId() + "\" value=\"" + giftList.get(i).getQty() + "\"/>"
                            + "&nbsp&nbsp&nbsp&nbsp<br><input type=\"button\"  onclick=\"javascript:SendUpdateGiftRequest('" + giftList.get(i).getGb().getgId() + "')\" value=\"Update\"><input type=\"button\"  onclick=\"javascript:SendDeleteGiftRequest('" + giftList.get(i).getGb().getgId() + "')\" value=\"Delete\"></td>");
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td>Total</td>");

                out.println("<td>$" + (total) + "</td>");

                out.println("<td><input type=\"button\"  onclick=\"javascript:SendConfirmRequest()\" value=\"Next\"></td>");
                out.println("</tr>");

                out.println("</table>");

            } else {
                out.println("The Gift Cart Is Empty!");
            }
        %>

        <jsp:include page="/template/footer.jsp"/>


    </body>
</html>
