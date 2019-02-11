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
        		userInput = new UserWrapper();
                userInput = (UserWrapper)objIn.readObject();
                
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String query = userInput.getQuery();
            
            int action = userInput.getAction();
            System.out.println(query);
            System.out.println(action);
        
            	
            switch(action){
                case SEARCH_LAST_NAME:
                	System.out.println("in search last name switch");
            		userOutput = new UserWrapper();
                	if(db.searchUserLastName(query) != null) {
                		userOutput.setUserList(db.searchUserLastName(query));
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
                	if(db.searchUserID(Integer.parseInt(query)) != null) {
                		userOutput.setUserList(db.searchUserID(Integer.parseInt(query)));
                		userOutput.setAction(SEARCH_SUCCESS);
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
                	if(db.searchUserType(query) != null) {
                		userOutput.setUserList(db.searchUserType(query));
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
                	if(db.addUser(addUser) == ADD_SUCCESS) {
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
                	if(db.updateExistingUser(updateUser) == UPDATE_SUCCESS) {
                		userOutput.setAction(UPDATE_SUCCESS);
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
