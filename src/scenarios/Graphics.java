package scenarios;

import graphics.Display;
import graphics.SpriteManager;
import neuralnetwork.SimulationInteraction;
import simulation.ActorManager;

public class Graphics {

	private Display display;

	public void run() {
		
		

		
		ActorManager.initialize(true);
		SpriteManager.initialize();
		display = new Display(900, 900, "jason");
		
		
		System.out.println("Display");
		display.run();
		
	}

	public static void main(String[] args) {
		new Graphics().run();
	}

}