package engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;


import engine.Window;
import common.Enemy;
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

	/*public void render(Window window, Texture2D[] textures) {
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
	}*/
	
	public void render(Window window, List<Golem> friendlyUnits, List<Enemy> enemyUnits) {
		clear();
		
		glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

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

		
		
		for (int i = 0; i < Map.getNumberofRows(); i++) {
			for (int j = 0; j < Map.getNumberofColumns(); j++) {
				// Set world matrix for this item
				Matrix4f worldMatrix2 = transformation.getWorldMatrix(Map.getMapTexture()[j][i].getPosition(), Map.getMapTexture()[i][j].getRotation(),
						Map.getMapTexture()[i][j].getScale());

				shaderProgram.setUniform("worldMatrix", worldMatrix2);

				// Render the sprite
				Map.getMapTexture()[i][j].render();
			}
		}
			// Render each unit
		renderAllUnits(friendlyUnits, enemyUnits);
	
		shaderProgram.unbind();
	}
	
	public void renderAllUnits(List<Golem> friendlyUnits, List<Enemy> enemyUnits){
		for (Golem friendly : friendlyUnits) {
			// Set world matrix for this item
			Matrix4f worldMatrix = transformation.getWorldMatrix(new Vector3f(friendly.GetPosition().x,friendly.GetPosition().y,0), friendly.GetCurrentFrameTexture().getRotation(),
					friendly.getScale());

			shaderProgram.setUniform("worldMatrix", worldMatrix);

			// Render the sprite
			friendly.Draw();
		}
		for (Enemy enemy : enemyUnits) {
			// Set world matrix for this item
			Matrix4f worldMatrix = transformation.getWorldMatrix(new Vector3f(enemy.GetPosition().x,enemy.GetPosition().y,0), enemy.GetCurrentFrameTexture().getRotation(),
					enemy.getScale());

			shaderProgram.setUniform("worldMatrix", worldMatrix);

			// Render the sprite
			enemy.Draw();
		}
		
		
	}

	public void cleanup() {
		if (shaderProgram != null) {
			shaderProgram.cleanup();
		}
	}
}
