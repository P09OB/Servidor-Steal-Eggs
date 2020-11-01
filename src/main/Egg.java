package main;

public class Egg {
	
	public final static int SPEED = 10;
	public final static int GRAVITY = 8;
	
	private int posX;
	private int posY;
	private boolean alive;
	private boolean moving;
	private Player p;
	private boolean flying;
	private char dir;
	private int limit;
	
	public Egg(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.alive = true;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void move() {
		if(dir == Player.RIGHT) {
			posX+=SPEED;
		}
		else {
			posX-=SPEED;
		}
	}
	
	public boolean isInBasket() {
		if(p.getNumPlayer() == 1) {
			if((posX>=950 && posX<=1000)&&(posY>=150 && posY<=250)) {
				return true;
			}
		}
		else {
			if((posX>=0 && posX<=50)&&(posY>=150 && posY<=250)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void respawn() {
		flying=false;
		moving=false;
		posX=500;
		posY=10;
	}
	
	public void gravity() {
	 posY+=GRAVITY;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}

	public char getDir() {
		return dir;
	}

	public void setDir(char dir) {
		this.dir = dir;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit() {
		this.limit = posX;
	}
	
	

}
