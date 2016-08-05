package simulation;

import graphics.Color;

public class Unit extends Actor {
	
	
	private int damage;			//health of target depleted on "attack"
	private int health;			//amount of damage unit can take before target wins
	
	
	
	private int targetUnitIndex;	//opponent unit
	
	public Unit(){
		super();
		damage = 10;
		health = 100;
		setName("Unit " + getIndex());
	}
	
	/*
	 * @brief	constructors, set and get functions
	 */
	public Unit(int d, int h, double s, int c, String n, Point p) 
	{
		super(p, 0, s, n, .05, 4);
		damage = d; health = h; 
	}
	
	//public int getRange(){	return range;}
	public int getDamage(){	return damage;}
	public int getHealth(){	return health;}
	public int decreaseHealth(int amount){	health -= amount;	return health;}
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

		System.out.println(ActorManager.getActor(getIndex()).getName() + " just updated");
		if(isReady()){
			if ((int)(Math.random() * 2) == 0)
				initializeMovement(Point.randomPoint(-1, -1, 1, 1));
			else {
				fire(Point.randomPoint(-1, -1, 1, 1));
				System.out.println("Unit is firing");
			}
		}
		else{
			moveTo();
		}
			
			
	}
	
	public boolean isProjectile(){return false;}
	public boolean outOfBounds(){return false;}
	
	
	
	
	
	
	
}