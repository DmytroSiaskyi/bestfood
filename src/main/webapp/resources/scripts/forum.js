$(document).ready(function(){
    var csrf = $("#csrf").val();
    var back = $(".background");
    var form = $(".form");
    var thanks = $(".thanks");
    $(".wButton").on("click", function(e){
        e.preventDefault();
        $(back).show();
        $(form).show();
        window.history.pushState("", "", "http://localhost:1010/forum/write");
    });
    $(".cButton").on("click", function(){
        $(back).hide();
        $(form).hide();
    });
    $(".sButton").on("click", function(){savePost();});
    var buildFrom = function(){
        var obj = {
            id: null,
            login: "",
            email: "",
            comment: "",
            created: null
        };

        var tmp = $(form).find("input[name='name']")[0];
        console.log(tmp);
        obj.login = $(tmp).val();
        tmp = $(form).find("input[name='email']")[0];
        obj.email = $(tmp).val();
        tmp = $(form).find("textarea[name='text']")[0];
        obj.comment = $(tmp).val();
        return obj;
    };

    var savePost = function(){
        var data = buildFrom();
        console.log(data);
        $.ajax({
            type: 'POST',
            url: "/forum/dynamic/write?timestamp="+new Date().getTime(),
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            headers: {'X-CSRF-TOKEN':csrf},
            success: function(response){
                if(response.status == "OK"){
                    //$(back).hide();
                    $(form).hide();
                    var t = $(thanks).find(".thanksText")[0];
                    $(t).html("Спасибі," + response.data.login + "! Ваше повідомлення відправлено.");
                    $(thanks).show();
                }else{
                    var tmp = $(form).find("input[name='name']")[0];
                    $(tmp).val(response.data.login);
                    tmp = $(form).find("input[name='email']")[0];
                    $(tmp).val(response.data.email);
                    tmp = $(form).find("textarea[name='text']")[0];
                    $(tmp).html(response.data.comment);
                    tmp = $(form).find(".error")[0];
                    $(tmp).html(response.message);
                }
            },
            error: function(error){
                console.log(error);
            }
        });
    }
    window.onpopstate = function(e){
        if(window.location.href != "http://localhost:1010/forum/write"){
            $(back).hide();
            $(form).hide();
        }else{
            $(back).show();
            $(form).show();
        }
    }
});
