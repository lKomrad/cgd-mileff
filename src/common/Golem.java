package common;

import java.util.ArrayList;

import org.joml.Vector2f;

import engine.Texture2D;
import engine.Vector2D;

public class Golem {
	private Vector2f rallyPoint;
	private final boolean isEnemy = false;
	public Vector2f getRallyPoint() {
		return rallyPoint;
	}
	public void setRallyPoint(Vector2f rallyPoint) {
		this.rallyPoint = rallyPoint;
	}
	public boolean isEnemy() {
		return isEnemy;
	}
	// Frames vector
		private ArrayList<Texture2D> m_vFrames;

		// Actual frame
		private int m_iActualFrame;

		// Position of the sprite
		private Vector2D m_vSpritePosition;

		// The last time the animation was update
		private long m_iLastUpdate;

		// The number of frames per second
		private int m_iFps;

		public Golem() {
			m_iActualFrame = 0;
			m_vFrames.clear();
			m_iLastUpdate = System.currentTimeMillis();
			m_iFps = 15; // default FPS animation rate
			m_vFrames = new ArrayList<Texture2D>();
		}

		public Golem(String filenames, int numOfFrames, float X, float Y) {
			m_iActualFrame = 1;
			m_iLastUpdate = System.currentTimeMillis();
			m_iFps = 15; // default FPS animation rate
			m_vFrames = new ArrayList<Texture2D>();

			/** Loading textures */
			LoadTextures(filenames, numOfFrames);

			m_vSpritePosition = new Vector2D(X, Y);
		}

		/** Loads sprite textures */
		public void LoadTextures(String filenames, int numOfFrames) {

			if (numOfFrames == 1) {
				Texture2D tex = new Texture2D();
				tex.CreateTexture(filenames + ".png");
				m_vFrames.add(tex);
			} else {
				/** Loading single textures */
				for (int i = 0; i < numOfFrames; i++) {
					Texture2D tex = new Texture2D();
					
					if(0<=i && i<9) {
						tex.CreateTexture(filenames +"0"+ (i + 1) + ".png");
					}
					else {
						tex.CreateTexture(filenames + (i + 1) + ".png");
					}
					
					
					m_vFrames.add(tex);
				}
			}
		}

		/** Draw Animated Sprite */
		public void Draw() {
			Texture2D tex = m_vFrames.get(m_iActualFrame - 1);
			tex.Draw(m_vSpritePosition);
			Update();
		}

		/** Draw Animated Sprite */
		public void Draw(Vector2D pos) {
			Texture2D tex = m_vFrames.get(m_iActualFrame - 1);
			tex.Draw(pos);
			Update();
		}

		/** Update frames */
		public void Update() {
			if (500.0f / m_iFps < (System.currentTimeMillis() - m_iLastUpdate)) {
				m_iLastUpdate = System.currentTimeMillis();
				if (++m_iActualFrame > m_vFrames.size()) {
					m_iActualFrame = 1;
				}
			}
		}

		/** Draw Animated Sprite */
		public void DrawOne() {
			Texture2D tex = m_vFrames.get(m_iActualFrame - 1);
			tex.Draw(m_vSpritePosition);
			if (1000.0f / m_iFps < (System.currentTimeMillis() - m_iLastUpdate)) {
				m_iLastUpdate = System.currentTimeMillis();
				if (m_iActualFrame < m_vFrames.size()) {
					m_iActualFrame++;
				}
			}
		}

		/** Draw Animated Sprite */
		public void DrawOne(Vector2D pos) {
			Texture2D tex = m_vFrames.get(m_iActualFrame - 1);
			tex.Draw(pos);
			if (1000.0f / m_iFps < (System.currentTimeMillis() - m_iLastUpdate)) {
				m_iLastUpdate = System.currentTimeMillis();
				if (m_iActualFrame < m_vFrames.size()) {
					m_iActualFrame++;
				}
			}
		}

		/** Draw Animated Sprite */
		public void DrawOne(float posX, float posY) {
			Texture2D tex = m_vFrames.get(m_iActualFrame - 1);

			tex.Draw(posX, posY);

			if (1000.0f / m_iFps < (System.currentTimeMillis() - m_iLastUpdate)) {
				m_iLastUpdate = System.currentTimeMillis();
				if (m_iActualFrame < m_vFrames.size()) {
					m_iActualFrame++;
				}
			}
		}

		/** Draw One Frane of the Animated Sprite */
		public void DrawFrame(int frame) {
			Texture2D tex = m_vFrames.get(frame);
			tex.Draw(m_vSpritePosition);
		}

		/** Draw One Frane of the Animated Sprite in X, Y position */
		public void DrawFrame(int frame, float X, float Y) {
			Texture2D tex = m_vFrames.get(frame);
			tex.Draw(X, Y);
		}

		/** Draw One Frane of the Animated Sprite */
		public void DrawFrame(int frame, Vector2D pos) {
			Texture2D tex = m_vFrames.get(frame);
			tex.Draw(pos);
		}

		/** Reset Animation */
		public void Reset() {
			m_iActualFrame = 1;
		}

		/** Get current frame of the Animated Sprite */
		public Texture2D GetCurrentFrameTexture() {
			return m_vFrames.get(m_iActualFrame-1);
		}
		
		/** Get one frame of the Animated Sprite */
		public Texture2D GetTexture(int frame) {
			return m_vFrames.get(frame);
		}

		/** Get the frame number of the Sprite */
		public int GetNumberOfFrames() {
			return m_vFrames.size();
		}

		/** Set Animation Speed */
		public void SetAnimationSpeed(int fps) {
			m_iFps = fps;
		}

		/** Set sprite position */
		public void SetPosition(float X, float Y) {
			m_vSpritePosition.x = X;
			m_vSpritePosition.y = Y;
		}

		/** Set sprite position */
		public void SetPosition(Vector2D pos) {
			m_vSpritePosition = pos;
		}

		public Vector2D GetPosition() {
			return m_vSpritePosition;
		}

		public float GetSpritePosX() {
			return m_vSpritePosition.x;
		}

		public float GetSpritePosY() {
			return m_vSpritePosition.y;
		}

		public void SetPositionX(float posX) {
			m_vSpritePosition.x = posX;
		}

		public void SetPositionY(float posY) {
			m_vSpritePosition.y = posY;
		}

		/** Returns the fps of the animation */
		public int GetAnimationSpeed() {
			return m_iFps;
		}

		public void cleanUp() {
			for (int i = 0; i < m_vFrames.size(); i++) {
				Texture2D tex = m_vFrames.get(i);
				tex.cleanup();
			}
		}
	
	
	
}
