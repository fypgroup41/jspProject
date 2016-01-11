<%-- 
   Document   : ShoppingCart
   Created on : Nov 27, 2015, 10:05:21 PM
   Author     : test
--%>
<%@page import="ict.bean.GiftCartBean"%>
<%@page import="ict.bean.GiftCartListBean"%>
<%@page import="ict.bean.GiftBean"%>
<%@page import="ict.bean.ManufacturerBean"%>
<%@page import="ict.bean.ProductManufacurerBean"%>
<%@page import="ict.bean.ProductCartBean"%>
<%@page import="ict.bean.ProductCartListBean"%>
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
        </style>
        <script>
            function SendUpdateRequest(id) {
                var qty = parseInt(document.getElementById(id).value);
                if (qty !== "NaN" && qty > 0) {
                    window.location = "Cart?action=update&pid=" + id + "&qty=" + qty;
                } else {
                    alert("Please enter a correct format");
                }


            }
            function SendDeleteRequest(id) {
                if (confirm('Are you sure to delete Product ID  :' + id)) {
                    window.location = "Cart?action=delete&pid=" + id;
                } else {

                }




            }
            function SendConfirmRequest() {
                window.location = "Cart?action=confirm";
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

                ProductCartListBean productList = (ProductCartListBean) session.getAttribute("productSession");

                out.println("<h1>Products</h1>");
                out.println("<table border='1' >");
                out.println("<tr>");
                out.println("<th>Product Name</th> <th>Product Description</th><th>Price</th><th>Quantity</th ><th></th > ");
                out.println("</tr>");
                // loop through the customer array to display each customer record
                double total = 0;
                for (int i = 0; i < productList.getList().size(); i++) {
                    out.println("<tr>");
                    out.println("<td><img src=\"" + request.getServletContext().getContextPath() + "/img/" + productList.getList().get(i).getPb().getProductPhoto() + "\" width=\"150px\" height=\"150px\"></img> </td>");
                    out.println("<td>" + productList.getList().get(i).getPb().getDescription() + "</td>");
                    out.println("<td>$" + productList.getList().get(i).getPb().getPrice() + "</td>");
                    out.println("<td>" + productList.getList().get(i).getQty() + "</td>");
                    total += productList.getList().get(i).getQty() * productList.getList().get(i).getPb().getPrice();
                    out.println("<td>$" + (productList.getList().get(i).getQty() * productList.getList().get(i).getPb().getPrice()) + "</td>");
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td></td>");
                out.println("<td>Total</td>");

                out.println("<td>$" + (total) + "</td>");

                out.println("</tr>");

                out.println("</table>");

            } else {
                out.println("The Shopping Cart Is Empty!");

            }
            if (session.getAttribute("giftSession") != null) {
                GiftCartListBean productsManufacurer2 = (GiftCartListBean) session.getAttribute("giftSession");

                if (productsManufacurer2 != null) {
                    out.println("<h4>Gift</h4>");

                    ArrayList<GiftCartBean> manufacturer2 = (ArrayList<GiftCartBean>) productsManufacurer2.getList();

                    out.println("<h1>Products</h1>");
                    out.println("<table border='1' class=\"sortable\">");
                    out.println("<tr>");
                    out.println("<th>Product Photo</th><th>Product Name </th> <th>Product Description</th><th>Price</th><th>Stock Quantity</th ><th></th >");
                    out.println("</tr>");
                    // loop through the customer array to display each customer record
                    if (manufacturer2.isEmpty()) {
                        out.println("<td colspan = 4 align = center>No Such Product(s)</td>");
                    }
                    int total = 0;
                    for (int i = 0; i < manufacturer2.size(); i++) {
                        GiftBean g = manufacturer2.get(i).getGb();
                        out.println("<tr>");
                        out.println("<td><img src=\"img/" + g.getGiftPhoto() + "\" width=\"200\" height=\"200\"></img> </td>");
                        out.println("<td>" + g.getgName() + "</td>");
                        out.println("<td>" + g.getDescription() + "</td>");
                        out.println("<td>" + g.getPt() + "pt</td>");
                        out.println("<td>" + manufacturer2.get(i).getQty() + "</td>");
                        total += manufacturer2.get(i).getQty() + g.getPt();
                        out.println("<td>Quantity : <input  type=\"number\" id=\"" + g.getgId() + "\" name=\"" + g.getgId() + "\" value=\"1\"/>"
                                + "&nbsp&nbsp&nbsp&nbsp<input type=\"button\"  onclick=\"javascript:SendAddGiftRequest('" + g.getgId() + "')\" value=\"Add\"></td><td>Total : " + total + "</td>");

                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<a href=\"searchProduct.jsp\">Back To Search</a>");
                }
            }

        %>
        <img src="<%=request.getServletContext().getContextPath()%>/img/creditcards.png" width="200px" height="30px">


        <form  method="get" action="confirmCart">
            <table>
                <tr>
                    <td>Card Type</td>
                    <td><input type="radio" name="cardType" value="MasterCard" checked>
                        MasterCard
                        <input type="radio" name="cardType" value="Visa">
                        Visa
                        <input type="radio" name="cardType" value="AmericanExpress">
                        AmericanExpress </td>
                </tr>
                <tr>
                    <td>Card Holder</td>
                    <td><input type="text" name="holder"></td>
                </tr>
                <tr>
                    <td>Card Number</td>
                    <td><input type="text" name="cardNo" placeholder="xxxx xxxx xxxx xxxx"></td>
                </tr>
                <tr>
                    <td>Card Security Code</td>
                    <td><input type="text" name="password" placeholder="Enter Four-Digit Number"</td>
                </tr>
                <tr>
                    <td>Receving Date</td>
                    <td><input type="date" name="ReceiveDate" ></td>
                </tr>
                <tr>
                    <td>Delveiry Time</td>

                    <td><select>
                            <option name="ReceiveTime" value="morning">Morning 7am - 10am</option>
                            <option name="ReceiveTime" value="afternoon">Afternoon 2pm-5pm</option>
                            <option name="ReceiveTime" value="evening">Evening 7pm-10pm</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Delivery Type</td>
                    <td><input type="radio" name="receive" value="pickUp" checked>
                        Pick Up
                        <input type="radio" name="receive" value="delivery">
                        Delivery </td>
                </tr>
                <tr>
                    <td>Expiry Date</td>


                    <td><input type="date" name="expiryDate"></td>
                </tr>
            </table>

            <input type="submit" value="submit">      
        </form>

        <jsp:include page="/template/footer.jsp"/>
    </body>
</html>