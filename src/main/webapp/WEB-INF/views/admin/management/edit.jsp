<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Редагування статті</title>
</head>
<body>
  <div class="title"><span>Редагування статті</span></div>
  <div class="article-edit">
     <form action="/admin/management/edit" method="post" class="formma">
         <p>Номер статті</p>
         <input type="text" name="id" value="${article.id}" readonly><br>
         <p>Порядок статті</p>
         <input type="text" name="orderId"  value="${article.orderId}">
         <p>Назва статті</p>
         <input type="text" name="name"  value="${article.name}">
          <p>Заголовок статті</p>
        <textarea name="title">${article.title}</textarea><br>
          <p>Зміст статті</p>
        <textarea name="text">${article.content}</textarea><br>
         <p>Короткий огляд</p>
         <textarea name="preview">${article.preview}</textarea><br>
          <p>Автор</p>
        <textarea name="author">${article.author}</textarea><br>
          <br><hr>
         <p class="submit">
             <input type="submit"  value="Зберегти зміни">
             <input type="submit" name="submit" formtarget="_blank" value="Попередній огляд">
         </p>
    </form>
  </div>
</body>
</html>
