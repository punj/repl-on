/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var value;
var urlParameter;
$(document).ready(function () {
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
            document.getElementById("totalQty").value = data.quantity;
            document.getElementById("totalQty").value = data.quantity;

            document.getElementById('eximBean.hasTooManyShipments').value = hasTooManyShipments;
            document.getElementById('eximBean.hasTooManyShipments').value = hasTooManyShipments;
            document.getElementById('eximBean.hasTooManyShipments').value = hasTooManyShipments;
            document.getElementById('eximBean.hasTooManyShipments').value = hasTooManyShipments;
            CKEDITOR.instances['eximBean.comment'].insertHtml(comment);
            /* var dd = document.getElementById('eximBean.hasTooManyShipments');
             for (var i = 0; i < dd.options.length; i++) {
             if (dd.options[i].text === '-1') {
             dd.selectedIndex = i;
             break;
             }
             }*/
//              $("div.eximBean.hasTooManyShipments").val(hasTooManyShipments).change();
            console.log(" Qty  " + data.quantity)
            console.log(" MaxPrice  " + data.max_price)
            console.log(" AvgPrice  " + data.min_price)
            console.log(" MinPrice  " + data.avg_price)
            // var obj = jQuery.parseJSON(data);
            // setTimeout($("#loader").hide(), 30009);
            //setTimeout($("#table1").html(data), 30000);
//            $("#loader").hide();
//            $("#table1").html(data);
            //alert(data.length);
            //  alert(""+data);

//            value1
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

    //    alert("*** "+document.getElementById('eximBean.is_contact_info_found').value);//                                    alert("do submit")
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
            comments: comment
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