package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleVenduManager;
import bll.EnchereManager;
import bll.UtilisateurManager;
import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import helpers.Util;

/**
 * Servlet implementation class DetailVenteServlet
 */
@WebServlet("/detail_vente")
public class DetailVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = request.getParameter("article") == null ? 0 : Integer.valueOf(request.getParameter("article"));
		Utilisateur u = null;
		ArticleVendu av = null;
		Enchere e = null;
		Boolean proprietaire = false;
		Boolean vainqueur = false;		
		String titre = "Détail vente";
		if (id != 0) {
			try {
				u = UtilisateurManager.getInstance().getById(1);
				av = ArticleVenduManager.getInstance().getById(id);
				e = EnchereManager.getInstance().getMaxByArticle(id);
				proprietaire = u.getIdUtilisateur() == av.getLeVendeur().getIdUtilisateur();
				if (e != null) {
					vainqueur = u.getIdUtilisateur() == e.getLeAcheteur().getIdUtilisateur();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				response.sendError(500);
			}
		}
		if (av.getEtat().equalsIgnoreCase("t") || av.getEtat().equalsIgnoreCase("r")) {
			titre = e.getLeAcheteur().getPseudo() + " a remporté l'enchère";
		}
		if (vainqueur && (av.getEtat().equalsIgnoreCase("t") || av.getEtat().equalsIgnoreCase("r"))) {
			titre = "Vous avez remporté la vente";
		}
		request.setAttribute("utilisateur", u);
		request.setAttribute("articleVendu", av);
		request.setAttribute("enchere", e);
		request.setAttribute("proprietaire", proprietaire);
		request.setAttribute("vainqueur", vainqueur);
		request.setAttribute("titre", titre);
		request.getRequestDispatcher("/WEB-INF/jsp/detail_vente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = request.getParameter("article") == null ? 0 : Integer.valueOf(request.getParameter("article"));
		
		if (request.getParameter("insert") != null) {
			try {
				// Mettre objet enchere
				EnchereManager.getInstance().addEnchere(null);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}			
		} else if (request.getParameter("retrait") != null) {
			try {
				// Mettre objet articleVendu
				ArticleVenduManager.getInstance().changeEtatArticle(null, "R");
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}

}
