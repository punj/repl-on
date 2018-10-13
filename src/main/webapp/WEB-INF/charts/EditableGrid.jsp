<%-- 
    Document   : EditableGrid
    Created on : Aug 30, 2016, 10:19:51 AM
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
        <title>JSP Page</title>
        <st:head jqueryui="true"/>

        <script type="text/javascript">
              $.subscribe('getselectedids', function(event,data) {
    	var s;
    	s = $("#gridmultitable").jqGrid('getGridParam','selarrrow');
    	alert('Selected Rows : '+s);
  	});
	  
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <s:url var="remoteurl" action="Grid_" />
        <s:url var="editurl" action="GridEdit_"/>
        <sj:grid
            id="gridmultitable"
            caption="Customer Examples (Editable/Multiselect)"
            dataType="json"
            href="%{remoteurl}"
            pager="true"
            navigator="true"
            navigatorSearchOptions="{sopt:['eq','ne','lt','gt']}"
            navigatorAddOptions="{height:280,reloadAfterSubmit:true}"
            navigatorEditOptions="{height:380,reloadAfterSubmit:false}"
            navigatorEdit="true"
            navigatorView="true"
            navigatorDelete="true"
            navigatorDeleteOptions="{height:280,reloadAfterSubmit:true}"
            gridModel="gridModel"
            rowList="10,15,20"
            rowNum="15"
            editurl="%{editurl}"
            multiselect="true"
            onSelectRowTopics="rowselect"
            >
            <sj:gridColumn name="id" index="id" title="ID" formatter="integer" editable="false" sortable="false" search="true" searchoptions="{sopt:['eq','ne','lt','gt']}"/>
            <sj:gridColumn name="name" index="name" title="Name" editable="true" edittype="text" sortable="true" search="false"/>
            <sj:gridColumn name="lastName" index="lastName" title="Last Name" sortable="false" hidden="true"/>
            <sj:gridColumn name="firstName" index="firstName" title="First Name" sortable="false" hidden="true"/>
            <sj:gridColumn name="addressLine1" index="addressLine1" title="Adress" sortable="false" hidden="true"/>
            <sj:gridColumn name="country" index="country" title="Country" editable="true" edittype="select" editoptions="{value:'France:France;USA:USA;Australia:Australia;Norway:Norway;Poland:Poland;Germany:Germany;Spain:Spain'}" sortable="false" search="false"/>
            <sj:gridColumn name="city" index="city" title="City" editable="true" edittype="text" sortable="false" search="false"/>
            <sj:gridColumn name="creditLimit" index="creditLimit" title="Credit Limit" formatter="currency" editable="true" edittype="text" sortable="false" search="false"/>
        </sj:grid>
        <br/>
        <st:submit var="grid_multi_getselectedbutton" value="Get Selected Rows" onClickTopics="getselectedids" button="true"/>
    </body>
</html>
