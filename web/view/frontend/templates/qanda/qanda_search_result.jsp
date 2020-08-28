<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href='<c:url value="/view/frontend/web/css/bootstrap.min.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/tag_input.css" />' rel='stylesheet'>
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
                                <a class="button-default-2 button-sign-up" href="#">Đăng ký</a>
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
                                        <input placeholder="Tìm kiếm" name="q" type="search" autocomplete="off"></input>
                                        <div class="search-click"></div>
                                        <button id="button-form-search" type="button">
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
                            <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static;top: 30px;left: 89.5px;">
                                <ul id="menu-explore-not-login" class="menu">
                                    <li class="menu-item"><a href="<%= request.getContextPath()%>/qanda/tags"><span class="glyphicon glyphicon-tag"></span> Tags</a></li>
                                </ul>
                            </div>
                        </nav>
                        <main class="main-wrap" style="position: relative; overflow: visible; box-sizing: border-box;">
                            <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static;">
                                <c:if test="${q_search.size() <= 0}"><h1>Không có dữ liệu</h1></c:if>
                                <c:if test="${q_search.size() > 0}">
                                    <div class="main-inner float_l">
                                        <div class="clearfix"></div>
                                        <div class="row">
                                            <div class="col col12">
                                                <div class="wrap-tabs">
                                                    <div class="menu-tabs active-menu">
                                                        <ul class="menu flex">
                                                            <li class="<c:if test="${type_sort == 'moi-nhat'}">active-tab</c:if>"><a href="${page_url}sort=moi-nhat">Mới nhất</a></li>
                                                            <li class="<c:if test="${type_sort == 'cu-nhat'}">active-tab</c:if>"><a href="${page_url}sort=cu-nhat">Cũ nhất</a></li>
                                                            <li class="<c:if test="${type_sort == 'noi-bat'}">active-tab</c:if>"><a href="${page_url}sort=noi-bat">Nổi bật</a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <section>
                                                <div class="question-articles">
                                                <c:forEach var="question" items="${q_search}">                                            
                                                    <article class="article-question">
                                                        <div class="single-inner-content">
                                                            <div class="question-inner">
                                                                <div class="question-image-vote">
                                                                    <div class="author-image">
                                                                        <a href="<%= request.getContextPath()%>/qanda/user/profile/question/${question.author.idUser}">
                                                                            <span class="author-image-span">
                                                                                <img class="avatar avatar-42 photo" alt="${question.author.lastname}" src="<c:url value="${question.author.account.avatar.url}" />" width="42" height="42">
                                                                            </span>
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                                <div class="question-content">
                                                                    <header class="article-header">
                                                                        <div class="question-header">
                                                                            <a class="post-author" href="<%= request.getContextPath()%>/qanda/user/profile/question/${question.author.idUser}">${question.author.firstname} ${question.author.lastname}</a>
                                                                            <div class="post-meta">
                                                                                <span class="post-date" >Đã hỏi lúc: ${question.convertTime}</span>
                                                                            </div>
                                                                        </div>
                                                                    </header>
                                                                    <div>
                                                                        <h2 class="post-title">
                                                                            <a href="<%= request.getContextPath()%>/qanda/question/${question.id}/${question.title_raw}" class="post-title">${question.title}</a>
                                                                        </h2>
                                                                    </div>
                                                                </div>
                                                                <div class="question-image-vote">
                                                                    <div class="question-sticky">
                                                                        <ul class="question-vote">
                                                                            <input name="_id_" value="${question.id}" type="hidden">
                                                                            <li class="question-vote-up <c:if test="${question.votedByUser != 'un_vote'}">q_v</c:if> <c:if test="${question.votedByUser == 'vote_up'}">voted</c:if>"><a href="javascript:void(0)" title="Like"><span class="glyphicon glyphicon-triangle-top"></span></a></li>
                                                                            <li class="vote_result">${question.vote}</li>
                                                                            <li class="question-vote-down <c:if test="${question.votedByUser != 'un_vote'}">q_v</c:if> <c:if test="${question.votedByUser == 'vote_down'}">voted</c:if>"><a href="javascript:void(0)" title="Dislike"><span class="glyphicon glyphicon-triangle-bottom"></span></a></li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                    <div class="question-content-second">
                                                                        <div class="post-wrap-content">
                                                                            <div class="question-content-text">
                                                                                    <p>${question.content}</p>
                                                                        </div>
                                                                        <div class="tagcloud">
                                                                            <div class="question-tags">
                                                                                <c:forEach var="tag" items="${question.tags}">
                                                                                    <a href="">${tag.name}</a>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <footer class="question-footer">
                                                                        <ul class="footer-meta">
                                                                            <li><span class="glyphicon glyphicon-comment"></span> ${question.countAnswer} <span class="question-span">Answers</span></li>
                                                                            <li><span class="glyphicon glyphicon-eye-open"></span> ${question.views} <span class="question-span">Views</span></li>
                                                                        </ul>
                                                                    </footer>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </article>
                                                </c:forEach>
                                            </div>
                                            <div class="pagination-wrap">
                                                <div class="main-pagination">
                                                    <div class="pagination">
                                                        <c:if test="${page_total > 0}">    
                                                            <c:if test="${q_search.size() > 0}">
                                                                <c:if test="${page_current != 1}">
                                                                    <a class="prev page-numbers" href="${page_url}page=${page_current-1}&sort=${type_sort}">
                                                                        <span class="glyphicon glyphicon-chevron-left"></span>
                                                                    </a>
                                                                </c:if>
                                                                <c:if test="${page_total <= 5}">
                                                                    <c:forEach var="i" begin="1" end="${page_total}">
                                                                        <c:if test="${i != page_current}">
                                                                            <a class="page-numbers" href="${page_url}page=${i}&sort=${type_sort}">${i}</a>
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
                                                                                <a class="page-numbers" href="${page_url}page=${i}&sort=${type_sort}">${i}</a>
                                                                            </c:if>
                                                                            <c:if test="${i == page_current}">
                                                                                <span aria-current="page" class="page-numbers current">${i}</span>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                        <span class="page-numbers dots">…</span>  
                                                                        <a class="page-numbers" href="${page_url}page=${page_total}&sort=${type_sort}">${page_total}</a>
                                                                    </c:if>
                                                                    <c:if test="${page_current > 3}">  
                                                                        <c:if test="${page_current >= (page_total - 3)}">
                                                                            <a class="page-numbers" href="${page_url}page=1&sort=${type_sort}">1</a>
                                                                            <span class="page-numbers dots">…</span>
                                                                            <c:forEach var="i" begin="${(page_total - 3)}" end="${page_total}">
                                                                                <c:if test="${i != page_current}">
                                                                                    <a class="page-numbers" href="${page_url}page=${i}&sort=${type_sort}">${i}</a>
                                                                                </c:if>
                                                                                <c:if test="${i == page_current}">
                                                                                    <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                        </c:if>
                                                                        <c:if test="${page_current < (page_total - 3)}">
                                                                            <a class="page-numbers" href="${page_url}page=1&sort=${type_sort}">1</a>
                                                                            <span class="page-numbers dots">…</span>
                                                                            <c:forEach var="i" begin="${(page_current - 1)}" end="${(page_current + 2)}">
                                                                                <c:if test="${i != page_current}">
                                                                                    <a class="page-numbers" href="${page_url}page=${i}&sort=${type_sort}">${i}</a>
                                                                                </c:if>
                                                                                <c:if test="${i == page_current}">
                                                                                    <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                            <span class="page-numbers dots">…</span>  
                                                                            <a class="page-numbers" href="${page_url}page=${page_total}&sort=${type_sort}">${page_total}</a>
                                                                        </c:if>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${page_current != page_total}">
                                                                    <a class="next page-numbers" href="${page_url}page=${page_current+1}&sort=${type_sort}">
                                                                        <span class="glyphicon glyphicon-chevron-right"></span>
                                                                    </a>
                                                                </c:if>
                                                            </c:if>
                                                        </c:if>                   
                                                    </div>
                                                </div>
                                            </div>
                                        </section>
                                    </div>
                                </c:if>                        
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
