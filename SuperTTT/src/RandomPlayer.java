import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {
	
	Random r = new Random();

	public RandomPlayer(Seed t) {
		super(t);
	}

	@Override
	public Move getMove(SuperBoard sb) {
		List<Move> moves = sb.getPossibleMoves();
		if (moves.size() > 0) {
			return moves.get(r.nextInt(moves.size()));
		} else {
			return null;
		}
	}

}
