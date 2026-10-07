<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dossier Patient - Généraliste</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            color: #111;
        }

        .container {
            width: 92%;
            max-width: 1200px;
            margin: 40px auto;
        }

        h1, h2 {
            margin: 0 0 18px;
            text-align: center;
        }

        .card {
            background: white;
            border: 1px solid #d9d9d9;
            padding: 20px;
            margin-bottom: 25px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }

        th, td {
            border: 1px solid #cfcfcf;
            padding: 10px 12px;
            text-align: left;
            vertical-align: top;
        }

        th {
            width: 30%;
            background: #f1f1f1;
        }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(2, minmax(260px, 1fr));
            gap: 18px;
        }

        .full-width {
            grid-column: 1 / -1;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: 600;
        }

        input, textarea, select, button {
            width: 100%;
            box-sizing: border-box;
            padding: 10px 12px;
            border: 1px solid #111;
            font-family: Arial, sans-serif;
            background: white;
        }

        textarea {
            min-height: 110px;
            resize: vertical;
        }

        button {
            cursor: pointer;
            background: #111;
            color: white;
            border: none;
            font-weight: 600;
        }

        .muted {
            color: #555;
        }
    </style>
</head>
<body>

    <jsp:include page="menu.jsp" />

    <div class="container">
        <h1>Dossier patient - Généraliste</h1>

        <div class="card">
            <h2>Informations du patient</h2>
            <table>
                <tr>
                    <th>Nom</th>
                    <td>Martin</td>
                </tr>
                <tr>
                    <th>Prénom</th>
                    <td>Sofia</td>
                </tr>
                <tr>
                    <th>Date de naissance</th>
                    <td>15/03/1988</td>
                </tr>
                <tr>
                    <th>N° sécurité sociale</th>
                    <td>188-038-123-456</td>
                </tr>
                <tr>
                    <th>Téléphone</th>
                    <td>0666554433</td>
                </tr>
                <tr>
                    <th>Adresse</th>
                    <td>12 rue de la Santé, Casablanca</td>
                </tr>
                <tr>
                    <th>Mutuelle</th>
                    <td>Axa Santé</td>
                </tr>
                <tr>
                    <th>Antécédents</th>
                    <td>Hypertension artérielle</td>
                </tr>
                <tr>
                    <th>Allergies</th>
                    <td>Aucune</td>
                </tr>
                <tr>
                    <th>Traitements en cours</th>
                    <td>Amoxicilline, vitamine C</td>
                </tr>
            </table>
        </div>

        <div class="card">
            <h2>Signes vitaux saisis par l'infirmier</h2>
            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Tension</th>
                        <th>Fréq. cardiaque</th>
                        <th>Température</th>
                        <th>Fréq. respiratoire</th>
                        <th>Poids</th>
                        <th>Taille</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>07/10/2026 08:30</td>
                        <td>12/8</td>
                        <td>78 bpm</td>
                        <td>36,8 °C</td>
                        <td>18 /min</td>
                        <td>68 kg</td>
                        <td>165 cm</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="card">
            <h2>Consultation du généraliste</h2>

            <form method="post" action="${pageContext.request.contextPath}/medecin/consultation">
                <div class="form-grid">
                    <div>
                        <label for="motif">Motif de consultation</label>
                        <input type="text" id="motif" name="motif" value="Fièvre et douleurs thoraciques" />
                    </div>

                    <div>
                        <label for="statut">Statut</label>
                        <select id="statut" name="statut">
                            <option value="EN_COURS" selected>En cours</option>
                            <option value="TERMINEE">Terminé</option>
                            <option value="EN_ATTENTE">En attente</option>
                        </select>
                    </div>

                    <div class="full-width">
                        <label for="examenClinique">Examen clinique</label>
                        <textarea id="examenClinique" name="examenClinique">Inspection générale normale. Auscultation pulmonaire sans râles. Toux discrète, respiration régulière.</textarea>
                    </div>

                    <div class="full-width">
                        <label for="symptomes">Analyse des symptômes</label>
                        <textarea id="symptomes" name="symptomes">Le patient décrit une fièvre légère associée à une douleur thoracique intermittente. Symptomatologie évoluant depuis 48 heures.</textarea>
                    </div>

                    <div class="full-width">
                        <label for="diagnostic">Diagnostic</label>
                        <textarea id="diagnostic" name="diagnostic">Suspicion d'infection respiratoire basse. À confirmer par examens complémentaires.</textarea>
                    </div>

                    <div class="full-width">
                        <label for="observations">Observations / conclusion</label>
                        <textarea id="observations" name="observations">Patient stable hémodynamiquement. Recommandation de surveillance, examens complémentaires et traitement symptomatique.</textarea>
                    </div>

                    <div class="full-width">
                        <label for="ordonnance">Ordonnance / traitement</label>
                        <textarea id="ordonnance" name="ordonnance">Paracétamol 1 g si besoin ; solution saline ; repos et hydratation abondante.</textarea>
                    </div>
                </div>

                <div style="margin-top: 20px;">
                    <button type="submit">Enregistrer la consultation</button>
                </div>
            </form>
        </div>
    </div>

</body>
</html>
