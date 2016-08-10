/**
 * 
 */
package scenarios;

import utilities.MatrixMath;

/**
 * @author aidan Matrix multiplication test
 */
public class MatrixMathTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double[][] operator = { { 1, 3, 1 } };

		double[][] operand = { { 1, 1 }, { 2, 3 } };
		double[][] operandTwo = { { 2, 1 }, { 21, 1 } };

		double[][] product = MatrixMath.subtract(operand, operandTwo);
		for (int i = 0; i < product[0].length; i++) {
			for (int j = 0; j < product.length; j++) {
				System.out.print(product[j][i] + " ");
			}
			System.out.println();
		}

	}

}