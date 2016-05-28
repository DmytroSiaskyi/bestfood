<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Додавання статті</title>
</head>
<body>
<div class="title"><span>Додавання статті</span></div>
<div class="article-edit">
  <form action="/articles/offer" method="post" class="formEdit">
    <p>Назва статті</p>
    <textarea name="title">${title}</textarea><br>
    <p>Зміст статті</p>
    <textarea name="text">${content}</textarea><br>
    <p class="submit">
      <input type="submit"  value="Відправити">
      <input type="submit" name="submit" formtarget="_blank" value="Попередній огляд">
    </p>
  </form>
</div>
</body>

