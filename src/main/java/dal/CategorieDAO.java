package dal;

import java.sql.SQLException;

import bo.Categorie;

public interface CategorieDAO {
	
	public void insert(Categorie c) throws SQLException;
	public Categorie selectAll() throws SQLException;

}
