<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Dashboard Spécialiste</title>
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
                        <font face="Arial, Helvetica, sans-serif" size="4"><b>Dashboard Spécialiste</b></font>
                        <hr>

                        <p>
                            <font face="Arial, Helvetica, sans-serif" size="2">
                                <a href="${pageContext.request.contextPath}/specialiste/creneau">Définir un créneau</a>
                            </font>
                        </p>

                        <c:choose>
                            <c:when test="${not empty demandes}">
                                <table border="1" width="100%" cellpadding="6" cellspacing="0">
                                    <tr>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Date</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Question</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Priorité</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Statut</font>
                                        </th>
                                        <th>
                                            <font face="Arial, Helvetica, sans-serif" size="2">Action</font>
                                        </th>
                                    </tr>

                                    <c:forEach var="demande" items="${demandes}">
                                        <tr>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">
                                                    ${demande.dateDemande}</font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">${demande.question}
                                                </font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">${demande.priorite}
                                                </font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">${demande.statut}
                                                </font>
                                            </td>
                                            <td>
                                                <font face="Arial, Helvetica, sans-serif" size="2">
                                                    <a
                                                        href="${pageContext.request.contextPath}/specialiste/demande/${demande.id}">Voir</a>
                                                </font>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <p>
                                    <font face="Arial, Helvetica, sans-serif" size="2">Aucune demande d'avis spécialisé
                                        pour le moment.</font>
                                </p>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>

        </body>

        </html>