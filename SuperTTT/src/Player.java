
public abstract class Player {
	
	public IFieldState.FieldState type;
	
	public Player(IFieldState.FieldState t) {
		this.type = t;
	}

	abstract public Move getMove(SuperBoard sb);
	
}
