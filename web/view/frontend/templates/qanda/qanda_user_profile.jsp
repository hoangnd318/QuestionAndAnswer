<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href='<c:url value="/view/frontend/web/css/bootstrap.min.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/content.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/question_detail.css" />' rel='stylesheet'>
        <link href='<c:url value="/view/frontend/web/css/qanda/user_profile.css" />' rel='stylesheet'>
        <script src='<c:url value="/view/frontend/web/js/jquery-3.3.1.min.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/bootstrap.min.js" />'></script>
    </head>
    <body class="home">    
        <script type="text/javascript">
            var user_id = "${user_account.idUser}";
        </script>
        <div class="background-cover"></div>
        <div id="wrap">
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
                            <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static;top: 30px;left: 89.5px;">
                                <ul id="menu-explore-not-login" class="menu">
                                    <li class="menu-item"><a href="<%= request.getContextPath()%>/qanda/tags"><span class="glyphicon glyphicon-tag"></span> Tags</a></li>
                                </ul>
                            </div>
                        </nav>
                        <main class="main-wrap" style="position: relative; overflow: visible; box-sizing: border-box;">
                            <div class="theiaStickySidebar" style="padding-top: 0px; padding-bottom: 1px; position: static;">
                                <div class="main-inner float_l">
                                    <div class="user-area-content">
                                        <div class="post-section user-area user-advanced">
                                            <div class="post-inner">
                                                <div class="user-head-area">
                                                    <div class="author-image">
                                                        <span class="author-image-span">
                                                            <img alt="${user_account.firstname} ${user_account.lastname}" src="<%= request.getContextPath()%>${user_account.account.avatar.url}" class="avatar avatar-84 photo" width="84" height="84">
                                                        </span>
                                                    </div>
                                                </div>
                                                <div class="user-content">
                                                    <div class="user-inner">
                                                        <div class="user-name">${user_account.firstname} ${user_account.lastname}</div>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                        <div class="user-stats">
                                            <ul class="row">
                                                <li class="user-questions">
                                                    <div>
                                                        <span class="glyphicon glyphicon-book"></span>
                                                        <div>
                                                            <span>${user_statistical.countQuestion}</span>
                                                            <h4>Câu hỏi</h4>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="user-answers">
                                                    <div>
                                                        <span class="glyphicon glyphicon-comment"></span>
                                                        <div>
                                                            <span>${user_statistical.countAnswer}</span>
                                                            <h4>Trả lời</h4>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="user-best-answers">
                                                    <div>
                                                        <span class="glyphicon glyphicon-education"></span>
                                                        <div>
                                                            <span>${user_statistical.countBestAnswer}</span>
                                                            <h4>Trả lời tốt nhất</h4>
                                                        </div>
                                                    </div>
                                                </li>
                                                <li class="user-points">
                                                    <div>
                                                        <span class="glyphicon glyphicon-tower"></span>
                                                        <div>
                                                            <span>${user_account.pointQA}</span>
                                                            <h4>Điểm tin cậy</h4>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="wrap-tabs">
                                        <div class="menu-tabs">
                                            <ul class="menu flex">
                                                <li class="<c:if test="${p_type == 'question'}">active-tab</c:if>">
                                                    <a href="<%= request.getContextPath()%>/qanda/user/profile/question<c:if test="${guest_id != null}">/${guest_id}</c:if>#section-questions">Câu hỏi</a>
                                                    </li>
                                                    <li class="<c:if test="${p_type == 'answer'}">active-tab</c:if>">
                                                    <a href="<%= request.getContextPath()%>/qanda/user/profile/answer<c:if test="${guest_id != null}">/${guest_id}</c:if>#comments">Câu trả lời</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    <c:if test="${p_type == 'question'}">
                                        <c:if test="${user_questions.size() <= 0}"><h1>Không có dữ liệu</h1></c:if>
                                        <c:if test="${user_questions.size() > 0}">
                                            <section id="section-questions">
                                                <div class="post-articles">
                                                    <div class="answers-tabs">
                                                        <h3 class="section-title">Các câu hỏi</h3>
                                                        <div class="answers-tabs-inner">
                                                            <ul>
                                                                <li class="<c:if test="${type_sort == 'moi-nhat'}">active-tab</c:if>"><a href="<%= request.getContextPath()%>/qanda/user/profile/question?sort=moi-nhat#section-questions">Mới nhất</a></li>
                                                                <li class="<c:if test="${type_sort == 'cu-nhat'}">active-tab</c:if>"><a href="<%= request.getContextPath()%>/qanda/user/profile/question?sort=cu-nhat#section-questions">Cũ nhất</a></li>
                                                                </ul>
                                                            </div>
                                                            <div class="clearfix"></div>
                                                        </div>
                                                    <c:forEach var="entry" items="${user_questions}">
                                                        <article class="article-question">
                                                            <div class="single-inner-content">
                                                                <div class="question-inner">
                                                                    <div class="question-image-vote">
                                                                        <div class="author-image">
                                                                            <a href="javascript:void(0)">
                                                                                <span class="author-image-span">
                                                                                    <img class="avatar avatar-42 photo" alt="${entry.author.lastname}" src="<c:url value="${entry.author.account.avatar.url}" />" width="42" height="42">
                                                                                </span>
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                    <div class="question-content">
                                                                        <header class="article-header">
                                                                            <div class="question-header">
                                                                                <a class="post-author" href="javascript:void(0)">${entry.author.firstname} ${entry.author.lastname}</a>
                                                                                <div class="post-meta">
                                                                                    <span class="post-date" >Đã hỏi lúc: ${entry.convertTime}</span>
                                                                                </div>
                                                                            </div>
                                                                        </header>
                                                                        <div>
                                                                            <h2 class="post-title">
                                                                                <a href="<%= request.getContextPath()%>/qanda/question/${entry.id}/${entry.title_raw}" class="post-title">${entry.title}</a>
                                                                            </h2>
                                                                        </div>
                                                                    </div>
                                                                    <div class="question-image-vote">
                                                                        <div class="question-sticky">
                                                                            <ul class="question-vote">
                                                                                <input name="_id_" value="${entry.id}" type="hidden">
                                                                                <li class="question-vote-up <c:if test="${entry.votedByUser != 'un_vote'}">q_v</c:if> <c:if test="${entry.votedByUser == 'vote_up'}">voted</c:if>"><a href="javascript:void(0)"  title="Like"><span class="glyphicon glyphicon-triangle-top"></span></a></li>
                                                                                <li class="vote_result">${entry.vote}</li>
                                                                                <li class="question-vote-down <c:if test="${entry.votedByUser != 'un_vote'}">q_v</c:if> <c:if test="${entry.votedByUser == 'vote_down'}">voted</c:if>"><a href="javascript:void(0)" title="Dislike"><span class="glyphicon glyphicon-triangle-bottom"></span></a></li>
                                                                                </ul>
                                                                            </div>
                                                                        </div>
                                                                        <div class="question-content-second">
                                                                            <div class="post-wrap-content">
                                                                                <div class="question-content-text">
                                                                                        <p>${entry.content}</p>
                                                                            </div>
                                                                            <div class="tagcloud">
                                                                                <div class="question-tags">
                                                                                    <c:forEach var="tag" items="${entry.tags}">
                                                                                        <a href="">${tag.name}</a>
                                                                                    </c:forEach>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <footer class="question-footer">
                                                                            <ul class="footer-meta">
                                                                                <li><span class="glyphicon glyphicon-comment"></span> ${entry.countAnswer} <span class="question-span">Answers</span></li>
                                                                                <li><span class="glyphicon glyphicon-eye-open"></span> ${entry.views} <span class="question-span">Views</span></li>
                                                                            </ul>
                                                                            <a class="meta-answer" href="#">Answer</a>
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
                                                                <c:if test="${user_questions.size() > 0}">
                                                                    <c:if test="${page_current != 1}">
                                                                        <a class="prev page-numbers" href="${page_url}page=${page_current-1}#section-questions">
                                                                            <span class="glyphicon glyphicon-chevron-left"></span>
                                                                        </a>
                                                                    </c:if>
                                                                    <c:if test="${page_total <= 5}">
                                                                        <c:forEach var="i" begin="1" end="${page_total}">
                                                                            <c:if test="${i != page_current}">
                                                                                <a class="page-numbers" href="${page_url}page=${i}#section-questions">${i}</a>
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
                                                                                    <a class="page-numbers" href="${page_url}page=${i}#section-questions">${i}</a>
                                                                                </c:if>
                                                                                <c:if test="${i == page_current}">
                                                                                    <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                            <span class="page-numbers dots">…</span>  
                                                                            <a class="page-numbers" href="${page_url}page=${page_total}#section-questions">${page_total}</a>
                                                                        </c:if>
                                                                        <c:if test="${page_current > 3}">  
                                                                            <c:if test="${page_current >= (page_total - 3)}">
                                                                                <a class="page-numbers" href="${page_url}page=1#section-questions">1</a>
                                                                                <span class="page-numbers dots">…</span>
                                                                                <c:forEach var="i" begin="${(page_total - 3)}" end="${page_total}">
                                                                                    <c:if test="${i != page_current}">
                                                                                        <a class="page-numbers" href="${page_url}page=${i}#section-questions">${i}</a>
                                                                                    </c:if>
                                                                                    <c:if test="${i == page_current}">
                                                                                        <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </c:if>
                                                                            <c:if test="${page_current < (page_total - 3)}">
                                                                                <a class="page-numbers" href="${page_url}page=1#section-questions">1</a>
                                                                                <span class="page-numbers dots">…</span>
                                                                                <c:forEach var="i" begin="${(page_current - 1)}" end="${(page_current + 2)}">
                                                                                    <c:if test="${i != page_current}">
                                                                                        <a class="page-numbers" href="${page_url}page=${i}#section-questions">${i}</a>
                                                                                    </c:if>
                                                                                    <c:if test="${i == page_current}">
                                                                                        <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                                <span class="page-numbers dots">…</span>  
                                                                                <a class="page-numbers" href="${page_url}page=${page_total}#section-questions">${page_total}</a>
                                                                            </c:if>
                                                                        </c:if>
                                                                    </c:if>
                                                                    <c:if test="${page_current != page_total}">
                                                                        <a class="next page-numbers" href="${page_url}page=${page_current+1}#section-questions">
                                                                            <span class="glyphicon glyphicon-chevron-right"></span>
                                                                        </a>
                                                                    </c:if>
                                                                </c:if>
                                                            </c:if>                   
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${p_type == 'answer'}">
                                        <c:if test="${user_answers.size() <= 0}"><h1>Không có dữ liệu</h1></c:if>
                                        <c:if test="${user_answers.size() > 0}">
                                            <div id="comments" class="post-section">
                                                <div class="post-inner">
                                                    <div class="answers-tabs">
                                                        <h3 class="section-title">Các câu trả lời</h3>
                                                        <div class="answers-tabs-inner">
                                                            <ul>
                                                                <li class="<c:if test="${type_sort == 'moi-nhat'}">active-tab</c:if>"><a href="<%= request.getContextPath()%>/qanda/user/profile/answer?sort=moi-nhat#comments">Mới nhất</a></li>
                                                                <li class="<c:if test="${type_sort == 'cu-nhat'}">active-tab</c:if>"><a href="<%= request.getContextPath()%>/qanda/user/profile/answer?sort=cu-nhat#comments">Cũ nhất</a></li>
                                                                </ul>
                                                            </div>
                                                            <div class="clearfix"></div>
                                                        </div>
                                                        <ol class="commentlist clearfix">
                                                        <c:forEach var="answers" items="${user_answers}">

                                                            <li class="comment">
                                                                <div class="comment-body clearfix">
                                                                    <div class="author-image">
                                                                        <a href="javascript:void(0)">
                                                                            <span class="author-image-span"><img class="avatar avatar-42 photo" alt="${answers.author.firstname} ${answers.author.lastname}" src="<%= request.getContextPath()%>${answers.author.account.avatar.url}" width="42" height="42"></span>
                                                                        </a>
                                                                    </div>
                                                                    <div class="comment-text">
                                                                        <div class="author clearfix">
                                                                            <div class="comment-meta">
                                                                                <div class="comment-author"> 
                                                                                    <a href="javascript:void(0)"> ${answers.author.firstname} ${answers.author.lastname} </a>
                                                                                </div>
                                                                                <a href="<%= request.getContextPath()%>/qanda/question/${answers.question.id}/${answers.question.title_raw}?answer_id=${answers.id}" class="comment-date"> Trả lời vào lúc: ${answers.convertTime} </a>
                                                                            </div>
                                                                        </div>
                                                                        <div class="text">
                                                                            <div>
                                                                                ${answers.content_raw}  
                                                                            </div>
                                                                            <div class="clearfix"></div>
                                                                            <ul class="question-vote answer-vote answer-vote-dislike"> 
                                                                                <input name="_id_" value="${answers.id}" type="hidden">
                                                                                <li class="answer-vote-up <c:if test="${answers.votedByUser != 'un_vote'}">a_v</c:if> <c:if test="${answers.votedByUser == 'vote_up'}">voted</c:if>"><a href="javascript:void(0)" title="Like"><span class="glyphicon glyphicon-triangle-top"></span></a></li>
                                                                                <li class="vote_result">${answers.vote}</li> 
                                                                                <li class="answer-vote-down <c:if test="${answers.votedByUser != 'un_vote'}">a_v</c:if> <c:if test="${answers.votedByUser == 'vote_down'}">voted</c:if>"><a href="javascript:void(0)" title="Dislike"><span class="glyphicon glyphicon-triangle-bottom"></span></a></li>
                                                                                </ul>
                                                                                <ul class="comment-reply">
                                                                                    <li>
                                                                                            <div data-answer-id="${answers.id}" class="reply-answer">
                                                                                        <a class="comment-reply-link" href="javascript:void(0)">
                                                                                            <span class="glyphicon glyphicon-pencil"></span>
                                                                                            Bình luận
                                                                                        </a>
                                                                                    </div>
                                                                                </li>
                                                                            </ul>
                                                                        </div>
                                                                    </div>
                                                                    <div id="comment-answer-${answers.id}" class="comment-answer">
                                                                        <div class="comment-content">
                                                                            <textarea id="comment-content-${answers.id}"></textarea>
                                                                        </div>
                                                                        <button type="button" data-answer-id="${answers.id}" class="b-save-comment">Lưu</button>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                        </c:forEach>
                                                    </ol>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div class="pagination-wrap">
                                                    <div class="main-pagination">
                                                        <div class="pagination">
                                                            <c:if test="${page_total > 0}">    
                                                                <c:if test="${user_answers.size() > 0}">
                                                                    <c:if test="${page_current != 1}">
                                                                        <a class="prev page-numbers" href="${page_url}page=${page_current-1}#comments">
                                                                            <span class="glyphicon glyphicon-chevron-left"></span>
                                                                        </a>
                                                                    </c:if>
                                                                    <c:if test="${page_total <= 5}">
                                                                        <c:forEach var="i" begin="1" end="${page_total}">
                                                                            <c:if test="${i != page_current}">
                                                                                <a class="page-numbers" href="${page_url}page=${i}#comments">${i}</a>
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
                                                                                    <a class="page-numbers" href="${page_url}page=${i}#comments">${i}</a>
                                                                                </c:if>
                                                                                <c:if test="${i == page_current}">
                                                                                    <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                </c:if>
                                                                            </c:forEach>
                                                                            <span class="page-numbers dots">…</span>  
                                                                            <a class="page-numbers" href="${page_url}page=${page_total}#comments">${page_total}</a>
                                                                        </c:if>
                                                                        <c:if test="${page_current > 3}">  
                                                                            <c:if test="${page_current >= (page_total - 3)}">
                                                                                <a class="page-numbers" href="${page_url}page=1#comments">1</a>
                                                                                <span class="page-numbers dots">…</span>
                                                                                <c:forEach var="i" begin="${(page_total - 3)}" end="${page_total}">
                                                                                    <c:if test="${i != page_current}">
                                                                                        <a class="page-numbers" href="${page_url}page=${i}#comments">${i}</a>
                                                                                    </c:if>
                                                                                    <c:if test="${i == page_current}">
                                                                                        <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                            </c:if>
                                                                            <c:if test="${page_current < (page_total - 3)}">
                                                                                <a class="page-numbers" href="${page_url}page=1#comments">1</a>
                                                                                <span class="page-numbers dots">…</span>
                                                                                <c:forEach var="i" begin="${(page_current - 1)}" end="${(page_current + 2)}">
                                                                                    <c:if test="${i != page_current}">
                                                                                        <a class="page-numbers" href="${page_url}page=${i}#comments">${i}</a>
                                                                                    </c:if>
                                                                                    <c:if test="${i == page_current}">
                                                                                        <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                    </c:if>
                                                                                </c:forEach>
                                                                                <span class="page-numbers dots">…</span>  
                                                                                <a class="page-numbers" href="${page_url}page=${page_total}#comments">${page_total}</a>
                                                                            </c:if>
                                                                        </c:if>
                                                                    </c:if>
                                                                    <c:if test="${page_current != page_total}">
                                                                        <a class="next page-numbers" href="${page_url}page=${page_current+1}#comments">
                                                                            <span class="glyphicon glyphicon-chevron-right"></span>
                                                                        </a>
                                                                    </c:if>
                                                                </c:if>
                                                            </c:if>                   
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>                    
                                    </c:if>
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
        </div>
        <script src='<c:url value="/view/frontend/web/js/socket.io/socket.io.js" />'></script>  
        <script src='<c:url value="/view/frontend/web/js/qanda/send_notification.js" />'></script> 
        <script src='<c:url value="/view/frontend/web/js/qanda/var_general.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/general.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/check_login.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/notifications.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/login.js" />'></script>
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
