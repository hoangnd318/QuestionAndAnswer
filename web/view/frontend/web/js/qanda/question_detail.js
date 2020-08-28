//binh chon best answer
$('.vote-best-answer').click(function () {
    var a_id = $(this).parent().children()[0].value;
    $.ajax({
        url: context_path + "/qanda/question/answer/bestanswer/vote",
        type: "post",
        dataType: "text",
        data: {
            a_id: a_id
        },
        success: function (data) {
            var resultJson = JSON.parse(data);
            if (resultJson.type == "ACCESS") {
                location.reload();
            } else {

            }
        }
    });
});

//sua cau tra loi
$('.edit-comment').click(function () {
    var c_id = $(this).parent().children()[0].value;
    $("#c_t_" + c_id).hide();
    $("#e_c_b_" + c_id).show();
    inputEditCommentListenner(c_id);
});

function inputEditCommentListenner(c_id) {
    $('.i_c_e').keyup(function (e) {
        if (e.keyCode == 13)
        {
            var c_content = $(this).val();
            if (c_content == '') {
                alert("Chưa nhập đủ thông tin");
            } else {
                $.ajax({
                    url: context_path + "/qanda/question/answer/comment/edit/save",
                    type: "post",
                    dataType: "text",
                    data: {
                        c_id: c_id,
                        c_content: c_content
                    },
                    success: function (data) {
                        var resultJson = JSON.parse(data);
                        if (resultJson.type == "ACCESS") {
                            $("#c_t_" + c_id).html(c_content);
                            $("#c_t_" + c_id).show();
                            $("#e_c_b_" + c_id).hide();
                            console.log($("#c_t_" + c_id));
                        } else {
                            alert("Đã xảy ra lỗi");
                        }
                    }
                });
            }
        }
    });
}

//xoa cau tra loi
$('.delete-answer').click(function () {
    if (confirm('Bạn có chắc chắn muốn xóa!')) {
        var a_id = $(this).parent().children()[0].value;
        $.ajax({
            url: context_path + "/qanda/question/answer/delete",
            type: "post",
            dataType: "text",
            data: {
                a_id: a_id
            },
            success: function (data) {
                var resultJson = JSON.parse(data);
                if (resultJson.type == "ACCESS") {
                    location.reload();
                } else {

                }
            }
        });
    }
});

//xoa binh luan
$('.delete-comment').click(function () {
    if (confirm('Bạn có chắc chắn muốn xóa!')) {
        var c_id = $(this).parent().children()[0].value;
        $.ajax({
            url: context_path + "/qanda/question/answer/comment/delete",
            type: "post",
            dataType: "text",
            data: {
                c_id: c_id
            },
            success: function (data) {
                var resultJson = JSON.parse(data);
                if (resultJson.type == "ACCESS") {
                    location.reload();
                } else {

                }
            }
        });
    }
});
//xoa cau hoi
$(".question-delete").click(function () {
    if (confirm('Bạn có chắc chắn muốn xóa!')) {
        $.ajax({
            url: context_path + "/qanda/question/delete",
            type: "post",
            dataType: "text",
            data: {
                q_id: question_id
            },
            success: function (data) {
                var resultJson = JSON.parse(data);
                if (resultJson.type == "ACCESS") {
                    $(location).attr('href', context_path + '/qanda');
                } else {

                }
            }
        });
    }
});

//hien thi form nhap binh luan
$('.reply-answer').click(function () {
    if (checkLogin() == false) {
        if (confirm('Bạn phải đăng nhập trước để bình luận câu trả lời!')) {
            showLoginForm();
        }
    } else {
        var id_tmp = $(this).attr("data-answer-id");
        $("#comment-answer-" + id_tmp).show();
    }
});

//them binh luan
$('.b-save-comment').click(function () {
    var id_tmp = $(this).attr("data-answer-id");
    var comment_text = $("#comment-content-" + id_tmp).val();
    if (comment_text == '') {
        alert("Chưa nhập đủ thông tin");
    } else {
        $.ajax({
            url: context_path + "/qanda/question/answer/comment/new",
            type: "post",
            dataType: "text",
            data: {
                u_id: user_id,
                a_id: id_tmp,
                c_content: comment_text
            },
            success: function (data) {
//                        $('#button_id').removeAttr('disabled');
                var resultJson = JSON.parse(data);
                if (resultJson[0].type == "ACCESS") {
                    var u_id = resultJson[1].depscription;
                    socket.emit('sendNotification', u_id, function (res) {
                        if (res == "ok") {
                            location.reload();
                        }
                    });
                } else {
                    $('#load-save').css("display", "none");
                    $('.response-content').html(resultJson.depscription);
                    $('#res-notok').css("display", "block");
                    $('.response').fadeIn(200);
                    setTimeout(function () {
                        $('.response').fadeOut(200);
                    }, 2000);
                }
            }
        });
    }

});

$('.show-answer-form').click(function () {
    if (checkLogin() == false) {
        if (confirm('Bạn phải đăng nhập trước để trả lời câu hỏi!')) {
            showLoginForm();
        }
    } else {
        $(".form-answer").css("display", "block");
        $(".comment-respond").css("display", "none");
    }
});

//luu cau tra loi
$('.b-save-answer').click(function () {
    if ($('#ask_question_body').val() == '') {
        alert("Chưa nhập đủ thông tin");
    } else {
        $.ajax({
            url: context_path + "/qanda/question/answer/new",
            type: "post",
            dataType: "text",
            data: {
                u_id: user_id,
                q_id: question_id,
                a_content: $('#ask_question_body').val(),
                a_content_raw: converter1.makeHtml($('#ask_question_body').val())
            },
            success: function (data) {
                $('#button_id').removeAttr('disabled');
                var resultJson = JSON.parse(data);
                if (resultJson[0].type == "ACCESS") {
                    var u_id = resultJson[1].depscription;
                    socket.emit('sendNotification', u_id, function (res) {
                        if (res == "ok") {
                            location.reload();
                        }
                    });

                } else {
                    $('#load-save').css("display", "none");
                    $('.response-content').html(resultJson.depscription);
                    $('#res-notok').css("display", "block");
                    $('.response').fadeIn(200);
                    setTimeout(function () {
                        $('.response').fadeOut(200);
                    }, 2000);
                }
            }
        });
    }
});
