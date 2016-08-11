package neuralnetwork;
 import graphics.Color;
import graphics.Display;
import graphics.SpriteManager;
import simulation.ActorManager;
public class SimulationInteraction extends NeuralNetworkManager{
	
	private AimingNetwork aimActorOne;
	public SimulationInteraction(){
		int [] neuronsPerLayerOne = new int[]{ 4, 11, 5, 2 };
		int [] neuronsPerLayerTwo = new int[]{ 4, 11, 5, 2 };
		
		
		int actorOneID = ActorManager.addActor(Color.red(), "Unit", 0);
		int actorTwoID = ActorManager.addActor(Color.green(), "Unit", 0);
		
		//MovementNetwork moveActorOne = new MovementNetwork(neuronsPerLayerOne, actorOneID, learningRate);
		//MovementNetwork moveActorTwo = new MovementNetwork(neuronsPerLayerTwo, actorTwoID, learningRate);
		
		aimActorOne = new AimingNetwork(neuronsPerLayerOne, actorOneID, learningRate);
		//ActorNetwork aimActorTwo = new AimingNetwork(neuronsPerLayerTwo, actorTwoID, learningRate);
		
	}
	public void run(){

		for (int run = 0; run < 70000; run++) {
			
			//moveActorOne.run();
			
			//moveActorTwo.run();
			aimActorOne.run();
			//aimActorTwo.run();
			System.out.println("continue");
		}
		
	}
	
	
}
