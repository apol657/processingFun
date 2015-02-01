package core;

import java.util.ArrayList;

import processing.core.PApplet;
import util.QuadTree;
import util.Vector;
import entities.Projectile;
import entities.Rectangle;

public class Core extends PApplet {

	private static Core instance;
	private static final long serialVersionUID = 1L;
	private int width = 1200, height = 700;
	private int fps = 60;
	private long t;

	private Rectangle ball;
	private Vector v;
	private float speed = 0.25f;

	private ArrayList<Projectile> projectiles;
	private ArrayList<Projectile> deadProjectiles;

	private QuadTree qt;
	private float rateOfFire = 10;
	private long fireCooldown;

	private boolean deleteMode = true;

	public void setup() {
		instance = this;
		size(width, height);
		t = millis();
		fireCooldown = millis();
		frame.setTitle("VectorMovementDemo");

		qt = new QuadTree(0, new Rectangle(0, 0, width, height), 2, 5);
		ball = new Rectangle(width / 2, height / 2, 25, 25);
		v = new Vector(2);

		projectiles = new ArrayList<Projectile>();
		deadProjectiles = new ArrayList<Projectile>();

	}

	public void input() {
		v.set(0, mouseX - ball.getCenterX());
		v.set(1, mouseY - ball.getCenterY());
		if (mousePressed && millis() - fireCooldown > 1000 / rateOfFire) {
			Vector v2 = (Vector) v.clone();
			v2.normalize();
			Projectile rect = new Projectile((float) ball.getX()
					+ (float) v2.get(0)*30, (float) ball.getY()
					+ (float) v2.get(1)*30, 25, 25);
			rect.getVector().set(0, v2.get(0) * v.length());
			rect.getVector().set(1, v2.get(1) * v.length());
			projectiles.add(rect);
			fireCooldown = millis();
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
		if (v.length() > 5) {
			Vector v2 = (Vector) v.clone();
			v2.normalize();
			ball.setX(ball.getX() + v2.get(0) * dt * speed * v.length() / 100);
			ball.setY(ball.getY() + v2.get(1) * dt * speed * v.length() / 100);
		}

		qt.clear();
		for (Projectile p : projectiles) {
			if (deleteMode) {
				if (p.getCenterX() > width + p.getWidth() / 2
						|| p.getCenterX() < 0 - p.getWidth() / 2
						|| p.getCenterY() > height + p.getHeight() / 2
						|| p.getCenterY() < 0 - p.getHeight() / 2) {
					deadProjectiles.add(p);
					continue;
				}
			} else {
				if (p.getCenterX() > width - p.getWidth() / 2
						|| p.getCenterX() < 0 + p.getWidth() / 2
						|| p.getCenterY() > height - p.getHeight() / 2
						|| p.getCenterY() < 0 + p.getHeight() / 2) {
					p.getVector().negate();
				}
			}
			p.setX(p.getX() + (float) p.getVector().get(0) * dt * speed / 50);
			p.setY(p.getY() + (float) p.getVector().get(1) * dt * speed / 50);
			qt.insert(p);
		}

		for (Projectile p : projectiles) {
			for (Projectile p2 : qt.retrieve(p)) {
				if (p != p2
						&& Math.sqrt(Math.pow(p.getCenterX() - p2.getCenterX(),
								2)
								+ Math.pow(p.getCenterY() - p2.getCenterY(), 2)) < p
									.getWidth()) {
					// Vector temp = (Vector) p.getVector().clone();
					// p.getVector().add(p2.getVector().normalize().negative());
					// p2.getVector().add(temp.normalize().negative());

					// if(p.getVector().length()>p2.getVector().length()){
					// p.getVector().add(p2.getVector().normalize().negative());
					// p2.getVector().add(temp.normalize());
					// }else{
					// p.getVector().add(p2.getVector().normalize());
					// p2.getVector().add(temp.normalize().negative());
					//
					// }
					// p.getVector().negate();
					// p2.getVector().negate();

					// colision code

					// p.getVector().add(p2.getVector().negative());
					// p2.getVector().add(temp);
					// System.out
					// .println(p.getVector().dotProduct(p2.getVector()));
					// if(p.getVector().dotProduct(p2.getVector())>0){
					// if(p.getVector().length()>p2.getVector().length()){
					// Vector temp = (Vector) p.getVector().clone();
					// p.getVector().add(p2.getVector().negative());
					// p2.getVector().add(temp);
					// }else{
					// Vector temp = (Vector) p2.getVector().clone();
					// p2.getVector().add(p.getVector().negative());
					// p.getVector().add(temp);
					//
					// }
					// }else{
					// p.getVector().negate();
					// p2.getVector().negate();
					// }
//					Vector temp = (Vector) p2.getVector().clone();
					Vector dir = new Vector(2);
					dir.set(0, p.getCenterX() - p2.getCenterX());
					dir.set(1, p.getCenterY() - p2.getCenterY());
					double pLength = p.getVector().length();
					double p2Length = p2.getVector().length();
					p.getVector().add(
							dir.normalized().scalarMultiplied(p2Length));
					p2.getVector().add(
							dir.negative().normalized()
									.scalarMultiplied(pLength));

					do {
						p.setX(p.getX() + (float) p.getVector().get(0) * dt
								* speed / 50);
						p.setY(p.getY() + (float) p.getVector().get(1) * dt
								* speed / 50);

						p2.setX(p2.getX() + (float) p2.getVector().get(0) * dt
								* speed / 100);
						p2.setY(p2.getY() + (float) p2.getVector().get(1) * dt
								* speed / 100);
					} while (Math.sqrt(Math.pow(
							p.getCenterX() - p2.getCenterX(), 2)
							+ Math.pow(p.getCenterY() - p2.getCenterY(), 2)) < p
							.getWidth());

				}
			}
		}
		for (Projectile r : deadProjectiles) {
			projectiles.remove(r);
		}
		deadProjectiles.clear();

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

		drawQuadTree(qt);
		noStroke();
		fill(255);
		ellipse((float) ball.getX() + (float) ball.getWidth() / 2,
				(float) ball.getY() + (float) ball.getHeight() / 2,
				(float) ball.getWidth(), (float) ball.getWidth());
		stroke(255, 0, 0);
		fill(0, 255, 0);
		point((float) ball.getCenterX(), (float) ball.getCenterY());

		stroke(0, 255, 0);
		point((float) ball.getX(), (float) ball.getY());
		point((float) ball.getX() + (float) ball.getWidth(),
				(float) ball.getY());
		point((float) ball.getX(),
				(float) ball.getY() + (float) ball.getHeight());
		point((float) ball.getX() + (float) ball.getWidth(),
				(float) ball.getY() + (float) ball.getHeight());

		stroke(128);
		line((float) ball.getCenterX(), (float) ball.getCenterY(),
				(float) (ball.getCenterX() + v.get(0)),
				(float) (ball.getCenterY() + v.get(1)));
		stroke(128 + 64);
		line((float) ball.getCenterX(), (float) ball.getCenterY(),
				(float) (ball.getCenterX() + v.get(0) / 2),
				(float) ball.getCenterY());

		line((float) ball.getCenterX(), (float) ball.getCenterY(),
				(float) (ball.getCenterX()),
				(float) (ball.getCenterY() + v.get(1) / 2));
		noStroke();
		fill(128, 32, 64);
		for (Projectile r : projectiles) {
			ellipse((float) r.getX() + (float) r.getWidth() / 2,
					(float) r.getY() + (float) r.getHeight() / 2,
					(float) r.getWidth(), (float) r.getWidth());
			stroke(32, 128, 32);
			line(r.getCenterX(), r.getCenterY(), r.getCenterX()
					+ (float) r.getVector().get(0), r.getCenterY()
					+ (float) r.getVector().get(1));
			noStroke();
		}
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
		// println(mouseButton);

	}

	public void mouseReleased() {
	}

	public void keyPressed() {
		// println(keyCode);
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