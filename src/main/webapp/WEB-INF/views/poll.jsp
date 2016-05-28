<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Best food - Опитування</title>
</head>
<body>
<div class="title"><span>Опитування</span></div>
  <div class="poll">
    <c:forEach var="poll" items="${pollList}">
      <h3>${poll.question}</h3>
      <ul class="pollUl">
        <li>
          <ul class="answerUl" id="${poll.id}ul">
          <c:forEach var="answer" items="${poll.answerList}">
            <li onclick="poll(${answer.id}, event, ${poll.id})">${answer.text}<div id="${answer.id}" class="selected">${answer.selected}</div></li>
          </c:forEach>
          </ul>
        </li>
      </ul>
    </c:forEach>
  </div>
<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<script type="text/javascript" charset="UTF-8" src="/static/scripts/poll.js?${cacheToken}"></script>
</body>
</html>
