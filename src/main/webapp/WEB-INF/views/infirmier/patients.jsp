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

                <h1>Liste des patients</h1>

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

                            </tr>

                        </c:forEach>

                    </tbody>

                </table>

            </div>

        </body>

        </html>