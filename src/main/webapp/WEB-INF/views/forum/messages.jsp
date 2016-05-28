<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Повідомлення</title>
</head>
<body>
<div class="title"><span>Повідомлення</span></div>
<div class = "wButton"><section><a href="/forum/write" class="button"><button id="messageButton" type="button">Написати повідомлення</button></a></section></div>

<ul class="comments">
    <c:forEach var="comment" items="${messagesList}">
        <li>
            <div class="comment">
                <p id="comment_date"> Дата: ${comment.created}</p>
                <p>Автор: ${comment.login}</p>
                <p>Текст: ${comment.comment}</p>
            </div>
            <br>
        </li>
    </c:forEach>
</ul>
<div class="pagination">
    <c:if test="${currentPage != 1}">
        <td><a href="/forum/messages?page=${currentPage - 1}">Попередня</a></td>
    </c:if>

    <c:forEach begin="1" end="${noOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                ${i}
            </c:when>
            <c:otherwise>
                <a href="/forum/messages?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage lt noOfPages}">
        <td><a href="/forum/messages?page=${currentPage + 1}">Наступна</a></td>
    </c:if>
</div>
<%--<div class="background"></div>--%>
<div id="form" class="form">
    <h2>Написати повідолення</h2>
    <div class="back">
        <div class="error">

        </div>
        <p class="name">
            <input type="text" name="name" placeholder="Логін" value="${user}"/>
            <label>Логін</label>
        </p>
        <p class="email">
            <input type="text" name="email" placeholder="mail@example.com" value="${email}"/>
            <label>Email</label>
        </p>
        <p class="text">
            <textarea name="text" placeholder="Текст повідомлення"></textarea>
        </p>
        <p class="submit">
            <input type="button" value="Відправити" class="sButton"/> <input type="button" value="Відміна" class="cButton"/>
        </p>
    </div>
</div>
<div class="thanks">
    <div class="thanksText"></div>
    <div class="thanksButton">
        <a href="/forum/messages" id="closeButton">Повернутись</a>
    </div>
</div>
<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script type="text/javascript" charset="UTF-8" src="/static/scripts/forum.js?${cacheToken}"></script>
</body>

