package common;

import java.util.ArrayList;

import common.Unit.Animation;
import engine.Texture2D;
import engine.Vector2D;

public class Ogre extends Enemy {
	
	private static ArrayList<Texture2D> idle_vFrames = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> idle_vFramesFlipped = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> walk_vFrames = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> walk_vFramesFlipped = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> attack_vFrames = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> attack_vFramesFlipped = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> oof_vFrames = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> oof_vFramesFlipped = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> dying_vFrames = new ArrayList<Texture2D>();
	private static ArrayList<Texture2D> dying_vFramesFlipped = new ArrayList<Texture2D>();
	
	public Ogre() {
		super();
	}

	public Ogre(float x, float y, float scale) {
		super(x,y,scale);
		
		m_vSpritePosition = new Vector2D(x, y);
	}
	
	public static void LoadAllTextures() {
		//idleAnimation
		String filenames = "textures/Ogre/PNG/PNG Sequences/Idle/0_Ogre_Idle_0";
		int numOfFrames = 18;
		LoadTextureGroup(filenames, numOfFrames, idle_vFrames, idle_vFramesFlipped);
		filenames = "textures/Ogre/PNG/PNG Sequences/Walking/0_Ogre_Walking_0";
		numOfFrames = 24;
		LoadTextureGroup(filenames, numOfFrames, walk_vFrames, walk_vFramesFlipped);
		filenames = "textures/Ogre/PNG/PNG Sequences/Slashing/0_Ogre_Slashing_0";
		numOfFrames = 12;
		LoadTextureGroup(filenames, numOfFrames, attack_vFrames, attack_vFramesFlipped);
		filenames = "textures/Ogre/PNG/PNG Sequences/Hurt/0_Ogre_Hurt_0";
		numOfFrames = 12;
		LoadTextureGroup(filenames, numOfFrames, oof_vFrames, oof_vFramesFlipped);
		filenames = "textures/Ogre/PNG/PNG Sequences/Dying/0_Ogre_Dying_0";
		numOfFrames = 15;
		LoadTextureGroup(filenames, numOfFrames, dying_vFrames, dying_vFramesFlipped);
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
		case Walking:
			this.setAnimation(Animation.Walk);
			break;
		case Attacking:
			elapsedTime += attackTimer.getElapsedTime();
			if (elapsedTime > 2 && targetUnit != null) {
				turnToTarget();
				this.setAnimation(Animation.Attack);
				framesPassed = 0;
				elapsedTime = 0;
				attacked = false;
			} else {
				framesPassed ++;
				if(framesPassed > attack_vFrames.size() + 1 && !attacked) {
					this.setAnimation(Animation.Idle);
					try {
						attackTarget();
						//targetUnit.setAnimation(Animation.Oof);
					} catch (Exception e) {
						this.setCurrentAction(CurrentAction.Idle);
					}
					framesPassed = 0;
					attacked = true;
				}
			}
			break;
		case Dying:
			this.setAnimation(Animation.Dying);
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
			case Walk:
				tex = walk_vFrames.get(m_iActualFrame - 1);
				break;
			case Attack:
				tex = attack_vFrames.get(m_iActualFrame - 1);
				break;
			case Oof:
				tex = oof_vFrames.get(m_iActualFrame - 1);
				break;
			case Dying:
				tex = dying_vFrames.get(m_iActualFrame - 1);
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
			case Walk:
				tex = walk_vFramesFlipped.get(m_iActualFrame - 1);
				break;
			case Attack:
				tex = attack_vFramesFlipped.get(m_iActualFrame - 1);
				break;
			case Oof:
				tex = oof_vFramesFlipped.get(m_iActualFrame - 1);
				break;
			case Dying:
				tex = dying_vFramesFlipped.get(m_iActualFrame - 1);
				break;
			default:
				tex = idle_vFramesFlipped.get(m_iActualFrame - 1);
				break;
			}	
		}

		tex.Draw(m_vSpritePosition);
		Update();
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
			case Walk:
				if (++m_iActualFrame > walk_vFrames.size()) {
					m_iActualFrame = 1;
				}
				break;
			case Attack:
				if (++m_iActualFrame > attack_vFrames.size()) {
					m_iActualFrame = 1;
				}
				break;
			case Oof:
				if (++m_iActualFrame > oof_vFrames.size()) {
					setAnimation(Animation.Idle);
				}
				break;
			case Dying:
				if (m_iActualFrame < dying_vFrames.size()) {
					m_iActualFrame++;
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
			case Walk:
				return walk_vFrames.get(m_iActualFrame - 1);
			case Attack:
				return attack_vFrames.get(m_iActualFrame - 1);
			case Oof:
				return oof_vFrames.get(m_iActualFrame - 1);
			case Dying:
				return dying_vFrames.get(m_iActualFrame - 1);
			default:
				return idle_vFrames.get(m_iActualFrame - 1);
			}			
		}else {
			switch (this.animation) {
			case Idle:
				return idle_vFramesFlipped.get(m_iActualFrame - 1);
			case Walk:
				return walk_vFramesFlipped.get(m_iActualFrame - 1);
			case Attack:
				return attack_vFramesFlipped.get(m_iActualFrame - 1);
			case Oof:
				return oof_vFramesFlipped.get(m_iActualFrame - 1);
			case Dying:
				return dying_vFramesFlipped.get(m_iActualFrame - 1);
			default:
				return idle_vFramesFlipped.get(m_iActualFrame - 1);
			}	
		}

	}
}
