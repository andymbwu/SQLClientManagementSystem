import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerController implements Runnable, Constants {

	private DBController db = null;
	private Socket aSocket = null;
	private ObjectOutputStream objOut = null;
	private ObjectInputStream objIn = null;

	private UserWrapper userInput;
	private UserWrapper userOutput;

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
            String query = userInput.getQuery();
            int action = userInput.getAction();
            UserModel user = userInput.getUserList().get(0);
            switch(action){
                case SEARCH_LAST_NAME:
                	if(db.searchUserLastName(user.getLastName()) != null) {
                		userOutput = new UserWrapper();
                		userOutput.setUserList(db.searchUserLastName(user.getLastName()));
                		objOut.writeObject(userOutput);
                	} else {
                		userOutput.setAction(SEARCH_FAIL);
                		userOutput.setUserList(null);
                		objOut.writeObject(userOutput);
                	}

//                	break;
                case SEARCH_USER_ID:
                	if(db.searchUserLastName(user.getLastName()) != null) {
                		userOutput = new UserWrapper();
                		userOutput.setUserList(db.searchUserID(user.getID()));
                		objOut.writeObject(userOutput);
                	} else {
                		userOutput.setAction(SEARCH_FAIL);
                		userOutput.setUserList(null);
                		objOut.writeObject(userOutput);
                	}
//                	break;
                case SEARCH_USER_TYPE:
                	if(db.searchUserLastName(user.getLastName()) != null) {
                		userOutput = new UserWrapper();
                		userOutput.setUserList(db.searchUserType(user.getUserType()));
                		objOut.writeObject(userOutput);
                	} else {
                		userOutput.setAction(SEARCH_FAIL);
                		userOutput.setUserList(null);
                		objOut.writeObject(userOutput);
                	}
//                	break;
                case DELETE_USER:
                	if(db.deleteUser(user.getID()) == DELETE_SUCCESS) {
                		userOutput = new UserWrapper();
                		userOutput.setAction(DELETE_SUCCESS);
                		
                	}
                	
                	
                	
                	
                	
//                	break;
                case ADD_USER:
                	db.addUser(user, this);
//                	break;
                case UPDATE_USER:
                	db.updateExistingUser(user, this);
                	//break;
            }

        }
        
	}
	
	public void write() {
		
		
	}
	
}
