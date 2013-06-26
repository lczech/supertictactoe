package players;

import java.util.List;
import java.util.Random;

import core.Move;
import core.Seed;
import core.SuperBoard;
import core.TTT;

public class MonteCarloPlayer extends Player {
	
	public int maxRuns = 1000;
	public int maxTime = 50;
	
	Random r = new Random();

	public MonteCarloPlayer(Seed t) {
		super(t);
	}

	@Override
	public Move getMove(SuperBoard sb) {
		long time = System.currentTimeMillis();
		int  runs = 0;
		
		List<Move> moves = sb.getPossibleMoves();
		if (moves.size() == 0) {
			return null;
		}
		
		int[] wins = new int[moves.size()];
		for (int i=0; i<wins.length; i++) {
			wins[i] = 0;
		}
		
		while (runs<maxRuns && time+maxTime>=System.currentTimeMillis()) {
			SuperBoard clone = sb.getClone();
			int first = r.nextInt(moves.size());
			clone.makeMove(moves.get(first), this.type);
			Seed active = this.opp;
			
			while (clone.isOpen() && clone.getState() == Seed.N) {
				List<Move> clonemoves = clone.getPossibleMoves();
				clone.makeMove(clonemoves.get(r.nextInt(clonemoves.size())), active);
				active = TTT.Opponent(active);
			}
			
			if (clone.getState() == this.type) {
				wins[first]++;
			} else if (clone.getState() == this.opp) {
				wins[first]--;
			}
			runs++;
		}
		
		//System.out.println("Did "+runs+" runs.");
		
		int maxv = Integer.MIN_VALUE;
		int maxi = -1;
		for (int i=0; i<wins.length; i++) {
			if (wins[i]>maxv) {
				maxv = wins[i];
				maxi = i;
			}
		}
		
		return moves.get(maxi);
	}

}
