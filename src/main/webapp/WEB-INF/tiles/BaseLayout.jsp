<%-- 
    Document   : BaseLayout
    Created on : Aug 27, 2016, 12:16:24 PM
    Author     : Punj
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <!-- head tag from Rachita -->

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

        <link href="web/images/favicon.png" rel="icon" type="image/x-icon"/>

        <link href="web/css/bootstrap.min.css" rel="stylesheet" >
        <!--<link href="web/js/bootstrap.min.js" rel="stylesheet">-->
        <!--<link href="web/js/jquery-1.10.1.min.js" rel="stylesheet">-->
        <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
        <link href="web/css/font-awesome.css" rel="stylesheet">
        <!-- Main and Responsive CSS -->
        <link href="web/css/main.css" rel="stylesheet">
        <link href="web/css/bootstrap-select.css" rel="stylesheet">

        <!-- head tag from Rachita ends above-->


        <title>
            <tiles:insertAttribute name="title" ignore="true" />
        </title>
    </head>
    <body>
        <!--table border="1" cellpadding="2" cellspacing="2" align="center">
            <tr>
                <td height="30" colspan="2">
        <!--tiles:insertAttribute name="header" />
    </td>
</tr>
<tr>
    <td height="250">
        <!--tiles:insertAttribute name="menu" />
    </td>
    <td width="350">
        <!--tiles:insertAttribute name="body" />
    </td>
</tr>
<tr>
    <td height="30" colspan="2">
        <!--tiles:insertAttribute name="footer" />
    </td>
</tr>
</table-->
        <tiles:insertAttribute name="header" />

        <tiles:insertAttribute name="menu" />
        <tiles:insertAttribute name="body" />

        <tiles:insertAttribute name="footer" />
        <script src="web/js/jquery-1.10.1.min.js" type="text/javascript"></script>
        <!--<script src="web/js/bootstrap.min.js" type="text/javascript"></script>-->
        <!--<script src="web/js/main.js" type="text/javascript"></script>-->
        <!--<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"  type="text/javascript"></script>-->


    </body>
    
    
</html>
