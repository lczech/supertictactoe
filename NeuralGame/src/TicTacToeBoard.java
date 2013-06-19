import java.awt.Graphics;
import java.awt.Rectangle;


public class TicTacToeBoard extends Board {

	Board[] fields;
	
	public TicTacToeBoard(Board[] fields) {
		if (fields.length != 9) {
			throw new IllegalArgumentException("9 fields required for TicTacToeBoard.");
		}
		this.fields=fields;
		
	}
	
	@Override
	public void draw(Graphics g, Rectangle rect) {
		// TODO Auto-generated method stub
	}

	@Override
	public void reset() {
		state=FieldState.Neutral;
		for (Board b: fields) {
			b.reset();
		}
	}

}
