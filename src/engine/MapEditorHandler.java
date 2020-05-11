package engine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import common.Decoration;
import common.MapComponent;
import common.Tower;

public class MapEditorHandler {

	// Map, amit szerkesztünk, majd exportálunk
	private static int[][] map = new int[10][10];
	private static ArrayList<MapComponent> mapComponents;
	private static ArrayList<Decoration> decorations = new ArrayList<Decoration>();
	private static ArrayList<Decoration> placeOfTowers = new ArrayList<Decoration>();
	private static ArrayList<Decoration> flags = new ArrayList<Decoration>();
	private static ArrayList<Vector2D> checkpoints = new ArrayList<Vector2D>();

	// Map komponensek, amiktõl variálhatjuk a mapot
	private static ArrayList<MapComponent> mcs;
	private static ArrayList<Decoration> decs;
	private static ArrayList<Decoration> pots;
	private static Decoration cp = new Decoration();

	// Gomb, amivel jóváhagyjuk

	// Térkép adatok, nem tudom ez kelleni fog e
	private int row;
	private int column;

	// Konstruktor(ok)

	public MapEditorHandler() {

	}

	// Getters Setters
	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}
	
	public static ArrayList<Decoration> getFlags() {
		return flags;
	}

	public static void setFlags(ArrayList<Decoration> flags) {
		MapEditorHandler.flags = flags;
	}

	public static Decoration getCp() {
		return cp;
	}

	public static void setCp(Decoration cp) {
		MapEditorHandler.cp = cp;
	}

	public static ArrayList<Vector2D> getCheckpoints() {
		return checkpoints;
	}

	public static void setCheckpoints(ArrayList<Vector2D> checkpoints) {
		MapEditorHandler.checkpoints = checkpoints;
	}

	public static ArrayList<MapComponent> getMapComponents() {
		return mapComponents;
	}

	public void setMapComponents(ArrayList<MapComponent> mapComponents) {
		this.mapComponents = mapComponents;
	}

	public static ArrayList<Decoration> getDecorations() {
		return decorations;
	}

	public void setDecorations(ArrayList<Decoration> decorations) {
		this.decorations = decorations;
	}

	public static ArrayList<Decoration> getPlaceOfTowers() {
		return placeOfTowers;
	}

	public void setPlaceOfTowers(ArrayList<Decoration> placeOfTowers) {
		this.placeOfTowers = placeOfTowers;
	}

	public static ArrayList<MapComponent> getMcs() {
		return mcs;
	}

	public void setMcs(ArrayList<MapComponent> mcs) {
		this.mcs = mcs;
	}

	public static ArrayList<Decoration> getDecs() {
		return decs;
	}

	public void setDecs(ArrayList<Decoration> decs) {
		this.decs = decs;
	}

	public static ArrayList<Decoration> getPots() {
		return pots;
	}

	public void setPots(ArrayList<Decoration> pots) {
		this.pots = pots;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	// Metódusok

	public static void drawMap() {
		ArrayList<MapComponent> tmcs = new ArrayList<MapComponent>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				MapComponent mc = new MapComponent();
				mc.setFilename("textures/MapComponents/fold/kivalogatva/road0.png");
				mc.setX(i * 99);
				mc.setY(j * 99);
				mc.setZ(0);

				Texture2D mapComp = new Texture2D();
				mapComp.CreateTexture(mc.getFilename());
				mapComp.setPosition(mc.getX(), mc.getY(), mc.getZ());
				mc.setTexture(mapComp);

				tmcs.add(mc);
			}
		}
		mapComponents = tmcs;
	}

	/*
	 * public static void drawDecorations() { try{ for (Decoration dec :
	 * decorations) { Texture2D mapComp = new Texture2D();
	 * mapComp.CreateTexture(dec.getFilename()); mapComp.setPosition(dec.getX(),
	 * dec.getY(), dec.getZ()); dec.setTexture(mapComp);
	 * 
	 * } } catch(Exception ex) {
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	public static void read() {
		ArrayList<MapComponent> tmcs = new ArrayList<MapComponent>();
		ArrayList<Decoration> tdecs = new ArrayList<Decoration>();
		ArrayList<Decoration> tpots = new ArrayList<Decoration>();
		String[] decTypes = { "kiegeszitok/ko", "kiegeszitok/fu", "kiegeszitok/kut", "kiegeszitok/kerites",
				"kiegeszitok/kivagott_fa", "epulet/haz/haz", "kiegeszitok/fa" };
		int[] array = { 5, 3, 2, 4, 5, 4, 3 };
		String filename = "";
		int count = 0;
		// ez ilyen eltolás akar lenni, de nem tudok neki értelmes nevet adni
		int placex = 0;
		int placey = 0;

		// Mapkomponensek betöltése
		placex = 1040;
		placey = 50;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 14; j++) {
				MapComponent mc = new MapComponent();
				mc.setId(count);
				mc.setFilename("textures/MapComponents/fold/kivalogatva/road" + count + ".png");
				mc.setX(i * 55 + placex);
				mc.setY(j * 55 + placey);
				mc.setZ(0);

				Texture2D mapComp = new Texture2D();
				mapComp.CreateTexture(mc.getFilename());
				mapComp.setPosition(mc.getX(), mc.getY(), mc.getZ());
				mapComp.setScale(0.5f);
				mc.setTexture(mapComp);

				tmcs.add(mc);
				count++;
			}
		}
		count = 0;

		// Dekorációminták betöltése
		placex = 1300;
		placey = 0;
		int i = 0;
		for (String type : decTypes) {
			for (int j = 0; j < array[i]; j++) {
				count++;
				Decoration dec = new Decoration();
				filename = "textures/MapComponents/" + type + "_00" + count + ".png";
				readdd(dec, filename, j, i + 1, placex, placey, 1f);

				tdecs.add(dec);
			}
			count = 0;
			i++;
		}
		Decoration dec = new Decoration();
		filename = "textures/MapComponents/epulet/var/var.png";
		readdd(dec, filename, 1, 1, 1200, 700, 1);

		tdecs.add(dec);

		// Torony helyeinek betöltése
		Decoration pot = new Decoration();
		filename = "textures/MapComponents/kiegeszitok/fold_001.png";
		readdd(pot, filename, 1, 1, 950, 800, 1);

		tpots.add(pot);
		
		// Checkpointok cucclija
		cp = new Decoration();
		filename = "textures/MapComponents/checkpoints/flag.png";
		readdd(cp, filename, 1, 1, 1020, 760, 0.1f);

		mcs = tmcs;
		decs = tdecs;
		pots = tpots;
		

	}

	public static void temporarymethod() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j] + " , ");
			}
			System.out.println("");
		}

	}

	public static void readdd(Decoration dec, String filename, int i, int j, int placex, int placey, float scale) {
		dec.setFilename(filename);
		dec.setX(i * 100 + placex);
		dec.setY(j * 100 + placey);
		dec.setZ(0);

		Texture2D mapComp = new Texture2D();
		mapComp.CreateTexture(dec.getFilename());
		mapComp.setPosition(dec.getX(), dec.getY(), dec.getZ());
		mapComp.setScale(scale);
		dec.setTexture(mapComp);
	}

	public static Decoration newDecoration(Decoration decoration) {
		Decoration dec = new Decoration();
		dec.setX(decoration.getX());
		dec.setY(decoration.getY());
		dec.setZ(0);
		dec.setFilename(decoration.getFilename());
		dec.loadTexture();
		return dec;
	}

	public static void addDecorationToArrayList(Decoration decoration) {
		Decoration dec = new Decoration();
		dec.setFilename(decoration.getFilename());
		dec.setX(decoration.getX());
		dec.setY(decoration.getY());
		dec.setZ(0);

		Texture2D mapComp = new Texture2D();
		mapComp.CreateTexture(dec.getFilename());
		mapComp.setPosition(dec.getX(), dec.getY(), dec.getZ());
		dec.setTexture(mapComp);
		decorations.add(dec);

	}
	
	public static void addCheckpointsToArrayList(Decoration decoration) {
		Decoration dec = new Decoration();
		dec.setFilename(decoration.getFilename());
		dec.setX(decoration.getX());
		dec.setY(decoration.getY());
		dec.setZ(0);

		Texture2D mapComp = new Texture2D();
		mapComp.CreateTexture(dec.getFilename());
		mapComp.setPosition(dec.getX(), dec.getY(), dec.getZ());
		mapComp.setScale(0.1f);
		dec.setTexture(mapComp);
		flags.add(dec);
		checkpoints.add(new Vector2D(dec.getX(), dec.getY()));

	}

	public static void addPlaceofTowerToArrayList(Decoration decoration) {
		Decoration dec = new Decoration();
		dec.setFilename(decoration.getFilename());
		dec.setX(decoration.getX());
		dec.setY(decoration.getY());
		dec.setZ(0);

		Texture2D mapComp = new Texture2D();
		mapComp.CreateTexture(dec.getFilename());
		mapComp.setPosition(dec.getX(), dec.getY(), dec.getZ());
		dec.setTexture(mapComp);
		placeOfTowers.add(dec);

	}

	public static void addToMap(int i, int j, int mapid) {
		map[i][j] = mapid;
	}

	public static void export(String filename) {
		exportMapComps(filename + "1.txt");
		exportDecs(decorations, filename + "2.txt");
		exportDecs(placeOfTowers, filename + "3.txt");
		exportCheckpoints(filename+ "4.txt");
	}

	public static void exportMapComps(String filename) {
		try {
			FileWriter writer = new FileWriter(filename, false);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					bufferedWriter.write(map[i][j] + " ");
				}
				bufferedWriter.newLine();
			}
			

			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void exportDecs(ArrayList<Decoration> decOrPots, String filename) {
		try {
			FileWriter writer = new FileWriter(filename, false);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			for (Decoration decoration : decOrPots) {
				bufferedWriter.write(decoration.getX() + " ");
				bufferedWriter.write(decoration.getY() + " ");
				bufferedWriter.write(0 + " ");
				bufferedWriter.write(decoration.getFilename());
				if(decOrPots.get(decOrPots.size()-1) != decoration) {
					bufferedWriter.newLine();
				}
			}

			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void exportCheckpoints(String filename) {
		try {
			FileWriter writer = new FileWriter(filename, false);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			for (Vector2D position : getCheckpoints()) {
				bufferedWriter.write(position.x + " ");
				bufferedWriter.write(position.y + " ");
				if(getCheckpoints().get(getCheckpoints().size()-1) != position) {
					bufferedWriter.newLine();
				}
			}
			System.out.println(getCheckpoints());

			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
