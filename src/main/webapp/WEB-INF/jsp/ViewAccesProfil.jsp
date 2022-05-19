<%@page import="bo.Enchere"%>
<%@page import="bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	body
{
  background-color:#f2f2f2;
  font-family: 'RobotoDraft', 'Roboto', sans-serif;
  -webkit-font-smoothing: antialiased;
}

*
{
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

h5
{
  margin:0px;
  font-size:1.4em;
  font-weight:700;
}

p
{
  font-size:12px;
}

.center
{
  height:100vh;
  width:100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* End Non-Essential  */

.property-card
{
  height:18em;
  width:14em;
  display:-webkit-box;
  display:-ms-flexbox;
  display:flex;
  -webkit-box-orient:vertical;
  -webkit-box-direction:normal;
  -ms-flex-direction:column;
  flex-direction:column;
  position:relative;
  -webkit-transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  -o-transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  border-radius:16px;
  overflow:hidden;
  -webkit-box-shadow:  15px 15px 27px #e1e1e3, -15px -15px 27px #ffffff;
  box-shadow:  15px 15px 27px #e1e1e3, -15px -15px 27px #ffffff;
}
/* ^-- The margin bottom is necessary for the drop shadow otherwise it gets clipped in certain cases. */

/* Top Half of card, image. */

.property-image
{
  height:6em;
  width:14em;
  padding:1em 2em;
  position:Absolute;
  top:0px;
  -webkit-transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  -o-transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  background-image:url('https://www.cdiscount.com/pdt2/5/0/8/1/700x700/auc3937114274508/rw/kit-autocollants-clavier-azerty-francais.jpg');
  background-size:cover;
  background-repeat:no-repeat;
}

/* Bottom Card Section */

.property-description
{
  background-color: #FAFAFC;
  height:12em;
  width:14em;
  position:absolute;
  bottom:0em;
  -webkit-transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  -o-transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  padding: 0.5em 1em;
  text-align:center;
}

/* Social Icons */

.property-social-icons
{
  width:1em;
  height:1em;
  background-color:black;
  position:absolute;
  bottom:1em;
  left:1em;
  -webkit-transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  -o-transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
  transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1);
}

/* Property Cards Hover States */

.property-card:hover .property-description
{
  height:0em;
  padding:0px 1em;
}
.property-card:hover .property-image
{
  height:18em;
}

.property-card:hover .property-social-icons
{
  background-color:white;
}

.property-card:hover .property-social-icons:hover
{
  background-color:blue;
  cursor:pointer;
}


/* Optional

.property-image-title
{
text-align:center;
position:Relative;
top:30%;
opacity:0;
transition:all 0.4s cubic-bezier(0.645, 0.045, 0.355, 1) 0.2s;
color:black;
font-size:1.2em;
}

.property-card:hover .property-image-title
{
opacity:1;
}

*/
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
		</div>
	</nav>
	<main>
		<div>
			<label for="filtre">Filtre :</label> 
			<input class="form-control me-2" type="search" placeholder="Search" id="search" aria-label="Search" style="width: auto;"> 
			<a href="#" onclick="recupererResultat()"><i class="fa-solid fa-magnifying-glass"></i></a><br>
			<br> <label for="categorie"> Catégorie : </label> <select name="categorie" id="categorie">
				<option value="toute">Toute</option>
				<option value="informatique">Informatique</option>
				<option value="Ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sport">Sport</option>
				<option value="Loisir">Loisir</option>
			</select><br>
			</div>
			<br>
			<div>
			 <label class="enchere" name ="enchere">
        <input type="radio" value="achat" id="achat" name="enchere" onclick="isCheckedAchat()"> Achats
      </label>
       <label class="checkbox" name ="enchere" style="margin-left: 202px;">
        <input type="radio" value="vente" id="vente" name="enchere" onclick="isCheckedVente()"> Ventes
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
			
		</div>
		<button class="btn btn-outline-success" type="submit">Search</button>
		<div id="tableauListe">
			<div class="center" style="margin-top: -10%">
			<%
				List<ArticleVendu> articleList = (List<ArticleVendu>) request.getAttribute("articleList");
				for(ArticleVendu article : articleList){
			%>
  <div class="property-card">
    <a href="#">
      <div class="property-image">
        <div class="property-image-title">
          <!-- Optional <h5>Card Title</h5> If you want it, turn on the CSS also. -->
        </div>
      </div></a>
    <div class="property-description">
      <h5> <%=article.getNom() %> </h5>
      <p> <%=article.getDescription() %></p>
      <p><%=article.getPrixInitial()+ " points" %></p>
      <p><%= article.getLeVendeur().getPseudo() %></p>
    </div>
    <a href="#">
      <div class="property-social-icons">
        <!-- I would usually put multipe divs inside here set to flex. Some people might use Ul li. Multiple Solutions -->
      </div>
    </a>
  </div>
  <% 
  } 
%>
</div>

		</div>
	</main>
</body>
<script type="text/javascript">

	document.getElementById("tableauListe").style.visibility="hidden";
	var nodes = document.getElementById("visibleAchat").getElementsByTagName('*');
	for(var i = 0; i < nodes.length; i++){
	     nodes[i].disabled = true;
	}
	
	var nodes2 = document.getElementById("visibleVente").getElementsByTagName('*');
	for(var i = 0; i < nodes2.length; i++){
		nodes2[i].disabled = true;
	}		
	
	function isCheckedAchat() {

		var elemAchat = document.getElementById("achat");
		
		
		if (elemAchat.checked = true) {
			
			for(var i = 0; i < nodes.length; i++){
			     nodes[i].disabled = false;
			}
			for(var i = 0; i < nodes2.length; i++){
				nodes2[i].disabled = true;
			}
		}
	}
	
	function isCheckedVente(){
		
		var elemVente = document.getElementById("vente");
		
		if (elemVente.checked = true){
			
			for(var i = 0; i < nodes.length; i++){
			     nodes[i].disabled = true;
			}
			for(var i = 0; i < nodes2.length; i++){
				nodes2[i].disabled = false;
			}	
		}
	}
	function afficherCards(){
		var idTable = document.getElementById("categorie");
		
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