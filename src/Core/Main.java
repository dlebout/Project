package Core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import PieMenuDessin.PieMenu;
import PieMenuDessin.PieMenuMouseDrag;
import PieMenuDessin.PieMenuMouseListener;
import PieMenuDessin.PtrFunc;

public class Main extends JFrame{
	
	protected boolean pieActivated = false;
	protected PieMenu pie;
	
	public Main(){
		super();
		
		setUpPie();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1200,900));
		//this.pack();
		this.setContentPane(new AfficheImage());
		this.getContentPane().setLayout(new BorderLayout()); 
		this.setVisible(true);
		
		this.addMouseListener(new PieMenuMouseListener(this));
		this.addMouseMotionListener(new PieMenuMouseDrag(this));
	}
	
	public void setUpPie(){
		try {
			pie = new PieMenu();
			PieMenu pie1 = new PieMenu();
			PieMenu pie2 = new PieMenu();
			PieMenu pie3 = new PieMenu();
			
			BufferedImage general = ImageIO.read(new File("./icon.png") );
			BufferedImage trait = ImageIO.read(new File("./icon1.png") ); 
			BufferedImage couleur = ImageIO.read(new File("./icon2.png") );
			
			
			pie2.addMenuItem( couleur, null, null);
			pie2.addMenuItem( couleur, null, null);
			pie2.addMenuItem( couleur, null, null);
			
			pie3.addMenuItem( couleur, null, null);
			pie3.addMenuItem( couleur, null, null);
			
			pie1.addMenuItem( trait, pie2, null);
			
			pie.addMenuItem( general, pie1, null);
			pie.addMenuItem( general, pie2, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


	}
	
	public PieMenu getPie(){
		return pie;
	}
	
	public void tryGlassPane(){
	}
	
	public void setPie(boolean b){ 
		pieActivated = b;
	}
	
	public static void main(String[] args){
		Main frame = new Main();
	}
	
	
	class AfficheImage extends JPanel
	{

		AfficheImage(){}

		public void paint(Graphics g)
		{
			super.paintComponents(g);
			if ( pieActivated ){
				pie.drawPieMenu(g);
			}
		}
	} 

}