<%@ page contentType="text/html; charset=UTF-8"%>
<%  request.setCharacterEncoding("UTF-8");
    String userName = (String)request.getAttribute("user"); %>
<div class="thanks">Дякуємо,  <%=userName%>! Ваше повідомлення успішно відправлено.<br><br>
</div>