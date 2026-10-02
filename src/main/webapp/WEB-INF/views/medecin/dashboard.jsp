<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Médecin</title>

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .container {
            width: 80%;
            margin: 100px auto;
            text-align: center;
        }

        .logout {
            display: inline-block;
            padding: 10px 20px;
            border: 1px solid black;
            text-decoration: none;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Dashboard Médecin Généraliste</h1>

    <p>Bienvenue dans votre espace médecin généraliste.</p>

    <a class="logout"
       href="${pageContext.request.contextPath}/logout">
        Déconnexion
    </a>

</div>

</body>
</html>