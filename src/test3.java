import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class test3 {

	private static int trials = 0;
	private static int correctTrials = 0;
	private static int numInputs;
	private static Random r = new Random();
	private static Scanner in = new Scanner(System.in);
	private static int sum = 11;
	private static ArrayList<ArrayList<neuron>> neuralNetwork = new ArrayList<ArrayList<neuron>>();
	
	
	//there is an error in this function it only takes the error from one of the neurons into account
	//calculates the error of a neuron that is not in the output layer
	public static double calculateInnerLayerError(int layer, int neuron){
		double neuronError = 0;
		double tempError = 0;
		int layerafter = layer+1;
		//loops through all the neurons in the layer after the current neuron
		for(int i = 0; i < neuralNetwork.get(layerafter).size(); i++){
			//calculates the weighted error the current neuron contributes to one neuron in the layer after it
			tempError = neuralNetwork.get(layer+1).get(i).getError()*neuralNetwork.get(layer+1).get(i).getWeights().get(neuron);
			neuronError += tempError;
		}
		
		return neuronError;
	}
	
	public static void accuracy() {

		System.out.println("percent of correct trials: " + correctTrials / (double) trials);
	}

	public static void checkIfCorrectOutput(double sigmoidVal) {

		if (sigmoidVal >= 0.5) { // a value of 0.4 is good when sum is set to 10
			// 0.8 is good when it is on either end
			System.out.println("The sum of the inputs is >=" + sum);
			if ((neuralNetwork.get(0).get(0).getInput().get(0)+neuralNetwork.get(0).get(0).getInput().get(1)) >= sum) {
				correctTrials++;

			}
	
			trials++;
		
		} else {
			System.out.println("The sum of the inputs is < " + sum);

			if ((neuralNetwork.get(0).get(0).getInput().get(0)+neuralNetwork.get(0).get(0).getInput().get(1)) < sum) {
				correctTrials++;
			}
	
			trials++;

		}
	}
	public static void main(String[]args){
		
		System.out.println("Enter the number of inputs you want the neural network to have");
		numInputs = Integer.parseInt(in.nextLine());
		System.out.println("Enter the number of layers you want the neural network to have");
		int layers = Integer.parseInt(in.nextLine());
		for(int i = 0; i < layers; i++){
			neuralNetwork.add(new ArrayList<neuron>());
			System.out.println("Enter the number of neurons you want in this layer");
			int n = Integer.parseInt(in.nextLine());
			for(int b = 0; b < n; b++){
			
				if(i == 0){
				neuralNetwork.get(i).add(new neuron(numInputs));
					for(int z = 0; z < numInputs; z++){
						neuralNetwork.get(i).get(b).getInput().set(z, (double) (r.nextInt(10) + 1));
					}
				}
				else{
				neuralNetwork.get(i).add(new neuron(neuralNetwork.get(i-1).size()));	
				}
			}
		}
			
		
		//repeats the training cycle as many times as is desired 
		for(int w = 0; w < 10; w++){
			trials = 0;
			correctTrials = 0;
		for(int t = 0; t < 1000000; t++){

		//the input values the entire neural network will take in
		
		//RUNNING THE NEURAL NETWORK
		//loops through all the layers in the neural network in the forward direction
		for(int i = 0; i < neuralNetwork.size(); i++){
			
			//loops through all the neurons in a particular layer of the neural network
			for(int b = 0; b < neuralNetwork.get(i).size(); b++){
				//GETTING INPUT FOR ALL NEURONS
				//if we are in the input layer of the neural network
				if(i == 0){
					
					//give the neurons in the input layer the raw input values
		
					for(int z = 0; z < numInputs; z++){
						
						neuralNetwork.get(i).get(b).getInput().set(z, (double)(r.nextInt(10) + 1));
						
					}
					
					
		
					//calculating z for all neurons in the input layer
					neuralNetwork.get(i).get(b).calculateZ();
					//calculating the Sigmoid or output value for all neurons in the input layer
					neuralNetwork.get(i).get(b).sigmoid();
					
				}
				//if we are not in the input layer of the neural network
				else{
					
					//loop through all the neurons in the previous layer of the neural network
					for(int j = 0; j < neuralNetwork.get(i-1).size(); j++){
						
						//sets the input value for each of the neurons not in the input layer 
						neuralNetwork.get(i).get(b).setInput(j, neuralNetwork.get(i-1).get(j).getSigmoidVal());	
					}
					//CALCULATING Z FOR ALL NEURONS	
					neuralNetwork.get(i).get(b).calculateZ();
					//CALCULATING THE SIGMOID OR OUTPUT VALUE FOR ALL NEURONS
					neuralNetwork.get(i).get(b).sigmoid();
					
				}
			
			}
			
		}
		//CHECK IF THE OUPUT NEURON HAD THE CORRECT OUTPUT
		//will need to be updated if multiple output neurons are added
		//adds to the trails and correct trials variables
		checkIfCorrectOutput(neuralNetwork.get(neuralNetwork.size()-1).get(0).getSigmoidVal());
		System.out.println("trials correct: " +correctTrials+ " trials: "+ trials);
		
		//looping through all layers in the neural network in the backward direction
		for(int i = neuralNetwork.size() - 1; i >= 0; i--){
			
			//loops through all the neurons in a particular layer of the neural network
			for(int j = 0; j < neuralNetwork.get(i).size(); j++){
			
				//if we are in the output layer of the neural network
				if(i == neuralNetwork.size() - 1 ){
					
				/*	//loops through all of the output neurons
					for(int b = 0; b < neuralNetwork.get(i).size(); b++){
					*/
						//calculates the error for all the neurons in the output layer
						neuralNetwork.get(i).get(j).calculateError(neuralNetwork.get(0).get(0).getInput().get(0),neuralNetwork.get(0).get(0).getInput().get(1));
						//}
					}
				//we are not in the output layer of the neural network
				else{
					
					//calculates the error full all neurons that are not in the output layer
					neuralNetwork.get(i).get(j).setError(calculateInnerLayerError(i,j));
						}
				
			}
			
		}
		
		//loops through all the layers in the neural network
		for(int i = 0; i < neuralNetwork.size(); i++){
			
			//loops through all the neurons in a particular layer
			for(int j = 0; j < neuralNetwork.get(i).size(); j++){
				
				//loops through all the w values for a particular neuron
				for(int b = 0; b < neuralNetwork.get(i).get(j).getWeights().size(); b++){
					
					//updates current w value 
					neuralNetwork.get(i).get(j).updateW(b);
					
				}
				}
		  }
		

		accuracy();
		}
		
	}
}
}
