package core;

import java.util.ArrayList;
import java.util.List;

public class SuperBoard  {
	
	private Seed state = Seed.N;
	
	private SubBoard[] boards = new SubBoard[9];
	
	private List<Move> history = new ArrayList<Move>();

	public SuperBoard() {
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
	
	public SubBoard getSubBoard(int i) {
		return this.boards[i];
	}
	
	public SubBoard[] getSubBoards() {
		return this.boards;
	}
	
	public boolean isOpen() {
		for (int i=0; i<9; i++) {
			if(boards[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public Move getLastMove() {
		return history.size()>0 ? history.get(history.size()-1) : null;
	}
	
	public ArrayList<Move> getPossibleMoves() {
		ArrayList<Move> list = new ArrayList<Move>();
		
		if (this.state != Seed.N) {
			return list;
		}
		
		if (this.history.size() == 0) {
			for (int i=0; i<9; i++) {
				boards[i].addMoves(list, i);
			}
		} else {
			int s = this.getLastMove().SubMove;
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
		if (player == Seed.N || this.state != Seed.N) {
			return false;
		}
		
		for (Move m: getPossibleMoves()) {
			if(move.equals(m)) {
				//System.out.println(player.toString() + " " + move.SuperMove + " " + move.SubMove);
				
				move.player = player;
				this.history.add(move);
				
				Seed ps = this.boards[move.SuperMove].getState();
				if (!this.boards[move.SuperMove].makeMove(move.SubMove, player)) {
					System.out.println("Weird error: move to (" + move.SuperMove + " " + move.SubMove + ") in list, but not possible");
				}
				
				if (ps != this.boards[move.SuperMove].getState()) {
					move.wonSub = true;
				}
				
				if (TTT.isWon(boards, move.SuperMove, player)) {
					//System.out.println("SUPER WON SUPER WON SUPER WON");
					this.state = player;
					move.wonSuper = true;
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean undoMove() {
		Move move = this.getLastMove();
		if (move==null) {
			return false;
		}
		
		//System.out.println("Undoing (" + move.SuperMove + " " + move.SubMove + ")");
		
		this.history.remove(this.history.size()-1);
		this.boards[move.SuperMove].undoMove(move.SubMove);
		
		if (move.wonSub) {
			this.boards[move.SuperMove].setState(Seed.N);
		}
		
		if (move.wonSuper) {
			this.setState(Seed.N);
		}
		
		return true;
	}
	
	public SuperBoard getClone() {
		SuperBoard sb = new SuperBoard();
		for (Move m : this.history) {
			sb.makeMove(m, m.player);
		}
		return sb;
	}
	
}
