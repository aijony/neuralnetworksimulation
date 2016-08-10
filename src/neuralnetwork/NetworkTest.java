package neuralnetwork;
import java.util.Scanner;
public class NetworkTest extends NeuralNetworkManager{
	

	private Scanner in = new Scanner(System.in);
	
	public void testXOR() {
		
		neuronsPerLayer = new int[]{ 2, 10, 5, 1 };
		
		network = new NeuralNetwork(neuronsPerLayer, learningRate);

		// Inputs for xor
		inputs = new double[][] { { 1, 1 }, { 1, 0 }, { 0, 1 }, { 0, 0} };

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
}
