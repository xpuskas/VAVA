package application;

import java.util.Arrays;

public class LoginManager {

	private static char[] pass = {'t', 'o', 'o', 'r'};
	
	public static boolean validate (String usrnm, char[] psswd) {
		
		boolean success = false;
		
		if (usrnm.equals("root") && Arrays.equals(psswd, pass)) {
			success = true;
		}
		
		return success; 
	}
}
