package engine;

import engine.Vector2D;

/**
 * Frame of a Sprite Animation
 * 
 * @author Mileff Peter
 *
 */
public class CSpriteFrame {

	/// Frame texture
	private Texture2D mFrame;

	/// Name of the frame
	private String mName;

	/// Original Bounding boxes
	private BoundingBox2D mBBoxOriginal;

	/// Transformed Bounding boxes
	private BoundingBox2D mBBoxTransformed;

	///
	/// Default Constructor
	///
	public CSpriteFrame() {
		mFrame = null;
		mName = "Empty Frame";
	}

	///
	/// Contructor with texture initialization
	///
	public CSpriteFrame(Texture2D frameTexture, String name) {
		// Store frame texture
		mFrame = frameTexture;

		// name of the frame
		mName = name;
	}
	
	public CSpriteFrame(Texture2D frameTexture) {
		// Store frame texture
		mFrame = frameTexture;
	}

	///
	/// Add a bounding box
	///
	public void AddBoundingBox(Vector2D minpoint, Vector2D maxPoint) {

		// Create new Bounding Box
		mBBoxOriginal = new BoundingBox2D(minpoint, maxPoint);

		// One for transformed Box
		mBBoxTransformed = new BoundingBox2D(minpoint, maxPoint);
	}

	///
	/// Return the original Bounding Box
	///
	public BoundingBox2D GetOriginalBB() {
		return mBBoxOriginal;

	}

	///
	/// Return the transformed Bounding Box
	///
	public BoundingBox2D GetTransformedBB() {

		return mBBoxTransformed;
	}

	///
	/// Get Frame texture
	///
	public Texture2D GetTexture() {
		return mFrame;
	}
}
