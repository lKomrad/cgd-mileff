package game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import common.Golem;
import common.Map;
//import engine.CSprite;
import engine.IGameLogic;
import engine.Renderer;
import engine.Texture2D;
import engine.MapHandler;
//import engine.Vector2D;
import engine.Window;

public class DummyGame implements IGameLogic {

	private final Renderer renderer;

	// 2D Texture items
	//private CSprite sprite;
	private Golem gameItems[] = new Golem[1];
	private Golem golem;
	private Map map;
	private Texture2D[][] mapTextures = new Texture2D[map.getNumberofRows()][map.getNumberofColumns()];

	public DummyGame() {
		renderer = new Renderer();
	}

	@Override
	public void init(Window window) throws Exception {
		renderer.init(window);
		mapTextures = map.drawMap();

		//sprite = new CSprite("textures/bird", 4, 0, 0);
		golem = new Golem("textures/Golems/PNG/Golem_01/PNG Sequences/Idle/Golem_01_Idle_0", 11,0,1);
		gameItems[0] = golem;
	}

	@Override
	public void input(Window window) {
		/*if (window.isKeyPressed(GLFW_KEY_UP)) {
			Vector2D pos = sprite.GetPosition();
			pos.y -= 10;
			sprite.SetPosition(pos);

		} else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
			Vector2D pos = sprite.GetPosition();
			pos.y += 10;
			sprite.SetPosition(pos);

		} else if (window.isKeyPressed(GLFW_KEY_LEFT)) {
			Vector2D pos = sprite.GetPosition();
			pos.x -= 10;
			sprite.SetPosition(pos);

		} else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
			Vector2D pos = sprite.GetPosition();
			pos.x += 10;
			sprite.SetPosition(pos);
		}*/
	}

	@Override
	public void update(float interval) {

	}

	@Override
	public void render(Window window) {
		renderer.render(window, golem, map);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
		//gameItems.cleanUp();
	}
	
	
}
