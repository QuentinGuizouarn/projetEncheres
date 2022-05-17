package dal.iDAO;

import java.sql.SQLException;

import bo.Enchere;

public interface EnchereDAO {

	public void insert(Enchere e) throws SQLException;
	public Enchere selectByArticle(int id) throws SQLException;
	public void delete(int id) throws SQLException;
	
}
