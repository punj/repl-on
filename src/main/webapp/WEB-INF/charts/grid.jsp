<%-- 
    Document   : grid
    Created on : Aug 30, 2016, 9:42:37 AM
    Author     : Punj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-grid-tags" prefix="sj" %>
<%@taglib uri="/struts-jquery-tags" prefix="st" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGN Grid</title>
                <st:head jqueryui="true"/>

    </head>
    <body>
        <h1>SGN Grid!</h1>
        <%--<s:url var="remoteurl" action="grid-data-provider" namespace="/grid"/>--%>
        <s:url var="remoteurl" action="Grid_"  />
        <sj:grid
            id="gridtable"
            caption="Customer Examples"
            dataType="json"
            href="%{remoteurl}"
            pager="true"
            gridModel="gridModel"
            rowList="10,15,20"
            rowNum="15"
            rownumbers="true"
            >
            <sj:gridColumn name="id" index="id" title="ID" formatter="integer" sortable="false"/>
            <sj:gridColumn name="name" index="name" title="Name" sortable="true"/>
            <sj:gridColumn name="country" index="country" title="Country" sortable="false"/>
            <sj:gridColumn name="city" index="city" title="City" sortable="false"/>
            <sj:gridColumn name="creditLimit" index="creditLimit" title="Credit Limit" formatter="currency" sortable="false"/>
        </sj:grid>
    </body>
</html>
