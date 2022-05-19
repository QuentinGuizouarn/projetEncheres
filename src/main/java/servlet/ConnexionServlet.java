package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bll.UtilisateurManager;
import bo.Utilisateur;
import helpers.HashPassword;

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

		Utilisateur user;
		
		try {
			String pseudo = request.getParameter("pseudo").trim();
			String motDePass = HashPassword.hashpassword(request.getParameter("motdepasse")).trim();
			user = UtilisateurManager.getInstance().getByConnection(pseudo, pseudo);
			//System.out.println(user);
			
//			boolean isPseudoEqual =  pseudo.equals(user.getPseudo());
//			boolean isMotDePassEqual = motDePass.equals(user.getMotDePasse()); 
			
			if(pseudo != null && motDePass != null){
				
//				pseudo.equals(user.getPseudo());
//				motDePass.equals(user.getMotDePasse());
				
				HttpSession session = request.getSession();
				session.setAttribute("pseudo", pseudo);
				session.setAttribute("mot de passe", motDePass);
				
				response.sendRedirect(request.getContextPath()+"/AccesProfilServlet");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			response.sendError(500);
		}
	}	
}
