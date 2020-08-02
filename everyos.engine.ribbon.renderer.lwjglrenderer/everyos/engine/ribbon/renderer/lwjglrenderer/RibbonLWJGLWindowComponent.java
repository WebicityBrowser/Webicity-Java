package everyos.engine.ribbonlwjgl;

import java.util.ArrayList;

import everyos.engine.ribbon.graphics.component.BufferedComponent;
import everyos.engine.ribbon.graphics.component.Component;
import everyos.engine.ribbon.graphics.ui.Dimension;
import everyos.engine.ribbon.graphics.ui.UIManager;
import everyos.engine.ribbon.graphics.ui.simple.SimpleUIManager;
import everyos.engine.ribbon.input.MouseBinding;
import everyos.engine.ribbon.shape.Location;
import everyos.engine.ribbonlwjgl.opengl.GLWindow;

public class RibbonLWJGLWindowComponent extends Component {
	public GLWindow window;
	@SuppressWarnings("unused")
	private Runnable closeEvent;
	@SuppressWarnings("unused")
	private ArrayList<MouseBinding> bindings = new ArrayList<MouseBinding>();

	public RibbonLWJGLWindowComponent(int monitor) {
		super(null);
		this.parent = null;
		this.window = new GLWindow(monitor);

		//Render code
		/*Renderer r = new RibbonAWTRenderer(g); //TODO
		calcSize(r, null, new DrawData());
		DrawData data = new DrawData();
		draw(r, data);
		bindings = data.bindings;*/
		
		//Close code
		//if (closeEvent!=null) closeEvent.run();
		
		//Mouse release code
		/*for (int i = bindings.size()-1; i>=0; i--) {
			if (bindings.get(i).aabb(e.getX(), e.getY())) {
				ClickListener l = (ClickListener) bindings.get(i).component.getAttribute("onrelease");
				if (l==null) return; //TODO: Move to component code
				l.accept(new ClickEvent(e.getX(), e.getY(), e.getButton()));
				break;
			}
		}*/
		
		//this.window.setLocation(bounds.x+100, bounds.y+100);
		//this.window.setMinimumSize(new Dimension(400, 400));
	}
	
	@Override public UIManager getUIManager() {
		return this.uimanager!=null?this.uimanager:(this.uimanager=SimpleUIManager.createUI());
	}
	
	@Override public Component attribute(String name, Object attr) {
		super.attribute(name, attr);
		
		if (name=="decorated") window.setUndecorated(!((boolean) attr)); //TODO: Do I move this to SimpleWindowComponentUI?
		if (name=="size") window.setSize((Dimension) attr); //TODO: Use Location instead
		if (name=="visible") {
			window.setVisible((boolean) attr);
		}
		if (name=="onclose") closeEvent = (Runnable) attr;
		if (name=="title") window.setTitle((String) attr);
		if (name=="icon-resource") {
			/*try {
				window.setIconImage(ImageIO.read(ClassLoader.getSystemClassLoader().getResource((String) attr)));
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		//if (name=="icon") window.setIconImage((Image) attr);
		if (name=="position") {
			//window.setLocation(bounds.x+(int) position.getX(), bounds.y+(int) position.getY());
		}
		
		return this;
	}
	
	public BufferedComponent createDefaultComponent() {
		return (BufferedComponent) new BufferedComponent(this)
			.attribute("position", new Location(0, 0, 0, 0))
			.attribute("size", new Location(1, 0, 1, 0));
	}
	
	public void close() {
		window.dispose();
	}
	
	@Override public void invalidate() {
		
	}
}
