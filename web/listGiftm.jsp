<%-- 
    Document   : listGiftm
    Created on : 2015年11月30日, 上午03:54:03
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
                ArrayList<GiftBean> gift
                        = (ArrayList<GiftBean>) request.getAttribute("gift");
                String deleted = (String)request.getAttribute("deleted");
                out.println("<form action=\"handleGift\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"action\" value=\"delete\"/>");
                out.println("<h1>Gift</h1>");
                out.println("<table border='1' >");
                out.println("<tr>");
                out.println("<th><input type=\"checkbox\" name=\"all\" onClick=\"cbAll(this,'gid')\" id=\"all\"><label for=\"all\"  style=\"cursor:pointer\"></label></th><th>Gift Id</th> <th> Gift Name</th><th> Point</th><th>Description</th><th> Category ID</th ><th> Stock Quantity</th ><th> Manufacturer ID</th ><th> Photo</th ><th></th>");
                out.println("</tr>");
                // loop through the customer array to display each customer record
                for (int i = 0; i < gift.size(); i++) {
                    GiftBean c = gift.get(i);
                    out.println("<tr>");
                    out.println("<td><input type=\"checkbox\" name=\"gid\" value=" + c.getgId() + " id=\""+ c.getgId() +"\"><label for=\""+ c.getgId() +"\"  style=\"cursor:pointer\"></td>");
                    out.println("<td>" + c.getgId() + "</td>");
                    out.println("<td>" + c.getgName() + "</td>");
                    out.println("<td>" + c.getPt() + "</td>");
                    out.println("<td>" + c.getDescription() + "</td>");
                    out.println("<td>" + c.getCatId() + "</td>");
                    out.println("<td>" + c.getStockQty() + "</td>");
                    out.println("<td>" + c.getmId()+ "</td>");
                    out.println("<td><img src=\"img/"+c.getGiftPhoto()+"\" height=\"100\"></td>");
                    out.println("<td><button type=\"button\"><a href=\"handleGift?action=ledit&gId=" + c.getgId() + "\" class=\"ed\">Edit</a></button></td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<button type=\"button\"><a href=\"handleGift?action=ledit&gId=\" class=\"ed\">Add</a></button>");
                out.println("<input type=\"submit\" value=\"Delete\"/>");
                out.println("</form>");
                if(!deleted.equals(""))
                out.println("<br/>"+deleted);
                out.println("<br/><button type=\"button\"><a href=\"maintain.jsp\" class=\"bk\">Back</a></button>");
            //}
        %>
<jsp:include page="/template/footer.jsp"/>
    </body>
</html>
