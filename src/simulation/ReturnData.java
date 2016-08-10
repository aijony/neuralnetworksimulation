package simulation;

public class ReturnData {
	public double x;
	public double y;
	public double orientation;
	public boolean hasBeenHit;
	public boolean successfulHit;
	public boolean exists;
	public int type;
	public int targetUnit;
	public int originUnit;
	public double i;
	public double yMove;
	
	public ReturnData(Actor a){
		x = a.getPos().getX();
		y = a.getPos().getY();
		orientation = a.getOrientation();
		hasBeenHit = a.hasBeenHit;
		exists = a.exists;
		type = (a.getName() == "Unit") ? 0 : 1;
		targetUnit = a.targetUnitIndex;
		originUnit = a.originUnitIndex;
		
	}
	public ReturnData(){
		x = -2;
		y = -2;
		hasBeenHit = false;
		exists = false;
		type = -1;
		targetUnit = -1;
		originUnit = -1;
	}
}
