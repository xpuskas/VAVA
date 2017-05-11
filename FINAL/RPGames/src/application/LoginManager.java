package application;

import java.util.Arrays;

public class LoginManager {

	private static char[] pass = {'t', 'o', 'o', 'r'};
	
	/**
	 * Approval of access when a user tries to log in - compares given char arrays
	 * which represent entered password and password retrieved from the database
	 * by entered user name. Also allows for root login.
	 * @param usrnm - entered user name
	 * @param psswd - password retrieved from the database
	 * @param psswd_entered - password typed in by user trying to get access to application
	 * @return true of false
	 */
	public static boolean validate (String usrnm, char[] psswd, char[] psswd_entered) {
		
		boolean success = false;
		
		if(Arrays.equals(psswd, psswd_entered)) {
			success = true;
		}
		
		if (usrnm.equals("root") && Arrays.equals(psswd, pass)) {
			success = true;
		}
		
		return success; 
	}
}
