package game;

/**
 * Class that represents the rectangles, which are the obstacles that move
 * horizontally. This class extends Polygon, and uses the Polygon's shape
 * instance variables and getPoints() to update the positions of the 
 * rectangles. This class also implements Element because it contains a move 
 * method. The rectangles move without any input from the user, so this class
 * does not need keyboard responsiveness.
 * 
 */

import java.awt.Graphics;

public class HorizontalObstacle extends Polygon implements Element {
	// define the x and y arrays for my points
	public int[] xPoints = new int[shape.length];
	public int[] yPoints = new int[shape.length];
	// the rectangles move faster than the triangles, at an increment of 3
	public int amountToMove = 3;
	// they start by going right to left
	private int direction = -1;

	// calling the Polygon super constructor
	public HorizontalObstacle(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}

	/**
	 * This is the paint method for the rectangle obstacles. I set my x and 
	 * y arrays with the values from the shape object. Then I draw and fill the 
	 * shape.
	 * 
	 */
	void paint(Graphics brush) {
		for (int i = 0; i < shape.length; i++) {
			xPoints[i] = (int) shape[i].x;
			yPoints[i] = (int) shape[i].y;
		}
		brush.drawPolygon(xPoints, yPoints, shape.length);
		brush.fillPolygon(xPoints, yPoints, shape.length);
	}

	/**
	 * This is the move method for my rectangle obstacles. As long as the 
	 * rectangles are inside the bounds of x, then the rectangle keeps moving.
	 * However, once the rectangle is going to be outside the bounds, then the
	 * direction reverses and it starts to travel the opposite way.
	 * 
	 */
	public void move() {
		double nextIncrement = position.x + amountToMove * direction;

		if (nextIncrement >= 0 && nextIncrement <= 800) {
			position.x = nextIncrement;
		} else {
			direction = direction * (-1);
		}
		rotation = 0;
		shape = getPoints();
	}

}
