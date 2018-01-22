package simulation;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import graphics.Color;
import graphics.SpriteManager;
import graphics.VertexMatrix;
public class ActorManager {
	private static ArrayList<Actor> actors;
	private static int count;
	private static int steps;
	private static boolean useSpriteManager;
	
	
	public static CountDownLatch ready = new CountDownLatch(0);
	public static void initialize(boolean sprites){
		actors = new ArrayList<Actor>();
		count = 0;
		steps = 0;
		useSpriteManager = sprites;
	}
	
	public static int addActor(Color color, String type, int originUnit){
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
		if (useSpriteManager){
			count = SpriteManager.newSprite();
			actors.add(count, newActor);}
		else{
			actors.add(count, newActor);
		
		}
		return count++;	
		
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
			((Unit) actors.get(actors.get(index).originUnitIndex)).waitReload.countDown();
			actors.remove(index);
			if (useSpriteManager)
				count = SpriteManager.dispose(index);
			else
				count--;
			
		}
	}
	
	public static void updateAll(){
		ready = new CountDownLatch(1);
		for (int x = 0; x < actors.size(); x++){
			update(x);
		}	
		steps++;
		
		ready.countDown();
	}
	
	public static Actor getActor(int index){
		return actors.get(index);
	}
	public static int getSize(){
		return actors.size();
	}
	public static ReturnData[] exportData(){
		ReturnData[] data = new ReturnData[400];
		for (int x = 0; x < getSize(); x++){
			data[x] = exportDatum(x);
		}
		for (int x = getSize(); x < 400; x++){
			data[x] = new ReturnData();
		}
		return data;
	}
	public static ReturnData exportDatum(int index){
		if(index >= actors.size()){
			return new ReturnData();
		}
		return new ReturnData(actors.get(index));
	}
	public static int getOriginProjectileIndex(int index){
		for (int x = 2; x < actors.size(); x++)
			if (actors.get(x).originUnitIndex == index)
				return x;
		return 2;
	}
}