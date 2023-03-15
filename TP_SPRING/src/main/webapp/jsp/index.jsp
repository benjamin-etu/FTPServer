<!DOCTYPE html>
<html lang="fr">
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

.container {
  background-color: #f7f7f7;
  padding: 20px;
  margin: 10%;
  border-radius: 10px;
}

h1 {
  font-size: 3em;
  color: #3f51b5;
  margin-top: 2em;
  margin-bottom: 1em;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

li {
  margin: 10px 0;
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




</style>
<head>
  <meta charset="UTF-8">
  <title>Page d'accueil</title>
</head>
<body>
  <div class="container">
    <h1>Bienvenue sur le Gestionnaire de Fiche de Presence</h1>
     ${welcome_message}
  <ul>
    <li><a href="/register">Creer un compte</a></li>
    <li><a href="/login">Se connecter</a></li>
    <li><a href="/logout">Se deconnecter</a></li>
    ${link_if_user_connected}
  </ul>
  </div>
</body>
</html>
