import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class DBController implements Constants {
	
	private ServerController sc;

	public Connection jdbc_connection;
	public PreparedStatement preparedStatement;
	public String databaseName = "userdb", tableName = "UserTable", dataFile = "users.txt";

	public String connectionInfo = "jdbc:mysql://localhost:3306/userdb?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true",
			login = "root", password = "password";

	/**
	 * The constructor for the Model class establishes a connection to the database.
	 */
	public DBController() {
		try {
			// If this throws an error, make sure you have added the mySQL connector JAR to
			// the project
			Class.forName("com.mysql.cj.jdbc.Driver");

			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Use the jdbc connection to create a new database in MySQL.
	 */
	public void createDB() {
		try {
			String sql = "CREATE DATABASE " + databaseName;
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Created Database " + databaseName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a data table inside of the database to hold users.
	 */
	public void createTable() {
		String sql = "CREATE TABLE " + tableName + "(" + "ID INT NOT NULL AUTO_INCREMENT, "
				+ "FIRSTNAME VARCHAR(20) NOT NULL, " + "LASTNAME VARCHAR(20) NOT NULL, "
				+ "ADDRESS VARCHAR(50) NOT NULL, " + "POSTALCODE CHAR(7) NOT NULL, " + "PHONENUMBER CHAR(12) NOT NULL,"
				+ "USERTYPE CHAR(1) NOT NULL," + "PRIMARY KEY ( id ))";
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Created Table " + tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method removes the data table from the database (and all the data held
	 * within it!).
	 */
	public void removeTable() {
		String sql = "DROP TABLE " + tableName;
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Removed Table " + tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method fills the data table with all the users from the text file
	 * 'users.txt', if the file is found.
	 */
	public void fillTable() {
		try {
			Scanner sc = new Scanner(new FileReader(dataFile));
			while (sc.hasNext()) {
				String userInfo[] = sc.nextLine().split(";");
				addUser(new UserModel(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4],
						userInfo[5]));

			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + dataFile + " Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method adds a new user to the database table.
	 * 
	 * @param user - User object to add to the database.
	 */
	public int addUser(UserModel user) {
		String sql = "INSERT INTO " + tableName + " VALUES ( NULL, ?, ?, ?, ?, ?, ?);";
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getAddress());
			preparedStatement.setString(4, user.getPostalCode());
			preparedStatement.setString(5, user.getPhoneNumber());
			preparedStatement.setString(6, user.getUserType());

			preparedStatement.executeUpdate();
			
			return ADD_SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ADD_FAIL;
	}

	/**
	 * This is a validation method that checks that the text entered into the 'first
	 * name' text field is less than 20 characters as per the project requirements.
	 * 
	 * @param firstName - String retrieved from the 'first name' text field in the
	 *                  GUI.
	 * @return - boolean return type. True if the firstName parameter is valid,
	 *         false otherwise.
	 */
	public boolean checkFirstName(String firstName) {
		if (firstName.length() > 20 || firstName.isEmpty())
			return false;

		return true;
	}

	/**
	 * This is a validation method that checks that the text entered into the 'last
	 * name' text field is less than 20 characters as per the project requirements.
	 * 
	 * @param lastName - String retrieved from the 'last name' text field in the
	 *                 GUI.
	 * @return - boolean return type. True if the lastName parameter is valid, false
	 *         otherwise.
	 */
	public boolean checkLastName(String lastName) {
		if (lastName.length() > 20 || lastName.isEmpty())
			return false;

		return true;
	}

	/**
	 * This is a validation method that checks that the text entered into the 'last
	 * name' text field is less than 50 characters as per the project requirements.
	 * 
	 * @param address - String retrieved from the 'address' text field in the GUI.
	 * @return - boolean return type. True if the address parameter is valid, false
	 *         otherwise.
	 */
	public boolean checkAddress(String address) {
		if (address.length() > 50 || address.isEmpty())
			return false;

		return true;
	}

	/**
	 * This is a validation method that checks that the text entered into the
	 * 'postal code' text field is in the format of A#A #A#, where A represents an
	 * upper case letter and # represents a number as per the project requirements.
	 * 
	 * @param postalCode - String retrieved from the 'postal code' text field in the
	 *                   GUI.
	 * @return - boolean return type. True if the postalCode parameter is valid,
	 *         false otherwise.
	 */
	public boolean checkPostalCode(String postalCode) {
		if (!postalCode.matches("[A-Za-z][0-9][A-Za-z]\\s[0-9][A-Za-z][0-9]"))
			return false;

		return true;
	}

	/**
	 * This is a validation method that checks that the text entered into the 'phone
	 * number' text field is in the format of ###-###-####, where # represents a
	 * number as per the project requirements.
	 * 
	 * @param phoneNumber - String retrieved from the 'phone number' text field in
	 *                    the GUI.
	 * @return - boolean return type. True if the phoneNumber parameter is valid,
	 *         false otherwise.
	 */
	public boolean checkPhoneNumber(String phoneNumber) {
		if (!phoneNumber.matches("[0-9][0-9][0-9][-][0-9][0-9][0-9][-][0-9][0-9][0-9][0-9]"))
			return false;

		return true;
	}

	/**
	 * This method updates an existing user in the database. The user is located
	 * by their ID and then all of their information is updated from the User
	 * parameter user that is passed to the method.
	 * 
	 * @param user - User object that contains all of the user information
	 *               that needs to be updated.
	 */
	public int updateExistingUser(UserModel user) {

		String sql = "UPDATE " + tableName + " SET FIRSTNAME = ?, LASTNAME = ?, ADDRESS = ?,"
				+ " POSTALCODE = ?, PHONENUMBER = ?, USERTYPE = ? WHERE ID = ?";

		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getAddress());
			preparedStatement.setString(4, user.getPostalCode());
			preparedStatement.setString(5, user.getPhoneNumber());
			preparedStatement.setString(6, user.getUserType());
			preparedStatement.setInt(7, user.getID());

			preparedStatement.executeUpdate();
			
			return UPDATE_SUCCESS;

		} catch (SQLException e) {
			e.printStackTrace();
			return UPDATE_FAIL;
		}

	}

	/**
	 * This method searches the database for users that have a last name that
	 * matches the input parameter lastName. If users are found then an array list
	 * of User objects are returned, if none are found then a null array list is
	 * returned.
	 * 
	 * @param lastName - last name of the user that the user is searching for.
	 * @return - returns an array list of User objects
	 */
	public ArrayList<UserModel> searchUserLastName(String lastName) {
		String sql = "SELECT * FROM " + tableName + " WHERE LASTNAME = ?";
		ResultSet user;
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setString(1, lastName);
			user = preparedStatement.executeQuery();
			ArrayList<UserModel> temp = new ArrayList<UserModel>();
			while (user.next()) {
				temp.add(new UserModel(user.getInt("ID"), user.getString("FIRSTNAME"), user.getString("LASTNAME"),
						user.getString("ADDRESS"), user.getString("POSTALCODE"), user.getString("PHONENUMBER"),
						user.getString("USERTYPE")));
			}
			return temp;

		} catch (SQLException e) {
			e.printStackTrace();
			
		}

		return null;
	}

	/**
	 * This method searches the database for users that have an ID that
	 * matches the input parameter userID. If users are found then an array list
	 * of User objects are returned, if none are found then a null array list is
	 * returned.
	 * 
	 * @param userID - ID of the user that the user is searching for.
	 * @return - returns an array list of User objects
	 */
	public ArrayList<UserModel> searchUserID(int userID) {
		String sql = "SELECT * FROM " + tableName + " WHERE ID = ?";
		ResultSet user;
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			user = preparedStatement.executeQuery();
			ArrayList<UserModel> temp = new ArrayList<UserModel>();
			if (user.next()) {
				temp.add(new UserModel(user.getInt("ID"), user.getString("FIRSTNAME"), user.getString("LASTNAME"),
						user.getString("ADDRESS"), user.getString("POSTALCODE"), user.getString("PHONENUMBER"),
						user.getString("USERTYPE")));
			}
			return temp;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This method searches the database for users that have a user type that
	 * matches the input parameter userType. If users are found then an array list
	 * of User objects are returned, if none are found then a null array list is
	 * returned.
	 * 
	 * @param userType - type of the user that the user is searching for.
	 * @return - returns an array list of User objects
	 */
	public ArrayList<UserModel> searchUserType(String userType) {
		String sql = "SELECT * FROM " + tableName + " WHERE USERTYPE = ?";
		ResultSet user;
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setString(1, userType);
			user = preparedStatement.executeQuery();
			ArrayList<UserModel> temp = new ArrayList<UserModel>();
			while (user.next()) {
				temp.add(new UserModel(user.getInt("ID"), user.getString("FIRSTNAME"), user.getString("LASTNAME"),
						user.getString("ADDRESS"), user.getString("POSTALCODE"), user.getString("PHONENUMBER"),
						user.getString("USERTYPE")));
			}
			return temp;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This method deletes a user from the database that has an ID matching the 
	 * input parameter id.
	 * 
	 * @param userID - type of the user that the user is searching for.
	 */
	public int deleteUser(int userID) {
		String sql = "DELETE FROM " + tableName + " WHERE ID = ?";
		try {
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			preparedStatement.executeUpdate();
			return DELETE_SUCCESS;

		} catch (SQLException e) {
			e.printStackTrace();
			return DELETE_FAIL;
		}
		

	}

	/**
	 * This method prints the contents of the database table to the console.
	 */
	public void printTable() {
		try {
			String sql = "SELECT * FROM " + tableName;
			preparedStatement = jdbc_connection.prepareStatement(sql);
			ResultSet users = preparedStatement.executeQuery();
			System.out.println("Users:");
			while (users.next()) {
				System.out.println(users.getString("ID") + ", " + users.getString("FIRSTNAME") + " "
						+ users.getString("LASTNAME") + ", " + users.getString("ADDRESS") + ", "
						+ users.getString("POSTALCODE") + ", " + users.getString("PHONENUMBER") + ", "
						+ users.getString("USERTYPE"));
			}
			users.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method is used to create the database and table in the database. 
	 */
	public static void main(String args[]) {
		DBController userInfo = new DBController();

		// You should comment this line out once the first database is created (either
		// here or in MySQL workbench)
		//userInfo.createDB();

		//userInfo.createTable();

		//System.out.println("\nFilling the table with users");
		//DBController.fillTable();

		//System.out.println("Reading all users from the table:");
		//DBController.printTable();

		// System.out.println("\nTrying to remove the table");
		// userInfo.removeTable();

		try {
			userInfo.preparedStatement.close();
			userInfo.jdbc_connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("\nThe program is finished running");
		}

	}

}