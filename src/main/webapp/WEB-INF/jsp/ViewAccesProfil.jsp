<%@page import="bo.Utilisateur"%>
<%@page import="bo.Categorie"%>
<%@page import="bo.Enchere"%>
<%@page import="bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Utilisateur u = (Utilisateur)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet type="text/css" href="../../assets/css/style.css"/>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
				<a href="<%=request.getContextPath()%>/nouvelle_vente">Vendre un
					article</a> <a href="<%=request.getContextPath()%>/view_profil?user=<%=u != null ? u.getPseudo() : null%>">Mon
					Profil</a> <a href="<%=request.getContextPath()%>/liste">Deconnexion</a>
			</div>
		</div>
	</nav>
	<main>
		<div>
			<form action="<%=request.getContextPath()%>/AccesProfilServlet" method="POST">
				<label for="filtre">Filtre :</label> <input
					class="form-control me-2" type="search" placeholder="Search"
					id="search" name="filtre" aria-label="Search" style="width: auto;">
				<br> <br> 
				<label
					for="categorie"> Catégorie : </label> <select name="categorie"
					id="categorie" onChange="afficherCardsByLibelle()">
					<!--  					onChange="afficherCardsAll();afficherCardsByLibelle();"> -->
					<option value="toute">Toutes</option>
					<%
					List<Categorie> listeCategorie = (List<Categorie>) request.getAttribute("categorieListe");
					for (Categorie categorie : listeCategorie) {
					%>

					<option value="<%=categorie.getLibelle()%>"><%=categorie.getLibelle()%></option>
					<%
					}
					%>
				</select>
		</div>
		<br>
		<div>
			<label class="enchere" name="enchere"> <input type="radio"
				value="achat" id="achat" name="enchere" onclick="isCheckedAchat()">
				Achats
			</label> <label class="checkbox" name="enchere" style="margin-left: 202px;">
				<input type="radio" value="vente" id="vente" name="enchere"
				onclick="isCheckedVente()"> Ventes
			</label><br> <br>
		</div>
		<div id="visibleAchat">
			
			<label class="checkbox" for="ouverte"> <input type="checkbox"
				value="ouverte" id="ouverte" name="ouverte"> Enchères
				ouvertes
			</label><br> <label class="checkbox" for="mesEncheres"> <input
				type="checkbox" value="mesEncheres" id="mesEncheres"
				name="mesEncheres"> Mes Enchères
			</label><br> <label class="checkbox" for="remportees"> <input
				type="checkbox" value="remportees" id="remportees" name="remportees">
				Mes enchères remportées
			</label>
			<div id="visibleVente" style="margin-left: 265px; margin-top: -71px;">
				<label class="checkbox" for="enCours"> <input
					type="checkbox" value="enCours" id="enCours" name="enCours">
					Mes Ventes en cours
				</label><br> <label class="checkbox" for="nonCommencees"> <input
					type="checkbox" value="nonCommencees" id="nonCommencees"
					name="nonCommencees"> Ventes non debutées
				</label><br> <label class="checkbox" for="terminee"> <input
					type="checkbox" value="terminee" id="terminee"
					name="terminee"> Ventes terminées
				</label>
			</div>
			<br>
		</div>
		<button class="btn btn-outline-success" type="submit">Search</button>
		</form>
		<div id="tableauListe" style="background-color: lightred;">
			<div class="center" style="margin-top: -10%">
				<%
				List<ArticleVendu> articleList = (List<ArticleVendu>) request.getAttribute("articleList");
				for (ArticleVendu article : articleList) {
					if(article.getEtat().equals("C") || article.getEtat().equals("N") || article.getEtat().equals("T")){
				%>
				<div class="property-card">
					<a href="#">
						<div class="property-image">
							<div class="property-image-title">
								<!-- Optional <h5>Card Title</h5> If you want it, turn on the CSS also. -->
							</div>
						</div>
					</a>
					<div class="property-description" id="categorieInformatique">
						<h5>
							<%=article.getNom()%>
						</h5>
						<p>
							<%=article.getDescription()%></p>
						<p><%=article.getPrixInitial() + " points"%></p>
						<p><%=article.getLeVendeur().getPseudo()%></p>
					</div>
					<div class="property-description" id="categorieLoisir">
						<h5>
							<%=article.getNom()%>
						</h5>
						<p>
							<%=article.getDescription()%></p>
						<p><%=article.getPrixInitial() + " points"%></p>
						<p><%=article.getLeVendeur().getPseudo()%></p>
					</div>
					<div class="property-description" id="categorieVetement">
						<h5>
							<%=article.getNom()%>
						</h5>
						<p>
							<%=article.getDescription()%></p>
						<p><%=article.getPrixInitial() + " points"%></p>
						<p><%=article.getLeVendeur().getPseudo()%></p>
					</div>
					<div class="property-description" id="categorieSport">
						<h5>
							<%=article.getNom()%>
						</h5>
						<p>
							<%=article.getDescription()%></p>
						<p><%=article.getPrixInitial() + " points"%></p>
						<p><%=article.getLeVendeur().getPseudo()%></p>
					</div>
					<div class="property-description" id="categorieAmeublement">
						<h5>
							<%=article.getNom()%>
						</h5>
						<p>
							<%=article.getDescription()%></p>
						<p><%=article.getPrixInitial() + " points"%></p>
						<p><%=article.getLeVendeur().getPseudo()%></p>
					</div>
					<a href="#">
						<div class="property-social-icons">
							<!-- I would usually put multipe divs inside here set to flex. Some people might use Ul li. Multiple Solutions -->
						</div>
					</a>
				</div>
				<%
					}
				}
				%>
			</div>
		</div>
	</main>


	<%@ include file="../../footer.jsp"%>
