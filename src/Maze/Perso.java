package Maze;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Perso {

	private double xPos;
	private double yPos;
	private double xSpeed;
	private double ySpeed;	
	private double maxSpeed;
	private double[] newSpeed;
	private int[] newPos;
	private final int radius;
	private final double coef;
	
	public Perso(int rad, int initXPos, int initYPos) {
		this.radius = rad;
		setX(initXPos);
		setY(initYPos);
		coef = 0.05;
		maxSpeed = 10;
		newSpeed = new double[2];
	}

	public int getRadius() {
		return this.radius;
	}
	public double getX() {
		return xPos;
	}
	public double getY() {
		return yPos;
	}
	private void setX(double x) {
		this.xPos = x;
	}
	private void setY(double y) {
		this.yPos = y;
	}
	
	public int[] updatePos(double dx, double dy) {
		this.xSpeed = coef * dx;
		this.ySpeed = coef * dy;
		newSpeed = checkMaxSpeed(xSpeed, ySpeed);	
		this.setX(this.getX() + newSpeed[0]);
		this.setY(this.getY() + newSpeed[1]);		
		newPos = new int[2];
		newPos[0] = (int) this.getX();
		newPos[1] = (int) this.getY();
		return newPos;
	}
	
	private double[] checkMaxSpeed(double xSpeed2, double ySpeed2) {
		double absoluteSpeed = Math.sqrt((Math.pow(xSpeed2, 2.0) + Math.pow(xSpeed2, 2.0)));
		double[] newSpeed = new double[2];


		if (absoluteSpeed > maxSpeed) {
			newSpeed[0] = (xSpeed2*(maxSpeed/absoluteSpeed));
			newSpeed[1] = (ySpeed2*(maxSpeed/absoluteSpeed));
		} else {
			newSpeed[0] = xSpeed2;
			newSpeed[1] = ySpeed2;
		}
		return newSpeed;
	}
	
	public void setPos(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
}



