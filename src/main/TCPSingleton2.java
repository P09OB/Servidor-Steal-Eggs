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

public class TCPSingleton2 extends Thread{
	
	private static TCPSingleton2 instancia;
	
	//Colocamos privado el constructor para que otras clases no pueden instanciar de el.
	private TCPSingleton2() {}
	
	public static TCPSingleton2 getInstance() {
		
		if(instancia == null) {
			
			instancia = new TCPSingleton2();
			instancia.start();
		}
		
		return instancia;
		
	}
	
	private BufferedWriter writer;
	private Socket socket;
	private onMessageListener observador;
	private boolean jug2 = false;
	
	//Metodo de suscripcion
	public void setObservador(onMessageListener observador) {
		this.observador = observador;
		
	}
	

	
	@Override 
	public void run() {
		
		try {
			
			//Conexion
		
			System.out.println("Esperando Conexion2....");
			ServerSocket server = new ServerSocket(4000);
			socket = server.accept();
			jug2=true;
			System.out.println("Conectado2"); 
			
			//Declaraciones 
			
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			
			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			//Recepcion 
			
			while(true) {
				
				String mensaje = reader.readLine();
				observador.recibirMensaje2(mensaje);

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

	public boolean getJug2() {
		return jug2;
	}


	
	
	
	

}
