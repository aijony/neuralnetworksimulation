package neuralnetwork;

import java.util.ArrayList;
import java.util.Random;

import utilities.MatrixMath;

public class Connections {
	
	/*
	The element of the array list is a double "matrix" of weights per layer
	Field is public to improve propagation speed by about 60% per n
	This is roughly 17 nanoseconds per n on a 4690k base clock CPU
	This optimization is insignificant but noted to prove a point
	Though these sort of optimizations that fight OOP will not continue
	To appear since it would just be easier to program it in a different language
	And it would make the code unreadable
*/
	public ArrayList<double [] []> weights;
	
	private int [] neuronsPerLayer;
	
	/*
		Create an array list of weight matrices. X is the input amount, Y is the output amount.
	*/
    public Connections(int [] neuronsPerLayerInput) {
		neuronsPerLayer = neuronsPerLayerInput;
    	weights = new ArrayList<double [] [] >(neuronsPerLayer.length);

		//Loops through setting the weights in between the inputs and the output, so there are one less synapse layers than neuron layers
		for (int currentLayer = 1; currentLayer < neuronsPerLayer.length; currentLayer++) {
			//Sets a 2 dimensional matrix of doubles. Each matrix's dimensions is x = input size and y = output size
			double [][] tempWeightMatrix = randomizeWeights(neuronsPerLayer[currentLayer - 1], neuronsPerLayer[currentLayer] );
			weights.add(currentLayer - 1, tempWeightMatrix);
		}
    }
    
    /*
     *     Creates weights based off of pre-existing weights
     */    
    public Connections(ArrayList<double [][]> inWeights) {
    	weights = inWeights;
    }
 
	// random
    private double getRandom() {
    	Random rnd = new Random();
        return (rnd.nextDouble() * 2 - 1); // [-1;1[
        
    }
    
    /*
     * @brief returns a set of random weights per layer
    */
    double[] []randomizeWeights(int x, int y) {
		double[][] output = new double[x] [y];
    	for(int indexX = 0; indexX < x; indexX++){
    		for(int indexY = 0; indexY < y; indexY++){	
			output[indexX][indexY] = getRandom();
    		}
    	}
    	return output;
	}

    

 
}