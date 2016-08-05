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
	
	public static void addActor(Color color, String type, int originUnit){
		Actor newActor;
		switch (type){
		case "Unit":
			newActor = new Unit();
			newActor.setIndex(count);
			break;
		case "Projectile":
			newActor = new Projectile((Unit) actors.get(originUnit));
			newActor.setIndex(count);
			break;
		default:
			newActor = new Actor();
			newActor.setIndex(count);
		}
		VertexMatrix vertices = new VertexMatrix(newActor.getNumSides());
		vertices.setPosition(newActor.getPositionMatrix());
		vertices.setColor(color);
		newActor.setVertexMatrix(vertices);
		newActor.setIndex(count);
		newActor.initializeMovement(Point.randomPoint(-1, -1, 1, 1));
		SpriteManager.newSprite();
		actors.add(count++, newActor);
		
	}
	
	public static void update(int index){
		actors.get(index).update();
		SpriteManager.update(actors.get(index).getVertexMatrix(), index);
		if (actors.get(index).outOfBounds()){
			actors.remove(index);
			updateIndices(index);
			System.out.println("Removing item of index " + index + " from ActorManager");
			count--;
		}
	}
	
	public static void updateAll(){
		for (int x = 0; x < count; x++){
			update(x);
		}	
	}
	
	public static Actor getActor(int index){
		return actors.get(index);
	}
	
	public static void updateIndices(int startPoint){
		System.out.println("Calling updateIndices with startPoint " + startPoint);
		for (int x = startPoint; x < actors.size(); x++){
			System.out.println("index before update: " + actors.get(x).getIndex());
			actors.get(x).setIndex(actors.get(x).getIndex() - 1);
			System.out.println("index after update: " + actors.get(x).getIndex());
		}
	}
}
