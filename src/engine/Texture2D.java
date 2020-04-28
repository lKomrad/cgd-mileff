package engine;

import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glPixelStorei;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_LINEAR;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.joml.Vector3f;

import engine.Vector2D;

import engine.PNGDecoder.Format;

/*import com.iit.uni.engine.graph.Mesh;
import com.iit.uni.engine.graph.PNGDecoder;
import com.iit.uni.engine.graph.PNGDecoder.Format;*/

/**
 * Texture 2D Class
 * 
 * @author Mileff Peter
 *
 */
public class Texture2D {

	// Hold the texture mesh
	private Mesh mesh = null;

	// Texture position
	private Vector3f position;

	// Texture scale
	private float scale;

	// Texture orientation
	private Vector3f rotation;

	// Texture width
	private int mWidth;

	// Texture height
	private int mHeight;

	// Texture OpenGL ID
	private int textureID;

	public Texture2D() {
		textureID = -1;
		scale = 1;
		mWidth = 0;
		mHeight = 0;

		position = new Vector3f(0, 0, 0);
		rotation = new Vector3f(0, 0, 0);
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, textureID);
	}

	public int getId() {
		return textureID;
	}

	/**
	 * Creates a 2d textures mesh
	 * 
	 * @param filename
	 * @return
	 */
	public boolean CreateTexture(String filename) {

		try {
			loadTexture(filename);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		// Create the Mesh
		float[] positions = new float[] { 
				0.0f, mHeight, 0.0f, 
				mWidth, mHeight, 0.0f, 
				mWidth, 0.0f, 0.0f, 
				mWidth, 0.0f, 0.0f, 
				0.0f, 0.0f, 0.0f, 
				0.0f, mHeight, 0.0f, };

		float[] textCoords = new float[] { 
				0.0f, 1.0f, 
				1.0f, 1.0f, 
				1.0f, 0.0f, 
				1.0f, 0.0f, 
				0.0f, 0.0f, 
				0.0f, 1.0f };
		
		/*float[] textCoords = new float[] { 
				1.0f, 1.0f, 
				0.0f, 1.0f, 
				0.0f, 0.0f,
				0.0f, 0.0f, 
				1.0f, 0.0f, 
				1.0f, 1.0f };*/

		mesh = new Mesh(positions, textCoords, this, 6);
		return true;
	}
	
	public boolean CreateTextureFlipped(String filename) {

		try {
			loadTexture(filename);
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		// Create the Mesh
		float[] positions = new float[] { 
				0.0f, mHeight, 0.0f, 
				mWidth, mHeight, 0.0f, 
				mWidth, 0.0f, 0.0f, 
				mWidth, 0.0f, 0.0f, 
				0.0f, 0.0f, 0.0f, 
				0.0f, mHeight, 0.0f, };

		/*float[] textCoords = new float[] { 
				0.0f, 1.0f, 
				1.0f, 1.0f, 
				1.0f, 0.0f, 
				1.0f, 0.0f, 
				0.0f, 0.0f, 
				0.0f, 1.0f };*/
		
		float[] textCoords = new float[] { 
				1.0f, 1.0f, 
				0.0f, 1.0f, 
				0.0f, 0.0f,
				0.0f, 0.0f, 
				1.0f, 0.0f, 
				1.0f, 1.0f };

		mesh = new Mesh(positions, textCoords, this, 6);
		return true;
	}

	private void loadTexture(String fileName) throws Exception {
		// Load Texture file

		File initialFile = new File(fileName);
		InputStream targetStream = new FileInputStream(initialFile);

		PNGDecoder decoder = new PNGDecoder(targetStream);

		mWidth = decoder.getWidth();
		mHeight = decoder.getHeight();

		// Load texture contents into a byte buffer
		ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
		decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
		buf.flip();

		// Create a new OpenGL texture
		textureID = glGenTextures();

		// Bind the texture
		glBindTexture(GL_TEXTURE_2D, textureID);

		// Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte
		// size
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

		 glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		 glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		// Upload the texture data
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE,
				buf);

		// Generate Mip Map
		glGenerateMipmap(GL_TEXTURE_2D);
	}

	public void cleanup() {
		glDeleteTextures(textureID);
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(float x, float y, float z) {
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(float x, float y, float z) {
		this.rotation.x = x;
		this.rotation.y = y;
		this.rotation.z = z;
	}

	public void render() {
		mesh.render();
	}
	
	public void Draw(float x, float y) {
		mesh.render();
	}

	public void Draw(Vector2D vec) {
		mesh.render();
	}

	public Mesh getMesh() {
		return mesh;
	}
}