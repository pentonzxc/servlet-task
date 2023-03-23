<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome page</title>
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
    <input class="form-control btn-secondary" type="submit" value="Авторизация" onclick="window.location='login.jsp'"/>
    <input class="form-control btn-secondary" type="submit" value="Регистрация" onclick="window.location='register.jsp'"/>
    <input class="form-control btn-secondary" type="submit" value="Домашняя страница" onclick="window.location='home.jsp'"/>
</div>
</body>
</html>