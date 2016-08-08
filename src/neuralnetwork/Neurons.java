package neuralnetwork;
import java.util.*;
 
public class Neurons {   
	
	/*
	The element of the array list is a double "matrix" of neurons per layer
	Field is public to improve propagation speed by about 60% per n
	This is roughly 17 nanoseconds per n on a 4690k base clock CPU
	This optimization is insignificant but noted to prove a point
	Though these sort of optimizations that fight OOP will not continue
	To appear since it would just be easier to program it in a different language
	And it would make the code unreadable
	 */
	public ArrayList<double [][]> values;
    final double bias = 1;
     
    private int [] neuronsPerLayer;
    
    public Neurons(int [] neuronsPerLayerInput) {
 		setNeuronsPerLayer(neuronsPerLayerInput);
     	values = new ArrayList<double [][] >(getNeuronsPerLayer().length);
     	
 		//Loops through setting the weights in between the inputs and the output.
 		for (int currentLayer = 0; currentLayer < getNeuronsPerLayer().length; currentLayer++) {
 			values.add(currentLayer, new double[1][getNeuronsPerLayer()[currentLayer]]);
 		}
     }
    
    
    
    
     public void setInput(double [][] input){
    	 values.set(0, input);
     }
     
     public double[] getOutput(){
    	 return values.get(values.size() - 1)[0];
     }




	public int [] getNeuronsPerLayer() {
		return neuronsPerLayer;
	}




	public void setNeuronsPerLayer(int [] neuronsPerLayer) {
		this.neuronsPerLayer = neuronsPerLayer;
	}
   
}