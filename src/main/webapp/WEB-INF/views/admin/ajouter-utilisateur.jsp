<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un utilisateur</title>
</head>
<body>
    <div class="container">
        <h1>Ajouter un utilisateur</h1>

        <c:if test="${not empty error}">
            <div class="alert">${error}</div>
        </c:if>

        <c:if test="${not empty success}">
            <div class="success">${success}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/admin/utilisateurs/ajouter">
            <div class="row">
                <label>
                    Nom
                    <input type="text" name="nom" required>
                </label>

                <label>
                    Prénom
                    <input type="text" name="prenom" required>
                </label>
            </div>

            <div class="row">
                <label>
                    Email
                    <input type="email" name="email" required>
                </label>

                <label>
                    Téléphone
                    <input type="text" name="telephone">
                </label>
            </div>

            <div class="row">
                <label>
                    Mot de passe
                    <input type="password" name="motDePasse" required>
                </label>

                <label>
                    Type utilisateur
                    <select name="role" id="roleSelect" required>
                        <option value="">-- Sélectionner --</option>
                        <c:forEach var="role" items="${roles}">
                            <option value="${role}">${role}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>

            <div id="infirmierFields" class="extra-fields">
                <div class="row">
                    <label>
                        Matricule professionnel
                        <input type="text" name="matriculePro">
                    </label>
                </div>
            </div>

            <div id="generalisteFields" class="extra-fields">
                <div class="row">
                    <label>
                        Matricule d'ordre
                        <input type="text" name="matriculeOrdre">
                    </label>
                </div>
            </div>

            <div id="specialisteFields" class="extra-fields">
                <div class="row">
                    <label>
                        Spécialité
                        <select name="specialite">
                            <option value="">-- Sélectionner --</option>
                            <c:forEach var="specialite" items="${specialites}">
                                <option value="${specialite}">${specialite}</option>
                            </c:forEach>
                        </select>
                    </label>

                    <label>
                        Tarif expertise
                        <input type="number" step="0.01" name="tarifExpertise">
                    </label>
                </div>

                <div class="row">
                    <label>
                        Durée consultation (minutes)
                        <input type="number" min="15" name="dureeConsultationMin" value="30">
                    </label>
                </div>
            </div>

            <div class="actions">
                <button class="btn" type="submit">Créer l'utilisateur</button>
                <a class="btn secondary" href="${pageContext.request.contextPath}/admin/dashboard">Retour</a>
            </div>
        </form>
    </div>

    <script>
        const roleSelect = document.getElementById('roleSelect');
        const fieldSets = {
            INFIRMIER: document.getElementById('infirmierFields'),
            GENERALISTE: document.getElementById('generalisteFields'),
            SPECIALISTE: document.getElementById('specialisteFields')
        };

        function toggleRoleFields() {
            const selectedRole = roleSelect.value;
            Object.values(fieldSets).forEach(field => field.classList.remove('active'));
            if (fieldSets[selectedRole]) {
                fieldSets[selectedRole].classList.add('active');
            }
        }

        roleSelect.addEventListener('change', toggleRoleFields);
        toggleRoleFields();
    </script>
</body>
</html>
