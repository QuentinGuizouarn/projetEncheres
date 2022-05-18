<%@ page language="java" import="bo.Categorie" import="java.util.List" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>    
<% 
	List<Categorie> lesCategories = (List<Categorie>) request.getAttribute("items");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
	<title>Nouvelle vente</title>
</head>
<body>

	<div class="container-fluid">
		<div class="row text-center">
			<h1>Nouvelle vente</h1>
		</div>
		<div class="row">
			<div class="col-6 offset-3">
				<form action="<%= request.getContextPath() %>/nouvelle_vente" method="POST">
				  <div class="mb-3">
				    <label for="nom" class="form-label">Article : </label>
				    <input type="text" class="form-control" name="nom" required>
				  </div>
				  <div class="mb-3">
				    <label for="description" class="form-label">Description : </label>
				    <textarea class="form-control" name="description" required></textarea>
				  </div>
				  <div class="mb-3">
				    <label for="categorie" class="form-label">Catégorie : </label>
				    <select name="categorie" class="form-select" aria-label="Default select example">
				    <% for (Categorie c : lesCategories) { %>
					  <option value="<%= c.getIdCategorie() %>_<%= c.getLibelle() %>">
					  	<%= c.getLibelle() %>
					  </option>
					<% } %>
					</select>
				  </div>
				  <div class="mb-3">
				    <label for="photo" class="form-label">Photo de l'article : </label>
				    <input type="url" class="form-control" name="photo">
				  </div>
				  <div class="mb-3">
				    <label for="prixInitial" class="form-label">Mise à prix : </label>
				    <input type="number" class="form-control" name="prixInitial" required>
				  </div>
				  <div class="mb-3">
				    <label for="dateDebut" class="form-label">Début de l'enchère : </label>
				    <input type="date" class="form-control" name="dateDebut" required>
				  </div>
				  <div class="mb-3">
				    <label for="dateFin" class="form-label">Fin de l'enchère : </label>
				    <input type="date" class="form-control" name="dateFin" required>
				  </div>
				  <div class="mb-3">
				    <label for="rue" class="form-label">Rue : </label>
				    <input type="text" class="form-control" name="rue" required>
				  </div>
				  <div class="mb-3">
				    <label for="codePostal" class="form-label">Code Postal : </label>
				    <input type="text" class="form-control" name="codePostal" required>
				  </div>
				  <div class="mb-3">
				    <label for="ville" class="form-label">Ville : </label>
				    <input type="text" class="form-control" name="ville" required>
				  </div>				  							  
				  <span>
					  <button type="submit" class="btn btn-primary">Enregistrer</button>
					  <button type="reset" class="btn">Annuler</button>
				  </span>
				</form>
			</div>
		</div>
	</div>

</body>
</html>