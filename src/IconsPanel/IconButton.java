package IconsPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class IconButton extends JButton{
	
	private String stringIcon;

	public IconButton() {		
		super();
		
		this.setBackground(Color.RED);
		
		Dimension dimButton = new Dimension(45,45);
		this.setPreferredSize(dimButton);
		this.setSize(dimButton);		
	}
	
	public void setIconString(String str) {		
		this.stringIcon = str;	
		updateIcon();
	}
	
	public void updateIcon() {
		// se servur de stringIcon pour actualiser l'icone sur le bouton
	}
	
}
