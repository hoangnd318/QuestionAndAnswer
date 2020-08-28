$(window).scroll(function () {
    var top = window.pageYOffset;
    if (top >= $(".nav-menu").offset().top) {
        $(".menu-left").css("position", "fixed");
        $(".go-up").show();
    } else {
        $(".menu-left").css("position", "static");
        $(".go-up").hide();
    }
});
$(".go-up").click(function () {
    $("html,body").animate({
        scrollTop: 0
    }, 500);
    return false;
});
$(".detail-content").height($(".main-inner").height() + 58);

//$(".notifications-click").click(function () {
//    $(".user-login-click").removeClass("user-click-open").find(" > ul").slideUp(200);
//    $(this).parent().toggleClass("user-notifications-seen").find(" > div").slideToggle(200).parent().find(" > .notifications-number").remove();
//});

$(".user-click").click(function () {
    $(".user-notifications").find(" > div").slideUp(200);
    $(this).parent().toggleClass("user-click-open").find(" > ul").slideToggle(200);
});

$("#on-search-text").click(function () {
    $(".tag_form").show(500);
    $(".tag_input_press").focus();
    $("#on-search-text").css("display", "none");
    $("#off-search-text").css("display", "block");
    $(".tag_input_result").css("display", "none");
});

$("#off-search-text").click(function () {
    $(".tag_form").hide(500);
    $("#off-search-text").css("display", "none");
    $("#on-search-text").css("display", "block");
});

$(".button-sign-in").click(function () {
    $(".required-error").html("");
    $(".wpqa_error").hide();
    $(".wrap-pop").css("display", "block");
    $(".panel-pop").show(250);
});
$(".wrap-pop").click(function () {
    $(".panel-pop").hide(250);
    $(".wrap-pop").css("display", "none");

});
function showLoginForm() {
    $(".required-error").html("");
    $(".wpqa_error").hide();
    $(".wrap-pop").css("display", "block");
    $(".panel-pop").show(250);
}


