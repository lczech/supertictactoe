package players;

import java.util.ArrayList;
import java.util.List;

import core.Move;
import core.Seed;
import core.SubBoard;
import core.SuperBoard;

public class PerfectPlayer extends Player {
	
	private boolean isFirstPlayer = false;
	
	private List<Integer> sendBoards = new ArrayList<Integer>();

	public PerfectPlayer(Seed t) {
		super(t);
	}
	
	@Override
	public Move getMove(SuperBoard sb) {
		if (sb.getLastMove() == null) {
			isFirstPlayer =  true;
			return new Move(4,4);
		} else if (!isFirstPlayer) {
			return new RandomPlayer(type).getMove(sb);
		}
		
		Integer board;
		List<Integer> boards = sb.getPossibleFields();
		
		if (boards.size() == 1) {
			board = boards.get(0);
		} else {
			board = null;
		}
		
		if (sb.getSubBoard(4).isOpen()) {
			return new Move(board, 4);
		} else {
			if (sendBoards.size() == 0) {
				sendBoards.add(board);
			}
			
			if (board == null) {
				int newBoard = 8-sendBoards.get(0);
				sendBoards.add(newBoard);
				board = newBoard;
			}
			
			for (int curBoard : sendBoards) {
				if (sb.getSubBoard(board).getField(curBoard).isOpen()) {
					return new Move(board, curBoard);
				}
			}
		}
		
		return null;
	}

}
