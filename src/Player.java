import java.awt.Graphics2D;

public class Player {

	private int x;
	private int y;
	private double theta;
	private boolean MOVE_LEFT;
	private boolean MOVE_RIGHT;
	private boolean MOVE_UP;
	private boolean MOVE_DOWN;
	private int speed = 3;
	
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
	
	public void drawRays(Graphics2D g2) {
		for (int angle = -45; angle <= 45; angle+= 1) {
			g2.drawLine(this.x, this.y, this.x + (int)(100*Math.cos(this.theta + Math.toRadians(angle))), this.y + (int)(100*Math.sin(this.theta + Math.toRadians(angle))));
		}
	}
}
