import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args) {
		final JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Board[] smallBoards = new Board[9];
		for (int i=0;i<9;i++){
			Board[] single = new Board[9];
			for (int j=0;j<9;j++){
				single[j] = new SingleField();
			}
			smallBoards[i] = new TicTacToeBoard(single);
		}
		final Board ticBoard = new TicTacToeBoard(smallBoards);
		
		ticBoard.draw(f.getRootPane().getGraphics(), new Rectangle(50, 50, 250, 250));
		
		f.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				ticBoard.draw(f.getRootPane().getGraphics(), new Rectangle(50, 50, 250, 250));
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
