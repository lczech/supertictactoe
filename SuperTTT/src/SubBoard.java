import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class SubBoard extends TTT {
	
	private Field[] fields;
	
	public SubBoard() {
		this.fields = new Field[9];
		for (int i=0; i<9; i++) {
			this.fields[i] = new Field();
		}
	}
	
	public boolean isOpen() {
		for (int i=0; i<9; i++) {
			if(this.fields[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public void addMoves(List<Move> target, int supermove) {
		for (int i=0; i<9; i++) {
			if(this.fields[i].isOpen()) {
				target.add(new Move(supermove, i));
			}
		}
	}
	
	public void makeMove(int field, TTT.Type player) {
		this.fields[field].State = player;
		System.out.println(field);
		
		if(isWon(fields, field, player)) {
			System.out.println("WON WON WON");
		}
	}

	@Override
	public void draw(Graphics g, Rectangle rect) {
		drawBoard(g, rect, fields);
		drawState(g, rect);
	}

}
