
public class Actor {
	private Point position;			//current position on x/y plane
	private double orientation;	//angle unit is facing (0-2PI)
	private double speed;			//distance unit can cover in one movement step
	private String name;		//name of unit
	
	public Actor(Point l, double o, double s, String n){
		position = l;
		orientation = o;
		speed = s;
		name = n;
	}
	public void setPos(Point p){	position = p;	}
	public Point getPos(){	return position;}
	public double getSpeed(){	return speed;}
	public String getName(){return name;}
	public double getOrientation(){return orientation;}
	public void setOrientation(double newO){ orientation = newO;}
	/*
	 * @brief 	Moves this unit incrementally based on field 'speed' to newPoint
	 * @param	newPoint: the target destination for position
	 * @returns	void
	 */
	public void moveTo(Point newPoint){
		double xDiff = newPoint.getX() - getPos().getX();	//remaining difference between current and goal x
		double yDiff = newPoint.getY() - getPos().getY();	//remaining difference between current and goal y
		
		double xMove; double yMove;		//delta x and y per step; hypotenuse of triangle formed
										//will be speed of unit
		
		double distance = (double)(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
		setOrientation(qualifyAngle(Math.acos(xDiff / distance) , isPositive(xDiff), isPositive(yDiff)));
		while (!newPoint.equals(getPos())){
			if (distance >= getSpeed()){
				xMove = getSpeed()*Math.cos(getOrientation());
				yMove = getSpeed()*Math.sin(getOrientation());
			}
			else {
				xMove = xDiff;
				yMove = yDiff;
			}
			
			stepTo(xMove, yMove);
			xDiff -= xMove;
			yDiff -= yMove;
			
			System.out.println("Location: " + getPos() + " Goal: " + newPoint);
			
			distance = (double)(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
			if (distance <= .00001)
				setPos(newPoint);
		}
	}	
	/*
	 * @brief	single increment for moveTo
	 * @param	xMove and yMove: values for delta x and delta y
	 */
	private void stepTo(double xMove, double yMove){
		setPos(new Point(((double)getPos().getX() + xMove), ((double)getPos().getY() + yMove)));
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
	/*
	 * @brief	returns random x/y coordinate between self-documenting parameters
	 */
	public Point randomPoint(double xMin, double yMin, double xMax, double yMax){
		return new Point((Math.random() * (xMax - xMin)) + xMin, (Math.random() * (yMax - yMin)) + yMin);
	}
	
}
