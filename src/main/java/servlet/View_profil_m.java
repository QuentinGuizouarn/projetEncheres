package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bll.UtilisateurManager;
import bo.Utilisateur;

/**
 * Servlet implementation class Servlet_view_profil
 */
@WebServlet("/view_profil_m")
public class View_profil_m extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur u = null;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession()!=null) {
			String pseudo = (String) request.getSession().getAttribute("pseudo");
			String mdp = (String) request.getSession().getAttribute("mot de passe");
			try {
				u = UtilisateurManager.getInstance().getByConnection(pseudo, mdp);
				System.out.println(pseudo + " " + mdp);
				System.out.println(u.toString());
				request.setAttribute("utilisateur", u);
				request.getRequestDispatcher("/WEB-INF/jsp/view_profil_m.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		} else {
			response.sendRedirect(request.getContextPath()+"/connexion");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
