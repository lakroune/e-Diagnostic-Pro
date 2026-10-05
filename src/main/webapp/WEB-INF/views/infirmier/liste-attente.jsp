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
                }

                .container {
                    width: 80%;
                    margin: 70px auto;
                }

                h1 {
                    text-align: center;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 30px;
                }

                th,
                td {
                    border: 1px solid black;
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
                            <th>CIN</th>
                            <th>Date</th>
                            <th>Statut</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty listeAttente}">
                                <c:forEach var="item" items="${listeAttente}" varStatus="loop">
                                    <tr>
                                        <td>${loop.count}</td>
                                        <td>${item.patient.nom} ${item.patient.prenom}</td>
                                        <td>${item.patient.cin}</td>
                                        <td>${item.dateCreation}</td>
                                        <td>${item.statut}</td>
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