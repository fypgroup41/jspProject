<html>
    <head>
        <style>
            img {
                left: 0;
                top: 0;
                width: 100% ;
                height: 100%;
                opacity: 0.3;
            }
            body {
                background-color : #484848;
                margin: 0;
                padding: 0;
                width: 100% ;
                height: 100%;
            }
            h1 {
                color : #000000;
                text-align : center;
                font-family: "SIMPSON";
            }
            div {
                position:absolute;
                top:50%;
                left:50%;
                width: 300px;
                margin: 0 auto;
                z-index: 2000;
                transform: translate(-50%, -50%);
            }
        </style>
    </head>
    <img src="http://placekitten.com/1500/1000">
    <div>
        <h1>Chat Room</h1>
        <form align="center" method="post" action="/JSP_Assignment/servlet/ChatServlet?your_config">
            <table>
                <tr><td>Name:</td><td><input type="TEXT" name="name" value="Eddie" autofocus></td></tr>
                <tr><td>E-mail:</td><td>  <input type="TEXT" name="mail" value="test@gmail.com"></td></tr>
                <tr><td>Color:</td><td> <input id="color" name="color"  type="color" /></td><td><input  type="Submit" value="Enter"></td></tr>
            </table>
        </form>
    </div>
</html> 