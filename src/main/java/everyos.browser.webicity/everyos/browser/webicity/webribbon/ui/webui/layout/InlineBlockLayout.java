package everyos.browser.webicity.webribbon.ui.webui.layout;

import everyos.browser.webicity.webribbon.core.component.WebComponent;
import everyos.browser.webicity.webribbon.core.ui.WebComponentUI;
import everyos.browser.webicity.webribbon.gui.UIBox;
import everyos.browser.webicity.webribbon.gui.UIContext;
import everyos.browser.webicity.webribbon.gui.shape.Position;
import everyos.browser.webicity.webribbon.gui.shape.SizePosGroup;
import everyos.browser.webicity.webribbon.ui.webui.appearence.Appearence;
import everyos.browser.webicity.webribbon.ui.webui.helper.ComputedChildrenHelper;
import everyos.browser.webicity.webribbon.ui.webui.psuedo.ScrollBar;
import everyos.engine.ribbon.core.event.UIEvent;
import everyos.engine.ribbon.core.graphics.GUIState;
import everyos.engine.ribbon.core.rendering.Renderer;
import everyos.engine.ribbon.core.shape.Dimension;
import everyos.engine.ribbon.core.shape.Rectangle;
import everyos.engine.ribbon.ui.simple.helper.RectangleBuilder;	

public class InlineBlockLayout implements Layout {
	private WebComponent component;
	private WebComponentUI ui;
	private Dimension outerSize;
	private Dimension pageSize;
	//TODO: Do I need to store the position?
	
	private ComputedChildrenHelper computedChildrenHelper;
	private Position position;
	private ScrollBar scrollBar;
	
	public InlineBlockLayout(WebComponent component, WebComponentUI ui) {
		this.component = component;
		this.ui = ui;
		this.scrollBar = new ScrollBar();
	}
	
	@Override
	public void render(Renderer r, SizePosGroup sizepos, UIContext context, Appearence appearence) {
		this.position = sizepos.getCurrentPointer();
		
		Dimension maxBlockSize = getMaxBlockSize(sizepos);
	
		SizePosGroup temporaryPageBounds = new SizePosGroup(
			sizepos.getSize().getWidth(), 0,
			0, 0,
			-1, -1);
		
		renderInnerPart(r, temporaryPageBounds, context, appearence);
		
		this.outerSize = temporaryPageBounds.getSize();
		
		//TODO: Make SizePosGroup#compareTo a thing
		if (maxBlockSize.getHeight()<temporaryPageBounds.getSize().getHeight() && maxBlockSize.getHeight()!=-1) {
			if (maxBlockSize.getWidth()!=-1) {
				temporaryPageBounds = new SizePosGroup(
					sizepos.getSize().getWidth()-10, 0,
					0, 0,
					-1, -1);
				
				renderInnerPart(r, temporaryPageBounds, context, appearence);
				
				this.outerSize = new Dimension(maxBlockSize.getWidth()-10, maxBlockSize.getHeight());
			}
		}
		
		this.pageSize = temporaryPageBounds.getSize();
		
		sizepos.setMinLineHeight(temporaryPageBounds.getSize().getHeight());
		sizepos.move(temporaryPageBounds.getSize().getWidth(), true);
		
		scrollBar.render(position, outerSize, pageSize);
	}

	@Override
	public void paint(Renderer r, Rectangle viewport, Appearence appearence) {
		
		//if (this.pageSize!=null) {
			scrollBar.paint(r, new Rectangle(position.getX(), position.getY(), outerSize.getWidth(), outerSize.getHeight()));
		//}
		
		r.paintMouseListener(component, position.getX(), position.getY(), outerSize.getWidth()+10, outerSize.getHeight(), e->{
			if (e.isExternal()) return;
			processEvent(e);
		});
		
		GUIState state = r.getState();
		r.restoreState(state.clone());
		Renderer childR = r.getSubcontext(position.getX(), position.getY(), outerSize.getWidth(), outerSize.getHeight());
		
		paintInnerPart(childR, viewport, appearence);
		
		r.restoreState(state);
	}
	
	@Override
	public void processEvent(UIEvent event) {
		if (this.pageSize!=null) {
			scrollBar.processEvent(event);
		}
	}
	
	protected Dimension getMaxBlockSize(SizePosGroup sizepos) {
		return new Dimension(-1, -1);
	}

	private void renderInnerPart(Renderer r, SizePosGroup sizepos, UIContext context, Appearence appearence) {
		appearence.render(r, sizepos, context);
		renderChildren(r, sizepos, context);
	}
	
	private void renderChildren(Renderer r, SizePosGroup sizepos, UIContext context) {
		this.computedChildrenHelper = new ComputedChildrenHelper(this.component, c->context.getManager().get(c, ui));
		
		for (WebComponentUI c: computedChildrenHelper.getChildren()) {
			c.render(r, sizepos, context);
		}
	}
	
	private void paintInnerPart(Renderer r, Rectangle viewport, Appearence appearence) {
		appearence.paint(r, viewport);
		paintChildren(r, viewport);
	}
	
	private void paintChildren(Renderer r, Rectangle viewport) {
		int curScrollY = scrollBar.getCurrentScrollY();
		
		r.setScrollY(curScrollY);
		
		//AABB based culling
		RectangleBuilder vpBuilder = new RectangleBuilder( //The viewport is the area in which subcomponents *could* be drawn
			0, curScrollY, // Child components actually use the parent's location as the origin
			outerSize.getWidth(), outerSize.getHeight());
		
		// The new viewport should fit within the old viewport			
		if (position.getX()+vpBuilder.getWidth()>viewport.getWidth()) {
			vpBuilder.setWidth(viewport.getWidth() - position.getX());
		}
		//TODO: Cut things outside top of outerSize
		if ((position.getY()-viewport.getY())+vpBuilder.getHeight()>viewport.getHeight()) {
			// TODO: This logic can be confusing, so I should make it easier to understand.
			vpBuilder.setHeight(viewport.getHeight() - (position.getY()-viewport.getY()));
		}
		
		Rectangle vp = vpBuilder.build();
		for (WebComponentUI c: computedChildrenHelper.getChildren()) {
			r.useBackground();
			if (c.getUIBox().intersectsWith(vp)) {
				c.paint(r, vp);
			}
		}
		//TODO: Sort by Z-index
	}

	@Override
	public UIBox getComputedUIBox() {
		return viewport->viewport.intersects(new Rectangle(position.getX(), position.getY(), outerSize.getWidth(), outerSize.getHeight()));
	}
}
