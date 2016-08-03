
public class Actor {
	private Point position;		//current position on x/y plane
	private double orientation;	//angle unit is facing (0-2PI)
	private double speed;		//distance unit can cover in one movement step
	private String name;		//name of unit
	private double size;		//rendered size of object in window
	private int numSides;		//number of sides of this polygon
	private PositionMatrix positions;
	private VertexMatrix vertices;
	private Polygon visual;
	private int index;
	
	
	public Actor(){
		position = randomPoint(-1, -1, 1, 1);
		orientation = Math.random() * Math.PI / 2;
		speed = .01;
		name = "Default name";
		size = .15;
		numSides = 4;
		positions = new PositionMatrix(numSides);
		findPositions();
		visual = new Polygon(numSides);
	}
	public Actor(Point l, double o, double sp, String n, double si, int nS){
		position = l;
		orientation = o;
		speed = sp;
		name = n;
		size = si;
		numSides = nS;
		positions = new PositionMatrix(numSides);
		findPositions();
		visual = new Polygon(numSides);
	}
	public void setPos(Point p){	position = p;	}
	public Point getPos(){	return position;}
	public double getSpeed(){	return speed;}
	public String getName(){return name;}
	public double getOrientation(){return orientation;}
	public void setOrientation(double newO){ orientation = newO;}
	public PositionMatrix getPositionMatrix(){return positions;}
	public VertexMatrix getVertexMatrix(){return vertices;}
	public void setVertexMatrix(VertexMatrix newMatrix){vertices = newMatrix;}
	public int getNumSides(){return numSides;}
	public void setIndex(int i){index = i;}
	public int getIndex(){return index;}
	/*
	 * @brief 	Moves this unit incrementally based on field 'speed' to newPoint
	 * @param	newPoint: the target destination for position
	 * @returns	void
	 */
	private void findPositions(){
		double angleOffset = Math.PI / numSides;
		for (int x = 0; x <= numSides - 1; x++){
			positions.getPosition(x).set((float)(size * Math.cos(orientation + ((1 + 2 * x) * angleOffset) )+ getPos().getX()), (float)(size * Math.sin(orientation + ((1 + 2 * x) * angleOffset) )+ getPos().getY()));
		}
	}
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
			findPositions();
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
	public void initializeMovement(){
		Point targetPoint = randomPoint(-1, -1, 1, 1);
		System.out.println(getName() + " is moving from " + getPos() + " to " + targetPoint);
		moveTo(targetPoint);
	}
}
