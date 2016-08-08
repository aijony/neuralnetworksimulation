
package neuralnetwork;

import java.util.*;

import utilities.MatrixMath;

public class NeuralNetwork {
	

    
 
    // for weight update all
    final HashMap<String, Double> weightUpdate = new HashMap<String, Double>();
 
		
	Propagation propagation;
	
	int [] neuronsPerLayer;
	Neurons neurons;
	Connections connections;
    
	/* */
    public NeuralNetwork(int [] npl) {
		neuronsPerLayer = npl;
		for(int index = 1; index < neuronsPerLayer.length -1; index++){
			neuronsPerLayer[index]++;
		}
		connections = new Connections(neuronsPerLayer);
		neurons = new Neurons(neuronsPerLayer);
    	propagation = new Propagation(neurons, connections, neuronsPerLayer);
	}	
		

    public double [] forwardPropagate(double [] input, double [] expected){
	
    	if(neuronsPerLayer[0] == input.length 
    		&& neuronsPerLayer[neuronsPerLayer.length - 1]
    		== expected.length)
    	{
    		propagation.setInput(input);
    		propagation.forward();
    		propagation.backward(expected);
    		return propagation.output()[0];
    	}
    	else{
    		return null;
    	}

    }
    
    public double calculateCost(double [] expected){
    	
		double [][] difference = MatrixMath.subtract(MatrixMath.rowToMatrix(expected), propagation.output());
    	
		difference = MatrixMath.multiply(difference, MatrixMath.transpose(difference));
    	return difference[0][0] * 0.5;
    }
  

}
