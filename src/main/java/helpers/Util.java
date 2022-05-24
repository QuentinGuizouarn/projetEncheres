package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dal.ConnectionProvider;
import servlet.DuplicatePseudoException;
import servlet.NotExistPseudoException;

public class Util {

	public static Connection cnx;
	
	public static String hashpassword(String password) {
		String generatedPassword = null;
	    try 
	    {
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(password.getBytes());
	      byte[] bytes = md.digest();

	      StringBuilder sb = new StringBuilder();
	      for (int i = 0; i < bytes.length; i++) {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	      }
	      generatedPassword = sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }	
	    return generatedPassword;
	}
	
	public static void checkField(String message, String s) throws Exception {
		if (s.isEmpty() || s.isBlank()) {
			throw new Exception(message);
		}
	}
	
	public static Boolean enchereApresDateJour(LocalDate date) {
		return LocalDate.now().isAfter(date);
	}

	public static boolean isEmailValid(String email) {
		String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z-]+\\.)+[a-zA-Z]{2,6}$";
		Pattern p = Pattern.compile(emailPattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}



	public static boolean isPasswordValide(String motDePass) {
		String motDePasse = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,20}$";
		Pattern p = Pattern.compile(motDePasse);
		Matcher m = p.matcher(motDePass);
		return m.matches();
	}

	public static boolean isPresent(String pseudo, String motDePass) throws DuplicatePseudoException, NotExistPseudoException{
        boolean isAvailable = false;
        String query = "SELECT * FROM UTILISATEURS WHERE pseudo = ? AND motDePasse = ?";
        
        try{
        	int i = 0;
    		cnx = ConnectionProvider.getConnection();
            PreparedStatement statement;
            ResultSet resultSet;
            statement = cnx.prepareStatement(query);

            statement.setString(1, pseudo);
            statement.setString(2, motDePass);
            
            //System.out.println(pseudo + " - " + motDePass);
            
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
            	//System.out.println("Dans le RS " + i);
            	i++;
            	if (i > 1) {
            		throw new DuplicatePseudoException("Utilisateur existe deux fois");
            	}
                isAvailable = true;
            }else{
        		throw new NotExistPseudoException("utilisateur n'existe pas!");
            }
        } catch(SQLException e){
        	e.printStackTrace();
        }
        return isAvailable;
	}

	public static boolean isPseudoUsed(String pseudo) throws DuplicatePseudoException {
		boolean isUsed = false;

        String query = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
        
        try{
        	int i = 0;
    		cnx = ConnectionProvider.getConnection();
            PreparedStatement statement;
            ResultSet resultSet;
            statement = cnx.prepareStatement(query);

            statement.setString(1, pseudo);
                        
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
            	throw new DuplicatePseudoException("Pseudo déjà utilisé!");
            }
        } catch(SQLException e){
        	e.printStackTrace();
        }
		return isUsed;
	}
}
