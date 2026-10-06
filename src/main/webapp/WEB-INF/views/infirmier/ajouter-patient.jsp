<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Ajouter un patient</title>

            <style>
                body {
                    margin: 0;
                    font-family: Arial, sans-serif;
                }

                .container {
                    width: 900px;
                    max-width: 90%;
                    margin: 40px auto;
                }

                h1,
                h2 {
                    text-align: center;
                    margin-bottom: 20px;
                }

                .search-box,
                .form-card {
                    border: 1px solid black;
                    padding: 20px;
                    background: white;
                    margin-bottom: 25px;
                }

                .search-row,
                .row {
                    display: grid;
                    grid-template-columns: repeat(2, minmax(250px, 1fr));
                    gap: 15px;
                }

                form {
                    display: grid;
                    gap: 15px;
                }

                label {
                    display: block;
                    margin-bottom: 5px;
                    font-weight: bold;
                }

                input,
                textarea,
                button {
                    width: 100%;
                    padding: 8px;
                    box-sizing: border-box;
                    border: 1px solid black;
                    background: white;
                    font-family: Arial, sans-serif;
                }

                textarea {
                    height: 80px;
                    resize: vertical;
                }

                button {
                    cursor: pointer;
                }

                .alert {
                    border: 1px solid black;
                    padding: 10px 15px;
                    margin-bottom: 15px;
                    background: #f6f6f6;
                }
            </style>
        </head>

        <body>

            <jsp:include page="menu.jsp" />

            <div class="container">
                <h1>Étape 1 : Recherche du patient</h1>

                <div class="search-box">
                    <form method="get" action="${pageContext.request.contextPath}/infirmier/patients/ajouter">
                        <div class="search-row">
                            <div>
                                <label for="numSecuriteSociale">Numéro de sécurité sociale</label>
                                <input type="text" id="numSecuriteSociale" name="numSecuriteSociale"
                                    value="${param.numSecuriteSociale}" placeholder="Rechercher un patient par NSS">
                            </div>
                            <div>
                                <button type="submit">Rechercher</button>
                            </div>
                        </div>
                    </form>
                </div>

                <c:if test="${not empty param.numSecuriteSociale}">
                    <c:if test="${not empty patientFound}">
                        <div class="alert">
                            <c:choose>
                                <c:when test="${patientFound}">
                                    Patient existant trouvé. Les informations sont affichées en lecture seule.
                                    Saisissez uniquement les nouveaux signes vitaux et ajoutez-le à la file d'attente.
                                </c:when>
                                <c:otherwise>
                                    Aucun patient trouvé. Remplissez le dossier du patient puis ajoutez-le à la file
                                    d'attente.
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:if>

                    <form method="post" action="${pageContext.request.contextPath}/infirmier/patients/ajouter">
                        <c:choose>
                            <c:when test="${patientFound}">
                                <div class="form-card">
                                    <h2>Informations du patient</h2>

                                    <div class="row">
                                        <div>
                                            <label for="nom">Nom</label>
                                            <input type="text" id="nom" name="nom" value="${patient.nom}" readonly>
                                        </div>

                                        <div>
                                            <label for="prenom">Prénom</label>
                                            <input type="text" id="prenom" name="prenom" value="${patient.prenom}" readonly>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div>
                                            <label for="dateNaissance">Date de naissance</label>
                                            <input type="date" id="dateNaissance" name="dateNaissance" value="${patient.dateNaissance}" readonly>
                                        </div>

                                        <div>
                                            <label for="numSecuriteSocialeForm">Numéro de sécurité sociale</label>
                                            <input type="text" id="numSecuriteSocialeForm" name="numSecuriteSociale" value="${patient.numSecuriteSociale}" readonly>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div>
                                            <label for="telephone">Téléphone</label>
                                            <input type="text" id="telephone" name="telephone" value="${patient.telephone}" readonly>
                                        </div>

                                        <div>
                                            <label for="adresse">Adresse</label>
                                            <input type="text" id="adresse" name="adresse" value="${patient.adresse}" readonly>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div>
                                            <label for="mutuelle">Mutuelle</label>
                                            <input type="text" id="mutuelle" name="mutuelle" value="${patient.mutuelle}" readonly>
                                        </div>
                                    </div>

                                    <div>
                                        <label for="antecedents">Antécédents</label>
                                        <textarea id="antecedents" name="antecedents" readonly>${patient.antecedents}</textarea>
                                    </div>

                                    <div>
                                        <label for="allergies">Allergies</label>
                                        <textarea id="allergies" name="allergies" readonly>${patient.allergies}</textarea>
                                    </div>

                                    <div>
                                        <label for="traitementsEnCours">Traitements en cours</label>
                                        <textarea id="traitementsEnCours" name="traitementsEnCours" readonly>${patient.traitementsEnCours}</textarea>
                                    </div>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div class="form-card">
                                    <h2>Créer un nouveau dossier patient</h2>

                                    <div class="row">
                                        <div>
                                            <label for="nom">Nom</label>
                                            <input type="text" id="nom" name="nom" value="${patient.nom}" required>
                                        </div>

                                        <div>
                                            <label for="prenom">Prénom</label>
                                            <input type="text" id="prenom" name="prenom" value="${patient.prenom}" required>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div>
                                            <label for="dateNaissance">Date de naissance</label>
                                            <input type="date" id="dateNaissance" name="dateNaissance" value="${patient.dateNaissance}">
                                        </div>

                                        <div>
                                            <label for="numSecuriteSocialeForm">Numéro de sécurité sociale</label>
                                            <input type="text" id="numSecuriteSocialeForm" name="numSecuriteSociale" value="${patient.numSecuriteSociale}">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div>
                                            <label for="telephone">Téléphone</label>
                                            <input type="text" id="telephone" name="telephone" value="${patient.telephone}">
                                        </div>

                                        <div>
                                            <label for="adresse">Adresse</label>
                                            <input type="text" id="adresse" name="adresse" value="${patient.adresse}">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div>
                                            <label for="mutuelle">Mutuelle</label>
                                            <input type="text" id="mutuelle" name="mutuelle" value="${patient.mutuelle}">
                                        </div>
                                    </div>

                                    <div>
                                        <label for="antecedents">Antécédents</label>
                                        <textarea id="antecedents" name="antecedents">${patient.antecedents}</textarea>
                                    </div>

                                    <div>
                                        <label for="allergies">Allergies</label>
                                        <textarea id="allergies" name="allergies">${patient.allergies}</textarea>
                                    </div>

                                    <div>
                                        <label for="traitementsEnCours">Traitements en cours</label>
                                        <textarea id="traitementsEnCours" name="traitementsEnCours">${patient.traitementsEnCours}</textarea>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <div class="form-card">
                            <h2>Ajouter les signes vitaux</h2>
                            <div class="row">
                                <div>
                                    <label for="tensionArterielle">Tension artérielle</label>
                                    <input type="text" id="tensionArterielle" name="tensionArterielle">
                                </div>

                                <div>
                                    <label for="frequenceCardiaque">Fréquence cardiaque (bpm)</label>
                                    <input type="number" id="frequenceCardiaque" name="frequenceCardiaque">
                                </div>
                            </div>

                            <div class="row">
                                <div>
                                    <label for="temperatureCorporelle">Température corporelle (°C)</label>
                                    <input type="number" step="0.1" id="temperatureCorporelle" name="temperatureCorporelle">
                                </div>

                                <div>
                                    <label for="frequenceRespiratoire">Fréquence respiratoire</label>
                                    <input type="number" id="frequenceRespiratoire" name="frequenceRespiratoire">
                                </div>
                            </div>

                            <div class="row">
                                <div>
                                    <label for="poidsKg">Poids (kg)</label>
                                    <input type="number" step="0.1" id="poidsKg" name="poidsKg">
                                </div>

                                <div>
                                    <label for="tailleCm">Taille (cm)</label>
                                    <input type="number" step="0.1" id="tailleCm" name="tailleCm">
                                </div>
                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${patientFound}">
                                <button type="submit" name="action" value="add-to-waiting">Ajouter à la file d'attente</button>
                                <button type="submit">Enregistrer les nouveaux signes vitaux</button>
                            </c:when>
                            <c:otherwise>
                                <button type="submit">Valider et ajouter à la file d'attente</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </c:if>
            </div>

        </body>

        </html>