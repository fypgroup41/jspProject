<%@page import="java.util.Enumeration"%>
<%@page import = "java.util.Date" session="true"%>



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