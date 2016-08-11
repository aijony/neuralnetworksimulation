package neuralnetwork;
 import graphics.Color;
import simulation.ActorManager;
public class SimulationInteraction extends NeuralNetworkManager{
	
	public void runSimulation(){

		int [] neuronsPerLayerOne = new int[]{ 5, 11, 5, 2 };
		int [] neuronsPerLayerTwo = new int[]{ 5, 11, 5, 2 };
		
		ActorManager.initialize(false);
		int actorOneID = ActorManager.addActor(Color.red(), "Unit", 0);
		int actorTwoID = ActorManager.addActor(Color.green(), "Unit", 0);
		
		ActorNetwork actorOne = new ActorNetwork(neuronsPerLayerOne, actorOneID, learningRate);
		ActorNetwork actorTwo = new ActorNetwork(neuronsPerLayerTwo, actorTwoID, learningRate);
		
		
		
		for (int run = 0; run < 70000; run++) {

			ActorManager.updateAll();
			actorOne.run();
			System.out.println("continue");
			actorTwo.run();
		}
		
	}
	
	
}
