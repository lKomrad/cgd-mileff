package game;

import engine.GameEngine;
import engine.IGameLogic;

public class MapEditorGame {
	
	public static void main(String[] args) {
		try {
			boolean vSync = true;
			IGameLogic gameLogic = new MapEditor();
			GameEngine gameEng = new GameEngine("2D Sprite Animation Demo", 1800, 1000, vSync, gameLogic);
			gameEng.start();
		} catch (Exception excp) {
			excp.printStackTrace();
			System.exit(-1);
		}
	}

}
