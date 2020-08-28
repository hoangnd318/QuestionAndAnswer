<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký tài khoản</title>
        <link href='<c:url value="/view/frontend/web/css/bootstrap.min.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/home/account/register_account.css" />' rel='stylesheet'>
        <link rel="stylesheet" href='<c:url value="/view/frontend/web/css/home/general.css" />'>
        <script src='<c:url value="/view/frontend/web/js/jquery-3.3.1.min.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/bootstrap.min.js" />'></script>
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
            <div class="row">
                <div class="col-md-10">
                    <section>
                        <c:if test="${user_account == null}">
                            <h1 class="entry-title"><span>Đăng ký tài khoản</span> </h1>
                        </c:if>
                        <c:if test="${user_account != null}">
                            <h1 class="entry-title"><span>Chỉnh sửa tài khoản</span> </h1>
                        </c:if>
                        <hr>
                        <form class="form-horizontal" method="post" name="signup" id="signup" enctype="multipart/form-data" >        
                            <div class="form-group">
                                <label class="control-label col-sm-3">Email <span class="text-danger">*</span></label>
                                <div class="col-md-8 col-sm-9">
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                        <input type="text" class="form-control" name="u_email" id="u_email" placeholder="Nhập vào Email" value="${user_account.account.email}">
                                    </div>
                                    <small></small> </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Tên tài khoản<span class="text-danger">*</span></label>
                                <div class="col-md-8 col-sm-9">
                                    <input <c:if test="${user_account != null}">disabled=""</c:if> type="text" class="form-control" name="u_username" id="u_username" placeholder="Nhập vào tên tài khoản" value="${user_account.account.username}">
                                    </div>
                                </div>
                            <c:if test="${user_account == null}">
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Mật khẩu <span class="text-danger">*</span></label>
                                    <div class="col-md-5 col-sm-8">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                            <input type="password" class="form-control" name="u_password" id="u_password" placeholder="Nhập vào mật khẩu" value="">
                                        </div>   
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Xác nhận mật khẩu <span class="text-danger">*</span></label>
                                    <div class="col-md-5 col-sm-8">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                            <input type="password" class="form-control" name="c_u_password" id="c_u_password" placeholder="Xác nhận mật khẩu" value="">
                                        </div>  
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Họ và tên <span class="text-danger">*</span></label>
                                <div class="col-md-2">
                                    <input type="text" class="form-control" name="u_firstname" id="u_firstname" placeholder="Họ" value="${user_account.firstname}">
                                </div>
                                <div class="col-md-2">
                                    <input type="text" class="form-control" name="u_midname" id="u_midname" placeholder="Tên đệm" value="${user_account.midname}">
                                </div>
                                <div class="col-md-2">
                                    <input type="text" class="form-control" name="u_lastname" id="u_lastname" placeholder="Tên" value="${user_account.lastname}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Ngày sinh <span class="text-danger">*</span></label>
                                <div class="col-xs-6">
                                    <div class="form-inline">
                                        <div class="form-group">
                                            <label>Chọn ngày: </label>
                                            <select id="u_b_d" name="u_b_d" class="form-control">
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Chọn tháng: </label>
                                            <select id="u_b_m" name="u_b_m" class="form-control">              
                                            </select>
                                        </div>
                                        <div class="form-group" >
                                            <label>Chọn năm: </label>
                                            <select id="u_b_y" name="u_b_y" class="form-control">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3">Giới tính <span class="text-danger">*</span></label>
                                <div class="col-md-8 col-sm-9">
                                    <label>
                                        <input name="u_gender" type="radio" value="Nam" <c:if test="${user_account.gender == 'Nam'}">checked</c:if>>
                                            Nam </label>
                                           
                                        <label>
                                            <input name="u_gender" type="radio" value="Nu" <c:if test="${user_account.gender == 'Nu'}">checked</c:if>>
                                            Nữ </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Địa chỉ <br>
                                    </label>
                                    <div class="col-md-5 col-sm-8">
                                        <input type="text" class="form-control" name="u_address" id="u_address" placeholder="Nhập vào địa chỉ" value="${user_account.address}">
                                </div>
                            </div>
                            <c:if test="${user_account.type == 'SV' || user_account == null}">
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Mã sinh viên<br>
                                    </label>
                                    <div class="col-md-5 col-sm-8">
                                        <input type="text" class="form-control" name="u_studentcode" id="u_studentcode" placeholder="Nhập vào địa chỉ" value="${user_account.studentCode}">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Ngành học<br>
                                    </label>
                                    <div class="col-md-5 col-sm-8">
                                        <select id="u_department" name="u_department" class="form-control">
                                            <option value="">-- Chọn ngành --</option>
                                            <c:forEach var="entry" items="${nganh}">
                                                <option value="${entry.id}" <c:if test="${user_account.department.id == entry.id}">selected=""</c:if>>${entry.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Khóa<br>
                                    </label>
                                    <div class="col-md-5 col-sm-8">
                                        <select id="u_course" name="u_course" class="form-control">
                                            <option value="">-- Chọn khóa --</option>
                                            <c:forEach var="entry" items="${khoa}">
                                                <option value="${entry.id}" <c:if test="${user_account.course.id == entry.id}">selected=""</c:if> >${entry.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <div class="col-xs-offset-3 col-xs-10">
                                    <c:if test="${user_account == null}">
                                        <input name="register-account" type="button" value="Đăng ký" class="btn btn-primary">
                                    </c:if>
                                    <c:if test="${user_account != null}">
                                        <input name="edit-account" type="button" value="Lưu" class="btn btn-primary">
                                    </c:if>
                                </div>
                            </div>
                        </form>
                    </section>
                </div>
            </div>
            <c:if test="${user_account != null }">
                <div class="row">
                    <div class="col-md-10">
                        <section>
                            <h1 class="entry-title"><span>Đổi mật khẩu</span> </h1>
                            <hr>
                            <form class="form-horizontal" method="post" name="signup" id="signup" enctype="multipart/form-data" >        
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Mật khẩu cũ <span class="text-danger">*</span></label>
                                    <div class="col-md-8 col-sm-8">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                            <input autocomplete="off" type="password" class="form-control" name="u_password_e_o" id="u_password_e_o" placeholder="Nhập vào mật khẩu cũ" value="">
                                        </div>   
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">Mật khẩu mới<span class="text-danger">*</span></label>
                                    <div class="col-md-8 col-sm-8">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                            <input autocomplete="off" type="password" class="form-control" name="u_password_e_n" id="u_password_e_n" placeholder="Nhập vào mật khẩu mới" value="">
                                        </div>  
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-offset-3 col-xs-10">
                                        <input name="update-password" type="button" value="Lưu" class="btn btn-primary">
                                    </div>
                                </div>
                            </form>
                        </section>
                    </div>
                </div>
            </c:if>
        </div>


        <script src='<c:url value="/view/frontend/web/js/socket.io/socket.io.js" />'></script>  
        <script src='<c:url value="/view/frontend/web/js/qanda/send_notification.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/var_general.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/check_login.js" />'></script>   
        <script src='<c:url value="/view/frontend/web/js/qanda/notifications.js" />'></script>
        <script type="text/javascript">
            //tao moi
            $('input[name="register-account"]').click(function () {
                var email = $('#u_email').val();
                var username = $('#u_username').val();
                var password = $('#u_password').val();
                var re_password = $('#c_u_password').val();
                var firstname = $('#u_firstname').val();
                var midname = $('#u_midname').val();
                var lastname = $('#u_lastname').val();
                var b_d = $('#u_b_d').val();
                var b_m = $('#u_b_m').val();
                var b_y = $('#u_b_y').val();
                var gender = $('input[name="u_gender"]:checked').val();
                var address = $('#u_address').val();
                var studen_code = $('#u_studentcode').val();
                var department = $('#u_department').val();
                var course = $('#u_course').val();
                if (course == '' || department == '' || re_password == '' || password == '' || gender == null || email == '' || username == '' || firstname == '' || midname == '' || lastname == '' || address == '' || studen_code == '') {
                    alert("Chưa nhập đủ thông tin");
                } else if (re_password != password) {
                    alert("Mật khẩu xác nhận không đúng");
                } else {
                    $.ajax({
                        url: "<%=request.getContextPath()%>/qanda/account/checkEmail",
                        type: "post",
                        dataType: "text",
                        data: {
                            email: email,
                            username: username
                        },
                        success: function (result) {
                            var resultJson = JSON.parse(result);
                            if (resultJson.type == "ACCESS") {
                                $.ajax({
                                    url: "<%=request.getContextPath()%>/qanda/register/save",
                                    type: "post",
                                    dataType: "text",
                                    data: {
                                        email: email,
                                        username: username,
                                        password: password,
                                        firstname: firstname,
                                        midname: midname,
                                        lastname: lastname,
                                        b_d: b_d,
                                        b_m: b_m,
                                        b_y: b_y,
                                        gender: gender,
                                        address: address,
                                        studen_code: studen_code,
                                        department: department,
                                        course: course
                                    },
                                    success: function (result) {
                                        var resultJson = JSON.parse(result);
                                        if (resultJson.type == "ACCESS") {
                                            window.location.replace("<%=request.getContextPath()%>/account/edit");
                                        }
                                    }
                                });
                            } else {
                                alert(resultJson.depscription);
                            }
                        }
                    });
                }
            });
            //chinh sua
            $('input[name="edit-account"]').click(function () {
                var email = $('#u_email').val();
                var firstname = $('#u_firstname').val();
                var midname = $('#u_midname').val();
                var lastname = $('#u_lastname').val();
                var b_d = $('#u_b_d').val();
                var b_m = $('#u_b_m').val();
                var b_y = $('#u_b_y').val();
                var gender = $('input[name="u_gender"]:checked').val();
                var address = $('#u_address').val();
                var studen_code = $('#u_studentcode').val();
                var department = $('#u_department').val();
                var course = $('#u_course').val();
                if (course == '' || department == '' || gender == null || email == '' || firstname == '' || midname == '' || lastname == '' || address == '' || studen_code == '') {
                    alert("Chưa nhập đủ thông tin");
                } else {
                    $.ajax({
                        url: "<%=request.getContextPath()%>/qanda/account/checkEmail",
                        type: "post",
                        dataType: "text",
                        data: {
                            email: email
                        },
                        success: function (result) {
                            var resultJson = JSON.parse(result);
                            if (resultJson.type == "ACCESS") {
                                $.ajax({
                                    url: "<%=request.getContextPath()%>/qanda/register/edit/save",
                                    type: "post",
                                    dataType: "text",
                                    data: {
                                        email: email,
                                        firstname: firstname,
                                        midname: midname,
                                        lastname: lastname,
                                        b_d: b_d,
                                        b_m: b_m,
                                        b_y: b_y,
                                        gender: gender,
                                        address: address,
                                        studen_code: studen_code,
                                        department: department,
                                        course: course
                                    },
                                    success: function (result) {
                                        var resultJson = JSON.parse(result);
                                        if (resultJson.type == "ACCESS") {
                                            alert("Chỉnh sửa thông tin thành công");
                                            window.location.replace("<%=request.getContextPath()%>/account/edit");
                                        }
                                    }
                                });
                            } else {
                                alert(resultJson.depscription);
                            }
                        }
                    });
                }
            });
            //doi mat khau
            $('input[name="update-password"]').click(function () {
                var password = $('#u_password_e_n').val();
                var password_old = '${user_account.account.password}';
                var password_old_c = $('#u_password_e_o').val();
                if (password_old_c != password_old) {
                    alert("Mật khẩu cũ nhập vào không đúng!");
                } else if (password == '' || password_old_c == '') {
                    alert("Chưa nhập đủ thông tin");
                } else {
                    $.ajax({
                        url: "<%=request.getContextPath()%>/qanda/account/password/edit",
                        type: "post",
                        dataType: "text",
                        data: {
                            password: password
                        },
                        success: function (result) {
                            var resultJson = JSON.parse(result);
                            if (resultJson.type == "ACCESS") {
                                alert("Đổi mật khẩu thành công");
                                window.location.replace("<%=request.getContextPath()%>/account/edit");
                            }
                        }
                    });
                }
            });
        </script>
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
        <script type="text/javascript">
            $(function () {

                for (i = new Date().getFullYear(); i > 1900; i--) {
                    $('#u_b_y').append($('<option />').val(i).html(i));
                }

                for (i = 1; i < 13; i++) {
                    $('#u_b_m').append($('<option />').val(i).html(i));
                }
                updateNumberOfDays();
                $('#u_b_y, #u_b_m').change(function () {

                    updateNumberOfDays();
                });
            });
            function updateNumberOfDays() {
                $('#u_b_d').html('');
                month = $('#u_b_m').val();
                year = $('#u_b_y').val();
                days = daysInMonth(month, year);
                for (i = 1; i < days + 1; i++) {
                    $('#u_b_d').append($('<option />').val(i).html(i));
                }

            }

            function daysInMonth(month, year) {
                return new Date(year, month, 0).getDate();
            }
        </script>
        <script type="text/javascript">
            var dateStr = '${user_account.birthday}';
            var date = new Date(dateStr);
            var day = date.getDate();
            var month = date.getMonth() + 1;
            var year = date.getFullYear();
            var change_year = '#u_b_y option[value=' + year + ']'
            var change_month = '#u_b_m option[value=' + month + ']'
            var change_day = '#u_b_d option[value=' + day + ']'
            var interval_obj = setInterval(function () {
                $(change_year).attr('selected', 'selected');
                $(change_month).attr('selected', 'selected');
                $(change_day).attr('selected', 'selected');
                if ($('#u_b_d').val() != null) {
                    clearInterval(interval_obj);
                }
            },
                    10);

        </script>
    </body>
</html>
