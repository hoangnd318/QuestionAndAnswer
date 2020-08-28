function getCountNotifi() {
    if (checkLogin() == true) {
        $.ajax({
            url: context_path + "/qanda/getCountNotifications",
            type: 'post',
            dataType: 'text',
            data: {

            },
            success: function (data) {
                var resultJson = JSON.parse(data);
                if (resultJson.depscription != "0") {
                    $('.notifications-number').html(resultJson.depscription);
                } else {
                    $('.notifications-number').hide();
                }
            }
        });
    }
}
getCountNotifi();
$('.notifications-click').click(function () {
    $.ajax({
        url: context_path + "/qanda/getNotifications",
        type: 'post',
        dataType: 'text',
        data: {

        },
        success: function (data) {
            var contextPath = context_path;
            var resultJson = JSON.parse(data);
            var li = "";
            for (var i = 0; i < resultJson.length; i++) {
                li += '<li id="'
                        + resultJson[i].id
                        + '" class="'
                        + resultJson[i].status
                        + '"><a href="'
                        + contextPath
                        + resultJson[i].url
                        + '&refe_noti='
                        + resultJson[i].id
                        + '"><div>'
                        + resultJson[i].content
                        + '<span class="notifications-date">'
                        + resultJson[i].convertTime
                        + '</span></div></a></li>';
            }
            $('.notifications-list').empty();
            $('.notifications-list').append(li);
        }
    });


    $(".user-login-click").removeClass("user-click-open").find(" > ul").slideUp(200);
    $(this).parent().toggleClass("user-notifications-seen").find(" > div").slideToggle(200).parent().find(" > .notifications-number").hide();
});

var is_busy = false;
var stopped = false;
$('.notifications-list').on('scroll', function () {
    if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
        if (is_busy == true) {
            return false;
        }
        if (stopped == true) {
            return false;
        }
        is_busy = true;
        var n_id = $('.notifications-list li:last-child')[0].id;
        $('.notifications-list').append('<li><span class="load_span" style="display: block;"><span class="loader_2"></span></span></li>');
        $.ajax({
            url: context_path + "/qanda/getNotifyNext",
            type: 'post',
            dataType: 'text',
            data: {
                n_id: n_id
            },
            success: function (result) {
                console.log(result);
                if (result != '[]') {
                    setTimeout(function () {
                        $('.notifications-list li:last').remove();
                        var resultJson = new Array();
                        resultJson = JSON.parse(result);
                        var li = "";
                        for (var i = 0; i < resultJson.length; i++) {
                            li += '<li id="'
                                    + resultJson[i].id
                                    + '" class="'
                                    + resultJson[i].status
                                    + '"><a href="'
                                    + context_path
                                    + resultJson[i].url
                                    + '&refe_noti='
                                    + resultJson[i].id
                                    + '"><div>'
                                    + resultJson[i].content
                                    + '<span class="notifications-date">'
                                    + resultJson[i].convertTime
                                    + '</span></div></a></li>';
                        }
                        $('.notifications-list').append(li);
                        is_busy = false;
                    }, 1000);
                } else {
                    $('.notifications-list li:last').remove();
                }
            }
        });
    }
});
