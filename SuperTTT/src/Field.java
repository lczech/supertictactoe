import java.awt.Graphics;
import java.awt.Rectangle;

public class Field {

	public static enum FieldState {Neutral, Player1, Player2};
	
	public FieldState State;
	
	public Field() {
		this.State = FieldState.Neutral;
	}
	
	public boolean isOpen() {
		return this.State == FieldState.Neutral;
	}
	
	public void draw(Graphics g, Rectangle rect) {
		rect.x      += 0.15 * rect.width;
		rect.y      += 0.15 * rect.height;
		rect.width  *= 0.7;
		rect.height *= 0.7;
		
		if (this.State == FieldState.Player1) {
			g.drawLine(rect.x, rect.y, rect.x+rect.width, rect.y+rect.height);
			g.drawLine(rect.x+rect.width, rect.y, rect.x, rect.y+rect.height);
		} else if (this.State == FieldState.Player2) {
			g.drawOval(rect.x, rect.y, rect.width, rect.height);
		}
				
	}

}
