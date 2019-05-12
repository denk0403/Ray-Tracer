import java.awt.Graphics2D;
import java.awt.Point;

public interface Barrier {

	public void drawSelf(Graphics2D g2);
	
	public Point getPoint1();
	
	public Point getPoint2();
}
