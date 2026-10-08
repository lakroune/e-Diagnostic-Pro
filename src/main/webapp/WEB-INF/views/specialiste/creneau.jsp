<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Définir un créneau</title>
</head>
<body>
 
<table width="100%" border="0" cellpadding="8" cellspacing="0">
    <tr>
        <td bgcolor="#f2f2f2">
            <font face="Arial, Helvetica, sans-serif" size="2">
                <b>Spécialiste :</b> ${specialiste.prenom} ${specialiste.nom} |
                <a href="${pageContext.request.contextPath}/specialiste/dashboard">Dashboard</a> |
                <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
            </font>
        </td>
    </tr>
</table>

<hr>

<table width="100%" border="0" cellpadding="10" cellspacing="0">
    <tr>
        <td>
            <font face="Arial, Helvetica, sans-serif" size="4"><b>Définir un créneau</b></font>
            <hr>

            <c:if test="${not empty error}">
                <p><font face="Arial, Helvetica, sans-serif" size="2" color="red">${error}</font></p>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/specialiste/creneau">
                <table border="0" cellpadding="6" cellspacing="0">
                    <tr>
                        <td><font face="Arial, Helvetica, sans-serif" size="2"><b>Date et heure de début</b></font></td>
                        <td><input type="datetime-local" name="dateHeureDebut" required width="250" height="28"></td>
                    </tr>
                    <tr>
                        <td><font face="Arial, Helvetica, sans-serif" size="2"><b>Date et heure de fin</b></font></td>
                        <td><input type="datetime-local" name="dateHeureFin" required width="250" height="28"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button type="submit" width="140" height="35">Enregistrer</button>
                        </td>
                    </tr>
                </table>
            </form>

            <hr>

            <font face="Arial, Helvetica, sans-serif" size="3"><b>Liste des créneaux</b></font>
            <c:choose>
                <c:when test="${not empty creneaux}">
                    <table border="1" width="100%" cellpadding="6" cellspacing="0">
                        <tr>
                            <th><font face="Arial, Helvetica, sans-serif" size="2">Début</font></th>
                            <th><font face="Arial, Helvetica, sans-serif" size="2">Fin</font></th>
                            <th><font face="Arial, Helvetica, sans-serif" size="2">Statut</font></th>
                        </tr>
                        <c:forEach var="creneau" items="${creneaux}">
                            <tr>
                                <td><font face="Arial, Helvetica, sans-serif" size="2">${creneau.dateHeureDebut}</font></td>
                                <td><font face="Arial, Helvetica, sans-serif" size="2">${creneau.dateHeureFin}</font></td>
                                <td><font face="Arial, Helvetica, sans-serif" size="2">${creneau.statut}</font></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p><font face="Arial, Helvetica, sans-serif" size="2">Aucun créneau défini pour le moment.</font></p>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>

</body>
</html>
