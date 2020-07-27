package everyos.browser.webicity.renderer.html.dom.impl;

import java.util.ArrayList;

import everyos.browser.webicity.renderer.html.dom.Attr;
import everyos.browser.webicity.renderer.html.dom.CDATASection;
import everyos.browser.webicity.renderer.html.dom.Comment;
import everyos.browser.webicity.renderer.html.dom.DOMImplementation;
import everyos.browser.webicity.renderer.html.dom.Document;
import everyos.browser.webicity.renderer.html.dom.DocumentFragment;
import everyos.browser.webicity.renderer.html.dom.DocumentType;
import everyos.browser.webicity.renderer.html.dom.Element;
import everyos.browser.webicity.renderer.html.dom.Event;
import everyos.browser.webicity.renderer.html.dom.HTMLCollection;
import everyos.browser.webicity.renderer.html.dom.Node;
import everyos.browser.webicity.renderer.html.dom.NodeFilter;
import everyos.browser.webicity.renderer.html.dom.NodeIterator;
import everyos.browser.webicity.renderer.html.dom.ProcessingInstruction;
import everyos.browser.webicity.renderer.html.dom.Range;
import everyos.browser.webicity.renderer.html.dom.Text;
import everyos.browser.webicity.renderer.html.dom.TreeWalker;

public class DocumentImpl extends NodeImpl implements Document {
	//Sidenote: https://github.com/whatwg/html/pull/3917 is a thing
	public static enum DocumentReadyState {LOADING, INTERACTIVE, COMPLETE}
	
	private ArrayList<MutationListener> mutationListeners = new ArrayList<>(); 
	
	public DocumentImpl() {
		super(null);
	}
	
	//

	@Override public DOMImplementation getImplementation() {
		return null;
	}

	@Override public String getURL() {
		return null;
	}

	@Override public String getDocumentURI() {
		return null;
	}

	@Override public String getCompatMode() {
		return null;
	}

	@Override public String getCharacterSet() {
		return null;
	}

	@Override public String getCharset() {
		return null;
	}

	@Override public String getInputEncoding() {
		return null;
	}

	@Override public String getContentType() {
		return null;
	}

	@Override public DocumentType getDoctype() {
		return null;
	}

	@Override public Element getDocumentElement() {
		return null;
	}

	@Override public HTMLCollection getElementsByTagName(String qualifiedName) {
		return null;
	}

	@Override public HTMLCollection getElementsByTagNameNS(String namespace, String localName) {
		return null;
	}

	@Override public HTMLCollection getElementsByClassName(String classNames) {
		return null;
	}

	@Override public DocumentFragment createDocumentFragment() {
		return null;
	}

	@Override public Text createTextNode(String data) {
		return null;
	}

	@Override public CDATASection createCDATASection(String data) {
		return null;
	}

	@Override public Comment createComment(String data) {
		return null;
	}

	@Override public ProcessingInstruction createProcessingInstruction(String target, String data) {
		return null;
	}

	@Override public Node importNode(Node node, boolean deep) {
		return null;
	}

	@Override public Node adoptNode(Node node) {
		return null;
	}

	@Override public Attr createAttribute(String localName) {
		return null;
	}

	@Override public Attr createAttributeNS(String namespace, String qualifiedName) {
		return null;
	}

	@Override public Event createEvent(String interfac) {
		return null;
	}

	@Override public Range createRange() {
		return null;
	}

	@Override public NodeIterator createNodeIterator(Node root, long whatToShow, NodeFilter filter) {
		return null;
	}

	@Override public TreeWalker createTreeWalker(Node root, long whatToShow, NodeFilter filter) {
		return null;
	}
	
	//Implementation Specific
	@Override public void bind(MutationListener listener) {
		mutationListeners.add(listener);
		listener.onBind(this);
	}
	@Override public void unbind(MutationListener listener) {
		mutationListeners.remove(listener);
	}
}
