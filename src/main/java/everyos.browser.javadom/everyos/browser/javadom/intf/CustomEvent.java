package everyos.browser.javadom.intf;

public interface CustomEvent extends Event {
	public Object getDetail();
	void initCustomEvent(String type, boolean bubbles, boolean cancelable, Object detail);
}
