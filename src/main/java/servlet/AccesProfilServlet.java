package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.ArticleVenduManager;
import bll.EnchereManager;
import bo.ArticleVendu;
import bo.Enchere;

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
		Enchere enchere = null;
		try {
			
			List<ArticleVendu> listeArticle = ArticleVenduManager.getInstance().getAll();
			
			for (ArticleVendu Article : listeArticle) {
				System.out.println(Article.getNom());
			}
			
			
			request.setAttribute("articleList", listeArticle);
			
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/ViewAccesProfil.jsp");
			
			//List<ArticleVendu> Listarticle = ArticleVenduManager.getInstance().getByNom("filter");
			
			
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
		
	}

}
