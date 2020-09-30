package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
	
	PImage intro,instrucciones,instru2,instru3,instru4,botonJugar2,
	botonSigui,botonJugar,botonInstru, botonAtras, escenario,conectarimg,
	perfil1,perfil2,botonJugar3,ganadorImg,intentarImg,salirImg,perfilG1,perfilG2,empateImg,homeImg;
	
	int pantalla, next;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PApplet.main("main.Main");

	}
	
	public void settings() {
		
		size(1200,700);
		
	}
	
	public void setup() {
		
		cargarImagenes();
		
		
	}
	
	public void draw() {
		
		switch(pantalla) {
		
		// INICIO
		
		case 0:
			

			image(intro,0,0);
			
			if((mouseX >= 440 && mouseX <= 732) &&(mouseY >= 393 && mouseY <= 455)) {
				image(botonJugar,0,0);
			} if((mouseX >= 440 && mouseX <= 732) &&(mouseY >= 476 && mouseY <= 544)) {
			    image(botonInstru,0,0);
			}
			break;
		
	    //CONEXION
		case 1:
			
			image(conectarimg,0,0);
			image(perfil1,270,286);
			image(perfil2,750,286);
			image(botonJugar3,483,606);
			if((mouseX >= 483 && mouseX <= 688) &&(mouseY >= 606 && mouseY <= 667 )) {
				image(botonJugar2,450,568);
				}
			
			
			zonaVolver();
			
			break;
			
		// JUEGO	
		case 2:
			image(escenario,0,0);
			
			break;
		//GANADOR  	
		case 3:
			
			boolean ganador = true;
			
			if(ganador == true ) {
				
				image(ganadorImg,0,0);
				
				//Se cambia por la imagen del ganador 
				image(perfilG1,449,169);
				image(perfilG2,449,169);
				
			}
			
			if(ganador != true) {
				
				image(empateImg,0,0);
				
				
			}
			
			
			
			
			if((mouseX >= 1090 && mouseX <= 1153) &&(mouseY >= 20 && mouseY <= 92 )) { 
			image(homeImg,1090,20);
			}
			if((mouseX >= 320 && mouseX <= 579) &&(mouseY >= 580 && mouseY <= 642 )) { 
				image(intentarImg,320,580);
			}
			
			if((mouseX >= 610 && mouseX <= 871) &&(mouseY >= 580 && mouseY <= 642 )) { 
				image(salirImg,610,580);
			}
	    	   
	    	   break;
	    	  	
	    // INSTRUCCIONES		
			
		case 4:	
			
			background(255);
			
			switch(next) {
			
			case 0:
				
				image(instrucciones,0,0);
				
				if((mouseX >= 959 && mouseX <= 1130) &&(mouseY >= 611 && mouseY <= 670 )) {   
					image(botonSigui,920,573);
				}
				zonaVolver();
				
				
				break;
				
			case 1:
				
				image(instru2,0,0);
				if((mouseX >= 959 && mouseX <= 1130) &&(mouseY >= 611 && mouseY <= 670 )) {
					image(botonSigui,920,573);
					}
				zonaVolver();

			    
				break;
				
			case 2:
				image(instru3,0,0);
				if((mouseX >= 959 && mouseX <= 1130) &&(mouseY >= 611 && mouseY <= 670 )) {
					image(botonSigui,920,573);
					}
				zonaVolver();


				
				break;
				
			case 3:
				
				image(instru4,0,0);
				if((mouseX >= 959 && mouseX <= 1130) &&(mouseY >= 611 && mouseY <= 670 )) {
					image(botonJugar2,920,573);
					}
				zonaVolver();

				
				break;
				
			}
			
			break;
			
		}
		
		fill(0);
		
		text("X:"+mouseX+"Y:"+mouseY,mouseX,mouseY);
		
		
		
	}
	
	public void mousePressed() {
		
		switch(pantalla) {
		
		//ZONAS SENSIBLES INICIO
		
		case 0:
			
			if((mouseX >= 440 && mouseX <= 732) &&(mouseY >= 393 && mouseY <= 455)) {
				pantalla = 1;
			}
			
			if((mouseX >= 440 && mouseX <= 732) &&(mouseY >= 476 && mouseY <= 544)) {
				pantalla = 4;
			}
			
			break;
			
			//ZONAS SENSIBLES CONEXION
		case 1:
			
			if((mouseX >= 31 && mouseX <= 101) &&(mouseY >= 19 && mouseY <= 68 )) {
				pantalla = 0;

			}
			
			if((mouseX >= 483 && mouseX <= 688) &&(mouseY >= 606 && mouseY <= 667 )) {
	    		   
	    		   pantalla = 2;
	    	}
				
			
			
			
			break;
			
			//ZONAS SENSIBLES JUEGO
       case 2:
    	   
    	   pantalla = 3;
    	   
    	   break;
    	   
    	 //ZONAS SENSIBLE GANADOR		   
       case 3:
    	   
    	   //Estas zonas tienen que reinicar el juego 
    	   
    	   if((mouseX >= 1090 && mouseX <= 1153) &&(mouseY >= 20 && mouseY <= 92 )) { 
   			pantalla =0;
   			}
   			if((mouseX >= 320 && mouseX <= 579) &&(mouseY >= 580 && mouseY <= 642 )) { 
   				pantalla =1;
   			}
   			
   			if((mouseX >= 610 && mouseX <= 871) &&(mouseY >= 580 && mouseY <= 642 )) { 
   				exit();
   			}
   	    	  
    	   
    	   break;
    	   
    
			
	    //ZONAS SENSIBLES INSTRUCCIONES	
		case 4:
              
			switch(next) {
			
			case 0:
				
				if((mouseX >= 959 && mouseX <= 1130) &&(mouseY >= 611 && mouseY <= 670 )) {
					next = 1;
				}
				if((mouseX >= 31 && mouseX <= 101) &&(mouseY >= 19 && mouseY <= 68 )) {
					pantalla = 0;

				}
				
				
				break;
				
			case 1:
				
				if((mouseX >= 959 && mouseX <= 1130) &&(mouseY >= 611 && mouseY <= 670 )) {
					next = 2;
				}
				if((mouseX >= 31 && mouseX <= 101) &&(mouseY >= 19 && mouseY <= 68 )) {
					next = 0;

				}
				
			
				break;
				
			case 2:
				if((mouseX >= 959 && mouseX <= 1130) &&(mouseY >= 611 && mouseY <= 670 )) {
					next = 3;
				} if((mouseX >= 31 && mouseX <= 101) &&(mouseY >= 19 && mouseY <= 68 )) {
					next = 1;

				}
				
				break;
				
			case 3:
				
				if((mouseX >= 959 && mouseX <= 1130) &&(mouseY >= 611 && mouseY <= 670 )) {
					pantalla = 1;
				} if((mouseX >= 31 && mouseX <= 101) &&(mouseY >= 19 && mouseY <= 68 )) {
					next = 2;

				}
								
				break;
				
			}
			
			break;
		
		
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
		conectarimg =loadImage("img/conexion.jpg");
		perfil1 = loadImage("img/perfilpng.png");
		perfil2 = loadImage("img/perfil2.png");
		botonJugar3 = loadImage("img/botonJugar2.png");
		ganadorImg = loadImage("img/PantallaGanador.jpg");
		intentarImg = loadImage("img/tryAgainButton.png");
		salirImg = loadImage("img/exitButton.png");
		perfilG1 = loadImage("img/perfilGrande.png");
		perfilG2 = loadImage("img/perfilGrande2.png");
		empateImg = loadImage("img/empate.jpg");
		homeImg= loadImage("img/iconoHome.png");


		



		
	}
	
	
	public void zonaVolver() {
		
		if((mouseX >= 31 && mouseX <= 101) &&(mouseY >= 19 && mouseY <= 68 )) {
			image(botonAtras,0,0);

		}
		
		
	}

}
