<%@page language="java" import="com.example.app.model.*,java.util.List" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Manager</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap" rel="stylesheet">
    <style>

body {
      background-color: #f2f2f2;
      font-family: 'Oswald', sans-serif;
      text-align: center;
      display: flex;
      align-items: center;
      justify-content: center;
      margin: 0 auto;
    }
   
   #block {
  background-color: #f7f7f7;
  padding: 20px;
  margin: 10%;
  border-radius: 10px;
  width: 80%;
}

h1 {
  font-size: 3em;
  color: #3f51b5;
  margin-top: 2em;
  margin-bottom: 1em;
}

h3 {
  font-size: 1.5em;
  color: #3f51b5;
  margin-top: 2em;
  margin-bottom: 1em;
}

form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 2em;
}

label {
  font-size: 1.2em;
  margin-bottom: 0.5em;
}

input[type="number"] {
  width: 50%;
  padding: 10px;
  margin-bottom: 1em;
  border: none;
  border-radius: 5px;
}

input[type="submit"] {
  color: #fff;
  background-color: #3f51b5;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  margin-top: 1em;
  cursor: pointer;
}

table {
  width: 100%;
  margin-top: 2em;
  border-collapse: collapse;
}

th, td {
  padding: 10px;
  border: 1px solid #ddd;
  text-align: center;
}

th {
  background-color: #f7f7f7;
  font-size: 1.2em;
  color: #3f51b5;
}

a {
  color: #555;
  background-color: #ddd;
  padding: 10px 20px;
  text-decoration: none;
  border-radius: 5px;
  display: block;
}

a:hover {
  background-color: #aaa;
}
</style>


  </head>
  <body>
    <div id="block">
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
                <td><a href="/manager/feuille/<%= fp.getId() %>">Afficher</a>/<a href="/manager/delete/feuille/<%= fp.getId() %>"> Supprimer</a></td>
                <td><%= fp.getMois() %></td>
                <td><%= fp.getAnnee() %></td>
            </tr>
        <% } %>
      </table>
    </div>
  </body>
</html>
