import java.util.ArrayList;
public class ActorManager {
	private static ArrayList<Actor> actors;
	private static int count;
	
	public ActorManager(){
		
	}
	public static void initialize(){
		actors = new ArrayList<Actor>();
		count = 0;
	}
	public static void addActor(Actor newActor){
		actors.add(count++, newActor);
		newActor.setIndex(SpriteManager.newSprite());
		
	}
	public static void update(int index){
		actors.get(index).getVertexMatrix().setPosition(actors.get(index).getPositionMatrix());
		SpriteManager.update(actors.get(index).getVertexMatrix(), index);
	}
}
