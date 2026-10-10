<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Télé-expertise</title>
        </head>

        <body>

            <jsp:include page="menu.jsp" />

            <table width="100%" border="0" cellpadding="8" cellspacing="0">
                <tr>
                    <td>
                        <font face="Arial, Helvetica, sans-serif" size="4"><b>Demande d'avis spécialiste</b></font>
                        <hr>

                        <font face="Arial, Helvetica, sans-serif" size="3"><b>Patient</b></font>
                        <p>
                            <font face="Arial, Helvetica, sans-serif" size="2">
                                <b>Nom :</b> ${patient.nom} ${patient.prenom}<br>
                                <b>N° sécurité sociale :</b> ${patient.numSecuriteSociale}
                            </font>
                        </p>
                        <hr>

                        <font face="Arial, Helvetica, sans-serif" size="3"><b>Étape 1 - Sélection de la spécialité</b>
                        </font>
                        <form method="get" action="${pageContext.request.contextPath}/medecin/tele-expertise">
                            <input type="hidden" name="patientId" value="${patient.id}" />
                            <p>
                                <font face="Arial, Helvetica, sans-serif" size="2">
                                    <label for="specialite">Spécialité médicale :</label>
                                    <select id="specialite" name="specialite" width="300" height="30">
                                        <option value="">-- Choisir --</option>
                                        <c:forEach var="specialite" items="${specialites}">
                                            <option value="${specialite.name()}" ${selectedSpecialite eq
                                                specialite.name() ? 'selected' : '' }>
                                                ${specialiteLibelles[specialite.name()]}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </font>
                            </p>
                            <p>
                                <button type="submit" width="180" height="35">Afficher les spécialistes</button>
                            </p>
                        </form>
                        <hr>

                        <c:if test="${not empty specialists}">
                            <font face="Arial, Helvetica, sans-serif" size="3"><b>Étape 2 - Spécialistes disponibles</b>
                            </font>
                            <table border="1" width="100%" cellpadding="6" cellspacing="0">
                                <tr>
                                    <th>
                                        <font face="Arial, Helvetica, sans-serif" size="2">Nom</font>
                                    </th>
                                    <th>
                                        <font face="Arial, Helvetica, sans-serif" size="2">Tarif</font>
                                    </th>
                                    <th>
                                        <font face="Arial, Helvetica, sans-serif" size="2">Disponibilité</font>
                                    </th>
                                    <th>
                                        <font face="Arial, Helvetica, sans-serif" size="2">Action</font>
                                    </th>
                                </tr>
                                <c:forEach var="specialist" items="${specialists}">
                                    <tr>
                                        <td>
                                            <font face="Arial, Helvetica, sans-serif" size="2">${specialist.nom}
                                                ${specialist.prenom}</font>
                                        </td>
                                        <td>
                                            <font face="Arial, Helvetica, sans-serif" size="2">
                                                ${specialist.tarifExpertise} MAD</font>
                                        </td>
                                        <td>
                                            <font face="Arial, Helvetica, sans-serif" size="2">
                                                <c:choose>
                                                    <c:when test="${not empty specialist.creneaux}">Disponible</c:when>
                                                    <c:otherwise>Indisponible</c:otherwise>
                                                </c:choose>
                                            </font>
                                        </td>
                                        <td>
                                            <font face="Arial, Helvetica, sans-serif" size="2">
                                                <a
                                                    href="${pageContext.request.contextPath}/medecin/tele-expertise?patientId=${patient.id}&specialite=${selectedSpecialite}&specialisteId=${specialist.id}">Choisir</a>
                                            </font>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <hr>
                        </c:if>

                        <c:if test="${not empty selectedSpecialist}">
                            <font face="Arial, Helvetica, sans-serif" size="3"><b>Étape 3 - Vérification de la
                                    disponibilité</b></font>
                            <p>
                                <font face="Arial, Helvetica, sans-serif" size="2">
                                    <b>Spécialiste sélectionné :</b> ${selectedSpecialist.nom}
                                    ${selectedSpecialist.prenom} - ${selectedSpecialist.specialite}
                                </font>
                            </p>

                            <font face="Arial, Helvetica, sans-serif" size="2"><b>Créneaux disponibles (futurs
                                    uniquement)</b></font>
                            <c:choose>
                                <c:when test="${not empty availableSlots}">
                                    <ul>
                                        <c:forEach var="slot" items="${availableSlots}">
                                            <li>
                                                <font face="Arial, Helvetica, sans-serif" size="2">
                                                    <input type="radio" name="creneauRadio" value="${slot.id}"
                                                        disabled />
                                                    ${slot.heureDebutFormatee} à ${slot.heureFinFormatee}
                                                </font>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:when>
                                <c:otherwise>
                                    <p>
                                        <font face="Arial, Helvetica, sans-serif" size="2">Aucun créneau disponible pour
                                            ce spécialiste pour le moment. Seuls les créneaux futurs sont affichés.
                                        </font>
                                    </p>
                                </c:otherwise>
                            </c:choose>

                            <font face="Arial, Helvetica, sans-serif" size="2"><b>Créneaux déjà réservés ou passés</b>
                            </font>
                            <c:choose>
                                <c:when test="${not empty reservedOrOldSlots}">
                                    <ul>
                                        <c:forEach var="slot" items="${reservedOrOldSlots}">
                                            <li>
                                                <font face="Arial, Helvetica, sans-serif" size="2"><span
                                                        style="color:#666;">${slot.dateHeureDebut.toLocalDate()} -
                                                        ${slot.dateHeureDebut.toLocalTime()} à
                                                        ${slot.dateHeureFin.toLocalTime()} - ${slot.statut} (non
                                                        disponible)</span></font>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </c:when>
                                <c:otherwise>
                                    <p>
                                        <font face="Arial, Helvetica, sans-serif" size="2">Aucun créneau historique à
                                            afficher.</font>
                                    </p>
                                </c:otherwise>
                            </c:choose>
                            <hr>
                        </c:if>

                        <c:if test="${not empty selectedSpecialist}">
                            <font face="Arial, Helvetica, sans-serif" size="3"><b>Étape 4 - Demande d'expertise</b>
                            </font>
                            <form method="post" action="${pageContext.request.contextPath}/medecin/tele-expertise">
                                <input type="hidden" name="patientId" value="${patient.id}" />
                                <input type="hidden" name="specialisteId" value="${selectedSpecialist.id}" />

                                <p>
                                    <font face="Arial, Helvetica, sans-serif" size="2">
                                        <label for="specialiteChoice">Spécialité :</label><br>
                                        <input id="specialiteChoice" type="text"
                                            value="${specialiteLibelles[selectedSpecialist.specialite.name()]}" readonly
                                            width="300" height="30" />
                                    </font>
                                </p>

                                <p>
                                    <font face="Arial, Helvetica, sans-serif" size="2">
                                        <label for="heureDebut">Créneau sélectionné :</label><br>
                                        <select id="heureDebut" name="heureDebut" required width="300" height="30">
                                            <option value="">-- Choisir un créneau disponible --</option>
                                            <c:forEach var="heure" items="${heuresJour}">
                                                <option value="${heure}">${heure}</option>
                                            </c:forEach>
                                        </select>
                                    </font>
                                </p>

                                <script>
                                    document.addEventListener('DOMContentLoaded', function () {
                                        const currentHour = new Date().getHours();
                                        const select = document.getElementById('creneauId');
                                        if (!select) return;

                                        Array.from(select.options).forEach(function (option) {
                                            if (!option.value) return;

                                            const hour = parseInt(option.value.split(':')[0], 10);
                                            if (!isNaN(hour) && hour < currentHour) {
                                                option.disabled = true;
                                                option.style.color = '#999';
                                            }
                                        });
                                    });
                                </script>

                                <p>
                                    <font face="Arial, Helvetica, sans-serif" size="2">
                                        <label for="question">Question posée au spécialiste :</label><br>
                                        <textarea id="question" name="question" rows="6" cols="60" required width="500"
                                            height="120"
                                            placeholder="Décrivez le cas médical et la question à clarifier."></textarea>
                                    </font>
                                </p>

                                <p>
                                    <font face="Arial, Helvetica, sans-serif" size="2">
                                        <label for="priorite">Niveau de priorité :</label><br>
                                        <select id="priorite" name="priorite" width="300" height="30">
                                            <option value="URGENTE">Urgente</option>
                                            <option value="NORMALE" selected>Normale</option>
                                            <option value="NON_URGENTE">Non urgente</option>
                                        </select>
                                    </font>
                                </p>

                                <p>
                                    <button type="submit" width="180" height="35">Envoyer la demande</button>
                                </p>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </table>

        </body>

        </html>