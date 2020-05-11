package common;

import java.util.ArrayList;

import common.Unit.Animation;
import common.Unit.CurrentAction;
import engine.Texture2D;
import engine.Vector2D;
import game.DummyGame;
import game.GameLogic;

public class Projectile extends Enemy {
	
	private static ArrayList<Texture2D> idle_vFrames = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> idle_vFramesFlipped = new ArrayList<Texture2D>();
	
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
	
	public static void LoadAllTextures() {
		//idleAnimation
		String filenames = "textures/Orc/PNG/PNG Sequences/Idle/0_Orc_Idle_0";
		int numOfFrames = 18;
		LoadTextureGroup(filenames, numOfFrames, idle_vFrames, idle_vFramesFlipped);
	}
	
	public static void LoadTextureGroup(String filenames, int numOfFrames, ArrayList<Texture2D> list, ArrayList<Texture2D> flipped) {

		if (numOfFrames == 1) {
			Texture2D tex = new Texture2D();
			tex.CreateTexture(filenames + ".png");
			list.add(tex);
			
			Texture2D texFlip = new Texture2D();
			tex.CreateTextureFlipped(filenames + ".png");
			flipped.add(texFlip);
		} else {
			//Loading single textures
			for (int i = 0; i < numOfFrames; i++) {
				Texture2D tex = new Texture2D();

				if (0 <= i && i < 10) {
					tex.CreateTexture(filenames + "0" + (i) + ".png");
				} else {
					tex.CreateTexture(filenames + (i) + ".png");
				}

				list.add(tex);
			}
			
			for (int i = 0; i < numOfFrames; i++) {
				Texture2D tex = new Texture2D();

				if (0 <= i && i < 10) {
					tex.CreateTextureFlipped(filenames + "0" + (i) + ".png");
				} else {
					tex.CreateTextureFlipped(filenames + (i) + ".png");
				}

				flipped.add(tex);
			}
		}
	}
	
	public void setCorrectAnimation() {
		switch (this.currentAction) {
		case Idle:
			this.setAnimation(Animation.Idle);
			break;
		default:
			break;
		}
	}
	
	
	
	public void Draw() {
		Texture2D tex;
		if(this.facingRight) {
			switch (this.animation) {
			case Idle:
				tex = idle_vFrames.get(m_iActualFrame - 1);
				break;
			default:
				tex = idle_vFrames.get(m_iActualFrame - 1);
				break;
			}			
		}else {
			switch (this.animation) {
			case Idle:
				tex = idle_vFramesFlipped.get(m_iActualFrame - 1);
				break;
			default:
				tex = idle_vFramesFlipped.get(m_iActualFrame - 1);
				break;
			}	
		}

		tex.Draw(m_vSpritePosition);
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
		if (500.0f / m_iFps < (System.currentTimeMillis() - m_iLastUpdate)) {
			m_iLastUpdate = System.currentTimeMillis();
			switch (this.animation) {
			case Idle:
				if (++m_iActualFrame > idle_vFrames.size()) {
					m_iActualFrame = 1;
				}
				break;
			default:
				break;
			}

		}
	}
	
	public Texture2D GetCurrentFrameTexture() {
		if(this.facingRight) {
			switch (this.animation) {
			case Idle:
				return idle_vFrames.get(m_iActualFrame - 1);
			default:
				return idle_vFrames.get(m_iActualFrame - 1);
			}			
		}else {
			switch (this.animation) {
			case Idle:
				return idle_vFramesFlipped.get(m_iActualFrame - 1);
			default:
				return idle_vFramesFlipped.get(m_iActualFrame - 1);
			}	
		}

	}
}
