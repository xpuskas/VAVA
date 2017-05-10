package language;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
	
	private static final List<String> languageOptions = new ArrayList<String>();
	private static final List<String> languageShortcuts = new ArrayList<String>();
	
	private static final String slovak = "Slovenský jazyk";
	private static final String slovak_s = "sk";
	
	private static final String english = "English";
	private static final String english_s = "en";
	
	private static ResourceBundle resource = null;
	
	
	public static void initialize() {
		
		languageOptions.add(slovak);
		languageShortcuts.add(slovak_s);
		
		languageOptions.add(english);
		languageShortcuts.add(english_s);
	}
	
	public static void setLanguage(String language) {

		if(languageOptions.contains(language)) {
			language = languageShortcuts.get(languageOptions.indexOf(language));
		}
		
		Locale locale = new Locale(language);
	//	Locale locale = new Locale(slovak_s);
		resource = ResourceBundle.getBundle("language.language", locale);
		
	}
	
	public static String getProperty(String propertyName) {
		if(resource == null) {
			setLanguage("default");
		}
	
		return resource.getString(propertyName);
	}
	
	public static List<String> getLanguageOptions() {
		return languageOptions;
	}
}
