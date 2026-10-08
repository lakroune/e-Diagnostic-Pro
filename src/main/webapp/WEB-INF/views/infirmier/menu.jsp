<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<table width="100%" border="0" cellpadding="8" cellspacing="0">
    <tr>
        <td bgcolor="#f2f2f2" valign="middle">
            <font face="Arial, Helvetica, sans-serif" size="2">
                <b>Menu Infirmier :</b>
                <a href="${pageContext.request.contextPath}/infirmier/dashboard">Dashboard</a>
                |
                <a href="${pageContext.request.contextPath}/infirmier/patients/ajouter">Ajouter un patient</a>
                |
                <a href="${pageContext.request.contextPath}/infirmier/patients">Patients</a>
                |
                <a href="${pageContext.request.contextPath}/infirmier/liste-attente">Liste d'attente</a>
                |
                <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
            </font>
        </td>
    </tr>
</table>

<hr>