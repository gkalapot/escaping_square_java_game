package game;

/*
CLASS: YourGameNameoids

DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
/**
 * Class that represents the control center of my game. It defines the objects
 * that I will use in my game, such as the character element, square, the
 * 2 different types of obstacles, and the target square. I define 1 charElement
 * square, 7 triangle obstacles, 4 horizontal rectangle obstacles, and 1
 * target square. This class contains 1 lambda expression, 1 anonymous class,
 * and 2 inner classes that are responsible for setting points, overriding
 * the Target paint method, and creating the welcome and end screens.
 * 
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class EscapingSquare extends Game {
	// no collisions in the beginning of the game
	boolean oldCollision = false;
	// user has initial score of 0
	public static int score = 0;
	// user has initially 3 lives
	public static int lives = 3;
	
	private boolean enterKey = false;
	Random rand = new Random();

	// I define some points I will use to create my shapes
	Point a = new Point(0, 0);
	Point b = new Point(20, 0);
	Point c = new Point(20, 20);
	Point x = new Point(30, 30);
	Point y = new Point(60, 30);
	Point z = new Point(45, 0);
	Point d = new Point(0, 20);
	Point e = new Point(10, 20);
	Point f = new Point(20, 10);
	Point g = new Point(0, 10);

	Point q = new Point(40, 0);
	Point r = new Point(40, 40);
	Point s = new Point(0, 40);

	// shape1 is my escaping square
	Point[] shape1 = { a, b, c, d };

	// the triangle and rectangle will be the obstacles
	Point[] triangle = { y, x, z };
	Point[] rectangle = { a, b, f, g };

	// the target square is where I want to get my escaping square to
	Point[] targetSquare = { a, q, r, s };

	/**
	 * This is my lambda expression that makes it easier to define points for my
	 * obstacles. I want them to be randomly generated for their vertical location,
	 * however, they should not be going through my target square.
	 * 
	 */
	PointGenerator p1 = (x) -> {
		int num = rand.nextInt(570);
		while (num > 490 && num < 600) {
			num = rand.nextInt(570);
		}
		return new Point(x, num);
	};

	// I define my square element, centered at (10,20) with no initial rotation
	CharElement ab = new CharElement(shape1, e, 0.0);
	// I define 7 triangle obstacles, all starting from different positions
	ObstaclesElement bc1 = new ObstaclesElement(triangle, p1.pointCreator(100), 0.0);
	ObstaclesElement bc2 = new ObstaclesElement(triangle, p1.pointCreator(200), 0.0);
	ObstaclesElement bc3 = new ObstaclesElement(triangle, p1.pointCreator(300), 0.0);
	ObstaclesElement bc4 = new ObstaclesElement(triangle, p1.pointCreator(400), 0.0);
	ObstaclesElement bc5 = new ObstaclesElement(triangle, p1.pointCreator(500), 0.0);
	ObstaclesElement bc6 = new ObstaclesElement(triangle, p1.pointCreator(600), 0.0);
	ObstaclesElement bc7 = new ObstaclesElement(triangle, p1.pointCreator(700), 0.0);
	// I define 4 rectangle obstacles, all starting from different positions
	HorizontalObstacle ho1 = new HorizontalObstacle(rectangle, p1.pointCreator(600), 0.0);
	HorizontalObstacle ho2 = new HorizontalObstacle(rectangle, p1.pointCreator(700), 0.0);
	HorizontalObstacle ho3 = new HorizontalObstacle(rectangle, p1.pointCreator(700), 0.0);
	HorizontalObstacle ho4 = new HorizontalObstacle(rectangle, p1.pointCreator(600), 0.0);

	/**
	 * This is an anonymous class that is of type target. It defines the target
	 * square then sets the x and y points in the corresponding arrays and uses the
	 * drawPolygon command to draw an unfilled red square at the bottom right of the
	 * screen.
	 * 
	 */
	Target t1 = new Target(targetSquare, new Point(750, 520), 0.0) {
		@Override
		void paint(Graphics brush) {
			shape = getPoints();
			brush.setColor(Color.red);
			for (int i = 0; i < shape.length; i++) {
				xPoints[i] = (int) shape[i].x;
				yPoints[i] = (int) shape[i].y;
			}
			brush.drawPolygon(xPoints, yPoints, shape.length);
		}
	};
	// creating an instance of my welcome screen using inner class structure
	EscapingSquare.WelcomeScreen w1 = new EscapingSquare.WelcomeScreen();

	// putting all my elements in a Polygon array
	Polygon[] elements = { ab, ho1, ho2, ho3, ho4, bc7, bc1, bc2, bc3, bc4, bc5, bc6, t1 };

	/**
	 * This constructor sets the dimensions of the screen and also focuses the
	 * KeyListener interface on my square and welcome screen objects, which are the
	 * objects that should respond to pressing the arrows.
	 * 
	 */
	public EscapingSquare() {
		super("TheEscapingSquare!", 800, 600);

		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(ab);
		this.addKeyListener(w1);

	}

	/**
	 * This is my paint method that sets the initial black screen. First, the
	 * enter key is set to false by default, so we call our welcome screen inner
	 * class to set the screen. When the user presses enter, we are introduced
	 * to the game. The score and amount of lives is shown at the top. Every
	 * time that the user's square collides with one of the obstacles, they
	 * lose a life. If the user manages to get to the target square without
	 * losing all three lives, the winning screen is displayed. Otherwise, if 
	 * the lives go down to 0, the losing screen is displayed.
	 * 
	 */
	public void paint(Graphics brush) {
		brush.setColor(Color.black);
		brush.fillRect(0, 0, width, height);
		if (w1.enterKey == false) {
			EscapingSquare.WelcomeScreen.setWelcomeScreen(brush);
		} else {
			brush.setColor(Color.black);
			brush.fillRect(0, 800, 0, 600);

			brush.setColor(Color.white);
			brush.drawString("Your score: " + score, 10, 10);
			brush.drawString("Lives: " + lives, 100, 10);
			brush.setColor(Color.red);
			t1.paint(brush);

			for (int i = 0; i < elements.length - 1; i++) {
				// if the element is the square, make it red
				if (elements[i] instanceof CharElement) {
					brush.setColor(Color.red);
				} else if (elements[i] instanceof ObstaclesElement) {
					// if the element is the triangle obstacle, make it green
					brush.setColor(Color.green);
				} else {
					// if the element is the rectangle obstacle, make it blue
					brush.setColor(Color.blue);
				}
				// move each element
				elements[i].move();

				// make an instance asking if our square has collided with any
				// of the obstacles
				boolean collision = ab.collides(bc1) || ab.collides(bc2) 
						|| ab.collides(bc3) || ab.collides(bc4) || ab.collides(bc5) 
						|| ab.collides(bc6) || ab.collides(bc7)
						|| ab.collides(ho1) || ab.collides(ho2) 
						|| ab.collides(ho3) || ab.collides(ho4);
				
				// if the previous collision was false, and then a collision happens
				// and our current collision is true, decrease lives by 1
				if (!oldCollision && collision) {
					lives--;
				}
				// update the old collision to be the same as the current one 
				oldCollision = collision;
				// if our squares makes it to the target square dimensions,
				// our score is set to 100 and we have won
				if (ab.position.x > 730 && ab.position.x < 800 
						&& ab.position.y < 600 && ab.position.y > 520) {
					score = 100;
				}

				// if our score is 100, it means we won and we can now set the
				// winning screen
				if (score == 100) {
					EscapingSquare.EndScreen.setWinningEndScreen(brush, lives);
				}
				// if our lives have reached 0, we set the lost screen
				if (lives <= 0) {
					EscapingSquare.EndScreen.setLosingEndScreen(brush);
				}

				// call paint on all elements
				elements[i].paint(brush);
			}
		}

	}

	/**
	 * This is an inner class that sets the welcome screen. It implements the
	 * KeyListener interface because we want the welcome screen to vanish after
	 * the user presses enter. This is a static class, so we do not need an 
	 * outer class object to use it. We also have a method that sets the welcome
	 * screen, setWelcomeScreen(Graphics brush) that sets the font, color,
	 * and size of the text.
	 * 
	 */
	public static class WelcomeScreen implements KeyListener {

		public boolean enterKey;

		@Override
		public void keyTyped(KeyEvent e) {

		}

		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_ENTER) {
				enterKey = true;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

		public static void setWelcomeScreen(Graphics brush) {
			Font defaultFont = brush.getFont();

			Font font = new Font("Arial", Font.BOLD, 30);
			Font font2 = new Font("Arial", Font.BOLD, 24);
			brush.setFont(font);
			brush.setColor(Color.cyan);
			brush.drawString("WELCOME", 300, 250);
			brush.setFont(font2);
			brush.drawString("Press Enter To Start", 270, 300);
			brush.setFont(defaultFont.deriveFont(Font.PLAIN));
		}

	}

	/**
	 * This is an inner class that sets the end screen. This is a static class, 
	 * so we do not need an outer class object to use it. This class has 2
	 * versions of the end screen. We have a winning screen and a losing screen.
	 * One displays the lost message and the other displays the win message. 
	 * Both display the score and remaining lives.
	 * 
	 */
	public static class EndScreen {
		public static void setWinningEndScreen(Graphics brush, int lives) {
			brush.setColor(Color.black);
			brush.fillRect(0, 0, 800, 600);
			Font defaultFont = brush.getFont();

			Font font = new Font("Arial", Font.BOLD, 30);
			Font font2 = new Font("Arial", Font.BOLD, 24);
			brush.setFont(font);
			brush.setColor(Color.cyan);
			brush.drawString("YOU WON", 300, 250);
			brush.setFont(font2);
			brush.drawString("Score: 100", 270, 300);
			brush.drawString("Remaining Lives: " + lives, 270, 350);
			brush.setFont(defaultFont.deriveFont(Font.PLAIN));
		}

		public static void setLosingEndScreen(Graphics brush) {
			brush.setColor(Color.black);
			brush.fillRect(0, 0, 800, 600);
			Font defaultFont = brush.getFont();

			Font font = new Font("Arial", Font.BOLD, 30);
			Font font2 = new Font("Arial", Font.BOLD, 24);
			brush.setFont(font);
			brush.setColor(Color.cyan);
			brush.drawString("You Lost :(", 300, 250);
			brush.setFont(font2);
			brush.drawString("Score: 0", 270, 300);
			brush.drawString("Remaining Lives: 0", 270, 350);
			brush.setFont(defaultFont.deriveFont(Font.PLAIN));
		}
	}

	/**
	 * Main method. We make an object of our outer class, and call repaint().
	 * 
	 */
	public static void main(String[] args) {
		EscapingSquare a = new EscapingSquare();

		a.repaint();

	}

}