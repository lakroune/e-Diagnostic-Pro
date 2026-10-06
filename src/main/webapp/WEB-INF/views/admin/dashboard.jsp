<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Dashboard Admin</title>
    </head>

    <body>

        <div class="container">

            <h1>Dashboard Admin</h1>

            <p>Bienvenue dans votre espace administrateur.</p>

            <div class="actions">
                <a class="btn" href="${pageContext.request.contextPath}/admin/utilisateurs/ajouter">
                    Ajouter un utilisateur
                </a>
                <a class="btn" href="${pageContext.request.contextPath}/logout">
                    Déconnexion
                </a>
            </div>

        </div>

    </body>

    </html>