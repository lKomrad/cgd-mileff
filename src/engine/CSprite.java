package engine;

import java.util.ArrayList;

import org.joml.Matrix4f;

import engine.Vector2D;

/*******************************************************************************
 * Animated Texture class as sprite
 * 
 * @author Mileff Peter
 * 
 */
public class CSprite {

	// Frames vector
	private ArrayList<CSpriteFrame> frames;

	public CSprite() {
		frames = new ArrayList<CSpriteFrame>();
	}
	
	public void addSpriteFrame(CSpriteFrame sprite) {
		getFrames().add(sprite);
	}
	
	/** Get one frame of the Animated Sprite */
	public Texture2D GetTexture(int frame) {
		return getFrames().get(frame).GetTexture();
	}
	/** Get the frame number of the Sprite */
	public int GetNumberOfFrames() {
		return getFrames().size();
	}

	public void cleanUp() {
		for (int i = 0; i < getFrames().size(); i++) {
			Texture2D tex = getFrames().get(i).GetTexture();
			tex.cleanup();
		}
	}

	public ArrayList<CSpriteFrame> getFrames() {
		return frames;
	}
}
