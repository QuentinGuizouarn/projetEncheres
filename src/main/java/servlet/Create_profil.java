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

import bll.UtilisateurManager;
import bo.Utilisateur;

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
		
		if (mdp.equals(compare)) {
			if (mdp.contains(nom)){response.sendError(500,"Le mot de passe ne peut pas contenir votre nom");}
			if (mdp.contains(prenom)){response.sendError(500,"Le mot de passe ne peut pas contenir votre prénom");}
			if(isValidEmailId(email) && isPasswordValide(mdp)) {
				try {
					if (mdp.equals(compare)) {
						u = new Utilisateur(pseudo, mdp, nom, prenom, email, tel, rue, cp, ville, 0, false);
						System.out.println(u.toString());
						UtilisateurManager.getInstance().addUtilisateur(u);
						response.sendRedirect(request.getContextPath()+"/liste");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				//response.sendRedirect(request.getContextPath()+"/connexion");
				response.sendError(500,"email ou mot de passe invalide");
			}
		} else {
			response.sendError(500,"Le mot de passe et la Confirmation doivent être identique");
		}
	}

	public static boolean isValidEmailId(String email) {
		String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern p = Pattern.compile(emailPattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}



	public static boolean isPasswordValide(String motDePass) {
		String motDePasse = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>])(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(motDePasse);
		Matcher m = p.matcher(motDePass);
		return m.matches();
	}
}


