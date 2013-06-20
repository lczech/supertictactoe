import java.awt.Graphics;
import java.awt.Rectangle;

public class Field extends TTT {
	
	public boolean isOpen() {
		return this.State == TTT.Type.N;
	}

	@Override
	public void draw(Graphics g, Rectangle rect) {
		drawState(g, rect);
	}
	
}
