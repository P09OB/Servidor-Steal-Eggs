package model;

public class GameState {
	
	int puntaje1;
	int puntaje2;
	int tiempo;
	String id;
	
	public GameState() {
		
	}
	
    public GameState(int puntaje1,int puntaje2, int tiempo, String id) {
    	
    	this.puntaje1=puntaje1;
    	this.puntaje2=puntaje2;
    	this.tiempo = tiempo;
    	this.id = id;

		
	}
	
	
	
	public int getPuntaje2() {
		return puntaje2;
	}

	public void setPuntaje2(int puntaje2) {
		this.puntaje2 = puntaje2;
	}

	public int getPuntaje1() {
		return puntaje1;
	}
	public void setPuntaje1(int puntaje1) {
		this.puntaje1 = puntaje1;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	

}