package neuralnetwork;
 import simulation.ActorManager;
 import simulation.Point;
 import utilities.IOInteraction;
public class SimulationInteraction extends NeuralNetworkManager{
	
	//Initialize network DONE
	//Setup the input array size DONE
	private int index = 0;
	private final int outputSize = 2;
	
	public void setInput(){
		input = IOInteraction.getInput(ActorManager.exportDatum(index));
	}
	
	public void runSimulation(){
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
					//Success bitwise boolean
				//Network learn (back propagate)
			
			
			ActorManager.updateAll();
				
			
			
			
		}
	}
}
