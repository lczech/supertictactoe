import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SuperBoard {
	
	private Board[] boards;
	private Move lastMove;

	public SuperBoard() {
		this.boards = new Board[9];
		for (int i=0; i<9; i++) {
			this.boards[i] = new Board();
		}
		this.lastMove = null;
	}
	
	public boolean isOpen() {
		for (int i=0; i<9; i++) {
			if(boards[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public List<Move> getMoves() {
		List<Move> list = new ArrayList<Move>();
		
		if(this.lastMove == null) {
			for (int i=0; i<9; i++) {
				boards[i].addMoves(list, i);
			}
		} else {
			int s = this.lastMove.SubMove;
			if (boards[s].isOpen()) {
				boards[s].addMoves(list, s);
			} else {
				for (int i=0; i<9; i++) {
					boards[i].addMoves(list, i);
				}
			}
		}
		
		return list;
	}
	
	public void makeMove(Move move, Player.PlayerType player) {
		this.lastMove = move;
		this.boards[move.SuperMove].makeMove(move.SubMove, player);
	}

	public void draw(Graphics g, Rectangle rect) {
		rect.x      += 0.1 * rect.width;
		rect.y      += 0.1 * rect.height;
		rect.width  *= 0.8;
		rect.height *= 0.8;
		
		int w=rect.width/3;
		int h=rect.height/3;
		
		int x0 = rect.x+(rect.width*0)/3;
		int x1 = rect.x+(rect.width*1)/3;
		int x2 = rect.x+(rect.width*2)/3;
		int x3 = rect.x+(rect.width*3)/3;
		
		int y0 = rect.y+(rect.height*0)/3;
		int y1 = rect.y+(rect.height*1)/3;
		int y2 = rect.y+(rect.height*2)/3;
		int y3 = rect.y+(rect.height*3)/3;
		
		int bw=0;
		int d;
		for (int i=0; i<=1; i++) {
			if(i==0) d = -w/40; else d = w/40;
			
			g.drawLine(x0+bw, y1+d, x3-bw, y1+d);
			g.drawLine(x0+bw, y2+d, x3-bw, y2+d);
			g.drawLine(x1+d, y0+bw, x1+d, y3-bw);
			g.drawLine(x2+d, y0+bw, x2+d, y3-bw);
		}
		
		Rectangle[] subrects = new Rectangle[9];
		subrects[0] = new Rectangle(x0,y0,w,h);
		subrects[1] = new Rectangle(x1,y0,w,h);
		subrects[2] = new Rectangle(x2,y0,w,h);
		subrects[3] = new Rectangle(x0,y1,w,h);
		subrects[4] = new Rectangle(x1,y1,w,h);
		subrects[5] = new Rectangle(x2,y1,w,h);
		subrects[6] = new Rectangle(x0,y2,w,h);
		subrects[7] = new Rectangle(x1,y2,w,h);
		subrects[8] = new Rectangle(x2,y2,w,h);
		
		for (int i=0;i<9;i++) {
			boards[i].draw(g, subrects[i]);
		}
	}
}
