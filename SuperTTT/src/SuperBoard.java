import java.util.ArrayList;
import java.util.List;

public class SuperBoard  {
	
	public Game game;
	
	private Seed state;
	
	public SubBoard[] boards;

	public SuperBoard(Game g) {
		this.game = g;
		this.state = Seed.N;
		
		this.boards = new SubBoard[9];
		for (int i=0; i<9; i++) {
			this.boards[i] = new SubBoard();
		}
	}
	
	public Seed getState() {
		return this.state;
	}

	public Seed setState(Seed s) {
		Seed old = this.state;
		this.state = s;
		return old;
	}
	
	public boolean isOpen() {
		for (int i=0; i<9; i++) {
			if(boards[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Move> getPossibleMoves() {
		ArrayList<Move> list = new ArrayList<Move>();
		
		if (this.state != Seed.N) {
			return list;
		}
		
		if (this.game.history.size() == 0) {
			for (int i=0; i<9; i++) {
				boards[i].addMoves(list, i);
			}
		} else {
			int s = this.game.getLastMove().SubMove;
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
	
	public boolean makeMove(Move move, Seed player) {
		if (player == Seed.N) {
			return false;
		}
		
		for (Move m: getPossibleMoves()) {
			if(move.equals(m)) {
				System.out.println(player.toString() + " " + move.SuperMove + " " + move.SubMove);
				
				Seed ps = this.boards[move.SuperMove].getState();
				this.boards[move.SuperMove].makeMove(move.SubMove, player);
				
				if (ps != this.boards[move.SuperMove].getState()) {
					move.wonSub = true;
				}
				
				if (this.state == Seed.N && TTT.isWon(boards, move.SuperMove, player)) {
					System.out.println("SUPER WON SUPER WON SUPER WON");
					this.state = player;
					move.wonSuper = true;
				}
				return true;
			}
		}
		return false;
	}
	
}
