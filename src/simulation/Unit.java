package simulation;

import graphics.Color;

public class Unit extends Actor {
	
	
	private boolean canFire;
	
	
	private int targetUnitIndex;	//opponent unit
	
	public Unit(int count){
		super();
		setName("Unit " + getIndex());
		setIndex(count);
		canFire = true;
		if (getIndex() == 0)
			targetUnitIndex = 1;
		else
			targetUnitIndex = 0;
		System.out.println("TargetUnitIndex of unit " + getIndex() + " is " + targetUnitIndex);
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
	
	
	/*
	 * 	@brief	sets target point to random x/y coordinate from (0, 0) to (100, 100)
	 * 
	 * 	@returns void
	 */
	
	
	private void fire(Point target){
		ActorManager.addActor(Color.blue(), "Projectile", getIndex());
	}

	public void update(){

		//System.out.println(ActorManager.getActor(getIndex()).getName() + " just updated");
		if(isReady()){
			if ((int)(Math.random() * 2) == 0){
				initializeMovement(Point.randomPoint(new RangeSet()));
				canFire = true;
			}
			else if (canFire){
				fire(Point.randomPoint(new RangeSet()));
				System.out.println("Unit " + getIndex() + " is firing");
				canFire = false;
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