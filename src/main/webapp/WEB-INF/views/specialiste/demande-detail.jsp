<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Détail de la demande</title>
</head>
<body>

    <table width="100%" border="0" cellpadding="8" cellspacing="0">
        <tr>
            <td bgcolor="#f2f2f2">
                <font face="Arial, Helvetica, sans-serif" size="2">
                    <b>Bienvenue :</b> ${specialiste.prenom} ${specialiste.nom} |
                    <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
                </font>
            </td>
        </tr>
    </table>

    <hr>

    <table width="100%" border="0" cellpadding="10" cellspacing="0">
        <tr>
            <td>
                <font face="Arial, Helvetica, sans-serif" size="4"><b>Détail de la demande d'avis</b></font>
                <hr>

                <p>
                    <font face="Arial, Helvetica, sans-serif" size="2">
                        <b>Patient :</b> ${demande.consultation.patient.nom} ${demande.consultation.patient.prenom}<br>
                        <b>Date de la demande :</b> ${demande.dateDemande}<br>
                        <b>Spécialité :</b> ${demande.medecinSpecialiste.specialite}<br>
                        <b>Priorité :</b> ${demande.priorite}<br>
                        <b>Statut :</b> ${demande.statut}<br>
                    </font>
                </p>

                <p>
                    <font face="Arial, Helvetica, sans-serif" size="2">
                        <b>Question :</b><br>
                        ${demande.question}
                    </font>
                </p>

                <p>
                    <font face="Arial, Helvetica, sans-serif" size="2">
                        <b>Créneau :</b>
                        ${demande.creneau.dateHeureDebut} à ${demande.creneau.dateHeureFin}
                    </font>
                </p>

                <p>
                    <a href="${pageContext.request.contextPath}/specialiste/dashboard">Retour au dashboard</a>
                </p>
            </td>
        </tr>
    </table>

</body>
</html>
