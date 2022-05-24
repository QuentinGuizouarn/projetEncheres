package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.dbcp2.PoolablePreparedStatement;

import bll.UtilisateurManager;
import bo.Utilisateur;
import dal.ConnectionProvider;
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

		Utilisateur user;
		
		try {
			String pseudo = request.getParameter("pseudo").trim();
			String motDePass = HashPassword.hashpassword(request.getParameter("motdepasse")).trim();
			user = UtilisateurManager.getInstance().getByConnection(pseudo, motDePass);
			//System.out.println(user.toString());
			
//			boolean isPseudoEqual =  pseudo.equals(user.getPseudo());
//			boolean isMotDePassEqual = motDePass.equals(user.getMotDePasse()); 
			
			if(pseudo != null && motDePass != null){
				try {
					if (Util.isPresent(pseudo, motDePass)) {
						
//					pseudo.equals(user.getPseudo());
//					motDePass.equals(user.getMotDePasse());
						
						HttpSession session = request.getSession();
						session.setAttribute("pseudo", pseudo);
						session.setAttribute("mot de passe", motDePass);
						
						response.sendRedirect(request.getContextPath()+"/AccesProfilServlet");
					} else {
						response.sendError(500, "utilisateur n'existe pas!");
					}
				} catch (DuplicatePseudoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendError(500, e.getMessage());
				} catch (NotExistPseudoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendError(500, e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendError(500, e.getMessage());
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
