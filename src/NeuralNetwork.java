import java.util.ArrayList;

public class NeuralNetwork {

	private int trials;
	private int correctTrials;
	private ArrayList<Double> correctOutputs;
	private ArrayList<ArrayList<Neuron>> neuralNetwork;
	private int numberRawInputs;
	
	
	public static void accuracy() {

		System.out.println("percent of correct trials: " + correctTrials / (double) trials);
	}

	//returns a specified layer from the network
	public ArrayList<Neuron> getNeuralNetworkLayer(int layer) {
		return neuralNetwork.get(layer);
	}
	
	//returns a neuron given its position in the network
	public Neuron getNeuron(int layer, int neuron){
		return neuralNetwork.get(layer).get(neuron);
	}

	//lets the network know it is in training mode
	public void trainNeuralNetwork(ArrayList<Double> rawInputs){
		
		//RUNNING THE NEURAL NETWORK
		//loops through all the layers in the neural network in the forward direction
		for(int currentLayer = 0; currentLayer < neuralNetwork.size(); currentLayer++){
			
			//loops through all the neurons in a particular layer of the neural network
			for(int currentNeuron = 0; currentNeuron < getNeuralNetworkLayer(currentLayer).size(); currentNeuron++){
				//GETTING INPUT FOR ALL NEURONS
				//if we are in the input layer of the neural network
				if(currentLayer == 0){
					
					//give the neurons in the input layer the raw input values
		
					for(int currentInput = 0; currentInput < numberRawInputs; currentInput++){
						
						getNeuron(currentLayer,currentNeuron).getInput().set(currentInput, rawInputs.get(currentInput));
						
					}
					
					
		
					//calculating z for all neurons in the input layer
					getNeuron(currentLayer, currentNeuron).calculateZ();
					//calculating the Sigmoid or output value for all neurons in the input layer
					getNeuron(currentLayer, currentNeuron).sigmoid();
					
				}
				//if we are not in the input layer of the neural network
				else{
					
					//loop through all the neurons in the previous layer of the neural network
					for(int neuronInPreviousLayer = 0; neuronInPreviousLayer < getNeuralNetworkLayer(currentLayer-1).size(); neuronInPreviousLayer++){
						
						//sets the input value for each of the neurons not in the input layer 
						getNeuron(currentLayer, currentNeuron).setInput(neuronInPreviousLayer, getNeuron(currentLayer-1, neuronInPreviousLayer).getSigmoidVal());	
					}
					//CALCULATING Z FOR ALL NEURONS	
					getNeuron(currentLayer, currentNeuron).calculateZ();
					//CALCULATING THE SIGMOID OR OUTPUT VALUE FOR ALL NEURONS
					getNeuron(currentLayer, currentNeuron).sigmoid();
					
				}
			
			}
			
		}	
		
		
		//CHECK IF THE OUPUT NEURON HAD THE CORRECT OUTPUT
		//will need to be updated if multiple output neurons are added
		//adds to the trails and correct trials variables
	
		//need to know test condition to program this
		//checkIfCorrectOutput(neuralNetwork.get(neuralNetwork.size()-1).get(0).getSigmoidVal());
		
		//System.out.println("trials correct: " +correctTrials+ " trials: "+ trials);
		
		
		
		//looping through all layers in the neural network in the backward direction
		for(int currentLayer = neuralNetwork.size() - 1; currentLayer >= 0; currentLayer--){
			
			//loops through all the neurons in a particular layer of the neural network
			for(int currentNeuron = 0; currentNeuron < neuralNetwork.get(currentLayer).size(); currentNeuron++){
			
				//if we are in the output layer of the neural network
				if(currentLayer == neuralNetwork.size() - 1 ){
					
				/*	//loops through all of the output neurons
					for(int b = 0; b < neuralNetwork.get(i).size(); b++){
					*/
						//calculates the error for all the neurons in the output layer
					//	getNeuron(currentLayer, currentNeuron).calculateError(insert parameters);
						//}
					}
				//we are not in the output layer of the neural network
				else{
					
					//calculates the error full all neurons that are not in the output layer
				//	getNeuron(currentLayer, currentNeuron).setError(insert parameters);
						}
				
			}
			
		}
		
		//loops through all the layers in the neural network
		for(int currentLayer = 0; currentLayer < neuralNetwork.size(); currentLayer++){
			
			//loops through all the neurons in a particular layer
			for(int currentNeuron = 0; currentNeuron < getNeuralNetworkLayer(currentLayer).size(); currentNeuron++){
				
				//loops through all the w values for a particular neuron
				for(int currentWeight = 0; currentWeight < getNeuron(currentLayer, currentNeuron).getWeights().size(); currentWeight++){
					
					//updates current w value 
					getNeuron(currentLayer, currentNeuron).updateW(currentWeight);
					
				}
				}
		  }
		

		accuracy();
		
	}
	
	//lets the network know that it is in testing mode
	public void testNeuralNetwork(){
		
		
	}

	// creates a neural network of size (layers, neuronsPerLayer)
	public NeuralNetwork(int numberInputs, int layers, ArrayList<Integer> neuronsPerLayer,
			ArrayList<Double> CorrectOutputs) {

		// initializes trials and correct trials
		trials = 0;
		correctTrials = 0;
		numberRawInputs = numberInputs;

		// initializes correctOutputs
		for (double d : CorrectOutputs) {
			correctOutputs.add(d);
		}
		

		// adds the correct number of layers to the neural network
		for (int i = 0; i < layers; i++) {
			neuralNetwork.add(new ArrayList<Neuron>());
		}

		// adds the correct number of neurons to each layer of the neural
		// network
		for (int i = 0; i < neuronsPerLayer.size(); i++) {
			for (int j = 0; j < neuronsPerLayer.get(i); j++) {
				
				//if we are in the first layer numberInputs applies
				if(i == 0){
				getNeuralNetworkLayer(i).add(new Neuron(numberRawInputs));
				}
				
				//if we are not in the first layer the number of inputs = the size of the previous layer
				else{
				getNeuralNetworkLayer(i).add(new Neuron(getNeuralNetworkLayer(i-1).size()));
				}
			}
		}
		
	
	}


	
	
}
