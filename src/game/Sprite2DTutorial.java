package game;

import engine.GameEngine;
import engine.IGameLogic;

/**
 * Simple tutorial for 2D texture loading and rendering
 * 
 * @author Mileff Peter
 *
 */
public class Sprite2DTutorial {

	public static void main(String[] args) {
		try {
			boolean vSync = true;
			IGameLogic gameLogic = new DummyGame();
			GameEngine gameEng = new GameEngine("2D Sprite Animation Demo", 1024, 768, vSync, gameLogic);
			gameEng.start();
		} catch (Exception excp) {
			excp.printStackTrace();
			System.exit(-1);
		}
	}
}