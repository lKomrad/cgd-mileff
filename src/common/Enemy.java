package common;

import java.util.List;

import engine.Vector2D;
import game.GameLogic;

public class Enemy extends Unit {
		private final boolean isEnemy = true;

		private List<Vector2D> goalLocations;
		private Vector2D currentGoal;

		public Enemy() {
			super();
			currentGoal = new Vector2D(300,450);
		}
		
		public Enemy(float x, float y) {
			super(x,y);
			currentGoal = new Vector2D(300,450);
		}
		
		public Enemy(float x, float y, float scale) {
			super(x,y,scale);
			currentGoal = new Vector2D(300,450);
		}

		public List<Vector2D> getGoalLocations() {
			return goalLocations;
		}

		public void setGoalLocations(List<Vector2D> goalLocations) {
			this.goalLocations = goalLocations;
		}
		
		public Vector2D getGoalLocation(int index) {
			return this.goalLocations.get(index);
		}
		
		public void addGoalLocation(Vector2D goalLocation) {
			this.goalLocations.add(goalLocation);
		}
		
		public void MoveToGoal() {
			if (this.GetPosition() != goalLocations.get(goalLocations.size() - 1)) {
				
			}
		}
		
		public void Walk() {
			Vector2D currentPoz = this.GetPosition();
			Vector2D goal = this.currentGoal;

			if (this.currentAction == CurrentAction.Walking && !(currentPoz.x == this.currentGoal.x && currentPoz.y == this.currentGoal.y)) {
				float distance = GameLogic.calculateDistance(currentPoz, goal);
				float steps = distance / this.speed;
				if (steps < 1) steps = 1;
				float xDiff = (goal.x - currentPoz.x) / steps;
				float yDiff = (goal.y - currentPoz.y) / steps;
				this.SetPosition(this.GetPosition().x + xDiff, this.GetPosition().y + yDiff);
			}
		}
		
		public boolean NotReachedGoal() {
			if (this.GetPosition().equals(this.currentGoal)) {
				return false;
			} else return true;
		}
}
