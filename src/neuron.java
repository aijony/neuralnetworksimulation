import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class neuron{
	
	private ArrayList<Double> weights = new ArrayList<Double>();
	private ArrayList<Double> input = new ArrayList<Double>();
	private int trials , correctTrials;
	private final int sum = 10;
	private double z, sigmoidVal, error;
	public neuron(){
	
		weights.add(.5);
		weights.add(.5);
	}
	
	public double calculateZ(){
		z = 0;
		for(int i = 0; i < input.size(); i++ ){
		
			double product = weights.get(i)*input.get(i);
			z +=product;
		}
		return z;
	}
	public void simulateNeuron(){
		calculateZ();
		sigmoidVal = sigmoid();
		if(sigmoidVal>=0.8){
			System.out.println("The sum of the inputs is >="+sum);
			if( (input.get(0)+input.get(1))>=sum){
				correctTrials++;  
			}
			 trials++;
		}
		else{
			System.out.println("The sum of the inputs is < "+sum);
			
			if( (input.get(0)+input.get(1))<sum){
				correctTrials++;  
			}
			 trials++;
		}
		calculateError();
		for(int i = 0; i<weights.size(); i++){
		updateW(i);
		}
	}
	
	public double sigmoid(){
		return  1/(1+Math.pow(Math.E, -z));
	}
	
	public void calculateError(){
		if( (input.get(0)+input.get(1))>=sum){
			error= 1-sigmoidVal;
		}
		
		else{
			error= 0-sigmoidVal;
			
		}
	}
	
	public void accuracy(){
		
		System.out.println( "percent of correct trials: "+correctTrials/(double)trials);
	}
	
	

	
	public void updateW(int index){
		 double dSigmoid= ((Math.pow(Math.E, z))/(Math.pow(1+Math.pow(Math.E, z),2))); //derivative of the sigmoid function
		 
		 double weightedError = error * weights.get(index); //error multiplied by weight of that connection

		 double newW = weights.get(index) + 0.09*dSigmoid*input.get(index)*weightedError;
	
		
		weights.set(index, newW);
	}
	
	
	public static void main(String[] args){
		neuron n = new neuron();
		neuron n2 = new neuron();
		neuron nh = new neuron();
		neuron nh2 = new neuron();
		neuron no = new neuron();
		Scanner in = new Scanner(System.in);
		Random r = new Random();
		int temp= 0;
		n.input.add(null);
		n.input.add(null);
		n2.input.add(null);
		n2.input.add(null);
		nh.input.add(null);
		nh.input.add(null);
		nh2.input.add(null);
		nh2.input.add(null);
		no.input.add(null);
		no.input.add(null);
		double rand;
		double rand2;
		//while(temp!=-109){
		for(int i =0; i<1000; i++){
			rand = 6;//(double)(r.nextInt(10)+1);
			rand2 = 6;//(double)(r.nextInt(10)+1);
		//System.out.println("original w = "+n.weights.get(0));
		//System.out.println("original w2 = "+n.weights.get(1));
		//System.out.println("Enter the first input value");
		n.input.set(0, rand);//Integer.parseInt(in.nextLine()));
	//	System.out.println("Enter the second input value");
		n.input.set(1, rand2); //Integer.parseInt(in.nextLine()));
		n.simulateNeuron();
		
		n2.input.set(0, n.input.get(0));//Integer.parseInt(in.nextLine()));
		//	System.out.println("Enter the second input value");
		n2.input.set(1, n.input.get(1)); //Integer.parseInt(in.nextLine()));
		n2.simulateNeuron();
		
		nh.input.set(0, n.sigmoidVal);//Integer.parseInt(in.nextLine()));
		//	System.out.println("Enter the second input value");
		nh.input.set(1, n.sigmoidVal); //Integer.parseInt(in.nextLine()));
		nh.simulateNeuron();
			
		nh2.input.set(0, n2.sigmoidVal);//Integer.parseInt(in.nextLine()));
		//	System.out.println("Enter the second input value");
		nh2.input.set(1, n2.sigmoidVal); //Integer.parseInt(in.nextLine()));
		nh2.simulateNeuron();
		
		
		no.input.set(0, nh.sigmoidVal);//Integer.parseInt(in.nextLine()));
		//	System.out.println("Enter the second input value");
		no.input.set(1, nh2.sigmoidVal); //Integer.parseInt(in.nextLine()));
		no.simulateNeuron();
		System.out.println(no.sigmoidVal);
		System.out.println("Error calculation is: "+no.error);
		System.out.println("new w = "+no.weights.get(0));
		System.out.println("new w2 = "+no.weights.get(1));
		System.out.println("inputs: " +rand+" "+rand2);
		System.out.println("trials: "+ no.trials+ "correct trials: "+no.correctTrials);
		no.accuracy();
		}
	
	}
	
}
