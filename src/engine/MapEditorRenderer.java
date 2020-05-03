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

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector3f;


import engine.Window;
import game.DummyGame;
import common.Decoration;
import common.Enemy;
import common.Golem;
import common.Map;
import common.MapComponent;
import common.PlaceOfTower;
import common.Tower;
import common.Unit;


/**
 * Simple renderer class
 * 
 * @author Mileff Peter
 *
 */
public class MapEditorRenderer {

	private final Transformation transformation;

	private ShaderProgram shaderProgram;

	public MapEditorRenderer() {
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

	
	public void render(Window window) {
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

		for (MapComponent mc : MapEditorHandler.getMapComponents()) {
			
			Texture2D texture = mc.getTexture(); 
			// Set world matrix for this item
			Matrix4f worldMatrix2 = transformation.getWorldMatrix(texture.getPosition(), texture.getRotation(),
					texture.getScale());

			shaderProgram.setUniform("worldMatrix", worldMatrix2);

			// Render the decorations
			texture.render();
		}
		
		for (MapComponent mc : MapEditorHandler.getMcs()) {
			
			Texture2D texture = mc.getTexture(); 
			// Set world matrix for this item
			Matrix4f worldMatrix2 = transformation.getWorldMatrix(texture.getPosition(), texture.getRotation(),
					texture.getScale());

			shaderProgram.setUniform("worldMatrix", worldMatrix2);

			// Render the decorations
			texture.render();
		}
		
		for (Decoration decs : MapEditorHandler.getDecs()) {
			
			Texture2D texture = decs.getTexture(); 
			// Set world matrix for this item
			Matrix4f worldMatrix2 = transformation.getWorldMatrix(texture.getPosition(), texture.getRotation(),
					texture.getScale());

			shaderProgram.setUniform("worldMatrix", worldMatrix2);

			// Render the decorations
			texture.render();
		}
				
			
		for (Decoration pots : MapEditorHandler.getPots()) {
			
			Texture2D texture = pots.getTexture(); 
			// Set world matrix for this item
			Matrix4f worldMatrix2 = transformation.getWorldMatrix(texture.getPosition(), texture.getRotation(),
					texture.getScale());

			shaderProgram.setUniform("worldMatrix", worldMatrix2);

			// Render the decorations
			texture.render();
		}
		
			
		
	
		shaderProgram.unbind();
	}
	
	
	
	

	public void cleanup() {
		if (shaderProgram != null) {
			shaderProgram.cleanup();
		}
	}
}
