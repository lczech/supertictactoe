import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

public class Board {
	
	private Field[] fields;
	
	public Board() {
		this.fields = new Field[9];
		for (int i=0; i<9; i++) {
			this.fields[i] = new Field();
		}
	}
	
	public boolean isOpen() {
		for (int i=0; i<9; i++) {
			if(this.fields[i].isOpen()) {
				return true;
			}
		}
		return false;
	}
	
	public void addMoves(List<Move> target, int supermove) {
		for (int i=0; i<9; i++) {
			if(this.fields[i].isOpen()) {
				target.add(new Move(supermove, i));
			}
		}
	}
	
	public void makeMove(int field, int player) {
		if(player==0) {
			this.fields[field].State = Field.FieldState.Player1;
		} else {
			this.fields[field].State = Field.FieldState.Player1;
		}
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
		
		g.drawLine(x0+bw, y1, x3-bw, y1);
		g.drawLine(x0+bw, y2, x3-bw, y2);
		g.drawLine(x1, y0+bw, x1, y3-bw);
		g.drawLine(x2, y0+bw, x2, y3-bw);
		
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
