import java.io.Serializable;
import java.util.ArrayList;

public class UserWrapper implements Constants, Serializable {

	private int action;
	private String query;
	private ArrayList<UserModel> userList;

	public UserWrapper() {
		this.action=0;
		this.query="";
		this.userList=null;
	}

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


	public void clear() {
		this.action = 0;
		this.query = "";
		this.userList=null;
	}
	
//	public String toString() {
//		userList.get(0)
//	}
	
}
