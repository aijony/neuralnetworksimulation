package neuralnetwork;
import java.util.ArrayList;

import utilities.MatrixMath;
public class Propagation {
	
	private double[][] expected;
	private  ArrayList<double [][]> neurons, connections, deltas, nets;
	
	private int[] neuronsPerLayer;
	private int currentLayer;
	
	Propagation(Neurons inNeurons, Connections inConnections, int[] npl){
		neuronsPerLayer = npl;
		neurons = inNeurons.values;
		nets = new Neurons(neuronsPerLayer).values;
		connections = inConnections.weights;
		
	}
	

	public void forward(double[] input) {
		neurons.set(0, MatrixMath.rowToMatrix(input));
		for(currentLayer = 1; currentLayer < neurons.size(); currentLayer++){
			double [][] outputs = calculateNeurons(neurons.get(currentLayer - 1), connections.get(currentLayer - 1));
			neurons.set(currentLayer, outputs);
		}
	}
	
	public double[][] calculateNeurons(double[][] inputs, double[][] weights){
		//Calculate Z
		nets.set(currentLayer, MatrixMath.multiply(weights, inputs))  ;
		//Activate Z with sigmoid
		return MatrixMath.sigmoid(nets.get(currentLayer));
		
    }
	
	public Connections backward(double [] ideal) {
		
		expected = MatrixMath.rowToMatrix(ideal);
		ArrayList<double [][]> newWeights = new ArrayList<double [][]>( );
		deltas = new ArrayList<double [][]>();
		
		for(int index = 0; index < connections.size() - 1; index++){
			double[][] padding = new double[][]{{0}};
			newWeights.add(padding);
			deltas.add(padding);
		}
		
		for(int synapseLayer = connections.size() - 1; synapseLayer >= 0; synapseLayer--){
			double[][] delta = calculateDelta(synapseLayer);
			deltas.add(synapseLayer, delta);
			newWeights.add(synapseLayer, calculateWeight(synapseLayer));
		}
		double [][] product =  newWeights.get(0);
		for(int i = 0; i < product[0].length; i++){
			for(int j = 0; j < product.length; j++){
			System.out.print(product[j][i] + " ");
			}
			System.out.println();
		}
		
		return new Connections(newWeights);
		
	}
	


	private double[][] calculateDelta(int synapseLayer) {

		double [][] delta = new double[1][neuronsPerLayer[synapseLayer + 1]];
		//calculateFirstDeltats
		if(synapseLayer == connections.size() -1){
			for(int outputRow = 0; outputRow < output()[0].length; outputRow++){
				double calculated = (output()[0][outputRow] - expected[0][outputRow]);
				calculated	*= MatrixMath.sigmoidPrime(nets.get(synapseLayer) [0][outputRow]);
				delta[0][outputRow] = calculated;
				
			}
		}
		else{
			double calculated = 0;
			for(int outputRow = 0; outputRow < output()[0].length; outputRow++){
				for(int sumIndex = 0; sumIndex < deltas.get(synapseLayer + 1).length; sumIndex++ ){
					calculated = deltas.get(synapseLayer + 1)[sumIndex][0];
					calculated *= connections.get(synapseLayer + 1)[0][sumIndex];
				}
				calculated	*= MatrixMath.sigmoidPrime(nets.get(synapseLayer) [0][outputRow]);
				delta[0][outputRow] = calculated;
			}
		}
		delta = MatrixMath.transpose(delta);
		
		return delta;
	}
	

	private double[][] calculateWeight(int synapseLayer) {
		double [][] derivativeOfCost = MatrixMath.multiply(neurons.get(synapseLayer), deltas.get(synapseLayer));
		derivativeOfCost = MatrixMath.transpose(derivativeOfCost);
		return MatrixMath.subtract(connections.get(synapseLayer), derivativeOfCost);
	}
	

	public double[][] output(){
		return neurons.get(neurons.size() -1);
	}

}
