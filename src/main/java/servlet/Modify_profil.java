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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession()!=null) {
			try {
				Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
				u = UtilisateurManager.getInstance().getById(user.getIdUtilisateur());
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
			if (request.getParameter("save") != null) {
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
				u = UtilisateurManager.getInstance().getById(user.getIdUtilisateur());
				if (HashPassword.hashpassword(mdp).equals(u.getMotDePasse())) {
					u.setPseudo(pseudo);
					u.setNom(nom);
					u.setPrenom(prenom);
					u.setEmail(email);
					u.setTelephone(tel);
					u.setRue(rue);
					u.setCodePostal(cp);
					u.setVille(ville);
					if (!newmdp.isEmpty() && !compare.isEmpty()) {
						if (newmdp.equals(compare)) {
							u.setMotDePasse(HashPassword.hashpassword(newmdp));
						} else {
							response.sendError(500,"Le Nouveau mot de passe et la Confirmation doivent être identique");
						}
					}
					UtilisateurManager.getInstance().changeUtilisateur(u);
					response.sendRedirect(request.getContextPath()+"/AccesProfilServlet");
				} else {
					response.sendRedirect(request.getContextPath()+"/modify_profil");
				}
			} else if (request.getParameter("delete") != null) {
				UtilisateurManager.getInstance().removeUtilisateur(u.getIdUtilisateur());
				response.sendRedirect(request.getContextPath()+"/connexion");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
