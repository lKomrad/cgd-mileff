package common;

import java.util.ArrayList;

import org.joml.Vector2f;

import engine.Texture2D;
import engine.Vector2D;
import game.DummyGame;

public class Golem extends Unit {
	private final boolean isEnemy = false;
		
		public Golem() {
			super();
			
			attack = 7;
			health = 300000000;
		}
		
		public Golem(float x, float y) {
			super(x,y);
			
			attack = 7;
			health = 300000000;

			m_vSpritePosition = new Vector2D(x, y);
		}
		
		public Golem(float x, float y, float scale) {
			super(x,y,scale);
			
			attack = 7;
			health = 300000000;

			m_vSpritePosition = new Vector2D(x, y);
		}
		
		public void LoadAllTextures() {
			//idleAnimation
			String filenames = "textures/Golems/PNG/Golem_01/PNG Sequences/Idle/Golem_01_Idle_0";
			int numOfFrames = 12;
			LoadTextureGroup(filenames, numOfFrames, idle_vFrames, idle_vFramesFlipped);
			filenames = "textures/Golems/PNG/Golem_01/PNG Sequences/Walking/Golem_01_Walking_0";
			numOfFrames = 18;
			LoadTextureGroup(filenames, numOfFrames, walk_vFrames, walk_vFramesFlipped);
			filenames = "textures/Golems/PNG/Golem_01/PNG Sequences/Attacking/Golem_01_Attacking_0";
			numOfFrames = 12;
			LoadTextureGroup(filenames, numOfFrames, attack_vFrames, attack_vFramesFlipped);
		}
		
		public void attackTarget() {
			targetUnit.health -= attack;
			if (targetUnit.health < 0) {
				DummyGame.enemyUnits.remove(targetUnit);
			}
		}
}
