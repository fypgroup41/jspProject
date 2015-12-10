<%-- 
    Document   : listUser
    Created on : 2015年11月28日, 下午05:49:14
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
    </head>
    <body>
        <jsp:include page="/template/header.jsp"/>
        <%
            ArrayList<UserBean> user = (ArrayList<UserBean>) request.getAttribute("user");
            ArrayList<String> yn = (ArrayList<String>) request.getAttribute("yn");
            String updated = (String) request.getAttribute("updated");
            out.println("<h1>User</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>User ID</th><th>Name</th><th> User Type </th><th> Email </th><th> Credit Amount </th><th> Bouns Point </th>"
                    + "<th> Available </th><th> Delivery Address </th><th> State for Avalible for Transaction </th><th> Transactions </th><th> Total Amount </th>");
            out.println("</tr>");
            // loop through the customer array to display each customer record
            UserBean users =(UserBean)session.getAttribute("userInfo");
            for (int i = 0; i < user.size(); i++) {
                if (!user.get(i).getuId().equals(users.getuId())) {
                    out.println("<form action=\"handleUser1\" method=\"get\">");
                    out.println("<input type=\"hidden\" name=\"action\" value=\"update\"/>");
                    UserBean c = user.get(i);
                    out.println("<tr>");
                    out.println("<input type=\"hidden\" name=\"uId\" value=\"" + c.getuId() + "\"/>");
                    out.println("<td>" + c.getuId() + "</td>");
                    out.println("<input type=\"hidden\" name=\"uName\" value=\"" + c.getuName() + "\"/>");
                    out.println("<td>" + c.getuName() + "</td>");
                    out.println("<input type=\"hidden\" name=\"type\" value=\"" + c.getuType() + "\"/>");
                    if (c.getuType().equals("a")) {
                        out.println("<td>Admin</td>");
                    } else {
                        out.println("<td>Menber</td>");
                    }
                    out.println("<input type=\"hidden\" name=\"email\" value=\"" + c.getEmail() + "\"/>");
                    out.println("<td>" + c.getEmail() + "</td>");
                    out.println("<input type=\"hidden\" name=\"creditAmount\" value=\"" + c.getCreditAmount() + "\"/>");
                    out.println("<td>" + c.getCreditAmount() + "</td>");
                    //out.println("<input type=\"hidden\" name=\"bounspt\" value=\""+c.getBounspt()+"\"/>");
                    out.println("<td><input type=\"number\" name=\"bounspt\" required pattern=\"\\d+\" value=\"" + c.getBounspt() + "\"/></td>");
                    out.println("</select></td>");
                    out.println("<td><select name=\"available\">");
                    for (int x = 0; x < yn.size(); x++) {
                        String state = yn.get(x);
                        if (x != c.getAvailable()) {
                            int bl = 0;
                            if (state.equals("true")) {
                                bl = 1;
                            } else {
                                bl = 0;
                            }
                            out.println("<option value=\"" + bl + "\" selected>" + state + "</option>");
                        } else {
                            int bl = 0;
                            if (state.equals("true")) {
                                bl = 1;
                            } else {
                                bl = 0;
                            }
                            out.println("<option value=\"" + bl + "\">" + state + "</option>");
                        }
                    }
                    out.println("</select></td>");
                    out.println("<input type=\"hidden\" name=\"deliveryAddress\" value=\"" + c.getDeliveryAddress() + "\"/>");
                    out.println("<td>" + c.getDeliveryAddress() + "</td>");
                    out.println("</select></td>");
                    out.println("<td><select name=\"a_f_t\">");
                    for (int x = 0; x < yn.size(); x++) {
                        String state = yn.get(x);
                        if (x != c.getA_f_t()) {
                            int bl = 0;
                            if (state.equals("true")) {
                                bl = 1;
                            } else {
                                bl = 0;
                            }
                            out.println("<option value=\"" + bl + "\" selected>" + state + "</option>");
                        } else {
                            int bl = 0;
                            if (state.equals("true")) {
                                bl = 1;
                            } else {
                                bl = 0;
                            }
                            out.println("<option value=\"" + bl + "\">" + state + "</option>");
                        }
                    }
                    out.println("</select></td>");
                    out.println("<input type=\"hidden\" name=\"transactions\" value=\"" + c.getTransactions() + "\"/>");
                    out.println("<td>" + c.getTransactions() + "</td>");
                    out.println("<input type=\"hidden\" name=\"total_amount\" value=\"" + c.getTotal_amount() + "\"/>");
                    out.println("<td>" + c.getTotal_amount() + "</td>");
                    out.println("<td><input type=\"submit\" value=\"Update\"/></td>");

                    out.println("</tr>");
                }
            }
            out.println("</table>");
            out.println("<br/>");
            out.println("<button type=\"button\"><a href=\"maintain.jsp\" class=\"bk\">Back</a></button>");
            if (!updated.equals("")) {
                out.println("<br/>" + updated);
            }

        %>
        <jsp:include page="/template/footer.jsp"/>
    </body>
</html>
