package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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
	
	public AccesProfilServlet() {
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		String filtre = request.getParameter("filtre");
		ArticleVendu nomArticle = new ArticleVendu();
		
		boolean isCheckedVenteEnCours = request.getParameter("enCours") != null;
		boolean isCheckedVenteNonCommencees = request.getParameter("nonCommencees") != null;
		boolean isCheckedVenteTerminee = request.getParameter("terminee") != null;
		
		boolean isCheckedEnchereOuverte = request.getParameter("ouverte")!= null;
		boolean isCheckedMesEcheres = request.getParameter("mesEncheres") != null;
		
		Utilisateur u = null;
		
		try {
			if(isCheckedVenteEnCours = true ) {
				if(nomArticle.getEtat().equals("C")) {
				List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
				request.setAttribute("articleList", listeArticle);
				}
			} else if(isCheckedVenteNonCommencees = true) {
				if(nomArticle.getEtat().equals("N")) {
					List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
					request.setAttribute("articleList", listeArticle);
					}
			} else if(isCheckedVenteTerminee = true ) {
				if(nomArticle.getEtat().equals("T")) {
				List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
				request.setAttribute("articleList", listeArticle);
				}
			} else if(isCheckedEnchereOuverte = true) {
				if(nomArticle.getEtat().equals("C")) {
					List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
					request.setAttribute("articleList", listeArticle);
					}
				else if(isCheckedMesEcheres = true) {
					int id = (int) request.getSession().getAttribute("id");
					u = UtilisateurManager.getInstance().getById(id);
					request.setAttribute("utilisateur", u);
					if(u.equals(nomArticle.getLeVendeur().getIdUtilisateur())) {
						List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
						request.setAttribute("articleList", listeArticle);
						}
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ViewAccesProfil.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			if(!filtre.isEmpty() || filtre != null || !filtre.isBlank()) {
//				if(nomArticle.getNom().equals(filtre)) {
//			List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getByNom(filtre);
//			request.setAttribute("articleList", listeArticle);
//			}
//			
//			}
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ViewAccesProfil.jsp");
//		rd.forward(request, response);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			response.sendError(500);
//		}
	}

}
