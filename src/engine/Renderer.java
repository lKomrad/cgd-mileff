package engine;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glViewport;

import org.joml.Matrix4f;
import org.joml.Vector3f;


import engine.Window;

import common.Golem;
import common.Map;


/**
 * Simple renderer class
 * 
 * @author Mileff Peter
 *
 */
public class Renderer {

	private final Transformation transformation;

	private ShaderProgram shaderProgram;

	public Renderer() {
		transformation = new Transformation();
	}

	public void init(Window window) throws Exception {
	
		// Create shader
		shaderProgram = new ShaderProgram();
		shaderProgram.createVertexShader(Utils.loadFile("shaders/vertex.vs"));
		shaderProgram.createFragmentShader(Utils.loadFile("shaders/fragment.fs"));
		shaderProgram.link();

		// Create uniforms for world and projection matrices and texture
		shaderProgram.createUniform("projectionMatrix");
		shaderProgram.createUniform("worldMatrix");
		shaderProgram.createUniform("texture_sampler");
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void render(Window window, Texture2D[] textures) {
		clear();

		if (window.isResized()) {
			glViewport(0, 0, window.getWidth(), window.getHeight());
			window.setResized(false);
		}

		shaderProgram.bind();

		// Update orthogonal projection Matrix
		Matrix4f projectionMatrix = transformation.getOrthoProjectionMatrix(0, window.getWidth(), window.getHeight(),
				0);

		shaderProgram.setUniform("projectionMatrix", projectionMatrix);

		shaderProgram.setUniform("texture_sampler", 0);

		// Render each gameItem
		for (Texture2D texture : textures) {
			
			// Set world matrix for this item
			Matrix4f worldMatrix = transformation.getWorldMatrix(texture.getPosition(), texture.getRotation(),
					texture.getScale());

			shaderProgram.setUniform("worldMatrix", worldMatrix);

			// Render the sprite
			texture.render();
		}

		shaderProgram.unbind();
	}
	
	public void render(Window window, Golem golem) {
		clear();

		if (window.isResized()) {
			glViewport(0, 0, window.getWidth(), window.getHeight());
			window.setResized(false);
		}

		shaderProgram.bind();

		// Update orthogonal projection Matrix
		Matrix4f projectionMatrix = transformation.getOrthoProjectionMatrix(0, window.getWidth(), window.getHeight(),
				0);

		shaderProgram.setUniform("projectionMatrix", projectionMatrix);

		shaderProgram.setUniform("texture_sampler", 0);

		// Render each gameItem
	

			// Set world matrix for this item
			Matrix4f worldMatrix = transformation.getWorldMatrix(new Vector3f(golem.GetPosition().x,golem.GetPosition().y,0), golem.GetCurrentFrameTexture().getRotation(),
					golem.GetCurrentFrameTexture().getScale());

			shaderProgram.setUniform("worldMatrix", worldMatrix);

			// Render the sprite
			golem.Draw();
	
		shaderProgram.unbind();
	}
	
	public void render(Window window, Golem golem, Map map) {
		clear();

		if (window.isResized()) {
			glViewport(0, 0, window.getWidth(), window.getHeight());
			window.setResized(false);
		}

		shaderProgram.bind();

		// Update orthogonal projection Matrix
		Matrix4f projectionMatrix = transformation.getOrthoProjectionMatrix(0, window.getWidth(), window.getHeight(),
				0);

		shaderProgram.setUniform("projectionMatrix", projectionMatrix);

		shaderProgram.setUniform("texture_sampler", 0);

		// Render each gameItem
	

			// Set world matrix for this item
			Matrix4f worldMatrix = transformation.getWorldMatrix(new Vector3f(golem.GetPosition().x,golem.GetPosition().y,0), golem.GetCurrentFrameTexture().getRotation(),
					golem.GetCurrentFrameTexture().getScale());

			shaderProgram.setUniform("worldMatrix", worldMatrix);

			// Render the sprite
			//golem.Draw();
			Texture2D[][] maptexture = map.drawMap();
			for (int i = 0; i < map.getNumberofRows(); i++) {
				for (int j = 0; j < map.getNumberofColumns(); j++) {
					// Set world matrix for this item
					Matrix4f worldMatrix2 = transformation.getWorldMatrix(maptexture[j][i].getPosition(), maptexture[i][j].getRotation(),
							maptexture[i][j].getScale());

					shaderProgram.setUniform("worldMatrix", worldMatrix2);

					// Render the sprite
					maptexture[i][j].render();
				}
			}
				
				
	
		shaderProgram.unbind();
	}

	public void cleanup() {
		if (shaderProgram != null) {
			shaderProgram.cleanup();
		}
	}
}
