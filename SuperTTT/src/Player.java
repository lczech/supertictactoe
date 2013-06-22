
public abstract class Player {
	
	public Seed type;
	
	public Seed opp;
	
	public Player(Seed t) {
		this.type = t;
		this.opp  = TTT.Opponent(t);
	}

	abstract public Move getMove(SuperBoard sb);
	
}
