package game;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_2;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_3;

import java.util.ArrayList;
import java.util.List;

import common.Decoration;
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
	public static Decoration tempdec = new Decoration();
	public static boolean potordec = false;
	private static boolean decbool = false;
	private static boolean closoljmarbepls = false;

	

	public static Decoration getTempdec() {
		return tempdec;
	}

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
		//MapEditorHandler.drawDecorations();
		
	}

	@Override
	public void input(Window window) {
		if (window.isKeyPressed(GLFW_KEY_ENTER)) {
			if(closoljmarbepls == false) {
				MapEditorHandler.export("ezegyproba");
				MapEditorHandler.temporarymethod();
				closoljmarbepls = true;
			}
			
			
		}
		else if (window.mouseButtonDown(GLFW_MOUSE_BUTTON_1)) {
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
						 mc.setId(mapcomp.getId());
						 changeTexture(mc);
						 
						MapEditorHandler.addToMap((int)(window.getCursorYPosition()/99),
							 (int)(window.getCursorXPosition()/99), mc.getId());
						 
					 }
				 }
				
			}
		}
		 if (window.mouseButtonDown(GLFW_MOUSE_BUTTON_2)) {
				for (Decoration dec : MapEditorHandler.getDecs()) {
					if(window.getCursorXPosition() > dec.getX() && window.getCursorXPosition() < dec.getX() + 50) {
						 if(window.getCursorYPosition() > dec.getY() && window.getCursorYPosition() < dec.getY() + 50) {
							 dec.getTexture().setScale(1.2f);
							 tempdec.loadTextures(dec);
							 tempdec.getTexture().setPosition(500, 500, 0);
							 potordec = false;
							 decbool = true;
							 
						 }
						 else{
							 dec.getTexture().setScale(1);
							}
					 }
					else{
						dec.getTexture().setScale(1);
					}
					
				}
				for (Decoration dec : MapEditorHandler.getPots()) {
					if(window.getCursorXPosition() > dec.getX() && window.getCursorXPosition() < dec.getX() + 50) {
						 if(window.getCursorYPosition() > dec.getY() && window.getCursorYPosition() < dec.getY() + 50) {
							 dec.getTexture().setScale(1.2f);
							 tempdec.loadTextures(dec);
							 tempdec.getTexture().setPosition(500, 500, 0);
							 potordec = true;
							 decbool = true;
							 
						 }
						 else{
							 dec.getTexture().setScale(1);
							}
					 }
					else{
						dec.getTexture().setScale(1);
					}
					
				}
				/*for (MapComponent mc : MapEditorHandler.getMapComponents()) {
					if(window.getCursorXPosition() > mc.getX() && window.getCursorXPosition() < mc.getX() + 99) {
						 if(window.getCursorYPosition() > mc.getY() && window.getCursorYPosition() < mc.getY() + 99) {
							 mc.setFilename(tempdec.getFilename());
							 changeTexture(mc);
						 }
					 }
					
				}*/
				
		}
		 if(tempdec != null && decbool == true) {
				tempdec.getTexture().setPosition((float)window.getCursorXPosition(),
						(float)window.getCursorYPosition(), 0);
				if (window.mouseButtonDown(GLFW_MOUSE_BUTTON_3)) {
					if(window.getCursorXPosition()< 900 && window.getCursorYPosition()< 900)
					{tempdec.setX((int)window.getCursorXPosition());
					tempdec.setY((int)window.getCursorYPosition());
					if(potordec == false) {
						MapEditorHandler.addDecorationToArrayList(tempdec);
					}
					else {
						MapEditorHandler.addPlaceofTowerToArrayList(tempdec);
					}
					
					decbool = false;
					}
				}
			}
		 else {
			 
			
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
