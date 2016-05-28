<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="loggedInUser" property="principal"/>
<html>
<head>
  <title>Статті</title>
</head>
<body>
<div class="article-list">
  <div class="title"><span>Статті</span></div>
  <security:authorize access="hasRole('ROLE_USER')">
    <div class = "wButton"><section><a href="/articles/offer" class="button"><button id="messageButton" type="button">Запропонувати статтю</button></a></section></div>
  </security:authorize>
  <ul class = "article_ul">
    <c:forEach var="category" items="${categoriesList}">
      <li>
          <div class="prev_title" onclick="display('${category.id}')">${category.name}</div>
          <hr>
          <ul class="categoryList" id="${category.id}"></ul>
      </li>
    </c:forEach>
  </ul>
</div>
<div id="viewBox">
  <div class="title"></div>
  <div class = "article_text"></div>
  <hr>
  <div class="about_article">
    <div class="author"></div>
    <div class="created"></div>
  </div>
</div>
<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script type="text/javascript" charset="UTF-8" src="/static/scripts/article.js?${cacheToken}"></script>
<script type="text/javascript" charset="UTF-8" src="/static/scripts/articleShow.js?${cacheToken}"></script>
</body>

