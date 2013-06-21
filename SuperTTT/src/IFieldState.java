
public interface IFieldState {
	
	public static enum FieldState {N, X, O};
	
	public FieldState getState();
	
	public void setState(FieldState s);
	
}
