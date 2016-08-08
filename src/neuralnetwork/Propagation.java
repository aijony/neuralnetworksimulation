package neuralnetwork;
import java.util.ArrayList;

import utilities.MatrixMath;
public class Propagation {
	
	private double[][] expected;
	private  ArrayList<double [][]> neurons, connections, deltas, nets;
	

	private int[] neuronsPerLayer;
	private int currentLayer;
	private final double learningRate = .9;
	private final double bias = 1.0;
	
	Propagation(Neurons inNeurons, Connections inConnections, int[] npl){
		neuronsPerLayer = npl;
		neurons = inNeurons.values;
		nets = new Neurons(neuronsPerLayer).values;
		connections = inConnections.weights;
	}
	
	public void setInput(double [] input){
		neurons.set(0, MatrixMath.rowToMatrix(input));
	}
	
	public void forward() {
		for(currentLayer = 1; currentLayer < neurons.size(); currentLayer++){
			double [][] outputs = calculateNeurons(neurons.get(currentLayer - 1), connections.get(currentLayer - 1));
			if(currentLayer != connections.size()){
				outputs[0][neuronsPerLayer[currentLayer]-1] = bias;
				}
				neurons.set(currentLayer, outputs);
		}
	}
	
	
	
	private double[][] calculateNeurons(double[][] inputs, double[][] weights){
		//Calculate Z
		double [][] outputs = MatrixMath.multiply(weights, inputs);
		if(currentLayer != connections.size()){
			outputs[0][neuronsPerLayer[currentLayer]-1] = bias;
		}
		nets.set(currentLayer, outputs);
		
		//Activate Z with sigmoid
		return MatrixMath.sigmoid(nets.get(currentLayer));
		
    }
	
	public void backward(double [] ideal) {
		
		expected = MatrixMath.rowToMatrix(ideal);
		ArrayList<double [][]> newWeights = new ArrayList<double [][]>( );
		deltas = new ArrayList<double [][]>();
		
		for(int index = 0; index < connections.size(); index++){

			
			double[][] padding = new double[][]{{0}};
			newWeights.add(padding);
			deltas.add(padding);
		}
		for(int synapseLayer = connections.size() - 1; synapseLayer >= 0; synapseLayer--){
			double[][] delta = calculateDelta(synapseLayer);
			

			deltas.set(synapseLayer, delta);

			newWeights.set(synapseLayer, calculateWeight(synapseLayer));
		}

		connections = newWeights;
		
	}
	

	private double[][] calculateDelta(int synapseLayer) {

		double [][] delta = new double[1][neuronsPerLayer[synapseLayer + 1]];
		//calculateFirstDeltats
		if(synapseLayer == connections.size() -1){
			delta = MatrixMath.subtract(output(), expected); 
		}
		else{
			delta = deltas.get(synapseLayer + 1);
			delta = MatrixMath.transpose(delta);

			delta = MatrixMath.multiply(delta, connections.get(synapseLayer + 1));
		}
		 delta	= MatrixMath.multiply(MatrixMath.sigmoidPrime(nets.get(synapseLayer + 1)), MatrixMath.transpose(delta));
		 
		 return delta;
	}

	private double[][] calculateWeight(int synapseLayer) {
		
 		
		 double [][] derivativeOfCost = MatrixMath.transpose(neurons.get(synapseLayer ));
		
		 derivativeOfCost = MatrixMath.multiply(deltas.get(synapseLayer), derivativeOfCost );
		//TODO LearningRate Scaler
		 derivativeOfCost = MatrixMath.multiply(derivativeOfCost, learningRate );
		 
		 derivativeOfCost = MatrixMath.subtract(connections.get(synapseLayer), derivativeOfCost);
		 return derivativeOfCost;
	}
	
	public void test(double [][] product){
			for(int i = 0; i < product[0].length; i++){
				for(int j = 0; j < product.length; j++){
				System.out.print(product[j][i] + " ");
				}
				System.out.println();
			}
		
	}

	public double[][] output(){
		return neurons.get(neurons.size() -1);
	}

}
