package game;

/**
 * Class that represents the triangles, which are the obstacles that move
 * up and down. This class extends Polygon and implements Element. It is very
 * similar to the HorizontalObstacle class, however, this class has a different
 * move method that relies on the y components of the triangle obstacles.
 * 
 */

import java.awt.Graphics;

public class ObstaclesElement extends Polygon implements Element {
	// setting my x and y arrays
	public int[] xPoints = new int[shape.length];
	public int[] yPoints = new int[shape.length];
	// making the triangles move by increments of 2
	public int amountToMove = 2;
	private int direction = 1;

	// calling the super constructor in Polygon
	public ObstaclesElement(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}

	/**
	 * This is the paint method for the triangle obstacles. I set my x and y arrays
	 * with the values from the shape object. Then I draw and fill the shape.
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
	 * This move method defines how the triangles will move. This method makes the
	 * triangles go up and down the screen. As long as the triangle is inside the
	 * frame of the screen, it will keep going up. Once the triangle goes outside
	 * the y bounds, then it reverses direction and goes the opposite way.
	 * 
	 */
	public void move() {
		double nextIncrement = position.y + amountToMove * direction;

		if (nextIncrement >= 0 && nextIncrement <= 570) {
			position.y = nextIncrement;
		} else {
			direction = direction * (-1);
		}
		rotation = 0;
		shape = getPoints();
	}

}
