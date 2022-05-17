package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Utilisateur;
import dal.iDAO.UtilisateurDAO;

public class UtilisateurDAOImpl implements UtilisateurDAO {
	
	private static final String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur)"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE idUtilisateur = ?";
	private static final String SELECT_BY_CONNECTION = "SELECT * FROM UTILISATEURS WHERE pseudo = ? AND motDePasse = ?";
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo = ?, motDePasse = ?, nom = ?, prenom = ?, email = ?, telephone = ?, "
			+ "rue = ?, codePostal = ?, ville = ?, credit = ? WHERE idUtilisateur = ?";
	private static final String DELETE = "DELETE FROM UTILISATEURS WHERE idUtilisateur = ?";
	
	public Connection cnx;
	public UtilisateurDAOImpl() throws SQLException {
		cnx = ConnectionProvider.getConnection();
	}

	@Override
	public void insert(Utilisateur u) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(INSERT);
		ps.setString(1, u.getPseudo());
		ps.setString(2, u.getNom());
		ps.setString(3, u.getPrenom());
		ps.setString(4, u.getEmail());
		ps.setString(5, u.getTelephone());
		ps.setString(6, u.getRue());
		ps.setString(7, u.getCodePostal());
		ps.setString(8, u.getVille());
		ps.setString(9, u.getMotDePasse());
		ps.setInt(10, u.getCredit());
		ps.setBoolean(11, u.isAdministrateur());
		ps.executeUpdate();
		cnx.close();	
	}

	@Override
	public Utilisateur selectById(int id) throws SQLException {
		Utilisateur u = null;
		PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			u = new Utilisateur( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
					rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getBoolean(12) );
		}
		cnx.close();
		return u;
	}

	@Override
	public Utilisateur selectByConnection(String pseudo, String motDePasse) throws SQLException {
		Utilisateur u = null;
		PreparedStatement ps = cnx.prepareStatement(SELECT_BY_CONNECTION);
		ps.setString(1, pseudo);
		ps.setString(2, motDePasse);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			u = new Utilisateur( rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
					rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getBoolean(12) );
		}
		cnx.close();
		return u;
	}

	@Override
	public void update(Utilisateur u) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(UPDATE);
		ps.setString(1, u.getPseudo());
		ps.setString(2, u.getNom());
		ps.setString(3, u.getPrenom());
		ps.setString(4, u.getEmail());
		ps.setString(5, u.getTelephone());
		ps.setString(6, u.getRue());
		ps.setString(7, u.getCodePostal());
		ps.setString(8, u.getVille());
		ps.setString(9, u.getMotDePasse());
		ps.setInt(10, u.getCredit());
		ps.setBoolean(11, u.isAdministrateur());
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
