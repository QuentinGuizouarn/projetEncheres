package helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class Util {
	
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

}
