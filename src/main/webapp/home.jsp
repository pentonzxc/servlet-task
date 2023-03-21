<%--
  Created by IntelliJ IDEA.
  User: pentonzxc
  Date: 21.03.23
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style type="text/css">
        html, body {
            height: 100%;
            background-color: wheat;
        }
    </style>
</head>
<body>

<div class="container h-100 w-25 d-flex flex-column justify-content-center gap-3">
    <form action="users" method="get">
        <input class="btn-secondary" type="submit" value="Таблица"/>
    </form>
    <form action="logout" method="get">
        <input class="btn-secondary" type="submit" value="Выйти"/>
    </form>
</div>


</body>
</html>
