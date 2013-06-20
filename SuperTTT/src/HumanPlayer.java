
public class HumanPlayer extends Player {
	
	Move move;
	BoardView bview;

	public HumanPlayer(TTT.Type t, BoardView bview) {
		super(t);
		// TODO Auto-generated constructor stub
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
		//return move;
		//System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		sb.makeMove(move, this.type);
		return true;
	}
	
	public void setMove(Move move) {
		this.move=move;
		//System.out.println("aaaaaaaaaaaaaaaaaaa");
	}

}
