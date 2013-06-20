
public abstract class Player {
	
	public static enum PlayerType {Player1, Player2};
	
	public PlayerType type;
	
	public Player(PlayerType t) {
		this.type = t;
	}
	
	abstract void makeMove(SuperBoard sb);
	
}
