<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rapport d'état</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            color: #333;
        }
        .box {
            border: 1px solid #ddd;
            padding: 20px;
            max-width: 800px;
            background: #fafafa;
        }
        .title {
            font-size: 22px;
            font-weight: bold;
            margin-bottom: 15px;
        }
        .label {
            font-weight: bold;
        }
        a {
            color: #0066cc;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="box">
        <div class="title">Type</div>
        <div>Rapport d'état</div>

        <div class="title" style="margin-top:20px;">Message</div>
        <div>La ressource demandée [<b>${pageContext.request.contextPath}/specialiste/demande/${param.id}</b>] n'est pas disponible.</div>

        <div class="title" style="margin-top:20px;">Description</div>
        <div>La ressource demandée n'est pas disponible.</div>

        <div style="margin-top:25px;">
            <a href="${pageContext.request.contextPath}/specialiste/dashboard">Retour au dashboard</a>
        </div>
    </div>
</body>
</html>
