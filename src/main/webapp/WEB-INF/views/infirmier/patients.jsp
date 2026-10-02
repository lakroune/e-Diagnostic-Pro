
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">

    <title>Liste des patients</title>

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
            margin: 70px auto;
        }

        h1 {
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
        }

        th,
        td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }

    </style>

</head>

<body>

    <jsp:include page="menu.jsp" />

    <div class="container">

        <h1>Liste des patients</h1>

        <table>

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>CIN</th>
                    <th>Téléphone</th>
                </tr>
            </thead>

            <tbody>

                <tr>
                    <td>1</td>
                    <td>Patient</td>
                    <td>Test</td>
                    <td>AB123456</td>
                    <td>0612345678</td>
                </tr>

            </tbody>

        </table>

    </div>

</body>
</html>