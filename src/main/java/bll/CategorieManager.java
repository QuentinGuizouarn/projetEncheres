package bll;

import java.sql.SQLException;
import java.util.List;

import bo.Categorie;
import dal.Factory;

public class CategorieManager {
	
	public static CategorieManager instance = new CategorieManager();	
	private CategorieManager() {}	
	
	public static CategorieManager getInstance() {
		return instance;
	}
	
	public void addCategorie(Categorie c) throws SQLException {
		Factory.createCategorieDAO().insert(c);
	}
	
	public List<Categorie> getAll() throws SQLException {
		return Factory.createCategorieDAO().selectAll();
	}

}
