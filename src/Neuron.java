import java.util.*;

public class Neuron {

	private ArrayList<Double> weights = new ArrayList<Double>();
	private ArrayList<Double> input = new ArrayList<Double>();

	private final int sum = 10; // the number that the neural network is testing
								// to see if is greater than or less than the
								// sum of the input values
	private double z, sigmoidVal, error;

	public Neuron(int numInputs) {

		Random r = new Random();
		for(int i = 0; i < numInputs; i++){
		weights.add(r.nextDouble());
		input.add(null);
		}
	}

	// there is no function for setting sigmoidVal or Z directly since there are
	// already functions that compute these
	
//	public static void checkIfCorrectOutput(double sigmoidVal) {
//
//		if (sigmoidVal >= 0.5) { // a value of 0.4 is good when sum is set to 10
//			// 0.8 is good when it is on either end
//			System.out.println("Output Output");
//			if ( () >= () ) {
//				correctTrials++;
//
//			}
//	
//			trials++;
//		
//		} else {
//			System.out.println("Output Output");
//
//			if ( () ) < () ) {
//				correctTrials++;
//			}
//	
//			trials++;
//
//		}
//	}
	
	
	public double getSigmoidVal() {

		return sigmoidVal;
	}

	public double getZ() {

		return z;
	}

	public ArrayList<Double> getWeights() {

		return weights;
	}

	public ArrayList<Double> getInput() {

		return input;
	}

	public double getError() {

		return error;
	}

	public void setError(double value) {
		error = value;
	}

	public void setWeights(int index, double value) {
		weights.set(index, value);

	}

	public void setInput(int index, double value) {
		input.set(index, value);
	}

	public double calculateZ() {
		z = 0; // z = the sum of all the products of the weights and the inputs

		for (int i = 0; i < input.size(); i++) {
		
			double product = weights.get(i) * input.get(i);
			z += product;
		}
		return z;
	}

	public void sigmoid() {
		sigmoidVal = 1 / (1 + Math.pow(Math.E, -z));
	}

	public void calculateError(double inputs, double input2) {
		if ((inputs + input2) >= sum) {
			error = 1 - sigmoidVal;
		}

		else {
			error = 0 - sigmoidVal;

		}
	}

	public void updateW(int index) {
		double dSigmoid = ((Math.pow(Math.E, z)) / (Math.pow(1 + Math.pow(Math.E, z), 2))); // derivative
																							// of
																							// the
																							// sigmoid
																							// function

		double newW = weights.get(index) + 0.09 * dSigmoid * input.get(index) *  error;
//System.out.println("new w " +newW);
		weights.set(index, newW);
	}


}
