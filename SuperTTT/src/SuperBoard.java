import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SuperBoard implements IFieldState {
	
	private FieldState state;
	
	public SubBoard[] boards;
	private List<Move> history;

	public SuperBoard() {
		this.state = FieldState.N;
		
		this.boards = new SubBoard[9];
		for (int i=0; i<9; i++) {
			this.boards[i] = new SubBoard();
		}
		
		this.history = new ArrayList<Move>();
	}
	
	@Override
	public FieldState getState() {
		return this.state;
	}

	@Override
	public void setState(FieldState s) {
		this.state = s;
		
	}
	
	public Move getLastMove() {
		return history.size()>0? history.get(history.size()-1) : null;
	}
	
	public boolean isOpen() {
		for (int i=0; i<9; i++) {
			if(boards[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Move> getPossibleMoves() {
		List<Move> list = new ArrayList<Move>();
		
		if(this.history.size() == 0) {
			for (int i=0; i<9; i++) {
				boards[i].addMoves(list, i);
			}
		} else {
			int s = this.history.get(this.history.size()-1).SubMove;
			if (boards[s].isOpen()) {
				boards[s].addMoves(list, s);
			} else {
				for (int i=0; i<9; i++) {
					boards[i].addMoves(list, i);
				}
			}
		}
		
		return list;
	}
	
	public List<Integer> getPossibleFields() {
		List<Integer> l = new ArrayList<Integer>();
		for (Move m: getPossibleMoves()) {
			if(!l.contains(m.SuperMove)) {
				l.add(m.SuperMove);
			}
		}
		return l;
	}
	
	public boolean makeMove(Move move, FieldState player) {
		Move lastmove = getLastMove();
		boolean result = ((lastmove == null || lastmove.SubMove==move.SuperMove) && this.boards[move.SuperMove].makeMove(move.SubMove, player));
		if (result) history.add(move);
		return result;
	}

	public void draw(Graphics g, Rectangle rect) {
		TTT.drawBoard(g, rect, false);
		
		Rectangle[] subrects = TTT.getSubrects(rect);
		for (int i=0;i<9;i++) {
			boards[i].draw(g, subrects[i], this.getPossibleFields().contains(i));
		}
		
		TTT.drawState(g, rect, this.getState());
	}
	
}
