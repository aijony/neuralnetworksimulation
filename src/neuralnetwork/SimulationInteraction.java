package neuralnetwork;
 import simulation.ActorManager;
 import simulation.Point;
 import utilities.IOInteraction;
public class SimulationInteraction extends NeuralNetworkManager{
	
	//Initialize network
	//Setup the input array size
	private int index = 0;
	private final int outputSize = 2;
	
	public void setInput(){
		input = IOInteraction.getInput(ActorManager.exportDatum(index));
	}
	
	public void runSimulationNetwork() throws InterruptedException{
		boolean runSimulation = true;
		
		ActorManager.initialize(false);
		
		//Get input array (actor) 
		setInput();
		neuronsPerLayer[0] = input.length;
		neuronsPerLayer[neuronsPerLayer.length - 1] = outputSize;
		network = new NeuralNetwork(neuronsPerLayer, learningRate);
		
		//Loop
		while(runSimulation = true){
			//Simulation sends inputs 
			setInput();
			//Simulation waits for response
			//Network outputs (forward propagate)
			network.forwardPropagate(input);
			Point outputPoint = new Point(network.getOutput()[0][0], network.getOutput()[0][1]);
			ActorManager.getActor(index).initializeMovement(outputPoint);
			
			//Network waits for response
			ActorManager.getActor(index).waitMovementUpdate.await();
			
			//Network learn (back propagate)
			boolean success = !ActorManager.exportDatum(index).hasBeenHit;
			network.backards(IOInteraction.checkOutput(success, network.getOutput()));
			
			
			ActorManager.updateAll();
				
			
		}
	}
}
