<%@ page language="java" import="bo.Categorie" import="bo.Utilisateur"
	import="java.util.List" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Categorie> lesCategories = (List<Categorie>) request.getAttribute("items");
Utilisateur u = (Utilisateur) request.getAttribute("utilisateur");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet">
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
			<div class="row text-center" style="margin-top: 2%;">
				<h2>Nouvelle vente</h2>
			</div>
			<div class="row">
				<div class="col-6 offset-3">
					<form action="<%=request.getContextPath()%>/nouvelle_vente"
						method="POST">
						<div class="mb-3">
							<label for="nom" class="form-label">Article : </label> <input
								type="text" class="form-control" name="nom" required>
						</div>
						<div class="mb-3">
							<label for="description" class="form-label">Description :
							</label>
							<textarea class="form-control" name="description" required></textarea>
						</div>
						<div class="mb-3">
							<label for="categorie" class="form-label">Catégorie : </label> <select
								name="categorie" class="form-select"
								aria-label="Default select example" required>
								<%
								for (Categorie c : lesCategories) {
								%>
								<option value="<%=c.getIdCategorie()%>_<%=c.getLibelle()%>">
									<%=c.getLibelle()%>
								</option>
								<%
								}
								%>
							</select>
						</div>
						<!-- 
			  <div class="mb-3">
			    <label for="photo" class="form-label">Photo de l'article : </label>
			    <input type="url" class="form-control" name="photo">
			  </div>				  
			   -->
						<div class="mb-3">
							<label for="prixInitial" class="form-label">Mise à prix :
							</label> <input type="number" min="0" class="form-control"
								name="prixInitial" value="0" required>
						</div>
						<div class="mb-3">
							<label for="dateDebut" class="form-label">Début de
								l'enchère : </label> <input type="date"
								id="dateDebut" onChange="checkDateDebut(event);" class="form-control"
								name="dateDebut" required>
						</div>
						<div class="mb-3">
							<label for="dateFin" class="form-label">Fin de l'enchère
								: </label> <input type="date" id="dateFin" onChange="checkDateFin(event);"
								class="form-control" name="dateFin" required>
						</div>
						<div class="mb-3">
							<label for="rue" class="form-label">Rue : </label> <input
								type="text" class="form-control" name="rue"
								value="<%=u.getRue()%>" required>
						</div>
						<div class="mb-3">
							<label for="codePostal" class="form-label">Code Postal :
							</label> <input type="text" class="form-control" name="codePostal"
								value="<%=u.getCodePostal()%>" required>
						</div>
						<div class="mb-3">
							<label for="ville" class="form-label">Ville : </label> <input
								type="text" class="form-control" name="ville"
								value="<%=u.getVille()%>" required>
						</div>
						<span> <input name="idUtilisateur"
							value="<%=u.getIdUtilisateur()%>" type="hidden"> <input
							name="pseudo" value="<%=u.getPseudo()%>" type="hidden">
							<button type="submit" class="btn btn-primary">Enregistrer</button>
							<button type="reset" class="btn btn-light">Annuler</button>
						</span>
					</form>
				</div>
			</div>
		</main>
	</div>
	<script>
		function checkDateDebut(e) {
			var date = new Date();

			var day = date.getDate();
			var month = date.getMonth() + 1;
			var year = date.getFullYear();

			if (month < 10) month = "0" + month;
			if (day < 10) day = "0" + day;

			var today = year + "-" + month + "-" + day;       
			
			//document.getElementById("theDate").value = today;

			let dateDebut = e.target.value;
		}
		function checkDateFin(e) {
			let dateFin = e.target.value;
			const dateDebut = document.getElementById("dateDebut");
			console.log(dateFin + " et " + dateDebut.value)
		}
	</script>
</body>
</html>