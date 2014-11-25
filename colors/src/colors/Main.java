package colors;

import processing.core.PApplet;

public class Main extends PApplet {
	
	private static PApplet instance;
	private static final long serialVersionUID = 1L;
	int width = 1400, height = 700;
	
	long t;


	public void setup() {
		instance = this;
		size(width	, height);
		background(222);
		noStroke();
		t = millis();
		
	}

	public void draw() {
		long dt = millis() - t;

		boolean update = false;
		if (dt > 10 * 3) {
			update = true;
			t = millis();
		}
	}

	public void mousePressed() {
		System.out.println("click");
		// saveFrame("test####.png");

	}

	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "colors.Main" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
	
	public static PApplet getInstance(){
		return instance;
	}

}