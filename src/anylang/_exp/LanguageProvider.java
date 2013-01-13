package anylang._exp;


public interface LanguageProvider {

	boolean has(String id);

	Language2 make(String id);
	
}
