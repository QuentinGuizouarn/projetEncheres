<%@ page language="java" import="bo.Categorie" import="bo.Utilisateur" import="bo.ArticleVendu" 
	import="java.util.List" import="java.time.LocalDate" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%>
<%
List<Categorie> lesCategories = (List<Categorie>) request.getAttribute("items");
Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
ArticleVendu av = (ArticleVendu) request.getAttribute("articleVendu");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="#" rel="shortcut icon">
	<link href="./assets/css/style.css" rel="stylesheet">
	<title>Nouvelle vente</title>
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
			<div class="row text-center justify-content-center mt-4 mb-4">
				<div class="col-6">
					<h3><%= request.getAttribute("titre") %></h3>
				</div>
			</div>
			<form action="<%=request.getContextPath()%>/nouvelle_vente" method="POST">
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="nom" class="form-label">Article : </label> 
					</div>
					<div class="col-4">
						<input type="text" class="form-control" name="nom" value="<%= av != null ? av.getNom() : "" %>" required>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="description" class="form-label">Description : </label>
					</div>
					<div class="col-4">
						<textarea class="form-control" name="description" required><%= av != null ? av.getDescription() : "" %></textarea>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="categorie" class="form-label">Catégorie : </label> 
					</div>
					<div class="col-4">
						<select name="categorie" class="form-select" aria-label="Default select example" required>
							<% for (Categorie c : lesCategories) { %>
							<option value="<%=c.getIdCategorie()%>_<%=c.getLibelle()%>">
								<%=c.getLibelle()%>
							</option>
							<% } %>
						</select>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="prixInitial" class="form-label">Mise à prix : </label> 
					</div>
					<div class="col-4">
						<input type="number" min="0" class="form-control"name="prixInitial" value="<%= av != null ? av.getPrixInitial() : 0 %>" required>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="dateDebut" class="form-label">Début de l'enchère : </label> 
					</div>
					<div class="col-4">
						<input type="date" onChange="checkDateDebut(event);" id="dateDebutPicker" class="form-control" name="dateDebut" 
						value="<%= av != null ? av.getDateDebut() : LocalDate.now() %>" required>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="dateFin" class="form-label">Fin de l'enchère : </label> 
					</div>
					<div class="col-4">
						<input type="date" id="dateFinPicker" class="form-control" name="dateFin" value="<%= av != null ? av.getDateFin() : "" %>" required>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="rue" class="form-label">Rue : </label> 
					</div>
					<div class="col-4">
						<input type="text" class="form-control" name="rue" value="<%= av != null ? av.getRue() : u.getRue() %>" required>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="codePostal" class="form-label">Code Postal : </label> 
					</div>
					<div class="col-4">
						<input pattern="[0-9]{5}" type="text" class="form-control" name="codePostal" value="<%= av != null ? av.getCodePostal() : u.getCodePostal() %>" required>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<div class="col-2">
						<label for="ville" class="form-label">Ville : </label> 
					</div>
					<div class="col-4">
						<input type="text" class="form-control" name="ville" value="<%= av != null ? av.getVille() : u.getVille() %>" required>
					</div>
				</div>
				<div class="row justify-content-center mb-4">
					<input type="hidden" id="idArticle" name="idArticle" value="<%= av != null ? av.getIdArticle() : 0 %>">
					<div class="inline">
						<% if (av == null || (av != null && av.getEtat().equalsIgnoreCase("n"))) { %>
						<button type="submit" name="insert_update" class="btn btn-primary">Enregistrer</button>
						<button type="reset" class="btn btn-light">Annuler</button>
						<% } %>
						<% if (av != null && av.getEtat().equalsIgnoreCase("n")) { %>
						<button type="submit" onClick="return confirm('Confirmez-vous la suppression ?')" name="delete" class="btn btn-light">Annuler la vente</button>
						<% } %>
					</div>
				</div>
			</form>
		</main>
	</div>
<script>
if (idArticle.value != null) {
	dateFinPicker.min = dateDebutPicker.value;
}
dateDebutPicker.min = new Date().toISOString().split("T")[0];

function checkDateDebut(e) {
	dateFinPicker.min = dateDebutPicker.value;
}
</script>
</body>
</html>