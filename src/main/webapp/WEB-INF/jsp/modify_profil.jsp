<%@ page import="bo.Utilisateur"  import="servlet.Modify_profil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>ENI-Enchères</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/view_profil?user="<%=u != null ? u.getIdUtilisateur() : ""%>><img alt=""
				src="https://www.sdis68.fr/dynamic/images/le_sdis68/cac_/logo_encheres!_283x200!_3!_0x0!_0!_FFFFFF.png"></a>
			<div class="collapse navbar-collapse " id="navbarSupportedContent">
				<h1 style="margin-left: 465px;">ENI ENCHERES</h1>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="row justify-content-md-center">
				<div class="col-md-auto">
					<h2>Mon Profil</h2>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label></label>
				</div>
			</div>
			<form method="post" action="/projetEncheres/liste_enchere">
				<table class="table table-borderless">
					<tr>
						<td><label for="texte">Pseudo : </label></td>
						<td><input type="text" name="pseudo" id="pseudo" value="<%=u != null ? u.getPseudo() : ""%>" required/></td>
						<td></td>
						<td><label for="texte">Nom : </label></td>
						<td><input type="text" name="last_name" id="last_name" value="<%=u != null ? u.getNom() : ""%>" required/></td>
					</tr>
					<tr>
						<td><label for="texte">Prénom : </label></td>
						<td><input type="text" name="first_name" id="first_name" value="<%=u != null ? u.getPrenom() : ""%>" required/></td>
						<td></td>
						<td><label for="texte">Email : </label></td>
						<td><input type="text" name="email" id="email" value="<%=u != null ? u.getEmail() : ""%>" required/></td>
					</tr>
					<tr>
						<td><label for="texte">Téléphone : </label></td>
						<td><input type="text" name="phone" id="phone" value="<%=u != null ? u.getTelephone() : ""%>" required/></td>
						<td></td>
						<td><label for="texte">Rue : </label></td>
						<td><input type="text" name="road" id="road" value="<%=u != null ? u.getRue() : ""%>" required/></td>
					</tr>
					<tr>
						<td><label for="texte">Code Postal : </label></td>
						<td><input type="text" name="cp" id="cp" value="<%=u != null ? u.getCodePostal() : ""%>" required/></td>
						<td></td>
						<td><label for="texte">Ville : </label></td>
						<td><input type="text" name="city" id="city" value="<%=u != null ? u.getVille() : ""%>" required/></td>
					</tr>
					<tr>
						<td><label for="texte">Mot de passe actuel : </label></td>
						<td><input type="password" name="mdp" id="mdp" value="<%=u != null ? u.getMotDePasse() : ""%>" required/></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="texte">Nouveau Mot de passe : </label></td>
						<td><input type="password" name="newmdp" id="newmdp" /></td>
						<td></td>
						<td><label for="texte">Confirmation : </label></td>
						<td><input type="password" name="compare" id="compare" /></td>
					</tr>
					<tr>
						<td><label for="texte">Crédit : </label></td>
						<td><label for="text" id="credit"><%=u != null ? u.getCredit() : 0%></label></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<div class="row">
					<div class="col">
						<label></label>
					</div>
				</div>
				<div class="row justify-content-md-center">
					<div class="col col-lg-2">
						<input type="submit" class="btn btn-outline-primary"
							value="Enregistrer" />
					</div>
					<div class="col col-lg-2">
						<a class="btn btn-outline-danger"
							href="${pageContext.request.contextPath}/liste_enchere">Supprimer
							mon Compte</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
	integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>
</html>