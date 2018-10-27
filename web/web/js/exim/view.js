/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('.panel-collapse').on('show.bs.collapse', function () {
    $(this).siblings('.panel-heading').addClass('active');
});

$('.panel-collapse').on('hide.bs.collapse', function () {
    $(this).siblings('.panel-heading').removeClass('active');
});

function setValue(buttonName) {
    var value = $("#" + buttonName).val();
    document.getElementById("eximBean.comment").value += value;
}

function setTitle() {
    document.title = "REPL-" + document.getElementById("eximBean.consigneeName").value;

}



function insertAtCaret(text) {
    text = document.getElementById(text).value;
    var txtarea = document.getElementById("eximBean.comment");
    if (!txtarea) {
        return;
    }

    var scrollPos = txtarea.scrollTop;
    var strPos = 0;
    var br = ((txtarea.selectionStart || txtarea.selectionStart == '0') ?
            "ff" : (document.selection ? "ie" : false));
    if (br == "ie") {
        txtarea.focus();
        var range = document.selection.createRange();
        range.moveStart('character', -txtarea.value.length);
        strPos = range.text.length;
    } else if (br == "ff") {
        strPos = txtarea.selectionStart;
    }

    var front = (txtarea.value).substring(0, strPos);
    var back = (txtarea.value).substring(strPos, txtarea.value.length);
    txtarea.value = front + text + back;
    strPos = strPos + text.length;
    if (br == "ie") {
        txtarea.focus();
        var ieRange = document.selection.createRange();
        ieRange.moveStart('character', -txtarea.value.length);
        ieRange.moveStart('character', strPos);
        ieRange.moveEnd('character', 0);
        ieRange.select();
    } else if (br == "ff") {
        txtarea.selectionStart = strPos;
        txtarea.selectionEnd = strPos;
        txtarea.focus();
    }

    txtarea.scrollTop = scrollPos;
}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
}
;

function loadDetailReportData() {
//    var bla = $('#eximBean.consigneeName').val();

//    alert("bla " + document.getElementById("eximBean.consigneeName").value);
    var pageNo = getUrlParameter("pageNo")
    //console.log("page No " + getUrlParameter("pageNo"));
    $.ajax({
        url: "Exim_loadConsigneeDetailReport.action",//?pageNo=" + pageNo + "&consigneeName=" + encodeURIComponent(document.getElementById("eximBean.consigneeName")).value,
         data: {
        consigneeName: document.getElementById("eximBean.consigneeName").value,
        //uid: uid
        //...
    },
        dataType: "html",
        success: function (data) {
            
            // var obj = jQuery.parseJSON(data);
                        console.log(data);

            $("#loader").hide();
            $("#table1").html(data);
//            alert(""+data);
        }, error: function(){
            // notify user of error or retry
            alert("Try Again Refreshing Page!")
      }
    });
//$("#div1").load("Exim_loadConsigneeDetailReport.action");
}


function setValue(buttonName) {
    var value =  $("#" + buttonName).val();
    //document.getElementById("eximBean.comment").value += value;
     CKEDITOR.instances['eximBean.comment'].insertHtml("<p><strong> <span class='marker'>"+ value+ "&nbsp;</span></strong></p>");
//    ('textarea#eximBean.comment').ckeditor().editor.insertText('eximBean.comment');
}

