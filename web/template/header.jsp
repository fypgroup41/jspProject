<%-- 
 Document   : header
 Created on : Nov 24, 2015, 9:53:11 AM
 Author     : a1
--%>

<%@page import="ict.bean.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>JSP Page</title>
    </head>

    <style>
        .tableBorder{
            border-collapse: collapse;
            border: 1px solid black;
        }
        .ulTable {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }
        .liTable {
            float: left;
        }
        #navigationBar li a {
            display: block;
            width: 200px;
            background-color: #dddddd;
        }
        #searchTable{
            display: inline-block;
            float: right;
            text-align: left;
        }
        #navigationBar{
            display:initial;
            width:100%
        }
        input[type=checkbox] {
            display:none;
        }

        input[type=checkbox] + label
        {
            vertical-align: middle;
            background-color:orange;
            height: 20px;
            width: 20px;
            display:inline-block;
            padding: 0 0 0 0px;

        }
        input[type=checkbox]:checked + label:after
        {
            background-color: orange;
            height: 20px;
            width: 20px;
            display:inline-block;
            padding: 0 0 0 0px;   
            content: "\2713";
            display: block;
            text-align: center;
            line-height: 150%;
            font-size: .85em;
        }
        .enjoy-css {
            display: inline-block;
            -webkit-box-sizing: content-box;
            -moz-box-sizing: content-box;
            box-sizing: content-box;
            padding: 3px 5px;
            border: 1px solid #b7b7b7;
            -webkit-border-radius: 3px;
            border-radius: 3px;
            font: normal 16px/normal "Times New Roman", Times, serif;
            color: rgba(15,15,15,1);
            -o-text-overflow: clip;
            text-overflow: clip;
            background: rgba(252,252,252,1);
            -webkit-box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) inset;
            box-shadow: 2px 2px 2px 0 rgba(0,0,0,0.2) inset;
            text-shadow: 1px 1px 0 rgba(255,255,255,0.66) ;
            -webkit-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
            -moz-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
            -o-transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
            transition: all 200ms cubic-bezier(0.42, 0, 0.58, 1);
        }     .ed, .bk {
            text-decoration:none;
            color: black;
        }
    </style>



    <script>
        function SendSearchRequest(product, gift, keyword, price) {
            var productLink = "";
            var giftLink = "";
            var priceLink = "";
            if (product.checked) {
                productLink = "&product=" + keyword.value;
            }
            if (gift.checked) {
                giftLink = "&gift=" + keyword.value;
            }
            if (price.value >= 0) {
                priceLink = "&price=" + price.value;
            }

            if (productLink == "" && giftLink == "") {
                alert("Please choose the search type");
            } else {
                window.location = "<%=getServletContext().getContextPath() + "/"%>" + "HandleSearch?action=searchByKeywords" + productLink + giftLink + priceLink;
            }
        }
    </script>
    <body>
        <script src="javascript/sorttable.js"></script>
        <jsp:useBean id="userInfo" class="ict.bean.UserBean" scope="session"/>
        <%
            if (userInfo.getuId() != null) {
        %>
        <div align="right">
            <a href="<%=getServletContext().getContextPath() + "/"%>editUser.jsp" title="bonus points:&nbsp;<%=userInfo.getBounspt()%>" > <img  src="<%=getServletContext().getContextPath() + "/"%>icon/user.png" id="user" width="25" height="25" style="vertical-align:top"><span style="vertical-align:top; "><jsp:getProperty name="userInfo" property="uId" />&#x25BE</span></a></div>    
                <%                } else {
                        out.println("<div align=\"right\">Please <a href=\"" + getServletContext().getContextPath() + "/index.jsp \">Login</a></div>");
                    }
                %>




        <h3><img src="<%=getServletContext().getContextPath() + "/"%>img/logo.jpg"  wiidth="100" height="75" style="vertical-align:middle" />  Welcome Stationery Online Station 
            <table id="searchTable" >
                <tr>
                    <td>Search Keyword :</td><td><input type=\"text\"class="enjoy-css" id="keyword"/></td>
                    <td><img   src="<%=getServletContext().getContextPath() + "/"%>icon/search.png" id="search" style="cursor:pointer" onclick=" javascript:SendSearchRequest(product, gift, keyword, price)"></td></tr>
                <tr>
                    <td>$ below
                        <select name="price" id="price">
                            <option value="0">/</option>
                            <option value="250">250</option>
                            <option value="100">100</option>
                            <option value="50">50</option>
                        </select>
                    </td>
                    <td>
                        Product <input type="checkbox" id="product" value="product" ><label for="product"  style="cursor:pointer"></label>       
                        Gift <input type="checkbox" id="gift" value="gift"><label for="gift"  style="cursor:pointer"></label> 
                    </td>
                    <td></td>
                </tr>
            </table>
        </h3>  

        <div id="navigationBar">
            <ul class="ulTable">
                <li class="liTable"><button type="button" onclick="window.location.href='<%=getServletContext().getContextPath() + "/"%>/ShowAll'" class="btn">Home</button></li>
                    <%
                        UserBean user = (UserBean) session.getAttribute("userInfo");
                        if (user.getuType().equals("a")) {
                            out.println("<li class=\"liTable\"><a href=\"maintain.jsp\">Maintain</a></li>");
                        }
                    %>
                <li class="liTable"><button type="button" onclick="window.location.href='<%=getServletContext().getContextPath() + "/"%>/HandleSearch?action=searchAll&item=product'" class="btn">Product</button></li>
                <li class="liTable"><button type="button" onclick="window.location.href='<%=getServletContext().getContextPath() + "/"%>/HandleSearch?action=searchAll&item=gift'" class="btn">Gift</button></li>
                <li class="liTable"><button type="button" onclick="window.location.href='<%=getServletContext().getContextPath() + "/"%>/HandleSearch?action=searchByPrice&price=500&order=asc&item=product'" class="btn">searchByPrice</button></li>
                <li class="liTable"><button type="button" onclick="window.location.href='http://cdnjs.com/libraries/twitter-bootstrap'" class="btn">Basic Button</button></li>
                    <%
                        if (userInfo.getuId() != null) {
                    %>
                <li class="liTable"><button type="button" onclick="window.location.href='<%=getServletContext().getContextPath() + "/"%>showOrder.jsp'" class="btn">Show Order</button></li>
                <li class="liTable"><button type="button" onclick="window.location.href='<%=getServletContext().getContextPath() + "/"%>main?action=logout'" class="btn">Logout</button></li>
                    <%}%>
            </ul>
        </div>



        <hr>

    </body>
</html>
