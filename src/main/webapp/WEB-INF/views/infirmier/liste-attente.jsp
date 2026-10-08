<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste d'attente</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #ffffff;
            color: #111111;
        }

        .container {
            width: 85%;
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
            padding: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="menu.jsp" />

    <div class="container">
        <h1>Liste d'attente</h1>

        <table>
            <thead>
                <tr>
                    <th>Position</th>
                    <th>Patient</th>
                    <th>N° sécurité sociale</th>
                    <th>Date d'enregistrement</th>
                    <th>Statut</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty listeAttente}">
                        <c:forEach var="patient" items="${listeAttente}" varStatus="loop">
                            <tr>
                                <td>${loop.count}</td>
                                <td>${patient.nom} ${patient.prenom}</td>
                                <td>${not empty patient.numSecuriteSociale ? patient.numSecuriteSociale : 'Non renseigné'}</td>
                                <td>${not empty patient.dateEnregistrement ? patient.dateEnregistrement : 'Non renseignée'}</td>
                                <td>En attente</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="5">Aucun patient dans la liste d'attente.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>