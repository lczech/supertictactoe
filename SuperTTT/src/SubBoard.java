import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SubBoard implements IFieldState {
	
	private FieldState state;
	
	private Field[] fields;
	
	public SubBoard() {
		this.state = FieldState.N;
		
		this.fields = new Field[9];
		for (int i=0; i<9; i++) {
			this.fields[i] = new Field();
		}
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
		for (int i=0; i<9; i++) {
			if(this.fields[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
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
	
	public boolean makeMove(int field, IFieldState.FieldState player) {
		if (this.fields[field].getState()==FieldState.N) {
			this.fields[field].setState(player);
			System.out.println(field);
			
			if(TTT.isWon(fields, field, player)) {
				System.out.println("WON WON WON");
				this.setState(player);
			}
			
			return true;
		} else {
			return false;
		}
	}

	public void draw(Graphics g, Rectangle rect, boolean active) {
		TTT.drawBoard(g, rect, active);
		
		Rectangle[] subrects = TTT.getSubrects(rect);
		for (int i=0;i<9;i++) {
			fields[i].draw(g, subrects[i]);
			// , this.getPossibleFields().contains(i)
		}
		
		TTT.drawState(g, rect, this.state);
	}

}
