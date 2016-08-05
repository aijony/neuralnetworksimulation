/**
 * 
 */
package utilities;

/**
 * @author aidan
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double [][] operator = {
		{1,
		 3, 
		 1
		}};
		
		double [][] operand = {{1}, {2}};
		

		double [][] product =  MatrixMath.multiply(operator, operand);
		for(int i = 0; i < product[0].length; i++){
			for(int j = 0; j < product.length; j++){
			System.out.print(product[j][i] + " ");
			}
			System.out.println();
		}
		
	}

}