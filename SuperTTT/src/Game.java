import java.util.List;


public class Game {
	
	SuperBoard board = new SuperBoard();
	
	int currplayer = 0;
	Player[] players = new Player[2];
	
	private List<Move> history;
	
	public Game(Player p1, Player p2) {
		players[0] = p1;
		players[1] = p2;
	}
}
