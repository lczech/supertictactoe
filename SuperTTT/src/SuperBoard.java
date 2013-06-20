import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SuperBoard extends TTT {
	
	private SubBoard[] boards;
	private Move lastMove;

	public SuperBoard() {
		this.boards = new SubBoard[9];
		for (int i=0; i<9; i++) {
			this.boards[i] = new SubBoard();
		}
		this.lastMove = null;
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
		
		if(this.lastMove == null) {
			for (int i=0; i<9; i++) {
				boards[i].addMoves(list, i);
			}
		} else {
			int s = this.lastMove.SubMove;
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
		this.lastMove = move;
		this.boards[move.SuperMove].makeMove(move.SubMove, player);
	}

	public void draw(Graphics g, Rectangle rect) {
		drawBoard(g, rect, boards);
		drawState(g, rect);
	}
	
}
