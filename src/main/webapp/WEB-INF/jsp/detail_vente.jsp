<%@ page language="java" import="bo.ArticleVendu" import="bo.Utilisateur" import="bo.Enchere" 
	import="java.time.format.DateTimeFormatter" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%
Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
ArticleVendu av = (ArticleVendu) request.getAttribute("articleVendu");
Enchere e = (Enchere) request.getAttribute("enchere");
Boolean proprietaire = (Boolean) request.getAttribute("proprietaire");
Boolean vainqueur = (Boolean) request.getAttribute("vainqueur");
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
		<div class="row text-center justify-content-center mt-4">
			<div class="col-6">
				<h3><%= request.getAttribute("titre") %></h3>
			</div>
		</div>
		<form action="<%=request.getContextPath()%>/detail_vente" method="POST">
			<div class="row justify-content-center mb-4">
				<div class="col-6">
					<h5><label for="nom" class="form-label"><%= av.getNom() %></label></h5>
				</div>
			</div>
			<div class="row justify-content-center mb-4">
				<div class="col-2">
					<label for="lblDescription" class="form-label">Description :</label>
				</div>
				<div class="col-4">
					<label for="description" class="form-label"><%= av.getDescription() %></label> 
				</div>
			</div>
			<div class="row justify-content-center mb-4">
				<div class="col-2">
					<label for="lblCategorie" class="form-label">Catégorie :</label>
				</div>
				<div class="col-4">
					<label for="categorie" class="form-label"><%= av.getLaCategorie().getLibelle() %></label>
				</div>
			</div>
			<div class="row justify-content-center mb-4">
				<div class="col-2">
					<label for="lblOffreMax" class="form-label">Meilleure offre :</label>
				</div>
				<div class="col-4">
				<% if (e != null) { %>
					<label for="offreMax" class="form-label"><%= e.getMontant() %> points par <%= e.getLeAcheteur().getPseudo() %></label>
				<% } else { %>
					<label for="offreMax" class="form-label">Aucune</label>
				<% } %>
				</div>
			</div>
			<div class="row justify-content-center mb-4">
				<div class="col-2">
					<label for="lblPrixInitial" class="form-label">Mise à prix :</label>
				</div>
				<div class="col-4">
					<label for="prixInitial" class="form-label"><%= av.getPrixInitial() %> points</label>
				</div>
			</div>
			<div class="row justify-content-center mb-4">
				<div class="col-2">
					<label for="lblDateFin" class="form-label">Fin de l'enchère :</label>
				</div>
				<div class="col-4">
					<label for="dateFin" class="form-label"><%= av.getDateFin().format(formatters) %></label>
				</div>
			</div>
			<div class="row justify-content-center mb-4">
				<div class="col-2">
					<label for="lblAdresse" class="form-label">Retrait :</label>
				</div>
				<div class="col-4">
					<div class="row justify-content-center">
						<label for="rue" class="form-label"><%= av.getRue() %></label>
						<label for="ville" class="form-label"><%= av.getCodePostal() %> <%= av.getVille() %></label>					
					</div>							
				</div>
			</div>
			<div class="row justify-content-center mb-4">
				<div class="col-2">
					<label for="lblVendeur" class="form-label">Vendeur :</label>
				</div>
				<div class="col-4">
					<label for="vendeur" class="form-label"><%= av.getLeVendeur().getPseudo() %></label>
				</div>
			</div>
			<% if (av.getEtat().equalsIgnoreCase("c")) { %>			
			<div class="row justify-content-center mb-4">
				<div class="col-2">
					<label for="offre" class="form-label">Ma proposition :</label>
				</div>
				<% if (u.getCredit() >= (e != null ? e.getMontant() : av.getPrixInitial())) { %>
				<div class="col-2">
					<input type="number" min="<%= e != null ? e.getMontant() : av.getPrixInitial() %>"
					max="<%= u.getCredit() %>" class="form-control" name="offre" 
					value="<%= e != null ? e.getMontant() : av.getPrixInitial() %>" 
					required>
				</div>					
				<div class="col-2">
					<button type="submit" name="insert" class="btn btn-primary">Enchérir</button>
				</div>
				<% } else { %>
				<div class="col-4">
					<label class="form-label">Crédits insuffisants</label>
				</div>
				<% } %>
			</div>
			<% } else if (av.getEtat().equalsIgnoreCase("t")) { %>
				<% if (vainqueur) { %>
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="lblTelephone" class="form-label">Tél :</label>
					</div>
					<div class="col-4">
						<label for="telephone" class="form-label"><%= e != null ? e.getLeArticle().getLeVendeur().getTelephone() : "Non communiqué" %></label>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<div class="col-6">
						<button type="button" onclick="location.href='<%= request.getContextPath() %>/liste'" 
						name="retour" class="btn btn-primary">Retour
						</button>
					</div>
				</div>
				<% } else if (proprietaire) { %>
				<div class="row justify-content-center mb-4">
					<div class="col-6">
						<button type="submit" name="retrait" class="btn btn-primary">Retrait effectué</button>
					</div>
				</div>
				<% } %>
			<% } else if (av.getEtat().equalsIgnoreCase("r")) { %>
			<div class="row justify-content-center mb-4">
				<div class="col-6">
					<label class="form-label fw-bold">Retrait de l'article déjà effectué</label>
				</div>
			</div>
			<% } %>
			<input name="idUtilisateur" value="<%= u.getIdUtilisateur() %>" type="hidden"> 
			<input name="pseudo" value="<%= u.getPseudo() %>" type="hidden">
		</form>
	</main>
</div>
</body>
</html>