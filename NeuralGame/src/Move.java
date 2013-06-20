
public class Move {
	
	int position = 0;
	public int[] move;
	private Player.player player;
	
	public Move(Player.player player, int... move) {
		this.move = move;
		this.player = player;
	}
	
	public Move getSubMove() {
		position++;
		return this;
	}
	
	public int getCurrentPosition() {
		return move[position];
	}
	
	public boolean isLastMove() {
		return position==move.length-1;
	}
	
	public Player.player getPlayer() {
		return player;
	}
}
