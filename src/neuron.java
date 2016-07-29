import java.util.*;

public class neuron {

	private ArrayList<Double> weights = new ArrayList<Double>();
	private ArrayList<Double> input = new ArrayList<Double>();
	private int trials, correctTrials;
	private final int sum = 18; // the number that the neural network is testing
								// to see if is greater than or less than the
								// sum of the input values
	private double z, sigmoidVal, error;

	public neuron() {

		Random r = new Random();
		weights.add(r.nextDouble());
		weights.add(r.nextDouble());
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

	public void simulateNeuron() {
		calculateZ();
		sigmoidVal = sigmoid();
		if (sigmoidVal >= 0.8) { // a value of 0.4 is good when sum is set to 10
									// 0.8 is good when it is on either end
			System.out.println("The sum of the inputs is >=" + sum);
			if ((input.get(0) + input.get(1)) >= sum) {
				correctTrials++;
			}
			trials++;
		} else {
			System.out.println("The sum of the inputs is < " + sum);

			if ((input.get(0) + input.get(1)) < sum) {
				correctTrials++;
			}
			trials++;
		}
		calculateError();
		for (int i = 0; i < weights.size(); i++) {
			updateW(i);
		}
	}

	public double sigmoid() {
		return 1 / (1 + Math.pow(Math.E, -z));
	}

	public void calculateError() {
		if ((input.get(0) + input.get(1)) >= sum) {
			error = 1 - sigmoidVal;
		}

		else {
			error = 0 - sigmoidVal;

		}
	}

	public void accuracy() {

		System.out.println("percent of correct trials: " + correctTrials / (double) trials);
	}

	public void updateW(int index) {
		double dSigmoid = ((Math.pow(Math.E, z)) / (Math.pow(1 + Math.pow(Math.E, z), 2))); // derivative
																							// of
																							// the
																							// sigmoid
																							// function

		double weightedError = error * weights.get(index); // error multiplied
														   // by weight of that
														   // connection

		double newW = weights.get(index) + 0.09 * dSigmoid * input.get(index) * weightedError;

		weights.set(index, newW);
	}

	public static void main(String[] args) {
		neuron n = new neuron();
		Scanner in = new Scanner(System.in);
		Random r = new Random();
		int temp = 0;
		n.input.add(null);
		n.input.add(null);

		for (int i = 0; i < 1000000; i++) {

			n.input.set(0, (double) (r.nextInt(10) + 1));

			n.input.set(1, (double) (r.nextInt(10) + 1));
			n.simulateNeuron();

			n.accuracy();
		}

	}

}
