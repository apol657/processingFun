package entities;

import util.Vector;

public class Projectile {

	private float x;
	private float y;
	private float width;
	private float height;

	private Vector v;

	public Projectile() {

	}

	public Projectile(float x, float y, float width, float height) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		v=new Vector(2);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Vector getVector() {
		return v;
	}

	public float getCenterX() {
		return x + width / 2;
	}

	public float getCenterY() {
		return y + height / 2;
	}

}
