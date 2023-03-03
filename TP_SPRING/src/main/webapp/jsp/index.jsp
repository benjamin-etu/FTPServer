<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Page d'accueil</title>
</head>
<body>
  <h1>Bienvenue sur le Gestionnaire de Fiche de Presence</h1>
  ${welcome_message}
  <ul>
    <li><a href="/register">Creer un compte</a></li>
    <li><a href="/login">Se connecter</a></li>
    <li><a href="/logout">Se deconnecter</a></li>
    ${link_if_user_connected}
  </ul>
</body>
</html>
