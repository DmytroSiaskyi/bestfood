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

</body>