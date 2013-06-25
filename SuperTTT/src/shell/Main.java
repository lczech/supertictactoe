package shell;

import javax.swing.JFrame;

import core.Constants;
import core.Game;
import core.GameView;
import core.Seed;

import players.HumanPlayer;
import players.Player;
import players.RandomPlayer;

public class Main {
	
	static Game game;
	
	public static void main(String[] args) {
		Constants.init();
		
		final JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameView gview = new GameView();
		f.add(gview);
		
		//players[0] = new RandomPlayer(Seed.X);
		//players[1] = new RandomPlayer(Seed.O);
		//players[1] = new HumanPlayer(Seed.O, gview);
		Player player1 = new RandomPlayer(Seed.O);//HumanPlayer(Seed.X, gview);
		Player player2 = new RandomPlayer(Seed.X);

		game = new Game(player1,player2);
		game.setBoardView(gview);
		game.run();
	}
	
}
