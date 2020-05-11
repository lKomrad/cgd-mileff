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
	public static int potordecorcp = 0;
	private static boolean decbool = false;
	public static boolean closoljmarbepls = false;

	

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
				window.windowShouldClose();
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
					if(window.getCursorXPosition() > dec.getX() && window.getCursorXPosition() < dec.getX() + dec.getTexture().GetWidth()) {
						 if(window.getCursorYPosition() > dec.getY() && window.getCursorYPosition() < dec.getY() + dec.getTexture().GetHeight()) {
							 dec.getTexture().setScale(1.2f);
							 tempdec.loadTextures(dec);
							 tempdec.getTexture().setPosition(500, 500, 0);
							 potordecorcp = 0;
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
					if(window.getCursorXPosition() > dec.getX() && window.getCursorXPosition() < dec.getX() + dec.getTexture().GetWidth()) {
						 if(window.getCursorYPosition() > dec.getY() && window.getCursorYPosition() < dec.getY() + dec.getTexture().GetHeight()) {
							 dec.getTexture().setScale(1.2f);
							 tempdec.loadTextures(dec);
							 tempdec.getTexture().setPosition(500, 500, 0);
							 potordecorcp = 1;
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
				Decoration cp = MapEditorHandler.getCp();
					if(window.getCursorXPosition() > cp.getX() && window.getCursorXPosition() < cp.getX() + cp.getTexture().GetWidth()) {
						 if(window.getCursorYPosition() > cp.getY() && window.getCursorYPosition() < cp.getY() + cp.getTexture().GetHeight()) {
							 cp.getTexture().setScale(0.12f);
							 tempdec.loadTextures(cp);
							 tempdec.getTexture().setPosition(500, 500, 0);
							 tempdec.getTexture().setScale(0.1f);
							 potordecorcp = 2;
							 decbool = true;
							 
						 }
						 else{
							 cp.getTexture().setScale(0.1f);
							}
					 }
					else{
						cp.getTexture().setScale(0.1f);
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
					if(window.getCursorXPosition() < 990 - (tempdec.getTexture().GetWidth() * tempdec.getTexture().getScale()) && 
							window.getCursorYPosition() < 990 - (tempdec.getTexture().GetHeight() * tempdec.getTexture().getScale()))
					{tempdec.setX((int)window.getCursorXPosition());
					tempdec.setY((int)window.getCursorYPosition());
					if(potordecorcp == 0) {
						MapEditorHandler.addDecorationToArrayList(tempdec);
					}
					else if (potordecorcp == 1) {
						MapEditorHandler.addPlaceofTowerToArrayList(tempdec);
					}
					else if( potordecorcp == 2){
						MapEditorHandler.addCheckpointsToArrayList(tempdec);
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
