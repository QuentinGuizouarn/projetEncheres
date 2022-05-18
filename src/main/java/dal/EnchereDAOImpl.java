package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.ArticleVendu;
import bo.Enchere;
import bo.Utilisateur;
import dal.iDAO.EnchereDAO;

public class EnchereDAOImpl implements EnchereDAO {
	
	private static final String INSERT = "INSERT INTO ENCHERES (date, montant, idArticle, idUtilisateur) VALUES (?, ?, ?, ?)";
	private static final String SELECT_BY_ARTICLE = "SELECT idEnchere = MAX(E.idEnchere), E.date, E.montant, E.idArticle,"
			+ "		U.idUtilisateur, U.pseudo"
			+ "	FROM ENCHERES E"
			+ "	INNER JOIN UTILISATEURS U"
			+ "		ON U.idUtilisateur = E.idUtilisateur"
			+ "	WHERE E.idArticle = ?"
			+ "	GROUP BY E.date, E.montant, E.idArticle, U.idUtilisateur, U.pseudo";
	
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
	public Enchere selectByArticle(ArticleVendu av) throws SQLException {
		Enchere e = null;
		PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ARTICLE);
		ps.setInt(1, av.getIdArticle());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			e = new Enchere( rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getInt(3), av, new Utilisateur(rs.getInt(5), rs.getString(6)) );
		}
		return e;
	}

}
