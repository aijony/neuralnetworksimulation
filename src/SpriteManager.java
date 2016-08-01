import java.util.ArrayList;

public class SpriteManager implements RendererInterface{

	private static ArrayList<Polygon> sprites;
	
	private static int count;
	
	public SpriteManager(){
		sprites = new ArrayList<Polygon>();
	}
	
	/*
	 * Recieves a sent polygon and return ID
	*/
	public int newSprite(Polygon object){
		sprites.add(count, object);
		return count++;
	}
	
	public void update(Polygon object, int index) {
		sprites.set(index, object);		
	}

	@Override
	public void render() {
		for(int index = 0; index < sprites.size(); index++){
			sprites.get(index).render();
		}
	}

	@Override
	public void dispose() {
		for(int index = 0; index < sprites.size(); index++){
			sprites.get(index).dispose();
		}
	}


}
