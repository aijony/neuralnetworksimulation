package neuralnetwork;

import java.util.ArrayList;

import utilities.MatrixMath;

public class Propagation {

	private double[][] expected;
	private ArrayList<double[][]> neurons, connections, deltas, nets;

	private int[] neuronsPerLayer;
	private int currentLayer;
	private double learningRate;
	private final double bias = 1.0;

	Propagation(Neurons inNeurons, Connections inConnections, int[] npl, double learn) {
		neuronsPerLayer = npl;
		neurons = inNeurons.values;
		nets = new Neurons(neuronsPerLayer).values;
		connections = inConnections.weights;
		learningRate = learn;
	}

	/*
	 * Forward propagates by multiplying the weights by previous neurons and
	 * activates Loops through the entire network to propagate
	 */
	public void forward(double[] input) {
		// Sets the input from a 1D array to a "matrix"
		for(int i = 0; i < input.length; i++){
			
		}
		neurons.set(0, MatrixMath.rowToMatrix(input));

		// Loops tjrpigj tje metwprl
		for (currentLayer = 1; currentLayer < neurons.size(); currentLayer++) {
			// Calculates neurons based off of the previous neurons and the
			// weights
			double[][] outputs = calculateNeurons(neurons.get(currentLayer - 1), connections.get(currentLayer - 1));
			// Sets the bias
			if (currentLayer != connections.size()) {
				outputs[0][neuronsPerLayer[currentLayer] - 1] = (bias);
			}

			// Sets the calculations to the neurons
			neurons.set(currentLayer, outputs);
		}
	}

	/*
	 * Calculates a layer of neurons based off the previous inputs and weights
	 */
	private double[][] calculateNeurons(double[][] inputs, double[][] weights) {

		// Calculate Z
		double[][] outputs = MatrixMath.multiply(weights, inputs);
		if (currentLayer != connections.size()) {
			outputs[0][neuronsPerLayer[currentLayer] - 1] = bias;
		}
		nets.set(currentLayer, outputs);

		// Activate Z with sigmoid
		return MatrixMath.sigmoid(nets.get(currentLayer));

	}

	/*
	 * Back propagation method for matrix math: Runs through calculating
	 * semi-persistant deltas and using them to calculate proceeding deltas and
	 * new weights
	 */
	public void backward(double[] ideal) {

		// Makes the expected compatible
		expected = MatrixMath.rowToMatrix(ideal);

		ArrayList<double[][]> newWeights = new ArrayList<double[][]>();
		deltas = new ArrayList<double[][]>();

		// Initializes array list to a certain sides since the back propagation
		// will be working backwards
		for (int index = 0; index < connections.size(); index++) {
			double[][] padding = new double[][] { { 0 } };
			newWeights.add(padding);
			deltas.add(padding);
		}

		// Starts at the input and works to the last hidden layer setting a
		// delta matrix that aligns with a neuron matrix (1D shaped 2D arrays)
		for (int synapseLayer = connections.size() - 1; synapseLayer >= 0; synapseLayer--) {
			double[][] delta = calculateDelta(synapseLayer);
			deltas.set(synapseLayer, delta);
			newWeights.set(synapseLayer, calculateWeight(synapseLayer));
		}
		// Sets the field to the new weight
		connections = newWeights;
	}

	/*
	 * Calculates the delta either based off of the ideal and actual if it is
	 * the first one or the sum of the previous deltas times the weight. Then
	 * multiplies that value by the sigmoid prime of the proceeding neurons net
	 * input
	 */
	private double[][] calculateDelta(int synapseLayer) {

		double[][] delta = new double[1][neuronsPerLayer[synapseLayer + 1]];
		// calculate first deltas
		if (synapseLayer == connections.size() - 1) {
			delta = MatrixMath.subtract(output(), expected);
		} else {
			// Initial value is brought from previously computed deltas
			delta = deltas.get(synapseLayer + 1);
			// Transposes it for multiplication compatibility
			delta = MatrixMath.transpose(delta);
			// Multiplies it by the corresponding weights to the delta
			// (This also deals with the summation due to the properties of
			// matrix multiplication)
			delta = MatrixMath.multiply(delta, connections.get(synapseLayer + 1));
		}
		// Multiplies the proceeding neurons to a transposed delta
		delta = MatrixMath.multiply(MatrixMath.sigmoidPrime(nets.get(synapseLayer + 1)), MatrixMath.transpose(delta));
		return delta;
	}

	/*
	 * Calculates the new weight based off of the derivative of the cost,
	 * learning rate, and old weight
	 */
	private double[][] calculateWeight(int synapseLayer) {

		// Initial value is a transposed proceeding neuron
		double[][] derivativeOfCost = MatrixMath.transpose(neurons.get(synapseLayer));
		// Multiplies the delta by the neurons value (Calculated in forward
		// propagation and activating it)
		derivativeOfCost = MatrixMath.multiply(deltas.get(synapseLayer), derivativeOfCost);
		// Scaler multiplies every value in the derivative by the learning rate
		derivativeOfCost = MatrixMath.multiply(derivativeOfCost, learningRate);
		// Subtracts previous weights by the scaled derivative weight
		derivativeOfCost = MatrixMath.subtract(connections.get(synapseLayer), derivativeOfCost);
		return derivativeOfCost;
	}

	public double[][] output() {
		return neurons.get(neurons.size() - 1);
	}
	
	public Connections getWeights(){
		return new Connections(connections);
	}

}
