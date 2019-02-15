package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import Model.UserModel;
import Model.Constants;
import Model.UserWrapper;

/**
 * Class that implements runnable and receives objects from the client side via socket connection through a
 * series of switch statements
 */
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
	/**
	 * Method that waits for a UserWrapper to be sent from the client side and executes database commands
	 * depending on the action id
	 */
	public void run() {
        while (true) {
        	try {
        		userInput = new UserWrapper();
                userInput = (UserWrapper)objIn.readObject();

            
            String query = userInput.getQuery();

            int action = userInput.getAction();

            switch(action){
                case SEARCH_LAST_NAME:
            		userOutput = new UserWrapper();
            		ArrayList<UserModel> searchLastName = new ArrayList<UserModel>();
            		searchLastName = db.searchUserLastName(query);
                	if(searchLastName.size() != 0) {
                		userOutput.setUserList(searchLastName);
                		userOutput.setAction(SEARCH_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(SEARCH_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
                	break;

                case SEARCH_USER_ID:
                	userOutput = new UserWrapper();
            		ArrayList<UserModel> searchID = new ArrayList<UserModel>();
            		searchID = db.searchUserID(Integer.parseInt(query));
                	if(searchID.size() != 0) {
                		userOutput.setAction(SEARCH_SUCCESS);
                		userOutput.setUserList(searchID);
                		write(userOutput);
                	} else {
                		userOutput.setAction(SEARCH_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
                	break;

                case SEARCH_USER_TYPE:
                	userOutput = new UserWrapper();
                	ArrayList<UserModel> searchType = new ArrayList<UserModel>();
                	searchType = db.searchUserType(query);
                	if(searchType.size() != 0) {
                		userOutput.setUserList(searchType);
                		userOutput.setAction(SEARCH_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(SEARCH_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
                	break;

                case DELETE_USER:
            		userOutput = new UserWrapper();
                	if(db.deleteUser(Integer.parseInt(query)) == DELETE_SUCCESS) {
                		userOutput.setAction(DELETE_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(DELETE_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
                	break;

                case ADD_USER:
                	userOutput = new UserWrapper();
                	UserModel addUser = userInput.getUserList().get(0);
                	int add = db.addUser(addUser);
                	if(add == ADD_SUCCESS) {
                		userOutput.setAction(ADD_SUCCESS);
                		write(userOutput);
                	} else {
                		userOutput.setAction(ADD_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
                	break;

                case UPDATE_USER:
                	userOutput = new UserWrapper();
                	UserModel updateUser = userInput.getUserList().get(0);
                	
                	int temp = db.updateExistingUser(updateUser);

                	if(temp == UPDATE_SUCCESS) {
                		userOutput.setAction(UPDATE_SUCCESS);
						userOutput.setUserList(null);
                		write(userOutput);
                	} else {
                		userOutput.setAction(UPDATE_FAIL);
                		userOutput.setUserList(null);
                		write(userOutput);
                	}
                	break;
            }
        	} catch (IOException e) {
                System.out.println("Client has been disconnected.");
                break;
            } catch (ClassNotFoundException e) {
            	System.out.println("Client has been disconnected.");
                break;
            }
        }

	}

	/**
	 * Method that writes userOutputs from the run() method to an object to send feedback to the client side,
	 * indicating successful or failed task
	 * @param u: Userwrapper from run() class
	 */
	public void write(UserWrapper u) {
		try {
			objOut.writeObject(u);
			objOut.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
