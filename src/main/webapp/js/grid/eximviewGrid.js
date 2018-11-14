/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var value;
var urlParameter;
$(document).ready(function () {
    $("#inlineloader").hide();
    // date picker
    // 
    //   $('input[name="dates"]').daterangepicker();
//date picker ends
    //   alert("#1#1#1" + window.location.href);
    var url = new URL(window.location.href);
    urlParameter = url.searchParams.get("cName");
    setConsigneeValues(urlParameter)
    console.log(urlParameter);
    $("#alert-message-success").hide();
    $("#alert-message-fail").hide();
    $("#loader1").hide();
    var options2 = {
        url: function (phrase) {
            return "View_autocomplete.action?phrase=" + phrase + "&format=json";
        },
        getValue: "name",
        requestDelay: 500,
        list: {
            showAnimation: {
                type: "fade", //normal|slide|fade
                time: 400,
                callback: function () {}
            },
            hideAnimation: {
                type: "slide", //normal|slide|fade
                time: 400,
                callback: function () {}
            }, onSelectItemEvent: function () {
                value = $("#searchBuyer").getSelectedItemData();
                console.log("@@@@@&&&& " + JSON.stringify(value));
                // $("#data-holder").val(value).trigger("change");
            }
        }
    };
    $("#searchBuyer").easyAutocomplete(options2);
    
    
    $("#myModal").on("hidden.bs.modal", function () {
    // put your default event here
    //alert('sgn close')
});
});
function loadData() {
    //  var   value1 = $("#searchBuyer").getSelectedItemData();

//    console.log("sgn loadData "+ JSON.stringify(value1));
    var value1 = $("#searchBuyer").getSelectedItemData();
//    var value = $("#searchBuyer").val;
    console.log("sgn loadData value " + JSON.stringify(value));
}

function setConsigneeValues(cName) {

    //  alert(" setConsigneeValues ")
    //  var value1 = $("#searchBuyer").getSelectedItemData();
//    var value = $("#searchBuyer").val;
    console.log("sgn loadData value " + cName);
    $.ajax({
        url: "View_setConsigneeValues.action", //?pageNo=" + pageNo + "&consigneeName=" + encodeURIComponent(document.getElementById("eximBean.consigneeName")).value,
        //  async: false,
        data: {
//            consigneeName: document.getElementById("eximBean.consigneeName").value,
            consigneeName: cName,
            //uid: uid
            //...
        },
        dataType: "json",
        success: function (data) {
            console.log(" " + JSON.stringify(data));
            console.log(" data.consignee_name " + urlParameter);
            //va var hasTooManyShipments  = data.hasTooManyShipments || defaultValue
            var hasTooManyShipments = ('hasTooManyShipments' in data) ? data.hasTooManyShipments : "-1";
            var email = ('email' in data) ? data.email : "";
            var web = ('web' in data) ? data.web : "";
            var phone = ('phone' in data) ? data.phone : "";
            var comment = ('comment' in data) ? data.comment : "";
            var is_contact_info_found = ('is_contact_info_found' in data) ? data.is_contact_info_found : "-1";
            var isFraud = ('isFraud' in data) ? data.isFraud : "-1";
            var everContacted = ('everContacted' in data) ? data.everContacted : "-1";
            //  alert('isFraud '+isFraud );
            console.log(" data.hasTooManyShipments " + data.hasTooManyShipments);
            console.log(" data.is_contact_info_found " + data.is_contact_info_found);
//            $("#eximBean.consigneeName1").text(data.consignee_name);
//            $("#eximBean.consigneeName1").val(data.consignee_name);
//            $("#eximBean.consigneeName1").html(data.consignee_name);
            document.getElementById("consigneeName1").value = data.consignee_name;
            document.getElementById("consigneeCountry").value = data.consignee_country;
            document.getElementById("count").value = data.count;
            document.getElementById("consignee_address").value = data.consignee_address;
            document.getElementById("eximBean.phone").value = phone
            document.getElementById("eximBean.email").value = email;
            document.getElementById("eximBean.web").value = web;
            document.getElementById("eximBean.is_contact_info_found").value = is_contact_info_found;
            document.getElementById("maxPrice").value = data.max_price;
            document.getElementById("avgPrice1").value = data.avg_price;
            document.getElementById("minPrice1").value = data.min_price;
            document.getElementById("totalQty").value = data.quantity;
//            document.getElementById("totalQty").value = data.quantity;
            document.getElementById("eximBean.isBlackListed").value = isFraud;
            document.getElementById("eximBean.everContacted").value = everContacted;

//            document.getElementById('eximBean.hasTooManyShipments').value = hasTooManyShipments;
//            document.getElementById('eximBean.hasTooManyShipments').value = hasTooManyShipments;
//            document.getElementById('eximBean.hasTooManyShipments').value = hasTooManyShipments;
            document.getElementById('eximBean.hasTooManyShipments').value = hasTooManyShipments;
            // alert(comment);
            CKEDITOR.instances['eximBean.comment'].setData(comment);

            /* var dd = document.getElementById('eximBean.hasTooManyShipments');
             for (var i = 0; i < dd.options.length; i++) {
             if (dd.options[i].text === '-1') {
             dd.selectedIndex = i;
             break;
             }
             }*/
//              $("div.eximBean.hasTooManyShipments").val(hasTooManyShipments).change();
            console.log(" Qty  " + data.quantity);
            console.log(" MaxPrice  " + data.max_price);
            console.log(" AvgPrice  " + data.min_price);
            console.log(" MinPrice  " + data.avg_price);
 
            loadDetailReportData(urlParameter);
        }, error: function () {
            // notify user of error or retry
            //   alert("Try Again Refreshing Page!")
        }, complete: function (data) {
            console.log(data);
//                                   setTimeout( console.log(data), 5000);

        }
    });
}


function loadDetailReportData(cName) {
    //   var value1 = $("#searchBuyer").getSelectedItemData();
    // alert(cName);
    $("#loader1").show();
//    var bla = $('#eximBean.consigneeName').val();

//    alert("bla " + document.getElementById("eximBean.consigneeName").value);
    // var pageNo = getUrlParameter("pageNo")
    //console.log("page No " + getUrlParameter("pageNo"));
    $.ajax({
        url: "Exim_loadConsigneeDetailReport.action", //?pageNo=" + pageNo + "&consigneeName=" + encodeURIComponent(document.getElementById("eximBean.consigneeName")).value,
        //  async: false,
        data: {
            consigneeName: cName, //document.getElementById("eximBean.consigneeName").value,
            //uid: uid
            //...
        },
        dataType: "html",
        success: function (data) {

            // var obj = jQuery.parseJSON(data);
            setTimeout($("#loader").hide(), 30009);
            setTimeout($("#table1").html(data), 30000);
//            $("#loader").hide();
//            $("#table1").html(data);
            //alert(data.length);
            //  alert(""+data);
        }, error: function () {
            // notify user of error or retry
            //   alert("Try Again Refreshing Page!")
        }, complete: function (data) {
            console.log(data);
            $("#loader1").hide();

//                                   setTimeout( console.log(data), 5000);

        }
    });
//$("#div1").load("Exim_loadConsigneeDetailReport.action");
}

function submitSave() {
            // alert('sgn edited '+changeDetected);
        // submit the form
    //   var v = document.getElementById('eximBean.is_contact_info_found').value;
    //               alert(v);
    //    alert($( "#eximBean.is_contact_info_found option:selected" ).text());
    //    var conceptName = $('#eximBean.is_contact_info_found').find(":selected").text();
    //    alert(conceptName);
    // if (v != -1) {
    //
    //
//    alert(CKEDITOR.instances.editor1.getData());
    var comment = CKEDITOR.instances['eximBean.comment'].getData();
    var is_contact_info_found = document.getElementById('eximBean.is_contact_info_found').value;
    var hasTooManyShipments = document.getElementById('eximBean.hasTooManyShipments').value;
    var isBlackListed = document.getElementById('eximBean.isBlackListed').value;
    var everContacted = document.getElementById('eximBean.everContacted').value;

    //alert("*** "+document.getElementById('eximBean.isBlackListed').value);//                                    alert("do submit")
    var params = {
        consigneeName: $("#consigneeName1").val(),
        is_contact_info_found: is_contact_info_found,
        hasTooManyShipments: hasTooManyShipments,
        phone: document.getElementById('eximBean.phone').value,
        web: document.getElementById('eximBean.web').value,
        email: document.getElementById('eximBean.email').value,
        comments: comment
    };
    var query = "View_updateConsigneeInfo.action?"
    var param = jQuery.param(params);
    query = query + param;
    //var shallowEncoded = $.param(str, true);
    //var shallowDecoded = decodeURIComponent(shallowEncoded);

    //console.log("shallowEncoded  "+shallowEncoded);
    //console.log("shallowDecoded  "+shallowDecoded);
    //alert(shallowDecoded);

    // console.log("Query  " + param);
    // document.myform.action = query;
    // document.myform.submit();
    /*} else {
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
     }*/

    $("#loader1").show();
    $.ajax({
        url: "View_updateConsigneeInfo.action", //?pageNo=" + pageNo + "&consigneeName=" + encodeURIComponent(document.getElementById("eximBean.consigneeName")).value,
        //  async: false,
        data: {
            consigneeName: $("#consigneeName1").val(),
            is_contact_info_found: is_contact_info_found,
            hasTooManyShipments: hasTooManyShipments,
            phone: document.getElementById('eximBean.phone').value,
            web: document.getElementById('eximBean.web').value,
            email: document.getElementById('eximBean.email').value,
            comments: comment,
            isBlackListed: isBlackListed,
            everContacted: everContacted,
        },
        dataType: "text",
        success: function (data) {

            // var obj = jQuery.parseJSON(data);
            //   setTimeout($("#loader").hide(), 30009);
            // setTimeout($("#table1").html(data), 30000);
//            $("#loader").hide();
//            $("#table1").html(data);
            //alert(data.length);
            //  alert(""+data);
            scrollToTop();
            $("#alert-message-success").fadeTo(2000, 500).slideUp(500, function () {
                $("#alert-message-success").slideUp(500);
            });
//            });
        }, error: function () {
            // notify user of error or retry
            //   alert("Try Again Refreshing Page!")
            scrollToTop();
            $("#alert-message-fail").fadeTo(2000, 500).slideUp(500, function () {
                $("#alert-message-fail").slideUp(500);
            });
        }, complete: function (data) {
            console.log(data);
            $("#loader1").hide();
//                                   setTimeout( console.log(data), 5000);

        }
    });
}

function scrollToTop() {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
}


function setReminder() {
//    alert('sgn ');
    $("#inlineloader").show();
   //  $("#datepicker").datepicker("setDate", "1");
    var date = $("#datepicker").val();
    var time = $("#timepicker").val();
    time = time.replace(':', "")
    var datetime = time + "." + date;
    // alert('date ' + date + " time " + time);
    //alert(datetime);
    //1300.7.Aug.2015
    // 900.31.Oct.2018
    if (date == "") {
        alert('Select date');
    } else {
        $.ajax({
            url: "Reminder_.action",
            //  async: false,
            data: {
                date: date,
                time: time,
                datetime: datetime,
             //   defaultTime: '8:00',
                consigneeName: $("#consigneeName1").val(),
            },
            dataType: "json",
            success: function (data) {
                alert("ss");
                $("#inlineloader").hide();

            }, error: function () {
            }, complete: function (data) {
                $("#inlineloader").hide();

                console.log(data);

            }
        });
    }

}