package simulation;
import java.util.ArrayList;

import graphics.Color;
import graphics.SpriteManager;
import graphics.VertexMatrix;
public class ActorManager {
	private static ArrayList<Actor> actors;
	private static int count;
	
	public static void initialize(){
		actors = new ArrayList<Actor>();
		count = 0;
	}
	public static void addActor(Color color){
		
		Actor newActor = new Actor();
		VertexMatrix vertices = new VertexMatrix(newActor.getNumSides());
		vertices.setPosition(newActor.getPositionMatrix());
		vertices.setColor(color);
		newActor.setVertexMatrix(vertices);

		newActor.initializeMovement();
		actors.add(count++, newActor);
		newActor.setIndex(SpriteManager.newSprite());
	}
	
	public static void update(int index){
		actors.get(index).update();
		SpriteManager.update(actors.get(index).getVertexMatrix(), index);
	}
	public static Actor getActor(int index){
		return actors.get(index);
	}
	
}
