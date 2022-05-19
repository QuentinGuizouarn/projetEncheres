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
<title>Liste Encheres</title>
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
				<a href="<%=request.getContextPath()%>/nouvelleEnchere">Encheres</a> 
				<a href="<%=request.getContextPath()%>/nouvelle_vente">Vendre un article</a>
				<a href="<%=request.getContextPath()%>/view_profil_m">Mon Profil</a>
				<a href="<%=request.getContextPath()%>/liste">Deconnexion</a>
			</div>
	</nav>
	<main>
		<div>
			<label for="filtre">Filtre :</label> 
			<input class="form-control me-2"
				type="search" placeholder="Search" aria-label="Search"
				style="width: auto;"> <a href="#"><i
				class="fa-solid fa-magnifying-glass"></i></a><br>
			<br> <label for="pet-select"> Catégorie : </label> <select
				name="categorie" id="pselect">
				<option value="toute">Toute</option>
				<option value="informatique">Informatique</option>
				<option value="Ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sport">Sport</option>
				<option value="Loisir">Loisir</option>
			</select><br>
			<br>
			<div>
			 <label class="checkbox" name = "achat">
        <input type="radio" value="achat" id="achat" name="enchere" onclick="isChecked()"> Achats
      </label>
       <label class="checkbox" name = "vente" style="margin-left: 202px;">
        <input type="radio" value="vente" id="vente" name="enchere" onclick="isChecked()"> Ventes
      </label><br><br>
			</div>
			<div id="visibleAchat">
			<label class="checkbox" for="ouverte">
        <input type="checkbox" value="ouverte" id="ouverte" name="ouverte"> Enchères ouvertes
      </label><br>
      <label class="checkbox" for="mesEncheres">
        <input type="checkbox" value="mesEncheres" id="mesEncheres" name="mesEncheres"> Mes Enchères
      </label><br>
       <label class="checkbox" for="remportees">
        <input type="checkbox" value="remportees" id="remportees" name="remportees"> Mes enchères remportées
      </label>
			<div id="visibleVente" style="margin-left: 265px;margin-top: -71px;">
			<label class="checkbox" for="enCours">
        <input type="checkbox" value="enCours" id="enCours" name="enCours"> Ventes en cours
      </label><br>
      <label class="checkbox" for="nonCommencees">
        <input type="checkbox" value="nonCommencees" id="nonCommencees" name="nonCommencees"> Ventes non debutées
      </label><br>
       <label class="checkbox" for="remportees">
        <input type="checkbox" value="remportees" id="remportees" name="remportees"> Ventes terminées
      </label>
			</div><br>
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
<script type="text/javascript">

	var nodes = document.getElementById("visibleAchat").getElementsByTagName('*');
	var nodes2 = document.getElementById("visibleVente").getElementsByTagName('*');
	
	for(var i = 0; i < nodes.length; i++){
	     nodes[i].disabled = true;
	}
	
	for(var i = 0; i < nodes2.length; i++){
		nodes2[i].disabled = true;
	}		
	
	function isChecked() {

		var elemAchat = document.getElementById("achat");
		var elemVente = document.getElementById("vente");

		if (elemAchat.checked) {
			
			for(var i = 0; i < nodes.length; i++){
			     nodes[i].disabled = false;
			}
			
			for(var i = 0; i < nodes2.length; i++){
				nodes2[i].disabled = true;
			}
		}
		if (elemVente.checked) {
			
			for(var i = 0; i < nodes.length; i++){
			     nodes[i].disabled = true;
			}
			
			for(var i = 0; i < nodes2.length; i++){
				nodes2[i].disabled = false;
			}
		}
	}
</script>
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