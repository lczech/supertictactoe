package core;

import java.util.ArrayList;
import java.util.List;

public class SubBoard implements ISeeded {
	
	private Seed state;
	
	public Field[] fields;
	
	public SubBoard() {
		this.state = Seed.N;
		
		this.fields = new Field[9];
		for (int i=0; i<9; i++) {
			this.fields[i] = new Field();
		}
	}
	
	@Override
	public Seed getState() {
		return this.state;
	}

	@Override
	public Seed setState(Seed s) {
		Seed old = this.state;
		this.state = s;
		return old;
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
	
	public boolean makeMove(int field, Seed player) {
		if (this.fields[field].getState() == Seed.N) {
			this.fields[field].setState(player);
			
			if(this.state == Seed.N && TTT.isWon(fields, field, player)) {
				System.out.println("WON WON WON");
				this.setState(player);
			}
			
			return true;
		} else {
			return false;
		}
	}

}
