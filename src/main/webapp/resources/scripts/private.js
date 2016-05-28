$(document).ready(function(){
    var csrf = $("#csrf").val();
    var back = $(".background");
    var form = $(".form");
    var thanks = $(".thanks");
    $(".wButton").on("click", function(e){
        e.preventDefault();
        $(back).show();
        $(form).show();
    });
    $(".cButton").on("click", function(){
        $(back).hide();
        $(form).hide();
    });
    $(".sButton").on("click", function(){savePost();});
    var buildFrom = function(){
        var obj = {
            author: null,
            adressee: "",
            writed: null,
            text: "",
            read: false
        };

        var tmp = $(form).find("input[name='name']")[0];
        console.log(tmp);
        obj.adressee = $(tmp).val();
        tmp = $(form).find("textarea[name='text']")[0];
        obj.text = $(tmp).val();
        return obj;
    };

    var savePost = function(){
        var data = buildFrom();
        console.log(data);
        $.ajax({
            type: 'POST',
            url: "/private/write",
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            headers: {'X-CSRF-TOKEN':csrf},
            success: function(response){
                if(response.status == "OK"){
                    //$(back).hide();
                    $(form).hide();
                    var t = $(thanks).find(".thanksText")[0];
                    $(t).html("Спасибі! Ваше повідомлення відправлено.");
                    $(thanks).show();
                }else{
                    var tmp = $(form).find("input[name='name']")[0];
                    $(tmp).val(response.data.login);
                    tmp = $(form).find("textarea[name='text']")[0];
                    $(tmp).html(response.data.text);
                    tmp = $(form).find(".error")[0];
                    console.log(response.message);
                    $(tmp).html(response.message);
                }
            },
            error: function(error){
                console.log(error);
            }
        });
    }

});
function display(id){
    var tmp = $("#"+id);
    var tempDirectory;
    if(id==2){
        tempDirectory = $("#inMessages");
    }else{
        tempDirectory = $("#outMessages");
    }
    if($(tempDirectory).css('display') == 'none'){
        $(tempDirectory).show();
    }else{
        $(tempDirectory).hide();
    }
}

