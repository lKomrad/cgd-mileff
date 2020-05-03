package common;

import java.util.ArrayList;
import java.util.List;

import engine.Vector2D;
import game.DummyGame;
import game.GameLogic;

public class Enemy extends Unit {

		private List<Vector2D> goalLocations;
		
		public Enemy() {
			super();
			
			goalLocations = new ArrayList<Vector2D>();
			health = 20;
			attack = 5;
		}
		
		public Enemy(float x, float y) {
			super(x,y);

			goalLocations = new ArrayList<Vector2D>();
			health = 20;
			attack = 5;
		}
		
		public Enemy(float x, float y, float scale) {
			super(x,y,scale);

			goalLocations = new ArrayList<Vector2D>();
			health = 20;
			attack = 5;
		}

		public List<Vector2D> getGoalLocations() {
			return goalLocations;
		}
		
		public void addGoalLocation(Vector2D location) {
			this.goalLocations.add(location);
		}

		public void setGoalLocations(List<Vector2D> goalLocations) {
			this.goalLocations = goalLocations;
		}
		
		public Vector2D getGoalLocation(int index) {
			return this.goalLocations.get(index);
		}
		
		/*public void MoveToGoal() {
			if (this.GetPosition() != goalLocations.get(goalLocations.size() - 1)) {
				
			}
		}*/
		
		public void Walk() {
			Vector2D currentPoz = this.GetPosition();
			//Vector2D goal = this.currentGoal;
			Vector2D goal = this.goalLocations.get(0);

			if (this.currentAction == CurrentAction.Walking && !(currentPoz.x == goal.x && currentPoz.y == goal.y)) {
				float distance = GameLogic.calculateDistance(currentPoz, goal);
				float steps = distance / this.speed;
				if (steps < 1) {
					this.SetPosition(goal);
				} else {
					float xDiff = (goal.x - currentPoz.x) / steps;
					float yDiff = (goal.y - currentPoz.y) / steps;
					this.SetPosition(this.GetPosition().x + xDiff, this.GetPosition().y + yDiff);
				}
				
				if(currentPoz.x < goal.x) {
					this.facingRight = true;
				} else this.facingRight = false;
			}
		}
		
		public boolean NotReachedGoal() {
			if (this.GetPosition().x == this.goalLocations.get(0).x && this.GetPosition().y == this.goalLocations.get(0).y) {
				if(this.goalLocations.size()>1) {
					this.goalLocations.remove(0);
				} else this.goalLocations.set(0, this.GetPosition());
				return false;
				
				/*if (goalIndex < this.goalLocations.size()+1) goalIndex ++;
				if(goalIndex < this.goalLocations.size()) {
					this.currentGoal = this.getGoalLocation(goalIndex);					
				}else this.currentGoal = this.GetPosition();
				return false;*/
			} else {
				return true;
			}
		}
		

		public void attackTarget() {
			targetUnit.health -= attack;
			if (targetUnit.health < 0) {
				DummyGame.friendlyUnits.remove(targetUnit);
				DummyGame.dyingUnits.add(targetUnit);
				targetUnit.setCurrentAction(CurrentAction.Dying);
				targetUnit = null;
			}
		}
}
