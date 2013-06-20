import java.awt.Graphics;
import java.awt.Rectangle;


public class SingleField extends Board {

	public SingleField() {
		reset();
	}
	
	@Override
	public void draw(Graphics g, Rectangle rect) {
		switch (state){
		case Player1:
			g.drawOval(rect.x, rect.y, rect.width, rect.height);
		case Player2:
			g.drawLine(rect.x, rect.y, rect.x+rect.width, rect.y+rect.height);
		default:
			break;
		}
	}

	@Override
	public void reset() {
		state=FieldState.Neutral;
	}

	@Override
	public void makeMove(Move m) {
		if (m.getPlayer()==Player.player.p1) {
			state=FieldState.Player1;
		} else {
			state=FieldState.Player2;
		}
	}
	
	//blabla

}
