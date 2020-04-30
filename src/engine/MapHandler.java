package engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.Decoration;
import common.PlaceOfTower;


public class MapHandler {

	private int[][] map;
	private ArrayList<Decoration> decorations;
	private ArrayList<PlaceOfTower> placeOfTowers;
	private static int rows;
	private static int columns;

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {

		this.map = map;
	}

	public MapHandler(String filename, String decfilename, String potfilename, int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		decorations = readDecorationFile(decfilename);
		placeOfTowers = readTowerplaceFile(potfilename);
		map = readMapFile(filename);
	}
	
	public MapHandler() {
	}

	public static int getRows() {
		return rows;
	}

	public ArrayList<PlaceOfTower> getPlaceOfTowers() {
		return placeOfTowers;
	}

	public void setPlaceOfTowers(ArrayList<PlaceOfTower> placeOfTowers) {
		this.placeOfTowers = placeOfTowers;
	}

	public ArrayList<Decoration> getDecorations() {
		return decorations;
	}

	public void setDecorations(ArrayList<Decoration> decorations) {
		this.decorations = decorations;
	}

	public static int getColumns() {
		return columns;
	}

	public static int[][] readMapFile(String filename) {
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			int matrix[][] = new int[rows][columns];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					matrix[i][j] = sc.nextInt();
					System.out.println(matrix[i][j]);
				}
			}

			return matrix;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<Decoration> readDecorationFile(String filename) {
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			ArrayList<Decoration> decs = new ArrayList<Decoration>();
			
			while (sc.hasNextLine()) {
				Decoration dec = new Decoration();
				dec.setX(sc.nextInt());
				dec.setY(sc.nextInt());
				dec.setZ(sc.nextInt());
				dec.setFilename(sc.next());
				decs.add(dec);
			}
			System.out.println(decs);
			return decs;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ArrayList<PlaceOfTower> readTowerplaceFile(String filename) {
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			ArrayList<PlaceOfTower> decs = new ArrayList<PlaceOfTower>();
			
			while (sc.hasNextLine()) {
				PlaceOfTower dec = new PlaceOfTower();
				dec.setX(sc.nextInt());
				dec.setY(sc.nextInt());
				dec.setZ(sc.nextInt());
				dec.setFilename(sc.next());
				decs.add(dec);
			}
			System.out.println(decs);
			return decs;
		} catch (Exception e) {
			return null;
		}
	}
	

	
	

	

}
