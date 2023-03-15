<!DOCTYPE html>
<html>
  <head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap" rel="stylesheet">
    <style>
      body {
        font-family: 'Oswald', sans-serif;
  background-color: #f2f2f2;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

h1 {
  font-size: 3em;
  color: #3f51b5;
  margin-top: 2em;
  margin-bottom: 1em;
}

a {
  color: #555; /* Nouvelle couleur de texte */
  background-color: #ddd; /* Nouvelle couleur de fond */
  padding: 10px 20px;
  text-decoration: none;
  border-radius: 5px;
  display: block;
}

a:hover {
  background-color: #aaa; /* Nouvelle couleur de fond au survol */
}

form {
  background-color: #f7f7f7;
  padding: 20px;
  width: 80%;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

label {
  display: inline-block;
  margin-bottom: 10px;
  font-weight: bold;
  color: #555;
}

input[type="email"],
input[type="text"],
input[type="password"] {
  display: block;
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
  border: none;
}

input[type="submit"] {
  padding: 10px 20px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  color: #555; /* Nouvelle couleur de texte */
  background-color: #ddd; /* Nouvelle couleur de fond */
  padding: 10px 20px;
  text-decoration: none;
  border-radius: 5px;
  display: block;
}

input[type="submit"]:hover{
  background-color: #aaa; /* Nouvelle couleur de fond au survol */
}


    </style>
    <meta charset="UTF-8">
    <title>Inscription étudiant</title>
  </head>
  <body>
    <h1>Inscription</h1>
    <form action="/register/create_user" method="post">
      <label for="email">Email :</label>
      <input type="email" id="email" name="email" required><br><br>
      <label for="nom">Nom :</label>
      <input type="text" id="nom" name="nom" required><br><br>
      <label for="prenom">Prenom :</label>
      <input type="text" id="prenom" name="prenom" required><br><br>
      <label for="motdepasse">Mot de passe :</label>
      <input type="password" id="motdepasse" name="mdp" required><br><br>
      <input type="submit" value="S'inscrire">
    </form>
  </body>
</html>
