var messageId;
$(document).ready(function(){
        var csrf = $("#csrf").val();
        var back = $(".background");
        var form = $(".formm");
        $(".answerMessage").on("click", function(e){
            e.preventDefault();
            $(back).show();
            $(form).show();
        });
        $(".cButton").on("click", function(){
            $(back).hide();
            $(form).hide();
        });
        $(".sButton").on("click", function(){
            var tmp = $(form).find("input[name='text']")[0];
            var obj = {
                id: messageId,
                login: "",
                email: "",
                comment: "",
                created: null
            };
            obj.comment = $(tmp).val();
            $.ajax({
                type: 'POST',
                url: "/admin/management/answer",
                data: JSON.stringify(obj),
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                headers: {'X-CSRF-TOKEN':csrf},
                error: function(error){
                    console.log(error);
                }
            });

            $(back).hide();
            $(form).hide();
        });
    }
)
function call (id){
    console.log(id);
    messageId = id;
};