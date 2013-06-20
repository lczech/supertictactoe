import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Field extends TTT {
	
	public boolean isOpen() {
		return this.State == TTT.Type.N;
	}

	@Override
	public void draw(Graphics g, Rectangle rect, boolean active) {
		drawState(g, rect);
	}

	@Override
	public List<Integer> getPossibleFields() {
		return new ArrayList<Integer>();
	}
	
}
