/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $('#example').DataTable( {
        responsive: {
            details: {
                display: $.fn.dataTable.Responsive.display.modal( {
                    header: function ( row ) {
                        var data = row.data();
                        return 'Details for '+data[0]+' '+data[1];
                    }
                } ),
                renderer: function ( api, rowIdx, columns ) {
                    var data = $.map( columns, function ( col, i ) {
                        return '<tr>'+
                                '<td>'+col.title+':'+'</td> '+
                                '<td>'+col.data+'</td>'+
                            '</tr>';
                    } ).join('');
 
                    return $('<table/>').append( data );
                }
            }
        }
    } );
} );