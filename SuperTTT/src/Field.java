import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Field implements IFieldState {
	
	private FieldState state;
	
	public Field() {
		this.state = FieldState.N;
	}

	@Override
	public FieldState getState() {
		return this.state;
	}

	@Override
	public void setState(FieldState s) {
		this.state = s;
		
	}
	
	public boolean isOpen() {
		return this.state == FieldState.N;
	}
	
	public void draw(Graphics g, Rectangle rect) {
		TTT.drawState(g, rect, this.state);
	}

	public List<Integer> getPossibleFields() {
		return new ArrayList<Integer>();
	}
	
}
