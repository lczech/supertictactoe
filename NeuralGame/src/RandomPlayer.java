import java.util.List;
import java.util.Random;


public class RandomPlayer extends Player {
	
	Random r = new Random();

	@Override
	public void move(TicTacToeBoard b) {
		List<Move> moves = b.getPossibleMoves();
		int m = r.nextInt() % moves.size();
		b.makeMove(moves.get(m));
	}

}
