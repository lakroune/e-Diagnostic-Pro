<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Détails du Patient</title>
        </head>

        <body>

            <c:choose>
                <c:when test="${not empty patient}">
                    <h1>Détails du Patient</h1>

                    <ul>
                        <li><strong>ID :</strong> ${patient.id}</li>
                        <li><strong>Nom :</strong> ${patient.nom}</li>
                        <li><strong>Prénom :</strong> ${patient.prenom}</li>
                        <li><strong>Date de naissance :</strong> ${patient.dateNaissance}</li>
                    </ul>

                    <hr>

                    <h2>Historique des Signes Vitaux</h2>

                    <c:choose>
                        <c:when test="${not empty patient.signesVitaux}">
                            <table border="1" cellpadding="8" cellspacing="0">
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
                                        <th>Actions</th>
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
                                            <td>
                                                <button type="button" onclick="editSigneVital(
                                            '${sv.id}',
                                            '${sv.tensionArterielle}',
                                            '${sv.frequenceCardiaque}',
                                            '${sv.temperatureCorporelle}',
                                            '${sv.frequenceRespiratoire}',
                                            '${sv.poidsKg}',
                                            '${sv.tailleCm}'
                                        )">Modifier</button>

                                                <form
                                                    action="${pageContext.request.contextPath}/patients/signeVitaux/delete"
                                                    method="POST" style="display:inline;"
                                                    onsubmit="return confirm('Voulez-vous vraiment supprimer cet enregistrement ?');">
                                                    <input type="hidden" name="id" value="${sv.id}">
                                                    <input type="hidden" name="patientId" value="${patient.id}">
                                                    <button type="submit">Supprimer</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p>Aucun signe vital enregistré pour ce patient.</p>
                        </c:otherwise>
                    </c:choose>

                    <hr>

                    <h2 id="formTitle">Ajouter des Signes Vitaux</h2>

                    <form id="signeVitalForm" action="${pageContext.request.contextPath}/patients/signeVitaux"
                        method="POST">

                        <input type="hidden" name="patientId" value="${patient.id}">
                        <input type="hidden" id="signeVitalId" name="id" value="">

                        <p>
                            <label for="tensionArterielle">Tension artérielle :</label><br>
                            <input type="text" id="tensionArterielle" name="tensionArterielle" placeholder="ex: 12/8">
                        </p>

                        <p>
                            <label for="frequenceCardiaque">Fréquence cardiaque (bpm) :</label><br>
                            <input type="number" id="frequenceCardiaque" name="frequenceCardiaque">
                        </p>

                        <p>
                            <label for="temperatureCorporelle">Température corporelle (°C) :</label><br>
                            <input type="number" step="0.1" id="temperatureCorporelle" name="temperatureCorporelle">
                        </p>

                        <p>
                            <label for="frequenceRespiratoire">Fréquence respiratoire :</label><br>
                            <input type="number" id="frequenceRespiratoire" name="frequenceRespiratoire">
                        </p>

                        <p>
                            <label for="poidsKg">Poids (kg) :</label><br>
                            <input type="number" step="0.1" id="poidsKg" name="poidsKg">
                        </p>

                        <p>
                            <label for="tailleCm">Taille (cm) :</label><br>
                            <input type="number" step="0.1" id="tailleCm" name="tailleCm">
                        </p>

                        <p>
                            <button type="submit" id="submitBtn">Enregistrer les signes vitaux</button>
                            <button type="button" id="cancelBtn" onclick="resetForm()" style="display:none;">Annuler la
                                modification</button>
                        </p>

                    </form>

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

            <script>
                function editSigneVital(id, tension, freqCard, temp, freqResp, poids, taille) {
                    document.getElementById('signeVitalForm').action = '${pageContext.request.contextPath}/patients/signeVitaux/update';
                    document.getElementById('signeVitalId').value = id;
                    document.getElementById('tensionArterielle').value = tension;
                    document.getElementById('frequenceCardiaque').value = freqCard;
                    document.getElementById('temperatureCorporelle').value = temp;
                    document.getElementById('frequenceRespiratoire').value = freqResp;
                    document.getElementById('poidsKg').value = poids;
                    document.getElementById('tailleCm').value = taille;

                    document.getElementById('formTitle').innerText = 'Modifier les Signes Vitaux';
                    document.getElementById('submitBtn').innerText = 'Mettre à jour';
                    document.getElementById('cancelBtn').style.display = 'inline';
                }

                function resetForm() {
                    document.getElementById('signeVitalForm').action = '${pageContext.request.contextPath}/patients/signeVitaux';
                    document.getElementById('signeVitalForm').reset();
                    document.getElementById('signeVitalId').value = '';

                    document.getElementById('formTitle').innerText = 'Ajouter des Signes Vitaux';
                    document.getElementById('submitBtn').innerText = 'Enregistrer les signes vitaux';
                    document.getElementById('cancelBtn').style.display = 'none';
                }
            </script>

        </body>

        </html>