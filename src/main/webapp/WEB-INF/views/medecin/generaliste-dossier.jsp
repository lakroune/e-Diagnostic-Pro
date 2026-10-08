<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Dossier Patient - Généraliste</title>
        </head>

        <body>

            <jsp:include page="menu.jsp" />

            <table width="100%" border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td>
                        <font face="Arial, Helvetica, sans-serif" size="4"><b>Dossier patient - Généraliste</b></font>
                        <hr>

                        <font face="Arial, Helvetica, sans-serif" size="3"><b>Informations du patient</b></font>
                        <table border="1" width="100%" cellpadding="6" cellspacing="0">
                            <tr>
                                <td width="30%">
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>Nom</b></font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.nom}</font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>Prénom</b></font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.prenom}</font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>Date de naissance</b></font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.dateNaissance}</font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>N° sécurité sociale</b></font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.numSecuriteSociale}
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>Téléphone</b></font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.telephone}</font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>Adresse</b></font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.adresse}</font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>Mutuelle</b></font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.mutuelle}</font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>Antécédents</b></font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.antecedents}</font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>Allergies</b></font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.allergies}</font>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2"><b>Traitements en cours</b>
                                    </font>
                                </td>
                                <td>
                                    <font face="Arial, Helvetica, sans-serif" size="2">${patient.traitementsEnCours}
                                    </font>
                                </td>
                            </tr>
                        </table>
                        <hr>

                        <font face="Arial, Helvetica, sans-serif" size="3"><b>Signes vitaux saisis par l'infirmier</b>
                        </font>
                        <c:choose>
                            <c:when test="${not empty patient.signesVitaux}">
                                <table border="1" width="100%" cellpadding="6" cellspacing="0">
                                    <tr>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Date</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Tension</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Fréq. cardiaque</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Température</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Fréq. respiratoire</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Poids</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Taille</font>
                                        </th>
                                    </tr>
                                    <c:forEach var="signe" items="${patient.signesVitaux}">
                                        <tr>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">${signe.datePrise}
                                                </font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">
                                                    ${signe.tensionArterielle}</font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">
                                                    ${signe.frequenceCardiaque} bpm</font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">
                                                    ${signe.temperatureCorporelle} °C</font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">
                                                    ${signe.frequenceRespiratoire} /min</font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">${signe.poidsKg} kg
                                                </font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">${signe.tailleCm} cm
                                                </font>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p>
                                    <font face="Arial, Helvetica, sans-serif" size="2">Aucun signe vital enregistré pour
                                        ce patient.</font>
                                </p>
                            </c:otherwise>
                        </c:choose>
                        <hr>

                        <font face="Arial, Helvetica, sans-serif" size="3"><b>Consultation du généraliste</b></font>
                        <form method="post" action="${pageContext.request.contextPath}/medecin/consultation">
                            <input type="hidden" name="patientId" value="${patient.id}" />

                            <p>
                                <font face="Arial, Helvetica, sans-serif" size="2">
                                    <label for="motif">Motif de consultation :</label><br>
                                    <input type="text" id="motif" name="motif" value="Fièvre et douleurs thoraciques"
                                        width="300" height="30" />
                                </font>
                            </p>

                            <p>
                                <font face="Arial, Helvetica, sans-serif" size="2">
                                    <label for="statut">Statut :</label><br>
                                    <select id="statut" name="statut" width="300" height="30">
                                        <option value="EN_COURS" selected>En cours</option>
                                        <option value="TERMINEE">Terminé</option>
                                        <option value="EN_ATTENTE">En attente</option>
                                    </select>
                                </font>
                            </p>

                            <p>
                                <font face="Arial, Helvetica, sans-serif" size="2">
                                    <label for="examenClinique">Examen clinique :</label><br>
                                    <textarea id="examenClinique" name="examenClinique" rows="5" cols="80" width="500"
                                        height="120"></textarea>
                                </font>
                            </p>

                            <p>
                                <font face="Arial, Helvetica, sans-serif" size="2">
                                    <label for="symptomes">Analyse des symptômes :</label><br>
                                    <textarea id="symptomes" name="symptomes" rows="5" cols="80" width="500"
                                        height="120"></textarea>
                                </font>
                            </p>

                            <p>
                                <font face="Arial, Helvetica, sans-serif" size="2">
                                    <label for="diagnostic">Diagnostic :</label><br>
                                    <textarea id="diagnostic" name="diagnostic" rows="5" cols="80" width="500"
                                        height="120"></textarea>
                                </font>
                            </p>

                            <p>
                                <font face="Arial, Helvetica, sans-serif" size="2">
                                    <label for="observations">Observations / conclusion :</label><br>
                                    <textarea id="observations" name="observations" rows="5" cols="80" width="500"
                                        height="120"></textarea>
                                </font>
                            </p>

                            <p>
                                <font face="Arial, Helvetica, sans-serif" size="2">
                                    <label for="ordonnance">Ordonnance / traitement :</label><br>
                                    <textarea id="ordonnance" name="ordonnance" rows="5" cols="80" width="500"
                                        height="120"></textarea>
                                </font>
                            </p>

                            <p>
                                <button type="submit" name="action" value="enregistrer" width="180"
                                    height="35">Enregistrer la consultation</button>
                                <button type="submit" name="action" value="demander_avis_specialiste" width="220"
                                    height="35">Demander avis spécialiste</button>
                                <button type="submit" name="action" value="cloturer" width="160"
                                    height="35">Clôturer</button>
                            </p>
                        </form>
                    </td>
                </tr>
            </table>

        </body>

        </html>