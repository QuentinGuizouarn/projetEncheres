package dal.iDAO;

import java.sql.SQLException;
import java.util.List;

import bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	public void insert(ArticleVendu av) throws SQLException;
	public ArticleVendu selectById(int id) throws SQLException;
	public List<ArticleVendu> selectAll() throws SQLException;
	public List<ArticleVendu> selectByNom(String nom) throws SQLException;
	public void update(ArticleVendu av) throws SQLException;
	public void delete(int id) throws SQLException;	

}
