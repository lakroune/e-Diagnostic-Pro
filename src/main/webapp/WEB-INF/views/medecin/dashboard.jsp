<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Dashboard Médecin</title>

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            color: #111;
        }

        .menu {
            border-bottom: 1px solid #111;
            padding: 18px 20px;
            text-align: center;
            background: white;
        }

        .menu a {
            margin: 0 12px;
            text-decoration: none;
            color: #111;
            font-weight: 600;
        }

        .container {
            width: 92%;
            max-width: 1200px;
            margin: 40px auto;
        }

        h1 {
            margin-bottom: 10px;
        }

        .table-wrap {
            background: white;
            border: 1px solid #ddd;
            overflow: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            min-width: 900px;
        }

        th, td {
            padding: 12px 14px;
            border-bottom: 1px solid #ddd;
            text-align: left;
            vertical-align: top;
        }

        th {
            background: #f1f1f1;
            font-weight: 700;
        }

        .empty {
            background: white;
            border: 1px solid #ddd;
            padding: 18px;
            text-align: center;
            color: #555;
        }

        .dossier-btn {
            display: inline-block;
            background: #111827;
            color: #fff;
            text-decoration: none;
            border: 1px solid #111827;
            padding: 8px 14px;
            border-radius: 4px;
            font-weight: 600;
        }

        .dossier-btn:hover {
            background: #1f2937;
        }
    </style>

</head>

<body>

    <jsp:include page="menu.jsp" />

    <div class="container">
        <h1>Patients en attente</h1>

        <c:choose>
            <c:when test="${not empty patientsEnAttente}">
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Nom</th>
                                <th>Prénom</th>
                                <th>Heure d'arrivée</th>
                                <th>Signes vitaux</th>
                                <th>N° sécurité sociale</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="patient" items="${patientsEnAttente}">
                                <tr>
                                    <td>${patient.nom}</td>
                                    <td>${patient.prenom}</td>
                                    <td>
                                        <c:forEach var="fileAttente" items="${patient.fileAttentes}">
                                            <c:if test="${fileAttente.statut eq 'EN_ATTENTE'}">
                                                ${fileAttente.heureArrivee.toLocalTime()}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty patient.signesVitaux}">
                                                <c:set var="dernierSigne" value="${patient.signesVitaux[patient.signesVitaux.size() - 1]}" />
                                                ${dernierSigne.tensionArterielle} / ${dernierSigne.frequenceCardiaque} bpm / ${dernierSigne.temperatureCorporelle}°C
                                            </c:when>
                                            <c:otherwise>—</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${patient.numSecuriteSociale}</td>
                                    <td>
                                        <a class="dossier-btn" href="${pageContext.request.contextPath}/medecin/dossier/${patient.id}">Dossier</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <div class="empty">
                    Aucun patient en attente pour le moment.
                </div>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>