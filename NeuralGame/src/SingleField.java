import java.awt.Graphics;
import java.awt.Rectangle;


public class SingleField extends Board {

	public SingleField() {
		reset();
	}
	
	@Override
	public void draw(Graphics g, Rectangle rect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		state=FieldState.Neutral;
	}

}
