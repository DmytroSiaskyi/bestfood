<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
  <title>Керування категорією</title>
</head>
<body>
<div class="title"><span>Додавання статей "${catName}"</span></div>
<div class="page-table">
  <table border="1" width="100%" cellpadding="5" bgcolor="#6495ed">
    <th>Назва</th>
    <th>Автор</th>
    <th>Дата створення</th>
    <th>Дадати до категорії</th>
    <c:forEach var="article" items="${articleData}">
      <tr>
        <td>${article.title}</td>
        <td>${article.author}</td>
        <td>${article.created}</td>
        <td><a href="/admin/management/category/addArticle?articleId=${article.id}&categoryId=${category}"><img src="/static/image/ico/Add.ico" class="table_ico" /></a></td>
      </tr>
    </c:forEach>
  </table>
</div>

</body>
</html>


