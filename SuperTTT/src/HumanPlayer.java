
public class HumanPlayer extends Player {
	
	Move move;
	BoardView bview;

	public HumanPlayer(IFieldState.FieldState t, BoardView bview) {
		super(t);
		this.bview = bview;
		bview.registerHumanPlayer(this);
	}
	
	public void setMove(Move move) {
		this.move=move;
	}

	@Override
	boolean makeMove(SuperBoard sb) {
		synchronized(this){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return sb.makeMove(move, this.type);
	}

	@Override
	Move getMove(SuperBoard sb) {
		synchronized(this){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return move;
	}

}
