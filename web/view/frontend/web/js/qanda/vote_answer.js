$('.answer-vote-up').click(function () {
    var a_id = $(this).parent().children()[0].value;
    var check = $(this).hasClass('a_v');
    var checkVoted = $(this).hasClass('voted');
    if (checkLogin() == false) {
        if (confirm('Bạn phải đăng nhập trước khi bình chọn!')) {
            showLoginForm();
        }
    } else {
        if (checkVoted == true) {
            $.ajax({
                url: context_path + "/qanda/question/answer/vote",
                type: "post",
                dataType: "text",
                data: {
                    type: 'un_vote',
                    a_id: a_id,
                    u_id: user_id
                },
                success: function (result) {
                    var resultJson = JSON.parse(result);
                    if (resultJson.type == "ACCESS") {
                        location.reload();
                    }
                }
            });
        } else {
            if (check == true) {
                $.ajax({
                    url: context_path + "/qanda/question/answer/vote",
                    type: "post",
                    dataType: "text",
                    data: {
                        type: 'change_vote_up',
                        a_id: a_id,
                        u_id: user_id
                    },
                    success: function (result) {
                        var resultJson = JSON.parse(result);
                        if (resultJson.type == "ACCESS") {
                            location.reload();
                        }
                    }
                });
            } else {
                $.ajax({
                    url: context_path + "/qanda/question/answer/vote",
                    type: "post",
                    dataType: "text",
                    data: {
                        type: 'vote_up',
                        a_id: a_id,
                        u_id: user_id
                    },
                    success: function (result) {
                        var resultJson = JSON.parse(result);
                        if (resultJson.type == "ACCESS") {
                            location.reload();
                        }
                    }
                });
            }
        }
    }
});

$('.answer-vote-down').click(function () {
    var a_id = $(this).parent().children()[0].value;
    var check = $(this).hasClass('a_v');
    var checkVoted = $(this).hasClass('voted');
    if (checkLogin() == false) {
        if (confirm('Bạn phải đăng nhập trước khi bình chọn!')) {
            showLoginForm();
        }
    } else {
        if (checkVoted == true) {
            $.ajax({
                url: context_path + "/qanda/question/answer/vote",
                type: "post",
                dataType: "text",
                data: {
                    type: 'un_vote',
                    a_id: a_id,
                    u_id: user_id
                },
                success: function (result) {
                    var resultJson = JSON.parse(result);
                    if (resultJson.type == "ACCESS") {
                        location.reload();
                    }
                }
            });
        } else {
            if (check == true) {
                $.ajax({
                    url: context_path + "/qanda/question/answer/vote",
                    type: "post",
                    dataType: "text",
                    data: {
                        type: 'change_vote_down',
                        a_id: a_id,
                        u_id: user_id
                    },
                    success: function (result) {
                        var resultJson = JSON.parse(result);
                        if (resultJson.type == "ACCESS") {
                            location.reload();
                        }
                    }
                });
            } else {
                $.ajax({
                    url: context_path + "/qanda/question/answer/vote",
                    type: "post",
                    dataType: "text",
                    data: {
                        type: 'vote_down',
                        a_id: a_id,
                        u_id: user_id
                    },
                    success: function (result) {
                        var resultJson = JSON.parse(result);
                        if (resultJson.type == "ACCESS") {
                            location.reload();
                        }
                    }
                });
            }
        }
    }
});