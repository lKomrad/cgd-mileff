package game;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_2;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_3;

import java.util.ArrayList;
import java.util.List;

import common.Enemy;
import common.Goblin;
import common.Golem;
import common.Map;
import common.MapComponent;
import common.Ogre;
import common.Orc;
import common.PlaceOfTower;
import common.Unit;
import common.Unit.CurrentAction;
import engine.IGameLogic;
import engine.MapEditorHandler;
import engine.MapEditorRenderer;
import engine.Renderer;
import engine.Texture2D;
import engine.Timer;
import engine.Window;

public class MapEditor implements IGameLogic{
	
	private final MapEditorRenderer renderer;
	private static MapComponent mapcomp;

	

	Timer timer = new Timer();
	float elapsedSeconds = 0;

	public MapEditor() {
		renderer = new MapEditorRenderer();
	}

	@Override
	public void init(Window window) throws Exception {
		renderer.init(window);
		MapEditorHandler.drawMap();
		MapEditorHandler.read();
		
	}

	@Override
	public void input(Window window) {
		 if (window.mouseButtonDown(GLFW_MOUSE_BUTTON_1)) {
				for (MapComponent mc : MapEditorHandler.getMcs()) {
					if(window.getCursorXPosition() > mc.getX() && window.getCursorXPosition() < mc.getX() + 55) {
						 if(window.getCursorYPosition() > mc.getY() && window.getCursorYPosition() < mc.getY() + 55) {
							 mc.getTexture().setScale(0.45f);
							 mapcomp = mc;
						 }
						 else{
								mc.getTexture().setScale(0.5f);
							}
					 }
					else{
						mc.getTexture().setScale(0.5f);
					}
					
				}
				for (MapComponent mc : MapEditorHandler.getMapComponents()) {
					if(window.getCursorXPosition() > mc.getX() && window.getCursorXPosition() < mc.getX() + 99) {
						 if(window.getCursorYPosition() > mc.getY() && window.getCursorYPosition() < mc.getY() + 99) {
							 mc.setFilename(mapcomp.getFilename());
							 changeTexture(mc);
						 }
					 }
					
				}
				
		}
				

	}
	
	public void changeTexture(MapComponent mc) {
		Texture2D mapComp = new Texture2D();
		mapComp.CreateTexture(mc.getFilename());
		mapComp.setPosition(mc.getX(), mc.getY(), mc.getZ());
		mapComp.setScale(1);
		mc.setTexture(mapComp);
	}

	@Override
	public void update(float interval) {
		
		
		collectGarbage();
		//System.gc();
	}
	
	
	
	
	public void collectGarbage() {
		elapsedSeconds += timer.getElapsedTime();
		if(elapsedSeconds > 1) {
			elapsedSeconds = 0;
			System.gc();
		}
	}

	@Override
	public void render(Window window) {
		renderer.render(window);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
		//gameItems.cleanUp();
	}
	
	

}
