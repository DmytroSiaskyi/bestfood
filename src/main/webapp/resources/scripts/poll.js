$(document).ready(function(){
    var csrf = $("#csrf").val();
    var form = $(".addPoll");
    var poll = $(form).find("input[name='question']")[0];
    $(".addNewPoll").on("click", function(e){
        e.preventDefault();
        $.ajax({
            type: 'POST',
            url: "/admin/management/poll/dynamic/add",
            data: JSON.stringify($(poll).val()),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            headers: {'X-CSRF-TOKEN':csrf},
            success: function(response){
               var table = $("#pollTable");
                $(table).append('<tr id="'+response.id + '"><td>'+response.id+'</td>' + '"><td><a href="/admin/management/poll/edit?id=' + response.id + '">'+response.question+'</a></td>'+ '"><td>'+'<a href="/admin/management/poll/delete?id=' + response.id + '"><img src="/static/image/ico/delete1.ico" class="table_ico" /></a>'+'</td>' + '</tr>');
            },
            error: function(error){
                console.log(error);
            }
        });
    });
});
function poll(id, e, pollId){
    e.preventDefault();
    $.ajax({
        type: 'POST',
        url: "/poll?id=" + id,
        data: JSON.stringify(),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        headers: {'X-CSRF-TOKEN':csrf},
        success: function(response){
            var tmp = $("#"+id);
            var poll = $('#' + pollId + 'ul');
            var value = parseInt($(tmp).html());
            console.log(value);
            value++;
            $(tmp).html(value);
            console.log(poll);
            $(poll).find('li').each(function(){
                var current = $(this);
                current.removeAttr("onclick", null);
            });
        },
        error: function(error){
            console.log(error);
        }
    });
};