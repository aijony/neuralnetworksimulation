package simulation;
import java.util.concurrent.CountDownLatch;

import graphics.PositionMatrix;
import graphics.VertexMatrix;

public class Actor {
	private Point position;		//current position on x/y plane
	private double orientation;	//angle unit is facing (0-2PI)
	private double speed;		//distance unit can cover in one movement step
	private String name;		//name of unit
	private double size;		//rendered size of object in window
	private int numSides;		//number of sides of this polygon
	private PositionMatrix positions;
	private VertexMatrix vertices;
	private Point targetPoint;
	private int index;
	public boolean hasBeenHit;
	public boolean exists = true;
	public boolean successfulHit;
	public double xDiff, yDiff, xMove, yMove, distance; //Doubles needed to calculate the stepTo method
	private boolean ready = false; //Defines whether or not the actor is ready to aim for a new spot
	public int targetUnitIndex;
	public int originUnitIndex;
	public RangeSet projectileRanges;
	public RangeSet movementRanges;
	
	CountDownLatch waitUpdate = new CountDownLatch(1);
	
	public Actor(){
		position = Point.randomPoint(new RangeSet());
		orientation = Math.random() * Math.PI / 2;
		speed = .02;
		name = "Default name";
		size = .2;
		numSides = 4;
		positions = new PositionMatrix(numSides);
		vertices = new VertexMatrix(numSides);
		findPositions();

		projectileRanges = new RangeSet(-1, -1, 1, 1);
		movementRanges = new RangeSet(-1, -1, 1, 1);
	}
	public Actor(Point l, double o, double sp, String n, double si, int nS){
		
		position = l;
		orientation = o;
		speed = sp;
		name = n;
		size = si;
		numSides = nS;
		positions = new PositionMatrix(numSides);
		vertices = new VertexMatrix(numSides);
		findPositions();
		projectileRanges = new RangeSet(-1, -1, 1, 1);
		movementRanges = new RangeSet(-1, -1, 1, 1);
	}
	public Actor(Point l, double o, String n){
		this(l, o, .05, n, .15, 4);
	}
	public void setPos(Point p){	position = p;	}
	public Point getPos(){	return position;}
	public double getSpeed(){	return speed;}
	public String getName(){return name;}
	protected void setName(String n){name = n;}
	public double getOrientation(){return orientation;}
	public void setOrientation(double newO){ orientation = newO;}
	public PositionMatrix getPositionMatrix(){return positions;}
	public VertexMatrix getVertexMatrix(){return vertices;}
	public void setVertexMatrix(VertexMatrix newMatrix){vertices = newMatrix;}
	public int getNumSides(){return numSides;}
	public boolean isReady() {return ready;}
	public void setReady(boolean ready) {this.ready = ready;}
	public int getIndex(){return index;}
	public void setIndex(int value){index = value;}
	public double getSize(){return size;}
	public void setMovementRanges(double x1, double y1, double x2, double y2){movementRanges = new RangeSet(x1, y1, x2, y2);}
	public void setProjectileRanges(double x1, double y1, double x2, double y2){projectileRanges = new RangeSet(x1, y1, x2, y2);}
	
	protected void findPositions(){
		double angleOffset = Math.PI / numSides;
		for (int x = 0; x < numSides; x++){
			positions.getPosition(x).set((float)(size * Math.cos(orientation + ((1 + 2 * x) * angleOffset) )+ getPos().getX()), (float)(size * Math.sin(orientation + ((1 + 2 * x) * angleOffset) )+ getPos().getY()));
		}
		vertices.setPosition(positions);
	}
	
	/*
	 * @brief 	Moves this unit incrementally based on field 'speed' to newPoint
	 */
	public void moveTo(){
			
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
			
			//System.out.println("Location: " + getPos() + " Goal: " + newPoint);
			
			distance = (double)(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
			if (distance <= .00001)
				setPos(targetPoint);

			findPositions();
			
			setReady(targetPoint.equals(getPos()));
		
	}	
	/*
	 * @brief	single increment for moveTo
	 * @param	xMove and yMove: values for delta x and delta y
	 */
	protected void stepTo(double xMove, double yMove){
		setPos(new Point(((double)getPos().getX() + xMove), ((double)getPos().getY() + yMove)));
		
	}
	/*
	 * @brief	returns proper angle as it relates to its quadrant in the unit circle
	 * 			necessary because Math basic trig functions only go from 0-2PI
	 */
	protected double qualifyAngle(double angle, boolean xPositive, boolean yPositive){
		if (xPositive && yPositive || !xPositive && yPositive)
			return angle;
		else
			return (2 * Math.PI) - angle;
	}
	/*
	 * @brief	returns whether or not a double is positive
	 */
	protected boolean isPositive(double number){
		return (number >= 0);
	}
	
	/*
	 * @brief	returns random x/y coordinate between self-documenting parameters
	 */
	
	
	public void update(){
		if(isReady()){
			initializeMovement(Point.randomPoint(new RangeSet()));
		}
		else{
			moveTo();
		}
			
			
	}
	
	public void initializeMovement(Point newPoint){
		targetPoint = newPoint;

		xDiff = targetPoint.getX() - getPos().getX();	//remaining difference between current and goal x
		yDiff = targetPoint.getY() - getPos().getY();	//remaining difference between current and goal y
		
		xMove = 0;  yMove = 0;		//delta x and y per step; hypotenuse of triangle formed
									//will be speed of unit
		
		distance = (double)(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
		setOrientation(qualifyAngle(Math.acos(xDiff / distance) , isPositive(xDiff), isPositive(yDiff)));
	
		moveTo();
	}
	public boolean isProjectile(){return false;}
	public boolean outOfBounds(){return false;}
	public boolean collision(){return false;}
	public void decreaseHealth(int amount){}
}
