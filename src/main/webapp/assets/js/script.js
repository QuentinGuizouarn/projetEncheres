/**
 * 
 */
 
 	//document.getElementById("tableauListe").style.visibility = "hidden";
	var nodes = document.getElementById("visibleAchat").getElementsByTagName(
			'*');
	for (var i = 0; i < nodes.length; i++) {
		nodes[i].disabled = true;
	}

	var nodes2 = document.getElementById("visibleVente").getElementsByTagName(
			'*');
	for (var i = 0; i < nodes2.length; i++) {
		nodes2[i].disabled = true;
	}

	function isCheckedAchat() {

		var elemAchat = document.getElementById("achat");

		if (elemAchat.checked = true) {

			for (var i = 0; i < nodes.length; i++) {
				nodes[i].disabled = false;
			}
			for (var i = 0; i < nodes2.length; i++) {
				nodes2[i].disabled = true;
			}
		}
	}

	function isCheckedVente() {

		var elemVente = document.getElementById("vente");

		if (elemVente.checked = true) {

			for (var i = 0; i < nodes.length; i++) {
				nodes[i].disabled = true;
			}
			for (var i = 0; i < nodes2.length; i++) {
				nodes2[i].disabled = false;
			}
		}
	}
	function afficherCardsAll() {
		var idTable = document.getElementById("categorie");
		if (idTable.value == "toute") {
			document.getElementById("tableauListe").style.display = "none";
		} else {
			document.getElementById("tableauListe").style.display = "none";
		}
	}
	
	
	console.log("Test !")

	function afficherCardsByLibelle() {

		var idTable = document.getElementById("categorie");

		var categorieArticle = document.getElementById("tableauListe")
		var tableCategorie = categorieArticle.getElementsByTagName("DIV");
		
		console.log("Categorie : " + idTable.value);
		
		document.getElementById("categorieInformatique").style.display = 'none';
		document.getElementById("categorieLoisir").style.display = 'none'
		document.getElementById("categorieVetement").style.display = 'none';
		document.getElementById("categorieSport").style.display = 'none';
		document.getElementById("categorieAmeublement").style.display = 'none';		
		
		switch(idTable.value) {			
			
			case 'informatique':
				document.getElementById("categorieInformatique").style.display = 'block';
				console.log (document.getElementById("categorieInformatique").style);
				break;
				
			case 'loisir':
					document.getElementById("categorieInformatique").style.display = 'none';
				document.getElementById("categorieLoisir").style.display ='block';
						document.getElementById("categorieVetement").style.display = 'none';
				console.log (document.getElementById("categorieLoisir").style);
				break;
				
			default:
				console.log("DEFAULT");
				break;
		}
		}
/* 

		if (idTable.value === "informatique") {
			console.log("info")
			document.getElementById("categorieLoisir").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "hidden";
			document.getElementById("categorieSport").style.visibility = "hidden";
			document.getElementById("categorieAmeublement").style.visibility = "hidden";
			document.getElementById("categorieInformatique").style.visibility = "visible";



		}
		if (idTable.value === "loisir") {
			console.log("loisir")
			document.getElementById("categorieInformatique").style.display = 'none';

			document.getElementById("categorieVetement").style.visibility = "hidden";
			document.getElementById("categorieSport").style.visibility = "hidden";
			document.getElementById("categorieAmeublement").style.visibility = "hidden";

				document.getElementById("categorieLoisir").style.visibility = "visible";
			
		}

		if (idTable.value === "vetement") {
			console.log("vetement")
			document.getElementById("categorieInformatique").style.visibility = "hidden";
			document.getElementById("categorieLoisir").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "visible";
			document.getElementById("categorieSport").style.visibility = "hidden";
			document.getElementById("categorieAmeublement").style.visibility = "hidden";
			for (var i = 0; i < tableCategorie.length; i++) {
				//document.write("coucou");
				document.getElementById("categorieVetement").style.visibility = "visible";

			}
		}

		if (idTable.value === "sport") {
			console.log("sport")
			document.getElementById("categorieInformatique").style.visibility = "hidden";
			document.getElementById("categorieLoisir").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "visible";
			document.getElementById("categorieSport").style.visibility = "hidden";
			document.getElementById("categorieAmeublement").style.visibility = "hidden";

			for (var i = 0; i < tableCategorie.length; i++) {
				//document.write("coucou");
				document.getElementById("categorieSport").style.visibility = "visible";
			}
		}

		if (idTable.value === "ameublement") {
			console.log("ameublement")
			document.getElementById("categorieInformatique").style.visibility = "hidden";
			document.getElementById("categorieLoisir").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "hidden";
			document.getElementById("categorieVetement").style.visibility = "visible";
			document.getElementById("categorieSport").style.visibility = "hidden";
			document.getElementById("categorieAmeublement").style.visibility = "hidden";
			for (var i = 0; i < tableCategorie.length; i++) {
				//document.write("coucou");
				document.getElementById("categorieAmeublement").style.visibility = "visible";
			}
		} 
		*/