<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Dashboard Infirmier</title>

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
        </style>
    </head>

    <body>

        <nav class="menu">

            <a href="${pageContext.request.contextPath}/infirmier/dashboard">
                Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/infirmier/patients/ajouter">
                Ajouter un patient
            </a>

            <a href="${pageContext.request.contextPath}/infirmier/patients">
                Liste des patients
            </a>

            <a href="${pageContext.request.contextPath}/infirmier/attente">
                Liste d'attente
            </a>

            <a href="${pageContext.request.contextPath}/logout">
                Déconnexion
            </a>

        </nav>

        <div class="container">

            <h1>Dashboard Infirmier</h1>

            <p>Bienvenue dans votre espace infirmier.</p>

        </div>

    </body>

    </html>