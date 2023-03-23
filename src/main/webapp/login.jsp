<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style type="text/css">
        html, body {
            height: 100%;
            background-color: wheat;
        }
    </style>
    <%!
        public boolean addErrorMessage(HttpServletRequest request) {
            return request.getAttribute("error") != null;
        }
    %>
</head>
<body>
<button class="btn-primary rounded-3" onclick="window.location='index.jsp'">Back to welcome page</button>
<div
        class="container h-100 w-25 d-flex flex-column justify-content-center gap-3"
>
    <form action="login" method="post">
        <div class="card border-dark">
            <div class="card-body">
                <h5 class="card-title text-center mb-4">Login page</h5>
                <h6 class=" text-danger text-center">
                    <%= addErrorMessage(request) ? request.getAttribute("error") : "" %>
                </h6>
                <div class="form-group mb-2">
                    <label for="someUsername">Username</label>
                    <input
                            type="text"
                            class="form-control"
                            id="someUsername"
                            name="username"
                            aria-describedby="nameHelp"
                            placeholder="Enter username"
                    />
                </div>
                <div class="form-group mb-3">
                    <label for="somePassword">Password</label>
                    <input
                            type="text"
                            class="form-control"
                            name="password"
                            id="somePassword"
                            placeholder="Password"
                    />
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </div>
    </form>
</div>


<%--<form action="login" method="post">--%>
<%--    --%>

<%--    <input type="text" name="username" placeholder="Username" id="username"/>--%>
<%--    <input type="text" name="password" placeholder="Password" id="password"/>--%>
<%--    <input type="submit" value="login">--%>
<%--</form>--%>
</body>
</html>
