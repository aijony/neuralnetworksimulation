package neuralnetwork;

import simulation.ActorManager;
import simulation.Point;
import utilities.IOInteraction;

public abstract class ActorNetwork extends Thread{
		//Initialize network
		//Setup the input array size
		protected int index = 0;
		private final int outputSize = 2;
		protected double input[];
		private int[] neuronsPerLayer;
		private NeuralNetwork network;
		private  double learningRate;
		
		protected boolean success = false;
		protected Point outputPoint;
		protected int projectileIndex = 0;
		
		public ActorNetwork(int[] npl, int indexParam, double learnRate){
			neuronsPerLayer = npl;
			index = indexParam;
			learningRate = learnRate;
			
			
			//Get input array (actor) 
			setInput();
			if(projectileIndex != -1){
			neuronsPerLayer[0] = input.length;
			neuronsPerLayer[neuronsPerLayer.length - 1] = outputSize;
			network = new NeuralNetwork(neuronsPerLayer, learningRate);
			}
		}
		
		protected abstract void setInput();
		protected abstract void awaitUpdate();
		protected abstract void calculateSuccess();
		protected abstract void setOutput();
		protected abstract boolean checkReady();
		public void run(){
			if(checkReady()){
			//Simulation sends inputs 
			setInput();
			
			if(projectileIndex != -1){
			calculateSuccess();
			network.backPropagate(IOInteraction.checkOutput(success, network.getOutput()));	
			//Simulation waits for response
			//Network outputs (forward propagate)
			network.forwardPropagate(input);
			outputPoint = new Point(network.getOutput()[0][0], network.getOutput()[0][1]);
			setOutput();
			
			
			}
			}
			}
		
			
}
