package common;

public class PlaceOfTower extends Decoration{
	
	private Tower tower;

	
	
	


	public Tower getTower() {
		return tower;
	}






	public void setTower(Tower tower) {
		this.tower = tower;
	}






	public void placeTower(String filename) {
		Tower tower = new Tower();
		//ezeket majd lehet, h inkább átszervezem a konstruktorba, egyelõre csak mûködõképessé próbálom tenni
		tower.setX(getX());
		tower.setY(getY());
		tower.setZ(0);
		tower.setFilename(filename);
		tower.loadTowerTexture();
		this.tower = tower;
	}
	
	public void placeTower(String filename, Golem golem) {
		Tower tower = new Tower(golem);
		//ezeket majd lehet, h inkább átszervezem a konstruktorba, egyelõre csak mûködõképessé próbálom tenni
		tower.setX(getX());
		tower.setY(getY());
		tower.setZ(0);
		tower.setFilename(filename);
		tower.loadTowerTexture();
		tower.putGolemOnMap(golem);
		this.tower = tower;
	}


	public boolean hasTower() {
		if(getTower() != null) {
			return true;
		}
		else {
			return false;
		}
	}
	

}
