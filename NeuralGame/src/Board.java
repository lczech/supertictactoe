import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Board {
	
	public enum FieldState {Player1, Player2, Neutral};
	
	public abstract void draw(Graphics g, Rectangle rect);
	protected FieldState state = FieldState.Neutral;
	
	public abstract void reset();
	
	public FieldState getState() {
		return state;
	}
	
}
