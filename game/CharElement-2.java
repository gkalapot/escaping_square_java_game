package game;

/**
 * Class that defines our character element. In my game, it is a square. This
 * is the element that the user has control over and can move around on the
 * screen. In extends the Polygon class and makes a lot of use of the methods 
 * and instance variables in Polygon. It updates the position of the square
 * and also implements Element and KeyListener. Element is implemented because
 * it has a move method, and KeyListener is implemented to incorporate 
 * keyboard responsiveness.
 * 
 */

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CharElement extends Polygon implements KeyListener, Element {

	private int lengthOfShape;
	// setting my forward, back, left, and right values to false
	private boolean foward = false, back = false, left = false, right = false;
	// setting up the x and y arrays for my points
	int[] xPoints = new int[lengthOfShape];
	int[] yPoints = new int[lengthOfShape];
	// the square will move in increments of 2
	private int amountToMove = 2;
	private double netRotation = 0.0;

	// calling Polygon super constructor and defining the length of the shape
	// array
	public CharElement(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		lengthOfShape = inShape.length;
		this.xPoints = new int[lengthOfShape];
		this.yPoints = new int[lengthOfShape];
	}

	/**
	 * This is the move method for our square. If we click the up button, we
	 * change the x and y position of the shape to make it go forward. With the 
	 * down button, our shape moves backwards. If we click the right arrow, we 
	 * rotate the shape by 1 degree to the right. If we click the left arrow,
	 * we rotate the shape by 1 degree to the left. We update our current 
	 * position by getPoints().
	 * 
	 */
	public void move() {
		if (foward == true) {
			position.x = position.x + (amountToMove * Math.cos(Math.toRadians(netRotation)));
			position.y = position.y + (amountToMove * Math.sin(Math.toRadians(netRotation)));
			rotation = 0;
			shape = getPoints();

		} else if (back == true) {
			position.x = position.x - (amountToMove * Math.cos(Math.toRadians(netRotation)));
			position.y = position.y - (amountToMove * Math.sin(Math.toRadians(netRotation)));

			rotation = 0;
			shape = getPoints();
		} else if (right == true) {
			rotate(1);
			netRotation = (netRotation + rotation) % 360;
			shape = getPoints();
		} else if (left == true) {
			rotate(-1);
			netRotation = (netRotation + rotation) % 360;
			shape = getPoints();
		}

		// if our square goes outside the bounds, we make it return to its
		// original position
		if (position.x < 0 || position.x > 770) {
			this.position = new Point(10, 10);
			shape = getPoints();
		}
		if (position.y < 0 || position.y > 570) {
			this.position = new Point(10, 10);
			shape = getPoints();
		}

	}

	/**
	 * This is the paint method for my square. It defines the x and y arrays
	 * and puts in the values from the shape instance in Polygon. Then it uses
	 * brush to draw and fill the square.
	 * 
	 */
	void paint(Graphics brush) {
		for (int i = 0; i < shape.length; i++) {
			xPoints[i] = (int) shape[i].x;
			yPoints[i] = (int) shape[i].y;
		}

		brush.drawPolygon(xPoints, yPoints, lengthOfShape);
		brush.fillPolygon(xPoints, yPoints, lengthOfShape);
	}
	
	/**
	 * CharElement implements the KeyListener interface, which is essential for
	 * keyboard responsiveness on our square. I have boolean values that define
	 * if the user wants to move the square forward, backwards, left, or right
	 * and they are initially set to false. If the user clicks one of the arrow
	 * keys, then the correct value is set to true. When the user releases the
	 * key, the value is set back to false.
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			foward = true;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			right = true;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			left = true;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			back = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			foward = false;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			right = false;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			left = false;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			back = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
