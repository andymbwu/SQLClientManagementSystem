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
            		userOutput = new UserWrapper();
                	if(db.searchUserLastName(user.getLastName()) != null) {
                		userOutput.setUserList(db.searchUserLastName(user.getLastName()));
                		userOutput.setAction(SEARCH_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(SEARCH_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}

//                	break;
                case SEARCH_USER_ID:
            		userOutput = new UserWrapper();
                	if(db.searchUserLastName(user.getLastName()) != null) {
                		userOutput.setUserList(db.searchUserID(user.getID()));
                		userOutput.setAction(SEARCH_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(SEARCH_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
//                	break;
                case SEARCH_USER_TYPE:
                	userOutput = new UserWrapper();
                	if(db.searchUserLastName(user.getLastName()) != null) {
                		userOutput.setUserList(db.searchUserType(user.getUserType()));
                		userOutput.setAction(SEARCH_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(SEARCH_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
//                	break;
                case DELETE_USER:
            		userOutput = new UserWrapper();
                	if(db.deleteUser(user.getID()) == DELETE_SUCCESS) {
                		userOutput.setAction(DELETE_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(DELETE_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
                	
//                	break;
                case ADD_USER:
                	userOutput = new UserWrapper();
                	if(db.addUser(user) == ADD_SUCCESS) {
                		userOutput.setAction(ADD_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(ADD_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
                	
//                	break;
                case UPDATE_USER:                	
                	userOutput = new UserWrapper();
                	if(db.updateExistingUser(user) == UPDATE_SUCCESS) {
                		userOutput.setAction(UPDATE_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(UPDATE_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                		
                	}
                	
                	
                	//break;
            }

        }
        
	}
	
	public void write(UserWrapper u) {
		try {
			objOut.writeObject(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
