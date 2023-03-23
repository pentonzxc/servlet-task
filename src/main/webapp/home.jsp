<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <form class="form-group" action="users" method="get">
        <input
                class="form-control btn-secondary"
                type="submit"
                value="Таблица"
        />
    </form>
    <form class="form-group" action="logout" method="get">
        <input class="form-control btn-secondary" type="submit" value="Выйти"/>
    </form>
</div>


</body>
</html>
