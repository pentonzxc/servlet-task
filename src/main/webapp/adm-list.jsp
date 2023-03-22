<%--
  Created by IntelliJ IDEA.
  User: pentonzxc
  Date: 19.03.23
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>

    </title>
</head>
<body>
<div class="row">

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <%--        <div class="container text-left">--%>
        <%--            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add--%>
        <%--                New User</a>--%>
        <%--        </div>--%>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Country</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.country}"/></td>
                    <td>
<%--                        <a href="edit?id=<c:out value='${user.id}' />">Edit</a>--%>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>
</body>
</html>
