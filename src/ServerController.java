import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerController implements Runnable {

	DBController db = null;
	Socket socket = null;
	ObjectOutputStream objOut = null;
	ObjectInputStream objIn = null;

	public ServerController (DBController db, Socket socket) {
		this.socket = socket;
		this.db = db;
		try {
			this.objOut = new ObjectOutputStream(socket.getOutputStream());
			this.objIn = new ObjectInputStream(socket.getInputStream());  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
	
		while (true) {
            try {
                objIn = (Object)objIn.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

	
	
}
