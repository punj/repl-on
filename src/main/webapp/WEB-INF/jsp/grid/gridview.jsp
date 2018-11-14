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

        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

        <!--<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>-->
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.colVis.min.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!--<script src="js/grid/eximviewGrid.js" type="text/javascript"></script>-->

        <!-- other page js css -->
        <!-- datepicker starts -->
        <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
        <!-- datepicker ends -->


        <!-- datatables buttons js starts-->
        




<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.bootstrap.min.js"></script>
        <!-- datatables buttons js ends-->
        

        <style>
            .table.dataTable {
                border-collapse: collapse;
                width: 100% !important;;
            }
            .pagination {
                display: inline-block;
                padding-left: 0;
                margin: 23px 0;
                border-radius: 4px;
            }
            @media screen and (min-width: 768px) {
                .modal-dialog {
                    width: 700px; /* New width for default modal */
                    height: 900px;
                }
                .modal-sm {
                    width: 350px; /* New width for small modal */
                    height: 900px;
                }
            }
            @media screen and (min-width: 992px) {
                .modal-lg {
                    width: 100%; /* New width for large modal */
                    height: 100%;
                }
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
            .block {
                display: block;
                width: 100%;
                border: none;
                background-color: #4CAF50;
                padding: 14px 28px;
                font-size: 16px;
                cursor: pointer;
                text-align: center;
                color: white;
            }
        </style>

        <script>

            function format(d) {
            return 'Full name: ' + d.first_name + ' ' + d.last_name + '<br>' +
                    'Salary: ' + d.salary + '<br>' +
                    'The child row can contain any data you wish, including links, images, inner tables etc.';
            }
            var startPage = 0, endPage = 0;
            $(document).ready(function () {
            // alert("ready ");;
            // Setup - add a text input to each footer cell
//                $('#example tfoot th').each(function (i) {
//                    var title = $('#example thead th').eq($(this).index()).text();
//                    $(this).html('<input type="text" placeholder="Search ' + title + '" data-index="' + i + '"  id="searchCol-' + i + '"/>');
//                }); 
            //
            ;
            var selected = [];
            var jsn;
            var table = $('#example').DataTable({
            dom: 'Bfrtip',
                    buttons: [
                            'colvis','pageLength', 'copyHtml5','excelHtml5','csvHtml5','pdfHtml5'
                    ],
                    columnDefs: [
                    {
//                            targets: -1,
//                            visible: false
                    }
                    ],
                    "autoWidth": false,
                    select: true,
                    "processing": true,
                    "serverSide": true,
                    //"displayStart": 20,
//                   table.columns.adjust().draw( false );
                    "ajax": {
                    "url": "Grid_loadGridData.action",
                            "data": function (data) {
//                                            data.myKey = "myValue";
                            data.minQty = $('#minQty').val();
                            data.maxQty = $('#maxQty').val();
                            data.cnmae = $("#searchCol-1").val();
                            // console.log("!!!!! " + $("#searchCol-1").val());
                            // d.custom = $('#myInput').val();
                            // etc
                            },
                            "dataSrc": function (json) {
                            jsn = json;
                            //alert("  "+json.data);
                            startPage = json.start;
                            endPage = json.end;
                            //console.log(JSON.stringify(json)+ "\n "+startPage+"  "+endPage);

                            return json.data;
                            },
                            /* success: function () {
                             console.log("success")
                             //                            table.draw();
                             },*/
                            dataFilter: function (data) {
                            //console.log(data);
                            var json = jQuery.parseJSON(data);
                            //  console.log('filter***  ' + json.recordsFilter);
                            // startPage = json.start;
                            //console.log('filter**start*  ' + json.start);
                            //console.log('filter* end **  ' + json.end);
                            //console.log('filter***  ' + json.recordsTotal);
//                            data.recordsTotal = json.total;
                            // data.recordsTotal = 100;
//                            data.recordsFiltered = json.recordsFilter;
                            //data.recordsFiltered = 10;
                            //data.start =0;
                            //data.end = 10;
                            //data.length = 100;
//                            json.data = json.list;
//                            console.log(JSON.stringify(json));
                            return JSON.stringify(json); // return JSON string
                            }
                    },
                    "rowCallback": function (row, data) {
                    if ($.inArray(data.DT_RowId, selected) !== - 1) {
                    $(row).addClass('selected');
                    }
//                        console.log("data **** "+ JSON.stringify(data));
//                        console.log("data **** "+ data.isFraud);
                    if (data.isFraud == "YES")
                    {
                    $('td', row).css('background-color', 'Red');
                    }
                    },
                    paging: true,
                    "pagingType": "full_numbers",
                    "paginate": {
                    "first": "First",
                            "previous": "Previous",
                            "next": "Next",
                            "last": "Last"
                    },
                    language: {
                    "lengthMenu": "Display _MENU_ records per page",
                            "info": "Showing _START_ to _END_ of _TOTAL_ entries",
                            processing: '<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i><span class="sr-only">Loading...</span> ',
//                        searchPlaceholder: "Search Consignee",
//                        "infoEmpty": "",
//                        "infoEmpty": "Showingss "+startPage +" of "+endPage +data,
//                        "infoFiltered": "11 "+startPage
            },
                    infoCallback: function (settings, start, end, max, total, pre) {
                    console.log("start @@@@@ " + simpleStringify(table.rows().data()));
                    console.log("start " + start + " end" + end + " max " + max + " total " + total + " pre " + pre)
                            /*if (0 == max) {
                             return 'Showing 0 records';
                             }
                             if (1 == max) {
                             return 'Showing 1 record';
                             }
                             if (total == max) {
                             return 'Showing ' + max + ' records';
                             }*/
                            return pre; //'Showing ' + total + ' of ' + max + ' records';
                    },
                    drawCallback: function () {
                    //  processInfo(this.api().page.info());
                    console.log("page info " + JSON.stringify(this.api().page.info()));
                    },
                    stateSave: true,
                    fnStateLoaded: function (oSettings, oData) {
                    //console.log('Saved filter was: ' + JSON.stringify(oData));
                    //console.log('Saved filter was search: ' + JSON.stringify(oData.columns));
                    var searchvalue;
                    for (i = 0; i < oData.columns.length; i++) {
                    // x += oData.columns[i];
                    //  console.log("x " + i + "  " + JSON.stringify(oData.columns[i]))
                    // console.log("       x " + i + "  " + JSON.stringify(oData.columns[i].search.search))
                    switch (i) {
                    case 0: //sb_date
                            searchvalue = (oData.columns[i].search.search);
                    //  console.log(i + "  i is 0 " + searchvalue)
                    //searchvalue = JSON.parse(searchvalue);
                    //console.log("searchvalue.startDate " + searchvalue.startDate)
                    //console.log("searchvalue.endDate: " + searchvalue.endDate)
//                                     $('#daterange').data('daterangepicker').setStartDate('04/01/2017');
                    //$('#daterange').daterangepicker({startDate: searchvalue.startDate, endDate: searchvalue.endDate});

                    break;
                    case 1: //consignee_name
                            searchvalue = (oData.columns[i].search.search);
                    if (!isEmpty(oData.columns[i].search.search)) {
                    //  alert(searchvalue);
                    $("#searchConsigneename").val(searchvalue);
                    }
                    // console.log(i + "  i is 1")
                    break;
                    case 2: //destination_port
                            searchvalue = (oData.columns[i].search.search);
                    if (!isEmpty(oData.columns[i].search.search)) {
                    // alert(searchvalue);
                    $("#searchPort").val(searchvalue);
                    }
                    // console.log(i + "  i is 2")
                    break;
                    case 3: //quantity
                            searchvalue = (oData.columns[i].search.search);
                    console.log("case 3  " + simpleStringify(searchvalue));
//                                    searchvalue = JSON.parse(searchvalue);
//                                    var myJSON = JSON.stringify(searchvalue);

//                                    console.table(i + "  i is 3 *****>>> " + JSON.stringify(searchvalue))

//                                    console.dir(i + "  i is 3 **444***>>> " + myJSON)
//                                    console.log(i + "  i is 3 *****>>> " + JSON.parse(searchvalue))

                    break;
                    case 4: //unit
                            searchvalue = (oData.columns[i].search.search);
                    if (!isEmpty(oData.columns[i].search.search)) {
                    // alert(searchvalue);
                    $("#searchUnit").val(searchvalue);
                    }
                    //console.log(i + "  i is 4")
                    break;
                    case 5: //is_contact_info_found
                            searchvalue = (oData.columns[i].search.search);
                    if (!isEmpty(oData.columns[i].search.search)) {
                    // alert(searchvalue);
                    $("#searchContactFound").val(searchvalue);
                    }
                    // console.log(i + "  i is 5")
                    break;
                    case 6: //hasTooManyShipments
                            searchvalue = (oData.columns[i].search.search);
                    if (!isEmpty(oData.columns[i].search.search)) {
                    // alert(searchvalue);
                    $("#searchShipmentSize").val(searchvalue);
                    }
                    //  console.log(i + "  i is 6")
                    break;
                    case 7: //consignee_country
                            searchvalue = (oData.columns[i].search.search);
                    if (!isEmpty(oData.columns[i].search.search)) {
                    // alert(searchvalue);
                    $("#searchConsigneeCountry").val(searchvalue);
                    }
                    // console.log(i + "  i is 7")
                    break;
                    case 8: //updatedOn
                            searchvalue = (oData.columns[i].search.search);
                    // console.log(i + "  i is 8")
                    break;
                    case 9: //isFraud
                            searchvalue = (oData.columns[i].search.search);
                    if (!isEmpty(oData.columns[i].search.search)) {
                    // alert(searchvalue);
                    $("#searchIsFraud").val(searchvalue);
                    }
                    // console.log(i + "  i is 9")
                    break;
                    case 10: //everContacted
                            searchvalue = (oData.columns[i].search.search);
                    if (!isEmpty(oData.columns[i].search.search)) {
                    // alert(searchvalue);
                    $("#searchEverContacted").val(searchvalue);
                    }
                    // console.log(i + "  i is 10")
                    break;
                    }
                    }
                    },
                    "lengthMenu": [[10, 25, 50, 100], [10, 25, 50, 100]],
                    scrollY: '52vh',
                    scrollX: '52vh',
                    // scrollY: 200,
                    deferRender: true,
                    scroller: true,
                    keys: true, fixedHeader: {
                    //header: true,
                    //  footer: true
                    },
                    "responsive": true,
//                    "ajax": "ajax.data",

                    /*buttons: [
                            'pageLength', 'copy', 'excel', 'pdf'
                    ],*/
                    "columns": [
                    {"data": "sb_date", "width": "0px"}, //0
                    {"data": "consignee_name"}, //1
                    {"data": "destination_port"}, //2
                    {"data": "quantity"}, //3
                    {"data": "unit"}, //4
                    {"data": "is_contact_info_found"}, //5
                    {"data": "hasTooManyShipments"}, //6
                    {"data": "consignee_country"}, //7
                    {"data": "updatedOn"}, //8
                    {"data": "isFraud"}, //9
                    {"data": "everContacted"} //10
                    ],
            });
            // add row


            // on click

            $('#example tbody').on('click', 'tr', function () {
            //alert('sgn selected ')
            //  console.log("%%%%%%%%% " + JSON.stringify(table.row(this).data()));
            var cname1 = table.row(this).data().consignee_name;
            document.getElementById('loadNewPage').src = "Grid_createGrid.action?cName=" + cname1;
            //  console.log("URL for IFRAME " + document.getElementById('loadNewPage').src);
            $("#modalTitle").html(cname1);
            //  callPage();
            $('#myModal').modal('show');
            if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
            } else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            }
            });
            $('#button').click(function () {
            table.row('.selected').remove().draw(false);
            });
            $('#showBtn').click(function () {
            //alert("ss")
            //table.columns.adjust().draw(false);
            });
            //  table.column(0).width("2%");
            table.column(0).visible(true, true);
            table.column(1).visible(true, true);
            table.column(2).visible(true, true);
            table.column(3).visible(true, true);
            table.column(4).visible(true, true);
            table.column(5).visible(true, true);
            table.column(6).visible(true, true);
            table.column(7).visible(true, true);
            table.column(8).visible(true, true);
            table.column(9).visible(true, true);
            table.column(10).visible(true, true);
//                table.column(7).visible(true, true);
            // Apply the search


            /*table.columns().every(function () {
             var that = this;
             $('input', this.footer()).on('keyup change', function () {
             console.log(" **** "+that.search() +"  "+this.value)
             if (that.search() !== this.value) {
             that.search(this.value).draw();
             }
             });
             })
             
             // loader hide
             //                $('#loadNewPage').on('load', function () {
             //                    alert('loader hide event')
             //
             //                    $('#loader1').hide();
             //                });*/
            $('#searchConsigneename').on('keyup change', function () {
            //table.search(this.value).draw();
            console.log("searchConsigneename length " + this.value.length);
            /* if (this.value.length === 3) {
             table.column(1).search(this.value, true, false).draw();
             } else if (this.value.length === 0) {
             table.column(1).search(this.value, true, false).draw();
             }*/
            table.column(1).search(this.value, true, false).draw();
            // console.log("searchConsigneename  " + this.value);
            });
            $('#searchPort').on('keyup change', function () {
            //table.search(this.value).draw();
            table.column(2).search(this.value, true, false).draw();
            // console.log("searchPort  " + this.value);
            });
            $('#searchUnit').on('keyup change', function () {
            //table.search(this.value).draw();
            table.column(4).search(this.value, true, false).draw();
            // console.log("unit  " + this.value);
            });
            $('#searchContactFound').on('keyup change', function () {
            //table.search(this.value).draw();
            table.column(5).search(this.value, true, false).draw();
            //console.log("searchContactFound  " + this.value);
            });
            $('#searchShipmentSize').on('keyup change', function () {
            //table.search(this.value).draw();
            table.column(6).search(this.value, true, false).draw();
            // console.log("searchShipmentSize  " + this.value);
            });
            $('#searchConsigneeCountry').on('keyup change', function () {
            //table.search(this.value).draw();
            table.column(7).search(this.value, true, false).draw();
            // console.log("searchConsigneeCountry  " + this.value);
            });
            $('#searchIsFraud').on('keyup change', function () {
            //table.search(this.value).draw();
            table.column(9).search(this.value, true, false).draw();
            // console.log("searchIsFraud  " + this.value);
            });
            $('#searchEverContacted').on('keyup change', function () {
            //table.search(this.value).draw();
            table.column(10).search(this.value, true, false).draw();
            // console.log("searchEverContacted  " + this.value);
            });
            $('#minQty').on('keyup change', function () {
            //table.search(this.value).draw();
            var mnQty = $("#minQty").val();
            var mxQty = $("#maxQty").val();
            var qtyRange = {"minQty": mnQty, "maxQty": mxQty}

            table.column(3).search(qtyRange, true, false).draw();
            // console.log("minQty  " + mnQty + "  maxQty " + mxQty);
            });
            $('#maxQty').on('keyup change', function () {
            var mnQty = $("#minQty").val();
            var mxQty = $("#maxQty").val();
            var qtyRange = {"minQty": mnQty, "maxQty": mxQty}

            table.column(3).search(qtyRange, true, false).draw();
            console.log("minQty  " + mnQty + "  maxQty " + mxQty);
            });
            //search custom
            $('#search').on('click change', function (event) {
            event.preventDefault();
            /* if ($('#minQty').val() == "")
             {
             $('#minQty').focus();
             } else if ($('#maxQty').val() == "")
             {
             $('#maxQty').focus();
             } else
             {
             table.draw();
             }*/
            table.draw();
            });
            $("#myModal").on("hidden.bs.modal", function () {
            // put your default event here
            table.draw();
            });
            $('input[name="daterange"]').daterangepicker({
            opens: 'left',
                    locale: {
                    format: 'DD-MM-YYYY',
                            cancelLabel: 'Clear'
                    },
                    //  "showDropdowns": true,
                    // "startDate": "01/09/2017",
                    "endDate": "31/08/2018",
                    //"minDate": "01/09/2017",
                    "maxDate": "31/08/2018",
                    "autoApply": true,
                    "maxSpan": {
                    "days": 365
                    },
            }, function (start, end, label) {
            // console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
            var dateRanges = {"startDate": start.format('YYYY-MM-DD'), "endDate": end.format('YYYY-MM-DD')}
            // console.log("dateRanges " + JSON.stringify(dateRanges));
            table.column(0).search(JSON.stringify(dateRanges), true, false).draw();
            });
            $('#daterange').on('cancel.daterangepicker', function (ev, picker) {
            //do something, like clearing an input
            $('#daterange').val('');
            });
            $('#daterange').data('daterangepicker').setStartDate('01/09/2017');
            $('#daterange').data('daterangepicker').setEndDate('31/08/2018');
            $("#clearSearch").click(function () {
            table.column(0).search("", true, false).draw();
            $("#minQty").val("");
            $("#maxQty").val("");
//                    $("#maxQty").val("");

            table.column(1).search("", true, false).draw();
            $("#searchConsigneename").val("");
            table.column(2).search("", true, false).draw();
            $("#searchPort").val("");
            table.column(3).search("", true, false).draw();
            table.column(4).search("", true, false).draw();
            $("#searchContactFound").val("");
            table.column(5).search("", true, false).draw();
            $("#searchShipmentSize").val("");
            table.column(6).search("", true, false).draw(); // searchConsigneeCountry
            $("#searchConsigneeCountry").val("");
            table.column(7).search("", true, false).draw();
            $("#searchIsFraud").val("");
            table.column(8).search("", true, false).draw();
            $("#searchEverContacted").val("");
            table.column(9).search("", true, false).draw();
            $("#searchUnit").val("");
            table.column(10).search("", true, false).draw();
            //$("#searchConsigneeCountry").val("");
            });
            }); // ready ends &columns[0][search][value]



            /*function callPage(urlParam) {
             alert("callPage")
             $('#myModal').on('shown.bs.modal', function () {      //correct here use 'shown.bs.modal' event which comes in bootstrap3
             $(this).find('iframe').attr('src', 'Grid_createGrid.action?cName='+cName)
             })
             /*alert("callPage")
             $("#pageContent").load("View_view.action", function (responseTxt, statusTxt, xhr) {
             if (statusTxt == "success")
             alert("External content loaded successfully!");
             if (statusTxt == "error")
             alert("Error: " + xhr.status + ": " + xhr.statusText);
             });*/
            /* }*/

            function isEmpty(value) {
            return typeof value == 'string' && !value.trim() || typeof value == 'undefined' || value === null;
            }

            function simpleStringify(object) {
            var simpleObject = {};
            for (var prop in object) {
            if (!object.hasOwnProperty(prop)) {
            continue;
            }
            if (typeof (object[prop]) == 'object') {
            continue;
            }
            if (typeof (object[prop]) == 'function') {
            continue;
            }
            simpleObject[prop] = object[prop];
            }
            return JSON.stringify(simpleObject); // returns cleaned up JSON
            }
            ;

        </script>
    </head>
    <body>


        <div class="container">

            <!-- Trigger the modal with a button -->
            <!--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>-->

            <button type="button" class="block" data-toggle="collapse" data-target="#searchTab">Show/Hide Search Tab</button>

            <!--<h2>View Data</h2>-->

            <!--<button data-toggle="collapse" data-target="#searchTab">Search Tab</button>-->

            <div class="collapse" style="text-align: center" id="searchTab">
                <div class="well">
                    <form class="form-inline" style="text-align: center">
                        <h4>Search  </h4>
                        <hr>
                        <div class="form-group">
                            <label for="minQty">Quantity</label>
                            <input type="number" class="form-control" id="minQty" name="minQty">
                        </div>
                        <div class="form-group">
                            <label for="number">Between</label>
                            <input type="number" class="form-control" id="maxQty" name="maxQty">
                        </div>
                        <div class="checkbox">
                            <!--<label><input type="checkbox"> Remember me</label>-->
                        </div>

                        <!--<button style="background-color: rgb(124,77,255);color: #fff" type="submit" id="search"  class="btn btn-primary">SEARCH</button>-->
                        <div class="form-group">
                            <label for="daterange">Date</label>
                            <input type="text" class="form-control" id="daterange" name="daterange" />
                        </div>

                        <hr>
                        <div class="form-group">
                            <label for="srchConsigneename">Search by Consignee Name</label>
                            <input type="text" class="form-control" id="searchConsigneename" name="searchConsigneename">
                        </div>
                        <div class="form-group">
                            <label for="srchPort">Search by Port Name</label>

                            <s:select id="searchPort" name="searchPort"  class="form-control" list="searchPortMap"  headerKey="" headerValue="--All--"/>
                            <!--<input type="text" class="form-control" id="searchPort" name="searchPort">-->
                        </div>
                        <div class="form-group">
                            <label for="searchUnit">Search by Unit</label>
                            <s:select id="searchUnit" name="searchUnit"  class="form-control" list="searchUnitList"  headerKey="" headerValue="--All--"/>

                            <!--<input type="text" class="form-control" id="searchUnit" name="searchUnit">-->
                        </div>
                        <hr>
                        <div class="form-group">
                            <label for="srchContactFound">Search by Contact Found?</label>
                            <!--                        <input type="text" class="form-control" id="searchContactFound" name="searchContactFound">-->
                            <s:select id="searchContactFound" name="searchContactFound"  class="form-control" 
                                      list="searchContactFoundMap"  headerKey="" headerValue="--All--"/>
                        </div>
                        <div class="form-group">
                            <label for="srchShipmentSize">Search by Shipment Size</label>
                            <!--<input type="text" class="form-control" id="searchShipmentSize" name="searchShipmentSize">-->
                            <s:select id="searchShipmentSize" name="searchShipmentSize"  class="form-control" 
                                      list="searchHasTooManyShipmentsMap"  headerKey="" headerValue="--All--"/>
                        </div>
                        <div class="form-group">
                            <label for="srchConsigneeCountry">Search by Country</label>
                            <s:select id="searchConsigneeCountry" name="searchConsigneeCountry"  class="form-control" 
                                      list="searchCountryList"  headerKey="" headerValue="--All--"/>

                            <!--<input type="text" class="form-control" id="searchConsigneeCountry" name="searchConsigneeCountry">-->
                        </div>
                        <hr>
                        <div class="form-group">
                            <label for="srchIsFraud">Search by Fraud?</label>
                            <!--<input type="text" class="form-control" id="searchIsFraud" name="searchIsFraud">-->
                            <s:select id="searchIsFraud" name="searchIsFraud"  class="form-control" 
                                      list="searchIsFraudMap"  headerKey="" headerValue="--All--"/>
                        </div>
                        <div class="form-group">
                            <label for="srchEverContacted">Search by Contacted?</label>
                            <s:select id="searchEverContacted" name="searchEverContacted"  class="form-control" 
                                      list="searchEverContactedMap"  headerKey="" headerValue="--All--"/>
                            <!--<input type="text" class="form-control" id="searchEverContacted" name="searchEverContacted">-->
                        </div>
                        <div class="form-group">
                            <button style="background-color: rgb(124,77,255);color: #fff" type="button" id="clearSearch"  class="btn btn-primary" >Clear Search</button>

                        </div>
                    </form> 
                </div>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog" tabindex='-1'>
                <div class="modal-dialog modal-lg">

                    <!-- Modal content-->
                    <div class="modal-content" id="pageContent">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 id="modalTitle" class="modal-title">
                            </h4>
                        </div>
                        <div class="modal-body">
                            <!--<p>Some text in the modal.</p>-->
                            <!--<iframe src="Grid_createGrid.action?cName=abcd" width="300" height="380" frameborder="0" allowtransparency="true"></iframe>--> 
                            <iframe id="loadNewPage" src="" style="display: block;width:100%;height:400" width="100%" height="400vh"   frameborder="0" allowtransparency="true"></iframe> 
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>

        </div>
        <!--<input type="button" id="showBtn" value="show Qty" />-->
        <!--length=25, search, start =100, search[value]-->
        <!--<h1>Hello World!</h1>-->
        <table id="example" class="display" style="width:100%">
            <thead>
                <tr>
                    <th class="all">Date</th>  
                    <th class="all">Consignee Name</th> 
                    <th class="all">Port</th> 
                    <th class="all">Quantity</th> 
                    <th class="all">Unit</th> 
                    <th class="all">Contact Found?</th> 
                    <th class="all">Many Shipments?</th>
                    <th class="all">Consignee Country</th>
                    <th class="all">Updated On</th> 
                    <th class="all">Is Fraud?</th>  
                    <th class="all">Ever Contacted?</th> 
                    <!--<th class="all"> </th>-->
                </tr>
            </thead>
            <!--            <tfoot>
                            <tr>
                                <th class="all">Date</th>
                                <th class="all">Consignee Name</th> 
                                <th class="all">Port</th> 
                                <th class="all">Quantity</th>
                                <th class="all">Unit</th>
                                <th class="all">Has contacted detail?</th>  
                                <th class="all">Many Shipments?</th>  
                                <th class="all">Consignee Country</th>
                                <th class="all">Updated On</th>
                                <th class="all">Is Fraud?</th>
                                <th class="all">Ever Contacted?</th>
                            </tr>
                        </tfoot>-->
        </table>
    </body>
</html>
<!--<script type="text/javascript" src="jquery.dataTables.js"></script>-->
