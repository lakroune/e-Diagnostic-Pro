<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<nav class="menu">

    <a href="${pageContext.request.contextPath}/infirmier/dashboard">
        Dashboard
    </a>

    <a href="${pageContext.request.contextPath}/infirmier/patients/ajouter">
        Ajouter un patient
    </a>

    <a href="${pageContext.request.contextPath}/infirmier/patients">
        Patients
    </a>

    <a href="${pageContext.request.contextPath}/infirmier/attente">
        Liste d'attente
    </a>

    <a href="${pageContext.request.contextPath}/logout">
        Déconnexion
    </a>

</nav>