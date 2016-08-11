package scenarios;

import graphics.Display;

public class Graphics {

	private Display display;

	public void run() {
		display = new Display(500, 500, "jason");
	}

	public static void main(String[] args) {
		new Graphics().run();
	}

}