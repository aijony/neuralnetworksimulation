package neuralnetwork;
 import graphics.Color;
import graphics.Display;
import graphics.SpriteManager;
import simulation.ActorManager;
public class SimulationInteraction extends NeuralNetworkManager{
	
	private AimingNetwork aimActorOne;
	private AimingNetwork aimActorTwo;
	private MovementNetwork moveActorOne;
	private MovementNetwork moveActorTwo;
	private AimingNetwork aimActorFour;
	private AimingNetwork aimActorThree;
	public SimulationInteraction(){
		int [] neuronsPerLayerOne = new int[]{ 4, 1, 5, 2 };
		int [] neuronsPerLayerTwo = new int[]{ 4, 11, 7, 2 };
		int [] neuronsPerLayerThree = new int[]{ 4, 16, 5, 2 };
		int [] neuronsPerLayerFour = new int[]{ 4, 11, 66, 2 };
		
		
		int actorOneID = ActorManager.addActor(Color.red(), "Unit", 0);
		int actorTwoID = ActorManager.addActor(Color.green(), "Unit", 0);
		int actorThreeID = ActorManager.addActor(Color.red(), "Unit", 0);
		int actorFourID = ActorManager.addActor(Color.green(), "Unit", 0);
		
		//moveActorOne = new MovementNetwork(neuronsPerLayerOne, actorOneID, learningRate);
		//moveActorTwo = new MovementNetwork(neuronsPerLayerTwo, actorTwoID, learningRate);
		
		aimActorOne = new AimingNetwork(neuronsPerLayerThree, actorOneID, learningRate);
		aimActorTwo = new AimingNetwork(neuronsPerLayerFour, actorTwoID, learningRate);
		aimActorThree = new AimingNetwork(neuronsPerLayerOne, actorThreeID, learningRate);
		aimActorFour = new AimingNetwork(neuronsPerLayerTwo, actorFourID, learningRate);
	}
	public void run(){

	
			try {
				ActorManager.ready.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	moveActorOne.run();
			aimActorOne.run();
			aimActorTwo.run();
			aimActorFour.run();
			aimActorThree.run();
		
	}
	
	
}
