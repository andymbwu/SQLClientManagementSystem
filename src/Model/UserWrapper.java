package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that wraps user info into an object that also contains an action id and query
 */
public class UserWrapper implements Constants, Serializable {

    private int action;
    private String query;
    private ArrayList<UserModel> userList;

    /**
     * Constructor for user wrapper class that sets args to null or 0
     */
    public UserWrapper() {
        this.action = 0;
        this.query = "";
        this.userList = null;
    }

    /**
     * Constructor that passes user, query, and action values
     *
     * @param user
     * @param query
     * @param action
     */
    public UserWrapper(UserModel user, String query, int action) {
        this.action = action;
        this.query = query;
        userList.add(user);
    }

    /**
     * getter for action int
     *
     * @return
     */
    public int getAction() {
        return action;
    }

    /**
     * setter for action int
     *
     * @param action
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * getter for query string
     *
     * @return
     */
    public String getQuery() {
        return query;
    }

    /**
     * setter for query string
     *
     * @param query
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * getter for temp user arraylist
     *
     * @return
     */
    public ArrayList<UserModel> getUserList() {
        return userList;
    }

    /**
     * setter for temp user arraylist
     *
     * @param userList
     */
    public void setUserList(ArrayList<UserModel> userList) {
        this.userList = userList;
    }

    /**
     * method that clears the user wrapper
     */
    public void clear() {
        this.action = 0;
        this.query = "";
        this.userList = null;
    }

}
