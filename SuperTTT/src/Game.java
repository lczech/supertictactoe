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
		players[0] = p1;
		players[1] = p2;
	}
	
	public void setBoardView(GameView bview) {
		this.gview=bview;
		bview.setGame(this);
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
				
				if (gview != null) {
					//board.draw(graphics, new Rectangle(50,50,300,300));
					gview.repaint();
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
	
	public void drawBoard(Graphics g, Rectangle rect) {
		TTT.drawBoard(g, rect, gview.colorDefault);
		
		Color c;
		Move lastmove = this.getLastMove();
		Rectangle[] subrects = TTT.getSubrects(rect);
		Rectangle[] subsubrects;
		
		for (int i=0;i<9;i++) {
			if (this.board.getPossibleFields().contains(i)) {
				c = gview.colorActive;
			} else if (!this.board.boards[i].isOpen()) {
				c = gview.colorInactive;
			} else {
				c = gview.colorDefault;
			}
			TTT.drawBoard(g, subrects[i], c);
			
			subsubrects = TTT.getSubrects(subrects[i]);
			for (int j=0;j<9;j++) {
				if (lastmove != null && i==lastmove.SuperMove && j==lastmove.SubMove) {
					c = gview.colorLastMove;
				} else {
					c = gview.colorDefault;
				}
				TTT.drawState(g, subsubrects[j], this.board.boards[i].fields[j].getState(), c);
			}
			
			TTT.drawState(g, subrects[i], this.board.boards[i].getState(), gview.colorDefault);
		}
		
		TTT.drawState(g, rect, this.board.getState(), gview.colorDefault);
	}
	
}
