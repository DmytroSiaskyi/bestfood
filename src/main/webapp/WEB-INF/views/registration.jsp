<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="title"><span>Реєстрація</span></div>
<div id="centerLogin">
<div class="registr">
<form action="/registration" method="post" class="formReg">
  <p class="name">
    <input type="text" name="login" id="name" placeholder="Логін">
    <label for="name">Логін</label>
  </p>
  <p class="name">
    <input type="text" name="firstname" id="firstname" placeholder="Ім'я">
    <label for="name">Ім'я</label>
  </p>
  <p class="name">
    <input type="text" name="surname" id="surname" placeholder="Прізвище">
    <label for="name">Прізвище</label>
  </p>
  <p class="email">
    <input type="text" name="email" id="email" placeholder="mail@example.com">
    <label for="email">Email</label>
  </p>
  <p class="name">
    <input type="password" name="password" id="password" placeholder="Пароль">
    <label for="email">Пароль</label>
  </p>
  <p class="submit">
    <input type="submit" value="Зареєструватись">
  </p>
</form>
  ${error}
</div>
</div>