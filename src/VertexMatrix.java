import java.util.ArrayList;


public class VertexMatrix {

	private ArrayList<Vertex> vertexList;
	private int vertexSize;
	private int vertexAmount;
	
	

	/*
 	* Creates the amount of vertices given and sets fields 
 	*/
	public VertexMatrix(int amount){
		vertexAmount = amount;
		vertexList = new ArrayList<Vertex>(vertexAmount);
		for(int count = 0; count < vertexAmount; count ++){
			vertexList.add(new Vertex());
		}
		vertexSize = vertexList.get(0).toArray().length;
	}
	

	/*
 	* Returns an array that can be converted to shortbuffer for openGL
 	*/
	public float [] toArray(){
		float [] vertices = new float[vertexAmount * vertexSize ];
		int index = 0;

		//System.out.println(vertexList.get(0).getPosition().getX());
		for(Vertex vertex : vertexList){

			for(float data : vertex.toArray()){
				vertices[index++] = data;
			}
		}
	
		
		
		return vertices;
	}

	/*
 	* Creates the correct indices based off of the amount of vertices
 	* Scales for any polygon
 	*/
	public short[] getIndices() {
		short[] indices = new short[(vertexAmount - 2) * 3];
		int index = 0;
		for(int count = 1; count <= vertexAmount - 2; count++){
			//Re-using 0 for every triangle's index reduces the polygon count and allows for a scalable loop.
			indices[index++] = 0;
			indices[index++] = (short) count;
			indices[index++] = (short) (count + 1);
		}
		
		

		
		return indices;
	}
	
	public void setPosition(PositionMatrix positions){
		for(int index = 0; index < vertexAmount; index++){
			Position inputPos = positions.getPosition(index);
			//System.out.println(index + ", " + inputPos.getX() + ", " + inputPos.getY() + ", " );
			vertexList.get(index).setPosition(inputPos);
			//System.out.print(vertexList.get(index).getPosition().getX());
		}
	}
	
	public void setColor(Color colorIn){
		for(int index = 0; index < vertexAmount; index++){
			vertexList.get(index).setColor(colorIn);
		}
	}
}
