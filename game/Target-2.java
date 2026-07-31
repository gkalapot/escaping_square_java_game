package game;

/**
 * Class that represents the target square that the user needs to get the
 * character element square inside of. It extends the Polygon class, but does
 * not implement the Element interface because this is a stationary object
 * with no move method.
 * 
 */

import java.awt.Graphics;

public class Target extends Polygon{
	// define my x and y arrays 
	public int[] xPoints=new int[shape.length];
	public int[] yPoints=new int[shape.length];

	// call my super constructor to set the shape, center, and rotation in Polygon
	public Target(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}
	
	// paint method overridden by anonymous class
	void paint(Graphics brush) {
		
	}

}
