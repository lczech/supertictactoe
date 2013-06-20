import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public abstract class TTT {
	
	public static enum Type {N, X, O};

	public TTT.Type State;
	
	public TTT() {
		this.State  = TTT.Type.N;
	}
	
	public int Point2Field(int x, int y) {
		return y*3 + x;
	}
	
	public Point Field2Point(int field) {
		return new Point(field%3, field/3);
	}
	
	abstract public boolean isOpen();
	
	public boolean isWon(TTT[] board, int lastmove, TTT.Type player) {
		boolean won = false;
		
		switch (lastmove) {
		case 0:
			won |= board[1].State == player && board[2].State == player;
			won |= board[3].State == player && board[6].State == player;
			won |= board[4].State == player && board[8].State == player;
			break;
			
		case 1:
			won |= board[0].State == player && board[2].State == player;
			won |= board[4].State == player && board[7].State == player;
			break;
			
		case 2:
			won |= board[0].State == player && board[1].State == player;
			won |= board[5].State == player && board[8].State == player;
			won |= board[4].State == player && board[6].State == player;
			break;
			
		case 3:
			won |= board[0].State == player && board[6].State == player;
			won |= board[4].State == player && board[5].State == player;
			break;
			
		case 4:
			won |= board[1].State == player && board[7].State == player;
			won |= board[3].State == player && board[5].State == player;
			won |= board[0].State == player && board[8].State == player;
			won |= board[2].State == player && board[6].State == player;
			break;
			
		case 5:
			won |= board[2].State == player && board[8].State == player;
			won |= board[3].State == player && board[4].State == player;
			break;
			
		case 6:
			won |= board[0].State == player && board[3].State == player;
			won |= board[7].State == player && board[8].State == player;
			won |= board[4].State == player && board[2].State == player;
			break;
			
		case 7:
			won |= board[1].State == player && board[4].State == player;
			won |= board[6].State == player && board[8].State == player;
			break;
			
		case 8:
			won |= board[6].State == player && board[7].State == player;
			won |= board[2].State == player && board[5].State == player;
			won |= board[0].State == player && board[4].State == player;
			break;
			
		default:
			break;
		}
		
		return won;
	}
	
	abstract public void draw(Graphics g, Rectangle rect);
	
	public void drawState(Graphics g, Rectangle rect) {
		rect.x      += 0.15 * rect.width;
		rect.y      += 0.15 * rect.height;
		rect.width  *= 0.7;
		rect.height *= 0.7;
		
		switch (this.State) {
		case X:
			g.drawLine(rect.x, rect.y, rect.x+rect.width, rect.y+rect.height);
			g.drawLine(rect.x+rect.width, rect.y, rect.x, rect.y+rect.height);
			break;
			
		case O:
			g.drawOval(rect.x, rect.y, rect.width, rect.height);
			break;

		default:
			break;
		}		
	}
	
	public void drawBoard(Graphics g, Rectangle rect, TTT[] fields, boolean active) {
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
		
		if (active) {
			g.setColor(Color.red);
		}
		//g.drawLine(x0+bw, y1, x3-bw, y1);
		//g.drawLine(x0+bw, y2, x3-bw, y2);
		//g.drawLine(x1, y0+bw, x1, y3-bw);
		//g.drawLine(x2, y0+bw, x2, y3-bw);
		
		int d;
		for (int i=0; i<2; i++) {
			if(i==0) d = -w/40; else d = w/40;
			
			g.drawLine(x0+bw, y1+d, x3-bw, y1+d);
			g.drawLine(x0+bw, y2+d, x3-bw, y2+d);
			g.drawLine(x1+d, y0+bw, x1+d, y3-bw);
			g.drawLine(x2+d, y0+bw, x2+d, y3-bw);
		}
		g.setColor(Color.black);
		
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
			fields[i].draw(g, subrects[i]);
		}
	}

}
