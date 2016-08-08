package simulation;

public class RangeSet {
	private double xMin;
	private double xMax;
	private double yMin;
	private double yMax;
	
	public RangeSet(){
		xMin = -1; xMax = 1; yMin = -1; yMax = 1;
	}
	public RangeSet(double x1, double x2, double y1, double y2){
		xMin = x1; xMax = x2; yMin = y1; yMin = y2;
	}
	public double getXMin(){return xMin;}
	public double getXMax(){return xMax;}
	public double getYMin(){return yMin;}
	public double getYMax(){return yMax;}
}
