import java.io.IOException;

public class Unit {
	
	private int range;
	private int damage;
	private int health;
	private int speed;
	private int cost;
	private Unit target;
	private Point loc;
	public Unit(int r, int d, int h, int s, int c) {
		range = r; damage = d; health = h; speed = s; cost = c;
	}
	public void setPos(Point position){
		 loc = position;
	}
	public Point getPos(){
		return loc;
	}
	public int getRange(){
		return range;
	}
	public int getDamage(){
		return damage;
	}
	public int getHealth(){
		return health;
	}
	public int damage(int amount){
		health -= amount;
		return health;
	}
	public int getSpeed(){
		return speed;
	}
	public int getCost(){
		return cost;
	}
	public void moveTo(Point newPoint){
		double xDiff = newPoint.getX() - loc.getX();
		double yDiff = newPoint.getY() - loc.getY();
		
		double xMove; double yMove;
		
		double distance = (double)(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
		double angleToPoint = Math.acos(xDiff / distance);
		
		System.out.println("Angle: " + angleToPoint);
		System.out.println("Distance: " + distance);
		System.out.println("Speed: " + getSpeed());
		System.out.println("xDiff: " + xDiff + " yDiff: " + yDiff);
		while (!newPoint.equals(loc)){
			if (distance >= getSpeed()){
				xMove = getSpeed()*Math.cos(angleToPoint);
				yMove = getSpeed()*Math.sin(angleToPoint);
			}
			else {
				xMove = xDiff;
				yMove = yDiff;
			}
			System.out.println("xMove: " + xMove + " yMove: " + yMove);
			
			xDiff -= xMove;
			yDiff -= yMove;
			stepTo(xMove, yMove);
		
			System.out.println("Current: " + loc + " Goal: " + newPoint);
			distance = (double)(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
			System.out.println("Distance: " + distance);
			System.out.println("xDiff: " + xDiff + " yDiff: " + yDiff);
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	private void stepTo(double xMove, double yMove){
		setPos(new Point((int)(loc.getX() + xMove), (int)(loc.getY() + yMove)));
	}
	private double qualifyAngle(double angle, boolean xPositive, boolean yPositive){
		if (xPositive && yPositive || !xPositive && yPositive)
			return angle;
		else
			return (2 * Math.PI) - angle;
	}
	private boolean isPositive(double number){
		return true;
	}
}