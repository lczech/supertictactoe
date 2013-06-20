
public abstract class Player {
	SuperBoard superboard;
	
	public Player(SuperBoard sb) {
		this.superboard = sb;
	}
	
	abstract void makeMove();
}
