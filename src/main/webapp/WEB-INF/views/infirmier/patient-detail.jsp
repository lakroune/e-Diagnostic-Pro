<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détails du patient</title>
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

        h1, h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }

        th, td {
            border: 1px solid #000000;
            padding: 8px;
            text-align: left;
            vertical-align: top;
        }

        th {
            background: #f3f3f3;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 8px 12px;
            border: 1px solid #000000;
            text-decoration: none;
            color: #000000;
            background: #ffffff;
        }

        .empty-state {
            margin-top: 15px;
            font-style: italic;
        }
    </style>
</head>
<body>
    <jsp:include page="menu.jsp" />

    <c:choose>
        <c:when test="${not empty patient}">
            <div class="container">
                <h1>Détails du patient</h1>

                <table>
                    <tr>
                        <th>Nom</th>
                        <td>${patient.nom}</td>
                        <th>Prénom</th>
                        <td>${patient.prenom}</td>
                    </tr>
                    <tr>
                        <th>Date de naissance</th>
                        <td>${patient.dateNaissance}</td>
                        <th>N° sécurité sociale</th>
                        <td>${not empty patient.numSecuriteSociale ? patient.numSecuriteSociale : 'Non renseigné'}</td>
                    </tr>
                    <tr>
                        <th>Téléphone</th>
                        <td>${not empty patient.telephone ? patient.telephone : 'Non renseigné'}</td>
                        <th>Adresse</th>
                        <td>${not empty patient.adresse ? patient.adresse : 'Non renseignée'}</td>
                    </tr>
                    <tr>
                        <th>Mutuelle</th>
                        <td>${not empty patient.mutuelle ? patient.mutuelle : 'Non renseignée'}</td>
                        <th>Statut</th>
                        <td>
                            <c:set var="patientStatus" value="Aucun statut" />
                            <c:forEach var="fileAttente" items="${patient.fileAttentes}">
                                <c:if test="${fileAttente.statut eq 'EN_ATTENTE'}">
                                    <c:set var="patientStatus" value="En attente" />
                                </c:if>
                                <c:if test="${fileAttente.statut eq 'PRIS_EN_CHARGE'}">
                                    <c:set var="patientStatus" value="Pris en charge" />
                                </c:if>
                            </c:forEach>
                            ${patientStatus}
                        </td>
                    </tr>
                    <tr>
                        <th>Antécédents</th>
                        <td colspan="3">${not empty patient.antecedents ? patient.antecedents : 'Aucun'}</td>
                    </tr>
                    <tr>
                        <th>Allergies</th>
                        <td colspan="3">${not empty patient.allergies ? patient.allergies : 'Aucune'}</td>
                    </tr>
                    <tr>
                        <th>Traitements en cours</th>
                        <td colspan="3">${not empty patient.traitementsEnCours ? patient.traitementsEnCours : 'Aucun'}</td>
                    </tr>
                    <tr>
                        <th>Date d'enregistrement</th>
                        <td colspan="3">${not empty patient.dateEnregistrement ? patient.dateEnregistrement : 'Non renseignée'}</td>
                    </tr>
                </table>

                <h2>Historique des signes vitaux</h2>

                <c:choose>
                    <c:when test="${not empty patient.signesVitaux}">
                        <table>
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Tension</th>
                                    <th>Fréq. cardiaque</th>
                                    <th>Température</th>
                                    <th>Fréq. respiratoire</th>
                                    <th>Poids</th>
                                    <th>Taille</th>
                                    <th>Infirmier</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="sv" items="${patient.signesVitaux}">
                                    <tr>
                                        <td>${sv.datePrise}</td>
                                        <td>${sv.tensionArterielle}</td>
                                        <td>${sv.frequenceCardiaque} bpm</td>
                                        <td>${sv.temperatureCorporelle} °C</td>
                                        <td>${sv.frequenceRespiratoire}</td>
                                        <td>${sv.poidsKg} kg</td>
                                        <td>${sv.tailleCm} cm</td>
                                        <td>${sv.infirmier.nom} ${sv.infirmier.prenom}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p class="empty-state">Aucun signe vital enregistré pour ce patient.</p>
                    </c:otherwise>
                </c:choose>

                <a class="back-link" href="${pageContext.request.contextPath}/infirmier/patients">← Retour à la liste des patients</a>
            </div>
        </c:when>

        <c:otherwise>
            <div class="container">
                <h1>Patient introuvable</h1>
                <p class="empty-state">Aucun patient ne correspond à cet identifiant.</p>
                <a class="back-link" href="${pageContext.request.contextPath}/infirmier/patients">← Retour à la liste des patients</a>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="container">
                        <div class="card">
                            <h1>Patient introuvable</h1>
                            <p class="empty-state">Aucun patient ne correspond à cet identifiant.</p>
                            <a class="back-link" href="${pageContext.request.contextPath}/infirmier/patients">← Retour à
                                la liste des patients</a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

        </body>

        </html>