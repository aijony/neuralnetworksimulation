package neuralnetwork;
import utilities.MatrixMath;
public class Propagation {
	
	int currentLayer;
	double[][] expected;
	Neurons neurons;
	Connections connections;
	
	Propagation(Neurons inNeurons, Connections inConnections){
		neurons = inNeurons;
		connections = inConnections;
	}
	
	public double[][] calculateNeurons(double[][] inputs, double[][] weights){
		
		double [][] outputs =  MatrixMath.multiply(weights, inputs);
		outputs = MatrixMath.sigmoid(outputs);
    	return outputs;
    }

	

	public void forward(double[] input) {
		neurons.values.set(0, MatrixMath.rowToMatrix(input));
		for(currentLayer = 1; currentLayer < neurons.values.size(); currentLayer++){
			double [][] outputs = calculateNeurons(neurons.values.get(currentLayer - 1), connections.weights.get(currentLayer - 1));
			neurons.values.set(currentLayer, outputs);
		}
	}
	
	public double[][] output(){
		
		return neurons.values.get(neurons.values.size() -1);
	}

}
