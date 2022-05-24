package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleVenduManager;
import bll.CategorieManager;
import bll.UtilisateurManager;
import bo.ArticleVendu;
import bo.Categorie;
import bo.Utilisateur;

/**
 * Servlet implementation class AjoutNouvelleVenteServlet
 */
@WebServlet("/nouvelle_vente")
public class AjoutNouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String idArticle = null;
	private Utilisateur u;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		idArticle = request.getParameter("article");
		u = (Utilisateur) request.getSession().getAttribute("user");
		List<Categorie> items = null;
		ArticleVendu av = null;
		try {
			items = CategorieManager.getInstance().getAll();
			av = idArticle != null ? ArticleVenduManager.getInstance().getById(Integer.valueOf(idArticle)) : null;
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		String titre = av != null && !av.getEtat().equalsIgnoreCase("n") ? "Vente non modifiable" : "Nouvelle vente";
		request.setAttribute("utilisateur", u);
		request.setAttribute("items", items);
		request.setAttribute("articleVendu", av);
		request.setAttribute("titre", titre);
		request.getRequestDispatcher("/WEB-INF/jsp/ajout_nouvelle_vente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int id = idArticle != null ? Integer.valueOf(idArticle) : 0;
		Categorie c = null;
		ArticleVendu av = null;
		try {			
			if (request.getParameter("insert_update") != null) {		
				String nom = request.getParameter("nom");
				String description = request.getParameter("description");
				int prixInital = Integer.valueOf(request.getParameter("prixInitial"));
				LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
				LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");		
				String etat = "N";
				String chaineCategorie = request.getParameter("categorie");
				
				c = new Categorie( Integer.valueOf(chaineCategorie.split("_")[0]), 
					String.valueOf(chaineCategorie.split("_")[1]) );
				
				if (id != 0) {
					av = new ArticleVendu(id, prixInital, 0, nom, description, 
						etat, rue, codePostal, ville, dateDebut, dateFin, u, c);
					ArticleVenduManager.getInstance().changeArticleVendu(av);
				} else {
					av = new ArticleVendu(prixInital, 0, nom, description, etat, rue, 
						codePostal, ville, dateDebut, dateFin, u, c);
					ArticleVenduManager.getInstance().addArticleVendu(av);
				}
			} else if (request.getParameter("delete") != null && id != 0) {
				ArticleVenduManager.getInstance().removeArticleVendu(id);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
			response.sendError(500);
		}
		idArticle = null;
		response.sendRedirect(request.getContextPath() + "/liste");
		
	}

}
