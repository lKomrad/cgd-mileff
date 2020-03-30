package engine;


import java.io.File;

import java.util.Scanner;

public class TxtReader {

	public static void main(String args[]) {
		int[][] level = readFile("levels/testlevel.txt", 3, 3);
		System.out.println(level[0][0]);
		System.out.println(level[0][1]);
		System.out.println(level[0][2]);
	}

	public static int[][] readFile(String filename, int rows, int columns) {
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			int matrix[][] = new int[rows][columns];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					matrix[i][j] = sc.nextInt();
				}
			}

			System.out.println("tata");
			return matrix;
		} catch (Exception e) {
			System.out.println("tata2");
			return null;
		}
	}

}
