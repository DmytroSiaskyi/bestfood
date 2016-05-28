<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Керування опитуваннями</title>
</head>
<body>
<div class="title"><span>Керування опитуваннями</span></div>
<h3>Додати опитування</h3>
<form class="addPoll" action="/admin/management/poll/add" method="post" style="text-align: center;">
  <p class="order">
    <input type="text" name="question" id="value" placeholder="Запитання">
  </p>
  <p class="submit">
    <input type="submit" class="addNewPoll" value="Додати">
  </p>
</form>
<div class="page-table">
  <table id="pollTable" border="1" width="100%" cellpadding="5" bgcolor="#6495ed">
    <th>Номер</th>
    <th>Запитання</th>
    <th>Видалити</th>
    <c:forEach var="poll" items="${pollList}">
      <tr id="${poll.id}">
        <td>${poll.id}</td>
        <td><a href="/admin/management/poll/edit?id=${poll.id}">${poll.question}</a></td>
        <td><a href="/admin/management/poll/delete?id=${poll.id}"><img src="/static/image/ico/delete1.ico" class="table_ico" /></a></td>
      </tr>
    </c:forEach>
  </table>
</div>
<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script type="text/javascript" charset="UTF-8" src="/static/scripts/poll.js?${cacheToken}"></script>
</body>
</html>

