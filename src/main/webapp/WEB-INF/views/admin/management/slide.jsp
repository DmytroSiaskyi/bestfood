<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Керування слайдами</title>
</head>
<body>
<div class="title"><span>Керування слайдами</span></div>
<div class="page-table">
  <table border="1" width="100%" cellpadding="5" bgcolor="#6495ed">
    <th>Текст слайду</th>
    <th>Посиланян</th>
    <th>Зображення</th>
    <th>Порядок слайду</th>
    <th>Видалити</th>
    <c:forEach var="slide" items="${slidesList}">
      <tr>
        <td>${slide.text}</td>
        <td>${slide.link}</td>
        <td>${slide.imageUrl}</td>
        <td>${slide.orderId}</td>
        <td><a href="/admin/management/slides/delete?id=${slide.id}"><img src="/static/image/ico/delete1.ico" class="table_ico" /></a></td>
      </tr>
    </c:forEach>
  </table>
</div>
<h2 onclick="my_f('adding')">Додавання слайду</h2>
<div id="adding" class="addSlide" style="display: none;">
  <form class="addingSlide" action="/admin/management/slides/add" method="post" style="text-align: center;">
    <p class="text">
      <textarea name="text" placeholder="Текст слайду">${newSlide.text}</textarea>
    </p>
    <p class="text">
      <textarea name="link" placeholder="Посилання">${newSlide.link}</textarea>
    </p>
    <p class="text">
      <textarea name="image" placeholder="Зображення">${newSlide.imageUrl}</textarea>
    </p>
    <p class="order">
      <input type="text" name="order" id="name" placeholder="order">
    </p>
    <p class="submit">
      <input type="submit" value="Додати">
    </p>
    <p>${error}</p>
  </form>
</div>
<h2 onclick="my_f('setFreq')">Задати частоту</h2>
<div class="setFreq" id="setFreq" style="display: none;">
  <form class="addingSlide" action="/admin/management/slides/setfrequency" method="post" style="text-align: center;">
    <p class="order">
      <input type="text" name="freq" id="value" placeholder="Частота">
    </p>
    <p class="submit">
      <input type="submit" value="Задати">
    </p>
  </form>
</div>
<script>
  function my_f(objName) {
    var object = document.getElementById(objName);
    object.style.display == 'none' ? object.style.display = 'block' : object.style.display = 'none'
  }
</script>

</body>
</html>
