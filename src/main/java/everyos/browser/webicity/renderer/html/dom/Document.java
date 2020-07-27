package everyos.browser.webicity.renderer.html.dom;

import everyos.browser.webicity.renderer.html.dom.impl.MutationListener;

public interface Document {
	//constructor();

	DOMImplementation getImplementation();
	String getURL();
	String getDocumentURI();
	String getCompatMode();
	String getCharacterSet();
	String getCharset(); // historical alias of .characterSet
	String getInputEncoding(); // historical alias of .characterSet
	String getContentType();
	
	DocumentType getDoctype();
	Element getDocumentElement();
	HTMLCollection getElementsByTagName(String qualifiedName);
	HTMLCollection getElementsByTagNameNS(String namespace, String localName);
	HTMLCollection getElementsByClassName(String classNames);
	
	//Element createElement(String localName, optional (String or ElementCreationOptions) options = {});
	//Element createElementNS(String? namespace, String qualifiedName, optional (String or ElementCreationOptions) options = {});
	DocumentFragment createDocumentFragment();
	Text createTextNode(String data);
	CDATASection createCDATASection(String data);
	Comment createComment(String data);
	ProcessingInstruction createProcessingInstruction(String target, String data);
	
	Node importNode(Node node, boolean deep);
	Node adoptNode(Node node);
	
	Attr createAttribute(String localName);
	Attr createAttributeNS(String namespace, String qualifiedName);
	
	Event createEvent(String interfac); // historical
	
	Range createRange();
	
	//NodeFilter.SHOW_ALL = 0xFFFFFFFF
	NodeIterator createNodeIterator(Node root, long whatToShow, NodeFilter filter);
	TreeWalker createTreeWalker(Node root, long whatToShow, NodeFilter filter);
	
	//Implementation specific
	void bind(MutationListener listener);
	void unbind(MutationListener listener);
}
