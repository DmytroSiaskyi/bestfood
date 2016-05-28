<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Керування повідомленнями</title>
</head>
<body>
    <div class="title"><span>Керування повідомленнями</span></div>
    <div class="page-table">
        <table border="1" width="100%" cellpadding="5" bgcolor="#6495ed">
            <th>Дата відправлення</th>
            <th>Автор</th>
            <th>Пошта</th>
            <th>ІР Адреса</th>
            <th>Текст</th>
            <th>Відповідь</th>
            <th>Відповісти</th>
            <th>Видалити</th>
            <c:forEach var="message" items="${messageList}">
                <tr>
                    <td>${message.created}</td>
                    <td>${message.login}</td>
                    <td>${message.email}</td>
                    <td>${message.IP}</td>
                    <td>${message.comment}</td>
                    <td><c:if test="${message.answer!=null}">
                        +
                    </c:if></td>
                    <td>
                        <a href="/admin/management/answer" onclick="call(${message.id})" class="answerMessage"><img src="/static/image/ico/document_edit.png" class="table_ico" /></a>
                    </td>
                    <td><a href="/admin/management/deleteMessage?id=${message.id}"><img src="/static/image/ico/delete1.ico" class="table_ico" /></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="form" class="formm">
        <h2>Написати відповідь</h2>
        <div class="back">
            <input type="text" name="text" placeholder="Відповідь"/>
            <p class="submit">
                <input type="button" value="Відправити" class="sButton"/> <input type="button" value="Відміна" class="cButton"/>
            </p>
        </div>
    </div>
    <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <script type="text/javascript" charset="UTF-8" src="/static/scripts/answer.js?${cacheToken}"></script>
</body>
</html>
