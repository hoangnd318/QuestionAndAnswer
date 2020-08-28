<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href='<c:url value="/view/frontend/web/css/bootstrap.min.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/tag_input.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/content.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/question_add.css" />' rel='stylesheet'>
        <script src='<c:url value="/view/frontend/web/js/jquery-3.3.1.min.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/bootstrap.min.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/markdown/Markdown.Converter.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/markdown/Markdown.Editor.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/markdown/Markdown.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/markdown/Markdown.Sanitizer.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/markdown/resizer.js" />'></script>
        <link href='<c:url value="/view/frontend/web/css/qanda/wmd.css" />' rel='stylesheet'>
    </head>
    <body class="home">
        <script type="text/javascript">
            var answer_id = "${answer.id}";
        </script>
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
                                <input id="password_838" class="required-item" name="pwd_88" type="password">
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
        <div class="background-cover"></div>
        <div id="wrap" class="<c:if test = "${user_account == null}">wrap-not-login</c:if><c:if test = "${user_account != null}">wrap-login</c:if>">
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
                                            <div class="float_l">${user_account.lastname}</div>
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
                                        <li class="menu-item"><a href="<%= request.getContextPath() + "/qanda"%>">Trang chủ</a></li>
                                        <li class="menu-item"><a href="">Hướng dẫn</a></li>
                                    </ul>
                                </nav> 
                                <div class="header-search float_r">
                                    <form class="searchform" method="get" action="<%= request.getContextPath()%>/qanda/search">
                                        <input id="search-q" placeholder="Tìm kiếm" name="q" type="search" autocomplete="off"></input>
                                        <div class="search-click"></div>
                                        <button id="button-form-search" type="submit">
                                            <span class="glyphicon glyphicon-search"></span>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
            </div>   
            <div class="detail-content">
                <div class="inner-content">
                    <div class="container-content">
                        <nav class="nav-menu fixed_nav_menu" style="position: relative; overflow: visible; box-sizing: border-box;">
                            <div class="theiaStickySidebar menu-left" style="padding-top: 0px; padding-bottom: 1px; position: static;top: 30px;left: 89.5px;">
                                <ul id="menu-explore-not-login" class="menu">
                                    <li class="menu-item"><a href="<%= request.getContextPath()%>/qanda/tags"><span class="glyphicon glyphicon-tag"></span> Tags</a></li>
                                </ul>
                            </div>
                        </nav>
                        <main class="main-wrap" style="position: relative; overflow: visible; box-sizing: border-box;">
                            <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static;">
                                <div class="main-inner float_l">
                                    <div class="clearfix"></div>
                                    <h1>
                                        Sửa câu trả lời
                                    </h1>
                                    <hr>
                                    <div class="form-detail-question">
                                        <div class="title-input">
                                        </div>
                                        <br>
                                        <div class="content-input">
                                            <label>Nội dung câu trả lời<span class="required">*</span></label>
                                            <div class="wmd-panel">
                                                <div id="wmd-button-bar" ></div>
                                                <label for="answer_question_body" class="required"></label>
                                                <textarea name="q_content" class="wmd-input" id="ask_question_body">${answer.content}></textarea>
                                            </div>
                                            <div style="margin-top: 15px;">Review câu trả lời</div>
                                            <div id="wmd-preview" class="wmd-panel wmd-preview"></div>
                                        </div>
                                        <div class="question-save">
                                            <button type="button" class="b-edit-answer">Lưu câu trả lời</button>
                                            <div id="load-save" class="tag-loading">
                                                <div class="loader"></div> 
                                            </div> 
                                            <div class="response">
                                                <div class="response-content"></div>
                                                <span id="res-ok" class="glyphicon glyphicon-ok"></span>
                                                <span id="res-notok" class="glyphicon glyphicon-remove"></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="hide-main-inner"></div>
                                <div class="hide-sidebar">
                                    <div class="hide-sidebar-inner"></div>
                                </div>
                                <aside class="sidebar float_l" style="position: relative; overflow: visible; box-sizing: border-box;">
                                    <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static;">
                                        <div class="inner-sidebar">
                                        </div>
                                    </div>
                                </aside>
                            </div>
                        </main>
                    </div>
                </div>
            </div> 
        </div>
        <div class="go-up">
            <span class="glyphicon glyphicon-menu-up"></span>
        </div>
        <script src='<c:url value="/view/frontend/web/js/socket.io/socket.io.js" />'></script>  
        <script src='<c:url value="/view/frontend/web/js/qanda/send_notification.js" />'></script> 
        <script src='<c:url value="/view/frontend/web/js/qanda/var_general.js" />'></script>                                        
        <script src='<c:url value="/view/frontend/web/js/qanda/general.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/check_login.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/notifications.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/login.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/edit_answer.js" />'></script>
        <script type="text/javascript">
            var converter1 = Markdown.getSanitizingConverter();
        </script>
    </body>
</html>
