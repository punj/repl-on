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
        <!--<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.18/b-1.5.4/b-colvis-1.5.4/b-html5-1.5.4/b-print-1.5.4/fh-3.1.4/kt-2.5.0/r-2.2.2/sc-1.5.0/sl-1.2.6/datatables.min.css"/>-->

        <!--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>-->
        <!--<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>-->
        <!--<script type="text/javascript" src="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.18/b-1.5.4/b-colvis-1.5.4/b-html5-1.5.4/b-print-1.5.4/fh-3.1.4/kt-2.5.0/r-2.2.2/sc-1.5.0/sl-1.2.6/datatables.min.js"></script>-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <style>
            .pagination {
                display: inline-block;
                padding-left: 0;
                margin: 23px 0;
                border-radius: 4px;
            }
        </style>

        <script>

            function format(d) {
                return 'Full name: ' + d.first_name + ' ' + d.last_name + '<br>' +
                        'Salary: ' + d.salary + '<br>' +
                        'The child row can contain any data you wish, including links, images, inner tables etc.';
            }
            $(document).ready(function () {
                var selected = [];
                var table = $('#example').DataTable({
                    select: true,
                    "serverSide": true,
                    "pagingType": "full_numbers",
                    stateSave: true,
                    scrollY: 200,
                    deferRender: true,
                    scroller: true,
                    keys: true,
//                    "ajax": "ajax.data",
                    "ajax": {
                        "url": "ajax.data",
                        "data": function (d) {
                            console.log("!!!!! " + JSON.stringify(d));
                            d.myKey = "myValue";
                            // d.custom = $('#myInput').val();
                            // etc
                        },
                        dataFilter: function (data) {
                            var json = jQuery.parseJSON(data);
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
                    buttons: [
                        'copy', 'excel', 'pdf'
                    ],
                    "columns": [
                        {"data": "name"},
                        {"data": "position"},
                        {"data": "office"},
                        {"data": "extn"},
                        {"data": "start_date"},
                        {"data": "salary"}
                    ],
                    "columnDefs": [
                        {
                            "targets": [2],
                            "visible": false,
                            "searchable": false
                        },
                        {
                            "targets": [3],
                            "visible": false
                        }
                    ]
                });

                // add row

                var detailRows = [];

                $('#example tbody').on('click', 'tr td.details-control', function () {
                    var tr = $(this).closest('tr');
                    var row = dt.row(tr);
                    var idx = $.inArray(tr.attr('id'), detailRows);

                    if (row.child.isShown()) {
                        tr.removeClass('details');
                        row.child.hide();

                        // Remove from the 'open' array
                        detailRows.splice(idx, 1);
                    } else {
                        tr.addClass('details');
                        row.child(format(row.data())).show();

                        // Add to the 'open' array
                        if (idx === -1) {
                            detailRows.push(tr.attr('id'));
                        }
                    }
                });


                // On each draw, loop over the `detailRows` array and show any child rows
                table.on('draw', function () {
                    $.each(detailRows, function (i, id) {
                        $('#' + id + ' td.details-control').trigger('click');
                    });
                });

                // on click

                /*$('#example tbody').on('click', 'tr', function () {
                 //alert('sgn selected ')
                 console.log(table.row(this).data());
                 if ($(this).hasClass('selected')) {
                 $(this).removeClass('selected');
                 } else {
                 table.$('tr.selected').removeClass('selected');
                 $(this).addClass('selected');
                 }
                 });
                 $('#button').click(function () {
                 table.row('.selected').remove().draw(false);
                 });*/
            }); // ready ends







        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table id="example" class="display" style="width:100%">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Office</th>
                    <th>Extn.</th>
                    <th>Start date</th>
                    <th>Salary</th>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th>Name</th>
                    <th>Position</th>
                    <th>Office</th>
                    <th>Extn.</th>
                    <th>Start date</th>
                    <th>Salary</th>
                </tr>
            </tfoot>
        </table>
    </body>
</html>
<!--<script type="text/javascript" src="jquery.dataTables.js"></script>-->
