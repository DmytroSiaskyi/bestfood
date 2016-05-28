<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><decorator:title/></title>
    <link rel="icon" href="/static/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/static/css/style.css">
    <script type="text/javascript" src="/static/scripts/jquery-1.11.1.min.js"></script>
</head>
<body>
<security:authorize access="isAnonymous()">
    <a href="/login?form=1"><div class="authent_button">Вхід</div></a>
</security:authorize>
<security:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
    <a href="/logout"><div class="authent_button">Вихід</div></a>
</security:authorize>
<header class="main_page">
    <h2 class = "header-title">Best food</h2>
    <h3 class = "header_title">the most affordable and the most delicious food</h3>
    <div class = "menu-strip">
        <ul class = "header-menu">
            <li><a href="/"><div class="center_item">Головне меню</div></a></li>
            <li><a href="/admin/management/pages"><div class="center_item">Cторінки</div></a></li>
            <li><a href="/admin/management/messages"><div class="center_item">Повідомлення</div></a></li>
            <li><a href="/admin/management/category"><div class="center_item">Категорії</div></a></li>
            <li><a href="/admin/management/slides"><div class="center_item">Слайд-шоу</div></a></li>
            <li><a href="/admin/management/poll"><div class="center_item">Опитування</div></a></li>
        </ul>
    </div>
</header>
<div class="background"></div>
<div id="Boody">
    <decorator:body/>
</div>
<div id="Footer">
    <div id="Footer-center">
        Сайт є інформаційною сторінкою,<br>
        призначеною для поширення інформації про здорове харчування<br><br>
        © Best food 2015
    </div>
</div>
</body>
</html>
