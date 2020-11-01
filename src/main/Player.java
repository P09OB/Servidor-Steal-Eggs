package main;

public class Player {
	
	public final static int SPEED = 10;
	public final static int GRAVITY = 8;
	public final static char RIGHT = 'r';
	public final static char LEFT = 'l';
	public final static int HEIGHT_LIMIT = 200;
	public final static int JUMP_SPEED = 10;
	
	private int posX;
	private int posY;
	private boolean jumping;
	private boolean alive;
	private char dir;
	private boolean moving;
	private Egg e;
	private boolean holding;
	private boolean steal;
	private int score;
	private int numPlayer;
	
	public Player(int posX, int posY, Egg e, int numPlayer) {
		this.posX = posX;
		this.posY = posY;
		this.alive = true;
		this.setDir(RIGHT);
		this.e = e;
		this.numPlayer = numPlayer;
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

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public boolean checkEgg() {
		if((e.getPosX()>=posX && e.getPosX()<=posX+80)&&(e.getPosY()>=posY&&e.getPosY()<=posY+150)) {
			return true;
		}
		return false;
	}
	
	public void getEgg() {
		e.setP(this);
		e.setMoving(true);
		holding=true;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	
	public void jump() {
		posY-=JUMP_SPEED;

	}
	
	public void gravity() {
	 posY+=GRAVITY;
	}

	public char getDir() {
		return dir;
	}

	public void setDir(char dir) {
		this.dir = dir;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public Egg getE() {
		return e;
	}

	public void setE(Egg e) {
		this.e = e;
	}

	public boolean isHolding() {
		return holding;
	}

	public void setHolding(boolean holding) {
		this.holding = holding;
	}

	public boolean isSteal() {
		return steal;
	}

	public void setSteal(boolean steal) {
		this.steal = steal;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNumPlayer() {
		return numPlayer;
	}

	public void setNumPlayer(int numPlayer) {
		this.numPlayer = numPlayer;
	}
	
	

}
