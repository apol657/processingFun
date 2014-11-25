package colors;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

public class Core extends PApplet {

	private static Core instance;
	private static final long serialVersionUID = 1L;
	private int width = 1400, height = 700;
	private int fps = 120;
	private long t;
	private KeyManager keys;
	private Random rng;

	private Circle circle;

	private ArrayList<Circle> circles;
	private ArrayList<Circle> deadCircles;

	public void setup() {
		instance = this;
		size(width, height);
		background(222);
		noStroke();
		t = millis();

		rng = new Random();
		circle = new Circle(50, 50, 25, 25);
		keys = new KeyManager();
		circles = new ArrayList<Circle>();
		deadCircles = new ArrayList<Circle>();
	}

	public void update(long dt) {
		boolean update = false;
		if (dt > 1000 / fps) {
			update = true;
			t = millis();
		}
		if (update) {
//			circles.add(new Circle(rng.nextInt(width - 50) + 25, rng
//					.nextInt(height - 50) + 25, 25, 25,
//					(rng.nextFloat() / 10) - 1f / 20, (rng.nextFloat() / 2),
//					true));

			circles.add(new Circle(rng.nextInt(width - 50) + 25, rng
					.nextInt(height - 50) + 25-100, 25, 25,
					((rng.nextFloat() / 10) - 1f / 20)*2, ((rng.nextFloat() / 2))*2,
					true));
		}
		circle.update(dt);
		for (Circle c : circles) {
			c.update(dt);
			if (c.getX() < -c.getWidth() || c.getX() > width + c.getWidth()
					|| /*c.getY() < -c.getHeight()
					||*/ c.getY() > height + c.getHeight()) {
				deadCircles.add(c);
			}
		}
		for (Circle dc : deadCircles) {
			circles.remove(dc);
		}
		deadCircles.clear();

	}

	public void draw() {
		long dt = millis() - t;
		t = millis();
		update(dt);

		background(50);
		circle.draw();
		for (Circle c : circles) {
			c.draw();
		}
	}

	public void mousePressed() {
	}

	public void mouseReleased() {
	}

	public void keyPressed() {
		keys.set(keyCode, true);
	}

	public void keyReleased() {
		keys.set(keyCode, false);
	}

	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "colors.Core" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}

	public static Core getInstance() {
		return instance;
	}

	public KeyManager getKeys() {
		return keys;
	}

	public Random getRng() {
		return rng;
	}
}