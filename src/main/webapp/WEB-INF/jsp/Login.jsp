<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>RIK EXIM PRIVATE LIMITED</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
        <link href="web/images/favicon.png" rel="icon" type="image/x-icon"/>

        <link href="web/css/bootstrap.min.css" rel="stylesheet">
        <link href="web/css/font-awesome.css" rel="stylesheet">
        <!-- Main and Responsive CSS -->
        <link href="web/css/main.css" rel="stylesheet">

    </head>
    <body class="loginBg">
        <div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2 loginBox">
            <div class="loginLogo col-md-12 text-center"><a href="#"><img src="web/images/riklogo.png" width="350" height="77" alt="RIK EXIM PRIVATE LIMITED" /></a></div>
            <div class="loginForm col-md-12">
                 <s:form action="Login" theme="simple"> 
                             <s:actionerror />

                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa-2x" aria-hidden="true"></i></span>
                            <!--<input name="email" type="email" placeholder="Email Address" class="form-control" required>-->
                            <s:textfield  name="user" required="true" placeholder="Email Address"   class="form-control"  ></s:textfield> 
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-2x" aria-hidden="true"></i></span>
                            <!--<input name="password" type="password" placeholder="Password" class="form-control" required>-->
                        <s:password  name="password" type="password" placeholder="Password" class="form-control" required="true"> </s:password>
                        </div>
                    </div>
                    <div class="form-group"><input name="" type="submit" value="Login" class="btn btn-lg btn-block"></div>    
                </s:form>        
            </div>
            <div class="col-md-12 forgotLinks text-center"><a href="forgot-password.html">Forgot Password</a>   |   <a href="register.html">Register</a></div>
        </div>


        <!-- jQuery Core -->
        <script src="web/js/jquery-1.10.1.min.js"></script>
        <script src="web/js/bootstrap.min.js"></script>
        <!-- Main Js -->
        <script src="web/js/main.js"></script>


    </body>
</html>
