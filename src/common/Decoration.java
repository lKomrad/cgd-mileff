package common;

import java.util.ArrayList;

import engine.Texture2D;

public class Decoration {
	
	private int x;
	private int y;
	private int z;
	private String filename;
	private Texture2D texture;
	
	public Decoration() {
		
	}
	
	public Decoration(int x, int y, int z, String filename) {
		super();
		this.x = x;
		this.y = y;
		this.filename = filename;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public Texture2D getTexture() {
		return texture;
	}

	public void setTexture(Texture2D texture) {
		this.texture = texture;
	}

	public Texture2D loadTexture() {
		Texture2D dec = new Texture2D();
		dec.CreateTexture(getFilename());
		dec.setPosition(getX(), getY(), getZ());
		return dec;
	}

	public void loadTextures(Decoration dec) {
		 setX(dec.getX());
		 setY(dec.getY());
		 setZ(0);
		 setFilename(dec.getFilename());
		 Texture2D text = new Texture2D();
		 text.CreateTexture(getFilename());
		 text.setPosition(getX(), getY(), getZ());
		 setTexture(text);
	}
	
}
