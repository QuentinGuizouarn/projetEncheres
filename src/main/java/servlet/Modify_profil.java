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
import helpers.HashPassword;

/**
 * Servlet implementation class Servlet_create_profil
 */
@WebServlet("/modify_profil")
public class Modify_profil extends HttpServlet {
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
				request.setAttribute("utilisateur", u);
				request.getRequestDispatcher("/WEB-INF/jsp/modify_profil.jsp").forward(request, response);
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
		String id = request.getSession().getId();
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("last_name");
		String prenom = request.getParameter("first_name");
		String email = request.getParameter("email");
		String tel = request.getParameter("phone");
		String rue = request.getParameter("road");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("city");
		String mdp = request.getParameter("mdp");
		String newmdp = request.getParameter("newmdp");
		String compare = request.getParameter("compare");
		try {
			u = UtilisateurManager.getInstance().getById(Integer.valueOf(id));
			if (HashPassword.hashpassword(mdp).equals(u.getMotDePasse())) {
				u.setPseudo(pseudo);
				u.setNom(nom);
				u.setPrenom(prenom);
				u.setEmail(email);
				u.setTelephone(tel);
				u.setRue(rue);
				u.setCodePostal(cp);
				u.setVille(ville);
				if (newmdp != null || compare != null) {
					if (newmdp.equals(compare)) {
						u.setMotDePasse(HashPassword.hashpassword(newmdp));
					} else {
						response.sendError(500,"Le Nouveau mot de passe et la Confirmation doivent être identique");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
	}
}
