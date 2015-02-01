package entities;

public class Circle extends Ellipse {

	private double radius;
	public Circle(double x, double y, double radius) {
		super(x, y, x + 2 * radius, y + 2 * radius);
		this.radius=radius;
	}

	public double getRadius() {
		return radius;
	}
}
