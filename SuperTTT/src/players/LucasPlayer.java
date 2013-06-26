package players;

import java.util.ArrayList;

import core.ISeeded;
import core.Move;
import core.Seed;
import core.SuperBoard;

public class LucasPlayer extends Player {
	
	private int maxdepth = 8;
	private int callcount;

	public LucasPlayer(Seed t) {
		super(t);
	}
	
	private int fieldValue(ISeeded[] board, int field) {
		int val;
		if(field%2==1) {
			val = 2;
		} else if (field==4) {
			val = 4;
		} else {
			val = 3;
		}
		
		if (board[field].getState() == this.type) {
			return val;
		} else if (board[field].getState() == this.opp) {
			return -val;
		} else {
			return 0;
		}
	}
	
	private int boardValue(ISeeded[] board) {
		int sum = 0;
		for (int i=0; i<board.length; i++) {
			sum += fieldValue(board, i);
		}
		return sum;
	}
	
	private int scoreLine(ISeeded[] board, int c1, int c2, int c3) {
		int score  = 0;
		int factor = 10;
		
		if (board[c1].getState() == this.type) {
			score = 1;
		} else if (board[c1].getState() == this.opp) {
			score = -1;
		}
		
		if (board[c2].getState() == this.type) {
			if (score == 1) {
				score *= factor;
			 } else if (score == -1) {
				return 0;
			 } else {
				score = 1;
			 }
		} else if (board[c2].getState() == this.opp) {
			if (score == -1) {
				score *= factor;
			 } else if (score == 1) {
				return 0;
			 } else {
				score = -1;
			 }
		}
		
		if (board[c3].getState() == this.type) {
			if (score > 0) {
				score *= factor;
			 } else if (score < 0) {
				return 0;
			 } else {
				score = 1;
			 }
		} else if (board[c3].getState() == this.opp) {
			if (score < 0) {
				score *= factor;
			 } else if (score > 1) {
				return 0;
			 } else {
				score = -1;
			 }
		}
		
		return score;
	}
	
	private int scoreBoard(ISeeded[] board) {
		int score = 0;
		score += scoreLine(board, 0, 1, 2); // row 0
		score += scoreLine(board, 3, 4, 5); // row 1
		score += scoreLine(board, 6, 7, 8); // row 2
		score += scoreLine(board, 0, 3, 6); // col 0
		score += scoreLine(board, 1, 4, 7); // col 1
		score += scoreLine(board, 2, 5, 8); // col 2
		score += scoreLine(board, 0, 4, 8); // dia
		score += scoreLine(board, 2, 4, 6); // dia
		return score;
	}
	
	private int score (SuperBoard board) {
		int score = 0;
		score += this.scoreBoard(board.getSubBoards());
		score += this.boardValue(board.getSubBoards());
		score *= 10000;
		
		for (int i=0; i<board.getSubBoards().length; i++) {
			score += this.scoreBoard(board.getSubBoard(i).getFields());
			score += this.boardValue(board.getSubBoard(i).getFields());
		}
		
		return score;
	}
	
	private int[] minimax(SuperBoard board, int depth, Seed player, int alpha, int beta) {
		this.callcount++;
		
		int score;
		int bestSuper = -1;
		int bestSub   = -1;
		
		ArrayList<Move> list = board.getPossibleMoves();
		if (list.isEmpty() || depth == 0) {
			score = this.score(board);
			return new int[] {score, bestSuper, bestSub};
		} else {
			for (Move m : list) {
				board.makeMove(m, player);
				
				if (player == this.type) {
					score = minimax(board, depth-1, this.opp, alpha, beta)[0];
					if (score > alpha) {
						alpha     = score;
						bestSuper = m.SuperMove;
						bestSub   = m.SubMove;
					}
				} else {
					score = minimax(board, depth-1, this.type, alpha, beta)[0];
					if (score < beta) {
						beta      = score;
						bestSuper = m.SuperMove;
						bestSub   = m.SubMove;
					}
				}

				board.undoMove();
				if (alpha >= beta) break;
			}
			
			return new int[] {(player == this.type) ? alpha : beta, bestSuper, bestSub};
		}
	}

	@Override
	public Move getMove(SuperBoard board) {
		this.callcount = 0;
		int[] result = minimax(board, this.maxdepth, this.type, Integer.MIN_VALUE, Integer.MAX_VALUE);
		
		System.out.println("Calls "+this.callcount);
		return new Move(result[1], result[2]);
	}

}
