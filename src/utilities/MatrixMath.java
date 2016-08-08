package utilities;

public class MatrixMath {
	
	public static double [][] multiply(double [][] operator, double [][] operand){
		
		//operatorColumnAmnt and operandRowAmnt amount must be equal
		int definedDimensionSize = operator.length;
 
		/*if( definedDimensionSize  != operand[0].length){
			//Matrices are undefined
			return null;
		}*/
		int operatorRowAmnt = operator[0].length;
		int operandColumnAmnt = operand.length;
		double [][] product = new double [operandColumnAmnt][operatorRowAmnt];
		for(int operatorRow = 0; operatorRow < operatorRowAmnt; operatorRow++){
			for(int operandColumn = 0; operandColumn < operandColumnAmnt; operandColumn++){
				for(int operateIndex = 0; operateIndex < definedDimensionSize;  operateIndex++){
					product [operandColumn] [operatorRow] 
							= product [operandColumn] [operatorRow] 
							+ ( operator [operateIndex] [operatorRow]
							* operand [operandColumn] [operateIndex]); 
				}
			}
		}
		return product;
	}
	/*
	 * Switches the x and y values of the matrix
	*/
	public static double [][] transpose(double [][] input){
		double[][] output = new double[input[0].length][input.length];
		for(int dimensionTwo = 0; dimensionTwo < output.length; dimensionTwo++){
			for(int dimensionOne = 0; dimensionOne < output[0].length; dimensionOne++){	
			//Put input row into output column
			output[dimensionTwo][dimensionOne] = input[dimensionOne][dimensionTwo] ;
			}
		}
		return output;
	}
	
	
	public static double [][] sigmoid(double [][] input){
	
		int lengthX = input.length;
		int lengthY = input[0].length;
		double [][] output = new double[lengthX][lengthY];
		for(int x = 0; x < lengthX; x++){
			for(int y = 0; y < lengthY; y++){
				output[x][y]  = 1.0 / (1.0 +  (Math.exp(-input[x][y])));;
			}
		}
		return output;
		
	}
	

	public static double [][] sigmoidPrime(double [][] input){
		int lengthX = input.length;
		int lengthY = input[0].length;
		double [][] output = new double[lengthX][lengthY];
		for(int x = 0; x < lengthX; x++){
			for(int y = 0; y < lengthY; y++){
				output[x][y]= sigmoidPrime(input[x][y]);
			}
		}
		return output;
	}
	
	
	
	public static double [][] multiply(double [][] operator, double scaler){
		int lengthX = operator.length;
		int lengthY = operator[0].length;
		double [][] output = new double[lengthX][lengthY];
		for(int x = 0; x < lengthX; x++){
			for(int y = 0; y < lengthY; y++){
				output[x][y] = operator[x][y] * scaler;
			}
		}
		return output;
	}
	
	public static double [][] subtract(double [][] operator, double [][]operand){
		int lengthX = operator.length;
		int lengthY = operator[0].length;
		double [][] output = new double[lengthX][lengthY];
			
		for(int x = 0; x < lengthX; x++){
			for(int y = 0; y < lengthY; y++){
				output[x][y] = operator[x][y] - operand[x][y];
			}
		}
		return output;
	}
	
	public static double [][] rowToMatrix(double [] in) {
    	double[][] rowToMatrix = new double [1][in.length];
    	rowToMatrix[0] = in;
		return rowToMatrix;
    }
	
	


	public static double sigmoidPrime(double input) {
		return (Math.exp(input)) / Math.pow((1.0  +  (Math.exp(input))), 2);
	}
	
	public static double[][] columnToMatrix(double[] in) {
		double[][] columnToMatrix = new double [in.length][1];
    	for(int index = 0; index < in.length; index++){
    		columnToMatrix[index][0] = in[index];
    	}
		return columnToMatrix;
	}


}
