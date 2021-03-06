package everyos.browser.webicitybrowser.gui.ui;

import everyos.browser.webicitybrowser.gui.component.URLBar;
import everyos.engine.ribbon.core.component.Component;
import everyos.engine.ribbon.core.event.CharEvent;
import everyos.engine.ribbon.core.event.Key;
import everyos.engine.ribbon.core.event.KeyboardEvent;
import everyos.engine.ribbon.core.event.MouseEvent;
import everyos.engine.ribbon.core.event.UIEvent;
import everyos.engine.ribbon.core.rendering.Renderer;
import everyos.engine.ribbon.core.shape.Dimension;
import everyos.engine.ribbon.core.shape.SizePosGroup;
import everyos.engine.ribbon.core.ui.ComponentUI;
import everyos.engine.ribbon.core.ui.UIDirective;
import everyos.engine.ribbon.core.ui.UIManager;
import everyos.engine.ribbon.ui.simple.SimpleBlockComponentUI;
import everyos.engine.ribbon.ui.simple.appearence.Appearence;
import everyos.engine.ribbon.ui.simple.helper.StringWrapHelper;

public class URLBarUI extends SimpleBlockComponentUI {
	private Appearence appearence;

	public URLBarUI(Component c, ComponentUI parent) {
		super(c, parent);
		
		this.appearence = new URLBarAppearence();
	}
	
	@Override
	protected Appearence getAppearence() {
		return this.appearence;
	}
	
	private class URLBarAppearence implements Appearence {
		private String text = "";
		
		private boolean active = true;
		
		private long lastCursorOn = System.currentTimeMillis();

		private Dimension bounds;

		private int computedCursorOffset = 0;
		private int currentCursorPosition = 0;
		private Renderer lastRenderer;
		
		@Override
		public void render(Renderer r, SizePosGroup sizepos, UIManager mgrui) {
			this.text = getComponent().casted(URLBar.class).getText();
			
			int width = StringWrapHelper.stringWidth(r, text);
			sizepos.move(width+10, true);
			sizepos.setMinLineHeight(r.getFontHeight());
			
			this.bounds = sizepos.getSize();
		}

		@Override
		public void paint(Renderer r) {
			this.lastRenderer = r;
			
			r.useBackground();
			r.drawEllipse(0, 0, bounds.getHeight(), bounds.getHeight());
			r.drawEllipse(bounds.getWidth()-bounds.getHeight(), 0, bounds.getHeight(), bounds.getHeight());
			r.drawFilledRect(bounds.getHeight()/2, 0, bounds.getWidth()-bounds.getHeight(), bounds.getHeight());
			
			r.useForeground();
			r.drawText(bounds.getHeight(), bounds.getHeight()/2-r.getFontHeight()/2, text);
			
			paintAnimation(r, bounds);
			
			r.paintListener(e->processEvent(e));
		}
		
		private void paintAnimation(Renderer r, Dimension bounds) {
			if (active) {
				if (System.currentTimeMillis()-lastCursorOn>1000) {
					lastCursorOn = System.currentTimeMillis();
				}
				if (System.currentTimeMillis()-lastCursorOn<500) {
					r.drawLine(bounds.getHeight() + computedCursorOffset , bounds.getHeight()/4, 0, bounds.getHeight()/2);
				}
			}
		}

		@Override
		public void directive(UIDirective directive) {
			
		}

		@Override
		public void processEvent(UIEvent e) {
			if (e instanceof MouseEvent) {
				MouseEvent ev = (MouseEvent) e;
				if (ev.isExternal()) {
					return;
				}
				
				if (ev.getAction() == MouseEvent.PRESS && ev.getButton() == MouseEvent.LEFT_BUTTON) {
					int mouseX = ev.getRelativeX() - bounds.getHeight();
					
					if (mouseX < 0) {
						mouseX = 0;
					}
					
					int currentMouseOffset = 0;
					int i;
					for (i=0; i<text.length(); i++) {
						int charWidth = lastRenderer.charWidth(text.codePointAt(i)); //TODO: codePointAt?
						if (currentMouseOffset + charWidth*.5 > mouseX) {
							break;
						}
						currentMouseOffset += charWidth;
					}
					
					this.computedCursorOffset = currentMouseOffset;
					this.currentCursorPosition = i;
				}
			} else if (e instanceof CharEvent) {
				String insertion = ((CharEvent) e).getChar();
				this.text = text.substring(0, currentCursorPosition) + insertion + text.substring(currentCursorPosition);
				currentCursorPosition++;
				computedCursorOffset += lastRenderer.charWidth(insertion.codePointAt(0));
			} else if (e instanceof KeyboardEvent) {
				KeyboardEvent ev = (KeyboardEvent) e;
				if (ev.getKey() == Key.BACKSPACE && (ev.getAction() == KeyboardEvent.KEY_PRESS || ev.getAction() == KeyboardEvent.KEY_HOLD)) {
					if (currentCursorPosition == 0) {
						return;
					}
					computedCursorOffset -= lastRenderer.charWidth(text.codePointAt(currentCursorPosition-1));
					this.text = text.substring(0, currentCursorPosition-1) + text.substring(currentCursorPosition);
					currentCursorPosition--;	
				} else if (ev.getKey() == Key.ENTER && ev.getAction() == KeyboardEvent.KEY_RELEASE) {
					getComponent().casted(URLBar.class).getAction().accept(text);
				}
			}
		}
	}
}
