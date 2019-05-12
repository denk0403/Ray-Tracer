import java.awt.Point;

public class Ray {

	int x;
	int y;
	double theta;
	
	public Ray(int x, int y, double theta) {
		this.x = x;
		this.y = y;
		this.theta = theta;
	}
	
	public Point cast(Barrier b) {
		double x1 = b.getPoint1().x;
		double y1 = b.getPoint1().y;
		double x2 = b.getPoint2().x;
		double y2 = b.getPoint2().y;
		
		double x3 = this.x;
		double y3 = this.y;
		double x4 = this.x + Math.cos(this.theta);
		double y4 = this.y + Math.sin(this.theta);
		
		double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
	    if (Math.abs(den) <= 0.00000001) {
		      return null;
		}
	    
	    double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
	    double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
	    
	    if (t > 0 && t < 1 && u > 0) {
		      Point pt = new Point();
		      pt.x = (int)(x1 + t * (x2 - x1));
		      pt.y = (int) (y1 + t * (y2 - y1));
		      return pt;
		} else {
		      return null;
		    }
		}
	
	/*
	  cast(wall) {
		    const x1 = wall.a.x;
		    const y1 = wall.a.y;
		    const x2 = wall.b.x;
		    const y2 = wall.b.y;

		    const x3 = this.pos.x;
		    const y3 = this.pos.y;
		    const x4 = this.pos.x + this.dir.x;
		    const y4 = this.pos.y + this.dir.y;

		    const den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
		    if (den == 0) {
		      return;
		    }

		    const t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
		    const u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
		    if (t > 0 && t < 1 && u > 0) {
		      const pt = createVector();
		      pt.x = x1 + t * (x2 - x1);
		      pt.y = y1 + t * (y2 - y1);
		      return pt;
		    } else {
		      return;
		    }
		  }
		  */

}
