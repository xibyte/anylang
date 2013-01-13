package anylang._exp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mlang.Logger;

public class LanguagesRegistry {

	private final List<LanguageProvider> lproviders = new ArrayList<LanguageProvider>();
	private final Map<String, Language2> languages = new HashMap<String, Language2>();
	
	
	public void register(LanguageProvider languageProvider) {
		lproviders.add(languageProvider);
	}
	
	public Language2 get(String id) {
		Language2 lang = languages.get(id);
		if (lang == null) {
			lang = load(id);
			if (lang  != null) {
				languages.put(id, lang);
			}
		}
		return lang;
	}

	private Language2 load(String id) {
		for (LanguageProvider lp : lproviders) {
			if (lp.has(id)) {
				Language2 lang = lp.make(id);
				if (lang != null) {
					return lang;
				}
			}
		}
		Logger.warn("Unable to find language: " + id);
		return null;
	}
	
}
