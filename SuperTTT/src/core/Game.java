package core;
import players.Player;

public class Game implements Runnable {
	
	private boolean    doPrint = false;
	
	private SuperBoard board = new SuperBoard();
	private GameView   gview;
	
	int activeplayer = 0;
	Player[] players = new Player[2];
	
	public Game(Player p1, Player p2) {
		if (p1.type == Seed.X && p2.type == Seed.O
				|| p1.type == Seed.O && p2.type == Seed.X) {
			players[0] = p1;
			players[1] = p2;
		} else {
			System.out.println("Invalid player configuration.");
			System.exit(0);
		}
	}
	
	public SuperBoard getBoard() {
		return this.board;
	}
	
	public void setBoardView(GameView bview) {
		this.gview=bview;
		bview.setGame(this);
	}
	
	public void makeMove(Move move) {
		if (board.makeMove(move, players[activeplayer].type)) {
			if (doPrint) {
				System.out.println(move.player.toString() + " " + move.SuperMove + " " + move.SubMove);
				if (move.wonSub) {
					System.out.println("WON WON WON");
				}
				if (move.wonSuper) {
					System.out.println("SUPER WON SUPER WON SUPER WON");
				}
			}
			
			if (gview != null) {
				gview.repaint();
			}
			
			activeplayer = 1-activeplayer; //next player is on turn
		} else {
			System.out.println("Invalid move: (" + move.SuperMove + " " + move.SubMove + ")");
		}
	}
	
	public void undoMove() {
		if (this.board.undoMove()) {
			activeplayer = 1-activeplayer;
		}
	}

	@Override
	public void run() {
		while (board.isOpen() && board.getState() == Seed.N) {
			SuperBoard clone = board.getClone();
			Move       move  = players[activeplayer].getMove(clone);
			makeMove(move);
		}
		
		//if (doPrint) {
			if (board.getState() != Seed.N) {
				System.out.println("WINNER: "+board.getState());
			} else {
				System.out.println("DRAW!");
			}
		//}
	}
	
}
