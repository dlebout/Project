package PieMenuDessin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Core.Main;


public class PieMenuMouseListener extends MouseAdapter {
	Main main;
	
	
	public PieMenuMouseListener(Main m){
		main = m;
	}
	
	public void mousePressed(MouseEvent evt) {
		 if(evt.getButton() == MouseEvent.BUTTON3){
			 System.out.println("test pressed");
			 main.getPie().setPositionItems(evt.getX(), evt.getY());
			 main.setPie(true);
			 main.repaint();
		 }
	}

	public void mouseReleased(MouseEvent evt) {
		if(evt.getButton() == MouseEvent.BUTTON3){
			System.out.println("test released");
			main.setPie(false);
			main.getPie().reset();
			main.repaint();
		 }
	}

}
