<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Forum</title>
        <link href='<c:url value="/view/frontend/web/css/bootstrap.min.css" />' rel='stylesheet'>
        <link rel="stylesheet" href='<c:url value="/view/frontend/web/css/home/general.css" />'>
        <!--<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
        <script src='<c:url value="/view/frontend/web/js/jquery-3.3.1.min.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/bootstrap.min.js" />'></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <script type="text/javascript">
            var user_id = "${user_account.idUser}";
        </script>
        <div class="wrap-pop"></div>
        <div id="login-panel" class="panel-pop">
            <div class="pop-border-radius">
                <div class="pop-header">
                    <h3>Đăng nhập</h3>
                </div>
                <div class="panel-pop-content">
                    <form class="wpqa_form" action="" method="">
                        <div class="wpqa_error" style="display: none;">
                            <span class="required-error"></span>
                        </div>
                        <div class="form-inputs">
                            <p class="login-text">
                                <label for="username_838">Username<span class="required">*</span></label>
                                <input id="username_838" class="required-item" name="username_88" type="text">
                            </p>
                            <p class="login-password">
                                <label for="password_838">Password<span class="required">*</span></label>
                                <input id="password_838" class="required-item" name="pwd_88" type="password" autocomplete="off">
                            </p>
                            <p class="form-submit">
                                <span class="load_span" style="display: none;">
                                    <span class="loader_2"></span>
                                </span>
                                <input value="Login" class="button-default login-submit" type="button">
                            </p>
                        </div>
                    </form>
                </div>
            </div>
            <div class="pop-footer"></div>
        </div>

        <div class="hidden-header">
            <header class="header">
                <div class="details-container">
                    <div class="right-header float_r">
                        <c:if test="${user_account == null}">
                            <a class="button-default button-sign-in" href="javascript:">Đăng nhập</a>
                            <a class="button-default-2 button-sign-up" href="<%= request.getContextPath()%>/account/register">Đăng ký</a>
                        </c:if>
                        <c:if test="${user_account != null}">
                            <div class="user-login-area">
                                <div class="user-login-click float_r">
                                    <span class="user-click"></span>
                                    <div class="user-image float_l">
                                        <img class="avatar avatar-29 photo" alt="" src="<c:url value="${user_account.account.avatar.url}" />" width="29" height="29">
                                    </div>
                                    <div class="user-login float_l" style="margin-top: 22px;">
                                        <span>Chào mừng</span>
                                        <br>
                                        <div class="float_l">${user_account.firstname} ${user_account.lastname}</div>
                                    </div>
                                    <span class="glyphicon glyphicon-chevron-down open-mini"></span>
                                    <ul style="display: none">
                                        <li><a href="<%= request.getContextPath() + "/qanda/user/profile/question"%>"><span class="glyphicon glyphicon-user"></span> Thông tin tài khoản</a></li>
                                        <li><a href="<%= request.getContextPath() + "/account/edit"%>"><span class="glyphicon glyphicon-cog"></span> Chỉnh sửa thông tin tài khoản</a></li>
                                        <li><a href="<%= request.getContextPath() + "/qanda/logout"%>"><span class="glyphicon glyphicon-log-out"></span> Đăng xuất</a></li>
                                    </ul>
                                </div>
                                <div class="user-notifications float_r">
                                    <span class="notifications-click"></span>
                                    <span class="glyphicon glyphicon-bell bell-count"></span>
                                    <span class="notifications-number"></span>
                                    <div style="display: none" class="notifications-detail">
                                        <ul class="notifications-list">

                                            <span class="load_span" style="display: block;">
                                                <span class="loader_2"></span>
                                            </span>

                                        </ul>

                                        <!--                                            <a href="#">Xem tất cả thông báo</a>-->
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="left-header float_l">
                        <div class="mid-header">
                            <nav class="nav float_l">
                                <ul id="menu-header" class="menu">
                                    <li class="menu-item"><a href="<%= request.getContextPath()%>">Trang chủ</a></li>
                                    <li class="menu-item"><a href="javascript:void(0)" class="button-tk-nhom">Tìm kiếm nhóm môn học</a></li>
                                    <li class="menu-item"><a href="<%= request.getContextPath() + "/qanda"%>">Hỏi và đáp</a></li>
                                        <c:if test="${user_account.type == 'GV'}">
                                        <li class="menu-item"><a href="<%= request.getContextPath() + "/groups/create"%>">Thêm nhóm môn học</a></li>
                                        </c:if>
                                </ul>
                            </nav> 
                        </div>
                    </div>
                </div>                
            </header>
        </div>
        <div class="khung-tim-kiem-nhom">
            <form class="searchform" method="get" action="<%= request.getContextPath()%>/groups/search">
                <div class="o-nhap"><input type="search" placeholder="Nhập tên nhóm..." name="key"></div>
                <div class="nut-tk"><button type="submit">Tìm kiếm</button></div>
            </form>
        </div>                        
        <div class="container">
            <div class="col-full">
                <div class="forum-list">

                    <h2 class="list-title">
                        <a href="<%= request.getContextPath() %>/groups/list">Nhóm môn học</a>
                    </h2>
                    <c:if test="${group_private.size() <= 0 || group_private == null}">
                        <div style="text-align: center; height: 40px;"><p>Bạn chưa theo dõi nhóm môn học nào</p></div>
                    </c:if>
                    <c:if test="${group_private.size() > 0}">
                        <c:forEach var="entry" items="${group_private}">
                            <div class="forum-listing">
                                <div class="forum-details">
                                    <a class="text-xlarge" href="<%= request.getContextPath()%>/groups/${entry.id}">${entry.name}</a>
                                    <p>${entry.description}</p>
                                </div>

                                <!--                                <div class="threads-count">
                                                                    <p><span class="count">1</span> thread</p>
                                                                </div>-->

                                <div class="last-thread">
                                    <c:if test="${entry.followedByUser == 'true'}"><button data-g-id="${entry.id}" class="group-follow followed">Đã theo dõi</button></c:if>
                                    <c:if test="${entry.followedByUser == 'false'}"><button data-g-id="${entry.id}" class="group-follow unfollow">Theo dõi</button></c:if>
                                    </div>
                                </div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="forum-list">
                    <h2 class="list-title">
                        <a href="javascript:">Trao đổi</a>
                    </h2>
                    <c:forEach var="entry" items="${group_default}">
                        <div class="forum-listing">
                            <div class="forum-details">
                                <a class="text-xlarge" href="<%= request.getContextPath()%>/groups/${entry.id}">${entry.name}</a>
                                <p>${entry.description}</p>
                            </div>
                            <!--                                                    <div class="threads-count">
                                                                                    <p class="count">3</p> threads
                                                                                </div>
                                                                                <div class="last-thread">
                                                                                    <img class="avatar" src="https://i.imgur.com/aaw5m0T.jpg" alt="">
                                                                                    <div class="last-thread-details">
                                                                                        <a href="thread.html">Knock knock, who's there?</a>
                                                                                        <p class="text-xsmall">By <a href="profile.html">Princess Dee Dee</a>, 23 hours ago</p>
                                                                                    </div>
                                                                                </div>-->
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <script type=text/javascript>
            $(".button-tk-nhom").click(function () {
                $(".khung-tim-kiem-nhom").css("display", "block");
            });
        </script>
        <script type=text/javascript>
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
            $(".login-submit").click(function () {
                $(".required-error").html("");
                $(".wpqa_error").hide();
                $(this).css("display", "none");
                $(".load_span").css("display", "block");
                $.ajax({
                    url: '<%= request.getContextPath()%>' + '/qanda/checkLogin',
                    type: "post",
                    dataType: "text",
                    data: {
                        username: $("input[name='username_88']").val(),
                        password: $("input[name='pwd_88']").val()
                    },
                    success: function (result) {
                        var resultJson = JSON.parse(result);
                        if (resultJson[0].type == "ACCESS") {
//                            var socket = io('192.168.1.4:8000');
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
            $(".user-click").click(function () {
                $(".user-notifications").find(" > div").slideUp(200);
                $(this).parent().toggleClass("user-click-open").find(" > ul").slideToggle(200);
            });
        </script>
        <script src='<c:url value="/view/frontend/web/js/socket.io/socket.io.js" />'></script>  
        <script src='<c:url value="/view/frontend/web/js/qanda/send_notification.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/var_general.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/check_login.js" />'></script>   
        <script src='<c:url value="/view/frontend/web/js/qanda/notifications.js" />'></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $(".unfollow").click(function () {
                    var flag = checkLogin();
                    if (flag == false) {
                        if (confirm('Bạn phải đăng nhập để theo dõi nhóm!')) {
                            $(".required-error").html("");
                            $(".wpqa_error").hide();
                            $(".wrap-pop").css("display", "block");
                            $(".panel-pop").show(250);
                        }
                    } else {
                        var g_id = $(this).attr("data-g-id");
                        $.ajax({
                            url: "<%=request.getContextPath()%>/groups/addfollow",
                            type: "post",
                            dataType: "text",
                            data: {
                                g_id: g_id
                            },
                            success: function (data) {
                                var resultJson = JSON.parse(data);
                                if (resultJson.type == "ACCESS") {
                                    location.reload();
                                } else {
                                    alert(resultJson.depscription);
                                }
                            }
                        });
                    }
                });

                $(".followed").click(function () {
                    if (confirm('Bạn có chắc chắn muốn bỏ theo dõi nhóm!')) {
                        var g_id = $(this).attr("data-g-id");
                        $.ajax({
                            url: "<%=request.getContextPath()%>/groups/unfollow",
                            type: "post",
                            dataType: "text",
                            data: {
                                g_id: g_id
                            },
                            success: function (data) {
                                var resultJson = JSON.parse(data);
                                if (resultJson.type == "ACCESS") {
                                    location.reload();
                                } else {
                                    alert(resultJson.depscription);
                                }
                            }
                        });
                    }
                });
            });
        </script>
    </body>
</html>
