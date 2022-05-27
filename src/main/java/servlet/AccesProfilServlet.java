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

			Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
			u = UtilisateurManager.getInstance().getById(user.getIdUtilisateur());
			request.setAttribute("utilisateur", u);

			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/view_acces_profil.jsp");
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
		Integer categorie = Integer.valueOf(request.getParameter("categorie"));
		Enchere enchereUtilisateur = new Enchere();
		
		try {
			List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
			List<ArticleVendu> result = new ArrayList<ArticleVendu>();
			
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
		
			for (ArticleVendu article : listeArticle) {
				boolean check = true;

				if (!filtre.isEmpty() && !article.getNom().contains(filtre)) {
					check = false;
				}
				if (categorie != 0 && article.getLaCategorie().getIdCategorie() != categorie) {
					check = false;
				}
				if(isCheckedVenteEnCours = true && !article.getEtat().equalsIgnoreCase("C")) {
					check = false;
				}
				if(isCheckedVenteNonCommencees = true && !article.getEtat().equalsIgnoreCase("N")) {
					check = false;
				}
				if(isCheckedVenteTerminee = true && !article.getEtat().equalsIgnoreCase("T")) {
					check = false;
				}
				if(isCheckedEnchereOuverte = true && !article.getEtat().equalsIgnoreCase("C")) {
					check = false;
				}
				if(isCheckedMesEcheres = true) {
					boolean checkenchere = true;
					for (Enchere enchere : article.getLesEncheres()) {
						if (checkenchere && user.getIdUtilisateur() != enchere.getLeAcheteur().getIdUtilisateur()){
							checkenchere = false;
						}
					}
					if (checkenchere) {
					check = false;
					}
				}
				if(isCheckMesEncheresRemportee = true) {
					for (Enchere enchere : article.getLesEncheres()) {
						if(user.getIdUtilisateur() != enchere.getLeAcheteur().getIdUtilisateur()
								&& !article.getEtat().equalsIgnoreCase("T") 
								&& enchere.getMontant() != article.getPrixVente()) {
							check = false;
						}
					}
				}
				if (check){
					result.add(article);
				}
			}
			
			request.setAttribute("result", result);
			doGet(request, response);
				
			

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
