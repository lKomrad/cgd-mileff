package game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

import java.util.ArrayList;
import java.util.List;

import common.Unit;
import common.Unit.Animation;
import common.Unit.CurrentAction;
import common.Enemy;
import common.Goblin;
import common.Golem;
import common.Map;
import common.Ogre;
import common.Orc;
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
	//private Golem friendlyUnits[] = new Golem[1];
	private List<Golem> friendlyUnits = new ArrayList<Golem>();
	//private Enemy enemyUnits[] = new Enemy[1];
	private List<Enemy> enemyUnits = new ArrayList<Enemy>();
	private Map map;
	private Texture2D[][] mapTextures = new Texture2D[map.getNumberofRows()][map.getNumberofColumns()];
	boolean walking;
	boolean stopped;

	public DummyGame() {
		renderer = new Renderer();
	}

	@Override
	public void init(Window window) throws Exception {
		renderer.init(window);
		Map.loadMap();

		Golem golem = new Golem(300,450);
		golem.setScale(0.33f);
		
		friendlyUnits.add(golem);
		
		enemyUnits.add(new Orc(800,50,0.2f));
		//enemyUnits.add(new Ogre(800,250,0.2f));
		enemyUnits.add(new Ogre(0,100,0.2f));
		enemyUnits.add(new Goblin(800,400,0.2f));
		
		walking = true;
		stopped = false;
	}

	@Override
	public void input(Window window) {
		if (window.isKeyPressed('A')) {
			friendlyUnits.get(0).Reset();
			friendlyUnits.get(0).Attack();

		}/* else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
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
		float golemX = friendlyUnits.get(0).GetSpritePosX();
		float orcX = enemyUnits.get(0).GetSpritePosX();
		
		checkUnitActions(friendlyUnits, enemyUnits);
	}
	
	public void checkUnitActions(List<Golem> friendlyUnits, List<Enemy> enemyUnits) {
		//sorrend fontos
		checkEnemyWalking(enemyUnits);
		checkForFights(friendlyUnits, enemyUnits);
		walkToGoal(enemyUnits);
		setCorrectAnimations(friendlyUnits, enemyUnits);
	}
	
	public void checkForFights(List<Golem> friendlyUnits, List<Enemy> enemyUnits) {
		for (Enemy enemy : enemyUnits) {
			for (Golem golem : friendlyUnits) {
				if (GameLogic.calculateDistance(enemy, golem) < 200) {
					enemy.setCurrentAction(CurrentAction.Attacking);
					golem.setCurrentAction(CurrentAction.Attacking);
				}
			}
		}
	}
	
	public void checkEnemyWalking(List<Enemy> enemyUnits) {
		for (Enemy enemy : enemyUnits) {
			if(enemy.NotReachedGoal()) {
				enemy.setCurrentAction(CurrentAction.Walking);
			}
		}
	}
	
	public void walkToGoal(List<Enemy> enemyUnits) {
		for (Enemy enemy : enemyUnits) {
			if(enemy.getCuttentAction().equals(CurrentAction.Walking)) {
				enemy.Walk();
			}
		}
	}
	
	public void setCorrectAnimations(List<Golem> friendlyUnits, List<Enemy> enemyUnits) {
		for (Golem golem : friendlyUnits) {
			golem.setCorrectAnimation();
		}
		for (Enemy enemy : enemyUnits) {
			enemy.setCorrectAnimation();
		}
	}

	@Override
	public void render(Window window) {
		renderer.render(window, friendlyUnits, enemyUnits, map);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
		//gameItems.cleanUp();
	}
	
	
}
