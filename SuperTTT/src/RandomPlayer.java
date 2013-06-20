import java.util.List;
import java.util.Random;

public class RandomPlayer extends Player {
	
	Random r = new Random();

	public RandomPlayer(PlayerType t) {
		super(t);
	}

	@Override
	void makeMove(SuperBoard sb) {
		List<Move> moves = sb.getMoves();
		sb.makeMove(moves.get(r.nextInt(moves.size())), this.type);
	}

}
