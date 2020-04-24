package common;

import engine.Vector2D;

public class Goblin extends Enemy {
	public Goblin() {
		super();
	}

	public Goblin(float x, float y, float scale) {
		super(x,y,scale);
		
		m_vSpritePosition = new Vector2D(x, y);
	}
	
	public void LoadAllTextures() {
		//idleAnimation
		String filenames = "textures/Goblin/PNG/PNG Sequences/Idle/0_Goblin_Idle_0";
		int numOfFrames = 18;
		LoadTextureGroup(filenames, numOfFrames, idle_vFrames);
		filenames = "textures/Goblin/PNG/PNG Sequences/Walking/0_Goblin_Walking_0";
		numOfFrames = 24;
		LoadTextureGroup(filenames, numOfFrames, walk_vFrames);
		filenames = "textures/Goblin/PNG/PNG Sequences/Slashing/0_Goblin_Slashing_0";
		numOfFrames = 12;
		LoadTextureGroup(filenames, numOfFrames, attack_vFrames);
	}
}
