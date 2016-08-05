package utilities;

public class MatrixMath {
	public static double [][] multiply(double [][] operator, double [][] operand){
		
		
		//operatorColumnAmnt and operandRowAmnt amount must be equal
		int definedDimensionSize = operator.length;
 
		if( definedDimensionSize  != operand[0].length){
			//Matrices are undefined
			return null;
		}
		
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
	
	public static double [][] multiply(double [][] operator, double scaler){
		for(int x = 0; x < operator.length; x++){
			for(int y = 0; y < operator[0].length; y++){
				operator[x][y] *= scaler;
			}
		}
		return operator;
	}
}
