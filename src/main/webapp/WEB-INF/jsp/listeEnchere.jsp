<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<title>ENI-Enchères</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="<%=request.getContextPath()%>/liste"><img
				alt=""
				src="https://www.sdis68.fr/dynamic/images/le_sdis68/cac_/logo_encheres!_283x200!_3!_0x0!_0!_FFFFFF.png"></a>
			<div class="collapse navbar-collapse " id="navbarSupportedContent">
				<h1 style="margin-left: 465px;">ENI ENCHERES</h1>
			</div>
			<div>
				<a href="<%=request.getContextPath()%>/connexion">Se Connecter</a> <a
					href="<%=request.getContextPath()%>/create_profil">S'inscrire</a>
			</div>
	</nav>
	<main>
		<div>
			<label for="filtre">Filtre :</label> <input class="form-control me-2"
				type="search" placeholder="Search" aria-label="Search"
				style="width: auto;"> <a href="#"><i
				class="fa-solid fa-magnifying-glass"></i></a><br>
			<br> <label for="pet-select">Catégorie:</label> <select
				name="categorie" id="pselect">
				<option value="toute">Toute</option>
				<option value="informatique">Informatique</option>
				<option value="Ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sport">Sport</option>
				<option value="Loisir">Loisir</option>
			</select><br>
			<br>
			<button class="btn btn-outline-success" type="submit">Search</button>
		</div>
		<div>
			<table class="table" class="table" id="tableauListe">
  <thead>
    <tr>
      <th scope="col">numero de l'article</th>
      <th scope="col">Nom</th>
      <th scope="col">description</th>
      <th scope="col">Etat</th>
      <th scope="col">prix Initial</th>
      <th scope="col">prix Vente</th>
      <th scope="col">date de Debut</th>
      <th scope="col">date de Fin</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
      <td>@mdo</td>
      <td>@mdo</td>
      <td>@mdo</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
      <td>@fat</td>
      <td>@fat</td>
      <td>@fat</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Sotto</td>
      <td>May</td>
      <td>@twitter</td>
      <td>@twitter</td>
      <td>@twitter</td>
      <td>@twitter</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
		</div>
	</main>
</body>
<script src="https://kit.fontawesome.com/6898bc3621.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
	integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js"
	integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
	crossorigin="anonymous"></script>
</html>