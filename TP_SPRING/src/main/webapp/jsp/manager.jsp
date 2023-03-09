<%@page language="java" import="com.example.app.model.*,java.util.List" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Manager</title>
    <style>
      body {
        font-family: "Montserrat", sans-serif;
        margin: 0;
        padding: 0;
      }

      h1, h3 {
        text-align: center;
      }

      form {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: 20px 0;
      }

      label {
        font-weight: bold;
        margin-right: 10px;
      }

      input[type="number"] {
        padding: 5px;
        margin-bottom: 10px;
        border-radius: 5px;
        border: 1px solid #ccc;
        width: 100px;
      }

      input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        padding: 10px 20px;
        cursor: pointer;
        font-size: 16px;
        margin-top: 10px;
      }

      table {
        border-collapse: collapse;
        margin: 20px auto;
        font-size: 16px;
      }

      th, td {
        border: 1px solid #ccc;
        padding: 10px;
        text-align: center;
      }

      th {
        background-color: #007bff;
        color: #fff;
        font-weight: bold;
      }

      tr:nth-child(even) {
        background-color: #f2f2f2;
      }

      a {
        color: #007bff;
        text-decoration: none;
        margin-right: 10px;
      }

      a:hover {
        text-decoration: underline;
      }
    </style>
  </head>
  <body>
    <h1>Manager</h1>
    <h3>Creer une nouvelle feuille</h3>
    <form action="/manager/create" method="post">
        <label for="annee">Annee :</label>
        <input min="0" type="number" id="annee" name="annee" required><br><br>
        <label for="mois">Mois :</label>
        <input min="1" max="12" type="number" valueid="mois" name="mois" required><br><br>
        <input type="submit" value="Creer">
    </form>
    <h3>Vos fiches</h3>
    <table>
      <thead><th>Actions</th><th>Mois</th><th>Annee</th></thead>
      <% for (FeuillePresence fp : (List<FeuillePresence>) request.getAttribute("feuilles")) { %>
          <tr>
              <td><a href="/manager/feuille/<%= fp.getId() %>">Afficher</a>/<a href="/manager/delete/feuille/<%= fp.getId() %>">Supprimer</a></td>
              <td><%= fp.getMois() %></td>
              <td><%= fp.getAnnee() %></td>
          </tr>
      <% } %>
  </table>
  </body>
</html>
