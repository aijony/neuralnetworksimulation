package neuralnetwork;

import java.util.ArrayList;
import java.util.Random;

public class Connections {
	//The element of the array list is a double "matrix" of weights per layer
	private ArrayList<double [] []> weights;
	private int [] neuronsPerLayer;
	
    public Connections(int [] neuronsPerLayerInput) {
		neuronsPerLayer = neuronsPerLayerInput;
    	weights = new ArrayList<double [] [] >(neuronsPerLayer.length);

		//Loops through setting the weights in between the inputs and the output.
		for (int currentLayer = 1; currentLayer < neuronsPerLayer.length; currentLayer++) {
			//Sets a 2 dimensional matrix of doubles. Each matrix's dimensions is x = input size and y = output size
			double [][] tempWeightMatrix = randomizeWeights(neuronsPerLayer[currentLayer - 1], neuronsPerLayer[currentLayer]);
			weights.add(currentLayer, tempWeightMatrix);
		}
    }
 
	// random
    double getRandom() {
    	Random rnd = new Random();
        return (rnd.nextDouble() * 2 - 1); // [-1;1[
    }
    
    /*
     * @brief returns a set of random weights per layer
    */
    private double[] []randomizeWeights(int x, int y) {
		double[][] output = new double[x] [y];
    	for(int indexX = 0; indexX < x; indexX++){
    		for(int indexY = 0; indexY < x; indexY++){	
			output[indexX][indexY] = getRandom();
    		}
    	}
    	return output;
	}

    
    
    
    /*
     * @brief returns the weights for that layer
    */
    public double [][] getWeight(int layer) {
    	if(layer >= weights.size());
        return weights.get(layer);
    }
 
}