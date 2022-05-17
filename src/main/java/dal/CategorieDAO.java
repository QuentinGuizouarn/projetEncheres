package dal;

import java.sql.SQLException;
import java.util.List;

import bo.Categorie;

public interface CategorieDAO {
	
	public void insert(Categorie c) throws SQLException;
	public List<Categorie> selectAll() throws SQLException;

}
