<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Connexion</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 30px;
            }

            .login-container {
                width: 300px;
                margin: 50px auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            h2 {
                margin-top: 0;
                text-align: center;
            }

            form {
                margin-top: 20px;
            }

            label {
                display: block;
                margin-top: 10px;
                font-weight: bold;
            }

            input {
                margin: 5px 0 15px 0;
                display: block;
                padding: 8px;
                width: 100%;
                box-sizing: border-box;
            }

            button {
                padding: 10px;
                width: 100%;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 3px;
                cursor: pointer;
            }

            button:hover {
                background-color: black;
            }

            .error-message {
                color: red;
                margin-bottom: 15px;
                text-align: center;
            }
        </style>
    </head>

    <body>

        <div class="login-container">
            <h2>Connexion</h2>

            <%-- Affichage d'un message d'erreur si l'authentification échoue --%>
                <% String error=(String) request.getAttribute("error"); %>
                    <% if (error !=null) { %>
                        <div class="error-message">
                            <%= error %>
                        </div>
                        <% } %>

                            <form method="post" action="${pageContext.request.contextPath}/login">
                                <label for="email">Email</label>
                                <input type="email" id="email" name="email" required
                                    placeholder="Ex: email@example.com">

                                <label for="password">Mot de passe</label>
                                <input type="password" id="password" name="password" required>

                                <button type="submit">Se connecter</button>
                            </form>
        </div>

    </body>

    </html>