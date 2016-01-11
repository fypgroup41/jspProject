<%-- 
    Document   : index
    Created on : 2015年11月6日, 上午11:37:41
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #hover-cap-4col .thumbnail {
                position:relative;
                overflow:hidden;
                
            }
            .caption {
                display: none;
                position: absolute;
                top:0;
                left: 0;
                background: rgba(0,0,0,0.4);
                width:600px;
                height: 400px;
                color:#fff !important;
            }    
            i{
                position:absolute;
                top:50%;
                left:50%;
                
                margin: 0 auto;
                z-index: 2000;
                transform: translate(-50%, -50%);
            }</style>



        <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
        <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {

                $("[rel='tooltip']").tooltip();

                $('#hover-cap-4col .thumbnail').hover(
                        function () {
                            $(this).find('.caption').slideDown(250); //.fadeIn(250)
                        },
                        function () {
                            $(this).find('.caption').slideUp(250); //.fadeOut(205)
                        }
                );

            });
        </script>

    </head>
    <body>
        <%--<jsp:forward page="/main"/>--%>



        <ul class="thumbnails" id="hover-cap-4col">
            <li class="span3">
                <div class="thumbnail">
                    <div class="caption">
                        <h4>Caption Title</h4>

                        <i class="fa fa-cart-plus fa-3x"></i>

                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt.</p>
                        <p><a href="#" class="btn btn-inverse" rel="tooltip" title="Preview"><i class="icon-eye-open"></i></a> <a href="#" rel="tooltip" title="Visit Website" class="btn btn-inverse"><i class="icon-share"></i></a></p>
                    </div>
                    <img src="http://placehold.it/600x400" alt="ALT NAME">
                </div>
                <h4>Item Name</h4>
            </li>
            ...
            weitere Elemente
            ...
        </ul>


    </body>
</html>
