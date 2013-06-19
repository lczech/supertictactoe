
public class Game {
	private Board    board;
	private Player[] player = new Player[2];
	private int      activePlayer = 0;

	public Game(Board b, Player p1, Player p2) {
		this.board     = b;
		this.player[0] = p1;
		this.player[1] = p2;
	}
	
	public void move() {
		this.player[this.activePlayer].move(this.board);
		this.activePlayer = (this.activePlayer+1)%2;
	}
}
