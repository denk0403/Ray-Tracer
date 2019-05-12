import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Player {

	private int x;
	private int y;
	private double theta;
	private boolean MOVE_LEFT;
	private boolean MOVE_RIGHT;
	private boolean MOVE_UP;
	private boolean MOVE_DOWN;
	private boolean MOVE_FORWARD;
	private boolean MOVE_LEFTFORWARD;
	private boolean MOVE_BACKWARD;
	private boolean MOVE_RIGHTFORWARD;
	private int speed = 3;
	private List<Ray> rays = new ArrayList<>();
	private int ROTATE;

	public Player() {
		this.x = 0;
		this.y = 0;
		this.theta = 0;
	}

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.theta = 0;
	}

	public Player(int x, int y, double theta) {
		this.x = x;
		this.y = y;
		this.theta = theta;
	}

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public void move() {
		if (this.MOVE_LEFT) {
			this.move(-this.speed, 0);
		}
		if (this.MOVE_RIGHT) {
			this.move(this.speed, 0);
		}
		if (this.MOVE_UP) {
			this.move(0, this.speed);
		}
		if (this.MOVE_DOWN) {
			this.move(0, -this.speed);
		}
		if(this.MOVE_FORWARD) {
			this.move((int)(this.speed*Math.cos(this.theta)), (int)(this.speed*Math.sin(this.theta)));
		}
		if(this.MOVE_BACKWARD) {
			this.move(-(int)(this.speed*Math.cos(this.theta)), -(int)(this.speed*Math.sin(this.theta)));
		}
		if(this.MOVE_LEFTFORWARD) {
			this.move((int)(this.speed*Math.cos(this.theta + Math.PI/2)), (int)(this.speed*Math.sin(this.theta + Math.PI/2)));
		}
		if(this.MOVE_RIGHTFORWARD) {
			this.move((int)(this.speed*Math.cos(this.theta - Math.PI/2)), (int)(this.speed*Math.sin(this.theta - Math.PI/2)));
		}
		if (this.ROTATE > 0) {
			this.theta += Math.toRadians(5);
		} else if (this.ROTATE < 0) {
			this.theta -= Math.toRadians(5);
		}
	}

	public void updateThetaTo(double theta) {
		this.theta = theta;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public double getTheta() {
		return this.theta;
	}

	public void moveLeft(boolean ml) {
		this.MOVE_LEFT = ml;
	}

	public void moveRight(boolean mr) {
		this.MOVE_RIGHT = mr;
	}

	public void moveUp(boolean mu) {
		this.MOVE_UP = mu;
	}

	public void moveDown(boolean md) {
		this.MOVE_DOWN = md;
	}
	
	public void moveLeftForward(boolean ml) {
		this.MOVE_LEFTFORWARD = ml;
	}

	public void moveRightForward(boolean mr) {
		this.MOVE_RIGHTFORWARD = mr;
	}

	public void moveForward(boolean mu) {
		this.MOVE_FORWARD = mu;
	}

	public void moveBackward(boolean md) {
		this.MOVE_BACKWARD = md;
	}
	
	public List<Point> getIntersectionPoints(List<Barrier> barriers) {
		List<Point> points = new ArrayList<>();
		
		this.rays = new ArrayList<>();
		for (int angle = -45; angle <= 45; angle += 1) {
			this.rays.add(new Ray(this.x, this.y, this.theta + Math.toRadians(angle)));
		}
		if (!barriers.isEmpty()) {
			for (Ray r : this.rays) {
				Point closest = r.cast(barriers.get(0));
				for (Barrier b : barriers) {
					Point curP = r.cast(b);
					if (closest == null || (curP != null && new Point(this.x, this.y)
							.distance(curP) < new Point(this.x, this.y).distance(closest))) {
						closest = curP;
					}
				}
				points.add(closest);	
			}
		}
		return points;
	}

	public void drawRays(Graphics2D g2, List<Barrier> barriers) {
		for (Point p : this.getIntersectionPoints(barriers)) {
			if (p != null) {
				g2.drawLine(this.x, this.y, p.x, p.y);
			}
		}
	}
	
	public void rotateClockwise() {
		this.ROTATE = -1;
	}

	public void rotateCClockwise() {
		this.ROTATE = 1;
	}
	
	public void stopRotation() {
		this.ROTATE = 0;
	}
	
}
