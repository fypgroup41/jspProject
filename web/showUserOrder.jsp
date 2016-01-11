<%-- 
    Document   : showOrder
    Created on : Nov 30, 2015, 7:45:55 AM
    Author     : test
--%>

<%@page import="ict.bean.ProductBean"%>
<%@page import="ict.bean.ProductOrderBean"%>
<%@page import="ict.bean.OrderBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ict.bean.UserBean"%>
<%@page import="ict.db.DB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .status {
        display: inline;
        padding: 0.2em 0.6em 0.3em;
        font-size: smaller;
        font-weight: bold;
        line-height: 1;
        color: #FFF;
        text-align: center;
        white-space: nowrap;
        vertical-align: baseline;
        border-radius: 0.25em;
        background-color: #DD5600;

    }
    table td{
        border-radius: 0.25em;
        padding: 0px 10px 5px 0px;

    }
    table{
        border-spacing: 15px;
    }
</style>
<html>

    
        <jsp:useBean id="userInfo" class="ict.bean.UserBean" scope="session"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <body>

        <jsp:include page="/template/header.jsp"/>
        <h1>Order Order</h1>



        <%

            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB db = new DB(dbUrl, dbUser, dbPassword);
            ArrayList<OrderBean> ob = db.queryOrderByUID2(userInfo.getuId());

            out.println("<table  border=\"1\" class=\"table\"><tr><th>Order No.</th><th>Delivery Arragement</th><th>Status</th><th>Date</th><th>Delivery Date</th></tr>");
            for (int i = 0; i < ob.size(); i++) {
                ArrayList<ProductOrderBean> bob = db.queryProductOrderByID(ob.get(i).getoId());

                out.println("<tr>");
                out.println("<td>" + ob.get(i).getoId() + "<br></td>");
                out.println("<td><strong>" + ob.get(i).getoMode() + "</strong></td>");
                out.println("<td><span  class=\"status\" style=\"width:300\">" + ob.get(i).getoStatus() + "</span></td>");
                out.println("<td>" + ob.get(i).getoDate().substring(0, ob.get(i).getoDate().length() - 2) + "<br></td>");
                out.println("<td>" + ob.get(i).getDeliveryDate() + "<br></td></tr>");
                out.println("<tr>");
                String productOrder = "";
                String strVar = "";
                strVar += "onmouseover=\"this.width='100'; this.height='100'\" onmouseout=\"this.width='20'; this.height='20'\"";
                int total = 0;
                for (int j = 0; j < bob.size(); j++) {
                    if (j == 0) {

                        productOrder += (j + 1) + ". ";
                        productOrder += "<img src=\"img/" + db.queryProductByID(bob.get(j).getpId()).getProductPhoto() + "\" height=\"20\" width=\"20\"" + strVar + " >";
                        if (bob.get(j).getpId() != null) {
                            productOrder += bob.get(j).getpId() + " " + db.queryProductByID(bob.get(j).getpId()).getpName()+" ";
                        }
                        if (bob.get(j).getgId() != null) {
                            productOrder += bob.get(j).getgId() + " ";
                        }

                        productOrder += "$" + bob.get(j).getPrice() + " * " + bob.get(j).getQty() + "  $" + (int) (bob.get(j).getPrice() * bob.get(j).getQty()) + ".0<br>";
                        total += (int) (bob.get(j).getPrice() * bob.get(j).getQty());
                    } else {
                        productOrder += (j + 1) + ". ";
                        productOrder += "<img src=\"img/" + db.queryProductByID(bob.get(j).getpId()).getProductPhoto() + "\" height=\"20\" width=\"20\"" + strVar + " >";
                        if (bob.get(j).getpId() != null) {
                            productOrder += bob.get(j).getpId() + " " + db.queryProductByID(bob.get(j).getpId()).getpName()+" ";
                        }
                        if (bob.get(j).getgId() != null) {
                            productOrder += bob.get(j).getgId() + " ";
                        }
                        productOrder += "$" + bob.get(j).getPrice() + " * " + bob.get(j).getQty() + "  $" + (int) (bob.get(j).getPrice() * bob.get(j).getQty()) + ".0<br>";
                        total += (int) (bob.get(j).getPrice() * bob.get(j).getQty());
                    }

                }

                out.println("<td colspan=\"5\">" + productOrder + "<p align='right'>" + "All Total : $" + total + ".0 </p></td></tr>");
            }
            out.println("</table>");
        %>
        <jsp:include page="/template/footer.jsp"/>
    </body>
</html>
