<%-- 
    Document   : view
    Created on : Oct 24, 2018, 12:15:18 PM
    Author     : Punj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" buffer="5000kb" autoFlush="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Export Data</title>
        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.themes.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


        <!-- eximdb_view.jsp -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!--<script src="web/js/bootstrap.min.js"/>-->
        <script src = "web/js/jquery-1.10.1.min.js" />
        <link href = "https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel = "stylesheet" />
        <script src = "https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js" ></script>
        <link href="web/css/font-awesome.css" rel="stylesheet">
        <!-- Main and Responsive CSS -->
        <link href="web/css/main.css" rel="stylesheet">
        <link href="web/css/bootstrap-select.css" rel="stylesheet">


        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <script src="js/exim/view.js" type="text/javascript"></script>
        <script src="js/grid/eximviewGrid.js" type="text/javascript"></script>


        <script src="https://cdn.ckeditor.com/4.10.1/standard/ckeditor.js"></script>



        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <!--<link rel="stylesheet" href="/resources/demos/style.css">-->
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>


        <script>
//            alert(window.frames['loadNewPage'].contentDocument.document.getElementById('consigneeName1').value());
//            
            var changeDetected = false;
//            alert( document.getElementById('consigneeName1'));
            $(document).ready(function () {
                // alert("GRID VIEW.jsp");
                $('#loader1').hide();

                $('#timepicker').timepicker({
                    timeFormat: 'HH:mm',
                    interval: 15,
                    // minTime: '1',
                    //maxTime: '6:00pm',
                    defaultTime: '08:00',
                    //startTime: '10:00',
                    dynamic: false,
                    dropdown: true,
                    scrollbar: true
                });


                var $form = $('form'),
                        origForm = $form.serialize();

                $('form :input').on('change input', function () {
//                    alert('changed ');
                    $('.change-message').toggle($form.serialize() !== origForm);
                    changeDetected = $form.serialize() !== origForm;
                });
            });

            // //1300.7.Aug.2015@followupthen.com
            // 31.Oct.2018
            $(function () {
                $("#datepicker").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    minDate: 0,
                    dateFormat: 'd.M.yy',
                    // setDate: 1,
//                            'dd/mm/yy' 

                });
            });

            function copyFunction() {
                var copyText = document.getElementById("consigneeName1");
                copyText.select();
                document.execCommand("copy");

                var tooltip = document.getElementById("myTooltip");
                tooltip.innerHTML = "Copied: " + copyText.value;
            }

            function outFunc() {
                var tooltip = document.getElementById("myTooltip");
                tooltip.innerHTML = "Copy to clipboard";
            }
        </script>

        <style>
            .loader {
                border: 16px solid #f3f3f3;
                border-radius: 50%;
                border-top: 16px solid #3498db;
                width: 120px;
                height: 120px;
                -webkit-animation: spin 2s linear infinite; /* Safari */
                animation: spin 2s linear infinite;
            }
            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }

            /* button in textfiled starts*/
            body {
                background-color: #f1f1f1;
                font-family: Helvetica,Arial,Verdana;

            }
            h1 {
                color: green;
                text-align: center;
            }
            .redfamily {
                color: red;	
            }
            .search-box,.close-icon,.search-wrapper {
                position: relative;
                padding: 10px;
            }
            .search-wrapper {
                width: 500px;
                margin: auto;
                margin-top: 50px;
            }
            .search-box {
                width: 80%;
                border: 1px solid #ccc;
                outline: 0;
                border-radius: 15px;
            }
            .search-box:consigneeName1 {
                box-shadow: 0 0 15px 5px #b0e0ee;
                border: 2px solid #bebede;
            }
            .close-icon {
                border:1px solid transparent;
                background-color: transparent;
                display: inline-block;
                vertical-align: middle;
                outline: 0;
                cursor: pointer;
            }
            .close-icon:after {
                /*content: "X";*/
                display: block;
                width: 15px;
                height: 15px;
                position: absolute;
                background-color: #FA9595;
                z-index:1;
                right: 35px;
                top: 0;
                bottom: 0;
                margin: auto;
                padding: 2px;
                border-radius: 50%;
                text-align: center;
                color: white;
                font-weight: normal;
                font-size: 12px;
                box-shadow: 0 0 2px #E50F0F;
                cursor: pointer;
            }
            .search-box:not(:valid) ~ .close-icon {
                display: none;
            }
            /* button in textfiled end*/
            .tooltip {
                position: relative;
                display: inline-block;
            }

            .tooltip .tooltiptext {
                visibility: hidden;
                width: 140px;
                background-color: #555;
                color: #fff;
                text-align: center;
                border-radius: 6px;
                padding: 5px;
                position: absolute;
                z-index: 1;
                bottom: 150%;
                left: 50%;
                margin-left: -75px;
                opacity: 0;
                transition: opacity 0.3s;
            }

            .tooltip .tooltiptext::after {
                content: "";
                position: absolute;
                top: 100%;
                left: 50%;
                margin-left: -5px;
                border-width: 5px;
                border-style: solid;
                border-color: #555 transparent transparent transparent;
            }

            .tooltip:hover .tooltiptext {
                visibility: visible;
                opacity: 1;
            }
        </style>
    </head>
    <body>
        <div id="alert-message-success"  class="alert alert-success alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Success!</strong>Updated Successfully!
        </div>
        <div id="alert-message-fail"  class="alert alert-danger alert-dismissible">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Failed!</strong> Failed to Update
        </div>


        <div>
            <s:hidden id="cName" name="cName" value="%{cName}" />

            <s:url action="Exim.action" var="nextPage"  >
                <s:param name="pageNo"><s:property value="%{pageNo+1}"/></s:param>
                <s:param name="selectedCountry"><s:property value="#eximBean.searchCountry"/></s:param>
            </s:url>
            <%--<s:property value="%{nextPage}" />--%>
            <s:url action="Exim.action" var="previousPage" >
                <s:param name="pageNo"><s:property value="%{pageNo-1}"/></s:param>
            </s:url>
            <s:set var="Name" value="%{eximBean.consigneeName  + eximBean.ignoreSearchOnWebsite}"/>
            <%--<s:set var="Name" value="%{eximBean.consigneeName +', ' +eximBean.consigneeCountry + eximBean.ignoreSearchOnWebsite}"/>--%>
            <s:url value="http://www.google.com/search" var="searchOnGoogle" >
                <s:param name="q"><s:property value="%{Name}"/></s:param>
            </s:url>
            <%--<s:property value="%{previousPage}" />--%>

            <%--<s:form class="form-horizontal" action="Exim_updateConsigneeInfo.action" id="myform">--%> 
            <s:form class="form-horizontal"  id="myform" > 

                <s:hidden id="eximBean.consigneeName" name="eximBean.consigneeName" value="%{eximBean.consigneeName}" />
                <s:hidden id="eximBean.pageNo" name="eximBean.pageNo" value="%{eximBean.pageNo}" />
                <%--<s:hidden id="eximBean.searchCountry" name="eximBean.searchCountry" value="%{eximBean.searchCountry}" />--%>

                <s:if test="hasActionErrors()">
                    <div class="errorDiv">
                        <s:actionerror/>
                    </div>
                </s:if>

                <h4>Consignee Details</h4>

                <div class="well">

                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-xs-1">                        
                                <label class="control-label col-sm-3" for="consignee_name">Consignee Name </label>
                            </div>
                            <div class="col-sm-4">
                                <!--                                <p class="form-control-static">
                                                                    <a class="ex2" target="_blank"  href="<s:property value="#searchOnGoogle" />">
                                <%--<s:textfield id="consigneeName1"  class="form-control" var="consigneeName1" name="consigneeName1"  readonly="true"/>--%>
                            </a>

                                <%--<s:label   id="eximBean.total" name="eximBean.total"/>--%>
                            </p>-->
                                <s:textfield id="consigneeName1"   class="search-box" var="consigneeName1" name="consigneeName1"  />
                                <!--<input type="text" name="focus" required class="search-box" placeholder="Enter search term" />-->

                                <button class="close-icon" type="button" onclick="copyFunction();"><i class="fa fa-copy"></i>
                                    <span class="tooltiptext" id="myTooltip">Copy to clipboard</span>
                                </button>

                            </div>

                            <div  class="col-xs-1">                        
                                <label class="control-label col-sm-2" for="consigneeCountry">Country</label>
                            </div>
                            <div class="col-sm-4">
                                <p class="form-control-static">
                                    <s:textfield id="consigneeCountry"  class="form-control" var="consigneeCountry" name="consigneeCountry" readonly="true" />
                                    <%--<s:label id="consigneeCountry" for="eximBean.consigneeCountry" name="eximBean.consigneeCountry" style="text-align: left"  class="control-label col-sm-5" />--%>
                                </p>
                            </div>
                            <div class="col-sm-1">                        
                                <label class="control-label col-sm-2" for="consigneeCountry">Shipments </label>
                            </div>
                            <div class="col-sm-1">                        
                                <label class="control-label col-sm-2" for="consigneeCountry"> 
                                    <s:textfield id="count"  class="form-control" var="count" name="count"   style="width:60px" readonly="true"/>

                            </div>


                        </div> 



                        <div class="row">
                            <div class="col-xs-1">                        
                                <label class="control-label col-sm-3" for="consignee_name">Address </label>
                            </div>
                            <div class="col-xl-4">
                                <p class="form-control-static">
                                    <!--<a class="ex2" target="_blank"  href="<s:property value="#searchOnGoogle" />">-->
                                    <s:textfield id="consignee_address"  class="form-control" var="consignee_address" name="consignee_address"  readonly="true" 
                                                 style="width: 800px"/>
                                    <%--<s:label id="eximBean.consigneeName" name="eximBean.consigneeName"   class="control-label col-sm-9" style="text-align: left" />--%>
                                    <!--</a>-->

                                    <!--<label class="badge" for="consignee_name"><s:label   id="eximBean.total" name="eximBean.total"/> </label></p>-->
                            </div>

                        </div> 

                    </div> <!-- div fluid ends --> <!-- div fluid ends -->


                    <!--phone -->
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="inputEmail" class="control-label col-xs-3">Phone</label>
                                <div class="col-xs-9">
                                    <!--<input type="text" class="form-control" id="phone" placeholder="Phone">-->
                                    <s:textfield  name="eximBean.phone" class="form-control" id="eximBean.phone" placeholder="Phone"  />
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="isBlackListed" class="control-label col-xs-3">Is Black Listed?</label>
                                <div class="col-xs-9">
                                    <!--<input type="text" class="form-control" id="phone" placeholder="Phone">-->
                                    <s:select
                                        cssClass="form-control"
                                        headerKey="-1" headerValue="Select"
                                        list="#{'Y':'Yes', 'N':'No'}" 

                                        value="eximBean.isBlackListed"
                                        name="eximBean.isBlackListed" 
                                        id="eximBean.isBlackListed"
                                        />

                                </div>
                            </div>

                        </div>

                    </div>

                    <!-- email,web -->
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="inputEmail" class="control-label col-xs-3">Email</label>
                                <div class="col-xs-9">
                                    <!--<input type="email" class="form-control" id="inputEmail" placeholder="Email">-->
                                    <s:textfield id="eximBean.email" name="eximBean.email" class="form-control"  placeholder="Email"  />
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <div class="form-group">
                                <label for="inputPassword" class="control-label col-xs-4">Web</label>
                                <div class="col-xs-7">
                                    <s:textfield id="eximBean.web" name="eximBean.web" class="form-control"  placeholder="www.example.com"  />
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <label for="inputEmail" class="control-label col-xs-8" style="text-align: left">Contact Details Available?</label>
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <%--list="#{'Y':'Yes', 'N':'No','L':'Later'}"--%> 

                                        <s:select
                                            cssClass="form-control"
                                            onchange="getSelectValue()"
                                            headerKey="-1" headerValue="Select"
                                            list="#{'Y':'Yes', 'N':'No', 'L':'Later'}" 

                                            value="eximBean.selected"
                                            name="eximBean.is_contact_info_found" 
                                            id="eximBean.is_contact_info_found"
                                            />
                                        <!--                                        <select class="form-control" id="sel1">
                                                                                    <option>1</option>
                                                                                    <option>2</option>
                                                                                    <option>3</option>
                                                                                    <option>4</option>
                                                                                </select>-->
                                        <!--14.70-15.50 // Meshbag -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="minPrice" class="control-label col-xs-3">Total Qty</label>
                                <div class="col-xs-9">
                                    <%--<s:textfield id="minPrice1" name="minPrice1" class="form-control"  readonly="true" />--%>
                                    <s:textfield id="totalQty" name="totalQty" class="form-control"  readonly="true" />

                                </div>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <!--                            <div class="form-group">
                                                            <label for="avgPrice" class="control-label col-xs-4">Total Qty</label>
                                                            <div class="col-xs-7">
                            <s:textfield id="totalQty" name="totalQty" class="form-control"  readonly="true" />
                        </div>
                    </div>-->
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <label for="maxPrice1" class="control-label col-xs-8" style="text-align: left">Many Shipments?</label>
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <s:select
                                            cssClass="form-control"
                                            headerKey="-1" headerValue="Select"
                                            list="#{'M':'Micro :: 1 - 2','S':'Small :: 3-5','O':'Medium :: 6 - 10','B':'Big :: 11 - 15', 'L':'Large :: 16 - 20', 'G':'Giant :: >=21'}" 
                                            value="eximBean.hasTooManyShipments"
                                            name="eximBean.hasTooManyShipments" 
                                            id="eximBean.hasTooManyShipments"
                                            />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- price info -->
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="minPrice" class="control-label col-xs-3">Min Price</label>
                                <div class="col-xs-9">
                                    <!--<input type="email" class="form-control" id="inputEmail" placeholder="Email">-->
                                    <s:textfield id="minPrice1" name="minPrice1" class="form-control"  readonly="true" />
                                    <%--<s:label id="minPrice1" name="minPrice1" />--%>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <div class="form-group">
                                <label for="avgPrice" class="control-label col-xs-4">Avg Price</label>
                                <div class="col-xs-7">
                                    <s:textfield id="avgPrice1" name="avgPrice1" class="form-control"  readonly="true" />
                                    <%--<s:label id="avgPrice1" name="avgPrice1" />--%>

                                </div>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <label for="maxPrice1" class="control-label col-xs-8" style="text-align: left">Max Price</label>
                                <div class="col-xs-4">
                                    <div class="form-group">
                                        <s:textfield id="maxPrice" name="maxPrice" class="form-control" readonly="true"  />

                                        <%--<s:textfield name="maxPrice" id="maxPrice" readonly="true"/>--%>
                                        <%--<s:label name="maxPrice" id="maxPrice" />--%>
                                    </div>
                                </div>
                            </div>

                        </div>

                    </div>
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="everContacted" class="control-label col-xs-3">Ever Contacted?</label>
                                <div class="col-xs-9">
                                    <s:select
                                        cssClass="form-control"
                                        headerKey="-1" headerValue="Select"
                                        list="#{'Y':'Yes', 'N':'No'}" 

                                        value="eximBean.everContacted"
                                        name="eximBean.everContacted" 
                                        id="eximBean.everContacted"
                                        />
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="comment">Set Reminder</label><br/>
                                <div class="col-xs-9">
                                    <p><input type="text" id="datepicker"  class="form-control col-sm-4"  placeholder="Date">
                                        <input type="text" id="timepicker"  class="form-control  col-sm-3" placeholder="Time" ></p>
                                    <input type="button" class="btn btn-primary" value="Set Reminder" onclick="setReminder()" />  
                                    <p  id="inlineloader"><i class="fa fa-spinner fa-spin"></i> Setting up reminder...Please wait</p>
                                    <!--<p></p>-->
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="form-group">

                        <label for="comment">Comment:</label><br/>
                        <button type="button" id="defineValue1"  class="btn btn-default btn-sm" value="Not available on Google. " onclick="setValue(this.id)" title="Not available on Google.">Not available on Google</button>
                        <button type="button" id="defineValue2"  class="btn btn-default btn-sm" value="Irrelevant Company. " title="Irrelevant Company. " onclick="setValue(this.id)">Irrelevant Company</button>
                        <button type="button" id="defineValue3"  class="btn btn-default btn-sm" value="Owner is Indian. " title="Owner is Indian. " onclick="setValue(this.id)">Owner is Indian</button>
                        <button type="button" id="defineValue4"  class="btn btn-default btn-sm" value="Search Later. " title="Search Later. " onclick="setValue(this.id)">Search Later</button>
                        <button type="button" id="defineValue5"  class="btn btn-default btn-sm" value="Company is closed. " title="Company is closed. " onclick="setValue(this.id)">Company is closed</button>
                        <button type="button" id="defineValue6"  class="btn btn-default btn-sm" value="Has Branch in India. " title="Has Branch in India. " onclick="setValue(this.id)">Has Branch in India</button>
                        <button type="button" id="defineValue7"  class="btn btn-default btn-sm" value="Contact info might be incorrect or of similar company and NOT actual. " onclick="setValue(this.id)" title="Contact info might be incorrect or of similar company and NOT actual. ">Might be Incorrect Contact Details</button>
                        <button type="button" id="defineValue8"  class="btn btn-default btn-sm" value="Product is different. " onclick="setValue(this.id)" title="Contact info might be incorrect or of similar company and NOT actual. ">Product is different</button>
                        <button type="button" id="defineValue9"  class="btn btn-default btn-sm" value="Quantity is too small. " onclick="setValue(this.id)" title="Quantity is too small. ">Quantity is too small</button>
                        <button type="button" id="defineValue10"  class="btn btn-default btn-sm" value="Company is very Big/Good. " onclick="setValue(this.id)" title="Company is very Big/Good. ">Company is very Big/Good</button>
                        <button type="button" id="defineValue11"  class="btn btn-default btn-sm" value="Too many shipments from Export Database. " onclick="setValue(this.id)" title="Too many shipments from Export Database. ">Too many shipments from Export Database</button>
                        <button type="button" id="defineValue12"  class="btn btn-default btn-sm" value="Details from Website. " onclick="setValue(this.id)" title="Website details. ">Website details</button>

                        <br/>

                        <!--                        <textarea class="form-control" rows="5" id="comment"></textarea>-->
                        <s:textarea id="eximBean.comment" name="eximBean.comment" cssClass="form-control" rows="6"/>
                        <script>
                            var editor = CKEDITOR.replace('eximBean.comment');
                            editor.addCommand("addTimeStamp", {
                                exec: function (edt) {
                                    //  alert(edt.getData());
                                    // setValue1();

                                    var today = new Date();
                                    var dd = today.getDate();
                                    var mm = today.getMonth() + 1; //January is 0!
                                    var yyyy = today.getFullYear();

                                    if (dd < 10) {
                                        dd = '0' + dd
                                    }

                                    if (mm < 10) {
                                        mm = '0' + mm
                                    }
                                    // get am pm
                                    var hours = today.getHours();
                                    var minutes = today.getMinutes();
                                    var ampm = hours >= 12 ? 'PM' : 'AM';
                                    hours = hours % 12;
                                    hours = hours ? hours : 12; // the hour '0' should be '12'
                                    minutes = minutes < 10 ? '0' + minutes : minutes;
                                    var strTime = hours + ':' + minutes + ' ' + ampm;
                                    // get am pm ends
                                    today = dd + '/' + mm + '/' + yyyy + '  ' + strTime;
                                    // console.log(today);
                                    //document.getElementById("eximBean.comment").value += value;
                                    CKEDITOR.instances['eximBean.comment'].insertHtml("<p><strong> <span class='marker'>" + today + "&nbsp;</span></strong></p>");
                                }
                            });
                            editor.ui.addButton('SuperButton', {
                                label: "Time Stamp",
                                command: 'addTimeStamp',
                                toolbar: 'insert',
                                icon: 'http://localhost:8080/Onion-1.0-SNAPSHOT/images/calendar.png'
                            });
                            editor.addCommand("insertCountryCode", {
                                //allowedContent: "span[contenteditable](*)"
                                //, requiredContent: "span[contenteditable](*)"
                                //,
                                exec: function (editor, id) {
                                    console.log("insertCountryCode.exec(id: " + id + ")")
                                    //editor.insertElement(CKEDITOR.dom.element.createFromHtml('<span class="variable" contenteditable="false">[ ' + id + ' ]</span>'));
                                }
                            });
                            editor.ui.addRichCombo("InsertCountryCode", {
                                label: "ISD Code"
                                , title: "ISD Code"
                                , multiSelect: false
//                                , toolbar: "basicstyles,0"
                                , toolbar: "insert"
                                , init: function () {
                                    console.log("InsertVariableSelect.init")
                                    this.add('+93', 'Afghanistan');
                                    this.add('+358', 'Aland Islands');
                                    this.add('+355', 'Albania');
                                    this.add('+213', 'Algeria');
                                    this.add('+1684', 'AmericanSamoa');
                                    this.add('+376', 'Andorra');
                                    this.add('+244', 'Angola');
                                    this.add('+1264', 'Anguilla');
                                    this.add('+672', 'Antarctica');
                                    this.add('+1268', 'Antigua and Barbuda');
                                    this.add('+54', 'Argentina');
                                    this.add('+374', 'Armenia');
                                    this.add('+297', 'Aruba');
                                    this.add('+61', 'Australia');
                                    this.add('+43', 'Austria');
                                    this.add('+994', 'Azerbaijan');
                                    this.add('+1242', 'Bahamas');
                                    this.add('+973', 'Bahrain');
                                    this.add('+880', 'Bangladesh');
                                    this.add('+1246', 'Barbados');
                                    this.add('+375', 'Belarus');
                                    this.add('+32', 'Belgium');
                                    this.add('+501', 'Belize');
                                    this.add('+229', 'Benin');
                                    this.add('+1441', 'Bermuda');
                                    this.add('+975', 'Bhutan');
                                    this.add('+591', 'Bolivia, Plurinational State of');
                                    this.add('+387', 'Bosnia and Herzegovina');
                                    this.add('+267', 'Botswana');
                                    this.add('+55', 'Brazil');
                                    this.add('+246', 'British Indian Ocean Territory');
                                    this.add('+673', 'Brunei Darussalam');
                                    this.add('+359', 'Bulgaria');
                                    this.add('+226', 'Burkina Faso');
                                    this.add('+257', 'Burundi');
                                    this.add('+855', 'Cambodia');
                                    this.add('+237', 'Cameroon');
                                    this.add('+1', 'Canada');
                                    this.add('+238', 'Cape Verde');
                                    this.add('+ 345', 'Cayman Islands');
                                    this.add('+236', 'Central African Republic');
                                    this.add('+235', 'Chad');
                                    this.add('+56', 'Chile');
                                    this.add('+86', 'China');
                                    this.add('+61', 'Christmas Island');
                                    this.add('+61', 'Cocos (Keeling) Islands');
                                    this.add('+57', 'Colombia');
                                    this.add('+269', 'Comoros');
                                    this.add('+242', 'Congo');
                                    this.add('+243', 'Congo, The Democratic Republic of the Congo');
                                    this.add('+682', 'Cook Islands');
                                    this.add('+506', 'Costa Rica');
                                    this.add('+225', 'Cote dIvoire');
                                    this.add('+385', 'Croatia');
                                    this.add('+53', 'Cuba');
                                    this.add('+357', 'Cyprus');
                                    this.add('+420', 'Czech Republic');
                                    this.add('+45', 'Denmark');
                                    this.add('+253', 'Djibouti');
                                    this.add('+1767', 'Dominica');
                                    this.add('+1849', 'Dominican Republic');
                                    this.add('+593', 'Ecuador');
                                    this.add('+20', 'Egypt');
                                    this.add('+503', 'El Salvador');
                                    this.add('+240', 'Equatorial Guinea');
                                    this.add('+291', 'Eritrea');
                                    this.add('+372', 'Estonia');
                                    this.add('+251', 'Ethiopia');
                                    this.add('+500', 'Falkland Islands (Malvinas)');
                                    this.add('+298', 'Faroe Islands');
                                    this.add('+679', 'Fiji');
                                    this.add('+358', 'Finland');
                                    this.add('+33', 'France');
                                    this.add('+594', 'French Guiana');
                                    this.add('+689', 'French Polynesia');
                                    this.add('+241', 'Gabon');
                                    this.add('+220', 'Gambia');
                                    this.add('+995', 'Georgia');
                                    this.add('+49', 'Germany');
                                    this.add('+233', 'Ghana');
                                    this.add('+350', 'Gibraltar');
                                    this.add('+30', 'Greece');
                                    this.add('+299', 'Greenland');
                                    this.add('+1473', 'Grenada');
                                    this.add('+590', 'Guadeloupe');
                                    this.add('+1671', 'Guam');
                                    this.add('+502', 'Guatemala');
                                    this.add('+44', 'Guernsey');
                                    this.add('+224', 'Guinea');
                                    this.add('+245', 'Guinea-Bissau');
                                    this.add('+595', 'Guyana');
                                    this.add('+509', 'Haiti');
                                    this.add('+379', 'Holy See (Vatican City State)');
                                    this.add('+504', 'Honduras');
                                    this.add('+852', 'Hong Kong');
                                    this.add('+36', 'Hungary');
                                    this.add('+354', 'Iceland');
                                    this.add('+91', 'India');
                                    this.add('+62', 'Indonesia');
                                    this.add('+98', 'Iran, Islamic Republic of Persian Gulf');
                                    this.add('+964', 'Iraq');
                                    this.add('+353', 'Ireland');
                                    this.add('+44', 'Isle of Man');
                                    this.add('+972', 'Israel');
                                    this.add('+39', 'Italy');
                                    this.add('+1876', 'Jamaica');
                                    this.add('+81', 'Japan');
                                    this.add('+44', 'Jersey');
                                    this.add('+962', 'Jordan');
                                    this.add('+77', 'Kazakhstan');
                                    this.add('+254', 'Kenya');
                                    this.add('+686', 'Kiribati');
                                    this.add('+850', 'Korea, Democratic Peoples Republic of Korea');
                                    this.add('+82', 'Korea, Republic of South Korea');
                                    this.add('+965', 'Kuwait');
                                    this.add('+996', 'Kyrgyzstan');
                                    this.add('+856', 'Laos');
                                    this.add('+371', 'Latvia');
                                    this.add('+961', 'Lebanon');
                                    this.add('+266', 'Lesotho');
                                    this.add('+231', 'Liberia');
                                    this.add('+218', 'Libyan Arab Jamahiriya');
                                    this.add('+423', 'Liechtenstein');
                                    this.add('+370', 'Lithuania');
                                    this.add('+352', 'Luxembourg');
                                    this.add('+853', 'Macao');
                                    this.add('+389', 'Macedonia');
                                    this.add('+261', 'Madagascar');
                                    this.add('+265', 'Malawi');
                                    this.add('+60', 'Malaysia');
                                    this.add('+960', 'Maldives');
                                    this.add('+223', 'Mali');
                                    this.add('+356', 'Malta');
                                    this.add('+692', 'Marshall Islands');
                                    this.add('+596', 'Martinique');
                                    this.add('+222', 'Mauritania');
                                    this.add('+230', 'Mauritius');
                                    this.add('+262', 'Mayotte');
                                    this.add('+52', 'Mexico');
                                    this.add('+691', 'Micronesia, Federated States of Micronesia');
                                    this.add('+373', 'Moldova');
                                    this.add('+377', 'Monaco');
                                    this.add('+976', 'Mongolia');
                                    this.add('+382', 'Montenegro');
                                    this.add('+1664', 'Montserrat');
                                    this.add('+212', 'Morocco');
                                    this.add('+258', 'Mozambique');
                                    this.add('+95', 'Myanmar');
                                    this.add('+264', 'Namibia');
                                    this.add('+674', 'Nauru');
                                    this.add('+977', 'Nepal');
                                    this.add('+31', 'Netherlands');
                                    this.add('+599', 'Netherlands Antilles');
                                    this.add('+687', 'New Caledonia');
                                    this.add('+64', 'New Zealand');
                                    this.add('+505', 'Nicaragua');
                                    this.add('+227', 'Niger');
                                    this.add('+234', 'Nigeria');
                                    this.add('+683', 'Niue');
                                    this.add('+672', 'Norfolk Island');
                                    this.add('+1670', 'Northern Mariana Islands');
                                    this.add('+47', 'Norway');
                                    this.add('+968', 'Oman');
                                    this.add('+92', 'Pakistan');
                                    this.add('+680', 'Palau');
                                    this.add('+970', 'Palestinian Territory, Occupied');
                                    this.add('+507', 'Panama');
                                    this.add('+675', 'Papua New Guinea');
                                    this.add('+595', 'Paraguay');
                                    this.add('+51', 'Peru');
                                    this.add('+63', 'Philippines');
                                    this.add('+872', 'Pitcairn');
                                    this.add('+48', 'Poland');
                                    this.add('+351', 'Portugal');
                                    this.add('+1939', 'Puerto Rico');
                                    this.add('+974', 'Qatar');
                                    this.add('+40', 'Romania');
                                    this.add('+7', 'Russia');
                                    this.add('+250', 'Rwanda');
                                    this.add('+262', 'Reunion');
                                    this.add('+590', 'Saint Barthelemy');
                                    this.add('+290', 'Saint Helena, Ascension and Tristan Da Cunha');
                                    this.add('+1869', 'Saint Kitts and Nevis');
                                    this.add('+1758', 'Saint Lucia');
                                    this.add('+590', 'Saint Martin');
                                    this.add('+508', 'Saint Pierre and Miquelon');
                                    this.add('+1784', 'Saint Vincent and the Grenadines');
                                    this.add('+685', 'Samoa');
                                    this.add('+378', 'San Marino');
                                    this.add('+239', 'Sao Tome and Principe');
                                    this.add('+966', 'Saudi Arabia');
                                    this.add('+221', 'Senegal');
                                    this.add('+381', 'Serbia');
                                    this.add('+248', 'Seychelles');
                                    this.add('+232', 'Sierra Leone');
                                    this.add('+65', 'Singapore');
                                    this.add('+421', 'Slovakia');
                                    this.add('+386', 'Slovenia');
                                    this.add('+677', 'Solomon Islands');
                                    this.add('+252', 'Somalia');
                                    this.add('+27', 'South Africa');
                                    this.add('+211', 'South Sudan');
                                    this.add('+500', 'South Georgia and the South Sandwich Islands');
                                    this.add('+34', 'Spain');
                                    this.add('+94', 'Sri Lanka');
                                    this.add('+249', 'Sudan');
                                    this.add('+597', 'Suriname');
                                    this.add('+47', 'Svalbard and Jan Mayen');
                                    this.add('+268', 'Swaziland');
                                    this.add('+46', 'Sweden');
                                    this.add('+41', 'Switzerland');
                                    this.add('+963', 'Syrian Arab Republic');
                                    this.add('+886', 'Taiwan');
                                    this.add('+992', 'Tajikistan');
                                    this.add('+255', 'Tanzania, United Republic of Tanzania');
                                    this.add('+66', 'Thailand');
                                    this.add('+670', 'Timor-Leste');
                                    this.add('+228', 'Togo');
                                    this.add('+690', 'Tokelau');
                                    this.add('+676', 'Tonga');
                                    this.add('+1868', 'Trinidad and Tobago');
                                    this.add('+216', 'Tunisia');
                                    this.add('+90', 'Turkey');
                                    this.add('+993', 'Turkmenistan');
                                    this.add('+1649', 'Turks and Caicos Islands');
                                    this.add('+688', 'Tuvalu');
                                    this.add('+256', 'Uganda');
                                    this.add('+380', 'Ukraine');
                                    this.add('+971', 'United Arab Emirates');
                                    this.add('+44', 'United Kingdom');
                                    this.add('+1', 'United States');
                                    this.add('+598', 'Uruguay');
                                    this.add('+998', 'Uzbekistan');
                                    this.add('+678', 'Vanuatu');
                                    this.add('+58', 'Venezuela, Bolivarian Republic of Venezuela');
                                    this.add('+84', 'Vietnam');
                                    this.add('+1284', 'Virgin Islands, British');
                                    this.add('+1340', 'Virgin Islands, U.S.');
                                    this.add('+681', 'Wallis and Futuna');
                                    this.add('+967', 'Yemen');
                                    this.add('+260', 'Zambia');
                                    this.add('+263', 'Zimbabwe');
                                    /*var rich_combo = this
                                     
                                     rich_combo.add("foo", "foo", "foo")
                                     rich_combo.add("boo", "boo", "boo")
                                     rich_combo.add("goo", "goo", "goo")*/
                                }
                                , onClick: function (id) {
                                    CKEDITOR.instances['eximBean.comment'].insertHtml(" "+id+" ");

                                    console.log("InsertVariableSelect.onClick(id: " + id + ")")
//                                    editor.execCommand("insertVariable")
                                    editor.execCommand("insertCountryCode", id)
                                }
                            })

                        </script>
                        <br/>
                        <div class="col-xs-1" style="text-align: center;align:center" >
                            <!--<button type="button" class="btn btn-primary">Save-->

                            <!--</button>-->
                            <%--<s:submit value="Submit" class="col-xs-1" style="text-align: center;align:center;width:250px;height:40px;"/>--%>

                            <input type="button" value="Submit" class="col-xs-1" style="text-align: center;align:center;width:250px;height:40px;" onclick="submitSave()" />
                            <%--<s:submit value="Submit" class="col-xs-1" style="text-align: center;align:center;width:250px;height:40px;"/>--%>

                        </div>
                    </div>                    


                </div> <!--well over-->
                <h5>More Details</h5>


                <!--Table-->
                <!--<div id="loader" class="loader"></div>-->
                <div id="table1" > </div>

            </s:form>

        </div>

    </body>
</html>
<!--<script type="text/javascript" src="jquery.dataTables.js"></script>-->
