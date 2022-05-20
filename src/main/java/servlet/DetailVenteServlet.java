package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
		if (id != 0) {
			try {
				u = UtilisateurManager.getInstance().getById(2);
				av = ArticleVenduManager.getInstance().getById(id);
				e = EnchereManager.getInstance().getMaxByArticle(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
				response.sendError(500);
			}
		}
		request.setAttribute("utilisateur", u);
		request.setAttribute("articleVendu", av);
		request.setAttribute("enchere", e);
		request.getRequestDispatcher("/WEB-INF/jsp/detail_vente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = request.getParameter("article") == null ? 0 : Integer.valueOf(request.getParameter("article"));
		if (request.getParameter("insert") != null) {
			System.out.println(id);
		}		
	}

}
