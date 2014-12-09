package colors;

public class KeyManager {

	private boolean[] keyDown;

	public KeyManager() {
		keyDown = new boolean[255];
	}

	public void set(int keyCode, boolean state) {
		if (keyCode < 255 && keyCode >= 0) {
			keyDown[keyCode] = state;
		} else {
			System.out.println("KeyCode out of range.");
		}
	}

	public boolean isDown(int keyCode) {
		if (keyCode < 255 && keyCode >= 0) {
			return keyDown[keyCode];
		} else {
			System.out.println("KeyCode out of range.");
			return false;
		}
	}
}
