package graphics;
import java.util.ArrayList;

public class SpriteManager{

	private static ArrayList<Polygon> sprites;
	
	private static int count;
	
	public SpriteManager(){
		
	}
	public static void initialize(){
		sprites = new ArrayList<Polygon>();
		count = 0;
	}
	/*
	 * Receives a sent polygon and return ID
	*/
	public static int newSprite(){
		sprites.add(count, new Polygon(4));
		return count++;
	}
	
	public static void update(VertexMatrix vertices, int index) {
		sprites.get(index).update(vertices);
	}

	public static void render() {
		for(int index = 0; index < sprites.size(); index++){
			sprites.get(index).render();
		}
	}
	
	public static void disposeAll() {
		for(int index = 0; index < sprites.size(); index++){
			dispose(index);
		}
	}
	public static void dispose(int index){
		sprites.get(index).dispose();
		sprites.remove(index);
		count--;
	}
}
