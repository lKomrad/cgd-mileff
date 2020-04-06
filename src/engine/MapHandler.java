package engine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MapHandler {

	private int[][] map;
	private static int rows;
	private static int columns;

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {

		this.map = map;
	}

	public MapHandler(String filename, int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		map = readMapFile(filename);
	}
	
	public MapHandler() {
	}

	public static int getRows() {
		return rows;
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
	
	
	

	

}
