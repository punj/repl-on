<%-- 
    Document   : BaseLayout1
    Created on : Dec 15, 2016, 10:36:23 AM
    Author     : Punj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- Rachna's head tag below starts -->
        <meta charset="utf-8" />
        <title>REPL : Export Data Management</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

        <!--<link href="web/images/favicon.png" rel="icon" type="image/x-icon"/>-->

        <link href="web/css/bootstrap.min.css" rel="stylesheet">
        <script src="web/js/bootstrap.min.js"/>
            <script src = "web/js/jquery-1.10.1.min.js" />
                    <link href = "https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel = "stylesheet" />
                    <script src = "https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js" ></script>
        <link href="web/css/font-awesome.css" rel="stylesheet">
        <!-- Main and Responsive CSS -->
        <link href="web/css/main.css" rel="stylesheet">
        <link href="web/css/bootstrap-select.css" rel="stylesheet">
        <!-- Rachna's head tag above ends -->

    </head>
    <body>
        <%--<tiles:insertAttribute  name="header"/>--%>
        <%--<tiles:insertAttribute  name="menu"/>--%>

        <tiles:insertAttribute  name="body"/>

        <tiles:insertAttribute  name="footer"/>

        <!-- jQuery Core -->
        <script src="web/js/jquery-1.10.1.min.js"></script>
        <script src="web/js/bootstrap.min.js"></script>
        <!-- Main Js -->
        <script src="web/js/main.js"></script>
        <script>
            /**
             * EFECTO PARA FLECHAS EN ACORDEON
             */

            $(document).on('show', '.accordion', function (e) {
                //$('.accordion-heading i').toggleClass(' ');
                $(e.target).prev('.accordion-heading').addClass('accordion-opened');
            });

            $(document).on('hide', '.accordion', function (e) {
                $(this).find('.accordion-heading').not($(e.target)).removeClass('accordion-opened');
                //$('.accordion-heading i').toggleClass('fa-chevron-right fa-chevron-down');
            });


        </script>
    </body>
</html>
