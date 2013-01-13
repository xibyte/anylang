package mlang.dom;

public interface DOMProvider {

	DOM getDOM();
	
	void addListener(DOMListener listener);
	
	void removeListener(DOMListener listener);
	
}
