import java.io.Serializable;
import java.util.ArrayList;

public class UserWrapper implements Constants, Serializable {

	
	private int action;
	private String query;
	private ArrayList<UserModel> userList;

	public UserWrapper(UserModel user, String query, int action){
		this.action = action;
		this.query = query;
		userList.add(user);
	}


	
	
	
	
	
	
}
