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
            background: #ffffff;
            color: #111111;
        }

        .container {
            width: 85%;
            margin: 50px auto;
            text-align: center;
        }

        h1 {
            margin-bottom: 20px;
        }

        .box {
            border: 1px solid #000000;
            padding: 20px;
            background: #f7f7f7;
        }
    </style>
</head>
<body>
    <table width="100%" border="0" cellpadding="8" cellspacing="0">
        <tr>
            <td bgcolor="#f2f2f2" valign="middle">
                <font face="Arial, Helvetica, sans-serif" size="2">
                    <b>Menu Admin :</b>
                    <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                    |
                    <a href="${pageContext.request.contextPath}/admin/utilisateurs/ajouter">Ajouter un utilisateur</a>
                    |
                    <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
                </font>
            </td>
        </tr>
    </table>
    <hr>

    <div class="container">
        <h1>Dashboard Admin</h1>
        <div class="box">
            <p>Bienvenue dans votre espace administrateur.</p>
        </div>
    </div>
</body>
</html>