package everyos.browser.webicity.webribbon.ui.webui;

import everyos.browser.webicity.webribbon.core.component.WebComponent;
import everyos.browser.webicity.webribbon.core.ui.WebComponentUI;
import everyos.browser.webicity.webribbon.gui.shape.SizePosGroup;
import everyos.browser.webicity.webribbon.ui.webui.layout.InlineBlockLayout;
import everyos.browser.webicity.webribbon.ui.webui.layout.Layout;
import everyos.engine.ribbon.core.graphics.InvalidationLevel;
import everyos.engine.ribbon.core.shape.Dimension;

public class WebUIWebWindowUI extends WebUIWebComponentUI {
	private WindowLayout layout;
	private Dimension windowSize;

	public WebUIWebWindowUI(WebComponent component, WebComponentUI parent) {
		super(component, parent);
		
		this.windowSize = new Dimension(0, 0);
		this.layout = new WindowLayout(component, this);
	}
	
	public void setWindowSize(Dimension windowSize) {
		this.windowSize = windowSize;
		invalidate(InvalidationLevel.RENDER);
	}
	
	@Override
	protected Layout getLayout() {
		return this.layout;
	}
	
	private class WindowLayout extends InlineBlockLayout {

		public WindowLayout(WebComponent component, WebComponentUI ui) {
			super(component, ui);
		}
		
		@Override
		public Dimension getMaxBlockSize(SizePosGroup sizepos) {
			return windowSize;
		}
	}
}
