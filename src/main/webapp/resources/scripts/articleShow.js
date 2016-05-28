$(document).ready(function(){
        var csrf = $("#csrf").val();
        $(".article-item").on("click",function(e){
            console.log('dima');
            e.preventDefault();
            var target = $(this).attr("href");
            $.ajax({
                type: 'POST',
                url: target,
                data: {},
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                headers: {'X-CSRF-TOKEN':csrf},
                success: function(response){
                    console.log(response);
                    var box = $("#viewBox");
                    var tmp;
                    tmp = $(box).find('.title')[0];
                    $(tmp).html("<span>"+ response.title +"</span>");
                    tmp = $(box).find(".article_text")[0];
                    $(tmp).html(response.content);
                    tmp = $(box).find(".author")[0];
                    $(tmp).html(response.author);
                    tmp = $(box).find(".created")[0];
                    $(tmp).html(response.created);
                    $(".article-list").hide();
                    $(box).show();
                    target = "/articles/view/" + response.name;
                    window.history.pushState("", "", target);
                },
                error: function(error){
                    console.log(error);
                }
            });
        });
        window.onpopstate = function(e){
            if(window.location.href == "http://localhost:1010/articles/list"){
                var box = $("#viewBox");
                $(".article-list").show();
                $(box).hide();
            }else{
                location.href=window.location.href;
            }
        }
    }
);

