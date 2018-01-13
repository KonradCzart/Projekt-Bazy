package DataBase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHash{

	  private static Random RANDOM;
	  private static final int ITERATIONS = 10000;
	  private static final int KEY_LENGTH = 512;

	  /**
	   * static utility class
	   */
	  public PasswordHash() {
		  RANDOM = new SecureRandom();
	  }


	  public static String getNextSalt() {
	    byte[] salt = new byte[32];
	 //   RANDOM.setSeed();
	    RANDOM.nextBytes(salt);
	    String s = new String(salt);
	    return s;
	  }

	  private static byte[] hash(char[] password, byte[] salt) throws HashException {
	    PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
	    Arrays.fill(password, Character.MIN_VALUE);

	      SecretKeyFactory skf = null;
		try {
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		} catch (NoSuchAlgorithmException e) {
			throw new HashException();
		}
	      try {
			return skf.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException e) {
			throw new HashException();
		}
	  }

	  public static String hashPassword(String password, String salt)
	  {
			 byte[] saltt = salt.getBytes();
			 char[] charS = password.toCharArray();
			 byte[] hashpassword = null;
			 try {
				hashpassword = hash(charS, saltt);
			} catch (HashException e) {
				return null;
			}
			 
			 String line = new String(hashpassword);
			 return line;
			 
	  }

	  /**
	   * Generates a random password of a given length, using letters and digits.
	   *
	   * @param length the length of the password
	   *
	   * @return a random password
	   */

}
