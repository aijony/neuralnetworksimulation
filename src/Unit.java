public class Unit {
	
	private String name;	//name of unit
	private int range;		//maximum distance another unit can be for this unit to have increased accuracy
	private int damage;		//health of target depleted on "attack"
	private int health;		//amount of damage unit can take before target wins
	private int speed;		//distance unit can cover in one movement step
	private Point loc;		//current position on xy plane
	
	
	/*
	 * @brief	constructors, set and get functions
	 */
	public Unit(int r, int d, int h, int s, int c, String n, Point p) 
	{
		range = r; damage = d; health = h; speed = s;
		name = n;
		loc = p;
	}
	public void setPos(Point position){	loc = position;	}
	public Point getPos(){	return loc;}
	public int getRange(){	return range;}
	public int getDamage(){	return damage;}
	public int getHealth(){	return health;}
	public int decreaseHealth(int amount){	health -= amount;	return health;}
	public int getSpeed(){	return speed;}
	
	public void initializeMovement(){
		Point targetPoint = new Point((int)(Math.random() * 100), (int)(Math.random() * 100));
		System.out.println(name + " is moving from " + loc + " to " + targetPoint);
		moveTo(targetPoint);
	}
	
	
	/*
	 * @brief	determines whether or not given point is within range
	 *			as related to range statistic 
	 * @param	targetPoint:	point to determine within-ranginess to
	 * @returns	true if targetPoint is within range; false if otherwise
	 */
	public boolean withinRange(Point targetPoint){
		double distanceToTarget = Math.sqrt(Math.pow(targetPoint.getX() - getPos().getX(), 2) 
		+ Math.pow(targetPoint.getY() - getPos().getY(), 2));
		System.out.println(distanceToTarget);
		System.out.println(getRange());
		return (distanceToTarget <= getRange());
	}
	/*
	 * @brief 	Moves this unt incrementally based on field 'speed' to newPoint
	 * @param	newPoint: the target destination for loc
	 * @returns	void
	 */
	public void moveTo(Point newPoint){
		double xDiff = newPoint.getX() - loc.getX();	//remaining difference between current and goal x
		double yDiff = newPoint.getY() - loc.getY();	//remaining difference between current and goal y
		
		double xMove; double yMove;		//delta x and y per step; hypotenuse of triangle formed
										//will be speed of unit
		
		double distance = (double)(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
		double angleToPoint = qualifyAngle(Math.acos(xDiff / distance) , isPositive(xDiff), isPositive(yDiff));
		while (!newPoint.equals(loc)){
			if (distance >= getSpeed()){
				xMove = getSpeed()*Math.cos(angleToPoint);
				yMove = getSpeed()*Math.sin(angleToPoint);
			}
			else {
				xMove = xDiff;
				yMove = yDiff;
			}
			
			stepTo(xMove, yMove);
			xDiff -= xMove;
			yDiff -= yMove;
			
			System.out.println("Location: " + loc + " Goal: " + newPoint);
			
			distance = (double)(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
			
		}
		
		
	}
	
	/*
	 * @brief	single increment for moveTo
	 * @param	xMove and yMove: values for delta x and delta y
	 */
	private void stepTo(double xMove, double yMove){
		setPos(new Point(((double)loc.getX() + xMove), ((double)loc.getY() + yMove)));
	}
	
	/*
	 * @brief	returns proper angle as it relates to its quadrant in the unit circle
	 * 			necessary because Math basic trig functions only go from 0-2PI
	 */
	private double qualifyAngle(double angle, boolean xPositive, boolean yPositive){
		if (xPositive && yPositive || !xPositive && yPositive)
			return angle;
		else
			return (2 * Math.PI) - angle;
	}
	/*
	 * @brief	returns whether or not a double is positive
	 */
	private boolean isPositive(double number){
		return (number >= 0);
	}
	/*
	 * @brief	overloaded isPositive returns whether or not an int is positive
	 */
	@SuppressWarnings("unused")
	private boolean isPositive(int number){
		return (number >= 0);
	}
}