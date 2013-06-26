package players;

import java.awt.List;
import java.util.ArrayList;

import core.ISeeded;
import core.Move;
import core.Seed;
import core.SuperBoard;

public class LucasPlayer extends Player {

	public LucasPlayer(Seed t) {
		super(t);
	}
	
	private int fieldValue(int field) {
		if(field%2==1) {
			return 2;
		} else if (field==4) {
			return 4;
		} else {
			return 3;
		}
	}
	
	private int scoreLine(ISeeded[] board, int c1, int c2, int c3) {
		int score = 0;
		
		if (board[c1].getState() == this.type) {
			score = 1;
		} else if (board[c1].getState() == this.opp) {
			score = -1;
		}
		
		if (board[c2].getState() == this.type) {
			if (score == 1) {
				score *= 10;
			 } else if (score == -1) {
				return 0;
			 } else {
				score = 1;
			 }
		} else if (board[c2].getState() == this.opp) {
			if (score == -1) {
				score *= 10;
			 } else if (score == 1) {
				return 0;
			 } else {
				score = -1;
			 }
		}
		
		if (board[c3].getState() == this.type) {
			if (score > 0) {
				score *= 10;
			 } else if (score < 0) {
				return 0;
			 } else {
				score = 1;
			 }
		} else if (board[c3].getState() == this.opp) {
			if (score < 0) {
				score *= 10;
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
	
	private int[] minimax(SuperBoard board, int depth, Seed player, int alpha, int beta) {
		int score;
		int bestSuper = -1;
		int bestSub   = -1;
		
		ArrayList<Move> list = board.getPossibleMoves();
		if (list.isEmpty() || depth == 0) {
			score = this.scoreBoard(board.getSubBoards());
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
		int[] result = minimax(board, 4, this.type, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return new Move(result[1], result[2]);
	}

}
