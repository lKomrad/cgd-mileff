package common;

import java.util.ArrayList;

import org.joml.Vector2f;

import engine.Texture2D;
import engine.Vector2D;

public class Golem {
	private Vector2f rallyPoint;
	private final boolean isEnemy = false;
	
	// Frames vector
		private ArrayList<Texture2D> idle_vFrames;
		private ArrayList<Texture2D> walk_vFrames;
		private ArrayList<Texture2D> attack_vFrames;
		

		// Actual frame
		private int m_iActualFrame;

		// Position of the sprite
		private Vector2D m_vSpritePosition;

		// The last time the animation was update
		private long m_iLastUpdate;

		// The number of frames per second
		private int m_iFps;
		
		private float scale;

		public enum Animation {
			Idle,
			Walk,
			Attack
		}
		private Animation animation;
		
		public Golem() {
			m_iActualFrame = 0;
			idle_vFrames.clear();
			walk_vFrames.clear();
			m_iLastUpdate = System.currentTimeMillis();
			m_iFps = 15; // default FPS animation rate
			idle_vFrames = new ArrayList<Texture2D>();
			walk_vFrames = new ArrayList<Texture2D>();
			attack_vFrames = new ArrayList<Texture2D>();
			scale = 1;
		}
		
		public Golem(float x, float y) {
			m_iActualFrame = 1;
			m_iLastUpdate = System.currentTimeMillis();
			m_iFps = 15; // default FPS animation rate
			idle_vFrames = new ArrayList<Texture2D>();
			walk_vFrames = new ArrayList<Texture2D>();
			attack_vFrames = new ArrayList<Texture2D>();
			animation = Animation.Idle;
			scale = 1;
			
			/** Loading textures */
			LoadAllTextures();

			m_vSpritePosition = new Vector2D(x, y);
		}

		public Golem(String filenames, int numOfFrames, float X, float Y) {
			m_iActualFrame = 1;
			m_iLastUpdate = System.currentTimeMillis();
			m_iFps = 15; // default FPS animation rate
			idle_vFrames = new ArrayList<Texture2D>();
			walk_vFrames = new ArrayList<Texture2D>();
			attack_vFrames = new ArrayList<Texture2D>();
			scale = 1;

			/** Loading textures */
			LoadTextures(filenames, numOfFrames);

			m_vSpritePosition = new Vector2D(X, Y);
		}
		
		public void LoadAllTextures() {
			//idleAnimation
			String filenames = "textures/Golems/PNG/Golem_01/PNG Sequences/Idle/Golem_01_Idle_0";
			int numOfFrames = 12;
			LoadTextureGroup(filenames, numOfFrames, idle_vFrames);
			filenames = "textures/Golems/PNG/Golem_01/PNG Sequences/Walking/Golem_01_Walking_0";
			numOfFrames = 18;
			LoadTextureGroup(filenames, numOfFrames, walk_vFrames);
			filenames = "textures/Golems/PNG/Golem_01/PNG Sequences/Attacking/Golem_01_Attacking_0";
			numOfFrames = 12;
			LoadTextureGroup(filenames, numOfFrames, attack_vFrames);
		}

		/** Loads sprite textures */
		public void LoadTextures(String filenames, int numOfFrames) {

			if (numOfFrames == 1) {
				Texture2D tex = new Texture2D();
				tex.CreateTexture(filenames + ".png");
				idle_vFrames.add(tex);
			} else {
				/** Loading single textures */
				for (int i = 0; i < numOfFrames; i++) {
					Texture2D tex = new Texture2D();
					
					if(0<=i && i<10) {
						tex.CreateTexture(filenames +"0"+ (i) + ".png");
					}
					else {
						tex.CreateTexture(filenames + (i) + ".png");
					}
					
					
					idle_vFrames.add(tex);
				}
			}
		}
		
		public void LoadTextureGroup(String filenames, int numOfFrames, ArrayList<Texture2D> list) {

			if (numOfFrames == 1) {
				Texture2D tex = new Texture2D();
				tex.CreateTexture(filenames + ".png");
				list.add(tex);
			} else {
				/** Loading single textures */
				for (int i = 0; i < numOfFrames; i++) {
					Texture2D tex = new Texture2D();
					
					if(0<=i && i<10) {
						tex.CreateTexture(filenames +"0"+ (i) + ".png");
					}
					else {
						tex.CreateTexture(filenames + (i) + ".png");
					}
					
					
					list.add(tex);
				}
			}
		}
		
		
		
		/*public void loadAnimation() {
			String animationState;
			int numOfFrames;
			switch (this.animation) {
				case Idle:
					animationState = "textures/Golems/PNG/Golem_01/PNG Sequences/Idle/Golem_01_Idle_0";
					numOfFrames = 12;
					LoadTextures(animationState, numOfFrames);
					break;
				case Walk:
					animationState = "textures/Golems/PNG/Golem_01/PNG Sequences/Walking/Golem_01_Walking_0";
					numOfFrames = 18;
					LoadTextures(animationState, numOfFrames);
					break;
				default:
					break;
			}
		}*/
		
		public float getScale() {
			return this.scale;
		}

		public void setScale(float scale) {
			this.scale = scale;
		}
		
		public Animation getAnimation() {
			return animation;
		}

		public void setAnimation(Animation animation) {
			this.animation = animation;
		}
		
		//Unnecessary
		/*public void setSpriteScales(float scale) {
			for(int i = 0; i< m_vFrames.size(); i++) {
				m_vFrames.get(i).setScale(scale);
			}
		}*/

		/** Draw Animated Sprite */
		public void Draw() {
			Texture2D tex;
			switch (this.animation) {
			case Idle:
				tex = idle_vFrames.get(m_iActualFrame - 1);
				System.out.println("Actual frame: " +m_iActualFrame);
				break;
			case Walk:
				tex = walk_vFrames.get(m_iActualFrame - 1);
				System.out.println("Actual frame: " +m_iActualFrame);
				break;
			case Attack:
				tex = attack_vFrames.get(m_iActualFrame - 1);
				System.out.println("Actual frame: " +m_iActualFrame);
				break;
			default:
				tex = idle_vFrames.get(m_iActualFrame - 1);
				System.out.println("Actual frame: " +m_iActualFrame);
				break;
			}
			
			tex.Draw(m_vSpritePosition);
			Update();
		}

		/** Draw Animated Sprite */
		public void Draw(Vector2D pos) {
			Texture2D tex = idle_vFrames.get(m_iActualFrame - 1);
			tex.Draw(pos);
			Update();
		}

		/** Update frames */
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
						this.animation = Animation.Idle;
					}
					break;
				default:
					break;
				}
				
			}
		}

		/** Draw Animated Sprite */
		public void DrawOne() {
			Texture2D tex = idle_vFrames.get(m_iActualFrame - 1);
			tex.Draw(m_vSpritePosition);
			if (1000.0f / m_iFps < (System.currentTimeMillis() - m_iLastUpdate)) {
				m_iLastUpdate = System.currentTimeMillis();
				if (m_iActualFrame < idle_vFrames.size()) {
					m_iActualFrame++;
				}
			}
		}

		/** Draw Animated Sprite */
		public void DrawOne(Vector2D pos) {
			Texture2D tex = idle_vFrames.get(m_iActualFrame - 1);
			tex.Draw(pos);
			if (1000.0f / m_iFps < (System.currentTimeMillis() - m_iLastUpdate)) {
				m_iLastUpdate = System.currentTimeMillis();
				if (m_iActualFrame < idle_vFrames.size()) {
					m_iActualFrame++;
				}
			}
		}

		/** Draw Animated Sprite */
		public void DrawOne(float posX, float posY) {
			Texture2D tex = idle_vFrames.get(m_iActualFrame - 1);

			tex.Draw(posX, posY);

			if (1000.0f / m_iFps < (System.currentTimeMillis() - m_iLastUpdate)) {
				m_iLastUpdate = System.currentTimeMillis();
				if (m_iActualFrame < idle_vFrames.size()) {
					m_iActualFrame++;
				}
			}
		}

		/** Draw One Frane of the Animated Sprite */
		public void DrawFrame(int frame) {
			Texture2D tex = idle_vFrames.get(frame);
			tex.Draw(m_vSpritePosition);
		}

		/** Draw One Frane of the Animated Sprite in X, Y position */
		public void DrawFrame(int frame, float X, float Y) {
			Texture2D tex = idle_vFrames.get(frame);
			tex.Draw(X, Y);
		}

		/** Draw One Frane of the Animated Sprite */
		public void DrawFrame(int frame, Vector2D pos) {
			Texture2D tex = idle_vFrames.get(frame);
			tex.Draw(pos);
		}

		/** Reset Animation */
		public void Reset() {
			m_iActualFrame = 1;
		}

		
		public Vector2f getRallyPoint() {
			return rallyPoint;
		}
		public void setRallyPoint(Vector2f rallyPoint) {
			this.rallyPoint = rallyPoint;
		}
		public boolean isEnemy() {
			return isEnemy;
		}
		/** Get current frame of the Animated Sprite */
		public Texture2D GetCurrentFrameTexture() {
			switch (this.animation) {
			case Idle:
				return idle_vFrames.get(m_iActualFrame-1);
			case Walk:
				return walk_vFrames.get(m_iActualFrame-1);
			default:
				return idle_vFrames.get(m_iActualFrame-1);
			}
			
		}
		
		/** Get one frame of the Animated Sprite */
		public Texture2D GetTexture(int frame) {
			return idle_vFrames.get(frame);
		}

		/** Get the frame number of the Sprite */
		public int GetNumberOfFrames() {
			return idle_vFrames.size();
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
		
		public void Attack() {
			this.animation = Animation.Attack;
		}

		public void cleanUp() {
			for (int i = 0; i < idle_vFrames.size(); i++) {
				Texture2D tex = idle_vFrames.get(i);
				tex.cleanup();
			}
		}
	
	
	
}
