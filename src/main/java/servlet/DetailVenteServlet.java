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
		String id = request.getParameter("article");
		Utilisateur u = null;
		ArticleVendu av = null;
		Enchere e = null;
		if (id != null) {
			try {
				av = ArticleVenduManager.getInstance().getById(Integer.valueOf(id));
				//e = EnchereManager.getInstance().getMaxByArticle(Integer.valueOf(id));
			} catch (SQLException e1) {				
				e1.printStackTrace();
				response.sendError(500);
			}
		}
		request.setAttribute("article", av);
		request.setAttribute("enchere", e);
		request.getRequestDispatcher("/WEB-INF/jsp/detail_vente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
