<%@page import="java.net.InetAddress"%>
<%@page import="java.util.Enumeration"%>
<%@page import = "java.util.Date" session="true"%>


  <%
            out.print(request.getRequestURL() + "<br>");
           out.print(request.getRequestURI() + "<br>");

            
            out.print(InetAddress.getLocalHost() + "<br>");

            
            out.print(request.getQueryString() + "<br>");

            out.print(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "<br>");
            out.print(request.getContextPath() + "<br>");
            out.print(request.getServletPath() + "<br>");

        %>
<h1>Session in JSP</h1>
Your session ID is :  <%=session.getId()%>
<br>
Session created at  <%=new Date(session.getCreationTime())%>
<br>
Last time of activity <%=new Date(session.getLastAccessedTime())%>
<br>
<% Enumeration keys = session.getAttributeNames();
    while (keys.hasMoreElements()) {
        String key = (String) keys.nextElement();
        out.println(key + ": " + session.getValue(key) + "<br>");
 }%>