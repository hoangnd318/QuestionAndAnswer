$('.b-edit-answer').click(function () {
    if ($('#ask_question_body').val() == '') {
        alert("Chưa nhập đủ thông tin");
    } else {
        $.ajax({
            url: context_path + "/qanda/question/answer/edit/save",
            type: "post",
            dataType: "text",
            data: {
                a_id: answer_id,
                a_content: $('#ask_question_body').val(),
                a_content_raw: converter1.makeHtml($('#ask_question_body').val())
            },
            success: function (data) {
                $('#button_id').removeAttr('disabled');
                var resultJson = JSON.parse(data);
                if (resultJson.type == "ACCESS") {
                    $('#load-save').css("display", "none");
                    $('.response-content').html(resultJson.depscription);
                    $('#res-ok').css("display", "block");
                    $('.response').fadeIn(200);
                    setTimeout(function () {
                        $('.response').fadeOut(200);
                    }, 2000);
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