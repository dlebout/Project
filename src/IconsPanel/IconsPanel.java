package IconsPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class IconsPanel extends JPanel {
	
	private ArrayList<String> listIcons = new ArrayList();

	private GridBagLayout gridBag = new GridBagLayout();
	private GridBagConstraints cons = new GridBagConstraints();
	
	public IconsPanel() {
		super();
		this.setVisible(true);

		this.setLayout(gridBag);
	}
	
	public void addIcon(String strIcon) {		
		if (! listIcons.contains(strIcon)) {
			listIcons.add(strIcon);		
		} else {
			JDialog dialog = new JDialog();
			dialog.setTitle("Warning");
			dialog.add(new JLabel("icon name already existing"));
			dialog.setVisible(true);
			// A REFAIRE
		}
		updateIconButtons();		
	}
	
	public void deleteIcon(String strIcon) {		
		if (listIcons.contains(strIcon)) {
			listIcons.remove(strIcon);		
		}
		updateIconButtons();
	}
	
	public void updateIconButtons() {		
		this.removeAll();
		for (int i=0; i<listIcons.size(); i++) {
			IconButton iconButton = new IconButton();
			iconButton.setIconString(listIcons.get(i));
			iconButton.setText(listIcons.get(i));
			//this.add(iconButton);				
			cons.gridx = i;
			cons.gridy = 0;
			this.add(iconButton, cons);
		}		
	}
	
}
