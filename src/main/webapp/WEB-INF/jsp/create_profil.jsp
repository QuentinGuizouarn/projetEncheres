<%@page import="servlet.Servlet_create_profil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>ENI-Enchères</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg bg-light">
	<div class="container-fluid">
	  <a class="navbar-brand" href="<%=request.getContextPath()%>/view_profil-m"><img
	  alt=""
	  src="https://www.sdis68.fr/dynamic/images/le_sdis68/cac_/logo_encheres!_283x200!_3!_0x0!_0!_FFFFFF.png"></a>
	  <div class="collapse navbar-collapse " id="navbarSupportedContent">
	  	<h1 style="margin-left: 465px;">ENI ENCHERES</h1>
	  </div>
	</div>
  </nav>
  <div class="container">
  	<div class="row">
  	  <div class="row justify-content-md-center">
    	<div class="col-md-auto"><h2>Mon Profil</h2></div>
      </div>
 	  <div class="row"><div class="col"><label></label></div></div>
	  <form method="post" action="/projetEncheres/liste_enchere">
  		<div class="row">
    	  <div class="col"></div>
		  <div class="col"> <label for="texte">Pseudo : </label> </div>
		  <div class="col"> <input type="text" name="pseudo" id="pseudo"/> </div>
		  <div class="col"></div>
		  <div class="col"> <label for="texte">Nom : </label> </div>
		  <div class="col"> <input type="text" name="last_name" id="last_name"/> </div>
		  <div class="col"></div>
		</div>
 		<div class="row"><div class="col"><label></label></div></div>
		<div class="row">
   		  <div class="col"></div>
		  <div class="col"> <label for="texte">Prénom : </label> </div>
		  <div class="col"> <input type="text" name="first_name" id="first_name"/> </div>
		  <div class="col"></div>
		  <div class="col"> <label for="texte">Email : </label> </div>
		  <div class="col"> <input type="text" name="email" id="email"/> </div>
		  <div class="col"></div>
		</div>
 		<div class="row"><div class="col"><label></label></div></div>
		<div class="row">
   		  <div class="col"></div>
		  <div class="col"> <label for="nombre">Téléphone : </label> </div>
		  <div class="col"> <input type="text" name="phone" id="phone"/> </div>
		  <div class="col"></div>
		  <div class="col"> <label for="texte">Rue : </label> </div>
		  <div class="col"> <input type="text" name="road" id="road"/> </div>
     	  <div class="col"></div>
		</div>
 		<div class="row"><div class="col"><label></label></div></div>
		<div class="row">
   		  <div class="col"></div>
		  <div class="col"> <label for="nombre">Code Postal : </label> </div>
		  <div class="col"> <input type="text" name="cp" id="cp"/> </div>
		  <div class="col"></div>
		  <div class="col"> <label for="texte">Ville : </label> </div>
		  <div class="col"> <input type="text" name="city" id="city"/> </div>
     	  <div class="col"></div>
		</div>
 		<div class="row"><div class="col"><label></label></div></div>
 		<div class="row">
   		  <div class="col"></div>
		  <div class="col"> <label for="texte">Mot de passe actuel : </label> </div>
		  <div class="col"> <input type="text" name="mdp" id="mdp"/> </div>
		  <div class="col"></div>
		  <div class="col"> <label for="texte">Confirmation : </label> </div>
		  <div class="col"> <input type="text" name="compare" id="compare"/> </div>
     	  <div class="col"></div>
	  	</div>
 		<div class="row"><div class="col"><label></label></div></div>
 		<div class="row justify-content-md-center">
 		  <div class="col col-lg-2"> <input type="submit" class="btn btn-outline-primary" value="Créer"/> </div>
		  <div class="col col-lg-2"> <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/view_profil-m" title="Créer une nouvelle liste">annuler</a> </div>
		</div>
	  </form>
    </div>
  </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</html>