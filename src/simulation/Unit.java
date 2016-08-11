package simulation;

import java.util.concurrent.CountDownLatch;

import graphics.Color;

public class Unit extends Actor {
	

	public CountDownLatch waitReload = new CountDownLatch(1);
	private boolean canFire;
	
	public Unit(int count){
		super();
		setName("Unit " + getIndex());
		setIndex(count);
		originUnitIndex = getIndex();
		canFire = true;
		if (getIndex() == 0)
			targetUnitIndex = 1;
		else
			targetUnitIndex = 0;
		
	}
	
	/*
	 * @brief	constructors, set and get functions
	 */
	public Unit(int d, int h, double s, int c, String n, Point p) 
	{
		
		super(p, 0, s, n, .05, 4);
		canFire = true;
	}
	
	public int getTargetUnitIndex(){return targetUnitIndex;}
	
	public void setCanFire(boolean trueOrFalse){

		canFire = trueOrFalse;}
	
	/*
	 * 	@brief	sets target point to random x/y coordinate from (0, 0) to (100, 100)
	 * 
	 * 	@returns void
	 */
	
	
	public void fire(Point target){
		System.out.println("FIRE THE LAZERS");
		waitReload = new CountDownLatch(1);
		successfulHit = false;
		ActorManager.addActor(Color.blue(), "Projectile", getIndex());
	}

	public void update(){

		//System.out.println(ActorManager.getActor(getIndex()).getName() + " just updated");
		if(isReady()){
			
			new RangeSet();
			if ((int)(Math.random() * 2) == 0){
				//initializeMovement(Point.randomPoint(movementRanges));
				
			}
			else if (canFire && ActorManager.getSize() < 4){
				//fire(Point.randomPoint(projectileRanges));
				setCanFire(false);
			}
		}
		else{
			moveTo();
		}
			
			
	}
	
	public boolean isProjectile(){return false;}
	public boolean outOfBounds(){return false;}
	public boolean collision(){return false;}
}