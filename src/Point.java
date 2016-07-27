
public class Point {
	private int x;
	private int y;
	
	public Point(int xVal, int yVal){
		x = xVal;
		y = yVal;
	}
	public Point(Point toCopy){
<<<<<<< HEAD
		this(toCopy.getX(), toCopy.getY());
=======
		x = toCopy.getX();
		y = toCopy.getY();
>>>>>>> e254c4701d502e5da4e35994f3d228257290c00f
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
<<<<<<< HEAD
	public boolean equals(Point compare){
		return (getX() == compare.getX() && getY() == compare.getY());
	}
=======
>>>>>>> e254c4701d502e5da4e35994f3d228257290c00f
}
