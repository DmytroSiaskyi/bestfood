<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Керування сторінками</title>
</head>
<body>
    <div class="title"><span>Керування сторінками</span></div>
    <div class="page-table">
        <table border="1" width="100%" cellpadding="5" bgcolor="#6495ed">
                    <th></th>
                    <th>Назва статті</th>
                    <th>Дата створення</th>
                    <th>Автор</th>
                    <th>Порядок статті</th>
                    <th>Стан публікації</th>
                    <th>Рівень доступу</th>
                    <th>Змінити</th>
                    <th>Редагувати</th>
                    <th>Видалити</th>
            <c:forEach var="pageInfo" items="${articleData}">
                <tr>
                    <td><input type="checkbox" name="choice" value="true"></td>
                    <td>
                        <c:choose>
                            <c:when test="${pageInfo.enable == true}">
                                <a href=/articles/view/${pageInfo.name}>${pageInfo.title}</a>
                            </c:when>
                            <c:otherwise>
                                ${pageInfo.title}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${pageInfo.created}</td>
                    <td>${pageInfo.author}</td>
                    <td>${pageInfo.orderId}</td>
                    <form action="/admin/management/access" method="post">
                    <td>
                        ${pageInfo.enable}
                        <c:if test="${pageInfo.enable == true}">
                            <input type="checkbox" name="enable" value="true" checked>
                        </c:if>
                        <c:if test="${pageInfo.enable == false}">
                            <input type="checkbox" name="enable" value="true">
                        </c:if>
                    </td>
                    <td>
                            <select name="role">
                                <option selected value="${pageInfo.access}%${pageInfo.id}">
                                        <c:if test="${pageInfo.access == 0}">
                                        ROLE_ADMIN
                                        </c:if>
                                        <c:if test="${pageInfo.access == 1}">
                                        ROLE_USER
                                        </c:if>
                                        <c:if test="${pageInfo.access == 2}">
                                        ROLE_ANONYMOUS
                                        </c:if>
                                </option>
                                <c:if test="${pageInfo.access != 0}">
                                    <option value="0%${pageInfo.id}">ROLE_ADMIN</option>
                                </c:if>
                                <c:if test="${pageInfo.access != 1}">
                                <option value="1%${pageInfo.id}">ROLE_USER</option>
                                </c:if>
                                <c:if test="${pageInfo.access != 2}">
                                <option value="2%${pageInfo.id}">ROLE_ANONYMOUS</option>
                                </c:if>
                            </select>
                    </td>
                    <td><input type="submit" value="Зберегти" id="smbt"></td>
                    </form>
                    <td><a href="/admin/management/edit?page=${pageInfo.id}"><img src="/static/image/ico/document_edit.png" class="table_ico" /></a></td>
                    <td><a href="/admin/management/delete?id=${pageInfo.id}"><img src="/static/image/ico/delete1.ico" class="table_ico" /></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
