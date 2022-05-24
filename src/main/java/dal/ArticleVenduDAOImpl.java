package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.ArticleVendu;
import bo.Categorie;
import bo.Utilisateur;
import dal.iDAO.ArticleVenduDAO;

public class ArticleVenduDAOImpl implements ArticleVenduDAO {
	
	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom, description, dateDebut, dateFin, prixInitial,"
			+ "	prixVente, rue, codePostal, ville, etat, idUtilisateur, idCategorie)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT A.idArticle, A.nom, A.description, A.dateDebut, A.dateFin, A.prixInitial,"
			+ "	A.prixVente, A.rue, A.codePostal, A.ville, A.etat, A.idUtilisateur, U.pseudo, A.idCategorie, C.libelle"
			+ " FROM ARTICLES_VENDUS A"
			+ " INNER JOIN UTILISATEURS U ON U.idUtilisateur = A.idUtilisateur"
			+ " INNER JOIN CATEGORIES C ON C.idCategorie = A.idCategorie"
			+ " WHERE A.idArticle = ?";
	private static final String SELECT_ALL = "SELECT A.idArticle, A.nom, A.description, A.dateDebut, A.dateFin, A.prixInitial,"
			+ " A.prixVente, A.rue, A.codePostal, A.ville, A.etat, A.idUtilisateur, U.pseudo, A.idCategorie, C.libelle"
			+ " FROM ARTICLES_VENDUS A"
			+ " INNER JOIN UTILISATEURS U ON U.idUtilisateur = A.idUtilisateur"
			+ " INNER JOIN CATEGORIES C ON C.idCategorie = A.idCategorie";
	private static final String SELECT_BY_NOM = "SELECT A.idArticle, A.nom, A.description, A.dateDebut, A.dateFin, A.prixInitial,"
			+ " A.prixVente, A.rue, A.codePostal, A.ville, A.etat, A.idUtilisateur, U.pseudo, A.idCategorie, C.libelle"
			+ " FROM ARTICLES_VENDUS A"
			+ " INNER JOIN UTILISATEURS U ON U.idUtilisateur = A.idUtilisateur"
			+ " INNER JOIN CATEGORIES C ON C.idCategorie = A.idCategorie"
			+ " WHERE A.nom LIKE ? ";
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom = ?, description = ?, dateDebut = ?, dateFin = ?,"
			+ " prixInitial = ?, prixVente = ?, rue = ?, codePostal = ?, ville = ?, etat = ?, idUtilisateur = ?, idCategorie = ?"
			+ " WHERE idArticle = ?";
	private static final String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE idArticle = ?";
	
	public Connection cnx;
	public ArticleVenduDAOImpl() throws SQLException {
		cnx = ConnectionProvider.getConnection();
	}

	@Override
	public void insert(ArticleVendu av) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(INSERT);
		ps.setString(1, av.getNom());
		ps.setString(2, av.getDescription());
		ps.setDate(3, Date.valueOf(av.getDateDebut()));
		ps.setDate(4, Date.valueOf(av.getDateFin()));
		ps.setInt(5, av.getPrixInitial());
		ps.setInt(6, av.getPrixVente());
		ps.setString(7, av.getRue());
		ps.setString(8, av.getCodePostal());
		ps.setString(9, av.getVille());
		ps.setString(10, av.getEtat());
		ps.setInt(11, av.getLeVendeur().getIdUtilisateur());
		ps.setInt(12, av.getLaCategorie().getIdCategorie());
		ps.executeUpdate();
		cnx.close();
	}

	@Override
	public ArticleVendu selectById(int id) throws SQLException {
		ArticleVendu av = null;
		PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			av = new ArticleVendu( rs.getInt("idArticle"), rs.getInt("prixInitial"), rs.getInt("prixVente"), rs.getString("nom"), 
				rs.getString("description"), rs.getString("etat"), rs.getString("rue"), rs.getString("codePostal"), rs.getString("ville"),
				rs.getDate("dateDebut").toLocalDate(), rs.getDate("dateFin").toLocalDate(), new Utilisateur( rs.getInt("idUtilisateur"),
				rs.getString("pseudo"), null ), new Categorie( rs.getInt("idCategorie"), rs.getString("libelle") ) );
		}
		cnx.close();
		return av;
	}

	@Override
	public List<ArticleVendu> selectAll() throws SQLException {
		List<ArticleVendu> liste = new ArrayList<ArticleVendu>();
		Statement st = cnx.createStatement();
		ResultSet rs = st.executeQuery(SELECT_ALL);
		while (rs.next()) {
			liste.add(new ArticleVendu( rs.getInt("idArticle"), rs.getInt("prixInitial"), rs.getInt("prixVente"), rs.getString("nom"), 
				rs.getString("description"), rs.getString("etat"), rs.getString("rue"), rs.getString("codePostal"), rs.getString("ville"),
				rs.getDate("dateDebut").toLocalDate(), rs.getDate("dateFin").toLocalDate(), new Utilisateur( rs.getInt("idUtilisateur"),
				rs.getString("pseudo"), null ), new Categorie( rs.getInt("idCategorie"), rs.getString("libelle") ) ));
		}
		cnx.close();
		return liste;
	}
	
	@Override
	public List<ArticleVendu> selectByNom(String nom) throws SQLException {
		List<ArticleVendu> liste = new ArrayList<ArticleVendu>();
		PreparedStatement ps = cnx.prepareStatement(SELECT_BY_NOM);
		ps.setString(1, "%" + nom + "%");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			liste.add(new ArticleVendu( rs.getInt("idArticle"), rs.getInt("prixInitial"), rs.getInt("prixVente"), rs.getString("nom"), 
				rs.getString("description"), rs.getString("etat"), rs.getString("rue"), rs.getString("codePostal"), rs.getString("ville"),
				rs.getDate("dateDebut").toLocalDate(), rs.getDate("dateFin").toLocalDate(), new Utilisateur( rs.getInt("idUtilisateur"),
				rs.getString("pseudo"), null ), new Categorie( rs.getInt("idCategorie"), rs.getString("libelle") ) ));
		}
		return liste;
	}

	@Override
	public void update(ArticleVendu av) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(UPDATE);
		ps.setString(1, av.getNom());
		ps.setString(2, av.getDescription());
		ps.setDate(3, Date.valueOf(av.getDateDebut()));
		ps.setDate(4, Date.valueOf(av.getDateFin()));
		ps.setInt(5, av.getPrixInitial());
		ps.setInt(6, av.getPrixVente());
		ps.setString(7, av.getRue());
		ps.setString(8, av.getCodePostal());
		ps.setString(9, av.getVille());
		ps.setString(10, av.getEtat());
		ps.setInt(11, av.getLeVendeur().getIdUtilisateur());
		ps.setInt(12, av.getLaCategorie().getIdCategorie());
		ps.setInt(13, av.getIdArticle());
		ps.executeUpdate();
		cnx.close();
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(DELETE);
		ps.setInt(1, id);
		ps.executeUpdate();
		cnx.close();
	}
	
}
