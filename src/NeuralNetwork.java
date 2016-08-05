
import java.util.*;
 
public class NeuralNetwork {

    final boolean isTrained = false;
    final Random rand = new Random();
	private Scanner in = new Scanner(System.in);
	private ArrayList<ArrayList<Neuron>> neuralNetwork = new ArrayList<ArrayList<Neuron>>();
    final Neuron bias = new Neuron();

 
    final double learningRate = 0.9;
    final double momentum = 0.7;
 
    // Inputs for xor
    final double inputs[][] = { { 1, 1 }, { 1, 0 }, { 0, 1 }, { 0, 0 } };
 
    //  xor training data
    final double expectedOutputs[][] = { { 0 }, { 1 }, { 1 }, { 0 } };
    double resultOutputs[][] = { { -1 }, { -1 }, { -1 }, { -1 } }; // fake init
    double output[];
 
    // for weight update all
    final HashMap<String, Double> weightUpdate = new HashMap<String, Double>();
 
	//returns a specified layer from the network
	public ArrayList<Neuron> getNeuralNetworkLayer(int layer) {
		return neuralNetwork.get(layer);
	}
	
	//returns a neuron given its position in the network
	public Neuron getNeuron(int layer, int neuron){
		return neuralNetwork.get(layer).get(neuron);
	}

    
    public static void main(String[] args) {
       
        ArrayList<Integer> NPL = new ArrayList<Integer>();
        NPL.add(2);
        NPL.add(5);
        NPL.add(1);
    	
    	NeuralNetwork nn = new NeuralNetwork(NPL);
 
        int maxRuns = 500000;
        double minErrorCondition = 0.0001;
        nn.run(maxRuns, minErrorCondition);
    }
 
    public NeuralNetwork( ArrayList<Integer> neuronsPerLayer) {

		// adds the correct number of layers to the neural network
		for (int currentLayer = 0; currentLayer < neuronsPerLayer.size(); currentLayer++) {
			neuralNetwork.add(new ArrayList<Neuron>());
		}

		// adds the correct number of neurons to each layer of the neural
		// network
		for (int currentLayer = 0; currentLayer < neuronsPerLayer.size(); currentLayer++) {
			for (int currentNeuron = 0; currentNeuron < neuronsPerLayer.get(currentLayer); currentNeuron++) {
				
				//if we are in the first layer numberInputs applies
				if(currentLayer == 0){
				getNeuralNetworkLayer(currentLayer).add(new Neuron());

				}
				
				//if we are not in the first layer the number of inputs = the size of the previous layer
				else{
				     Neuron neuron = new Neuron();
	                    neuron.addInConnectionsS(getNeuralNetworkLayer(currentLayer-1));
	                    neuron.addBiasConnection(bias);
	                    getNeuralNetworkLayer(currentLayer).add(neuron);
	                   ArrayList<Connection> connections = getNeuron(currentLayer,currentNeuron).getAllInConnections();
	                 
	                   for (Connection conn : connections) {
	                       double newWeight = getRandom();
	                       conn.setWeight(newWeight);
	                   }
		
				}
			}
		}

 
        // reset id counters
        Neuron.counter = 0;
        Connection.counter = 0;
 
        if (isTrained) {
            trainedWeights();
            updateAllWeights();
        }
    }
 
    // random
    double getRandom() {
        return (rand.nextDouble() * 2 - 1); // [-1;1[
    }
 
    /**
     * 
     * @param inputs
     *            There is equally many neurons in the input layer as there are
     *            in input variables
     */
    public void setInput(double inputs[]) {
        for (int i = 0; i < getNeuralNetworkLayer(0).size(); i++) {
        	getNeuralNetworkLayer(0).get(i).setOutput(inputs[i]);
        }
    }
 
    public double[] getOutput() {
        double[] outputs = new double[getNeuralNetworkLayer(neuralNetwork.size()-1).size()];
        for (int i = 0; i < getNeuralNetworkLayer(neuralNetwork.size()-1).size(); i++)
            outputs[i] = getNeuralNetworkLayer(neuralNetwork.size()-1).get(i).getOutput();
        return outputs;
    }
 
    /**
     * Calculate the output of the neural network based on the input The forward
     * operation
     */
	public void activate() {

		for (int i = 1; i < neuralNetwork.size(); i++){
			for (Neuron n : getNeuralNetworkLayer(i)) {
				n.calculateOutput();
			}
		}
	}

    /**
     * all output propagate back
     * 
     * @param expectedOutput
     *            first calculate the partial derivative of the error with
     *            respect to each of the weight leading into the output neurons
     *            bias is also updated here
     */
    public void applyBackpropagation(double expectedOutput[]) {
 
 
        int i = 0;
        for (Neuron n : getNeuralNetworkLayer(neuralNetwork.size()-1)) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double out = n.getOutput();
                double input = con.leftNeuron.getOutput();
                double desiredOutput = expectedOutput[i];
 
                double partialDerivative = - input * (desiredOutput - out) * out * (1 - out) ; //the partial derivative of ETotal with respect to con
                double deltaWeight = -learningRate * partialDerivative; //the amount added to the current weight to update it
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
            i++;
        }
 
        // update weights for the hidden layer
        for (Neuron n : getNeuralNetworkLayer(neuralNetwork.size()-2)) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double out = n.getOutput();
                double input = con.leftNeuron.getOutput();
                double sumoutputs = 0;
                int j = 0;
                for (Neuron out_neu : getNeuralNetworkLayer(neuralNetwork.size()-1)) {
                    double weight = out_neu.getConnection(n.id).getWeight();
                    double desiredOutput = (double) expectedOutput[j];
                    double outl = out_neu.getOutput();
                    j++;
                    sumoutputs = sumoutputs
                            + (-(desiredOutput - outl) * outl * (1 - outl) * weight);
                }
 
                double partialDerivative = sumoutputs *  out * (1 - out) * input;
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
        }
    }
 
    void run(int maxSteps, double minError) {
        int i;
        // Train neural network until minError reached or maxSteps exceeded
        double error = 1;
        for (i = 0; i < maxSteps && error > minError; i++) {
            error = 0;
            for (int p = 0; p < inputs.length; p++) {
                setInput(inputs[p]);
 
                activate();
 
                output = getOutput();
                resultOutputs[p] = output;
 
                for (int j = 0; j < expectedOutputs[p].length; j++) {
                    double err = Math.pow(output[j] - expectedOutputs[p][j], 2);
                    error += err;
                }
 
                applyBackpropagation(expectedOutputs[p]);
      
               // printResult();
         
            }
        }
 
        printResult();
         
        System.out.println("# Trials " + i+"\n");
        if (i == maxSteps) {
            System.out.println("Training complete");
        } 
    }
     
    void printResult()
    {
        for (int p = 0; p < inputs.length; p++) {
            System.out.print("Inputs: ");
            for (int x = 0; x < getNeuralNetworkLayer(0).size(); x++) {
                System.out.print(inputs[p][x] + " ");
            }
 
            System.out.print("Expected: ");
            for (int x = 0; x < getNeuralNetworkLayer(2).size(); x++) {
                System.out.print(expectedOutputs[p][x] + " ");
            }
 
            System.out.print("Actual: ");
            for (int x = 0; x < getNeuralNetworkLayer(2).size(); x++) {
                System.out.print(resultOutputs[p][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
 
    String weightKey(int neuronId, int conId) {
        return "N" + neuronId + "_C" + conId;
    }
 
    
    // Take from hash table and put into all weights
    public void updateAllWeights() {
        // update weights for the output layer
        for (Neuron n : getNeuralNetworkLayer(neuralNetwork.size()-1)) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String key = weightKey(n.id, con.id);
                double newWeight = weightUpdate.get(key);
                con.setWeight(newWeight);
            }
        }
        // update weights for the hidden layer
        for (Neuron n : getNeuralNetworkLayer(neuralNetwork.size()-2)) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String key = weightKey(n.id, con.id);
                double newWeight = weightUpdate.get(key);
                con.setWeight(newWeight);
            }
        }
    }
 
    // trained data
    void trainedWeights() {
        weightUpdate.clear();
         
        weightUpdate.put(weightKey(3, 0), 1.03);
        weightUpdate.put(weightKey(3, 1), 1.13);
        weightUpdate.put(weightKey(3, 2), -.97);
        weightUpdate.put(weightKey(4, 3), 7.24);
        weightUpdate.put(weightKey(4, 4), -3.71);
        weightUpdate.put(weightKey(4, 5), -.51);
        weightUpdate.put(weightKey(5, 6), -3.28);
        weightUpdate.put(weightKey(5, 7), 7.29);
        weightUpdate.put(weightKey(5, 8), -.05);
        weightUpdate.put(weightKey(6, 9), 5.86);
        weightUpdate.put(weightKey(6, 10), 6.03);
        weightUpdate.put(weightKey(6, 11), .71);
        weightUpdate.put(weightKey(7, 12), 2.19);
        weightUpdate.put(weightKey(7, 13), -8.82);
        weightUpdate.put(weightKey(7, 14), -8.84);
        weightUpdate.put(weightKey(7, 15), 11.81);
        weightUpdate.put(weightKey(7, 16), .44);
    }

}