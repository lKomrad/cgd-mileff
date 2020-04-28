package common;

import engine.Vector2D;

public class Orc extends Enemy {

	public Orc() {
		super();
	}

	public Orc(float x, float y) {
		super(x,y);
		
		m_vSpritePosition = new Vector2D(x, y);
	}
	
	public Orc(float x, float y, float scale) {
		super(x,y,scale);
		
		m_vSpritePosition = new Vector2D(x, y);
	}
	
	public void LoadAllTextures() {
		//idleAnimation
		String filenames = "textures/Orc/PNG/PNG Sequences/Idle/0_Orc_Idle_0";
		int numOfFrames = 18;
		LoadTextureGroup(filenames, numOfFrames, idle_vFrames, idle_vFramesFlipped);
		filenames = "textures/Orc/PNG/PNG Sequences/Walking/0_Orc_Walking_0";
		numOfFrames = 24;
		LoadTextureGroup(filenames, numOfFrames, walk_vFrames, walk_vFramesFlipped);
		filenames = "textures/Orc/PNG/PNG Sequences/Slashing/0_Orc_Slashing_0";
		numOfFrames = 12;
		LoadTextureGroup(filenames, numOfFrames, attack_vFrames, attack_vFramesFlipped);
	}
	
}
