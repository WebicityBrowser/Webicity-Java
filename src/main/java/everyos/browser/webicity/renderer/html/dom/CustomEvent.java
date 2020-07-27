package everyos.browser.webicity.renderer.html.dom;

public interface CustomEvent extends Event {
	//constructor(String type, optional CustomEventInit eventInitDict = {});

	Object getDetail();

	void initCustomEvent(String type, boolean bubbles, boolean cancelable, Object detail); // historical
}
