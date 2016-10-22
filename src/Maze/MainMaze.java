package Maze;

import java.awt.Dimension;
import java.net.SocketException;
import java.util.Date;

import javax.swing.JFrame;

import TUIO.TuioClient;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPortIn;

import sun.util.logging.resources.logging;

public class MainMaze {

	/**
	 * @param args
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MazeArea mazeArea = new MazeArea();
		frame.add(mazeArea);
		frame.setVisible(true);
		frame.setPreferredSize(new Dimension(500,500));
		frame.setSize(500,500);
	}
}
