package Core;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import IconsPanel.IconsPanel;

public class mainWindow extends JFrame {

	/// MENU
	JMenuBar menuBar = new JMenuBar();
	JMenu menuFile = new JMenu("File");
	JMenu menuView = new JMenu("View");
	JMenuItem menuItemImport = new JMenuItem("Import");
	JMenuItem menuItemDelete = new JMenuItem("Delete");
	JMenuItem menuItemQuit = new JMenuItem("Quit");
	JMenuItem menuItemPhotoViewer = new JMenuItem("Photo viewer");
	JMenuItem menuItemBrowser = new JMenuItem("Browser");
	JMenuItem menuItemSplitMode = new JMenuItem("Split Mode");


	/// MAIN central windowI
	IconsPanel iconsPanel = new IconsPanel();
	JPanel centralWindow = new JPanel();
	JPanel logPanel = new JPanel();
	


	/// CONSTRUCTOR	
	public mainWindow() {

		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		init();

		this.setJMenuBar(menuBar);	
		this.setLayout(new BorderLayout());
		this.getContentPane().add(iconsPanel, BorderLayout.NORTH);	
		this.getContentPane().add(centralWindow, BorderLayout.CENTER);
		this.getContentPane().add(logPanel, BorderLayout.SOUTH);
		this.setSize(800,500);
		this.setMinimumSize(new Dimension(400,600));
	}


	private void init() {


		iconsPanel.setForeground(Color.BLUE);
		iconsPanel.setBackground(Color.WHITE);
		iconsPanel.setOpaque(true);
		//iconsPanel.setText("iconsPanel");
		iconsPanel.setPreferredSize(new Dimension(200,50));

		String hey = "hey";
		iconsPanel.addIcon(hey);

		String hey2 = "hey";
		iconsPanel.addIcon(hey2);
		// probleme : arrive au même endroit que le precedent
		
		//centralWindow.setText("central window");
		centralWindow.setBackground(new Color(222,222,222));
		centralWindow.setOpaque(true);

		logPanel.setForeground(Color.RED);
		logPanel.setBackground(Color.WHITE);
		logPanel.setOpaque(true);
		//logPanel.setText("logPanel");
		logPanel.setPreferredSize(new Dimension(200,75));

		menuFile.add(menuItemImport);
		menuFile.add(menuItemDelete);
		menuFile.add(menuItemQuit);

		menuView.add(menuItemPhotoViewer);
		menuView.add(menuItemBrowser);
		menuView.add(menuItemSplitMode);		

		menuBar.add(menuFile);
		menuBar.add(menuView);

	}

}
