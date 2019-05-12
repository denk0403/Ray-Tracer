import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

public class FirstPerson extends JComponent {

	RayTracer rt;

	public FirstPerson(RayTracer rt) {
		this.rt = rt;
		this.initComponents();
	}

	private void initComponents() {
		
		JFrame frame = new JFrame("Ray Tracer First-Person");
		frame.setSize(500, 500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.BLACK);
		//////////////////////
		
		this.setPreferredSize(new Dimension(500, 500));
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		
		Timer timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FirstPerson.this.repaint();
			}
		});
		timer.start();
		
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == 'w') {
					rt.p.moveForward(false);
				}
				if (e.getKeyChar() == 'a') {
					rt.p.moveLeftForward(false);
				}
				if (e.getKeyChar() == 's') {
					rt.p.moveBackward(false);
				}
				if (e.getKeyChar() == 'd') {
					rt.p.moveRightForward(false);
				}
				rt.repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyChar());
				if (e.getKeyChar() == 'w') {
					rt.p.moveForward(true);
					rt.p.moveBackward(false);
				}
				if (e.getKeyChar() == 'a') {
					rt.p.moveLeftForward(true);
					rt.p.moveRightForward(false);
				}
				if (e.getKeyChar() == 's') {
					rt.p.moveBackward(true);
					rt.p.moveForward(false);
				}
				if (e.getKeyChar() == 'd') {
					rt.p.moveRightForward(true);
					rt.p.moveLeftForward(false);
				}
				rt.repaint();
			}
		});

		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				if (e.getX() > 3 * FirstPerson.this.getWidth() / 4) {
					rt.p.rotateClockwise();
					rt.repaint();
				}
				if (e.getX() < FirstPerson.this.getWidth() / 4) {
					rt.p.rotateCClockwise();
					rt.repaint();
				}
				if (e.getX() > FirstPerson.this.getWidth() / 4 && e.getX() < 3 * FirstPerson.this.getWidth() / 4) {
					rt.p.stopRotation();
					rt.repaint();
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				rt.p.stopRotation();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		////////////////////
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.translate(this.getWidth()/2, this.getHeight()/2);
		g2.scale(1, -1);
		List<Point> points = rt.p.getIntersectionPoints(rt.barriers);
		for (int index = 0; index < points.size(); index += 1) {
			Point p = points.get(points.size()-index-1);
			if (p != null) {
				double distance = new Point(rt.p.getX(), rt.p.getY()).distance(p);
				if (distance < 200) {
					int colorNum = 200 - (int) ((distance * distance) / 200);
					g2.setColor(new Color(colorNum, colorNum, colorNum));
					int height = (int)(16.5*Math.sqrt(500 - (int)(500 * distance/200.0)));
					g2.fillRect(index * this.getWidth()/points.size() - this.getWidth()/2, -this.getHeight()/4, this.getWidth()/points.size(), height);
				}
				
			}
			
		}
	}
}
