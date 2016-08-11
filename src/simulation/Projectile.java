package simulation;

public class Projectile extends Actor{
	private int targetUnitIndex;
	
	public Projectile(Unit origin){
		super(origin.getPos(), origin.getOrientation(), .05, "Projectile", .01, 4);	
		targetUnitIndex = origin.getTargetUnitIndex();
		updateValues();
		originUnitIndex = origin.getIndex();
		setOrientation(qualifyAngle(Math.acos(xDiff / distance) , isPositive(xDiff), isPositive(yDiff)));
		moveTo();
	}
	public void update(){
		moveTo();
	}
	public void moveTo(){
		xMove = getSpeed()*Math.cos(this.getOrientation());
		yMove = getSpeed()*Math.sin(this.getOrientation());
		stepTo(xMove, yMove);
		findPositions();
		setReady(outOfBounds());
		System.out.println("The lazers are in motion");
		//System.out.println("Projectile " + getIndex() + " is stepping");
	}
	public boolean outOfBounds(){
		double x = getPos().getX();
		double y = getPos().getY();
		return (x > 1 || x < -1 || y > 1 || y < -1);
	}
	private void updateValues(){
		xDiff = getPos().getX() - ActorManager.getActor(targetUnitIndex).getPos().getX();
		yDiff = getPos().getY() - ActorManager.getActor(targetUnitIndex).getPos().getY();
		distance = Math.sqrt((Math.pow(xDiff, 2)) + Math.pow(yDiff, 2));
	}
	public boolean collision(){
		updateValues();
		boolean toReturn = (distance < ActorManager.getActor(targetUnitIndex).getSize());
		if (toReturn){
			ActorManager.getActor(targetUnitIndex).hasBeenHit = true;
			ActorManager.getActor(originUnitIndex).successfulHit = true;
		}
		return toReturn;

	}
	public boolean isProjectile(){return true;}
	public void decreaseHealth(int amount){}
}
