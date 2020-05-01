package common;

import engine.Texture2D;

public class Tower extends Decoration{
	
		public Texture2D loadTowerTexture() {
			if(getFilename() == "textures/MapComponents/epulet/bastya/bastya_001.png" || 
					getFilename() == "textures/MapComponents/epulet/bastya/bastya_002.png") {
				Texture2D tower = new Texture2D();
				tower.CreateTexture(getFilename());
				tower.setPosition(getX() + 20, getY() -42, getZ());
				return tower;
			}
			else if(getFilename() == "textures/MapComponents/epulet/bastya/bastya_003.png" || 
					getFilename() == "textures/MapComponents/epulet/bastya/bastya_004.png") {
				Texture2D tower = new Texture2D();
				tower.CreateTexture(getFilename());
				tower.setPosition(getX() + 2, getY() -45, getZ());
				return tower;
			}
			else if(getFilename() == "textures/MapComponents/epulet/bastya/bastya_005.png" || 
					getFilename() == "textures/MapComponents/epulet/bastya/bastya_006.png") {
				Texture2D tower = new Texture2D();
				tower.CreateTexture(getFilename());
				tower.setPosition(getX() + 17, getY() -61, getZ());
				return tower;
			}
			return null;
		}
}
