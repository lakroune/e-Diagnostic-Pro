<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Ajouter un patient</title>

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
                width: 600px;
                margin: 50px auto;
            }

            h1 {
                text-align: center;
                margin-bottom: 30px;
            }

            .form-group {
                margin-bottom: 15px;
            }

            label {
                display: block;
                margin-bottom: 5px;
            }

            input,
            textarea {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
            }

            textarea {
                height: 80px;
                resize: vertical;
            }

            button {
                width: 100%;
                padding: 10px;
                margin-top: 15px;
                cursor: pointer;
            }
        </style>
    </head>

    <body>

        <jsp:include page="menu.jsp" />

        <div class="container">

            <h1>Ajouter un patient</h1>

            <form method="post" action="${pageContext.request.contextPath}/infirmier/patients/ajouter">

                <div class="form-group">
                    <label for="nom">Nom</label>
                    <input type="text" id="nom" name="nom" required>
                </div>

                <div class="form-group">
                    <label for="prenom">Prénom</label>
                    <input type="text" id="prenom" name="prenom" required>
                </div>

                <div class="form-group">
                    <label for="dateNaissance">Date de naissance</label>
                    <input type="date" id="dateNaissance" name="dateNaissance">
                </div>

                <div class="form-group">
                    <label for="numSecuriteSociale">
                        Numéro de sécurité sociale
                    </label>

                    <input type="text" id="numSecuriteSociale" name="numSecuriteSociale">
                </div>

                <div class="form-group">
                    <label for="telephone">Téléphone</label>

                    <input type="text" id="telephone" name="telephone">
                </div>

                <div class="form-group">
                    <label for="adresse">Adresse</label>

                    <input type="text" id="adresse" name="adresse">
                </div>

                <div class="form-group">
                    <label for="mutuelle">Mutuelle</label>

                    <input type="text" id="mutuelle" name="mutuelle">
                </div>

                <div class="form-group">
                    <label for="antecedents">Antécédents</label>

                    <textarea id="antecedents" name="antecedents"></textarea>
                </div>

                <div class="form-group">
                    <label for="allergies">Allergies</label>

                    <textarea id="allergies" name="allergies"></textarea>
                </div>

                <div class="form-group">
                    <label for="traitementsEnCours">
                        Traitements en cours
                    </label>

                    <textarea id="traitementsEnCours" name="traitementsEnCours"></textarea>
                </div>

                <button type="submit">
                    Ajouter le patient
                </button>

            </form>

        </div>

    </body>

    </html>