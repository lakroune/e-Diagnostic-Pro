<%@ page import="ma.youcode.model.Patient" %>
    <%@ page import="java.util.List" %>
        <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Patients</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        margin: 30px;
                    }

                    table {
                        border-collapse: collapse;
                        width: 60%;
                        margin-top: 20px;
                    }

                    th,
                    td {
                        border: 1px solid #ccc;
                        padding: 10px;
                        text-align: left;
                    }

                    form {
                        margin-top: 20px;
                    }

                    input {
                        margin: 5px 0;
                        display: block;
                        padding: 8px;
                        width: 250px;
                    }

                    button {
                        padding: 8px 16px;
                    }
                </style>
            </head>

            <body>
                <h1>Gestion des patients</h1>

                <form method="post" action="${pageContext.request.contextPath}/patients">
                    <label>Nom</label>
                    <input type="text" name="nom" required>

                    <label>Prénom</label>
                    <input type="text" name="prenom" required>

                    <label>Téléphone</label>
                    <input type="text" name="telephone">

                    <button type="submit">Ajouter</button>
                </form>

                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Téléphone</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<Patient> patients = (List<Patient>) request.getAttribute("patients");
                                if (patients != null) {
                                for (Patient patient : patients) {
                                %>
                                <tr>
                                    <td>
                                        1
                                    </td>
                                    <td>
                                        <%= patient.getNom() %>
                                    </td>
                                    <td>
                                        <%= patient.getPrenom() %>
                                    </td>
                                    <td>
                                        <%= patient.getTelephone() %>
                                    </td>
                                </tr>
                                <% } } %>
                    </tbody>
                </table>
            </body>

            </html>