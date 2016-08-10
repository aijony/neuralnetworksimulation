package neuralnetwork;



public abstract class NeuralNetworkManager {

	protected double inputs[][];
	
	protected double expectedOutputs[][];
	protected final boolean isTrained = false;

	protected double learningRate = 12.2;
	protected int maxRuns = 6000;
	protected double minErrorCondition = 0.001;

	// NPL is neurons per layer
	protected int neuronsPerLayer[];

	protected NeuralNetwork network;



	
	/*
	 * @params If truue the results will be printed
	 */
	public void results(boolean print) {
		// Propagates through all different inputs
		for (int inputIndex = 0; inputIndex < inputs.length; inputIndex++) {
			network.propagate(inputs[inputIndex], expectedOutputs[inputIndex]);
			if (print)
				printResult(inputIndex);
		}
	}

	/* Prints the current target, actual, and output */
	private void printResult(int inputIndex) {
		System.out.println("Input: " + doubleArrayToString(inputs[inputIndex]) + "Target: "
				+ doubleArrayToString(expectedOutputs[inputIndex]) + "Actual: "
				+ doubleArrayToString(network.getOutput()[0]));
	}

	/* Converts a double to a string */
	private String doubleArrayToString(double[] input) {

		String outString = "";
		for (double out : input) {

			outString = outString + out + " ";
		}
		return outString;
	}

}
