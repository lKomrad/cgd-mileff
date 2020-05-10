package common;

import java.util.ArrayList;
import java.util.Random;

import org.joml.Vector2f;

import common.Unit.Animation;
import common.Unit.CurrentAction;
import engine.CSprite;
import engine.CSpriteFrame;
import engine.Texture2D;
import engine.Timer;
import engine.Vector2D;
import game.DummyGame;

public class Golem extends Unit {
	
	private static ArrayList<CSprite> sprites = new ArrayList<CSprite>();
		
		public Golem() {
			super();
			
			attack = 7;
			health = 30;
		}
		
		public Golem(float x, float y) {
			super(x,y);
			
			attack = 7;
			health = 30;

			m_vSpritePosition = new Vector2D(x, y);
		}
		
		public Golem(float x, float y, float scale) {
			super(x,y,scale);
			
			attack = 7;
			health = 30;

			m_vSpritePosition = new Vector2D(x, y);
		}
		
		public static void LoadAllSprites() {
			Random rnd = new Random();
			int num = rnd.nextInt(3) + 1;
			System.out.println(num);
			//idleAnimation
			String filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Idle/Golem_0"+num+"_Idle_0";
			int numOfFrames = 12;
			LoadSprite(filenames, numOfFrames);
			filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Walking/Golem_0"+num+"_Walking_0";
			numOfFrames = 18;
			LoadSprite(filenames, numOfFrames);
			filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Attacking/Golem_0"+num+"_Attacking_0";
			numOfFrames = 12;
			LoadSprite(filenames, numOfFrames);
			filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Hurt/Golem_0"+num+"_Hurt_0";
			numOfFrames = 12;
			LoadSprite(filenames, numOfFrames);
			filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Dying/Golem_0"+num+"_Dying_0";
			numOfFrames = 15;
			LoadSprite(filenames, numOfFrames);
		}
		
		public static void LoadSprite(String filenames, int numOfFrames) {

			if (numOfFrames == 1) {
				Texture2D tex = new Texture2D();
				tex.CreateTexture(filenames + ".png");
				CSpriteFrame newFrame = new CSpriteFrame(tex);
				newFrame.AddBoundingBox(new Vector2D(0.0f, 0.0f), new Vector2D(tex.GetWidth(), tex.GetHeight()));
				CSprite newSprite = new CSprite();
				newSprite.addSpriteFrame(newFrame);
				sprites.add(newSprite);
				
				Texture2D texFlip = new Texture2D();
				texFlip.CreateTextureFlipped(filenames + ".png");
				CSpriteFrame newFrameFlipped = new CSpriteFrame(texFlip);
				newFrame.AddBoundingBox(new Vector2D(0.0f, 0.0f), new Vector2D(tex.GetWidth(), tex.GetHeight()));
				CSprite newSpriteFlipped = new CSprite();
				newSprite.addSpriteFrame(newFrameFlipped);
				sprites.add(newSpriteFlipped);
			} else {
				CSprite newSprite = new CSprite();
				CSprite newSpriteFlipped = new CSprite();
				//Loading single textures
				for (int i = 0; i < numOfFrames; i++) {
					Texture2D tex = new Texture2D();

					if (0 <= i && i < 10) {
						tex.CreateTexture(filenames + "0" + (i) + ".png");
					} else {
						tex.CreateTexture(filenames + (i) + ".png");
					}

					CSpriteFrame newFrame = new CSpriteFrame(tex);
					newFrame.AddBoundingBox(new Vector2D(0.0f, 0.0f), new Vector2D(tex.GetWidth(), tex.GetHeight()));
					
					newSprite.addSpriteFrame(newFrame);
				}
				sprites.add(newSprite);
				
				for (int i = 0; i < numOfFrames; i++) {
					Texture2D tex = new Texture2D();

					if (0 <= i && i < 10) {
						tex.CreateTextureFlipped(filenames + "0" + (i) + ".png");
					} else {
						tex.CreateTextureFlipped(filenames + (i) + ".png");
					}
					CSpriteFrame newFrame = new CSpriteFrame(tex);
					newFrame.AddBoundingBox(new Vector2D(0.0f, 0.0f), new Vector2D(tex.GetWidth(), tex.GetHeight()));

					newSpriteFlipped.addSpriteFrame(newFrame);
				}
				sprites.add(newSpriteFlipped);
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
					if(framesPassed > sprites.get(4).getFrames().size() + 1 && !attacked) {
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
					tex = sprites.get(0).GetTexture(m_iActualFrame - 1);
					break;
				case Walk:
					tex = sprites.get(2).GetTexture(m_iActualFrame - 1);
					break;
				case Attack:
					tex = sprites.get(4).GetTexture(m_iActualFrame - 1);
					break;
				case Oof:
					tex = sprites.get(6).GetTexture(m_iActualFrame - 1);
					break;
				case Dying:
					tex = sprites.get(8).GetTexture(m_iActualFrame - 1);
					break;
				default:
					tex = sprites.get(0).GetTexture(m_iActualFrame - 1);
					break;
				}			
			}else {
				switch (this.animation) {
				case Idle:
					tex = sprites.get(1).GetTexture(m_iActualFrame - 1);
					break;
				case Walk:
					tex = sprites.get(3).GetTexture(m_iActualFrame - 1);
					break;
				case Attack:
					tex = sprites.get(5).GetTexture(m_iActualFrame - 1);
					break;
				case Oof:
					tex = sprites.get(7).GetTexture(m_iActualFrame - 1);
					break;
				case Dying:
					tex = sprites.get(9).GetTexture(m_iActualFrame - 1);
					break;
				default:
					tex = sprites.get(1).GetTexture(m_iActualFrame - 1);
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
					if (++m_iActualFrame > sprites.get(0).getFrames().size()) {
						m_iActualFrame = 1;
					}
					break;
				case Walk:
					if (++m_iActualFrame > sprites.get(2).getFrames().size()) {
						m_iActualFrame = 1;
					}
					break;
				case Attack:
					if (++m_iActualFrame > sprites.get(4).getFrames().size()) {
						m_iActualFrame = 1;
					}
					break;
				case Oof:
					if (++m_iActualFrame > sprites.get(6).getFrames().size()) {
						setAnimation(Animation.Idle);
					}
					break;
				case Dying:
					if (m_iActualFrame < sprites.get(8).getFrames().size()) {
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
					return sprites.get(0).GetTexture(m_iActualFrame - 1);
				case Walk:
					return sprites.get(2).GetTexture(m_iActualFrame - 1);
				case Attack:
					return sprites.get(4).GetTexture(m_iActualFrame - 1);
				case Oof:
					return sprites.get(6).GetTexture(m_iActualFrame - 1);
				case Dying:
					return sprites.get(8).GetTexture(m_iActualFrame - 1);
				default:
					return sprites.get(0).GetTexture(m_iActualFrame - 1);
				}			
			}else {
				switch (this.animation) {
				case Idle:
					return sprites.get(1).GetTexture(m_iActualFrame - 1);
				case Walk:
					return sprites.get(3).GetTexture(m_iActualFrame - 1);
				case Attack:
					return sprites.get(5).GetTexture(m_iActualFrame - 1);
				case Oof:
					return sprites.get(7).GetTexture(m_iActualFrame - 1);
				case Dying:
					return sprites.get(9).GetTexture(m_iActualFrame - 1);
				default:
					return sprites.get(1).GetTexture(m_iActualFrame - 1);
				}	
			}

		}
		
		public void attackTarget() {
			targetUnit.health -= attack;
			if (targetUnit.health < 0) { 
				DummyGame.enemyUnits.remove(targetUnit);
				DummyGame.dyingUnits.add(targetUnit);
				targetUnit.setCurrentAction(CurrentAction.Dying);
				targetUnit.setElapsedTime(0);
				targetUnit = null;
			}
		}
}
