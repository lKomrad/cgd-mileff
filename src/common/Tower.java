package common;

import common.Unit.Animation;
import common.Unit.CurrentAction;
import engine.Texture2D;
import engine.Timer;
import engine.Vector2D;

public class Tower extends Decoration{
	
		private Timer attackTimer = new Timer();
		private Enemy targetUnit;
		private float range = 0;
		private final float attackSpeed = 2;
		private float elapsedtime = 0;
		private Golem golem;
		
		public Tower() {
			attackTimer.init();
			this.range = 250;
		}
		public Tower(Golem golem) {
			attackTimer.init();
			//putGolemOnMap(golem);
		}
		
		
		public Golem getGolem() {
			return golem;
		}



		public void setGolem(Golem golem) {
			this.golem = golem;
		}



		public Vector2D getPosition() {
			Vector2D pos = new Vector2D(getX(), getY());
			return pos;
		}
		
		public float getRange() {
			return range;
		}

		public Enemy getTargetUnit() {
			return targetUnit;
		}

		public void setTargetUnit(Enemy targetUnit) {
			this.targetUnit = targetUnit;
		}

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
		
		public Golem putGolemOnMap(Golem golem) {
			golem.SetPosition(new Vector2D(getX() + 20, getY() + 10));
			golem.setScale(0.2f);
			this.golem = golem;
			return this.golem;
		}
		
		/*public Projectile summonProjectile(Enemy enemy) {
			setTargetUnit(enemy);
			Projectile projectile = new Projectile(getX() + 5, getY() + 5 , targetUnit);
			return projectile;
			
			
		}*/
		
		public Projectile summonProjectile() {
			
			Projectile projectile = new Projectile(getX(), getY() - 40, targetUnit);
			return projectile;
			
			
		}
		
		public boolean isShooting() {
			elapsedtime += attackTimer.getElapsedTime();
			if(elapsedtime >= attackSpeed) {
				elapsedtime = 0;
				return true;
			}
			else {
				return false;
			}
			
		}
		
		
		
		
		

}
