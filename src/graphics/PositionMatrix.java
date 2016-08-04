package graphics;
import java.util.ArrayList;

public class PositionMatrix {

	private static ArrayList<Position> positions;
	
	public PositionMatrix(int vertexAmount){
		positions = new ArrayList<Position>(vertexAmount);
		for(int index = 0; index < vertexAmount; index ++){
			positions.add(new Position());
		}
	}
	
	public Position getPosition(int index){
		if(index >= positions.size()){
			return new Position();
		}
		else{
			return positions.get(index);
		}
			
	}
	
	public int setPosition(Position positionIn, int index){
		if(index >= positions.size()){
			return -1;
		}
		else{
			positions.set(index, positionIn);
			return index;
		}
	}
	
	public int getSize(){
		return positions.size();
	}

	public void setTest() {
		if(getSize() == 4){

		positions.get(0).set(-0.6f, +0.0f);
		positions.get(1).set(+0.0f, -0.5f); 
		
		positions.get(2).set(+0.5f, -0.0f);

		positions.get(3).set(+0.0f, +0.5f);
		}
		System.out.println(getSize());
	}
}
