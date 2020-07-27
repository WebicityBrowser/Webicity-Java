package everyos.browser.webicity.renderer.html.dom.impl;

import everyos.browser.webicity.renderer.html.dom.Comment;

public class CommentImpl extends NodeImpl implements Comment {
	@SuppressWarnings("unused")
	private String data;

	public CommentImpl(DocumentImpl nodeDocument, String data) {
		super(nodeDocument);
		this.data = data;
	}
}
