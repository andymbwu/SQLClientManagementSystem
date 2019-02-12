package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class responsible for running the server side, and accepting the socket connection from client. Also initializes new
 * ServerController method that receives the inputs from the socket, and executes the thread pool
 */
public class ServerCommunication {

	private Socket aSocket;
	private ServerSocket serverSocket = null;
	private DBController db;

	public static final int port = 9809;
	
	/**
	 * Thread Pool to Handle Communication.
	 */
	private ExecutorService pool;

	/**
	 * Creates new database controller and executes server socket that matches port number from client
	 */
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

	/**
	 * Starts the server and accepts the socket connection from the client
	 */
	public void runServer() {
		try {
			while (true) {
				aSocket = serverSocket.accept();
				System.out.println("Client has been connected");

				ServerController sc = new ServerController(this.db, this.aSocket);
				pool.execute(sc);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			// Stop accepting new games and finish any active ones, then shutdown the threadpool.
			pool.shutdown();
			try {
				aSocket.close();
			} catch (IOException e1) {
				System.out.println("Client disconnected.");

			}
			
		}
	}

	/**
	 * Main method that starts the server
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServerCommunication myServer = new ServerCommunication();
		myServer.runServer();
	}

}



