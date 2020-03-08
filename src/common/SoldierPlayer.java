package common;

import org.joml.Vector2f;

public class SoldierPlayer extends Soldier {
	private Vector2f rallyPoint;
	private final boolean isEnemy = false;
	public SoldierPlayer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vector2f getRallyPoint() {
		return rallyPoint;
	}
	public void setRallyPoint(Vector2f rallyPoint) {
		this.rallyPoint = rallyPoint;
	}
	public boolean isEnemy() {
		return isEnemy;
	}
	
}
