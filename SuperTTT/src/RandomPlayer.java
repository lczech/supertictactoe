import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {
	
	Random r = new Random();

	public RandomPlayer(TTT.Type t) {
		super(t);
	}

	@Override
	boolean makeMove(SuperBoard sb) {
		List<Move> moves = sb.getPossibleMoves();
		if (moves.size() > 0) {
			sb.makeMove(moves.get(r.nextInt(moves.size())), this.type);
			return true;
		} else {
			return false;
		}
	}

}
