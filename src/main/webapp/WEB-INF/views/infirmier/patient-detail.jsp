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
                    background: #f4f6f9;
                    color: #1f2937;
                }

                .container {
                    width: 1100px;
                    max-width: 92%;
                    margin: 40px auto 60px;
                }

                .page-header {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    margin-bottom: 25px;
                }

                h1,
                h2 {
                    margin: 0;
                    color: #111827;
                }

                .card {
                    background: #fff;
                    border: 1px solid #dfe3ea;
                    border-radius: 10px;
                    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.03);
                    padding: 25px;
                    margin-bottom: 25px;
                }

                .info-grid {
                    display: grid;
                    grid-template-columns: repeat(2, minmax(220px, 1fr));
                    gap: 18px 25px;
                }

                .info-item {
                    display: flex;
                    flex-direction: column;
                    gap: 6px;
                }

                .label {
                    font-size: 12px;
                    text-transform: uppercase;
                    letter-spacing: 0.06em;
                    color: #6b7280;
                    font-weight: 700;
                }

                .value {
                    font-size: 15px;
                    color: #111827;
                    padding: 10px 12px;
                    background: #f9fafb;
                    border: 1px solid #e5e7eb;
                    border-radius: 6px;
                    min-height: 42px;
                }

                .full-width {
                    grid-column: 1 / -1;
                }

                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 15px;
                }

                th,
                td {
                    border: 1px solid #dfe3ea;
                    padding: 10px 12px;
                    text-align: left;
                    vertical-align: top;
                }

                th {
                    background: #f3f4f6;
                    color: #374151;
                }

                .back-link {
                    display: inline-block;
                    margin-top: 10px;
                    color: #111827;
                    text-decoration: none;
                    font-weight: 600;
                }

                .back-link:hover {
                    text-decoration: underline;
                }

                .empty-state {
                    margin-top: 16px;
                    color: #6b7280;
                    font-style: italic;
                }
            </style>
        </head>

        <body>

            <jsp:include page="menu.jsp" />

            <c:choose>
                <c:when test="${not empty patient}">
                    <div class="container">
                        <div class="page-header">
                            <h1>Détails du patient</h1>
                        </div>

                        <div class="card">
                            <div class="info-grid">
                                <div class="info-item">
                                    <span class="label">Nom</span>
                                    <span class="value">${patient.nom}</span>
                                </div>
                                <div class="info-item">
                                    <span class="label">Prénom</span>
                                    <span class="value">${patient.prenom}</span>
                                </div>
                                <div class="info-item">
                                    <span class="label">Date de naissance</span>
                                    <span class="value">${patient.dateNaissance}</span>
                                </div>
                                <div class="info-item">
                                    <span class="label">N° sécurité sociale</span>
                                    <span class="value">${not empty patient.numSecuriteSociale ? patient.numSecuriteSociale : 'Non renseigné'}</span>
                                </div>
                                <div class="info-item">
                                    <span class="label">Téléphone</span>
                                    <span class="value">${not empty patient.telephone ? patient.telephone : 'Non renseigné'}</span>
                                </div>
                                <div class="info-item">
                                    <span class="label">Adresse</span>
                                    <span class="value">${not empty patient.adresse ? patient.adresse : 'Non renseignée'}</span>
                                </div>
                                <div class="info-item">
                                    <span class="label">Mutuelle</span>
                                    <span class="value">${not empty patient.mutuelle ? patient.mutuelle : 'Non renseignée'}</span>
                                </div>
                                <div class="info-item">
                                    <span class="label">Statut</span>
                                    <span class="value">
                                        <c:choose>
                                            <c:when test="${patient.enAttente}">En attente</c:when>
                                            <c:otherwise>Pris en charge</c:otherwise>
                                        </c:choose>
                                    </span>
                                </div>
                                <div class="info-item full-width">
                                    <span class="label">Antécédents</span>
                                    <span class="value">${not empty patient.antecedents ? patient.antecedents : 'Aucun'}</span>
                                </div>
                                <div class="info-item full-width">
                                    <span class="label">Allergies</span>
                                    <span class="value">${not empty patient.allergies ? patient.allergies : 'Aucune'}</span>
                                </div>
                                <div class="info-item full-width">
                                    <span class="label">Traitements en cours</span>
                                    <span class="value">${not empty patient.traitementsEnCours ? patient.traitementsEnCours : 'Aucun'}</span>
                                </div>
                                <div class="info-item">
                                    <span class="label">Date d'enregistrement</span>
                                    <span class="value">${not empty patient.dateEnregistrement ? patient.dateEnregistrement : 'Non renseignée'}</span>
                                </div>
                            </div>
                        </div>

                        <div class="card">
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
                        </div>

                        <a class="back-link" href="${pageContext.request.contextPath}/infirmier/patients">← Retour à la liste des patients</a>
                    </div>
                </c:when>

                <c:otherwise>
                    <div class="container">
                        <div class="card">
                            <h1>Patient introuvable</h1>
                            <p class="empty-state">Aucun patient ne correspond à cet identifiant.</p>
                            <a class="back-link" href="${pageContext.request.contextPath}/infirmier/patients">← Retour à la liste des patients</a>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

        </body>

        </html>