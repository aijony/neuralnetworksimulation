package utilities;

import simulation.ReturnData;
public class IOInteraction {
	public static double[] getInput(ReturnData data){
		
		double output[] = new double[4] ;
		
		output[0] = data.x;
		output[1] = data.y;
		output[2] = data.i;
		output[3] = data.j;
		
		return output;
		
	}
	
	public static double[] checkOutput(boolean success, double[][] calculated){
		if(success)
			return  MatrixMath.multiply(calculated, 3)[0];
		else
			return MatrixMath.multiply(calculated, -3)[0];
	}
	
	public static int getProjectileIndex(ReturnData originData, ReturnData[] data){
		int originUnitID = originData.originUnit;
		for(ReturnData otherUnit : data){
			if(otherUnit.targetUnit == originUnitID && otherUnit.originUnit != otherUnit.index){
				System.out.println(otherUnit.index);
				return otherUnit.index;
			}
		}
		return -1;
	}
	
	public static int getEnemyIndex(ReturnData data){
		return data.targetUnit;
	}
}
