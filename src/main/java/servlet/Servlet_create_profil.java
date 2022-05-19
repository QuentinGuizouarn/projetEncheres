package servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet_create_profil
 */
@WebServlet("/create_profil")
public class Servlet_create_profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_create_profil() {
        super();
        // TODO Auto-generated constructor stub
    }
    
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
		String nom = request.getParameter("last_name");
		String prenom = request.getParameter("first_name");
		String email = request.getParameter("email");
		String motDePass = request.getParameter("mdp");
		String compare = request.getParameter("compare");
		
		if (motDePass.equals(compare)) {
			if (motDePass.contains(nom)){response.sendError(500,"Le mot de passe ne peut pas contenir votre nom");}
			if (motDePass.contains(prenom)){response.sendError(500,"Le mot de passe ne peut pas contenir votre prénom");}
			if(isValidEmailId(email) && isPasswordValide(motDePass)) {
				response.sendRedirect(request.getContextPath()+"/liste");
			}
			else {
				//response.sendRedirect(request.getContextPath()+"/connexion");
				response.sendError(500,"champs invalide");
			}
		} else {
			response.sendError(500,motDePass + " != " + compare);
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


