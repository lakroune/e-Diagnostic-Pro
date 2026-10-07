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

                .action-link {
                    color: black;
                    text-decoration: none;
                    border: 1px solid black;
                    padding: 8px 12px;
                    display: inline-block;
                }
            </style>
        </head>

        <body>

            <jsp:include page="menu.jsp" />

            <div class="container">

                <h1>Liste des patients</h1>

                <c:if test="${not empty errorMessage}">
                    <div style="margin: 15px 0; padding: 12px 14px; border: 1px solid #d1d5db; background: #fff7ed; color: #92400e; font-weight: bold;">
                        ${errorMessage}
                    </div>
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