<%@page import="servlets.Servlet_create_profil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ENI-Enchères</title>
</head>
<body>
	<h1>Mon Profil</h1>
	
	<form method="post" action="/Projet/Servlet">
	<div class="table-responsive">
		<table class="table table-borderless">
    		<tr>
			      <th> <label for="texte">Pseudo : </label> </th>
			      <td> <input type="text" name="pseudo" id="pseudo"/> </td>
			      <th> <label for="texte">Nom : </label> </th>
			      <td> <input type="text" name="last_name" id="last_name"/> </td>
      		</tr>
    		<tr>
			      <th> <label for="texte">Prénom : </label> </th>
			      <td> <input type="text" name="first_name" id="first_name"/> </td>
			      <th> <label for="texte">Email : </label> </th>
			      <td> <input type="text" name="email" id="email"/> </td>
      		</tr>
    		<tr>
			      <th> <label for="nombre">Téléphone : </label> </th>
			      <td> <input type="text" name="phone" id="phone"/> </td>
			      <th> <label for="texte">Rue : </label> </th>
			      <td> <input type="text" name="road" id="road"/> </td>
      		</tr>
    		<tr>
			      <th> <label for="nombre">Code Postal : </label> </th>
			      <td> <input type="text" name="cp" id="cp"/> </td>
			      <th> <label for="texte">Ville : </label> </th>
			      <td> <input type="text" name="city" id="city"/> </td>
      		</tr>
    		<tr>
			      <th> <label for="texte">Mot de passe actuel : </label> </th>
			      <td> <input type="text" name="mdp" id="mdp"/> </td>
			      <th> <label for="texte">Confirmation : </label> </th>
			      <td> <input type="text" name="compare" id="compare"/> </td>
      		</tr>
		</table>
	</div>
	<input type="submit" value="Créer"/>
	<input type="submit" value="Annuler">
	</form>
</body>
</html>