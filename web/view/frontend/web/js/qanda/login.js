$(".login-submit").click(function () {
    $(".required-error").html("");
    $(".wpqa_error").hide();
    $(this).css("display", "none");
    $(".load_span").css("display", "block");
    $.ajax({
        url: context_path + "/qanda/checkLogin",
        type: "post",
        dataType: "text",
        data: {
            username: $("input[name='username_88']").val(),
            password: $("input[name='pwd_88']").val()
        },
        success: function (result) {
            var resultJson = JSON.parse(result);
            if (resultJson[0].type == "ACCESS") {
                var socket = io('192.168.1.4:8000');
                location.reload();
            } else if (resultJson[0].type == "ERROR") {
                $(".load_span").css("display", "none");
                $(".login-submit").css("display", "block");
                $(".required-error").html(resultJson[0].depscription);
                $(".wpqa_error").slideDown(750);
                setTimeout(function () {
                    $(".wpqa_error").slideUp()(300);
                }, 2000);
            }
        }
    });
});