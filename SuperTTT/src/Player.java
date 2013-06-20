
public abstract class Player {
	
	public TTT.Type type;
	
	public Player(TTT.Type t) {
		this.type = t;
	}
	
	abstract void makeMove(SuperBoard sb);
	
}
