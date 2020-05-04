package common;

import java.util.ArrayList;
import java.util.List;

import engine.MapHandler;
import engine.Texture2D;

public class Map {
	
	private static MapHandler txtReader = new MapHandler("ezegyproba1.txt", "ezegyproba2.txt", "ezegyproba3.txt", 10, 10);
	private static int numberofRows = MapHandler.getRows();
	private static int numberofColumns = MapHandler.getColumns();
	//private static MapComponent[][] map = txtReader.getMap();
	private static int[][] map = txtReader.getMap();
	private static Texture2D[][] mapTexture = new Texture2D[MapHandler.getRows()][MapHandler.getColumns()];
	private static ArrayList<PlaceOfTower> placeOfTowers = txtReader.getPlaceOfTowers();
	private static ArrayList<Texture2D> placeOfTowersTextures = new ArrayList();
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



	public static ArrayList<PlaceOfTower> getPlaceOfTowers() {
		return placeOfTowers;
	}



	public static void setPlaceOfTowers(ArrayList<PlaceOfTower> placeOfTowers) {
		Map.placeOfTowers = placeOfTowers;
	}



	public static ArrayList<Texture2D> getPlaceOfTowersTextures() {
		return placeOfTowersTextures;
	}



	public static void setPlaceOfTowersTextures(ArrayList<Texture2D> placeOfTowersTextures) {
		Map.placeOfTowersTextures = placeOfTowersTextures;
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
	
public static void loadTowerPlaces() {
		
		System.out.println("lefutok?");
		ArrayList<Texture2D> decTextures = new ArrayList<Texture2D>();
		
		for (PlaceOfTower decoration : placeOfTowers) {
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
