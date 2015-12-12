<%-- 
    Document   : login
    Created on : 2015年11月6日, 上午11:37:58
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <%@page isErrorPage="true" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <style>

            #div-1a {
                position:absolute;
                top:absolute;
                right:0;
                width:70%;
                background-color: white;
            }
            #div-1b {
                position:absolute;
                top:absolute;
                left:0;
                width:30%;
                background-color: pink;
            }
            #de {
                position:static;
                top:0;
                left:0;
                width:100%;
                background-color: white;
            }
        </style>
    </head>
    <body>

        <div id="de">


            <h1 align="center"><p>Welcome Stationery Online Station</p></h1>


        </div >

        <div id="div-1a">
            <p>Login name is admin001 and password is "12345"</p>
            <p>Login name is mem001 and password is "12345"</p>
            <p>Login name is mem002 and password is "12345"</p></div>
        <div id="div-1b"> <form method="post" action="main">
                <input type="hidden" name="action" value="authenticate"/>
                <table border="0">
                    <tr><td><p align="right"><b>User ID</b></td>
                        <td><p><input type="text" name="username" maxLength="10"  size="15"></td>
                    </tr>
                    <tr><td> <p align="right"><b>Password:</b></td>
                        <td><p><input type="password" name="password" maxLength="10" size="15"></td>
                    </tr>
                    <tr><td><p align="center"></p></td><td><p align="center"><input type="button" value="Register" onclick="window.location = 'register.jsp'"><input type="submit" value="Login"></p></td>
                    </tr>


                </table>
            </form>
        </div>


        <jsp:include page="/template/footer.jsp"/>
    </body>
</html>
