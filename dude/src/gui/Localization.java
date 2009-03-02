package gui;

import main.Strings;
import java.util.HashMap;
/**
 * Localizationsupport for GUI
 * To use this do like this
 * Localization local = new Localization();
 * local.setLanguage(Strings.Swedish);
 * System.out.println(local.get(Strings.exitPanel));
 * @author josjoh
 *
 */
public class Localization {
	public final String LocalizationDefault = Strings.English;
	public final HashMap<String,HashMap<String,String>> Localization;
	private HashMap<String,String> currentLanguage;
	
	/**
	 * Initializes the languages
	 */
	public Localization(){
		Localization=new HashMap<String,HashMap<String,String>>();
		final HashMap<String,String> LocalizationEnglish = new HashMap<String,String>();
		final HashMap<String,String> LocalizationSwedish = new HashMap<String,String>();
		final HashMap<String,String> LocalizationMust	= new HashMap<String,String>();
		LocalizationEnglish.put(Strings.InGameMenuFunctions[0],"Start Game");
		LocalizationEnglish.put(Strings.InGameMenuFunctions[1],"Settings");
		LocalizationEnglish.put(Strings.InGameMenuFunctions[2],"Load Game");
		LocalizationEnglish.put(Strings.InGameMenuFunctions[3],"Save Game");
		LocalizationEnglish.put(Strings.InGameMenuFunctions[4],"Exit");
		LocalizationEnglish.put(Strings.GivePlayerName,			"Enter a playername");
		LocalizationEnglish.put(Strings.PlayerName,				"Playername ");		
		LocalizationEnglish.put(Strings.ChooseOne,				"Choose one");
		LocalizationEnglish.put(Strings.Input,					"Input");		
		LocalizationEnglish.put(Strings.ButtonLoad,				"Load");		
		LocalizationEnglish.put(Strings.ButtonSave,				"Save");
		LocalizationEnglish.put(Strings.exitTitle,				"Wanna quit, sir?");		
		LocalizationEnglish.put(Strings.exitQuestion,			"Want to quit?");
		LocalizationEnglish.put(Strings.ChooseAlternative,      "Choose an alternative");
		LocalizationEnglish.put(Strings.GraphicMode, 			"GraphicMode");
		LocalizationEnglish.put(Strings.FullScreen , 			"Fullscreen");
		LocalizationEnglish.put(Strings.Window	, 				"Window");
		LocalizationEnglish.put(Strings.GameTitle	, 				"The Game");
				
		LocalizationSwedish.put(Strings.InGameMenuFunctions[0],"Stara spelet");
		LocalizationSwedish.put(Strings.InGameMenuFunctions[1],"Inställningar");
		LocalizationSwedish.put(Strings.InGameMenuFunctions[2],"Ladda");
		LocalizationSwedish.put(Strings.InGameMenuFunctions[3],"Spara");
		LocalizationSwedish.put(Strings.InGameMenuFunctions[4],"Avsluta");
		LocalizationSwedish.put(Strings.GivePlayerName,			"Ange ett spelarnamn");
		LocalizationSwedish.put(Strings.PlayerName,				"Spelarnamn ");		
		LocalizationSwedish.put(Strings.ChooseOne,				"Välj ett alternativ ");
		LocalizationSwedish.put(Strings.Input,					"Mata in ");		
		LocalizationSwedish.put(Strings.ButtonLoad,				"Ladda");		
		LocalizationSwedish.put(Strings.ButtonSave,				"Spara");
		LocalizationSwedish.put(Strings.exitTitle,				"Musta brustet?");		
		LocalizationSwedish.put(Strings.exitQuestion,			"Vill du avsluta?");
		LocalizationSwedish.put(Strings.ChooseAlternative,      "Choose an alternative");
		LocalizationSwedish.put(Strings.GraphicMode, 			"GraphicMode");
		LocalizationSwedish.put(Strings.FullScreen , 			"Fullscreen");
		LocalizationSwedish.put(Strings.Window	, 				"Window");
		LocalizationSwedish.put(Strings.GameTitle	, 				"The Game");
		
		LocalizationMust.put(Strings.InGameMenuFunctions[0],"Stara spelet");
		LocalizationMust.put(Strings.InGameMenuFunctions[1],"Inställningar");
		LocalizationMust.put(Strings.InGameMenuFunctions[2],"Ladda");
		LocalizationMust.put(Strings.InGameMenuFunctions[3],"Spara");
		LocalizationMust.put(Strings.InGameMenuFunctions[4],"Avsluta");
		LocalizationMust.put(Strings.GivePlayerName,			"Ange ett spelarnamn");
		LocalizationMust.put(Strings.PlayerName,				"Spelarnamn ");		
		LocalizationMust.put(Strings.ChooseOne,				"Välj ett alternativ ");
		LocalizationMust.put(Strings.Input,					"Mata in ");		
		LocalizationMust.put(Strings.ButtonLoad,				"Ladda");		
		LocalizationMust.put(Strings.ButtonSave,				"Spara");
		LocalizationMust.put(Strings.exitTitle,				"Musta brustet?");		
		LocalizationMust.put(Strings.exitQuestion,			"Vill du avsluta?");
		LocalizationMust.put(Strings.ChooseAlternative,      "Choose an alternative");
		LocalizationMust.put(Strings.GraphicMode, 			"GraphicMode");
		LocalizationMust.put(Strings.FullScreen , 			"Fullscreen");
		LocalizationMust.put(Strings.Window	, 				"Window");
		LocalizationMust.put(Strings.GameTitle	, 				"The Game");
		
		Localization.put(Strings.English, LocalizationEnglish);
		Localization.put(Strings.Swedish, LocalizationSwedish);
		Localization.put(Strings.Must, LocalizationMust);
		setLanguage(LocalizationDefault);
	}
	/**
	 * Another constructor if you want to set language directly
	 * @param defaultLanguage
	 */
	public Localization(String defaultLanguage)
	{
		this();
		setLanguage(defaultLanguage);
	}
	/**
	 * Returns a localized word
	 * @param word
	 * @return localized word
	 */
	public String get(String word)
	{
		String string = currentLanguage.get(word);
		if ( string == null ) string = "unset";
		return string;
	}
	/**
	 * returns all the available languages
	 * @return String[] of languages
	 */
	public String[] availableLanguages()
	{
		String[] string = new String[Localization.size()];
		int i = 0;
		for(String s : Localization.keySet())
			string[i++] = s;
		return string;
	}
	/**
	 * sets the current language
	 * @param language
	 */
	public void setLanguage(String language)
	{
		if(Localization.containsKey(language))
			currentLanguage = Localization.get(language);
	}
}
