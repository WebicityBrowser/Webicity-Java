package everyos.browser.webicity.renderer.html.dom.impl;

import everyos.browser.webicity.renderer.html.dom.Text;

public class TextNodeImpl extends CharacterDataImpl implements Text {
	public TextNodeImpl(DocumentImpl nodeDocument, String text) {
		super(nodeDocument);
		this.wholeText = new StringBuilder(text);
	}
	public TextNodeImpl(DocumentImpl nodeDocument) {
		this(nodeDocument, "");
	}
	
	private StringBuilder wholeText;

	///
	
	@Override public Text splitText(long offset) {
		return null;
	}
	
	@Override public String getWholeText() {
		return null;
	}
	
	//Implementation specific
	@Override public void append(int codePoint) {
		wholeText.appendCodePoint(codePoint);
	}
	@Override public String getRawText() {
		return wholeText.toString();
	}
}
