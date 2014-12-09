package core;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entities.Entity;
import entities.Rectangle;
import processing.core.PApplet;
import util.QuadTree;

public class Core extends PApplet {

	private static Core instance;
	private static final long serialVersionUID = 1L;
	private int width = 800, height = 800;
	private int fps = 120;
	private long t;
	private QuadTree quadTree;
	private ArrayList<Entity> objects;
	private int objectsNum = 1000;
	private Random rng;
	private boolean shouldColor;

	public void setup() {
		instance = this;
		size(width, height);
		t = millis();
		frame.setTitle("QuadTreeDemo");
		rng = new Random(657);
		quadTree = new QuadTree(0, new Rectangle(0, 0, width, height), 10, 10);
		objects = new ArrayList<Entity>();
		for (int i = 0; i < objectsNum; i++) {
			int w = rng.nextInt(width / 16);
			int h = rng.nextInt(height / 16);
			int x = rng.nextInt(width - w);
			int y = rng.nextInt(height - h);
			Rectangle r = new Rectangle(x, y, w, h);
			r.setColor(100);
			objects.add(r);
			quadTree.insert(objects.get(i));
		}

		background(222);
		noStroke();

	}

	public void input() {
		if (mousePressed) {
			shouldColor = true;
		} else {
			shouldColor = false;
		}
	}

	public void update(long dt) {
		boolean update = false;
		if (dt > 1000 / fps) {
			update = true;
			t = millis();
		}
		if (update) {
		}

		for (int i = 0; i < objectsNum; i++) {
			objects.get(i).setColor(100);
		}
		frame.setTitle("QuadTreeDemo");
		if (shouldColor) {
			List<Entity> entities = quadTree.retrieve(mouseX, mouseY);
			// List<Entity> entities = quadTree.retrieve(objects.get(30));
			for (Entity e : entities) {
				e.setColor(250);
			}
			frame.setTitle("QuadTreeDemo: Need to check collision with only "+entities.size()+"/"+(objectsNum)+" objects.");
		}
	}

	public void draw() {
		// update time stuff
		long now = millis();
		long dt = millis() - t;
		t = now;

		// handle input
		input();

		// update logic
		update(dt);

		// draw
		background(50);

		fill(60);
		Entity e;
		for (int i = 0; i < objectsNum; i++) {
			stroke(objects.get(i).getColor());
			e = objects.get(i);
			rect((float) e.getX(), (float) e.getY(), (float) e.getWidth(),
					(float) e.getHeight());

		}

		drawQuadTree(quadTree);
		noStroke();

	}

	private void drawQuadTree(QuadTree qt) {
		stroke(100, 150, 100);
		noFill();
		rect((float) qt.getBounds().getX(), (float) qt.getBounds().getY(),
				(float) qt.getBounds().getWidth(), (float) qt.getBounds()
						.getHeight());
		if (qt.hasChildren()) {
			for (QuadTree qtc : qt.getChildren()) {
				drawQuadTree(qtc);
			}
		}

	}

	public void mousePressed() {

	}

	public void mouseReleased() {
	}

	public void keyPressed() {
	}

	public void keyReleased() {
	}

	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "core.Core" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}

	public static Core getInstance() {
		return instance;
	}
}