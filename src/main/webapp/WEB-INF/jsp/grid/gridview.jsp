<%-- 
    Document   : view
    Created on : Oct 24, 2018, 12:15:18 PM
    Author     : Punj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.colVis.min.js"></script>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!--<script src="js/grid/eximviewGrid.js" type="text/javascript"></script>-->

        <!-- other page js css -->




        <style>
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

        </style>

        <script>

            function format(d) {
                return 'Full name: ' + d.first_name + ' ' + d.last_name + '<br>' +
                        'Salary: ' + d.salary + '<br>' +
                        'The child row can contain any data you wish, including links, images, inner tables etc.';
            }
            $(document).ready(function () {
                // Setup - add a text input to each footer cell
                $('#example tfoot th').each(function (i) {
                    var title = $('#example thead th').eq($(this).index()).text();
                    $(this).html('<input type="text" placeholder="Search ' + title + '" data-index="' + i + '" />');
                });
                //
                ;
                var selected = [];
                var table = $('#example').DataTable({
                    dom: 'Bfrtip',
                    buttons: [
                        'colvis'
                    ],
                    columnDefs: [
                        {
//                            targets: -1,
//                            visible: false
                        }
                    ],
                    select: true,
                    "processing": true,
                    "serverSide": true,
//                   table.columns.adjust().draw( false );
                    "ajax"
                            : {
                                "url"
                                        : "Grid_loadGridData.action",
//                        "url": "ajax2.data",
                                "data"
                                        : function (d) {
                                            console.log("!!!!! " + JSON.stringify(d));
                                            d.myKey = "myValue";
                                            // d.custom = $('#myInput').val();
                                            // etc
                                        },
                                /* success: function () {
                                 console.log("success")
                                 //                            table.draw();
                                 },*/
                                dataFilter: function (data) {
                                    var json = jQuery.parseJSON(data);
                                    console.log('filter***  ' + json);
                                    console.log('filter***  ' + json.recordsTotal);
                                    json.recordsTotal = json.total;
                                    json.recordsFiltered = json.total;
//                            json.data = json.list;
//                            console.log(JSON.stringify(json));
                                    return JSON.stringify(json); // return JSON string
                                }
                            },
                    "rowCallback": function (row, data) {
                        if ($.inArray(data.DT_RowId, selected) !== -1) {
                            $(row).addClass('selected');
                        }
                    },
                    "pagingType": "full_numbers",
                    stateSave: true,
                    scrollY: '50vh',
                    // scrollY: 200,
                    deferRender: true,
                    scroller: true,
                    keys: true, fixedHeader: {
                        header: true,
                        footer: true
                    },
                    "responsive": false,
//                    "ajax": "ajax.data",

                    buttons: [
                        'copy', 'excel', 'pdf'
                    ],
                            "columns": [
                                {"data": "sb_date"},
                                {"data": "consignee_name"},
                                {"data": "destination_port"},
                                {"data": "quantity"},
                                {"data": "unit"},
                                {"data": "is_contact_info_found"},
                                {"data": "hasTooManyShipments"}
                            ],
                    "columnDefs": [
                        {
                            "width": "2%",
                            "targets": [0],
                            "visible": true,
                            "searchable": false
                        },
                        {
                            "targets": [1],
                            "visible": true
                        },
                        {
                            "targets": [2],
                            "visible": true,
                            "searchable": false
                        },
                        {
                            "targets": [3],
                            "visible": true,
                            "searchable": false
                        },
                        {
                            "targets": [4],
                            "visible": false,
                            "searchable": false
                        },
                        {
                            "targets": [5],
                            "visible": false,
                            "searchable": false
                        },
                        {
                            "targets": [6],
                            "visible": false,
                            "searchable": false
                        }
                    ]
                });
                // add row


                // on click

                $('#example tbody').on('click', 'tr', function () {
                    //alert('sgn selected ')
                    console.log("%%%%%%%%% " + JSON.stringify(table.row(this).data()));
                    var cname1 = table.row(this).data().consignee_name;
                    document.getElementById('loadNewPage').src = "Grid_createGrid.action?cName=" + cname1;
                    console.log("URL for IFRAME " + document.getElementById('loadNewPage').src);
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
//                table.column(7).visible(true, true);
                // Apply the search
                table.columns().every(function () {
                    var that = this;
                    $('input', this.footer()).on('keyup change', function () {
                        if (that.search() !== this.value) {
                            that
                                    .search(this.value)
                                    .draw();
                        }
                    });
                })
            }); // ready ends



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



        </script>
    </head>
    <body>

        <div class="container">
            <h2>Data</h2>
            <!-- Trigger the modal with a button -->
            <!--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>-->

            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
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
                    <th class="all">Contacted?</th>
                    <th class="all">Shipments?</th>
                    <!--<th class="all"> </th>-->
                </tr>
            </thead>
            <tfoot>
                <tr>
                     <th class="all">Date</th>
                    <th class="all">Consignee Name</th>
                    <th class="all">Port</th>
                    <th class="all">Quantity</th>
                    <th class="all">Unit</th>
                    <th class="all">Contacted?</th>
                    <th class="all">Shipments?</th>
                    <!--<th class="all"></th>-->
                </tr>
            </tfoot>
        </table>
    </body>
</html>
<!--<script type="text/javascript" src="jquery.dataTables.js"></script>-->
