package common;

import engine.Texture2D;

public class Soldier {
	private Texture2D texture;
	private float speedX;
	private float speedY;
	private int health;
	private int damage;
	private float detectionRange;
	
	public Texture2D getTexture() {
		return texture;
	}
	public void setTexture(Texture2D texture) {
		this.texture = texture;
	}
	public float getSpeedX() {
		return speedX;
	}
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}
	public float getSpeedY() {
		return speedY;
	}
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public float getDetectionRange() {
		return detectionRange;
	}
	public void setDetectionRange(float detectionRange) {
		this.detectionRange = detectionRange;
	}
}
