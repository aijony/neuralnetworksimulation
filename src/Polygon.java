
public class Polygon implements RendererInterface{

	private Renderer renderer;
	public Polygon(int vertexAmount){
		renderer = new Renderer(new Vertices(vertexAmount));

	}
	public void update(){

		renderer.update();
		
	}
	
	public void update(Vertices vertexList){

		renderer.update(vertexList);
		
	}
	
	public void render(){
		renderer.render();
	}
	
	public void dispose(){
		
	}
}
