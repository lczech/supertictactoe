
public class Game {
	private TicTacToeBoard board;
	private Player[] player = new Player[2];
	private int      activePlayer = 0;

	public Game(TicTacToeBoard b, Player p1, Player p2) {
		this.board     = b;
		this.player[0] = p1;
		this.player[1] = p2;
	}
	
	public void move() {
		this.player[this.activePlayer].move(this.board);
		this.activePlayer = (this.activePlayer+1)%2;
	}
}
