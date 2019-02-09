

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerCommunication {
	
//	Socket xSocket;
//	Socket oSocket;
	Socket aSocket;
	ServerSocket serverSocket;
	DBController db;

//	PrintWriter xSocketOut;
//	PrintWriter oSocketOut;
//	BufferedReader xSocketIn;
//	BufferedReader oSocketIn;
	
	/**
	 * Thread Pool to Handle Communication.
	 */
	private ExecutorService pool;

	public ServerCommunication() { // throws IOException {

		this.db = new DBController();
		
		try {
			serverSocket = new ServerSocket(9899);
			this.pool = Executors.newFixedThreadPool(5);
			
		} catch (IOException e) {
			System.out.println("Create new socket error");
			System.out.println(e.getMessage());
		}
		System.out.println("TicTacToe.Server is running");
	}

	public void runServer() {
		try {

			while (true) {
				aSocket = serverSocket.accept();
				System.out.println("Client has been connected");
				
//				oSocket = serverSocket.accept();
//				System.out.println("TicTacToe.Player O has been connected");

//				xSocketIn = new BufferedReader((new InputStreamReader(xSocket.getInputStream())));
//				oSocketIn = new BufferedReader((new InputStreamReader(oSocket.getInputStream())));
//				xSocketOut = new PrintWriter(xSocket.getOutputStream(), true);
//				oSocketOut = new PrintWriter(oSocket.getOutputStream(), true);
				
				//TicTacToe.Game needs to have both sockets as input arguments to it's constructor
				ServerController sc = new ServerController(this.db, this.aSocket);
				
				pool.execute(sc);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			// Stop accepting new games and finish any active ones, then shutdown the threadpool.
			pool.shutdown();
			try {
				aSocket.close();
//				oSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	public static void main(String[] args) throws IOException {
		ServerCommunication myServer = new ServerCommunication();
		myServer.runServer();
	}

}



