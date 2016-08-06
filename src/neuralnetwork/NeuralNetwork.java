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
		connections = new Connections(neuronsPerLayer);
		neurons = new Neurons(neuronsPerLayer);
    	propagation = new Propagation(neurons, connections);
	}	
		

    public double [] forwardPropagate(double [] input, double []expected){
    	
    	if(neuronsPerLayer[0] == input.length
    		&& neuronsPerLayer[neuronsPerLayer.length - 1]
    		== expected.length)
    	{
    		propagation.forward(input);
    		return propagation.output()[0];		
    	}
    	else{
    		return null;
    	}
    	
    
    }
  
    
 
   
 
    
    
   

 

}