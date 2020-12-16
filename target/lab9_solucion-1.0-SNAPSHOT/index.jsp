<%@page import="Beans.Employee"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Ingreso al sistema</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/signin.css" rel="stylesheet">
</head>

<body class="text-center">
    <form class="form-signin" method="POST" action="<%=request.getContextPath()%>/LoginServlet">
        <h1 class="h3 mb-3 font-weight-normal">Ingreso al sistema HR</h1>
        <input type="text" name="inputEmail" class="form-control" placeholder="Correo" autofocus="">
        <input type="password" name="inputPassword" class="form-control" placeholder="Password">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</body>
</html>

