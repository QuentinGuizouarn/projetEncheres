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
import helpers.HashPassword;
import helpers.Util;

/**
 * Servlet implementation class Servlet_create_profil
 */
@WebServlet("/create_profil")
public class Create_profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur u;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/create_profil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("last_name");
		String prenom = request.getParameter("first_name");
		String email = request.getParameter("email");
		String tel = request.getParameter("phone");
		String rue = request.getParameter("road");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("city");
		String mdp = request.getParameter("mdp");
		String compare = request.getParameter("compare");

		try {
			if (mdp.equals(compare)) {
				if (!mdp.contains(nom)){
					if (!mdp.contains(prenom)){
						if (!Util.isPseudoUsed(pseudo)) {
							if(Util.isEmailValid(email) && Util.isPasswordValide(mdp)) {
								try {
									if (mdp.equals(compare)) {
										u = new Utilisateur(pseudo, HashPassword.hashpassword(mdp), nom, prenom, email, tel, rue, cp, ville, 0, false);
										//System.out.println(u.toString());
										UtilisateurManager.getInstance().addUtilisateur(u);
										response.sendRedirect(request.getContextPath()+"/liste");
									}
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else { 
								System.out.println(mdp + Util.isPasswordValide(mdp) + " -- " + email + Util.isEmailValid(email));
								response.sendError(500,"email ou mot de passe invalide"); }
						}
					} else { response.sendError(500,"Le mot de passe ne peut pas contenir votre prénom");}
				} else { response.sendError(500,"Le mot de passe ne peut pas contenir votre nom"); }
			} else { response.sendError(500,"Le mot de passe et la Confirmation doivent être identique"); }
		} catch (DuplicatePseudoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response.sendError(500, e1.getMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}


