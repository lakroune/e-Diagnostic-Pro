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
            background: #ffffff;
            color: #111111;
        }

        .container {
            width: 85%;
            margin: 40px auto;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        td {
            padding: 10px;
            vertical-align: top;
        }

        input, select, button {
            width: 100%;
            box-sizing: border-box;
            padding: 8px 10px;
            border: 1px solid #000000;
            font-size: 14px;
            background: #ffffff;
        }

        .extra-fields {
            display: none;
            margin-top: 10px;
        }

        .extra-fields.active {
            display: block;
        }

        .actions {
            margin-top: 20px;
            text-align: center;
        }

        .btn {
            display: inline-block;
            min-width: 180px;
            margin: 5px;
            padding: 10px 14px;
            border: 1px solid #000000;
            background: #ffffff;
            color: #000000;
            text-decoration: none;
            cursor: pointer;
        }

        .alert, .success {
            border: 1px solid #000000;
            padding: 10px;
            margin-bottom: 15px;
            background: #f5f5f5;
        }
    </style>
</head>
<body>
    <table width="100%" border="0" cellpadding="8" cellspacing="0">
        <tr>
            <td bgcolor="#f2f2f2" valign="middle">
                <font face="Arial, Helvetica, sans-serif" size="2">
                    <b>Menu Admin :</b>
                    <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
                    |
                    <a href="${pageContext.request.contextPath}/admin/utilisateurs/ajouter">Ajouter un utilisateur</a>
                    |
                    <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
                </font>
            </td>
        </tr>
    </table>
    <hr>

    <div class="container">
        <h1>Ajouter un utilisateur</h1>

        <c:if test="${not empty error}">
            <div class="alert">${error}</div>
        </c:if>

        <c:if test="${not empty success}">
            <div class="success">${success}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/admin/utilisateurs/ajouter">
            <table>
                <tr>
                    <td><label>Nom</label></td>
                    <td><input type="text" name="nom" required></td>
                    <td><label>Prénom</label></td>
                    <td><input type="text" name="prenom" required></td>
                </tr>
                <tr>
                    <td><label>Email</label></td>
                    <td><input type="email" name="email" required></td>
                    <td><label>Téléphone</label></td>
                    <td><input type="text" name="telephone"></td>
                </tr>
                <tr>
                    <td><label>Mot de passe</label></td>
                    <td><input type="password" name="motDePasse" required></td>
                    <td><label>Type utilisateur</label></td>
                    <td>
                        <select name="role" id="roleSelect" required>
                            <option value="">-- Sélectionner --</option>
                            <c:forEach var="role" items="${roles}">
                                <option value="${role}">${role}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>

            <div id="infirmierFields" class="extra-fields">
                <table>
                    <tr>
                        <td style="width: 25%;"><label>Matricule professionnel</label></td>
                        <td><input type="text" name="matriculePro"></td>
                    </tr>
                </table>
            </div>

            <div id="generalisteFields" class="extra-fields">
                <table>
                    <tr>
                        <td style="width: 25%;"><label>Matricule d'ordre</label></td>
                        <td><input type="text" name="matriculeOrdre"></td>
                    </tr>
                </table>
            </div>

            <div id="specialisteFields" class="extra-fields">
                <table>
                    <tr>
                        <td><label>Spécialité</label></td>
                        <td>
                            <select name="specialite">
                                <option value="">-- Sélectionner --</option>
                                <c:forEach var="specialite" items="${specialites}">
                                    <option value="${specialite}">${specialite}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><label>Tarif expertise</label></td>
                        <td><input type="number" step="0.01" name="tarifExpertise"></td>
                    </tr>
                    <tr>
                        <td><label>Durée consultation (minutes)</label></td>
                        <td colspan="3"><input type="number" min="15" name="dureeConsultationMin" value="30"></td>
                    </tr>
                </table>
            </div>

            <div class="actions">
                <button type="submit" class="btn">Créer l'utilisateur</button>
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