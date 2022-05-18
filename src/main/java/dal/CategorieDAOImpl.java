package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Categorie;
import dal.iDAO.CategorieDAO;

public class CategorieDAOImpl implements CategorieDAO {
	
	private static final String INSERT = "INSERT INTO CATEGORIES (libelle) VALUES (?)";	
	private static final String SELECT_ALL = "SELECT * FROM CATEGORIES";
	
	public Connection cnx;
	public CategorieDAOImpl() throws SQLException {
		cnx = ConnectionProvider.getConnection();
	}

	@Override
	public void insert(Categorie c) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(INSERT);
		ps.setString(1, c.getLibelle());
		ps.executeUpdate();
		cnx.close();
	}

	@Override
	public List<Categorie> selectAll() throws SQLException {
		List<Categorie> liste = new ArrayList<Categorie>();
		PreparedStatement ps = cnx.prepareStatement(SELECT_ALL);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			liste.add(new Categorie( rs.getInt(1), rs.getString(2) ));
		}
		cnx.close();
		return liste;
	}

}
