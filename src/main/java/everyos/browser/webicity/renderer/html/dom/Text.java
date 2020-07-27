package everyos.browser.webicity.renderer.html.dom;

public interface Text extends CharacterData {
	//constructor(optional DOMString data = "");

	Text splitText(long offset);
	String getWholeText();
	
	//Implementation specific
	void append(int codePoint);
	String getRawText();
}
