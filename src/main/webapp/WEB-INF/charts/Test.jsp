<%-- 
    Document   : Test
    Created on : Aug 27, 2016, 1:44:43 PM
    Author     : Punj
--%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@ taglib uri="/WEB-INF/struts-jquery-chart-tags.tld" prefix="sjc"%>
<%--<%@ taglib uri="/" prefix="sjc"%>--%>



<!DOCTYPE html>
<html>
    <head>
        <sj:head jqueryui="true"/>
        <script type="text/javascript">
            $.subscribe('chartHover', function (event, data) {
                $("#topicsHover").text(event.originalEvent.pos.x.toFixed(2) + ',' + event.originalEvent.pos.y.toFixed(2));
            });
            $.subscribe('chartClick', function (event, data) {
                var item = event.originalEvent.item;
                if (item) {
                    $("#topicsClick").text("You clicked point " + item.dataIndex + " (" + item.datapoint[0] + "," + item.datapoint[1] + ") in " + item.series.label + ".");
                    event.originalEvent.plot.highlight(item.series, item.datapoint);
                }
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Fill between two Series</h3>
        --- ${parameters} ---
        <sjc:chart id="chartPointsFill" cssStyle="width: 600px; height: 400px;">
            <sjc:chartData
                id="series1"
                label="Series 1"
                data="[[2, 88.0], [3, 93.3], [4, 102.0], [5, 108.5], [6, 115.7], [7, 115.6], [8, 124.6], [9, 130.3], [10, 134.3], [11, 141.4], [12, 146.5], [13, 151.7], [14, 159.9], [15, 165.4], [16, 167.8], [17, 168.7], [18, 169.5], [19, 168.0]]"
                lines="{ show: true }"
                />
            <sjc:chartData
                id="series2"
                label="Series 2"
                data="[[2, 96.8], [3, 105.2], [4, 113.9], [5, 120.8], [6, 127.0], [7, 133.1], [8, 139.1], [9, 143.9], [10, 151.3], [11, 161.1], [12, 164.8], [13, 173.5], [14, 179.0], [15, 182.0], [16, 186.9], [17, 185.2], [18, 186.3], [19, 186.6]]"
                lines="{ show: true, fill: true }"
                fillBetween="series1"
                />
        </sjc:chart>
        
        
        <br/>---------- <br/>
        
         <sjc:chart id="chartPoints" cssStyle="width: 600px; height: 400px;">
    	<sjc:chartData
    		label="List -Points-"
    		list="points"
    		points="{ show: true }"
    		lines="{ show: false }"
     		curvedLines="true"
     		curvedLinesFit="true"
    	/>
    	<sjc:chartData
    		label="List -Points with Null Value-"
    		list="pointsWithNull"
    	/>
    	<sjc:chartData
    		label="Map -Integer, Integer-"
    		list="pointsFromMap"
    	/>
    </sjc:chart>
        
        <br/>
        	<h3>Chart with values from a List or a Map</h3>
    <sjc:chart id="chartPoints" cssStyle="width: 600px; height: 400px;">
    	<sjc:chartData
    		label="List -Points-"
    		list="points"
    		points="{ show: true }"
    		lines="{ show: false }"
     		curvedLines="true"
     		curvedLinesFit="true"
    	/>
    	<sjc:chartData
    		label="List -Points with Null Value-"
    		list="pointsWithNull"
    	/>
    	<sjc:chartData
    		label="Map -Integer, Integer-"
    		list="pointsFromMap"
    	/>
    </sjc:chart>

    <br/>

	<h3>Chart with values from a List with Objects</h3>
	<div id="topicsHover"></div>
	<div id="topicsClick"></div>
    <sjc:chart
    	id="chartObjects"
    	cssStyle="width: 600px; height: 400px;"
    	onClickTopics="chartClick"
    	onHoverTopics="chartHover"
    	xaxisLabel="Label-X"
    	yaxisLabel="Label-Y"
    	yaxisLabelFontSizePixels="22"
    	yaxisLabelFontFamily="Arial"
    	crosshair="true"
    	crosshairMode="xy"
    >
    <sjc:chartData
            list="objList"
            listKey="myKey"
            listValue="myValue"
            points="{ show: true }"
            lines="{ show: false }"
            clickable="true"
            hoverable="true"
            />
	<sjc:chartData
            label="List with Objects"
            list="objList"
            listKey="myKey"
            listValue="myValue"
            points="{ show: false }"
            lines="{ show: true }"
            curvedLines="true"
            curvedLinesFill="true"
            curvedLinesFillColor="#ccc"
            curvedLinesLineWidth="3"
            />
    </sjc:chart>

    <br/>

	<h3>Chart with Date Values</h3>
    <sjc:chart
    	id="chartDate"
    	xaxisMode="time"
    	xaxisTimeformat="%m.%Y"
    	xaxisMin="%{minTime}"
    	xaxisMax="%{maxTime}"
    	xaxisColor="#666"
    	xaxisTickSize="[3, 'month']"
    	xaxisTickColor="#aaa"
    	xaxisPosition="top"
    	yaxisPosition="right"
    	yaxisTickSize="10"
    	cssStyle="width: 600px; height: 400px;"
    >
    	<sjc:chartData
    		id="chartDateData"
    		label="Map -Date, Integer-"
    		list="dateFromMap"
    		color="#990066"
    		lines="{ show: true }"
    	/>
    </sjc:chart>

    <br/>

	<h3>Chart with AJAX Data and Topics</h3>
	<s:url var="chartDataUrl" action="Charts2_"/>
        -- ${doubleMap} ---
    <sjc:chart
    	id="chartAjax"
    	legendLabelBoxBorderColor="#990033"
    	legendPosition="ne"
    	legendShow="#ccc"
    	cssStyle="width: 600px; height: 400px;"
    >
    	<sjc:chartData
    		id="chartAjaxData1"
    		label="Map -Double, Double-"
    		href="%{chartDataUrl}"
    		list="doubleMap"
    		deferredLoading="true"
    		reloadTopics="reloadMap"
    		lines="{show : true}"
    	/>
    	<sjc:chartData
    		id="chartAjaxData2"
    		label="List -ListValue-"
    		href="%{chartDataUrl}"
    		list="objList"
    		listKey="myKey"
    		listValue="myValue"
    		reloadTopics="reloadList"
    		lines="{show : true}"
    	/>
    </sjc:chart>
    <sj:a onClickTopics="reloadMap" button="true" buttonIcon="ui-icon-refresh">Load/Reload Map</sj:a>
    <sj:a onClickTopics="reloadList" button="true" buttonIcon="ui-icon-refresh">Reload List</sj:a>

    <br/>

	<h3>Chart with AJAX Data with Stacked Values</h3>
    <sjc:chart
    	id="chartAjaxTwo"
    	cssStyle="width: 600px; height: 400px;"
    >
    	<sjc:chartData
    		id="chartAjaxTwoData1"
    		label="Map -Double, Double-"
    		href="%{chartDataUrl}"
    		list="doubleMap"
    		bars="{show : true, barWidth: 0.7}"
    		stack="stack1"
    	/>
    	<sjc:chartData
    		id="chartAjaxTwoData2"
    		label="List -ListValue-"
    		href="%{chartDataUrl}"
    		list="objList"
    		listKey="myKey"
    		listValue="myValue"
    		bars="{show : true, barWidth: 0.7}"
    		stack="stack1"
    	/>
    </sjc:chart>

    <br/>
    <br/>

	<h3>A Pie Chart</h3>
    <sjc:chart
    	id="chartPie"
    	cssStyle="width: 600px; height: 400px;"
    	pie="true"
    	pieLabel="true"
    >
    	<sjc:chartData
    		id="pieSerie1"
    		label="Serie 1"
    		data="10"
    	/>
    	<sjc:chartData
    		id="pieSerie2"
    		label="Serie 2"
    		data="3"
    	/>
    	<sjc:chartData
    		id="pieSerie3"
    		label="Serie 3"
    		data="17"
    	/>
    	<sjc:chartData
    		id="pieSerie4"
    		label="Serie 4"
    		data="37"
    	/>
    </sjc:chart>
    
    <br/>

	<h3>A Pie Donut Chart from a Map </h3>
    <sjc:chart
    	id="chartPie2"
    	cssStyle="width: 600px; height: 400px;"
    	legendShow="false"
    	pie="true"
    	pieLabel="true"
    	pieInnerRadius="0.3"
    	pieLabelRadius="0.6"
    	pieLabelBackgroundColor="#555"
    	pieLabelBackgroundOpacity="0.7"
    >
    	<s:iterator value="%{pieDataMap}">
	    	<sjc:chartData
	    		label="%{key}"
	    		data="%{value}"
	    	/>
    	</s:iterator>
    </sjc:chart>

    <br/>

	 
       	  
        <br/>

    </body>
</html>
