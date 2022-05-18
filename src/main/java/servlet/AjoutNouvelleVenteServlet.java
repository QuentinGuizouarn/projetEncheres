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
import bo.ArticleVendu;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;

/**
 * Servlet implementation class AjoutNouvelleVenteServlet
 */
@WebServlet("/nouvelle_vente")
public class AjoutNouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Categorie> items = CategorieManager.getInstance().getAll();
			request.setAttribute("items", items);
			request.getRequestDispatcher("/WEB-INF/pages/ajout_nouvelle_vente.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		try {
			Utilisateur u = new Utilisateur();
			String nom = request.getParameter("nom");
			String description = request.getParameter("description");
			int prixInital = Integer.valueOf(request.getParameter("prixInital"));
			LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
			LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
			String rue = request.getParameter("nom");
			String codePostal = request.getParameter("nom");
			String ville = request.getParameter("nom");		
			String chaineCategorie = request.getParameter("categorie");
			Categorie c = new Categorie( Integer.valueOf(chaineCategorie.split("_")[0]), 
				String.valueOf(chaineCategorie.split("_")[1]) );			
			ArticleVendu av = new ArticleVendu(prixInital, 0, nom, description, "C", rue, 
				codePostal, ville, dateDebut, dateFin, u, c);
			
			ArticleVenduManager.getInstance().addArticleVendu(av);
		} catch (SQLException e) {			
			e.printStackTrace();
			response.sendError(500);
		}
		
	}

}
