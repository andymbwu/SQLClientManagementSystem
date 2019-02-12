package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerCommunication {

	private Socket aSocket;
	private ServerSocket serverSocket = null;
	private DBController db;

	private ObjectOutputStream objOut = null;
	private ObjectInputStream objIn = null;

	public static final int port = 9809;
	
	/**
	 * Thread Pool to Handle Communication.
	 */
	private ExecutorService pool;

	public ServerCommunication() { // throws IOException {

		this.db = new DBController();
		
		try {
			serverSocket = new ServerSocket(port);
			this.pool = Executors.newFixedThreadPool(5);
			
		} catch (IOException e) {
			System.out.println("Create new socket error");
			System.out.println(e.getMessage());
		}
		System.out.println("Server is running");
	}

	public void runServer() {
		try {
			while (true) {
				aSocket = serverSocket.accept();
				System.out.println("Client has been connected");

				ServerController sc = new ServerController(this.db, this.aSocket);
//				objOut = new ObjectOutputStream(aSocket.getOutputStream());
//				objIn = new ObjectInputStream(aSocket.getInputStream());

				pool.execute(sc);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			// Stop accepting new games and finish any active ones, then shutdown the threadpool.
			pool.shutdown();
			try {
				aSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		ServerCommunication myServer = new ServerCommunication();
		myServer.runServer();
	}

}



