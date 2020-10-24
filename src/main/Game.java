package main;

import threads.EggThread;
import threads.PlayerThread;

public class Game {
	
	private Player player1;
	private Player player2;
	private boolean gameOver;
	private PlayerThread thread1;
	private PlayerThread thread2;
	private Egg e;
	private EggThread eggThread;
	
	public Game() {
		e = new Egg(500, 10);
		eggThread = new EggThread(e);
		eggThread.start();
		player1 = new Player(50, 450,e);
		player2 = new Player(870, 450,e);
		thread1 = new PlayerThread(player1);
		thread1.start();
		thread2 = new PlayerThread(player2);
		thread2.start();
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public Egg getE() {
		return e;
	}

	public void setE(Egg e) {
		this.e = e;
	}
	
	

}
