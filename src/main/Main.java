package main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.UUID;

import com.google.gson.Gson;

import model.Coordenadas;
import model.GameState;
import model.Restart;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements onMessageListener {

	private Game game;

	PImage intro, instrucciones, instru2, instru3, instru4, botonJugar2, botonSigui, botonJugar, botonInstru,
			botonAtras, escenario, conectarimg, perfil1, perfil2, botonJugar3, ganadorImg, intentarImg, salirImg,
			perfilG1, perfilG2, empateImg, homeImg, j1left, j1right, j1Jumping, j2left, j2right, j2jumping, egg,
			huevoArbol;

	private TcpSingleton tcp;
	private TCPSingleton2 tcp2;
	private InetAddress inetAddress;
	private Coordenadas coordenada;
	private int pantalla, next, ganador;

	private String ip;
	private String codigo;

	private int posx, posX = 870;

	int startTime;
	int time = 60;

	private boolean dirLeft = false, dirRight = true, dirLeft2 = true, dirRight2 = false, dir2 = false, jump = false,
			jump2 = false, steal = false, steal2 = false, finish = false, terminar = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PApplet.main("main.Main");

	}

	public void settings() {

		size(1200, 700);

	}

	public void setup() {
		pantalla = 1;
		

		// IMAGENES
		cargarImagenes();

		game = new Game();
		
		// TCP

		tcp = TcpSingleton.getInstance();
		tcp.setObservador(this);

		tcp2 = TCPSingleton2.getInstance();
		tcp2.setObservador(this);

		// OBTENER EL IP

		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ip = inetAddress.getHostAddress();

		System.out.println(ip);

		String[] parts = ip.split("\\.");

		codigo = parts[3];

		// TIEMPO EMPIEZA

		if (pantalla == 2) {
			startTime = millis();
		}

	}

	public void draw() {

		switch (pantalla) {

		// INICIO

		case 0:

			image(intro, 0, 0);

			if ((mouseX >= 440 && mouseX <= 732) && (mouseY >= 393 && mouseY <= 455)) {
				image(botonJugar, 0, 0);
			}
			if ((mouseX >= 440 && mouseX <= 732) && (mouseY >= 476 && mouseY <= 544)) {
				image(botonInstru, 0, 0);
			}
			break;

		// CONEXION
		case 1:

			image(conectarimg, 0, 0);
			textSize(20);
			fill(255);
			
			// IMAGENES CONEXION
			if (tcp.getJug1() == true) {

				image(perfil1, 270, 286);
				text("Conectado", 269, 542);
			} else {
				text("Esperando...", 269, 542);

			}

			if (tcp2.getJug2() == true) {

				image(perfil2, 750, 286);
				text("Conectado", 738, 542);
			} else {
				text("Esperando...", 738, 542);
			}

			image(botonJugar3, 483, 606);
			if ((mouseX >= 483 && mouseX <= 688) && (mouseY >= 606 && mouseY <= 667)) {
				image(botonJugar2, 450, 568);
			}

			zonaVolver();

			text("Ingresa este código en tu dispositivo móvil", 380, 80);
			fill(0);
			textSize(30);
			text("" + codigo, 560, 190);

			break;

		// JUEGO
		case 2:
			game.startThreads();
			image(escenario, 0, 0);

			// TIEMPO

			int elapsedtime = millis() - startTime;
			int interval = 1000;

			boolean timeshow;

			if (elapsedtime > interval) {
				timeshow = true;
			} else {
				timeshow = false;
			}

			if (timeshow == true) {
				if (time > 1) {
					time--;
					startTime = millis();

				} else {
					pantalla = 3;
					finish = true;
				}
			}

			fill(0);
			text("" + time, 288, 41);

			// ENVIA ESTADO DEL JUEGO

			Gson gson = new Gson();
			String id = UUID.randomUUID().toString();
			GameState gameState = new GameState(game.getPlayer1().getScore(), game.getPlayer2().getScore(), time,
					finish, id);
			String json = gson.toJson(gameState);

			tcp.enviar(json);
			tcp2.enviar(json);

			// PINTAR ELEMENTOS

			drawPlayers();
			drawEgg();
			drawScores();
			
			// PINTAR HUEVOS OBTENIDOS

			int score1 = game.getPlayer1().getScore();
			int score2 = game.getPlayer2().getScore();

			for (int i = 0; i < score1; i++) {

				int x = 57+ i * 10;
				
				image(huevoArbol, x, 234);

			}

			for (int i = 0; i < score2; i++) {

				int x = 1042 + i * 10;
				image(huevoArbol, x, 234);

			}

			break;

		// PANTALLA GANADOR
		case 3:

			drawScores();

			image(ganadorImg, 0, 0);
			
			fill(255);
			textSize(40);
			
			
			// IMAGEN DEL GANADOR

			switch (ganador) {

			case 0:

				image(empateImg, 0, 0);

				break;

			case 1:
				text("GANADOR JUGADOR 1", 368, 80);

				image(perfilG1, 449, 169);

				break;

			case 2:
				
				text("GANADOR JUGADOR 2", 368, 80);

				image(perfilG2, 449, 169);

				break;

			}

			if ((mouseX >= 472 && mouseX <= 734) && (mouseY >= 580 && mouseY <= 642)) {
				image(salirImg, 472, 580);
			}

			break;

		// INSTRUCCIONES

		case 4:

			background(255);

			switch (next) {

			case 0:

				image(instrucciones, 0, 0);

				if ((mouseX >= 959 && mouseX <= 1130) && (mouseY >= 611 && mouseY <= 670)) {
					image(botonSigui, 920, 573);
				}
				zonaVolver();

				break;

			case 1:

				image(instru2, 0, 0);
				if ((mouseX >= 959 && mouseX <= 1130) && (mouseY >= 611 && mouseY <= 670)) {
					image(botonSigui, 920, 573);
				}
				zonaVolver();

				break;

			case 2:
				image(instru3, 0, 0);
				if ((mouseX >= 959 && mouseX <= 1130) && (mouseY >= 611 && mouseY <= 670)) {
					image(botonSigui, 920, 573);
				}
				zonaVolver();

				break;

			case 3:

				image(instru4, 0, 0);
				if ((mouseX >= 959 && mouseX <= 1130) && (mouseY >= 611 && mouseY <= 670)) {
					image(botonJugar2, 920, 573);
				}
				zonaVolver();

				break;

			}

			break;

		}

		fill(0);

		//text("X:" + mouseX + "Y:" + mouseY, mouseX, mouseY);

	}

	public void mousePressed() {

		switch (pantalla) {

		// ZONAS SENSIBLES INICIO

		case 0:

			if ((mouseX >= 440 && mouseX <= 732) && (mouseY >= 393 && mouseY <= 455)) {
				pantalla = 1;
			}

			if ((mouseX >= 440 && mouseX <= 732) && (mouseY >= 476 && mouseY <= 544)) {
				pantalla = 4;
			}

			break;

		// ZONAS SENSIBLES CONEXION
		case 1:

			if ((mouseX >= 31 && mouseX <= 101) && (mouseY >= 19 && mouseY <= 68)) {
				pantalla = 0;

			}

			if (tcp.getJug1() == true && tcp2.getJug2() == true) {

				if ((mouseX >= 483 && mouseX <= 688) && (mouseY >= 606 && mouseY <= 667)) {

					pantalla = 2;
					tcp.enviar("entre");
					tcp2.enviar("entre");
				}

			} else {

				tcp.enviar("falta");
				tcp2.enviar("falta");

			}

			break;

		// ZONAS SENSIBLES JUEGO
		case 2:

			pantalla = 3;

			break;

		// ZONAS SENSIBLE GANADOR
		case 3:

			// SALIR DEL JUEGO
			

			if ((mouseX >= 472 && mouseX <= 734) && (mouseY >= 580 && mouseY <= 642)) {
				exit();
			}

			break;

		// ZONAS SENSIBLES INSTRUCCIONES
		case 4:

			switch (next) {

			case 0:

				if ((mouseX >= 959 && mouseX <= 1130) && (mouseY >= 611 && mouseY <= 670)) {
					next = 1;
				}
				if ((mouseX >= 31 && mouseX <= 101) && (mouseY >= 19 && mouseY <= 68)) {
					pantalla = 0;

				}

				break;

			case 1:

				if ((mouseX >= 959 && mouseX <= 1130) && (mouseY >= 611 && mouseY <= 670)) {
					next = 2;
				}
				if ((mouseX >= 31 && mouseX <= 101) && (mouseY >= 19 && mouseY <= 68)) {
					next = 0;

				}

				break;

			case 2:
				if ((mouseX >= 959 && mouseX <= 1130) && (mouseY >= 611 && mouseY <= 670)) {
					next = 3;
				}
				if ((mouseX >= 31 && mouseX <= 101) && (mouseY >= 19 && mouseY <= 68)) {
					next = 1;

				}

				break;

			case 3:

				if ((mouseX >= 959 && mouseX <= 1130) && (mouseY >= 611 && mouseY <= 670)) {
					pantalla = 1;
				}
				if ((mouseX >= 31 && mouseX <= 101) && (mouseY >= 19 && mouseY <= 68)) {
					next = 2;

				}

				break;

			}

			break;

		}

	}

	public void zonaVolver() {

		if ((mouseX >= 31 && mouseX <= 101) && (mouseY >= 19 && mouseY <= 68)) {
			image(botonAtras, 0, 0);

		}

	}


	public void drawPlayers() {

		Player p1 = game.getPlayer1();
		Player p2 = game.getPlayer2();

		p1.setPosX(posx);

		p2.setPosX(posX);

		// JUGADOR 1

		if (jump == true) {

			dirRight = false;
			dirLeft = false;

			game.getPlayer1().setJumping(true);
			image(j1Jumping, p1.getPosX(), p1.getPosY());

			dirLeft = true;

		}

		if (dirRight == true) {
			game.getPlayer1().setDir(Player.RIGHT);
			image(j1right, p1.getPosX(), p1.getPosY());

		}
		if (dirLeft == true) {
			game.getPlayer1().setDir(Player.LEFT);
			image(j1left, p1.getPosX(), p1.getPosY());

		}

		if (steal == true) {

			game.getPlayer1().setSteal(true);

		}

		// JUGADOR 2

		if (jump2 == true) {

			dirRight2 = false;
			dirLeft2 = false;

			game.getPlayer2().setJumping(true);
			image(j2jumping, p2.getPosX(), p2.getPosY());

			dirLeft2 = true;

		}

		if (dirRight2 == true) {

			dirLeft2 = false;
			game.getPlayer2().setDir(Player.RIGHT);
			image(j2right, p2.getPosX(), p2.getPosY());

		}
		if (dirLeft2 == true) {

			dirRight2 = false;
			game.getPlayer2().setDir(Player.LEFT);
			image(j2left, p2.getPosX(), p2.getPosY());

		}

		if (steal2 == true) {

			game.getPlayer2().setSteal(true);

			steal2 = false;
		}

	}

	public void drawScores() {

		textSize(20);
		fill(0);
		text(game.getPlayer1().getScore(), 959, 41);
		text(game.getPlayer2().getScore(), 1081, 41);

		if (pantalla == 3) {

			text(game.getPlayer1().getScore(), 959, 41);
			text(game.getPlayer2().getScore(), 1081, 41);

			if (game.getPlayer1().getScore() > game.getPlayer2().getScore()) {

				ganador = 1;

			}
			if (game.getPlayer1().getScore() == game.getPlayer2().getScore()) {

				ganador = 0;

			}
			if (game.getPlayer1().getScore() < game.getPlayer2().getScore()) {

				ganador = 2;

			}

		}

	}

	public void drawEgg() {
		fill(0);
		Egg e = game.getE();
		image(egg, e.getPosX(), e.getPosY());
	}

	public void resetGame() {
		pantalla = 2;
		time = 60;
		posX = 870;
		posx = 0;
		finish = false;
		game.getPlayer1().setScore(0);
		game.getPlayer2().setScore(0);
		game.getPlayer1().setSteal(false);
		game.getPlayer2().setSteal(false);
		game.getPlayer1().setJumping(false);
		game.getPlayer2().setJumping(false);


	}

	// MENSAJES JUGADOR 1

	@Override
	public void recibirMensaje1(String mensaje) {
		// TODO Auto-generated method stub

		if (pantalla == 2) {

			Gson gson = new Gson();
			Coordenadas coordenada = gson.fromJson(mensaje, Coordenadas.class);

			posx = coordenada.getX();
			dirLeft = coordenada.isDirLeft();
			dirRight = coordenada.isDirRight();
			jump = coordenada.isJump();
			steal = coordenada.isSteal();

		}

		if (pantalla == 3) {
			Gson gson = new Gson();
			Restart restart = gson.fromJson(mensaje, Restart.class);

			terminar = restart.isFinish();

			if (terminar == true) {
				resetGame();
			}
		}
	}

	// MENSAJES JUGADOR 2

	@Override
	public void recibirMensaje2(String mensaje) {
		// TODO Auto-generated method stub

		if (pantalla == 2) {

			Gson gson = new Gson();
			Coordenadas coordenada2 = gson.fromJson(mensaje, Coordenadas.class);

			posX = coordenada2.getX();
			dirLeft2 = coordenada2.isDirLeft();
			dirRight2 = coordenada2.isDirRight();
			jump2 = coordenada2.isJump();
			steal2 = coordenada2.isSteal();

		}

		if (pantalla == 3) {

			Gson gson = new Gson();
			Restart restart = gson.fromJson(mensaje, Restart.class);

			terminar = restart.isFinish();

			if (terminar == true) {
				resetGame();
			}
		}

	}

	public void cargarImagenes() {

		intro = loadImage("img/inicio.jpg");
		botonJugar = loadImage("img/botonJugar.png");
		botonInstru = loadImage("img/botonInstru.png");
		instrucciones = loadImage("img/instrucciones.jpg");
		instru2 = loadImage("img/instru2.jpg");
		instru3 = loadImage("img/instru3.jpg");
		instru4 = loadImage("img/instru4.jpg");
		botonSigui = loadImage("img/botonSiguiente.png");
		botonJugar2 = loadImage("img/botonJugar1.png");
		botonAtras = loadImage("img/arrow.png");
		escenario = loadImage("img/Escenario.jpg");
		conectarimg = loadImage("img/conexion.jpg");
		perfil1 = loadImage("img/perfilpng.png");
		perfil2 = loadImage("img/perfil2.png");
		botonJugar3 = loadImage("img/botonJugar2.png");
		ganadorImg = loadImage("img/PantallaFinal.png");

		intentarImg = loadImage("img/tryAgainButton.png");
		salirImg = loadImage("img/exitButton.png");
		perfilG1 = loadImage("img/perfilGrande.png");
		perfilG2 = loadImage("img/perfilGrande2.png");
		empateImg = loadImage("img/empate.png");
		homeImg = loadImage("img/iconoHome.png");
		j1left = loadImage("img/J1Left.png");
		j2left = loadImage("img/J2Left.png");
		j1right = loadImage("img/J1Right.png");
		j2right = loadImage("img/J2Right.png");
		j1Jumping = loadImage("img/J1Jumping.png");
		j2jumping = loadImage("img/J2Jumping.png");
		egg = loadImage("img/egg.png");
		huevoArbol = loadImage("img/huevoarbol.png");
	}

}
