package PieMenuDessin;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import Core.Main;


public class PieMenuMouseDrag extends MouseMotionAdapter{
	
	Main main;
	
	public PieMenuMouseDrag(Main m){
		main = m;
	}
	
	public void mouseDragged(MouseEvent evt) {
		main.getPie().collision(evt.getX(),evt.getY());
		main.repaint();
	}
}
