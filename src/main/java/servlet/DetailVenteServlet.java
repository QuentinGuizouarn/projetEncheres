package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
	
	private Utilisateur u = null;
	private ArticleVendu av = null;
	private Enchere e = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = request.getParameter("article") == null ? 0 : Integer.valueOf(request.getParameter("article"));
		Boolean proprietaire = false;
		Boolean vainqueur = false;		
		String titre = "D�tail vente";
		if (id != 0) {
			try {
				u = UtilisateurManager.getInstance().getById(3);
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
		if (vainqueur) {
			if (av.getEtat().equalsIgnoreCase("t") || av.getEtat().equalsIgnoreCase("r")) {
				titre = "Vous avez remport� la vente";
			} else {
				titre = "Vous �tes le leader des ench�res";
			}
		} else {
			if (av.getEtat().equalsIgnoreCase("t") || av.getEtat().equalsIgnoreCase("r")) {
				titre = e.getLeAcheteur().getPseudo() + " a remport� l'ench�re";
			}
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
		if (request.getParameter("insert") != null) {
			try {
				LocalDateTime date = LocalDateTime.now();
				int montant = Integer.valueOf(request.getParameter("offre"));
				e = new Enchere(date, montant, av, u);
				EnchereManager.getInstance().addEnchere(e);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		} else if (request.getParameter("retrait") != null) {
			try {
				ArticleVenduManager.getInstance().changeEtatArticle(av, "R");
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
		av = null;
		e = null;
		response.sendRedirect(request.getContextPath() + "/liste");
	}

}
