<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form,
	fieldset, input, textarea, p, blockquote, th, td {
	padding: 0;
	margin: 0;
}

fieldset, img {
	border: 0
}

ol, ul, li {
	list-style: none
}

:focus {
	outline: none
}

body, input, textarea, select {
	font-family: 'Open Sans', sans-serif;
	font-size: 16px;
	color: #4c4c4c;
}

p {
	font-size: 12px;
	width: 150px;
	display: inline-block;
	margin-left: 18px;
}

h1 {
	font-size: 32px;
	font-weight: 300;
	color: #4c4c4c;
	text-align: center;
	padding-top: 10px;
	margin-bottom: 10px;
}

html {
	background-color: #ffffff;
}

.testbox {
	margin: 20px auto;
	width: 343px;
	height: 345px;
	-webkit-border-radius: 8px/7px;
	-moz-border-radius: 8px/7px;
	border-radius: 8px/7px;
	background-color: #ebebeb;
	-webkit-box-shadow: 1px 2px 5px rgba(0, 0, 0, .31);
	-moz-box-shadow: 1px 2px 5px rgba(0, 0, 0, .31);
	box-shadow: 1px 2px 5px rgba(0, 0, 0, .31);
	border: solid 1px #cbc9c9;
	    margin-top: 9%;
    margin-left: 39%;
}

input[type=radio] {
	visibility: hidden;
}

form {
	margin: 0 30px;
}

label.radio {
	cursor: pointer;
	text-indent: 35px;
	overflow: visible;
	display: inline-block;
	position: relative;
	margin-bottom: 15px;
}

label.radio:before {
	background: #3a57af;
	content: '';
	position: absolute;
	top: 2px;
	left: 0;
	width: 20px;
	height: 20px;
	border-radius: 100%;
}

label.radio:after {
	opacity: 0;
	content: '';
	position: absolute;
	width: 0.5em;
	height: 0.25em;
	background: transparent;
	top: 7.5px;
	left: 4.5px;
	border: 3px solid #ffffff;
	border-top: none;
	border-right: none;
	-webkit-transform: rotate(-45deg);
	-moz-transform: rotate(-45deg);
	-o-transform: rotate(-45deg);
	-ms-transform: rotate(-45deg);
	transform: rotate(-45deg);
}

input[type=radio]:checked+label:after {
	opacity: 1;
}

hr {
	color: #a9a9a9;
	opacity: 0.3;
}

input[type=text], input[type=password] {
	width: 200px;
	height: 39px;
	-webkit-border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
	-moz-border-radius: 0px 4px 4px 0px/0px 0px 4px 4px;
	border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
	background-color: #fff;
	-webkit-box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	-moz-box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	border: solid 1px #cbc9c9;
	margin-left: -5px;
	margin-top: 13px;
	padding-left: 10px;
}

input[type=password] {
	margin-bottom: 25px;
}

#icon {
	display: inline-block;
	width: 15%;
	background-color: #3a57af;
	padding: 8px 0px 8px 15px;
	margin-left: 15px;
	-webkit-border-radius: 4px 0px 0px 4px;
	-moz-border-radius: 4px 0px 0px 4px;
	border-radius: 4px 0px 0px 4px;
	color: white;
	-webkit-box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	-moz-box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	border: solid 0px #cbc9c9;
}

.gender {
	margin-left: 30px;
	margin-bottom: 30px;
}

.accounttype {
	margin-left: 8px;
	margin-top: 20px;
}

button {
	font-size: 14px;
	font-weight: 600;
	color: white;
	padding: 6px 25px 0px 20px;
	margin: 10px 8px 20px 0px;
	display: inline-block;
	float: right;
	text-decoration: none;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	background-color: #3a57af;
	-webkit-box-shadow: 0 1px rgba(58, 87, 175, .75);
	-moz-box-shadow: 0 3px rgba(58, 87, 175, .75);
	box-shadow: 0 3px rgba(58, 87, 175, .75);
	transition: all 0.1s linear 0s;
	top: 0px;
	position: relative;
    border-color: snow;
    margin-right: 25%;
    margin-top:6%;
    width: 45%;
    height: 37px;
}

a.button:hover {
	top: 3px;
	background-color: #2e458b;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
}
.checkbox {
	  font-weight: normal;
	  margin-left: 5%;
	}
</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600'
	rel='stylesheet' type='text/css'>
<link
	href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css"
	rel="stylesheet">
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
		</div>
	</nav>
	<div class="testbox">
		<h1>Connexion</h1>
		<form method="POST" action="<%=request.getContextPath()%>/connexion">
			<hr>
			<label id="icon" for="email"><i class="icon-envelope "></i></label>
			<input type="text" name="email" id="email" placeholder="Email" required />
			<label id="icon" for="motdepasse"><i class="icon-shield"></i></label> 
			<input type="password" name="motdepasse" id="motdepasse" placeholder="Mot De Passe" required/> 
			<label class="checkbox">
        <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> Se souvenir de moi
      </label>
      <label class="checkbox">
        <input type="checkbox" value="forgot" id="forgot" name="forgot"> Mot de Passe oublié
      </label>
				 <button type="submit"  class="button">Connexion</button>
		</form>
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