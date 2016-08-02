import java.util.*;
public class test {

	private static int trials = 0;
	private static int correctTrials = 0;
	private static Random r = new Random();
	private static int sum = 10;
	private static int input, input2;
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
			if ((input + input2) >= sum) {
				correctTrials++;
			}
			trials++;
		} else {
			System.out.println("The sum of the inputs is < " + sum);

			if ((input + input2) < sum) {
				correctTrials++;
			}
			trials++;
		}
	}
	public static void main(String[]args){
		

		
		//CREATING THE NEURAL NETWORK
		//The first (input) layer of the neural network contains two neurons
		neuralNetwork.add(new ArrayList<neuron>());
		neuralNetwork.add(new ArrayList<neuron>());
		neuralNetwork.add(new ArrayList<neuron>());
		
		neuralNetwork.get(0).add(new neuron());
		neuralNetwork.get(0).add(new neuron());
		
		//The second (hidden) layer of the neural network contains two neurons
		neuralNetwork.get(1).add(new neuron());
		neuralNetwork.get(1).add(new neuron());
		
		//The third (output) layer of the neural network contains one neuron
		neuralNetwork.get(2).add(new neuron());
		
		
		
		//repeats the training cycle as many times as is desired 
		for(int t = 0; t < 100000; t++){

		//the input values the entire neural network will take in
		 input  =  (r.nextInt(10) + 1);
		 input2 =  (r.nextInt(10) + 1);
		System.out.println("raw Input = "+ input);
		System.out.println("raw Input2 = "+ input2);
		
		//RUNNING THE NEURAL NETWORK
		//loops through all the layers in the neural network in the forward direction
		for(int i = 0; i < neuralNetwork.size(); i++){
			
			//loops through all the neurons in a particular layer of the neural network
			for(int b = 0; b < neuralNetwork.get(i).size(); b++){
				//GETTING INPUT FOR ALL NEURONS
				//if we are in the input layer of the neural network
				if(i == 0){
					
					//give the neurons in the input layer the raw input values
					neuralNetwork.get(i).get(b).setInput(0, input);
					neuralNetwork.get(i).get(b).setInput(1, input2);
					System.out.println("layer: "+i+" number: "+b+" input recieved: "+neuralNetwork.get(i).get(b).getInput().get(0)+" input2 recieved: "+neuralNetwork.get(i).get(b).getInput().get(1));
					System.out.println("layer: "+i+" number: "+b+" weight: "+neuralNetwork.get(i).get(b).getWeights().get(0)+" weight2: "+neuralNetwork.get(i).get(b).getWeights().get(1));
			
					
					//calculating z for all neurons in the input layer
					neuralNetwork.get(i).get(b).calculateZ();
					System.out.println("layer: "+i+" number: "+b+" z: "+neuralNetwork.get(i).get(b).getZ());
					//calculating the Sigmoid or output value for all neurons in the input layer
					neuralNetwork.get(i).get(b).sigmoid();
					System.out.println("layer: "+i+" number: "+b+" sigmoid value: "+neuralNetwork.get(i).get(b).getSigmoidVal());
					
				}
				//if we are not in the input layer of the neural network
				else{
					
					//loop through all the neurons in the previous layer of the neural network
					for(int j = 0; j < neuralNetwork.get(i-1).size(); j++){
						
						//sets the input value for each of the neurons not in the input layer 
						neuralNetwork.get(i).get(b).setInput(j, neuralNetwork.get(i-1).get(j).getSigmoidVal());	
					}
					System.out.println("layer: "+i+" number: "+b+" input recieved: "+neuralNetwork.get(i).get(b).getInput().get(0)+" input2 recieved: "+neuralNetwork.get(i).get(b).getInput().get(1));
					System.out.println("layer: "+i+" number: "+b+" weight: "+neuralNetwork.get(i).get(b).getWeights().get(0)+" weight2: "+neuralNetwork.get(i).get(b).getWeights().get(1));
					//CALCULATING Z FOR ALL NEURONS	
					neuralNetwork.get(i).get(b).calculateZ();
					System.out.println("layer: "+i+" number: "+b+" z: "+neuralNetwork.get(i).get(b).getZ());
					//CALCULATING THE SIGMOID OR OUTPUT VALUE FOR ALL NEURONS
					neuralNetwork.get(i).get(b).sigmoid();
					System.out.println("layer: "+i+" number: "+b+" sigmoid value: "+neuralNetwork.get(i).get(b).getSigmoidVal());
					
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
						neuralNetwork.get(i).get(j).calculateError(input, input2);
						System.out.println("layer: "+i+" number: "+j+" Error: "+ neuralNetwork.get(i).get(j).getError());
						//}
					}
				//we are not in the output layer of the neural network
				else{
					
					//calculates the error full all neurons that are not in the output layer
					neuralNetwork.get(i).get(j).setError(calculateInnerLayerError(i,j));
					System.out.println("layer: "+i+" number: "+j+" Error: "+ neuralNetwork.get(i).get(j).getError());
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
				System.out.println("layer: "+i+" number: "+j+" weight: "+neuralNetwork.get(i).get(j).getWeights().get(0)+" weight2: "+neuralNetwork.get(i).get(j).getWeights().get(1));
			}
		  }
		

		accuracy();
		}
		
	}
}
