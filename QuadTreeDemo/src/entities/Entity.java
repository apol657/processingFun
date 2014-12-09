package entities;
public abstract class Entity {

	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected double centerX;
	protected double centerY;
	private int color;
	
	public void setColor(int color) {
		this.color = color;
	}public int getColor() {
		return color;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		centerX=x+width/2;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		centerY=y+height/2;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
		centerX=x+width/2;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
		centerY=y+height/2;
	}

	public double getCenterX() {
		return centerX;
	}

	public double getCenterY() {
		return centerY;
	}
	public Entity(double x, double y, double width, double height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		centerX=x+width/2;
		centerY=y+height/2;
	}

}
