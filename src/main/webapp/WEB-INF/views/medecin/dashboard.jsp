<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard Médecin</title>
</head>
<body>

    <jsp:include page="menu.jsp" />

    <table width="100%" border="0" cellpadding="8" cellspacing="0">
        <tr>
            <td>
                <font face="Arial, Helvetica, sans-serif" size="4"><b>Patients en attente</b></font>
                <hr>

                <c:choose>
                    <c:when test="${not empty patientsEnAttente}">
                        <table border="1" width="100%" cellpadding="6" cellspacing="0">
                            <tr>
                                <th><font face="Arial, Helvetica, sans-serif" size="2">Nom</font></th>
                                <th><font face="Arial, Helvetica, sans-serif" size="2">Prénom</font></th>
                                <th><font face="Arial, Helvetica, sans-serif" size="2">Heure d'arrivée</font></th>
                                <th><font face="Arial, Helvetica, sans-serif" size="2">Signes vitaux</font></th>
                                <th><font face="Arial, Helvetica, sans-serif" size="2">N° sécurité sociale</font></th>
                                <th><font face="Arial, Helvetica, sans-serif" size="2">Action</font></th>
                            </tr>

                            <c:forEach var="patient" items="${patientsEnAttente}">
                                <tr>
                                    <td><font face="Arial, Helvetica, sans-serif" size="2">${patient.nom}</font></td>
                                    <td><font face="Arial, Helvetica, sans-serif" size="2">${patient.prenom}</font></td>
                                    <td><font face="Arial, Helvetica, sans-serif" size="2">
                                        <c:forEach var="fileAttente" items="${patient.fileAttentes}">
                                            <c:if test="${fileAttente.statut eq 'EN_ATTENTE'}">
                                                ${fileAttente.heureArrivee.toLocalTime()}
                                            </c:if>
                                        </c:forEach>
                                    </font></td>
                                    <td><font face="Arial, Helvetica, sans-serif" size="2">
                                        <c:choose>
                                            <c:when test="${not empty patient.signesVitaux}">
                                                <c:set var="dernierSigne" value="${patient.signesVitaux[patient.signesVitaux.size() - 1]}" />
                                                ${dernierSigne.tensionArterielle} / ${dernierSigne.frequenceCardiaque} bpm / ${dernierSigne.temperatureCorporelle}°C
                                            </c:when>
                                            <c:otherwise>—</c:otherwise>
                                        </c:choose>
                                    </font></td>
                                    <td><font face="Arial, Helvetica, sans-serif" size="2">${patient.numSecuriteSociale}</font></td>
                                    <td><font face="Arial, Helvetica, sans-serif" size="2">
                                        <a href="${pageContext.request.contextPath}/medecin/dossier/${patient.id}">Dossier</a>
                                    </font></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p><font face="Arial, Helvetica, sans-serif" size="2">Aucun patient en attente pour le moment.</font></p>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>

</body>
</html>