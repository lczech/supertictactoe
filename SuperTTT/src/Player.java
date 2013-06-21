
public abstract class Player {
	
	public IFieldState.FieldState type;
	
	public Player(IFieldState.FieldState t) {
		this.type = t;
	}

	abstract Move getMove(SuperBoard sb);
	
}
