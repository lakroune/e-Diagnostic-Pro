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

            .container {
                width: 80%;
                margin: 100px auto;
                text-align: center;
            }

            h1 {
                margin-bottom: 10px;
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

            <h1>Dashboard Admin</h1>

            <p>Bienvenue dans votre espace administrateur.</p>

            <a class="logout" href="${pageContext.request.contextPath}/logout">
                Déconnexion
            </a>

        </div>

    </body>

    </html>