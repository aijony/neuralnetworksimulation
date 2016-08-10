package simulation;
import java.util.ArrayList;

import graphics.Color;
import graphics.SpriteManager;
import graphics.VertexMatrix;
public class ActorManager {
	private static ArrayList<Actor> actors;
	private static int count;
	private static int steps;
	private static boolean useSpriteManager;
	
	public static void initialize(boolean sprites){
		actors = new ArrayList<Actor>();
		count = 0;
		steps = 0;
		useSpriteManager = sprites;
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
		newActor.setMovementRanges(-1, -1, 1, 1);
		newActor.initializeMovement(Point.randomPoint(newActor.movementRanges));
		newActor.setIndex(count);
		if (useSpriteManager)
			actors.add(SpriteManager.newSprite(), newActor);
		else
			actors.add(count, newActor);
		count++;
		
	}
	
	public static void update(int index){
		actors.get(index).update();
		if (useSpriteManager)
			SpriteManager.update(actors.get(index).getVertexMatrix(), index);
		if (actors.get(index).outOfBounds() || actors.get(index).collision()){
			if (actors.get(index).collision()){
				actors.get(actors.get(index).targetUnitIndex).hasBeenHit = true;
				actors.get(actors.get(index).originUnitIndex).successfulHit = true;
			}
			actors.get(actors.get(index).originUnitIndex).setCanFire(true);
			actors.remove(index);
			if (useSpriteManager)
				SpriteManager.dispose(index);
			count--;
			
		}
	}
	
	public static void updateAll(){
		for (int x = 0; x < count; x++){
			update(x);
		}	
		steps++;
		//System.out.println(steps);
		if (steps % 15 == 0){
			exportData();
			
		}
	}
	
	public static Actor getActor(int index){
		return actors.get(index);
	}
	public static int getSize(){
		return actors.size();
	}
	public static ReturnData[] exportData(){
		ReturnData[] data = new ReturnData[4];
		for (int x = 0; x < getSize(); x++){
			data[x] = exportDatum(x);
			actors.get(x).hasBeenHit = false;
			actors.get(x).successfulHit = false;
		}
		for (int x = getSize(); x < 4; x++){
			data[x] = new ReturnData();
		}
		return data;
	}
	public static ReturnData exportDatum(int index){
		return new ReturnData(actors.get(index));
	}
	public static int getOriginProjectileIndex(int index){
		for (int x = 2; x < actors.size(); x++)
			if (actors.get(x).originUnitIndex == index)
				return x;
		return 2;
	}
}