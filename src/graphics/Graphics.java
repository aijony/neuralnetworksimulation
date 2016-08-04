package graphics;

public class Graphics {

	private Display display;

	public void run() {
		display = new Display(300, 300, "jason");
	}

	public static void main(String[] args) {
		new Graphics().run();
	}

}