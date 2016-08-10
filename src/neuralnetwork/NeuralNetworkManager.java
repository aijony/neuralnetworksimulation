package neuralnetwork;

import java.util.Scanner;

public class NeuralNetworkManager {

	private double inputs[][];
	private double expectedOutputs[][];
	private final boolean isTrained = false;

	private double learningRate = 12.2;
	private int maxRuns = 6000;
	private double minErrorCondition = 0.001;
	private Scanner in = new Scanner(System.in);

	// NPL is neurons per layer
	private int neuronsPerLayer[] = { 3, 10, 5, 1 };

	private NeuralNetwork network;

	public static void main(String[] args) {
		NeuralNetworkManager manager = new NeuralNetworkManager();
		manager.testGeneric();

	}

	public void testXOR() {

		network = new NeuralNetwork(neuronsPerLayer, learningRate);

		// Inputs for xor
		inputs = new double[][] { { 1, 1}, { 1, 0}, { 0, 1 }, { 0, 0 } };

		// xor training data
		expectedOutputs = new double[][] { { 0 }, { 1 }, { 1 }, { 0 } };

		int i;

		double error = 1;
		for (i = 0; i < maxRuns; i++) {
			error = 0;
			results(false);

		}
		results(true);
	}
	
	public void testGeneric(){
		System.out.println("How many layers would you like to have");	
		int [] NPLGeneric = new int[in.nextInt()];
		for(int i = 0; i < NPLGeneric.length; i ++){
			System.out.println("How many neurons would you like in layer #"+i+"?");
			NPLGeneric[i] = in.nextInt();
			
			network = new NeuralNetwork(NPLGeneric, learningRate);
			}
		System.out.println("How many test scenarios would you like to have?");
		int testScenarios = in.nextInt();
		System.out.println("How many inputs do you want in a test case?");
		System.out.println("NOTE: ALL inputs are DOUBLES");
		int ins = in.nextInt();
		inputs = new double[testScenarios][ins];
		expectedOutputs = new double[testScenarios][NPLGeneric[NPLGeneric.length-1]];
		
		for(int i = 0; i < testScenarios; i++){
			for(int j = 0; j < ins; j++){
				System.out.println("Please enter input #"+j+" for testScenario #"+i);
				inputs[i][j] = in.nextDouble();
				if(j == ins-1){
			for(int b = 0; b < NPLGeneric[NPLGeneric.length-1]; b++){
					System.out.println("Please enter expected output #"+j+" for testScenario #"+i);
					expectedOutputs[i][b] = in.nextDouble();
				
				}
				}
				
			}
		}
	
		int i;

		double error = 1;
		for (i = 0; i < maxRuns; i++) {
			error = 0;
			results(false);

		}
		results(true);
	}

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
