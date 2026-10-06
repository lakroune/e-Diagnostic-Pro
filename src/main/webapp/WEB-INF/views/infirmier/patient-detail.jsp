<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Détails du Patient</title>
        </head>

        <body>

            <jsp:include page="menu.jsp" />

            <c:choose>
                <c:when test="${not empty patient}">
                    <h1>Détails du Patient</h1>

                    <ul>
                        <li><strong>ID :</strong> ${patient.id}</li>
                        <li><strong>Nom :</strong> ${patient.nom}</li>
                        <li><strong>Prénom :</strong> ${patient.prenom}</li>
                        <li><strong>Date de naissance :</strong> ${patient.dateNaissance}</li>
                        <li><strong>N° sécurité sociale :</strong> ${not empty patient.numSecuriteSociale ? patient.numSecuriteSociale : 'Non renseigné'}</li>
                        <li><strong>Téléphone :</strong> ${not empty patient.telephone ? patient.telephone : 'Non renseigné'}</li>
                        <li><strong>Adresse :</strong> ${not empty patient.adresse ? patient.adresse : 'Non renseignée'}</li>
                        <li><strong>Mutuelle :</strong> ${not empty patient.mutuelle ? patient.mutuelle : 'Non renseignée'}</li>
                        <li><strong>Antécédents :</strong> ${not empty patient.antecedents ? patient.antecedents : 'Aucun'}</li>
                        <li><strong>Allergies :</strong> ${not empty patient.allergies ? patient.allergies : 'Aucune'}</li>
                        <li><strong>Traitements en cours :</strong> ${not empty patient.traitementsEnCours ? patient.traitementsEnCours : 'Aucun'}</li>
                        <li><strong>Statut :</strong>
                            <c:choose>
                                <c:when test="${patient.enAttente}">En attente</c:when>
                                <c:otherwise>Pris en charge</c:otherwise>
                            </c:choose>
                        </li>
                        <li><strong>Date d'enregistrement :</strong> ${not empty patient.dateEnregistrement ? patient.dateEnregistrement : 'Non renseignée'}</li>
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

                    <hr>

                    <h2>Modifier les informations du patient</h2>
                    <form action="${pageContext.request.contextPath}/infirmier/patients" method="POST">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${patient.id}">

                        <p>
                            <label>Nom :</label><br>
                            <input type="text" name="nom" value="${patient.nom}" required>
                        </p>
                        <p>
                            <label>Prénom :</label><br>
                            <input type="text" name="prenom" value="${patient.prenom}" required>
                        </p>
                        <p>
                            <label>Date de naissance :</label><br>
                            <input type="date" name="dateNaissance" value="${patient.dateNaissance}">
                        </p>
                        <p>
                            <label>Numéro de sécurité sociale :</label><br>
                            <input type="text" name="numSecuriteSociale" value="${patient.numSecuriteSociale}">
                        </p>
                        <p>
                            <label>Téléphone :</label><br>
                            <input type="text" name="telephone" value="${patient.telephone}">
                        </p>
                        <p>
                            <label>Adresse :</label><br>
                            <input type="text" name="adresse" value="${patient.adresse}">
                        </p>
                        <p>
                            <label>Mutuelle :</label><br>
                            <input type="text" name="mutuelle" value="${patient.mutuelle}">
                        </p>
                        <p>
                            <label>Antécédents :</label><br>
                            <textarea name="antecedents">${patient.antecedents}</textarea>
                        </p>
                        <p>
                            <label>Allergies :</label><br>
                            <textarea name="allergies">${patient.allergies}</textarea>
                        </p>
                        <p>
                            <label>Traitements en cours :</label><br>
                            <textarea name="traitementsEnCours">${patient.traitementsEnCours}</textarea>
                        </p>

                        <button type="submit">Enregistrer les modifications</button>
                    </form>

                    <form action="${pageContext.request.contextPath}/infirmier/patients" method="POST"
                        onsubmit="return confirm('Voulez-vous vraiment supprimer ce patient ?');">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${patient.id}">
                        <button type="submit">Supprimer ce patient</button>
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