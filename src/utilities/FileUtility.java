package utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtility {

	public static String loadString(String filepath) {
		StringBuilder shaderString = new StringBuilder();
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			while ((line = reader.readLine()) != null) {
				shaderString.append(line);
				shaderString.append('\n');
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load shader from file [" + filepath + "]", e);
		}

		return shaderString.toString();
	}
	
	public static void writeWeights( ArrayList<ArrayList<Double>> weights) {
		//close outFile reminder
	       PrintWriter outFile = null;
		try {
			outFile = new PrintWriter (new File("NNWeights.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	       //prints the number of layers
	       outFile.println(weights.size());
	       for(int i = 0; i < weights.size(); i++){
	    	   
	    	   //prints the number of neurons per layer
	    	   outFile.println(weights.get(i).size());
	       }
	       for(int i = 0; i < weights.size(); i ++){
	    	   for(int j = 0; j < weights.get(i).size(); j++){
	    		   //prints all of the weights
	    		   outFile.println(weights.get(i).get(j));
	    	   }
	       }
	       outFile.close ();
	      
	}
	
	public static ArrayList<ArrayList<Double>> readWeights(){
		
		ArrayList<ArrayList<Double>> weights = new ArrayList<ArrayList<Double>>();
		   File fileName = new File("NNWeights.txt");
		      Scanner inFile = null;
			try {
				inFile = new Scanner(fileName);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	
			}
		  
		      
		ArrayList<Integer> NPL = new ArrayList<Integer>();
		
		for(int i = inFile.nextInt(); i > 0; i-- ){
			
			NPL.add(null);
		}
		
		
		for(int i = 0; i < NPL.size(); i++){
	

			NPL.set(i, inFile.nextInt());

		}
		
       
		      for(int i = 0; i < NPL.size(); i++){
		    	  for(int j = 0; j < NPL.get(i); j++){
		    		  weights.add(new ArrayList<Double>());
		    		  weights.get(i).add(j, inFile.nextDouble());
		    		
		    	  }
		    	  
		      }
		         
		         
		         
		      inFile.close();
		
		
		return weights;	
	}
	public static void pweights(ArrayList<ArrayList<Double>> w){
	
		for(int i = 0; i < w.size(); i++){
			for(int j = 0; j < w.get(i).size(); j++){
				System.out.print(w.get(i).get(j)+"   ");
				
			}
			System.out.println();
		}
		
	}
	
//	public static void main(String []args){
//		ArrayList<ArrayList<Double>> w = new ArrayList<ArrayList<Double>>();
//
//		for(int i = 0; i < 5; i++){
//			for(int j = 0; j < 3; j++){
//				if(j == 0){
//					w.add(new ArrayList<Double>());
//				}
//				w.get(i).add(5.0);
//			}
//		}
//		
//		writeWeights(w);
//		
//		pweights(readWeights());
//		
//	}
//	
	
	
	
	
	
	
}
