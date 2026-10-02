<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Connexion</title>

        <style>
            body {
                margin: 0;
                font-family: Arial, sans-serif;
            }

            .login-container {
                width: 300px;
                margin: 150px auto;
            }

            h2 {
                text-align: center;
                margin-bottom: 30px;
            }

            label {
                display: block;
                margin-bottom: 5px;
            }

            input {
                width: 100%;
                padding: 8px;
                margin-bottom: 20px;
                box-sizing: border-box;
            }

            button {
                width: 100%;
                padding: 10px;
                cursor: pointer;
            }

            .error {
                text-align: center;
                margin-bottom: 20px;
            }
        </style>
    </head>

    <body>

        <div class="login-container">

            <h2>Connexion</h2>

            <% String error=(String) request.getAttribute("error"); if (error !=null) { %>
                <div class="error">
                    <%= error %>
                </div>
                <% } %>

                    <form method="post" action="${pageContext.request.contextPath}/login">

                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>

                        <label for="password">Mot de passe</label>
                        <input type="password" id="password" name="password" required>

                        <button type="submit">Se connecter</button>

                    </form>

        </div>

    </body>

    </html>