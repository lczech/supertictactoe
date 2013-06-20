import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Main {
	
	static Game game;
	
	static SuperBoard superboard = new SuperBoard();
	
	static int currplayer = 0;
	static Player[] players = new Player[2];
	
	public static void main(String[] args) {
		final JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//superboard.draw(f.getRootPane().getGraphics(), new Rectangle(50, 50, 250, 250));
		BoardView bview = new BoardView();
		f.add(bview);
		
		players[1] = new RandomPlayer(TTT.Type.X);
		players[0] = new HumanPlayer(TTT.Type.O, bview);//new RandomPlayer(TTT.Type.O);
		
		
		game = new Game(players[0], players[1]);
		game.setBoardView(bview);
		game.run();
		
		f.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				game.run();
				//players[currplayer].makeMove(superboard);
				//currplayer = (currplayer+1)%2;
				//superboard.draw(f.getRootPane().getGraphics(), new Rectangle(50, 50, 250, 250));
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
