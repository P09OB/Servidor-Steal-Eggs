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
	private boolean started;
	
	public Game() {
		e = new Egg(500, 10);
		eggThread = new EggThread(e);
		player1 = new Player(50, 450,e,1);
		player2 = new Player(870, 450,e,2);
		thread1 = new PlayerThread(player1);
		thread2 = new PlayerThread(player2);
	}
	
	public void startThreads() {
		if(!started) {
			eggThread.start();
			thread1.start();
			thread2.start();
			started=true;
		}
		
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
