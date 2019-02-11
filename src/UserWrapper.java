import java.io.Serializable;
import java.util.ArrayList;

public class UserWrapper implements Constants, Serializable {

	private int action = 0;
	private String query = "";
	private ArrayList<UserModel> userList;

	public UserWrapper() {}

	public UserWrapper(UserModel user, String query, int action){
		this.action = action;
		this.query = query;
		userList.add(user);
	}
	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public ArrayList<UserModel> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<UserModel> userList) {
		this.userList = userList;
	}


	
	
	
	
	
	
}
