import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RayTracer extends JPanel {

	Player p;
	ArrayList<Barrier> barriers = new ArrayList<>();

	public RayTracer() {
		this.p = new Player(0, 0);
		this.initComponents();
	}

	private void initComponents() {

		JFrame frame = new JFrame("Ray Tracer");
		frame.setSize(200, 200);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		////////////////////////////////

		this.setSize(new Dimension(200, 200));
		Timer timer = new Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p.move();
				RayTracer.this.repaint();
			}
		});
		timer.start();

		frame.setBackground(Color.BLACK);
		this.setOpaque(true);
		this.setBackground(Color.BLACK);

		this.addMouseListener(new MouseListener() {

			boolean hasOnePoint = false;
			int lastX = 0;
			int lastY = 0;

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (!hasOnePoint) {
					this.lastX = e.getX() - RayTracer.this.getWidth() / 2;
					this.lastY = -(e.getY() - RayTracer.this.getHeight() / 2);
					this.hasOnePoint = true;
				} else {
					this.hasOnePoint = false;
					Wall newWall = new Wall(lastX, lastY, e.getX() - RayTracer.this.getWidth() / 2,
							-(e.getY() - RayTracer.this.getHeight() / 2));
					barriers.add(newWall);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				int xDispFromO = e.getX() - RayTracer.this.getWidth() / 2;
				int yDispFromO = -(e.getY() - RayTracer.this.getHeight() / 2);
				p.updateThetaTo(Math.atan2(yDispFromO - p.getY(), xDispFromO - p.getX()));
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == 'w') {
					p.moveUp(false);
				}
				if (e.getKeyChar() == 'a') {
					p.moveLeft(false);
				}
				if (e.getKeyChar() == 's') {
					p.moveDown(false);
				}
				if (e.getKeyChar() == 'd') {
					p.moveRight(false);
				}
				RayTracer.this.repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getKeyChar());
				if (e.getKeyChar() == 'w') {
					p.moveUp(true);
					p.moveDown(false);
				}
				if (e.getKeyChar() == 'a') {
					p.moveLeft(true);
					p.moveRight(false);
				}
				if (e.getKeyChar() == 's') {
					p.moveDown(true);
					p.moveUp(false);
				}
				if (e.getKeyChar() == 'd') {
					p.moveRight(true);
					p.moveLeft(false);
				}
				RayTracer.this.repaint();
			}
		});

		/////////////////////////////////////
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.translate(this.getWidth() / 2, this.getHeight() / 2);
		g2.scale(1, -1);
		g2.setColor(Color.WHITE);
		g2.drawOval(this.p.getX() - 5, this.p.getY() - 5, 10, 10);
		for (Barrier b : barriers) {
			b.drawSelf(g2);
		}
		p.drawRays(g2, barriers);
	}
}
