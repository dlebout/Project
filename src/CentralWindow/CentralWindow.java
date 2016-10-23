package CentralWindow;

import java.awt.BorderLayout;


// SPLIT PANE
//https://openclassrooms.com/courses/apprenez-a-programmer-en-java/conteneurs-sliders-et-barres-de-progression#ss_part_2


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Core.Main;
import GestReco.GestureRecognizer;
import Maze.MazeArea;

public class CentralWindow extends JPanel implements ActionListener {

	public boolean actionBoolean;

	public CentralWindow() {
		super();
		init();

	}

	public void init() {
		this.addMouseListener(new  MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

				//System.out.println(arg0.getX());

				if (actionBoolean==true)
				{
					splitHorizontalWindow();
				} else {				
					splitVerticalWindow();
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void splitHorizontalWindow() {

		//CentralWindow mazeArea = new CentralWindow();
		MazeArea mazeArea = new MazeArea();
		CentralWindow centralWindow2 = new CentralWindow();

		this.setLayout(new GridLayout(0,2));
		this.add(mazeArea, BorderLayout.WEST);	
		this.add(centralWindow2, BorderLayout.EAST);

		// BOOLEAN HORZ-VERT SPLIT
		if (this.actionBoolean==true) {
		//mazeArea.actionBoolean = false;
		centralWindow2.actionBoolean = false;
		}
		else {

			//mazeArea.actionBoolean = true;
			centralWindow2.actionBoolean = true;
		}
		
		//COLORS
		mazeArea.setSize(new Dimension(500,500));
		mazeArea.setBackground(aleaColor());
		mazeArea.setOpaque(true);
		centralWindow2.setSize(new Dimension(500,500));
		centralWindow2.setBackground(aleaColor());
		centralWindow2.setOpaque(true);

		System.out.println("split done: horizontal");
		this.setOpaque(true);
		this.revalidate();
		this.repaint();
	}


	public void splitVerticalWindow() {

		CentralWindow mazeArea = new CentralWindow();
		CentralWindow centralWindow2 = new CentralWindow();

		this.setLayout(new GridLayout(2,0));
		this.add(mazeArea, BorderLayout.NORTH);	
		this.add(centralWindow2, BorderLayout.SOUTH);


		// BOOLEAN HORZ-VERT SPLIT
		if (this.actionBoolean==true) {
		mazeArea.actionBoolean = false;
		mazeArea.actionBoolean = false;
		}
		else {

			mazeArea.actionBoolean = true;
			mazeArea.actionBoolean = true;
		}
		
		//COLORS
		mazeArea.setSize(new Dimension(500,500));
		mazeArea.setBackground(Color.white);
		mazeArea.setOpaque(true);
		centralWindow2.setSize(new Dimension(500,500));
		centralWindow2.setBackground(aleaColor());
		centralWindow2.setOpaque(true);

		System.out.println("split done: vertical");
		this.setOpaque(true);
		this.revalidate();
		this.repaint();
	}
	
	public Color aleaColor() {

		int colorScalar1= (int) (Math.random()*256);
		int colorScalar2= (int) (Math.random()*256);
		int colorScalar3= (int) (Math.random()*256);
		Color greyColor = new Color(colorScalar1,colorScalar2,colorScalar3);
		return greyColor;
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(800,600));
		frame.setMinimumSize(new Dimension(800,600));
		frame.setVisible(true);
		frame.setContentPane(new CentralWindow());
		GestureRecognizer gr = new GestureRecognizer();
	}

}