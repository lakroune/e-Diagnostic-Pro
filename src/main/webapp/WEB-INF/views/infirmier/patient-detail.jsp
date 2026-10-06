<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Détails du Patient</title>
            <style>
                body {
                    margin: 0;
                    font-family: Arial, sans-serif;
                }

                .container {
                    width: 90%;
                    margin: 40px auto;
                }

                h1,
                h2 {
                    text-align: center;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                    margin-bottom: 20px;
                }

                th,
                td {
                    border: 1px solid black;
                    padding: 10px;
                    text-align: left;
                    vertical-align: top;
                }

                th {
                    width: 30%;
                    background: #f5f5f5;
                }

                p {
                    margin: 10px 0;
                }

                label {
                    display: block;
                    margin-bottom: 5px;
                }

                input,
                textarea,
                button,
                select {
                    width: 100%;
                    box-sizing: border-box;
                    padding: 8px;
                    border: 1px solid black;
                    font-family: Arial, sans-serif;
                }

                textarea {
                    min-height: 80px;
                    resize: vertical;
                }

                button {
                    background: white;
                    cursor: pointer;
                }

                form {
                    margin-top: 15px;
                }

                .form-grid {
                    display: grid;
                    grid-template-columns: repeat(2, minmax(250px, 1fr));
                    gap: 15px;
                }

                .full-width {
                    grid-column: 1 / -1;
                }

                .actions {
                    display: flex;
                    gap: 10px;
                    margin-top: 15px;
                }

                a {
                    color: black;
                }
            </style>
        </head>

        <body>

            <jsp:include page="menu.jsp" />

            <c:choose>
                <c:when test="${not empty patient}">
                    <div class="container">
                        <h1>Détails du Patient</h1>

                        <table>
                            <tr>
                                <th>ID</th>
                                <td>${patient.id}</td>
                            </tr>
                            <tr>
                                <th>Nom</th>
                                <td>${patient.nom}</td>
                            </tr>
                            <tr>
                                <th>Prénom</th>
                                <td>${patient.prenom}</td>
                            </tr>
                            <tr>
                                <th>Date de naissance</th>
                                <td>${patient.dateNaissance}</td>
                            </tr>
                            <tr>
                                <th>N° sécurité sociale</th>
                                <td>${not empty patient.numSecuriteSociale ? patient.numSecuriteSociale : 'Non
                                    renseigné'}</td>
                            </tr>
                            <tr>
                                <th>Téléphone</th>
                                <td>${not empty patient.telephone ? patient.telephone : 'Non renseigné'}</td>
                            </tr>
                            <tr>
                                <th>Adresse</th>
                                <td>${not empty patient.adresse ? patient.adresse : 'Non renseignée'}</td>
                            </tr>
                            <tr>
                                <th>Mutuelle</th>
                                <td>${not empty patient.mutuelle ? patient.mutuelle : 'Non renseignée'}</td>
                            </tr>
                            <tr>
                                <th>Antécédents</th>
                                <td>${not empty patient.antecedents ? patient.antecedents : 'Aucun'}</td>
                            </tr>
                            <tr>
                                <th>Allergies</th>
                                <td>${not empty patient.allergies ? patient.allergies : 'Aucune'}</td>
                            </tr>
                            <tr>
                                <th>Traitements en cours</th>
                                <td>${not empty patient.traitementsEnCours ? patient.traitementsEnCours : 'Aucun'}</td>
                            </tr>
                            <tr>
                                <th>Statut</th>
                                <td>
                                    <c:choose>
                                        <c:when test="${patient.enAttente}">En attente</c:when>
                                        <c:otherwise>Pris en charge</c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <th>Date d'enregistrement</th>
                                <td>${not empty patient.dateEnregistrement ? patient.dateEnregistrement : 'Non
                                    renseignée'}</td>
                            </tr>
                        </table>

                        <hr>

                        <h2>Historique des Signes Vitaux</h2>

                        <c:choose>
                            <c:when test="${not empty patient.signesVitaux}">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Tension</th>
                                            <th>Fréq. Cardiaque (bpm)</th>
                                            <th>Température (°C)</th>
                                            <th>Fréq. Respiratoire</th>
                                            <th>Poids (kg)</th>
                                            <th>Taille (cm)</th>
                                            <th>Infirmier</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="sv" items="${patient.signesVitaux}">
                                            <tr>
                                                <td>${sv.datePrise}</td>
                                                <td>${sv.tensionArterielle}</td>
                                                <td>${sv.frequenceCardiaque}</td>
                                                <td>${sv.temperatureCorporelle}</td>
                                                <td>${sv.frequenceRespiratoire}</td>
                                                <td>${sv.poidsKg}</td>
                                                <td>${sv.tailleCm}</td>
                                                <td>${sv.infirmier.nom} ${sv.infirmier.prenom}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p>Aucun signe vital enregistré pour ce patient.</p>
                            </c:otherwise>
                        </c:choose>

                </c:when>
                <c:otherwise>
                    <h1>Patient introuvable</h1>
                    <p>Aucun patient ne correspond à cet identifiant.</p>
                </c:otherwise>
            </c:choose>

            <hr>

            <p>
                <a href="${pageContext.request.contextPath}/infirmier/patients">← Retour à la liste des patients</a>
            </p>


        </body>

        </html>