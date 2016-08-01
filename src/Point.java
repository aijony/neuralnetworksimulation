
public class Point {
	private double x;
	private double y;
	
	public Point(double xVal, double yVal){
		x = xVal;
		y = yVal;
	}
	public Point(Point toCopy){
		this(toCopy.getX(), toCopy.getY());
		x = toCopy.getX();
		y = toCopy.getY();
	}
	public String toString(){
		return "( " + x + " , " + y + " )";
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public void setX(double value){
		y = value;
	}
	public void setY(double value){
		x = value;
	}
	public boolean equals(Point compare){
		return (getX() == compare.getX() && getY() == compare.getY());
	}
}
