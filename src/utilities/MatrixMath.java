package utilities;


//NOTICE improper use of matrices will result in a array error, no pre computation checking is done by
//these methods
public class MatrixMath {
	
	
	/* Matrix multiples 2 matrices, operator X size must be equal to operand Y size
	 * Please have a understanding of matrix multiplication before using this code
	 * */
	public static double [][] multiply(double [][] operator, double [][] operand){
		
		//operatorColumnAmnt and operandRowAmnt amount must be equal
		int definedDimensionSize = operator.length;
 
		
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
	/* Switches the x and y values of the matrix*/
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
	
	/* Calculates sigmoid of matrix*/
	public static double [][] sigmoid(double [][] input){
	
		int lengthX = input.length;
		int lengthY = input[0].length;
		double [][] output = new double[lengthX][lengthY];
		for(int x = 0; x < lengthX; x++){
			for(int y = 0; y < lengthY; y++){
				output[x][y]  =sigmoid(input[x][y]);
			}
		}
		return output;
		
	}
	/* Calculates sigmoid of a single double */
	public static double  sigmoid(double  input){
		
		return 1.0 / (1.0 +  (Math.exp(-input)));
	}
		
	
	/* Sigmoid prime for matrix */
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

	/* Sigmoid prime for double*/
	public static double sigmoidPrime(double input) {
		return (Math.exp(input)) / Math.pow((1.0  +  (Math.exp(input))), 2);
	}
	
	
	
	/*Multiplies a matrix by a scaler to scale the matrix */
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
	
	/* Subtracts all values from one matrix to another
	 * The matrices must be identical in size
	 * */
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
	
	//Sets a  1D array row to a matrix column
	public static double [][] rowToMatrix(double [] in) {
    	double[][] rowToMatrix = new double [1][in.length];
    	rowToMatrix[0] = in;
		return rowToMatrix;
    }
	
	

	//Converts a 1D array into a 2D array in the inverse dimension of row to matrix
	public static double[][] rowToMatrixColumn(double[] in) {
		double[][] columnToMatrix = new double [in.length][1];
    	for(int index = 0; index < in.length; index++){
    		columnToMatrix[index][0] = in[index];
    	}
		return columnToMatrix;
	}

	//Prints Matrices for testing purposes
	public static void printMatrix(double [][] product){
		for(int i = 0; i < product[0].length; i++){
			for(int j = 0; j < product.length; j++){
			System.out.print(product[j][i] + " ");
			}
			System.out.println();
		}
	
}

}
