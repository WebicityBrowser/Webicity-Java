package everyos.browser.webicity.renderer.html.dom;

import java.util.EventListener;

public interface EventTarget {
	//constructor();
	
	void addEventListener(String type, EventListener callback, AddEventListenerOptions options);
	void addEventListener(String type, EventListener callback, boolean options);
	void removeEventListener(String type, EventListener callback, EventListenerOptions options);
	void removeEventListener(String type, EventListener callback,boolean options);
	boolean dispatchEvent(Event event);
}
