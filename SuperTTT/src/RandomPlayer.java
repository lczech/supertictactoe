import java.util.List;
import java.util.Random;


public class RandomPlayer extends Player {
	
	Random r = new Random();

	public RandomPlayer(SuperBoard sb) {
		super(sb);
	}

	@Override
	void makeMove() {
		List<Move> moves = this.superboard.getMoves();
		System.out.println("count " + moves.size());
		int m = (r.nextInt() % moves.size() + moves.size()) % moves.size();
		System.out.println("m " + m);
		this.superboard.makeMove(moves.get(m), 0);
	}

}
