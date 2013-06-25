package players;
import core.GameView;
import core.Move;
import core.Seed;
import core.SuperBoard;


public class HumanPlayer extends Player {
	
	Move move;
	GameView gview;

	public HumanPlayer(Seed t, GameView gview) {
		super(t);
		this.gview = gview;
		gview.registerHumanPlayer(this);
	}
	
	public void setMove(Move move) {
		this.move=move;
	}

	@Override
	public Move getMove(SuperBoard sb) {
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
