package neuralnetwork;

import simulation.ActorManager;
import simulation.Point;
import utilities.IOInteraction;

public class ActorNetwork{
		//Initialize network
		//Setup the input array size
		private int index = 0;
		private final int outputSize = 2;
		protected double input[];
		private int[] neuronsPerLayer;
		private NeuralNetwork network;
		private  double learningRate;
		
		public ActorNetwork(int[] npl, int indexParam, double learnRate){
			neuronsPerLayer = npl;
			index = indexParam;
			learningRate = learnRate;
			
			
			//Get input array (actor) 
			setInput();
			neuronsPerLayer[0] = input.length;
			neuronsPerLayer[neuronsPerLayer.length - 1] = outputSize;
			network = new NeuralNetwork(neuronsPerLayer, learningRate);
		}
		
		private void setInput(){
			input = IOInteraction.getInput(ActorManager.exportDatum(index));
		}
		
		public void run(){
			
			//Simulation sends inputs 
			setInput();
			//Simulation waits for response
			//Network outputs (forward propagate)
			network.forwardPropagate(input);
			Point outputPoint = new Point(network.getOutput()[0][0], network.getOutput()[0][1]);
			ActorManager.getActor(index).initializeMovement(outputPoint);
			System.out.println("wait");
			//Network waits for response
			try {
	
				ActorManager.getActor(index).waitMovementUpdate.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Network learn (back propagate)
			boolean success = !ActorManager.exportDatum(index).hasBeenHit;
			
			network.backPropagate(IOInteraction.checkOutput(success, network.getOutput()));
			}
		

}
