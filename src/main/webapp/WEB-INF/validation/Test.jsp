<%-- 
    Document   : Test
    Created on : Aug 29, 2016, 2:08:11 PM
    Author     : Punj
NOTE: VALIDATION ONLY WORKS IF THEME IS SET AS SIMPLE OR XHTML
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="jqm" uri="/struts-jquery-mobile-tags" %>
<%@ taglib prefix="jbs" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <jbs:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Form - Struts2 Validation Example (Annotations)</title>
    </head>
    <body>
            <div align="center">
                    <h2>Login</h2>
                    <h3>Struts2 Validation Example (Using Annotations)</h3>
            <%--<s:actionmessage />--%>
                    <s:form action="Validation_execute" method="post" validate="true"  theme="bootstrap" cssClass="well form-vertical">
                            <s:textfield key="global.username" label="E-mail" name="email" />
                            <s:password label="Password" name="password" />
                            <s:submit value="Login" effect="highlight" cssClass="btn-success" />
                   
                        </s:form>
                </div>
    </body>
</html>