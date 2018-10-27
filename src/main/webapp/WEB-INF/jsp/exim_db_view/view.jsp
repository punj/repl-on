<%@taglib  prefix="s"  uri="/struts-tags"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" buffer="5000kb" autoFlush="false" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>REPL : Export Data Management</title>

        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1">

        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!--<link href="web/images/favicon.png" rel="icon" type="image/x-icon"/>-->

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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <script src="js/exim/view.js" type="text/javascript"></script>


        <script src="https://cdn.ckeditor.com/4.10.1/standard/ckeditor.js"></script>
        <!--<script src="https://cdn.ckeditor.com/4.10.1/full/ckeditor.js"></script>-->
        <!--<script src="https://cdn.ckeditor.com/4.10.1/basic/ckeditor.js"></script>-->

        <style>

            a.ex2:hover, a.ex2:active {font-size: 102%;
                                       cursor: pointer}
            .badge {
                display: inline-block;
                min-width: 10px;
                padding: 3px 7px;
                font-size: 12px;
                font-weight: bold;
                line-height: 1;
                color: white !important;
                text-align: center;
                white-space: nowrap;
                vertical-align: middle;
                background-color: red;
                border-radius: 10px;
            }

            .label.badge{
                color: white;


            }

            .loader {
                border: 16px solid #f3f3f3;
                border-radius: 50%;
                border-top: 16px solid #3498db;
                width: 120px;
                height: 120px;
                -webkit-animation: spin 2s linear infinite; /* Safari */
                animation: spin 2s linear infinite;
            }

            /* Safari */
            @-webkit-keyframes spin {
                0% { -webkit-transform: rotate(0deg); }
                100% { -webkit-transform: rotate(360deg); }
            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }
        </style>

        <script>
            function submitSave() {
                var v = document.getElementById('eximBean.is_contact_info_found').value;
                //               alert(v);
                //    alert($( "#eximBean.is_contact_info_found option:selected" ).text());
                //    var conceptName = $('#eximBean.is_contact_info_found').find(":selected").text();
                //    alert(conceptName);
                if (v != -1) {
                    //                    alert("do submit")
                    document.myform.action = "Exim_updateConsigneeInfo.action";
                    document.myform.submit();
                } else {
                    var txt;
                    var r = confirm("Contact Details Available?");
                    if (r == true) {
                        document.getElementById('eximBean.is_contact_info_found').value = 'Y';
                        document.getElementById('eximBean.selected').value = 'Y';
                        txt = "You pressed OK!";
                    } else {
                        document.getElementById('eximBean.is_contact_info_found').value = 'N';
                        document.getElementById('eximBean.selected').value = 'N';
                    }
                    // alert("Contact Details Available?");
                }
            }
            var searchCountry = "";
            function getSelectValue(t) {
                // alert(JSON.stringify($("#nextPage")))
                var val = t.options[t.selectedIndex].text;
                //alert(val);
                document.getElementById('eximBean.searchCountry').value = val;
                // alert(document.getElementById('eximBean.searchCountry').value);
                searchCountry = document.getElementById('eximBean.searchCountry').value;
                //            var sel = document.getElementById('eximBean.is_contact_info_found');
                //            alert(sel.options[sel.selectedIndex].text);
            }


            function nextPage() {
                alert("NextPage");
                var pno = 0;
                pno = document.getElementById("eximBean.pageNo").value;
                pno = parseInt(pno) + 1;
                //Exim.action
                // alert(pno);
                // alert(searchCountry);
                var s = "Exim_updateConsigneeInfo.action?pageNo=" + pno + "&eximBean.searchCountry=" + searchCountry;
                alert(s);
                document.myform.method = "POST";
                document.myform.action = s;
                document.myform.submit();
                //            var s = "Exim_updateConsigneeInfo.action?pageNo="+$("#eximBean.pageNo").val();
                //  alert(s)
                //   document.myform.submit();
            }
        </script>
    </head>

    <body onload="setTitle();
            loadDetailReportData();
            loadPriceDetail();"  >


        <input type="button" onclick="nextPage()" value="ajax" />
        <!--<input type="button" onclick="sgn123()" value="APPEND" />-->
        <s:head/>
        <%--<s:hidden id="eximBean.searchCountry" name="eximBean.searchCountry" />--%>



        <div class="container">
            <%--<s:hidden id="eximBean.ignoreSearchOnWebsite" name="eximBean.ignoreSearchOnWebsite" value="%{eximBean.ignoreSearchOnWebsite}" />--%>

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
                <!--
                            <h4>Search   </h4>
    
                <div class="well">
    
                    <div class="row">
                        <div class="col-sm-2">Company Name</div>
                        <div class="col-sm-5"> <input type="text" class="form-control" id="phone" placeholder="Phone"></div>
                        <div class="col-sm-2">Country</div>
                        <div class="col-sm-3">
                            <div class="form-group">
                                <select class="form-control" id="sel1">
                                    <option>United Arab Emirates</option>
                                    <option>Sri Lanka</option>
                                    <option>Malaysia</option>
                                    <option>Oman</option>
                                </select>
                            </div>
    
                        </div>
                    </div>
                </div--> <!--well over-->

                <ul class="pager">
                    <s:if test="%{pageNo >0}">
                        <li class="previous"><a href="<s:property value="#previousPage" />">Previous</a></li>

                    </s:if>
                    <li class="next"><a href="<s:property value="#nextPage" />">Next</a></li>
                    <!--<li class="next"><a href="#" onclick="nextPage(); return false;">Next > </a></li>-->
                </ul>
                <h4>Consignee Details</h4>
                <s:if test="hasActionErrors()">
                    <div class="errorDiv">
                        <s:actionerror/>
                    </div>
                </s:if>
                <h4>Search Section</h4>
                <div class="well">
                    <s:select
                        cssClass="form-control"

                        headerKey="-1" headerValue="Select"
                        list="eximBean.countries" 
                        value="eximBean.searchCountry"
                        name="eximBean.searchCountry" 
                        id="eximBean.searchCountry"
                        />

                    <!--s:select
                        cssClass="form-control"
                        onchange="getSelectValue(this);"
                        headerKey="-1" headerValue="Select"
                        list="eximBean.countries" 
                        value="eximBean.searchCountry"
                        name="eximBean.countries" 
                        id="eximBean.countries"
                        / -->
                </div>
                <div class="well">

                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-xs-1">                        
                                <label class="control-label col-sm-3" for="consignee_name">Consignee Name </label>
                            </div>
                            <div class="col-sm-4">
                                <p class="form-control-static">
                                    <a class="ex2" target="_blank"  href="<s:property value="#searchOnGoogle" />"> ${eximBean.consigneeName}
                                        <%--<s:label id="eximBean.consigneeName" name="eximBean.consigneeName"   class="control-label col-sm-9" style="text-align: left" />--%>
                                    </a>

                                    <label class="badge" for="consignee_name"><s:label   id="eximBean.total" name="eximBean.total"/> </label></p>
                            </div>

                            <div  class="col-xs-1">                        
                                <label class="control-label col-sm-2" for="consigneeCountry">Country</label>
                            </div>
                            <div class="col-sm-4">
                                <p class="form-control-static">
                                    <s:label id="consigneeCountry" for="eximBean.consigneeCountry" name="eximBean.consigneeCountry" style="text-align: left"  class="control-label col-sm-5" />
                                </p>
                            </div>
                            <div class="col-sm-1">                        
                                <label class="control-label col-sm-2" for="consigneeCountry">Page No</label>
                            </div>
                            <div class="col-sm-1">                        
                                <label class="control-label col-sm-2" for="consigneeCountry"> <s:property  value="%{eximBean.pageNo}" /></label>

                            </div>


                        </div>  

                    </div> <!-- div fluid ends --> <!-- div fluid ends -->
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="minPrice" class="control-label col-xs-3"  style="text-align: right">Address</label>
                                <div class="col-lg-9" style="">
                                    <%--<s:textfield id="minPrice1" name="minPrice1" class="form-control"  readonly="true" />--%>
                                    <%--<s:textfield id="totalQty" name="totalQty" class="form-control"  readonly="true" />--%>
                                    <s:label class="control-label col-lg-11" style="text-align: left" for="consigneeAddress" id="eximBean.consigneeAddress"  name="eximBean.consigneeAddress" />
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <!--                            <div class="form-group">
                                                            <label for="avgPrice" class="control-label col-xs-4">Total Qty</label>
                                                            <div class="col-xs-7">
                             
                        </div>
                    </div>-->
                        </div>
                         
                    </div>

                   


                    <!--phone -->
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label for="inputEmail" class="control-label col-xs-3">Phone</label>
                                <div class="col-xs-9">
                                    <!--<input type="text" class="form-control" id="phone" placeholder="Phone">-->
                                    <s:textfield  name="eximBean.phone" class="form-control" id="eximBean.phone" placeholder="Phone" />
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
                                    <s:textfield id="eximBean.email" name="eximBean.email" class="form-control"  placeholder="Email" />
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-5">
                            <div class="form-group">
                                <label for="inputPassword" class="control-label col-xs-4">Web</label>
                                <div class="col-xs-7">
                                    <s:textfield id="eximBean.web" name="eximBean.web" class="form-control"  placeholder="www.example.com" />
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
                            CKEDITOR.replace('eximBean.comment');
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
                <div id="loader" class="loader"></div>
                <div id="table1" > </div>

            </s:form>
            <ul class="pager">
                <s:if test="%{pageNo >0}">
                    <li class="previous"><a href="<s:property value="#previousPage" />">Previous</a></li>

                </s:if>
                <li class="next"><a href="<s:property value="#nextPage" />">Next</a></li>
            </ul>
        </div>

    </body>
</html>
