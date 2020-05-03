package common;

import java.util.ArrayList;
import java.util.Random;

import org.joml.Vector2f;

import common.Unit.CurrentAction;
import engine.Texture2D;
import engine.Vector2D;
import game.DummyGame;

public class Golem extends Unit {
		
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
		
		public void LoadAllTextures() {
			Random rnd = new Random();
			int num = rnd.nextInt(3) + 1;
			System.out.println(num);
			//idleAnimation
			String filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Idle/Golem_0"+num+"_Idle_0";
			int numOfFrames = 12;
			LoadTextureGroup(filenames, numOfFrames, idle_vFrames, idle_vFramesFlipped);
			filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Walking/Golem_0"+num+"_Walking_0";
			numOfFrames = 18;
			LoadTextureGroup(filenames, numOfFrames, walk_vFrames, walk_vFramesFlipped);
			filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Attacking/Golem_0"+num+"_Attacking_0";
			numOfFrames = 12;
			LoadTextureGroup(filenames, numOfFrames, attack_vFrames, attack_vFramesFlipped);
			filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Hurt/Golem_0"+num+"_Hurt_0";
			numOfFrames = 12;
			LoadTextureGroup(filenames, numOfFrames, oof_vFrames, oof_vFramesFlipped);
			filenames = "textures/Golems/PNG/Golem_0"+num+"/PNG Sequences/Dying/Golem_0"+num+"_Dying_0";
			numOfFrames = 15;
			LoadTextureGroup(filenames, numOfFrames, dying_vFrames, dying_vFramesFlipped);
		}
		
		public void attackTarget() {
			targetUnit.health -= attack;
			if (targetUnit.health < 0) { 
				DummyGame.enemyUnits.remove(targetUnit);
				DummyGame.dyingUnits.add(targetUnit);
				targetUnit.setCurrentAction(CurrentAction.Dying);
				targetUnit = null;
			}
		}
}
