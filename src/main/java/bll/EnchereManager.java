package bll;

import java.sql.SQLException;

import bo.ArticleVendu;
import bo.Enchere;
import dal.Factory;

public class EnchereManager {
	
	public static EnchereManager instance = new EnchereManager();	
	private EnchereManager() {}	
	
	public static EnchereManager getInstance() {
		return instance;
	}
	
	public void addEnchere (Enchere e) throws SQLException {
		Factory.createEnchereDAO().insert(e);
	}
	
	public Enchere getByArticle(ArticleVendu av) throws SQLException {
		return Factory.createEnchereDAO().selectByArticle(av);
	}

}
