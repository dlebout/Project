
package Maze;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import TUIO.TuioClient;

import com.illposed.osc.OSCPortIn;

public class MazeArea extends JPanel {

	Graphics2D g2;
	private Perso personnage; 
	private int[] newPos = new int[2];
	private int[] oldPos = new int[2];				
	private float xSpeed, ySpeed;
	private Image image;
	private int radius=41;
	private Ellipse2D persoEllipse;
	
	// environment
	private ArrayList<Rectangle> listObjects = new ArrayList<>();	

	// TUIO communication
	private OSCPortIn receiver;
	private TuioClient listener; 	


	public MazeArea() {
		super();
		
		init();
	}

	public void init() {
		
		this.setSize(new Dimension(500,500));
		this.setPreferredSize(new Dimension(400,400));
		this.setSize(400,400);	
		this.setBackground(Color.white);
		this.setOpaque(true);
		
		newPos[0] = 100;
		newPos[1] = 100;
		oldPos[0] = 100;
		oldPos[1] = 100;
		personnage = new Perso(radius,newPos[0],newPos[1]);
		persoEllipse = new Ellipse2D.Double(newPos[0], newPos[1], radius, radius);		

		Rectangle rect0 = new Rectangle(200,200,13,13);
		Rectangle rect1 = new Rectangle(50,250,13,13);
		listObjects.add(rect0);
		listObjects.add(rect1);

		try {
			BufferedImage buffImage = ImageIO.read(new File("ball.png"));
			image = buffImage;
		} catch(IOException e) {	
			System.out.println("image not caught");
		}


		// TUIO
		try  {
			receiver = new OSCPortIn(3333);
			listener = new TuioClient();
			receiver.addListener("/tuio/2Dcur", listener);
			receiver.startListening();
		} catch(SocketException e) {
			e.printStackTrace();
		}


		// thread for mouse
		Thread threadMouse = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					Point mousePos = MouseInfo.getPointerInfo().getLocation();
					
					// TUIO param
					xSpeed = (float) (renorm(listener.locationZ) * getWidth());
					ySpeed = (float) (renorm(listener.locationX) * getHeight());

					int xPerso = (int) personnage.getX();
					int yPerso = (int) personnage.getY();					
					newPos = personnage.updatePos(xSpeed, ySpeed);
					persoEllipse.setFrame(new Rectangle(newPos[0], newPos[1], radius, radius));


					if (newPos[0]<0) {
						newPos[0]=0;
					}
					if (newPos[0]+radius>getWidth()) {
						newPos[0] = getWidth()-radius;
					}
					if (newPos[1]<0 ) {
						newPos[1] = 0;
					}
					if (newPos[1]+radius>getWidth()) {
						newPos[1] = getWidth()-radius;
					}
					for (Object obj:listObjects) {
						if (persoEllipse.intersects((Rectangle2D) obj)) {
							newPos[0] = oldPos[0];
							newPos[1] = oldPos[1];
							personnage.setPos(newPos[0], newPos[1]);
						}
					}
					personnage.setPos(newPos[0], newPos[1]);

					Rectangle clearArea = new Rectangle(Math.min(oldPos[0],newPos[0])-10, 
							Math.min(oldPos[1],newPos[1])-10,
							Math.abs(oldPos[0]-newPos[0])+radius+20, 
							Math.abs(oldPos[1]-newPos[1])+radius+20);
					repaint(clearArea);

					try {
						Thread.sleep(35);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		threadMouse.start();
	}


	private float renorm(float x) {
		int alpha = 4;
		int power = 5;
		double norm = 2;
		int beta = 8;
		float res = (float) (alpha*Math.pow(x/norm, power)) / beta;		
		return res;
	}
	
	public void paint(Graphics g) {
		g2 = (Graphics2D) g;		
		
		// background
		g2.setColor(Color.white);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		// ball
		g.drawImage(image,newPos[0]-4,newPos[1]-4, this);
		oldPos[0] = newPos[0];
		oldPos[1] = newPos[1];	
		
		// environment
		g2.setColor(Color.black);
		for (Object obj:listObjects) {
			g2.fill((Shape) obj);
		}	
	}

	public void paintComponent(Graphics g)
	{
		g2 = (Graphics2D) g;
		
		// background
		g2.setBackground(Color.white);		

		// ball
		g.drawImage(image,newPos[0]-4,newPos[1]-4, this);
		oldPos[0] = newPos[0];
		oldPos[1] = newPos[1];		
		
		// environment
		g2.setColor(Color.black);
		for (Object obj:listObjects) {
			g2.fill((Shape) obj);
		}
	}
}

