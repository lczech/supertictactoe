import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class GameView extends JPanel {
	
	public Color colorDefault  = Color.black;
	public Color colorActive   = Color.red;
	public Color colorInactive = Color.gray;
	public Color colorLastMove = Color.blue;
	
	List<HumanPlayer> players = new ArrayList<HumanPlayer>();
	Game game;
	
	public void setGame(Game g){
		this.game = g;
	}
	
	public GameView() {
		//this.board = board;
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				int x=arg0.getX();
				int y=arg0.getY();
				x-=50;
				y-=50;
				x/=(400/9);
				y/=(400/9);
				for (HumanPlayer p: players){
					int superx=x/3;
					int supery=y/3;
					int subx = x-superx*3;
					int suby = y-supery*3;
					Move m = new Move(superx+3*supery, subx+3*suby);
					synchronized(p){	
						p.setMove(m);
						p.notify();
					}	
				}
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
	
	public void registerHumanPlayer(HumanPlayer humanPlayer) {
		players.add(humanPlayer);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (game!=null){
			this.drawBoard(g, new Rectangle(0,0,500,500));			
		}
	}

	public void drawBoard(Graphics g, Rectangle rect) {
		TTT.drawBoard(g, rect, this.colorDefault);
		
		Color c;
		Move lastmove = this.game.getLastMove();
		Rectangle[] subrects = TTT.getSubrects(rect);
		Rectangle[] subsubrects;
		
		for (int i=0;i<9;i++) {
			if (this.game.board.getPossibleFields().contains(i)) {
				c = this.colorActive;
			} else if (!this.game.board.boards[i].isOpen()) {
				c = this.colorInactive;
			} else {
				c = this.colorDefault;
			}
			TTT.drawBoard(g, subrects[i], c);
			
			subsubrects = TTT.getSubrects(subrects[i]);
			for (int j=0;j<9;j++) {
				if (lastmove != null && i==lastmove.SuperMove && j==lastmove.SubMove) {
					c = this.colorLastMove;
				} else {
					c = this.colorDefault;
				}
				TTT.drawState(g, subsubrects[j], this.game.board.boards[i].fields[j].getState(), c, 1);
			}
			
			TTT.drawState(g, subrects[i], this.game.board.boards[i].getState(), this.colorDefault, 2);
		}
		
		TTT.drawState(g, rect, this.game.board.getState(), this.colorDefault, 3);
	}

}
