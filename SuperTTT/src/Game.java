import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {
	
	SuperBoard board = new SuperBoard(this);
	private GameView gview;
	
	int activeplayer = 0;
	Player[] players = new Player[2];
	
	public List<Move> history = new ArrayList<Move>();
	
	public Game(Player p1, Player p2) {
		if (p1.type == Seed.X && p2.type == Seed.O
				|| p1.type == Seed.O && p2.type == Seed.X) {
			players[0] = p1;
			players[1] = p2;
		} else {
			System.out.println("Invalid player configuration.");
		}
	}
	
	public void setBoardView(GameView bview) {
		this.gview=bview;
		bview.setGame(this);
	}
	
	public Move getLastMove() {
		return history.size()>0? history.get(history.size()-1) : null;
	}
	
	public void makeMove(Move move) {
		if (board.makeMove(move, players[activeplayer].type)) {
			this.history.add(move);
			
			if (gview != null) {
				gview.repaint();
			}
			
			activeplayer = 1-activeplayer; //next player is on turn
		} else {
			System.out.println("Invalid move: (" + move.SuperMove + " " + move.SubMove + ")");
		}
	}
	
	public void undoMove() {
		Move move = this.getLastMove();
		if (move!=null) {
			System.out.println("Undoing (" + move.SuperMove + " " + move.SubMove + ")");
			
			this.board.boards[move.SuperMove].fields[move.SubMove].setState(Seed.N);
			
			if (move.wonSub) {
				this.board.boards[move.SuperMove].setState(Seed.N);
			}
			
			if (move.wonSuper) {
				this.board.setState(Seed.N);
			}
			
			this.history.remove(this.history.size()-1);
		}
	}

	@Override
	public void run() {
		while (board.isOpen() && board.getState() == Seed.N) {
			makeMove(players[activeplayer].getMove(board));
		}
		
		if (board.getState() != Seed.N) {
			System.out.println("WINNER: "+board.getState());
		} else {
			System.out.println("DRAW!");
		}
	}
	
}
