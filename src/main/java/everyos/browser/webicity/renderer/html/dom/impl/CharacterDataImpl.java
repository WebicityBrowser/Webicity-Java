package everyos.browser.webicity.renderer.html.dom.impl;

import everyos.browser.webicity.renderer.html.dom.CharacterData;

public class CharacterDataImpl extends NodeImpl implements CharacterData {
	public CharacterDataImpl(DocumentImpl nodeDocument) {
		super(nodeDocument);
	}

	@Override public String getData() {
		return null;
	}

	@Override public void setData(String data) {
		
	}

	@Override public long getLength() {
		return 0;
	}

	@Override public String substringData(long offset, long count) {
		return null;
	}

	@Override public void appendData(String data) {
		
	}

	@Override public void insertData(long offset, String data) {
		
	}

	@Override public void deleteData(long offset, long count) {
		
	}

	@Override public void replaceData(long offset, long count, String data) {
		
	}
}
