<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Керування опитуванням</title>
</head>
<body>
<div class="title"><span>Керування опитуванням</span></div>
<h3>Додавання відповіді</h3>
<form class="addAnswer" action="/admin/management/poll/add/answer?id=${poll.id}" method="post" style="text-align: center;">
  <p>Опитування номер: <div id="pollId"> ${poll.id}</div></p>
  <p class="order">
    <input type="text" name="answer" id="value" placeholder="Відповідь">
  </p>
  <p class="submit">
    <input type="submit" class="addNewPoll" value="Додати">
  </p>
</form>
<div class="page-table">
  <table id="pollTable" border="1" width="100%" cellpadding="5" bgcolor="#6495ed">
    <th>Відповідь</th>
    <th>Кількість голосів</th>
    <th>Видалити</th>
    <c:forEach var="answer" items="${poll.answerList}">
      <tr id="${answer.id}">
        <td>${answer.text}</td>
        <td>${answer.selected}</td>
        <td><a href="/admin/management/poll/delete/question?id=${answer.id}&poll=${poll.id}"><img src="/static/image/ico/delete1.ico" class="table_ico" /></a></td>
      </tr>
    </c:forEach>
  </table>
  <br>
</div>
<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script type="text/javascript" charset="UTF-8" src="/static/scripts/pollAnswer.js?${cacheToken}"></script>
</body>
</html>

