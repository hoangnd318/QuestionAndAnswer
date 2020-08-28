<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href='<c:url value="/view/frontend/web/css/bootstrap.min.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/tag_input.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/tags_index.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/content.css" />' rel='stylesheet'>
        <script src='<c:url value="/view/frontend/web/js/jquery-3.3.1.min.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/bootstrap.min.js" />'></script>
    </head>
    <body class="home">
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
                                    <div class="row">
                                        <div class="col col12">
                                            <div class="wrap-tabs">
                                                <div class="menu-tabs active-menu">
                                                    <ul class="menu flex">
                                                        <li class="<c:if test="${type_sort == 'noi-bat'}">active-tab</c:if>"><a href="<%= request.getContextPath() + "/qanda/tags?sort=noi-bat"%>">Phổ biến</a></li>
                                                        <li class="<c:if test="${type_sort == 'theo-ten'}">active-tab</c:if>"><a href="<%= request.getContextPath() + "/qanda/tags?sort=theo-ten"%>">Tên</a></li>
                                                        <li class="<c:if test="${type_sort == 'moi-nhat'}">active-tab</c:if>"><a href="<%= request.getContextPath() + "/qanda/tags?sort=moi-nhat"%>">Mới nhất</a></li>    
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <section>
                                            <article class="article-post">
                                                <div class="post-wrap-content">
                                                    <div class="tagcloud row">
                                                    <c:forEach var="data" items="${tags}">
                                                        <div class="col col4">
                                                            <div class="tag-sections">
                                                                <div class="tag-counter">
                                                                    <a href="<%= request.getContextPath()%>/qanda/question/search/tag/${data.name}">${data.name}</a>
                                                                    <c:if test="${type_sort == 'noi-bat' || type_sort == 'theo-ten'}">
                                                                        <span> x ${data.total}</span>
                                                                    </c:if>
                                                                    <c:if test="${type_sort == 'moi-nhat'}">
                                                                        <span> tạo lúc ${data.convertCreateTime}</span>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="pagination-wrap">
                                                <div class="main-pagination">
                                                    <div class="pagination">
                                                        <c:if test="${page_total > 1}">    
                                                            <c:if test="${tags.size() > 0}">
                                                                <c:if test="${page_current != 1}">
                                                                    <a class="prev page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=${page_current-1}">
                                                                        <span class="glyphicon glyphicon-chevron-left"></span>
                                                                    </a>
                                                                </c:if>
                                                                <c:if test="${page_total <= 5}">
                                                                    <c:forEach var="i" begin="1" end="${page_total}">
                                                                        <c:if test="${i != page_current}">
                                                                            <a class="page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=${i}">${i}</a>
                                                                        </c:if>
                                                                        <c:if test="${i == page_current}">
                                                                            <span aria-current="page" class="page-numbers current">${i}</span>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </c:if>
                                                                <c:if test="${page_total > 5}">
                                                                    <c:if test="${page_current <= 3}">
                                                                        <c:forEach var="i" begin="1" end="4">
                                                                            <c:if test="${i != page_current}">
                                                                                <a class="page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=${i}">${i}</a>
                                                                            </c:if>
                                                                            <c:if test="${i == page_current}">
                                                                                <span aria-current="page" class="page-numbers current">${i}</span>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                        <span class="page-numbers dots">…</span>  
                                                                        <a class="page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=${page_total}">${page_total}</a>
                                                                    </c:if>
                                                                    <c:if test="${page_current > 3}">  
                                                                        <c:if test="${page_current >= (page_total - 3)}">
                                                                            <a class="page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=1">1</a>
                                                                            <span class="page-numbers dots">…</span>
                                                                            <c:forEach var="i" begin="${(page_total - 3)}" end="${page_total}">
                                                                                <c:if test="${i != page_current}">
                                                                                    <a class="page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=${i}">${i}</a>
                                                                                </c:if>
                                                                                <c:if test="${i == page_current}">
                                                                                    <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                        <c:if test="${page_current < (page_total - 3)}">
                                                                            <a class="page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=1">1</a>
                                                                            <span class="page-numbers dots">…</span>
                                                                            <c:forEach var="i" begin="${(page_current - 1)}" end="${(page_current + 2)}">
                                                                                <c:if test="${i != page_current}">
                                                                                    <a class="page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=${i}">${i}</a>
                                                                                </c:if>
                                                                                <c:if test="${i == page_current}">
                                                                                    <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                            <span class="page-numbers dots">…</span>  
                                                                            <a class="page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=${page_total}">${page_total}</a>
                                                                        </c:if>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${page_current != page_total}">
                                                                    <a class="next page-numbers" href="<%= request.getContextPath()%>/qanda/tags?sort=${type_sort}&page=${page_current+1}">
                                                                        <span class="glyphicon glyphicon-chevron-right"></span>
                                                                    </a>
                                                                </c:if>
                                                            </c:if>
                                                        </c:if>                   
                                                    </div>
                                                </div>
                                            </div>
                                        </article>
                                    </section>
                                </div>
                                <div class="hide-main-inner"></div>
                                <div class="hide-sidebar">
                                    <div class="hide-sidebar-inner"></div>
                                </div>
                                <aside class="sidebar float_l" style="position: relative; overflow: visible; box-sizing: border-box;">
                                    <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static;">
                                        <div class="inner-sidebar">
                                            <div class="widget widget_ask">
                                                <a href="javascript:void(0)" class="button-default wpqa-question">Thêm câu hỏi</a>
                                            </div>
                                            <section id="tags-widget" class="widget tags-widget">
                                                <h4 class="widget-title"><span class="glyphicon glyphicon-tags" style="margin-right: 10px;"></span>Xu hướng tags</h4>
                                                <div class="tagscloud">
                                                    <c:forEach var="data" items="${tag_statistical}">
                                                        <a class="tagcloud-link" href="<%= request.getContextPath()%>/qanda/question/search/tag/${data.name}">${data.name}</a>
                                                    </c:forEach>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </aside>
                            </div>
                        </main>
                    </div>
                </div>
            </div> 
            <footer class="footer no-widget-icons">
                <div class="inner-footer">
                    <div class="top-footer">
                        <div class="detail-container">
                            <p class="credits">© 2018 Faker.</p>
                        </div>
                    </div>
                </div>
            </footer>
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
        <script src='<c:url value="/view/frontend/web/js/qanda/vote_question.js" />'></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $(".wpqa-question").click(function () {
                    var flag = checkLogin();
                    if (flag == false) {
                        if (confirm('Bạn phải đăng nhập để thêm câu hỏi!')) {
                            showLoginForm();
                        }
                    } else {
                        $(location).attr('href', context_path + '/qanda/question/add');
                    }
                });
            });
        </script>
    </body>
</html>
