package players;

import java.util.List;
import java.util.Random;

import core.Move;
import core.Seed;
import core.SuperBoard;
import core.TTT;

public class MonteCarloPlayer extends Player {
	
	// limits: stop after maxRuns random games or after maxTime milliseconds
	public int maxRuns = Integer.MAX_VALUE;
	public int maxTime = 1000;
	
	Random r = new Random();

	public MonteCarloPlayer(Seed t) {
		super(t);
	}
	
	public MonteCarloPlayer(Seed t, int maxtime) {
		super(t);
		this.maxTime = maxtime;
	}
	
	public MonteCarloPlayer(Seed t, int maxtime, int maxruns) {
		super(t);
		this.maxTime = maxtime;
		this.maxRuns = maxruns;
	}

	@Override
	public Move getMove(SuperBoard superboard) {
		// initialize limit counters
		long time = System.currentTimeMillis();
		int  runs = 0;
		
		// we want to try all possible moves...
		List<Move> moves = superboard.getPossibleMoves();
		if (moves.size() == 0) {
			return null;
		}
		
		// ...and count which of them wins the most games
		int[] wins = new int[moves.size()];
		for (int i=0; i<wins.length; i++) {
			wins[i] = 0;
		}
		
		// within our limits, try as many games as possible
		int first = 0;
		while (runs<maxRuns && time+maxTime>=System.currentTimeMillis()) {
			// create a clone and do the first move
			SuperBoard cloneboard = superboard.getClone();
			cloneboard.makeMove(moves.get(first), this.type);
			Seed active = this.opp;
			
			// finish the game randomly
			while (!cloneboard.isFinished()) {
				List<Move> clonemoves = cloneboard.getPossibleMoves();
				cloneboard.makeMove(clonemoves.get(r.nextInt(clonemoves.size())), active);
				active = TTT.Opponent(active);
			}
			
			// use outcome of the game
			if (cloneboard.getState() == this.type) {
				wins[first]++;
			} else if (cloneboard.getState() == this.opp) {
				wins[first]--;
			}
			
			// circle through all moves
			first = (first+1)%moves.size();
			runs++;
		}
		
		System.out.println("Runs  "+runs);
		
		// find the best move...
		int maxv = Integer.MIN_VALUE;
		int maxi = -1;
		for (int i=0; i<wins.length; i++) {
			if (wins[i]>maxv) {
				maxv = wins[i];
				maxi = i;
			}
		}
		
		// ...and return it
		return moves.get(maxi);
	}

}
