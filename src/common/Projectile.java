package common;

import java.util.ArrayList;

import common.Unit.Animation;
import common.Unit.CurrentAction;
import engine.Texture2D;
import engine.Vector2D;
import game.DummyGame;
import game.GameLogic;

public class Projectile extends Enemy {
	
	public static Texture2D texture = new Texture2D();
	
	
	boolean toBeDeleted;

	public Projectile(Enemy target) {
		super();
		this.scale = 0.1f;
		this.speed = 15;
		this.targetUnit = target;
		toBeDeleted = false;
	}

	public Projectile(float x, float y, Enemy target) {
		super(x,y);
		this.scale = 0.1f;
		this.speed = 15;
		this.targetUnit = target;
		toBeDeleted = false;
		
		m_vSpritePosition = new Vector2D(x, y);
	}
	
	public Projectile(float x, float y, float scale, Enemy target) {
		super(x,y,scale);
		this.speed = 15;
		this.targetUnit = target;
		toBeDeleted = false;
		
		m_vSpritePosition = new Vector2D(x, y);
	}
	
	
	public void Draw() {
		
		texture.Draw(m_vSpritePosition);
		Update();
	}
	
	//walk
	public boolean FlyToTarget() {
		Vector2D currentPoz = this.GetPosition();
		//Vector2D goal = this.currentGoal;
		Vector2D goal = this.targetUnit.GetPosition();

		if (!(currentPoz.x == goal.x && currentPoz.y == goal.y)) {
			float distance = GameLogic.calculateDistance(currentPoz, goal);
			float steps = distance / this.speed;
			if (steps < 1 && !toBeDeleted) {
				attackTarget();
				toBeDeleted = true;
				return true;
			} else {
				float xDiff = (goal.x - currentPoz.x) / steps;
				float yDiff = (goal.y - currentPoz.y) / steps;
				this.SetPosition(this.GetPosition().x + xDiff, this.GetPosition().y + yDiff);
			}
			
			if(currentPoz.x < goal.x) {
				this.facingRight = true;
			} else this.facingRight = false;
		} return false;
	}
	
	public void attackTarget() {
		targetUnit.health -= attack;
		if (targetUnit.health < 0) { 
			DummyGame.enemyUnits.remove(targetUnit);
			DummyGame.dyingUnits.add(targetUnit);
			targetUnit.setCurrentAction(CurrentAction.Dying);
		}
	}
	
	public boolean getToBeDeleted() {
		return this.toBeDeleted;
	}
	
	public void Update() {
	}
	
	public Texture2D GetCurrentFrameTexture() {
		return texture;
	}

	public static void createTexture() {
		texture.CreateTexture("textures/Projectiles/magicbubble.png");
	}
	
	@Override
	public void setCorrectAnimation() {
		// TODO Auto-generated method stub
		
	}
}
