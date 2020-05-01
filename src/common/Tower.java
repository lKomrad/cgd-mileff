package common;

import engine.Texture2D;

public class Tower extends Decoration{
	
		public Texture2D loadTowerTexture() {
			if(getFilename() != null) {
				Texture2D tower = new Texture2D();
				tower.CreateTexture(getFilename());
				tower.setPosition(getX() + 20, getY() -40, getZ());
				return tower;
			}
			return null;
		}

}
