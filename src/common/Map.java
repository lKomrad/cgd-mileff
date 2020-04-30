package common;

import java.util.ArrayList;
import java.util.List;

import engine.MapHandler;
import engine.Texture2D;

public class Map {
	
	private static MapHandler txtReader = new MapHandler("levels/kittylevel.txt", "levels/kittylevel2.txt", 12, 12);
	private static int numberofRows = MapHandler.getRows();
	private static int numberofColumns = MapHandler.getColumns();
	//private static MapComponent[][] map = txtReader.getMap();
	private static int[][] map = txtReader.getMap();
	private static Texture2D[][] mapTexture = new Texture2D[MapHandler.getRows()][MapHandler.getColumns()];
	private static ArrayList<Decoration> decorations = txtReader.getDecorations();
	private static ArrayList<Texture2D> decorationTextures = new ArrayList();
	
	
	public Map() {
		
	}
	


	public static ArrayList<Texture2D> getDecorationTextures() {
		return decorationTextures;
	}



	public static void setDecorationTextures(ArrayList<Texture2D> decorationTextures) {
		Map.decorationTextures = decorationTextures;
	}



	public static ArrayList<Decoration> getDecorations() {
		return decorations;
	}



	public static void setDecorations(ArrayList<Decoration> decorations) {
		Map.decorations = decorations;
	}



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

	public static void loadDecorations() {
		//ezek itt ideiglenesek, majd ehelyett fileból olvassa majd be 
		//ezt majd rendezni fogom x szerint növekvõ sorrendbe
		/*decorations.add(new Decoration(600, 912, 0, "textures/MapComponents/kiegeszitok/kivagott_fa_001.png"));
		decorations.add(new Decoration(500, 276, 0, "textures/MapComponents/kiegeszitok/kivagott_fa_002.png"));
		decorations.add(new Decoration(650, 823, 0, "textures/MapComponents/kiegeszitok/kivagott_fa_003.png"));
		decorations.add(new Decoration(640, 730, 0, "textures/MapComponents/kiegeszitok/kivagott_fa_004.png"));
		decorations.add(new Decoration(700, 780, 0, "textures/MapComponents/kiegeszitok/kivagott_fa_005.png"));
		decorations.add(new Decoration(800, 340, 0, "textures/MapComponents/kiegeszitok/fa_003.png"));
		decorations.add(new Decoration(900, 650, 0, "textures/MapComponents/kiegeszitok/fa_001.png"));
		decorations.add(new Decoration(100, 240, 0, "textures/MapComponents/kiegeszitok/fu_001.png"));
		decorations.add(new Decoration(320, 120, 0, "textures/MapComponents/kiegeszitok/fu_003.png"));
		decorations.add(new Decoration(54, 870, 0, "textures/MapComponents/kiegeszitok/fu_002.png"));
		decorations.add(new Decoration(150, 790, 0, "textures/MapComponents/kiegeszitok/fu_001.png"));
		decorations.add(new Decoration(187, 30, 0, "textures/MapComponents/kiegeszitok/fu_002.png"));
		decorations.add(new Decoration(1100, 20, 0, "textures/MapComponents/kiegeszitok/fu_003.png"));*/
		
		System.out.println("lefutok?");
		ArrayList<Texture2D> decTextures = new ArrayList<Texture2D>();
		
		for (Decoration decoration : decorations) {
			Texture2D dec = new Texture2D();
			dec.CreateTexture(decoration.getFilename());
			dec.setPosition(decoration.getX(), decoration.getY(), decoration.getZ());
			decTextures.add(dec);
		}
		Map.setDecorationTextures(decTextures);
	}

	public static void setMapTexture(Texture2D[][] mapTexture) {
		Map.mapTexture = mapTexture;
	}
	
	
	
	
	

}
