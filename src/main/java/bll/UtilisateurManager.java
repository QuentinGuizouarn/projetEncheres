package bll;

import java.sql.SQLException;

import bo.Utilisateur;
import dal.Factory;

public class UtilisateurManager {
	
	public static UtilisateurManager instance = new UtilisateurManager();	
	private UtilisateurManager() {}	
	
	public static UtilisateurManager getInstance() {
		return instance;
	}
	
	public void addUtilisateur(Utilisateur u) throws SQLException {
		Factory.createUtilisateurDAO().insert(u);
	}
	
	public Utilisateur getById(int id) throws SQLException {
		return Factory.createUtilisateurDAO().selectById(id);
	}
	
	public Utilisateur getByConnection(String pseudo, String motDePasse) throws SQLException {
		return Factory.createUtilisateurDAO().selectByConnection(pseudo, motDePasse);
	}
	
	public void changeUtilisateur(Utilisateur u) throws SQLException {
		Factory.createUtilisateurDAO().update(u);
	}
	
	public void removeUtilisateur(int id) throws SQLException {
		Factory.createUtilisateurDAO().delete(id);
	}

}
