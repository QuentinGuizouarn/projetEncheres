package bll;

import java.sql.SQLException;
import java.util.List;

import bo.ArticleVendu;
import dal.Factory;

public class ArticleVenduManager {
	
	public static ArticleVenduManager instance = new ArticleVenduManager();	
	private ArticleVenduManager() {}	
	
	public static ArticleVenduManager getInstance() {
		return instance;
	}
	
	public void addArticleVendu(ArticleVendu av) throws SQLException {
		Factory.createArticleVenduDAO().insert(av);
	}
	
	public ArticleVendu getById(int id) throws SQLException {
		return Factory.createArticleVenduDAO().selectById(id);
	}
	
	public List<ArticleVendu> getAll() throws SQLException {
		return Factory.createArticleVenduDAO().selectAll();
	}
	
	public List<ArticleVendu> getByNom(String nom) throws SQLException {
		return Factory.createArticleVenduDAO().selectByNom(nom);
	}
	
	public void changeArticleVendu(ArticleVendu av) throws SQLException {
		Factory.createArticleVenduDAO().update(av);
	}
	
	public void changeEtatArticle(ArticleVendu av, String etat) throws SQLException {
		Factory.createArticleVenduDAO().updateEtat(av, etat);
	}
	
	public void removeArticleVendu(int id) throws SQLException {
		Factory.createArticleVenduDAO().delete(id);
	}	

}
