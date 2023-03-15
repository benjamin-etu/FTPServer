<%@page language="java" import="com.example.app.model.*,java.util.*" %>
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
    <% FeuillePresence feuille = (FeuillePresence) request.getAttribute("feuille"); %>
    <h1>Fiche du <%= feuille.getMois() %>/<%= feuille.getAnnee() %></h1>
    <h3>Ajouter une ligne</h3>
      <form action="/manager/feuille/create/ligne/<%= feuille.getId() %>" method="post">
          <label for="jour">Jour (numero) :</label>
          <input min="1" max="31" type="number" id="jour" name="jour" required><br><br>
          <label for="hd">Horaire debut :</label>
          <input type="text" valueid="hd" name="hd" required><br><br>
          <label for="hf">Horaire fin :</label>
          <input type="text" valueid="hf" name="hf" required><br><br>
          <label for="subject">Matiere :</label>
          <input type="text" valueid="subject" name="subject" required><br><br>
          <label for="teacher">Enseignant :</label>
          <input type="text" valueid="teacher" name="teacher" required><br><br>
          <input type="submit" value="Creer">
      </form>
    <h3>Lignes de presence</h3>
    <table>
      <thead>
        <th>Actions</th>
        <th>Jour</th>
        <th>Heure Debut</th>
        <th>Heure Fin</th>
        <th>Matiere</th>
        <th>Enseignant</th>
      </thead>
      <% for (LignePresence lp : (List<LignePresence>) feuille.getLignesPresences()) { %>
          <tr>
              <td><a href="/manager/delete/ligne/<%= lp.getId() %>?feuilleId=<%= feuille.getId() %>"> Supprimer</a></td>
              <td><%= lp.getDayOfMonth() %></td>
              <td><%= lp.getHStart() %></td>
              <td><%= lp.getHEnd() %></td>
              <td><%= lp.getSubject() %></td>
              <td><%= lp.getTeacherName() %></td>
          </tr>
      <% } %>
    </table>
  </body>
</html>
