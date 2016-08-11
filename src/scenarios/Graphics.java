package scenarios;

import graphics.Display;
import graphics.SpriteManager;
import neuralnetwork.SimulationInteraction;
import simulation.ActorManager;

public class Graphics {

	private Display display;

	public void run() {
		
		
		display = new Display(500, 500, "jason");
		SpriteManager.initialize();
		ActorManager.initialize(true);
		
		
		
		SimulationInteraction simulation = new SimulationInteraction();
		simulation.run();
		
		display.run();
		
	}

	public static void main(String[] args) {
		new Graphics().run();
	}

}