package common;

import java.util.List;

import engine.MapHandler;
import engine.Texture2D;

public class Map {
	
	private static MapHandler txtReader = new MapHandler("levels/kittylevel.txt", 12, 12);
	private static int numberofRows = MapHandler.getRows();
	private static int numberofColumns = MapHandler.getColumns();
	//private static MapComponent[][] map = txtReader.getMap();
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



	/*public static MapComponent[][] getMap() {
		return map;
	}*/
	
	public static int[][] getMap() {
		return map;
	}


	public static Texture2D[][] getMapTexture() {
		return mapTexture;
	}

	
	public static void loadMap() {
		for (int i = 0; i < numberofRows; i++) {
				for (int j = 0; j < numberofColumns; j++) {
					
					Texture2D mapComp = new Texture2D();
					mapComp.CreateTexture("textures/MapComponents/fold/kivalogatva/road"+map[i][j]+".png");
					mapComp.setPosition(i*99, j*99, 0);
					mapTexture[i][j] = mapComp;
				}
			}
		Map.setMapTexture(mapTexture);
	}



	public static void setMapTexture(Texture2D[][] mapTexture) {
		Map.mapTexture = mapTexture;
	}
	
	
	
	
	

}
