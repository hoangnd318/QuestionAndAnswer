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
        <link href='<c:url value="/view/frontend/web/css/qanda/question_detail.css" />' rel='stylesheet'>
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
            var question_id = ${question_detail.id};
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
                                        <input placeholder="Tìm kiếm" name="search" type="search" autocomplete="off"></input>
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
                                    <div class="post-articles question-articles">
                                        <article id="" class="article-post">
                                            <div class="single-inner-content">
                                                <div class="question-inner">
                                                    <div class="question-image-vote">
                                                        <div class="author-image">
                                                            <a href="<%= request.getContextPath()%>/qanda/user/profile/question/${question_detail.author.idUser}">
                                                                <span class="author-image-span">
                                                                    <img class="avatar" src="<%= request.getContextPath()%>${question_detail.author.account.avatar.url}" width="42" height="42">
                                                                </span>
                                                            </a>
                                                        </div>
                                                    </div>
                                                    <div class="question-content question-content-first">
                                                        <header class="article-header">
                                                            <div class="question-header">
                                                                <a class="post-author" href="<%= request.getContextPath()%>/qanda/user/profile/question/${question_detail.author.idUser}">${question_detail.author.firstname} ${question_detail.author.lastname}</a>
                                                                <div class="post-meta">
                                                                    <span class="post-date">Đã hỏi lúc: ${question_detail.convertTime}</span>
                                                                </div>
                                                            </div>
                                                        </header>
                                                        <div class="nameq">
                                                            <h1 class="post-title">${question_detail.title}</h1>
                                                        </div>
                                                    </div>
                                                    <div class="question-image-vote">
                                                        <div class="">
                                                            <ul class="question-vote">
                                                                <input name="_id_" value="${question_detail.id}" type="hidden">
                                                                <li class="question-vote-up <c:if test="${question_detail.votedByUser != 'un_vote'}">q_v</c:if> <c:if test="${question_detail.votedByUser == 'vote_up'}">voted</c:if>">
                                                                        <a href="javascript:void(0)" title="Vote up">
                                                                            <span class="glyphicon glyphicon-triangle-top"></span>
                                                                        </a>
                                                                    </li>
                                                                        <li class="vote_result">${question_detail.vote}</li>
                                                                <li class="question-vote-down <c:if test="${question_detail.votedByUser != 'un_vote'}">q_v</c:if> <c:if test="${question_detail.votedByUser == 'vote_down'}">voted</c:if>">
                                                                        <a href="javascript:void(0)" title="Vote down">
                                                                            <span class="glyphicon glyphicon-triangle-bottom"></span>   
                                                                        </a>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <div class="question-content question-content-second">
                                                            <div class="post-wrap-content">
                                                                <div class="question-content-text">
                                                                    <div class="content-text">
                                                                    ${question_detail.content_raw}
                                                                </div>
                                                            </div>
                                                            <div class="tagcloud">
                                                                <div class="question-tags">
                                                                    <c:forEach var="entry" items="${question_detail.tags}">
                                                                        <a href="#">${entry.name}</a> 
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <footer class="question-footer">
                                                            <ul class="footer-meta">
                                                                <li>
                                                                    <span class="glyphicon glyphicon-comment"></span>
                                                                    ${question_detail.countAnswer}
                                                                    <span class="question-span">Answers</span>
                                                                </li>
                                                                <li><span class="glyphicon glyphicon-eye-open"></span> ${question_detail.views} <span class="question-span">Views</span></li>
                                                            </ul>
                                                        </footer>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <c:if test="${question_detail.author.idUser == user_account.idUser}">            
                                                    <div class="question-bottom">
                                                        <ul class="question-link-list">
                                                            <li><a href="<%= request.getContextPath()%>/qanda/question/edit/${question_detail.id}"><span class="glyphicon glyphicon-pencil"></span> Sửa</a></li>
                                                            <li><a class="question-delete" href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span> Xóa</a></li>
                                                        </ul>
                                                    </div>
                                                </c:if>
                                            </div>        
                                            <div class="question-adv-comments">
                                                <c:if test="${fn:length(question_detail.answers) > 0}"> 
                                                    <div id="comments" class="post-section">
                                                        <div class="post-inner">
                                                            <div class="answers-tabs">
                                                                <h3 class="section-title">Các câu trả lời</h3>
                                                                <div class="answers-tabs-inner">
                                                                    <ul>
                                                                        <li class="<c:if test="${type_sort == 'noi-bat'}">active-tab</c:if>"><a href="<%= request.getContextPath()%>/qanda/question/${question_detail.id}/${question_detail.title_raw}?sort=noi-bat">Bình chọn</a></li>
                                                                        <li class="<c:if test="${type_sort == 'moi-nhat'}">active-tab</c:if>"><a href="<%= request.getContextPath()%>/qanda/question/${question_detail.id}/${question_detail.title_raw}?sort=moi-nhat">Mới nhất</a></li>
                                                                        <li class="<c:if test="${type_sort == 'cu-nhat'}">active-tab</c:if>"><a href="<%= request.getContextPath()%>/qanda/question/${question_detail.id}/${question_detail.title_raw}?sort=cu-nhat">Cũ nhất</a></li>
                                                                        </ul>
                                                                    </div>
                                                                    <div class="clearfix"></div>
                                                                </div>
                                                                <ol class="commentlist clearfix">
                                                                <c:forEach var="answers" items="${question_detail.answers}">
                                                                    <li id="refe-${answers.id}" class="comment">
                                                                        <div class="comment-body clearfix">
                                                                            <div class="author-image">
                                                                                <a href="<%= request.getContextPath()%>/qanda/user/profile/question/${answers.author.idUser}">
                                                                                    <span class="author-image-span"><img class="avatar avatar-42 photo" alt="${answers.author.firstname} ${answers.author.lastname}" src="<%= request.getContextPath()%>${answers.author.account.avatar.url}" width="42" height="42"></span>
                                                                                </a>
                                                                            </div>
                                                                            <div class="comment-text">
                                                                                <div class="author clearfix">
                                                                                    <div class="comment-meta">
                                                                                        <c:if test="${answers.bestAnswer == 1}">
                                                                                            <div class="best-answer">Best Answer</div>
                                                                                        </c:if>
                                                                                        <div class="comment-author"> 
                                                                                            <a href="<%= request.getContextPath()%>/qanda/user/profile/question/${answers.author.idUser}"> ${answers.author.firstname} ${answers.author.lastname} </a>
                                                                                        </div>
                                                                                        <a href="javascript:void(0)" class="comment-date"> Trả lời vào lúc: ${answers.convertTime} </a>
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
                                                                                                <c:if test="${user_account.idUser == question_detail.author.idUser && question_detail.hasBestAnswer == 0 && user_account.idUser != answers.author.idUser}">
                                                                                            <li class="vote-best-answer"><button>Best Answer</button></li>
                                                                                            </c:if>
                                                                                    </ul>
                                                                                    <ul class="comment-reply">
                                                                                        <c:if test="${user_account.idUser == answers.author.idUser}">
                                                                                            <input name="_a_id_" value="${answers.id}" type="hidden">
                                                                                            <li class="edit-answer"><a href="<%= request.getContextPath()%>/qanda/question/answer/edit/${answers.id}"><span class="glyphicon glyphicon-pencil"></span></i> Sửa</a></li>
                                                                                            <li class="delete-answer"><a href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></i> Xóa</a></li>
                                                                                            </c:if>
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
                                                                        <ul class="children">
                                                                            <c:forEach var="comment" items="${answers.comments}">
                                                                                <li class="comment">
                                                                                    <div class="comment-body clearfix">
                                                                                        <div class="author-image">
                                                                                            <a href="<%= request.getContextPath()%>/qanda/user/profile/question/${comment.author.idUser}">
                                                                                                <span class="author-image-span"><img class="avatar avatar-42 photo" alt="${comment.author.firstname} ${comment.author.lastname}" src="<%= request.getContextPath()%>${comment.author.account.avatar.url}" width="42" height="42"></span>
                                                                                            </a>
                                                                                        </div>
                                                                                        <div class="comment-text">
                                                                                            <div class="author clearfix">
                                                                                                <div class="comment-meta">
                                                                                                    <div class="comment-author">
                                                                                                        <a href="<%= request.getContextPath()%>/qanda/user/profile/question/${comment.author.idUser}"> ${comment.author.firstname} ${comment.author.lastname} </a>

                                                                                                    </div>
                                                                                                    <a href="javascript:void(0)" class="comment-date"> Bình luận vào lúc: ${comment.convertTime} </a>
                                                                                                </div>
                                                                                            </div>
                                                                                            <div class="text">
                                                                                                <div id="c_t_${comment.id}">
                                                                                                    ${comment.content}
                                                                                                </div>
                                                                                                <div id="e_c_b_${comment.id}" class="edit-comment-box">
                                                                                                    <input autocomplete="off" class="i_c_e" name="c_text_${comment.id}" value="${comment.content}">
                                                                                                </div>
                                                                                            </div>
                                                                                            <c:if test="${user_account.idUser == comment.author.idUser}">
                                                                                                <ul class="comment-reply">
                                                                                                    <input name="_c_id_" value="${comment.id}" type="hidden">
                                                                                                    <li class="edit-comment"><a href="javascript:void(0)"><span class="glyphicon glyphicon-pencil"></span></i> Sửa</a></li>
                                                                                                    <li class="delete-comment"><a href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></i> Xóa</a></li>
                                                                                                </ul>
                                                                                            </c:if>
                                                                                        </div>
                                                                                    </div>
                                                                                </li>
                                                                            </c:forEach>
                                                                        </ul>
                                                                    </li>
                                                                </c:forEach>
                                                            </ol>
                                                            <div class="clearfix"></div>
                                                        </div>
                                                        <div class="pagination-wrap">
                                                            <div class="main-pagination">
                                                                <div class="pagination">
                                                                    <c:if test="${page_total > 1}">    
                                                                        <c:if test="${question_detail.answers.size() > 0}">
                                                                            <c:if test="${page_current != 1}">
                                                                                <a class="prev page-numbers" href="${page_url}&page=${page_current-1}">
                                                                                    <span class="glyphicon glyphicon-chevron-left"></span>
                                                                                </a>
                                                                            </c:if>
                                                                            <c:if test="${page_total <= 5}">
                                                                                <c:forEach var="i" begin="1" end="${page_total}">
                                                                                    <c:if test="${i != page_current}">
                                                                                        <a class="page-numbers" href="${page_url}&page=${i}">${i}</a>
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
                                                                                            <a class="page-numbers" href="${page_url}&page=${i}">${i}</a>
                                                                                        </c:if>
                                                                                        <c:if test="${i == page_current}">
                                                                                            <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                        </c:if>
                                                                                    </c:forEach>
                                                                                    <span class="page-numbers dots">…</span>  
                                                                                    <a class="page-numbers" href="${page_url}&page=${page_total}">${page_total}</a>
                                                                                </c:if>
                                                                                <c:if test="${page_current > 3}">  
                                                                                    <c:if test="${page_current >= (page_total - 3)}">
                                                                                        <a class="page-numbers" href="${page_url}&page=1">1</a>
                                                                                        <span class="page-numbers dots">…</span>
                                                                                        <c:forEach var="i" begin="${(page_total - 3)}" end="${page_total}">
                                                                                            <c:if test="${i != page_current}">
                                                                                                <a class="page-numbers" href="${page_url}&page=${i}">${i}</a>
                                                                                            </c:if>
                                                                                            <c:if test="${i == page_current}">
                                                                                                <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                    </c:if>
                                                                                    <c:if test="${page_current < (page_total - 3)}">
                                                                                        <a class="page-numbers" href="${page_url}&page=1">1</a>
                                                                                        <span class="page-numbers dots">…</span>
                                                                                        <c:forEach var="i" begin="${(page_current - 1)}" end="${(page_current + 2)}">
                                                                                            <c:if test="${i != page_current}">
                                                                                                <a class="page-numbers" href="${page_url}&page=${i}">${i}</a>
                                                                                            </c:if>
                                                                                            <c:if test="${i == page_current}">
                                                                                                <span aria-current="page" class="page-numbers current">${i}</span>
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                        <span class="page-numbers dots">…</span>  
                                                                                        <a class="page-numbers" href="${page_url}&page=${page_total}">${page_total}</a>
                                                                                    </c:if>
                                                                                </c:if>
                                                                            </c:if>
                                                                            <c:if test="${page_current != page_total}">
                                                                                <a class="next page-numbers" href="${page_url}&page=${page_current+1}">
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
                                                <div id="respond" class="comment-respond" style="">
                                                    <div class="button-default show-answer-form">Thêm câu trả lời</div>
                                                </div>
                                                <div class="form-answer" style="display: none;">
                                                    <div class="wmd-panel">
                                                        <div id="wmd-button-bar"></div>
                                                        <label for="answer_question_body" class="required"></label>
                                                        <textarea name="a_content" class="wmd-input" id="ask_question_body"></textarea>
                                                    </div>
                                                    <div style="margin-top: 15px;">Review câu trả lời</div>
                                                    <div id="wmd-preview" class="wmd-panel wmd-preview"></div>
                                                    <button type="button" class="b-save-answer">Gửi câu trả lời</button>
                                                    <div class="load-send">
                                                        <div class="tag-loading">
                                                            <div class="loader"></div> 
                                                        </div> 
                                                        <div class="res-send"></div>
                                                    </div>
                                                </div>
                                            </div> 
                                        </article>
                                    </div>
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
        <div class="go-up">
            <span class="glyphicon glyphicon-menu-up"></span>
        </div>
        <script src='<c:url value="/view/frontend/web/js/socket.io/socket.io.js" />'></script>  
        <c:if test="${user_account != null}">
            <script>
            var socket = io('192.168.1.4:8000');

            socket.on('reciveNotification', function (data) {
                var u_id = ${user_account.idUser};
                if (data == u_id) {
                    alert("nhan thong bao");
                }
            });
            </script>   
        </c:if>
        <script src='<c:url value="/view/frontend/web/js/socket.io/socket.io.js" />'></script>  
        <script src='<c:url value="/view/frontend/web/js/qanda/send_notification.js" />'></script> 
        <script src='<c:url value="/view/frontend/web/js/qanda/var_general.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/general.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/check_login.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/notifications.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/login.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/vote_question.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/vote_answer.js" />'></script>
        <script src='<c:url value="/view/frontend/web/js/qanda/question_detail.js" />'></script>
        <script type="text/javascript">
            var answer_id_refe = <%= request.getParameter("answer_id")%>;
            if (answer_id_refe != null) {
                var object = 'refe-' + answer_id_refe;
                $("html,body").animate({
                    scrollTop: $('#' + object).position().top
                }, 500);
                $('#' + object).css({border: '0 solid #f37736'}).animate({
                    borderWidth: 1
                }, 500);
                setTimeout(function () {
                    $('#' + object).animate({
                        borderWidth: 0
                    }, 500);
                }, 1000);
            }
        </script>
        <script type="text/javascript">
            var converter1 = Markdown.getSanitizingConverter();
        </script>
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
