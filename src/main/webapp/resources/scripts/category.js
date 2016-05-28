var categoryId;
$(document).ready(function(){
        var csrf = $("#csrf").val();
        var back = $(".background");
        var form = $(".formm");
        $(".answerMessage").on("click", function(e){
            e.preventDefault();
            $(back).show();
            $(form).hide();
            console.log(categoryId);
            var tmp = $(form).find("input[name='id']")[0];
            $(tmp).val(categoryId);
            $(form).show();
        });
        $(".cButton").on("click", function(){
            $(back).hide();
            $(form).hide();
        });
    }
)
function call (id){
    console.log(id);
    categoryId = id;
};
