<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Ajouter un utilisateur</title>

            <style>
                body {
                    margin: 0;
                    font-family: Arial, sans-serif;
                }

                .menu {
                    border-bottom: 1px solid black;
                    padding: 20px;
                    text-align: center;
                }

                .menu a {
                    margin: 0 15px;
                    text-decoration: none;
                    color: black;
                }

                .container {
                    width: 80%;
                    margin: 50px auto;
                }

                h1 {
                    text-align: center;
                }

                form {
                    margin-top: 20px;
                }

                .row {
                    display: flex;
                    gap: 20px;
                    margin-bottom: 15px;
                }

                label {
                    display: flex;
                    flex-direction: column;
                    gap: 8px;
                    width: 100%;
                }

                input,
                select,
                button {
                    padding: 8px 10px;
                    font-size: 14px;
                    border: 1px solid black;
                }

                .extra-fields {
                    display: none;
                    margin-top: 15px;
                }

                .extra-fields.active {
                    display: block;
                }

                .actions {
                    margin-top: 20px;
                    display: flex;
                    gap: 10px;
                }

                .btn {
                    padding: 10px 20px;
                    border: 1px solid black;
                    background: white;
                    color: black;
                    text-decoration: none;
                    display: inline-block;
                    cursor: pointer;
                }

                .alert {
                    border: 1px solid black;
                    padding: 10px;
                    margin-bottom: 15px;
                }

                .success {
                    border: 1px solid black;
                    padding: 10px;
                    margin-bottom: 15px;
                    background: #f3f3f3;
                }
            </style>
        </head>

        <body>
            <nav class="menu">
                <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                <a href="${pageContext.request.contextPath}/admin/utilisateurs/ajouter">Ajouter un utilisateur</a>
                <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
            </nav>

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
                        <a class="btn" href="${pageContext.request.contextPath}/admin/dashboard">Retour</a>
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