<%-- 
    Document   : Autocompleter
    Created on : Aug 30, 2016, 11:07:57 AM
    Author     : Punj
--%>

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-grid-tags" prefix="sj" %>
<%@taglib uri="/struts-jquery-tags" prefix="st" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <st:head jqueryui="true"/>

    </head>
    <body>
        <h1>SGN</h1>
        <s:form id="formAutocompleteJson" action="echo"  >
            <s:url var="jsonlanguages" action="Autocomplete_"/> 
            <%--<s:url var="jsonlanguages" action=""/>--%> 
            <st:autocompleter 
                id="languages" 
                name="echo"
                label="Handle a Array"
                href="%{jsonlanguages}" 
                delay="50" 
                loadMinimumCount="2"
                onChangeTopics="autocompleteChange"
                onFocusTopics="autocompleteFocus"
                onSelectTopics="autocompleteSelect"
                />
            ---${customers}---
            <s:url var="jsoncustomers" action="Autocomplete_"/> 
            <st:autocompleter 
                id="customers" 
                name="echo"
                label="Handle a List"
                href="%{jsoncustomers}" 
                list="customers"
                listValue="name" 
                listKey="id" 
                listLabel="label"
                delay="50" 
                loadMinimumCount="2"
                onChangeTopics="autocompleteChange"
                onFocusTopics="autocompleteFocus"
                onSelectTopics="autocompleteSelect"
                placeholder="Select a Customer"
                />
            <st:autocompleter 
                id="customersMap" 
                name="echo"
                label="Handle a Map"
                href="%{jsoncustomers}" 
                list="customersMap"
                delay="50" 
                loadMinimumCount="2"
                onChangeTopics="autocompleteChange"
                onFocusTopics="autocompleteFocus"
                onSelectTopics="autocompleteSelect"
                />
            <st:submit
                id="submitFormAutocompleteJson" 
                targets="result" 
                button="true" 
                value="Submit" 
                indicator="indicator"
                />
        </s:form>
    </body>
</html>
