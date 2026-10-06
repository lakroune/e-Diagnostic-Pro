<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<style>
    .menu {
        position: sticky;
        top: 0;
        z-index: 1000;
        background: white;
        border-bottom: 1px solid black;
        padding: 20px;
        text-align: center;
        box-sizing: border-box;
    }

    .menu a {
        margin: 0 15px;
        text-decoration: none;
        color: black;
    }
</style>

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

    <a href="${pageContext.request.contextPath}/infirmier/liste-attente">
        Liste d'attente
    </a>

    <a href="${pageContext.request.contextPath}/logout">
        Déconnexion
    </a>

</nav>