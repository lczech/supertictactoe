package shell;

import java.awt.Dimension;

import javax.swing.JFrame;

import core.Game;
import core.GameView;
import core.Seed;

import players.HumanPlayer;
import players.LucasPlayer;
import players.MonteCarloPlayer;
import players.PerfectPlayer;
import players.Player;
import players.RandomPlayer;

public class Main {
	
	static Game game;
	
	private static int numberOfGames = 10;
	
	public static void main(String[] args) {
		final JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameView gview = new GameView();
		gview.setBounds(0, 0, 500, 500);
		//gview.setPreferredSize(new Dimension(500,500));
		f.add(gview);
		//f.pack();
		
		Player player1 = new HumanPlayer(Seed.X, gview);
		//Player player1 = new LucasPlayer(Seed.X);
		//Player player1 = new MonteCarloPlayer(Seed.X, 1000);
		//Player player1 = new PerfectPlayer(Seed.X);
		//Player player1 = new RandomPlayer(Seed.X);
		
		//Player player2 = new HumanPlayer(Seed.O, gview);
		//Player player2 = new LucasPlayer(Seed.O);
		//Player player2 = new MonteCarloPlayer(Seed.O);
		Player player2 = new PerfectPlayer(Seed.O);
		//Player player2 = new RandomPlayer(Seed.O);
		
		int wonX=0, wonO=0, wonN=0;
		for (int i=0; i<numberOfGames; i++) {
			game = new Game(player1,player2);
			game.setBoardView(gview);
			f.repaint();
			game.run();
			
			switch (game.getBoard().getState()) {
				case N: wonN++; break;
				case X: wonX++; break;
				case O: wonO++; break;
			}
		}
		
		System.out.println("Draw: " + wonN + ", X: " + wonX + ", O: " + wonO);
	}
	
}
