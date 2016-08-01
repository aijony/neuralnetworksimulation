public class Unit extends Actor {
	
	
	//private int range;			//maximum distance another unit can be for this unit to have increased accuracy
	private int damage;			//health of target depleted on "attack"
	private int health;			//amount of damage unit can take before target wins
	
	
	
	private Unit targetUnit;	//opponent unit
	
	
	/*
	 * @brief	constructors, set and get functions
	 */
	public Unit(int r, int d, int h, double s, int c, String n, Point p) 
	{
		super(p, 0, s, n);
		//range = r;
		damage = d; health = h; 
	}
	
	//public int getRange(){	return range;}
	public int getDamage(){	return damage;}
	public int getHealth(){	return health;}
	public int decreaseHealth(int amount){	health -= amount;	return health;}
	
	
	/*
	 * 	@brief	sets target point to random x/y coordinate from (0, 0) to (100, 100)
	 * 
	 * 	@returns void
	 */
	public void initializeMovement(){
		Point targetPoint = randomPoint(-1, -1, 1, 1);
		System.out.println(getName() + " is moving from " + getPos() + " to " + targetPoint);
		moveTo(targetPoint);
	}
	
	private void fire(Point target){
		
	}
	
	public void attack(){
		
		
	}
	
	
	
	
	
	
	
	
	
	
}