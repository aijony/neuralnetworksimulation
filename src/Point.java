
public class Point {
	private int x;
	private int y;
	
	public Point(int xVal, int yVal){
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
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int value){
		y = value;
	}
	public void setY(int value){
		x = value;
	}
	public boolean equals(Point compare){
		return (getX() == compare.getX() && getY() == compare.getY());
	}
}
