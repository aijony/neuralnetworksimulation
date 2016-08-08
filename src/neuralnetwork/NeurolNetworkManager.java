package neuralnetwork;

public class NeurolNetworkManager {

	// Inputs for xor
    final static double inputs[][] = { { 1, 1 }, { 1, 0 }, { 0, 1 }, { 0, 0 } };
 
    //  xor training data
    final static double expectedOutputs[][] = { { 0 }, { 1 }, { 1 }, { 0 } };
	

    final boolean isTrained = false;
    
 
    final double learningRate = 0.9;
    final double momentum = 0.7;
 
    
	public static void main(String[] args) {
		       
		       //NPL is neurons per layer
		        int neuronsPerLayer [] = {2, 4, 2, 5, 1};
		       
		        
		    	NeuralNetwork network = new NeuralNetwork(neuronsPerLayer);

		        int maxRuns = 50000;
		        double minErrorCondition = 0.001;
		        
		        double output = network.forwardPropagate(inputs[1], expectedOutputs[0])[0];
		        
		        //System.out.println(output);
		    }
		 
	}

