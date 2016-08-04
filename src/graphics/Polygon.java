package graphics;

public class Polygon implements RendererInterface{

	private Renderer renderer;
	private VertexMatrix vertices;
	public Polygon(int vertexAmount){
		renderer = new Renderer(new VertexMatrix(vertexAmount));
	}
	
	
	public void update(VertexMatrix vertexList){		
		setVertices(vertexList);
		renderer.update(getVertices());
	}
	
	public void render(){
		renderer.render();
	}
	
	public void dispose(){
		renderer.dispose();
	}
	public VertexMatrix getVertices() {
		return vertices;
	}
	public void setVertices(VertexMatrix vertexList) {
		vertices = vertexList;
	}
}
