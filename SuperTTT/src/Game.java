import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {
	
	SuperBoard board = new SuperBoard(this);
	private BoardView bview;
	
	int activeplayer = 0;
	Player[] players = new Player[2];
	
	public List<Move> history = new ArrayList<Move>();
	
	public Game(Player p1, Player p2) {
		players[0] = p1;
		players[1] = p2;
	}
	
	public void setBoardView(BoardView bview) {
		this.bview=bview;
		bview.setBoard(board);
	}
	
	public Move getLastMove() {
		return history.size()>0? history.get(history.size()-1) : null;
	}

	@Override
	public void run() {
		Player winner = null;
		
		while (board.isOpen() && winner==null) {
			Move move = players[activeplayer].getMove(board);
			if (board.makeMove(move, players[activeplayer].type)) {
				this.history.add(move);
				
				if (board.getState() == players[activeplayer].type) {
					winner = players[activeplayer];
				}
				
				if (bview != null) {
					//board.draw(graphics, new Rectangle(50,50,300,300));
					bview.repaint();
				}
				
				activeplayer = 1-activeplayer; //next player is on turn
			} else {
				System.out.println("Invalid move: (" + move.SuperMove + " " + move.SubMove + ")");
			}
		}
		
		if (winner != null) {
			System.out.println("WINNER: "+winner.type);
		} else {
			System.out.println("DRAW!");
		}
	}
}
