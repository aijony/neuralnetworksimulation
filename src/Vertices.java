import java.util.ArrayList;


public class Vertices {
	private ArrayList<Vertex> vertexList;
	private int vertexSize;
	private int vertexAmount;
	
	public Vertices(int amount){
		vertexAmount = amount;
		vertexList = new ArrayList<Vertex>(vertexAmount);
		for(int count = 0; count < vertexAmount; count ++){
			vertexList.add(new Vertex());
			
		}
		vertexSize = vertexList.get(0).toArray().length;
	}
	
	
	public float [] toArray(){
		float [] vertices = new float[vertexAmount * vertexSize ];
		int index = 0;
		for(Vertex vertex : vertexList){

			for(float data : vertex.toArray()){
				
				vertices[index++] = data;
			}
		}
		
		return vertices;
	}

	public short[] getIndices() {
		short[] indices = new short[(vertexAmount - 2) * 3];
		int index = 0;
		for(int count = 1; count <= vertexAmount - 2; count++){
			indices[index++] = 0;
			indices[index++] = (short) count;
			indices[index++] = (short) (count + 1);
		}
		
		return indices;
	}
}
