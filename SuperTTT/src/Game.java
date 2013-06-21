import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Game implements Runnable {
	
	SuperBoard board = new SuperBoard();
	private BoardView bview;
	
	int activeplayer = 0;
	Player[] players = new Player[2];
	
	private List<Move> history;
	
	public Game(Player p1, Player p2) {
		players[0] = p1;
		players[1] = p2;
	}
	
	public void setBoardView(BoardView bview) {
		this.bview=bview;
		bview.setBoard(board);
	}

	@Override
	public void run() {
		Player winner = null;
		while (board.isOpen() && winner==null) {
			//Move s = players[activeplayer]..getMove();
			if (players[activeplayer].makeMove(board)) {
				if (TTT.isWon(board.boards, board.getLastMove().SuperMove, players[activeplayer].type)) {
					winner = players[activeplayer];
					board.setState(players[activeplayer].type);
				}
				//
				if (bview != null) {
					//board.draw(graphics, new Rectangle(50,50,300,300));
					bview.repaint();
				}
				activeplayer = 1-activeplayer; //next player is on turn
			}
		}
	}
}
