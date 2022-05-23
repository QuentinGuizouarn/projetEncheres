package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Enchere;
import bo.Utilisateur;
import dal.iDAO.EnchereDAO;
import helpers.Util;

public class EnchereDAOImpl implements EnchereDAO {
	
	private static final String INSERT = "INSERT INTO ENCHERES (date, montant, idArticle, idUtilisateur) VALUES (?, ?, ?, ?)";
	private static final String SELECT_MAX_BY_ARTICLE = "SELECT idEnchere = MAX(E.idEnchere), dateEnchere = E.date, E.montant,"
			+ " A.idArticle, A.nom, A.description, A.idCategorie, C.libelle, A.prixInitial, A.prixVente, A.dateDebut, A.dateFin, A.rue,"
			+ "	A.codePostal, A.ville, A.etat, idAcheteur = UA.idUtilisateur, pseudoAcheteur = UA.pseudo,"
			+ "	idVendeur = UV.idUtilisateur, pseudoVendeur = UV.pseudo, telephoneVendeur = UV.telephone"
			+ "	FROM ENCHERES E"
			+ "	INNER JOIN UTILISATEURS UA ON UA.idUtilisateur = E.idUtilisateur"
			+ "	INNER JOIN ARTICLES_VENDUS A ON A.idArticle = E.idArticle"
			+ "	INNER JOIN UTILISATEURS UV ON UV.idUtilisateur = A.idUtilisateur"
			+ "	INNER JOIN CATEGORIES C ON C.idCategorie = A.idCategorie"
			+ "	WHERE E.idArticle = ?"
			+ "	GROUP BY E.date, E.montant, A.idArticle, A.nom, A.description, A.idCategorie, C.libelle, A.prixInitial, A.prixVente,"
			+ "	A.dateDebut, A.dateFin, A.rue, A.codePostal, A.ville, A.etat, UA.idUtilisateur, UA.pseudo, UV.idUtilisateur, UV.pseudo, UV.telephone";
	
	public Connection cnx;
	public EnchereDAOImpl() throws SQLException {
		cnx = ConnectionProvider.getConnection();
	}

	@Override
	public void insert(Enchere e) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(INSERT);
		ps.setTimestamp(1, Timestamp.valueOf(e.getDate()));
		ps.setInt(2, e.getMontant());
		ps.setInt(3, e.getLeArticle().getIdArticle());
		ps.setInt(4, e.getLeAcheteur().getIdUtilisateur());
		ps.executeUpdate();
		cnx.close();
	}

	@Override
	public Enchere selectMaxByArticle(int idArticle) throws SQLException {
		Enchere e = null;
		ArticleVendu av = null;
		Categorie c = null;
		Utilisateur u = null;
		PreparedStatement ps = cnx.prepareStatement(SELECT_MAX_BY_ARTICLE);
		ps.setInt(1, idArticle);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			c = new Categorie( rs.getInt("idCategorie"), rs.getString("libelle") );
			av = new ArticleVendu( rs.getInt("idArticle"), rs.getInt("prixInitial"), rs.getInt("prixVente"), rs.getString("nom"),
				rs.getString("description"), rs.getString("etat"), rs.getString("rue"), rs.getString("codePostal"), rs.getString("ville"),
				rs.getDate("dateDebut").toLocalDate(), rs.getDate("dateFin").toLocalDate(), new Utilisateur( rs.getInt("idVendeur"),
				rs.getString("pseudoVendeur"), rs.getString("telephoneVendeur") ), c );
			u = new Utilisateur( rs.getInt("idAcheteur"), rs.getString("pseudoAcheteur"), null );
			e = new Enchere( rs.getInt("idEnchere"), rs.getTimestamp("dateEnchere").toLocalDateTime(), rs.getInt("montant"), av, u );
		}
		return e;
	}

}
