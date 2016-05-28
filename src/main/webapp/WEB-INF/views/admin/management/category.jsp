<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
  <title>Керування категоріями</title>
</head>
<body>
<div class="title"><span>Керування категоріями</span></div>
<div class="page-table">
  <table border="1" width="100%" cellpadding="5" bgcolor="#6495ed">
    <th>Id</th>
    <th>Назва</th>
    <th>Змінити назву</th>
    <th>Додати статтю</th>
    <th>Видалити категорію</th>
    <c:forEach var="category" items="${categoryList}">
      <tr>
        <td>${category.id}</td>
        <td><a href="/admin/management/category/manage?id=${category.id}">${category.name}</a></td>
        <td><a href="/admin/management/category/rename?id=${category.id}" onclick="call(${category.id})" class="answerMessage"><img src="/static/image/ico/document_edit.png" class="table_ico" /></a></td>
        <td><a href="/admin/management/category/addArticles?id=${category.id}"><img src="/static/image/ico/Add.ico" class="table_ico" /></a></td>
        <td><a href="/admin/management/category/delete?id=${category.id}"><img src="/static/image/ico/delete1.ico" class="table_ico" /></a></td>
      </tr>
    </c:forEach>
  </table>
</div>
<hr>
<div class="title"><span>Додати категорію</span></div>
  <form id="addCategory" action="/admin/management/category/add" method="post">
    <p>Нова категорія</p>
    <input type="text" name="name" placeholder="Назва"/>
    <p class="submit">
      <input type="submit" value="Зберегти" class="sButton"/>
  </form>
<br>
<div id="formRename" class="formm">
  <h2>Перейменувати категорію</h2>
  <div class="back">
    <form action="/admin/management/category/rename" method="post">
      <p>Id категорії</p>
    <input type="text" name="id" readonly>
      <p>Нова назва</p>
    <input type="text" name="text" placeholder="Назва"/>
    <p class="submit">
      <input type="submit" value="Зберегти" class="sButton"/> <input type="button" value="Відміна" class="cButton"/>
    </p>
    </form>
  </div>
</div>
<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script type="text/javascript" charset="UTF-8" src="/static/scripts/category.js?${cacheToken}"></script>
</body>
</html>
