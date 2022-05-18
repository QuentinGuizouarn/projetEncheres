package dal;

import java.sql.SQLException;

import dal.iDAO.ArticleVenduDAO;
import dal.iDAO.CategorieDAO;
import dal.iDAO.EnchereDAO;
import dal.iDAO.UtilisateurDAO;

public class Factory {
	
	public static ArticleVenduDAO createArticleVenduDAO() throws SQLException {
		return new ArticleVenduDAOImpl();	
	}
	
	public static CategorieDAO createCategorieDAO() throws SQLException {
		return new CategorieDAOImpl();	
	}
	
	public static EnchereDAO createEnchereDAO() throws SQLException {
		return new EnchereDAOImpl();	
	}
	
	public static UtilisateurDAO createUtilisateurDAO() throws SQLException {
		return new UtilisateurDAOImpl();	
	}

}
