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

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
            String query = userInput.getQuery();

            int action = userInput.getAction();

            System.out.println(query);
            System.out.println(action);


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
                	System.out.println("in search id switch");
                	userOutput = new UserWrapper();
            		ArrayList<UserModel> searchID = new ArrayList<UserModel>();
            		searchID = db.searchUserID(Integer.parseInt(query));
            		System.out.println("Size of user id search results: " + searchID.size());
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
                	System.out.println("in search type switch");
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
                	System.out.println("in delete switch");
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
                	System.out.println("in add switch");
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
                	System.out.println("in update switch");
                	userOutput = new UserWrapper();
                	UserModel updateUser = userInput.getUserList().get(0);
                	System.out.println(updateUser.getFirstName());
                	System.out.println(updateUser.getLastName());
                	System.out.println(updateUser.getAddress());
                	System.out.println(updateUser.getPhoneNumber());
                	System.out.println(updateUser.getPostalCode());
                	System.out.println(updateUser.getUserType());

					System.out.println("The array size is: " + userInput.getUserList().size());
                	int temp = db.updateExistingUser(updateUser);
                	System.out.println("Returned from  update with " + temp);

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
