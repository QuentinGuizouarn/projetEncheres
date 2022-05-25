package servlet;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleVenduManager;
import bll.CategorieManager;
import bll.EnchereManager;
import bll.UtilisateurManager;
import bo.ArticleVendu;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;

/**
 * Servlet implementation class AccesProfilServlet
 */
@WebServlet("/AccesProfilServlet")
public class AccesProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ArticleVendu> lesArticles = null;
		try {
			lesArticles = ArticleVenduManager.getInstance().getAll();
			for (ArticleVendu av : lesArticles) {
				if ((av.getEtat().equalsIgnoreCase("n")) &&
						(LocalDate.now().isAfter(av.getDateDebut()))) {
					av.setEtat("C");
				} else if ((av.getEtat().equalsIgnoreCase("c")) &&
						(LocalDate.now().isAfter(av.getDateFin()))) {
					av.setEtat("T");
				}
				ArticleVenduManager.getInstance().changeArticleVendu(av);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Utilisateur u = null;
		try {
			List<Categorie> categorieListe = CategorieManager.getInstance().getAll();
			request.setAttribute("categorieListe", categorieListe);
			List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
			request.setAttribute("articleList", listeArticle);

			String pseudo = (String) request.getSession().getAttribute("pseudo");
			String motDePasse = (String) request.getSession().getAttribute("motDePasse");
			u = UtilisateurManager.getInstance().getByConnection(pseudo, motDePasse);
			request.setAttribute("utilisateur", u);

			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ViewAccesProfil.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isCheckedVenteEnCours = request.getParameter("enCours") != null;		
		boolean isCheckedVenteNonCommencees = request.getParameter("nonCommencees") != null;
		boolean isCheckedVenteTerminee = request.getParameter("terminee") != null;
		boolean isCheckedEnchereOuverte = request.getParameter("ouverte")!= null;
		boolean isCheckedMesEcheres = request.getParameter("mesEncheres") != null;
		boolean isCheckMesEncheresRemportee = request.getParameter("remportees") != null;
		String filtre = request.getParameter("filtre");
		ArticleVendu nomArticle = new ArticleVendu();
		Utilisateur u = new Utilisateur();
		Enchere enchereUtilisateur = new Enchere();
	

		try {
			List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
			List<ArticleVendu> result = new ArrayList<ArticleVendu>();
			for (ArticleVendu article : listeArticle) {

				if(isCheckedVenteEnCours = true ) {
					if(article.getEtat().equalsIgnoreCase("C")) {
						result.add(article);
					}
				} else if(isCheckedVenteNonCommencees = true) {
					if(article.getEtat().equalsIgnoreCase("N")) {
						result.add(article);
					}
				} else if(isCheckedVenteTerminee = true ) {
					if(article.getEtat().equalsIgnoreCase("T")) {
						result.add(article);
					}
				} else if(isCheckedEnchereOuverte = true) {
					if(article.getEtat().equalsIgnoreCase("C")) {
						result.add(article);
					}
				} else if(isCheckedMesEcheres = true) {
					int id = (int) request.getSession().getAttribute("id");
					u = UtilisateurManager.getInstance().getById(id);
					//request.setAttribute("utilisateur", u);
					if(u.equals(enchereUtilisateur.getLeAcheteur().getIdUtilisateur())) {
						request.setAttribute("articleList", listeArticle);
					}
				} else if(isCheckMesEncheresRemportee = true) {
					int id = (int) request.getSession().getAttribute("id");
					u = UtilisateurManager.getInstance().getById(id);
					if(u.equals(enchereUtilisateur.getLeAcheteur().getIdUtilisateur()) 
							&& article.getEtat().equalsIgnoreCase("T") 
							&& enchereUtilisateur.getMontant() == article.getPrixVente()) {
						request.setAttribute("articleList", listeArticle);
					}
				}
			}
			request.setAttribute("result", result);
			doGet(request, response);
				
			

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//		try {
			//			if(!filtre.isEmpty() || filtre != null || !filtre.isBlank()) {
			//				if(nomArticle.getNom().startsWith(filtre) || nomArticle.getNom().equals(filtre) || nomArticle.getNom().contains(filtre)) {
			//					List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getByNom(filtre);
			//					request.setAttribute("articleList", listeArticle);
			//				}
			//			}
			//		request.setAttribute("listArticleFiltre", articlesFiltres);
			//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ViewAccesProfil.jsp");
			//		rd.forward(request, response);
			//		} catch (SQLException e) {
			//			e.printStackTrace();
			//			response.sendError(500);
			//		}
		}

		//	private List<ArticleVendu> filtre(HashMap<String, Boolean> listFiltreAchat) {
		//		List<ArticleVendu> articlesReference = new ArrayList();
		//		List<ArticleVendu> articlesFiltre = new ArrayList();
		//		List<ArticleVendu> articlesTransitoires = new ArrayList();;
		//		try {
		//			articlesReference = ArticleVenduManager.getInstance().getAll();
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//		}
		//		
		//		/**
		//		 * 
		//		 */
		//		if(listFiltreAchat.get("isCheckedEnchereOuverte")) {
		//			articlesReference.forEach(i -> {
		//				if((i.getDateDebut().isBefore(LocalDate.now())) && i.getDateFin().isAfter(LocalDate.now())) {
		//					articlesTransitoires.add(i);
		//				}
		//			});
		//		}
		//		/**
		//		 * Objectif Récuperer toutes les encheres d'un individu. Récupère toutes les enchères participées depuis la création du compte
		//		 *  -> mettre dans une liste touts les articles liés à ces enchères || Pas de doublons d'articles
		//		 */
		//		if(listFiltreAchat.get("isCheckedMesEcheres")) {
		//			
		//			articlesReference.forEach(i -> {
		//				
		//				Enchere enchereUtilisateur = new Enchere();
		//				Utilisateur u = new Utilisateur();
		//				HttpServletRequest request = null;
		//				
		//				int id = (int) request.getSession().getAttribute("id");
		//				
		//				if(u.equals(enchereUtilisateur.getLeAcheteur().getIdUtilisateur())) {
		//					articlesTransitoires.add(i);
		//				}
		//			});
		//		}
		//		/**
		//		 * Objectif Récuperer toutes les encheres gagnées d'un individu.
		//		 *  -> mettre dans une liste : 
		//		 *  		Toutes les enchères de l'individu ou le montant = article.prixVente
		//		 */
		//		if(listFiltreAchat.get("isCheckMesEncheresRemportee")) {
		//			ArticleVendu nomArticle = new ArticleVendu();
		//			Enchere enchereUtilisateur = new Enchere();
		//			Utilisateur u = new Utilisateur();
		//			HttpServletRequest request = null;
		//			int id = (int) request.getSession().getAttribute("id");
		//			articlesReference.forEach(i -> {
		//				if(u.equals(enchereUtilisateur.getLeAcheteur().getIdUtilisateur()) 
		//						&& nomArticle.getEtat().equalsIgnoreCase("T") 
		//						&& enchereUtilisateur.getMontant() == nomArticle.getPrixVente()) {
		//					articlesTransitoires.add(i);
		//				}
		//			});
		//		}
		//		System.out.println(articlesTransitoires);
		//		return articlesReference;
		//	}
		/*	
	private void sauvegarde(){
		try {
			if(isCheckedVenteEnCours = true ) {
				if(nomArticle.getEtat().equalsIgnoreCase("C")) {
				List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
				request.setAttribute("articleList", listeArticle);
				}
			} else if(isCheckedVenteNonCommencees = true) {
				if(nomArticle.getEtat().equalsIgnoreCase("N")) {
					List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
					request.setAttribute("articleList", listeArticle);
					}
			} else if(isCheckedVenteTerminee = true ) {
				if(nomArticle.getEtat().equalsIgnoreCase("T")) {
				List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
				request.setAttribute("articleList", listeArticle);
				}
			} else if(isCheckedEnchereOuverte = true) {
				if(nomArticle.getEtat().equalsIgnoreCase("C")) {
					List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
					request.setAttribute("articleList", listeArticle);
				}
			} else if(isCheckedMesEcheres = true) {
					int id = (int) request.getSession().getAttribute("id");
					u = UtilisateurManager.getInstance().getById(id);
					//request.setAttribute("utilisateur", u);
					if(u.equals(enchereUtilisateur.getLeAcheteur().getIdUtilisateur())) {
						List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
						request.setAttribute("articleList", listeArticle);
					}
				} else if(isCheckMesEncheresRemportee = true) {
					int id = (int) request.getSession().getAttribute("id");
					u = UtilisateurManager.getInstance().getById(id);
					if(u.equals(enchereUtilisateur.getLeAcheteur().getIdUtilisateur()) 
							&& nomArticle.getEtat().equalsIgnoreCase("T") 
							&& enchereUtilisateur.getMontant() == nomArticle.getPrixVente()) {
						List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
						request.setAttribute("articleList", listeArticle);
					}
				}

			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ViewAccesProfil.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		 */
	//		HashMap<String, Boolean> listFiltreAchat = new HashMap<String, Boolean>();
	//		List<ArticleVendu> articlesFiltres = new ArrayList();
	//		//Vente
	//		/**
	//		 * 	Select all where idVendeur = x
	//		 * Vente en cours :
	//		 * 	if((i.getDateDebut().isBefore(LocalDate.now())) && i.getDateFin().isAfter(LocalDate.now())) {
	//					articlesTransitoires.add(i);
	//				}
	//		 * non Commencées :
	//		 * if(i.getDateDebut().isAfter(LocalDate.now())){
	//					articlesTransitoires.add(i);
	//				}
	//		 * terminee :
	//		 * if(i.getDateFin().isAfter(LocalDate.now())){
	//					articlesTransitoires.add(i);
	//				} 
	//		 */

	//		
	////		System.out.println(request.getParameter("enCours"));
	////		System.out.println(isCheckedVenteNonCommencees);
	////		System.out.println(isCheckedVenteTerminee);
	//		
	//		
	//		//Achat

	//		listFiltreAchat.put("isCheckedEnchereOuverte", isCheckedEnchereOuverte);
	//		listFiltreAchat.put("isCheckedMesEcheres", isCheckedMesEcheres);
	//		listFiltreAchat.put("isCheckMesEncheresRemportee", isCheckMesEncheresRemportee);
	//	//	Utilisateur u = null;
	//		
	//		System.out.println(isCheckedEnchereOuverte);
	//		System.out.println(isCheckedMesEcheres);
	//		System.out.println(isCheckMesEncheresRemportee);
	//		articlesFiltres = filtre(listFiltreAchat);
	}
