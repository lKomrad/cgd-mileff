package engine;

import java.util.ArrayList;

import common.Decoration;
import common.MapComponent;

public class MapEditorHandler {
	
	//Map, amit szerkesztünk, majd exportálunk
	private int[][] map;
	private static ArrayList<MapComponent> mapComponents;
	private ArrayList<Decoration> decorations;
	private ArrayList<Decoration> placeOfTowers;
	
	//Map komponensek, amiktõl variálhatjuk a mapot
	private static ArrayList<MapComponent> mcs;
	private static ArrayList<Decoration> decs;
	private static ArrayList<Decoration> pots;
	
	//Gomb, amivel jóváhagyjuk
	
	
	//Térkép adatok, nem tudom ez kelleni fog e
	private int row;
	private int column;
	
	//Konstruktor(ok)
	
	public MapEditorHandler() {
		
	}
	
	
	
	//Getters Setters
	public int[][] getMap() {
		return map;
	}
	public void setMap(int[][] map) {
		this.map = map;
	}
	public static ArrayList<MapComponent> getMapComponents() {
		return mapComponents;
	}
	public void setMapComponents(ArrayList<MapComponent> mapComponents) {
		this.mapComponents = mapComponents;
	}
	public ArrayList<Decoration> getDecorations() {
		return decorations;
	}
	public void setDecorations(ArrayList<Decoration> decorations) {
		this.decorations = decorations;
	}
	public ArrayList<Decoration> getPlaceOfTowers() {
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
	
	//Metódusok
	
	public static void drawMap() {
		ArrayList<MapComponent> tmcs = new ArrayList<MapComponent>();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				MapComponent mc = new MapComponent();
				mc.setFilename("textures/MapComponents/fold/kivalogatva/road42.png");
				mc.setX(i*99);
				mc.setY(j*99);
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
	
	public static void read() {
		ArrayList<MapComponent> tmcs = new ArrayList<MapComponent>();
		ArrayList<Decoration> tdecs = new ArrayList<Decoration>();
		ArrayList<Decoration> tpots = new ArrayList<Decoration>();
		String[] decTypes = {"kiegeszitok/ko", "kiegeszitok/fu", "kiegeszitok/kut", "kiegeszitok/kerites", 
				"kiegeszitok/kivagott_fa", "epulet/haz/haz", "kiegeszitok/fa"};
		int[] array = {5, 3, 2, 4, 5, 4, 3};
		String filename = "";
		int count = 0;
		//ez ilyen eltolás akar lenni, de nem tudok neki értelmes nevet adni
		int placex = 0;
		int placey = 0;
		
		//Mapkomponensek betöltése
		placex = 1040;
		placey = 50;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 14; j++) {
				MapComponent mc = new MapComponent();
				mc.setId(count);
				mc.setFilename("textures/MapComponents/fold/kivalogatva/road"+count+".png");
				mc.setX(i*55 + placex);
				mc.setY(j*55 + placey);
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
		
		//Dekorációminták betöltése
		placex = 1300;
		placey = 0;
		int i = 0;
		for (String type : decTypes) {
				for (int j = 0; j < array[i]; j++) {
				count++;
				Decoration dec = new Decoration();
				filename = "textures/MapComponents/" + type + "_00"+count+".png";
				readdd(dec,filename, j, i+1, placex, placey, 1f);
				
				tdecs.add(dec);
				}
				count = 0;
				i++;
		}
		Decoration dec = new Decoration();
		filename = "textures/MapComponents/epulet/var/var.png";
		readdd(dec,filename, 1, 1, 1200, 700, 1);
		
		tdecs.add(dec);
		
		//Torony helyeinek betöltése
		Decoration pot = new Decoration();
		filename = "textures/MapComponents/kiegeszitok/fold_001.png";
		readdd(pot,filename, 1, 1, 1000, 800, 1);
		
		tpots.add(pot);


		
		mcs = tmcs;
		decs = tdecs;
		pots = tpots;
		
	}
	
	public static void readdd(Decoration dec, String filename, int i, int j, int placex, int placey, float scale) {
		 dec.setFilename(filename);
		 dec.setX(i*100 + placex);
		 dec.setY(j*100 + placey);
		 dec.setZ(0);
		
		Texture2D mapComp = new Texture2D();
		mapComp.CreateTexture( dec.getFilename());
		mapComp.setPosition( dec.getX(),  dec.getY(),  dec.getZ());
		mapComp.setScale(scale);
		 dec.setTexture(mapComp);
	}
	
	public void export() {
		exportMapComps();
		exportDecs();
		exportPots();
	}
	
	public void exportMapComps() {
		try {
			
		}
		catch(Exception e) {
			
		}
			
	}
	
	public void exportDecs() {
		try {
			
		}
		catch(Exception e) {
			
		}
		
	}
	
	public void exportPots() {
		try {
			
		}
		catch(Exception e) {
			
		}
	}
	

}
