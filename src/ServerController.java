import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerController implements Runnable {

	private DBController db = null;
	private Socket aSocket = null;
	private ObjectOutputStream objOut = null;
	private ObjectInputStream objIn = null;

	private UserWrapper userInput;

	public ServerController (DBController db, Socket socket) {
		this.aSocket = socket;
		this.db = db;
		try {
			this.objOut = new ObjectOutputStream(socket.getOutputStream());
			this.objIn = new ObjectInputStream(socket.getInputStream());  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {

        while (true) {
            try {
                userInput = (UserWrapper)objIn.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }


	
	
}
