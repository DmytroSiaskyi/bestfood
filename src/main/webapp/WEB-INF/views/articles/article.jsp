<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="loggedInUser" property="principal"/>
<html>
<head>
  <title>${article.title}</title>
</head>
<body>
<div class="title"><span>${article.title}</span></div>
<div class = "article_text">
  ${article.content}
</div>
<hr>
<div class="about_article">
  <div class="author">
    ${article.author}
  </div>
  <div class="created">
    ${article.created}
  </div>
</div>
<h2>Коментарі</h2>
<hr>
<security:authorize access="hasRole('ROLE_USER')">
  <h3>Додати коментарій</h3>
  <form id="articleComment" action="/articles/${article.name}/comment" method="post">
    <p></p><textarea name="text" placeholder="Текст коментарія">${commentText}</textarea></p>
    <p class="submit"><input type="submit" value="Коментувати"></p>
    <p class="commentError">${error}</p>
  </form>
</security:authorize>
<hr>
<ul class="comments">
  <c:forEach var="comment" items="${comments}">
    <li id="${comment.id}">
      <div class="comment">
        <security:authorize access="hasRole('ROLE_ADMIN')">
          <a class="deleteButton" href="/articles/${article.name}/comment/delete?id=${comment.id}" ><img src="/static/image/ico/delete1.ico" class="table_ico" /> Видалити коментарій </a>
        </security:authorize>
        <p id="comment_date"> Дата: ${comment.created}</p>
        <p>Автор: ${comment.author}</p>
        <p>Текст: ${comment.text}</p>
      </div>
      <br>
    </li>
  </c:forEach>
</ul>
<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script type="text/javascript" charset="UTF-8" src="/static/scripts/article.js?${cacheToken}"></script>
</body>