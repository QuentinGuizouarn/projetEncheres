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
import exception.DuplicatePseudoException;
import exception.NotExistPseudoException;
import helpers.HashPassword;
import helpers.Util;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo").trim();
		String motDePass = HashPassword.hashpassword(request.getParameter("motdepasse")).trim();
		//System.out.println(user.toString());
		
//			boolean isPseudoEqual =  pseudo.equals(user.getPseudo());
//			boolean isMotDePassEqual = motDePass.equals(user.getMotDePasse()); 
		
		if(pseudo != null && motDePass != null){
			try {
				if (Util.isPresent(pseudo, motDePass)) {
					
					Utilisateur user = UtilisateurManager.getInstance().getByConnection(pseudo, motDePass);					
					request.getSession().setAttribute("user", user);
					
					response.sendRedirect(request.getContextPath()+"/AccesProfilServlet");
				} else {
					response.sendError(500, "utilisateur n'existe pas!");
				}
			} catch (DuplicatePseudoException e) {
				e.printStackTrace();
				response.sendError(500, e.getMessage());
			} catch (NotExistPseudoException e) {
				e.printStackTrace();
				response.sendError(500, e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				response.sendError(500, e.getMessage());
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(500);
			}
		}
	}
}
