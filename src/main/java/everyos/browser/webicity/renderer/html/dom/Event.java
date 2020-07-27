package everyos.browser.webicity.renderer.html.dom;

public interface Event {
	//constructor(String type, optional EventInit eventInitDict = {});

	String getType();
	EventTarget getTarget();
	EventTarget getSrcElement(); // historical
	EventTarget getCurrentTarget();
	EventTarget[] composedPath();

	short getEventPhase();

	void stopPropagation();
	boolean getCancelBubble(); // historical alias of .stopPropagation
	void setCancelBubble(boolean cancelBubble);
	void stopImmediatePropagation();

	boolean getBubbles();
	boolean getCancelable();
	boolean getReturnValue();// historical
	void setReturnValue(boolean returnValue);
	void preventDefault();
	boolean getDefaultPrevented();
	boolean getComposed();

	boolean getIsTrusted();
	DOMHighResTimeStamp getTimeStamp();

	void initEvent(String type, boolean bubbles, boolean cancelable); // historical
}
