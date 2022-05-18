package dal.iDAO;

import java.sql.SQLException;

import bo.Utilisateur;

public interface UtilisateurDAO {
	
	public void insert(Utilisateur u) throws SQLException;
	public Utilisateur selectById(int id) throws SQLException;
	public Utilisateur selectByConnection(String pseudo, String motDePasse) throws SQLException;
	public void update(Utilisateur u) throws SQLException;
	public void delete(int id) throws SQLException;

}
