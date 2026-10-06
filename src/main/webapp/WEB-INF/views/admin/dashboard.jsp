<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Dashboard Admin</title>

        <style>
            body {
                margin: 0;
                font-family: Arial, sans-serif;
            }

            .menu {
                border-bottom: 1px solid black;
                padding: 20px;
                text-align: center;
            }

            .menu a {
                margin: 0 15px;
                text-decoration: none;
                color: black;
            }

            .container {
                width: 80%;
                margin: 100px auto;
                text-align: center;
            }

            .btn {
                display: inline-block;
                padding: 10px 20px;
                border: 1px solid black;
                text-decoration: none;
                color: black;
                background: white;
            }
        </style>
    </head>

    <body>

        <nav class="menu">
            <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin/utilisateurs/ajouter">Ajouter un utilisateur</a>
            <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
        </nav>

        <div class="container">

            <h1>Dashboard Admin</h1>

            <p>Bienvenue dans votre espace administrateur.</p>

        </div>

    </body>

    </html>