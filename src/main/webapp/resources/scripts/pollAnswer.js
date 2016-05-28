$(document).ready(function(){
    var csrf = $("#csrf").val();
    var form = $(".addAnswer");
    var pollId = $("#pollId").html();
    var poll = $(form).find("input[name='answer']")[0];
    $(".addNewPoll").on("click", function(e){
        e.preventDefault();
        console.log(pollId);
        $.ajax({
            type: 'POST',
            url: "/admin/management/poll/dynamic/answer/add?id="+ pollId,
            data: JSON.stringify($(poll).val()),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            headers: {'X-CSRF-TOKEN':csrf},
            success: function(response){
                var table = $("#pollTable");
                $(table).append('<tr id="'+response.id + '"><td>'+response.text+'</td>' + '"><td>' + response.selected + '</td>'+ '"><td>'+'<a href="/admin/management/poll/delete?id=' + response.id + '"><img src="/static/image/ico/delete1.ico" class="table_ico" /></a>'+'</td>' + '</tr>');
            },
            error: function(error){
                console.log(error);
            }
        });
    });
});