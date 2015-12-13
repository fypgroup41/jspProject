<%-- 
    Document   : footer
    Created on : Nov 24, 2015, 9:25:20 AM
    Author     : a1
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.Date,java.util.Enumeration" session="true"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <script type="text/javascript" src="<%=getServletContext().getContextPath() + "/"%>js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="<%=getServletContext().getContextPath() + "/"%>js/jquery-ui.js"></script>
    <script type="text/javascript" src="<%=getServletContext().getContextPath() + "/"%>js/jquery.js"></script>
    <script type="application/javascript">
        $(function () {      
        $("#log_status").append("<div style=\"background-color:AntiqueWhite;font-family: Monospace\"><div>"+"<%=session.getAttribute("status")%>"+"</div></div>");
        });

    </script>
    <body style="width:99%">
        <p align="center"><a href="sitmap.jsp">Site Map</a>&nbsp;|&nbsp;<a href="index.jsp">Home Page</a>&nbsp;|&nbsp;<a href="help.jsp">Help</a></p>
        <p align="center">© 1996-2015, stationery_online_station.com, Inc. or its affiliates</p>
        <div id="log_status" style="overflow: scroll;width:50%;height:300px;background-color:Lavender;float:left;">
            <h4>Log Console</h4>
        </div>
        



        <div style="float:right;overflow: scroll;width:50%;height:300px;background-color:wheat">

            <jsp:include page="<%= "/checkSession.jsp"%>"></jsp:include> 
            </div>
            
            
        </body>
    </html>
