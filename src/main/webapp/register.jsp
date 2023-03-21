<%--
  Created by IntelliJ IDEA.
  User: pentonzxc
  Date: 19.03.23
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="register" method="post">
    <%!
        public boolean addErrorMessage(HttpServletRequest request) {
            return request.getAttribute("error") != null;
        }
    %>

    <%= addErrorMessage(request) ? request.getAttribute("error") : "" %>

    <input type="text" name="username" placeholder="Username" id="username"/>
    <input type="text" name="password" placeholder="Password" id="password"/>
    <input type="submit" value="login">
</form>

</body>
</html>
