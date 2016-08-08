package graphics;

public class Graphics {

	private Display display;

	public void run() {
		display = new Display(700, 700, "jason");
	}

	public static void main(String[] args) {
		new Graphics().run();
	}

}