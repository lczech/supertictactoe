import java.util.ArrayList;
import java.util.List;

public class SuperBoard implements IFieldState {
	
	private Game game;
	
	private FieldState state;
	
	public SubBoard[] boards;

	public SuperBoard(Game g) {
		this.game = g;
		this.state = FieldState.N;
		
		this.boards = new SubBoard[9];
		for (int i=0; i<9; i++) {
			this.boards[i] = new SubBoard();
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
			if(boards[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Move> getPossibleMoves() {
		List<Move> list = new ArrayList<Move>();
		
		if(this.game.history.size() == 0) {
			for (int i=0; i<9; i++) {
				boards[i].addMoves(list, i);
			}
		} else {
			int s = this.game.history.get(this.game.history.size()-1).SubMove;
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
		for (Move m: getPossibleMoves()) {
			if(move.equals(m)) {
				this.boards[move.SuperMove].makeMove(move.SubMove, player);
				
				if (TTT.isWon(boards, move.SuperMove, player)) {
					System.out.println("SUPER WON SUPER WON SUPER WON");
					this.state = player;
				}
				return true;
			}
		}
		return false;
	}
	
	public void undoMove() {
		Move move = this.game.getLastMove();
		if (move!=null) {
			this.boards[move.SuperMove].fields[move.SubMove].setState(FieldState.N);
			this.game.history.remove(this.game.history.size()-1);
		}
	}
	
}
