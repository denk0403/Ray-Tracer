import java.awt.Graphics2D;
import java.awt.Point;

public class Wall implements Barrier {

	int x1;
	int y1;
	int x2;
	int y2;
	
	
	public Wall(int x1, int y1, int x2, int y2) {
		this.x1  = x1;
		this.y1  = y1;
		this.x2  = x2;
		this.y2  = y2;
	}
	
	@Override
	public void drawSelf(Graphics2D g2) {
		g2.drawLine(x1, y1, x2, y2);
	}
	
	public Point getPoint1() {
		return new Point(x1, y1);
	}
	
	public Point getPoint2() {
		return new Point(x2, y2);
	}
	
}
