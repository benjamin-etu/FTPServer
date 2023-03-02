<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Inscription étudiant</title>
  </head>
  <body>
    <h1>Inscription étudiant</h1>
    <form action="/register/create_user" method="post">
      <label for="email">Email :</label>
      <input type="email" id="email" name="email" required><br><br>
      <label for="nom">Nom :</label>
      <input type="text" id="nom" name="nom" required><br><br>
      <label for="prenom">Prénom :</label>
      <input type="text" id="prenom" name="prenom" required><br><br>
      <label for="motdepasse">Mot de passe :</label>
      <input type="password" id="motdepasse" name="mdp" required><br><br>
      <input type="submit" value="S'inscrire">
    </form>
  </body>
</html>
