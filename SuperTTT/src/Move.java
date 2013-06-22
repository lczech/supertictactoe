
public class Move {
	
	public int SuperMove;
	public int SubMove;
	
	public boolean wonSuper;
	public boolean wonSub;
	
	public Move(int supermove, int submove) {
		this.SuperMove = supermove;
		this.SubMove   = submove;
		
		this.wonSuper  = false;
		this.wonSub    = false;
	}
	
	public boolean equals(Move move) {
		return this.SuperMove == move.SuperMove && this.SubMove == move.SubMove;
	}
	
}
