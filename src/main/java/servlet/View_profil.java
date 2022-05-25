package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
@WebServlet("/view_profil")
public class View_profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur user = null;
	private Utilisateur profil = null;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession()!=null) {
			String pseudo = (String) request.getSession().getAttribute("pseudo");
			String mdp = (String) request.getSession().getAttribute("mot de passe");
			try {
				user = UtilisateurManager.getInstance().getByConnection(pseudo, mdp);
				if(request.getParameter("user")!=null) {
					profil = UtilisateurManager.getInstance().getById(Integer.valueOf(request.getParameter("user")));
				}
				//System.out.println(pseudo + " " + mdp);
				//System.out.println(user.toString());
				request.setAttribute("utilisateur", user);
				request.setAttribute("profil", profil);
				request.getRequestDispatcher("/WEB-INF/jsp/view_profil.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			} catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
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
