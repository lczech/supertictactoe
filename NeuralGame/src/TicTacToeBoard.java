import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import org.w3c.dom.css.Rect;


public class TicTacToeBoard extends Board {

	Board[] fields;
	
	public List<Move> getPossibleMoves() {
		return null;
	}
	
	public void makeMove(Move m) {
		fields[m.getCurrentPosition()].makeMove(m.getSubMove());
	}
	
	public TicTacToeBoard(Board[] fields) {
		if (fields.length != 9) {
			throw new IllegalArgumentException("9 fields required for TicTacToeBoard.");
		}
		this.fields=fields;
		
	}
	
	@Override
	public void draw(Graphics g, Rectangle rect) {

		rect.height -= 20;
		rect.width -= 20;
		rect.x += 10;
		rect.y += 10;
		
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
		
		System.out.println(fields[0]);
		System.out.println(subrects[0]);
		for (int i=0;i<9;i++) {
			fields[i].draw(g, subrects[i]);
		}
	}

	@Override
	public void reset() {
		state=FieldState.Neutral;
		for (Board b: fields) {
			b.reset();
		}
	}

}
