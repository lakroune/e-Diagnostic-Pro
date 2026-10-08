<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des patients</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #ffffff;
            color: #111111;
        }

        .container {
            width: 90%;
            margin: 40px auto;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #000000;
            padding: 8px;
            text-align: center;
            vertical-align: top;
        }

        .action-link {
            display: inline-block;
            padding: 7px 10px;
            border: 1px solid #000000;
            text-decoration: none;
            color: #000000;
            background: #ffffff;
        }

        .alert {
            border: 1px solid #000000;
            padding: 10px;
            margin-bottom: 15px;
            background: #f5f5f5;
        }
    </style>
</head>
<body>
    <jsp:include page="menu.jsp" />

    <div class="container">
        <h1>Liste des patients</h1>

        <c:if test="${not empty errorMessage}">
            <div class="alert">${errorMessage}</div>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Date naissance</th>
                    <th>N° sécurité sociale</th>
                    <th>Téléphone</th>
                    <th>Adresse</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="patient" items="${patients}">
                    <tr>
                        <td>${patient.id}</td>
                        <td>${patient.nom}</td>
                        <td>${patient.prenom}</td>
                        <td>${patient.dateNaissance}</td>
                        <td>${patient.numSecuriteSociale}</td>
                        <td>${patient.telephone}</td>
                        <td>${patient.adresse}</td>
                        <td>
                            <a class="action-link" href="${pageContext.request.contextPath}/infirmier/patients/${patient.id}">Détails</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>