<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Connexion étudiant</title>
  </head>
  <body>
    <h1>Connexion étudiant</h1>
    <form action="/login/log_user", method="post">
      <label for="email">Email :</label>
      <input type="email" id="email" name="email" required><br><br>
      <label for="motdepasse">Mot de passe :</label>
      <input type="password" id="motdepasse" name="mdp" required><br><br>
      <input type="submit" value="Se connecter">
    </form>
    <p>Vous avez oublié votre mot de passe ? <a href="">Cliquez ici</a> pour le récupérer.</p>
  </body>
</html>
