<%@ page language="java" import="bo.ArticleVendu" import="bo.Utilisateur" import="bo.Enchere" 
	import="java.time.format.DateTimeFormatter" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%
Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
ArticleVendu av = (ArticleVendu) request.getAttribute("articleVendu");
Enchere e = (Enchere) request.getAttribute("enchere");

DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="./assets/style.css" rel="stylesheet">
	<title>Détail vente</title>
</head>
<body>
<div class="container-fluid">
	<header>
		<nav class="navbar navbar-expand-lg bg-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="<%=request.getContextPath()%>/liste">
					<img alt=""
					src="https://www.sdis68.fr/dynamic/images/le_sdis68/cac_/logo_encheres!_283x200!_3!_0x0!_0!_FFFFFF.png">
				</a>
				<div class="collapse navbar-collapse " id="navbarSupportedContent">
					<h1 style="margin-left: 465px;">ENI ENCHERES</h1>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<div class="row text-center" style="margin-top: 2%;">
			<h2>Détail vente</h2>
		</div>
		<div class="row">
			<div class="col-6 offset-3">
				<form action="<%=request.getContextPath()%>/detail_vente" method="POST">
					<div class="mb-3">
						<label for="nom" class="form-label"><%= av.getNom() %></label> 
					</div>
					<div class="mb-3">
						<label for="lblDescription" class="form-label">Description : </label>
						<label for="description" class="form-label"><%= av.getDescription() %></label> 
					</div>
					<div class="mb-3">
						<label for="lblCategorie" class="form-label">Catégorie : </label>
						<label for="categorie" class="form-label"><%= av.getLaCategorie().getLibelle() %></label>
					</div>
					<div class="mb-3">
						<label for="lblOffreMax" class="form-label">Meilleure offre : </label>
						<% if (e != null) { %>
						<label for="offreMax" class="form-label"><%= e.getMontant() %> points par <%= e.getLeAcheteur().getPseudo() %></label>
						<% } else { %>
						<label for="offreMax" class="form-label">Aucune offre</label>
						<% } %>
					</div>
					<div class="mb-3">
						<label for="lblPrixInitial" class="form-label">Mise à prix : </label>
						<label for="prixInitial" class="form-label"><%= av.getPrixInitial() %> points</label>
					</div>
					<div class="mb-3">
						<label for="lblDateFin" class="form-label">Fin de l'enchère : </label>
						<label for="dateFin" class="form-label"><%= av.getDateFin().format(formatters) %></label>
					</div>
					<div class="mb-3">
						<label for="lblAdresse" class="form-label">Retrait : </label>
						<label for="rue" class="form-label"><%= av.getRue() %></label>
						<label for="ville" class="form-label"><%= av.getCodePostal() %> <%= av.getVille() %></label>
					</div>
					<div class="mb-3">
						<label for="lblVendeur" class="form-label">Vendeur : </label>
						<label for="vendeur" class="form-label"><%= av.getLeVendeur().getPseudo() %></label>
					</div>
					<div class="mb-3">
						<span>
							<label for="offre" class="form-label">Ma proposition : </label>
							<input type="number" min="<%= e != null ? e.getMontant() : av.getPrixInitial() %>" class="form-control" 
								name="offre" value="<%= e != null ? e.getMontant() : av.getPrixInitial() %>" required>
							<button type="submit" name="udpate" class="btn btn-primary">Enchérir</button>
						</span>
					</div>
						<input name="idUtilisateur" value="<%=u.getIdUtilisateur()%>" type="hidden"> 
						<input name="pseudo" value="<%=u.getPseudo()%>" type="hidden">
				</form>
			</div>
		</div>
	</main>
</div>
</body>
</html>