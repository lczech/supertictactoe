import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SubBoard extends TTT {
	
	public Field[] fields;
	
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
	
	@Override
	public List<Integer> getPossibleFields() {
		List<Integer> l = new ArrayList<Integer>();
		for (int i=0; i<9; i++) {
			if(this.fields[i].isOpen()) {
				l.add(i);
			}
		}
		return l;
	}
	
	public void addMoves(List<Move> target, int supermove) {
		for (int i=0; i<9; i++) {
			if(this.fields[i].isOpen()) {
				target.add(new Move(supermove, i));
			}
		}
	}
	
	public boolean makeMove(int field, TTT.Type player) {
		if (this.fields[field].State==TTT.Type.N) {
			this.fields[field].State = player;
			System.out.println(field);
			
			if(isWon(fields, field, player)) {
				System.out.println("WON WON WON");
				this.State = player;
			}
			
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g, Rectangle rect, boolean active) {
		drawBoard(g, rect, fields, active);
		drawState(g, rect);
	}

}
