
public class Move {
	public int SuperMove;
	public int SubMove;
	
	public Move(int supermove, int submove) {
		this.SuperMove = supermove;
		this.SubMove   = submove;
	}
	
	public boolean equals(Move move) {
		return this.SuperMove == move.SuperMove && this.SubMove == move.SubMove;
	}
	
}
