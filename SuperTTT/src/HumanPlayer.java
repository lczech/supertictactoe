
public class HumanPlayer extends Player {
	
	Move move;
	BoardView bview;

	public HumanPlayer(TTT.Type t, BoardView bview) {
		super(t);
		this.bview = bview;
		bview.registerHumanPlayer(this);
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
	
	public void setMove(Move move) {
		this.move=move;
	}

}
