package dal.iDAO;

import java.sql.SQLException;
import java.util.List;

import bo.Enchere;

public interface EnchereDAO {

	public void insert(Enchere e) throws SQLException;
	public Enchere selectMaxByArticle(int idArticle) throws SQLException;
	//public List<Enchere> selectAll() throws SQLException;
	
}
