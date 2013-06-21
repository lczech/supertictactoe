import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {
	
	Random r = new Random();

	public RandomPlayer(IFieldState.FieldState t) {
		super(t);
	}

	@Override
	boolean makeMove(SuperBoard sb) {
		List<Move> moves = sb.getPossibleMoves();
		if (moves.size() > 0) {
			int ran = r.nextInt(moves.size());
			Move m = moves.get(ran);
			sb.makeMove(m, this.type);
			return true;
		} else {
			return false;
		}
	}

	@Override
	Move getMove(SuperBoard sb) {
		List<Move> moves = sb.getPossibleMoves();
		if (moves.size() > 0) {
			return moves.get(r.nextInt(moves.size()));
		} else {
			return null;
		}
	}

}
