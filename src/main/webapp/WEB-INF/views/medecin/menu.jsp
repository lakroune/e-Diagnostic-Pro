<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<style>
    .menu {
        border-bottom: 1px solid #111;
        padding: 18px 20px;
        text-align: center;
        background: white;
    }

    .menu a {
        margin: 0 12px;
        text-decoration: none;
        color: #111;
        font-weight: 600;
    }
</style>

<nav class="menu">
    <a href="${pageContext.request.contextPath}/medecin/dashboard">Dashboard</a>
    <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
</nav>
