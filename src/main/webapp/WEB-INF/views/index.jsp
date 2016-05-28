<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>BestFood - Головна</title>
</head>
<body>

    <div class="title"><span>Свіжі новини</span></div>
    <div class="news">
        <ul class="news">
            <c:forEach var="n" items="${newsList}">
                <li>
                    <div class="news_name">
                            ${n.title}
                    </div>
                    <div class="photo">
                        <img src="${n.photoRef}" class="news_photo" />
                    </div>
                    <div class="news_text">
                            ${n.text}
                    </div>
                    <br>
                    <hr/>
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
