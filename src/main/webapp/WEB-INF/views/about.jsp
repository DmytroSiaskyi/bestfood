<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Best food - Про нас</title>
</head>
<body>
    <div class="title"><span>Про нас</span></div>
    <div class = "wButton"><section><a href="/poll" class="button"><button id="messageButton" type="button">Опитування</button></a></section></div>
    <br>
    <div id="slideFrequencyData" style="display:none;">${frequency}</div>
    <div class="slider" id="main-slider"><!-- outermost container element -->
        <div class="slider-wrapper"><!-- innermost wrapper element -->
            <c:forEach var="slide" items="${slideList}">
                <div class="slide" data-image="${slide.imageUrl}"><div class="slideData"><a href="${slide.link}">${slide.text}</a></div></div><!-- slides -->
            </c:forEach>
        </div>
        <div class="slider-nav"><!-- "Previous" and "Next" actions -->
            <button class="slider-previous">Previous</button>
            <button class="slider-next">Next</button>
        </div>
    </div>
    <p>
        Правильне харчування допомагає людині зберігати працездатність, уникати різних захворювань,
        підтримувати нормальну вагу, збільшити тривалість життя. На здорове харчування потрібно переходити
        поступово, щоб це не було стресом для організму. В будь-якому віці не пізно перейти на правильне здорове харчування.
    </p>
    <img src="/static/image/healthMenu2.jpg" class="centerImage">
    <p>
        Дана сторінка призначена для поширення інформації та порад для приготування здорової їжі, та організації здорового харчування.
        Тут люди можуть ділитись своїм досвідом та здобувати новий.
    </p>
    <div class="title"><span>Автор</span></div>
    <div class="aboutCenter"><p>студент групи ІС-32, ФІОТ<br>Сяський Дмитро<p/><br></div>
    <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <script type="text/javascript" charset="UTF-8" src="/static/scripts/slideShow.js?${cacheToken}"></script>
</body>
</html>
