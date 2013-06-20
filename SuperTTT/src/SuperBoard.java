import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SuperBoard extends TTT {
	
	private SubBoard[] boards;
	private List<Move> history;

	public SuperBoard() {
		this.boards = new SubBoard[9];
		for (int i=0; i<9; i++) {
			this.boards[i] = new SubBoard();
		}
		this.history = new ArrayList<Move>();
	}
	
	public boolean isOpen() {
		for (int i=0; i<9; i++) {
			if(boards[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Move> getMoves() {
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
	
	public void makeMove(Move move, TTT.Type player) {
		this.history.add(move);
		this.boards[move.SuperMove].makeMove(move.SubMove, player);
	}

	public void draw(Graphics g, Rectangle rect) {
		drawBoard(g, rect, boards, false);
		drawState(g, rect);
	}
	
}
