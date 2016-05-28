<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BestFood - Вхід</title>
</head>
<body>
<div class="title"><span>Вхід</span></div>
<div id="centerLogin">
<div class="login_form">
<form id="login-form" name="f" method="POST" action="/j_spring_security_check?spring-security-redirect=${redirect}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" style="display: none;"/>
    <input type="submit" style="display: none;">
    Логін:
    <input type="text" value="${userName}" name="j_username" id="username" class="input" autofocus><br><br>
    Пароль:
    <input type="password" name="j_password" id="password" class="input"><br>
    </table>
    <br>
    <div class="submit-holder">
        <div class="login-button" onclick="document.getElementById('login-form').submit()">Авторизуватись</div>
    </div>
    <br>
    <div class="submit-holder">
        <a href="/registration"><div class="login-button">Реєстрація</div></a>
    </div>
    <c:choose>
        <c:when test="${err}">
            <div class="login-error">
                <s:message code="login-box.error"/>
            </div>
        </c:when>
    </c:choose>
</form>
</div></div>
</body>
</html>