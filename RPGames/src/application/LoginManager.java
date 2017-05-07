package application;

import java.util.Arrays;

public class LoginManager {

	private static char[] pass = {'t', 'o', 'o', 'r'};
	
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
