package simulation;

public class ReturnData {
	public double x;
	public double y;
	public boolean hasBeenHit;
	public boolean exists;
	public String name;
	public int targetUnit;
	public int originUnit;
	
	public ReturnData(Actor a){
		x = a.getPos().getX();
		y = a.getPos().getY();
		hasBeenHit = a.hasBeenHit;
		exists = a.exists;
		name = a.getName();
		targetUnit = a.targetUnitIndex;
		originUnit = a.originUnitIndex;
	}
	public ReturnData(){
		x = -2;
		y = -2;
		hasBeenHit = false;
		exists = false;
		name = "";
		targetUnit = -1;
		originUnit = -1;
	}
}
