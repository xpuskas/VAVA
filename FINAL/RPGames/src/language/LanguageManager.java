package language;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Abstract class which provides set of static methods used for setting the current language,
 * providing language options in a form appropriate for displaying in graphical interface
 * and retrieving properties from language property files.
 */
public class LanguageManager {
	
	private static final List<String> languageOptions = new ArrayList<String>();
	private static final List<String> languageShortcuts = new ArrayList<String>();
	
	private static final String slovak = "Slovenèina";
	private static final String slovak_s = "sk";
	
	private static final String english = "English";
	private static final String english_s = "en";
	
	private static ResourceBundle resource = null;
	
	/**
	 * Initializes the constants for <code>LanguageManager</code> which it will provide
	 * to any controller that needs them. Should be called before requesting anything
	 * else from this class.
	 */
	public static void initialize() {
		
		languageOptions.add(slovak);
		languageShortcuts.add(slovak_s);
		
		languageOptions.add(english);
		languageShortcuts.add(english_s);
	}
	/**
	 * Sets the current language. Afterwards, any text requested will be from
	 * the last set language file.
	 * @param language - the language to be set
	 */
	public static void setLanguage(String language) {

		if(languageOptions.contains(language)) {
			language = languageShortcuts.get(languageOptions.indexOf(language));
		}
		
		Locale locale = new Locale(language);
		resource = ResourceBundle.getBundle("language.language", locale);
		
	}
	/**
	 * Retrieves the requested text from the current language file. 
	 * @param propertyName - property name of the text to be retrieved
	 * @return the required text
	 */
	public static String getProperty(String propertyName) {
		if(resource == null) {
			setLanguage("default");
		}
	
		return resource.getString(propertyName);
	}
	/**
	 * Returns the language options in a way which they should be displayed
	 * to user to choose from.
	 * @return list of language options
	 */
	public static List<String> getLanguageOptions() {
		return languageOptions;
	}
}
