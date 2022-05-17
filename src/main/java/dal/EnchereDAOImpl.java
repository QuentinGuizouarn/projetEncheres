package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Enchere;
import dal.iDAO.EnchereDAO;

public class EnchereDAOImpl implements EnchereDAO {
	
	private static final String INSERT = "INSERT INTO ENCHERES (date, montant, idArticle, idUtilisateur) VALUES (?, ?, ?, ?)";
	private static final String SELECT_BY_ARTICLE = "SELECT MAX(idEnchere) AS idEnchere, date, montant, idArticle, idUtilisateur "
			+ "FROM ENCHERES WHERE idArticle = ? GROUP BY date, montant, idArticle, idUtilisateur";
	
	public Connection cnx;
	public EnchereDAOImpl() throws SQLException {
		cnx = ConnectionProvider.getConnection();
	}

	@Override
	public void insert(Enchere e) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(INSERT);
		ps.setDate(1, Date.valueOf(e.getDate()));
		ps.setInt(2, e.getMontant());
		ps.setInt(3, e.getLeArticle().getIdArticle());
		ps.setInt(4, e.getLeAcheteur().getIdUtilisateur());
		ps.executeUpdate();
		cnx.close();
	}

	@Override
	public Enchere selectByArticle(int idArticle) throws SQLException {
		Enchere e = null;
		return e;
	}

}
