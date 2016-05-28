<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="title"><span>Написати повідомлення</span></div>
<%
  request.setCharacterEncoding("UTF-8");
  String user = (String)request.getAttribute("user");
  String email = (String)request.getAttribute("email");
  String error = (String)request.getAttribute("error");
  String message = (String)request.getAttribute("text");
%>
<div class="back">
  <div class="error">
    <%
      if(error!=null && !error.isEmpty()){
    %>
    <%=error%><%}%>
  </div>
  <form action="/forum/write" method="post" class="forms">
    <p class="name">
      <input type="text" name="name" id="name" placeholder="Логін"
        <%if(user!=null && !user.isEmpty()){%> value="<%=user%>"<%}%>>
      <label for="name">Логін</label>
    </p>
    <p class="email">
      <input type="text" name="email" id="email" placeholder="mail@example.com"
        <%if(email!=null && !email.isEmpty()){%> value="<%=email%>"<%}%>>
      <label for="email">Email</label>
    </p>
    <p class="text">
      <textarea name="text" placeholder="Текст повідомлення"><%if(message!=null){%><%=message%><%}%></textarea>
    </p>
    <p class="submit">
      <input type="submit" value="Відправити">
    </p>
  </form>
</div>