package common;

import engine.Texture2D;

public class Tower extends Decoration{
	
		public Texture2D loadTowerTexture() {
			if(getFilename() != null) {
				Texture2D tower = new Texture2D();
				tower.CreateTexture(getFilename());
				tower.setPosition(getX(), getY(), getZ());
				return tower;
			}
			return null;
		}

}
