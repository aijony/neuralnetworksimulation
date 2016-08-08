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
			newActor = new Unit(count);
			break;
		case "Projectile":
			newActor = new Projectile((Unit) actors.get(originUnit));
			break;
		default:
			newActor = new Actor();
		}
		VertexMatrix vertices = new VertexMatrix(newActor.getNumSides());
		vertices.setPosition(newActor.getPositionMatrix());
		vertices.setColor(color);
		newActor.setVertexMatrix(vertices);
		newActor.initializeMovement(Point.randomPoint(new RangeSet()));
		newActor.setIndex(count);
		actors.add(SpriteManager.newSprite(), newActor);
		
		count++;
		
	}
	
	public static void update(int index){
		actors.get(index).update();
		SpriteManager.update(actors.get(index).getVertexMatrix(), index);
		if (actors.get(index).outOfBounds() || actors.get(index).collision()){
			if (actors.get(index).collision())
				System.out.println("Projectile " + index + " hit its target");
			actors.remove(index);
			SpriteManager.dispose(index);
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
}