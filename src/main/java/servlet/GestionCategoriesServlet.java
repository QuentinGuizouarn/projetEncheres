package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.CategorieManager;
import bo.Categorie;
import bo.Utilisateur;

/**
 * Servlet implementation class GestionCategoriesServlet
 */
@WebServlet("/liste_categories")
public class GestionCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	
	private List<Categorie> lesCategories = null;
	private Utilisateur u;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		u = (Utilisateur) request.getSession().getAttribute("user");
		if (u.isAdministrateur() == true) {
			try {
				lesCategories = CategorieManager.getInstance().getAll();
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
		request.setAttribute("liste", lesCategories);
		request.getRequestDispatcher("/WEB-INF/jsp/liste_categories.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
