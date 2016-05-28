<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
  <title>Приватні повідомлення</title>
</head>
<body>
<div class="title"><span>Приватні повідомлення</span></div>
<div class = "wButton"><section><a href="/private/write" class="button"><button id="messageButton" type="button">Написати повідомлення</button></a></section></div>
<ul class = "article_ul">
    <li onclick="display(1)"><div class="prev_title">Вхідні повідомлення</div></li>
    <div class="privateMessages">
    <ul class="comments" id="outMessages" style="display: none">
        <c:forEach var="comment" items="${messagesListIn}">
            <li>
                <div class="comment">
                    <p id="comment_date"> Дата: ${comment.writed}</p>
                    <p>Автор: ${comment.author}</p>
                    <p>Текст: ${comment.text}</p>
                </div>
                <br>
            </li>
        </c:forEach>
    </ul></div>
    <li onclick="display(2)"><div class="prev_title">Вихідні повідомлення</div></li>
    <div class="privateMessages">
    <ul class="comments" id="inMessages" style="display: none">
        <c:forEach var="comment" items="${messagesListOut}">
            <li>
                <div class="comment">
                    <p id="comment_date"> Дата: ${comment.writed}</p>
                    <p>Адресат: ${comment.adressee}</p>
                    <p>Текст: ${comment.text}</p>
                </div>
                <br>
            </li>
        </c:forEach>
    </ul></div>
</ul>
<div id="form" class="form">
    <h2>Написати повідолення</h2>
    <div class="back">
        <div class="error">

        </div>
        <p class="name">
            <input type="text" name="name" placeholder="Логін" value="${user}"/>
            <label>Логін</label>
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
        <a href="/private/index" id="closeButton">Повернутись</a>
    </div>
</div>
<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script type="text/javascript" charset="UTF-8" src="/static/scripts/private.js?${cacheToken}"></script>
</body>
</html>