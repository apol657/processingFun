package colors;

public class Circle {

	private float x, y, width, height;
	private float vx, vy, maxvx, maxvy;
	private int red, green, blue;
	private float redR, greenR, blueR;
	boolean auto = false;

	public Circle(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		vx = 0.1f;
		vy = 0.1f;
		maxvx = 0.5f;
		maxvy = 0.5f;
		Core p = Core.getInstance();

		red = p.getRng().nextInt(255);
		green = p.getRng().nextInt(255);
		blue = p.getRng().nextInt(255);

		redR = p.getRng().nextInt(10);
		greenR = p.getRng().nextInt(10);
		blueR = p.getRng().nextInt(10);
	}

	public Circle(float x, float y, float width, float height, float vx,
			float vy, boolean auto) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vx = vx;
		this.vy = vy;
		this.auto = auto;
		maxvx = 0.5f;
		maxvy = 0.5f;
		Core p = Core.getInstance();

		red = p.getRng().nextInt(255);
		green = p.getRng().nextInt(255);
		blue = p.getRng().nextInt(255);

		redR = p.getRng().nextInt(10);
		greenR = p.getRng().nextInt(10);
		blueR = p.getRng().nextInt(10);
	}

	public void draw() {
		Core p = Core.getInstance();
		p.fill(red, green, blue);
		p.ellipse(x + width / 2, y + height / 2, width, height);
	}

	public void update(long dt) {
		red = (int) ((red + redR) % 255);
		green = (int) ((green + greenR) % 255);
		blue = (int) ((blue + blueR) % 255);
		if (auto) {
			x += vx * dt;
			y += vy * dt;
			return;
		}
		Core p = Core.getInstance();
		boolean leftright = false;
		boolean updown = false;
		if (p.getKeys().isDown(37)) {
			// left
			x -= dt * vx;
			if (vx < maxvx) {
				vx += 0.01f;
			}
			leftright = true;
		} else {

		}

		if (p.getKeys().isDown(39)) {
			// right
			x += dt * vx;
			if (vx < maxvx) {
				vx += 0.01f;
			}
			leftright = true;
		} else {

		}

		if (!leftright) {
			vx = 0.1f;
		}

		if (p.getKeys().isDown(38)) {
			// up
			y -= dt * vy;
			if (vy < maxvy) {
				vy += 0.01f;
			}
			updown = true;
		} else {

		}

		if (p.getKeys().isDown(40)) {
			// down
			y += dt * vy;
			if (vy < maxvy) {
				vy += 0.01f;
			}
			updown = true;
		} else {

		}

		if (!updown) {
			vy = 0.1f;
		}

	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
