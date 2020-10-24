package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import model.Coordenadas;

public class TcpSingleton extends Thread{
	
	private static TcpSingleton instancia;
	
	//Colocamos privado el constructor para que otras clases no pueden instanciar de el.
	private TcpSingleton() {}
	
	public static TcpSingleton getInstance() {
		
		if(instancia == null) {
			
			instancia = new TcpSingleton();
			instancia.start();
		}
		
		return instancia;
		
	}
	
	private BufferedWriter writer;
	private Socket socket;
	private onMessageListener observador;
	private boolean jug1 = false;
	private Coordenadas cordenada;
	
	//Metodo de suscripcion
	public void setObservador(onMessageListener observador) {
		this.observador = observador;
		
	}
	

	
	@Override 
	public void run() {
		
		try {
			
			//Conexion
			System.out.println("Esperando Conexion....");
			ServerSocket server = new ServerSocket(5000);
			socket = server.accept();
			jug1 = true;
			System.out.println("Conectado"); 
			
			//Declaraciones 
			
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			//Recepcion 
			
			while(true) {
				
				String mensaje = reader.readLine();
				observador.recibirMensaje1(mensaje);

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void enviar(String mensaje) {
		
		new Thread(
				
				()->{
					try {
						writer.write(mensaje+ "\n");
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
				).start();
		
		
		
	}

	public boolean getJug1() {
		return jug1;
	}

	
	
	
	
	

}
