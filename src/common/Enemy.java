package common;

import engine.Vector2D;

public class Enemy extends Unit {
		private final boolean isEnemy = true;
		
		//ez késõbb legyen List<Vector2D>
		private Vector2D goalLocation;

		public Enemy() {
			super();
		}
		
		public Enemy(float x, float y) {
			super(x,y);
		}
		
		public Enemy(float x, float y, float scale) {
			super(x,y,scale);
		}
	
		public Vector2D getGoalLocation() {
			return goalLocation;
		}

		public void setGoalLocation(Vector2D goalLocation) {
			this.goalLocation = goalLocation;
		}
}
