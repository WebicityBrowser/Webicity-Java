package everyos.browser.javadom.imp;

import everyos.browser.javadom.intf.CharacterData;
import everyos.browser.javadom.intf.Document;

public class JDCharacterData extends JDNode implements CharacterData {
	private StringBuilder data;

	protected JDCharacterData(Document nodeDocument) {
		super(nodeDocument);
		this.data = new StringBuilder();
	}

	@Override
	public void appendData(String data) {
		this.data.append(data);
	}

	@Override
	public void appendData(char data) {
		this.data.appendCodePoint(data);
	};
	
	@Override
	public String getData() {
		return this.data.toString();
	}
}
