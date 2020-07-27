package everyos.browser.webicity.renderer.html.dom;

public interface CharacterData extends Node {
	String getData();
	void setData(String data);
	long getLength();
	String substringData(long offset, long count);
	void appendData(String data);
	void insertData(long offset, String data);
	void deleteData(long offset, long count);
	void replaceData(long offset, long count, String data);
}
