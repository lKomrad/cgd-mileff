package common;

import engine.MapHandler;
import engine.Texture2D;

public class Map {
	
	private static MapHandler txtReader = new MapHandler("levels/testlevel.txt", 3, 3);
	private static int numberofRows = MapHandler.getRows();
	private static int numberofColumns = MapHandler.getColumns();
	private static int[][] map = txtReader.getMap();
	private static Texture2D[][] mapTexture = new Texture2D[MapHandler.getRows()][MapHandler.getColumns()];
	
	
	
	public static MapHandler getTxtReader() {
		return txtReader;
	}



	public static int getNumberofRows() {
		return numberofRows;
	}



	public static int getNumberofColumns() {
		return numberofColumns;
	}



	public static int[][] getMap() {
		return map;
	}



	public static Texture2D[][] getMapTexture() {
		return mapTexture;
	}



	public static Texture2D[][] drawMap() {
		for (int i = 0; i < numberofRows; i++) {
			for (int j = 0; j < numberofColumns; j++) {
				Texture2D mapComp = new Texture2D();
				switch (map[i][j]) {
				case 0:
					break;
				case 1:
					mapComp.CreateTexture("textures/MapComponents/nemut.png");
					break;
				case 2:
					mapComp.CreateTexture("textures/MapComponents/ut.png");
					break;
				case 3:
					mapComp.CreateTexture("textures/MapComponents/nemut.png");
					break;
				default:
					break;
				}
				mapComp.setPosition(i*200, j*200, 0);
				mapTexture[i][j] = mapComp;
			}
		}
		return mapTexture;
	}

}
