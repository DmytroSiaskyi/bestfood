$(document).ready(function() {
    var csrf = $("#csrf").val();

});
function display(id){
    var answer;
    var list = $("#" + id);
    if($(list).css('display') == 'none' && $(list).empty()) {
        $.ajax({
            type: 'POST',
            url: "/articles/list/"+id,
            data: JSON.stringify(answer),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            headers: {'X-CSRF-TOKEN':csrf},
            success: function(data){
                for (var i in data)
                {
                    $(list).append(' - <li><a href="/articles/view/'+ data[i].name +'" class="article-item"><div class="prev_title">' + data[i].title + '</div><div class="prev_text">' + data[i].previewText + '</div><div class="prew_created">' + data[i].createdString +'</div></a></li>');
                }
            },
            error: function(error){
                console.log(error);
            }
        });
        $(list).show();
    }else{
        $(list).hide();
    }
}
$(".deleteButton").on('click', function(e){
    e.preventDefault();
    var link = this.href;
    var arr = link.split('/');
    var name = arr[4];
    var id = arr[6].split('=')[1];
    console.log(id);
    deleteComment(name, id);
});
function deleteComment(name, id){
    console.log(id);
    var data = id;

    $.ajax({
        type: 'POST',
        url: "/articles/" + name + "/comment/dynamicDelete?id=" + id,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        headers: {'X-CSRF-TOKEN':csrf},
        success: function(data){
            var tmp = $("#"+id);
            tmp.hide();
        },
        error: function(error){
            console.log(error);
        }
    });
}
