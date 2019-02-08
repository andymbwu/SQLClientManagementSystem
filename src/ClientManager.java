import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ClientManager {

	public Connection jdbc_connection;
	public PreparedStatement preparedStatement;
	public String databaseName = "ClientDB", tableName = "ClientTable", dataFile = "clients.txt";
	
	public String connectionInfo = "jdbc:mysql://localhost:3306/clientdb?autoReconnect=true&useSSL=false",  
			  login          = "run_program",
			  password       = "password";
	
	
	public ClientManager()
	{
		try{
			// If this throws an error, make sure you have added the mySQL connector JAR to the project
			Class.forName("com.mysql.jdbc.Driver");
			
			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		}
		catch(SQLException e) { e.printStackTrace(); }
		catch(Exception e) { e.printStackTrace(); }
	}
	
	// Use the jdbc connection to create a new database in MySQL. 	
	public void createDB()
	{
		try {
			String sql = "CREATE DATABASE " + databaseName;
			preparedStatement = jdbc_connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			System.out.println("Created Database " + databaseName);
		} 
		catch( SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Create a data table inside of the database to hold clients
		public void createTable()
		{
			String sql = "CREATE TABLE " + tableName + "(" +
						 "ID INT NOT NULL AUTO_INCREMENT, " +
						 "FIRSTNAME VARCHAR(20) NOT NULL, " + 
					     "LASTNAME VARCHAR(20) NOT NULL, " + 
					     "ADDRESS VARCHAR(50) NOT NULL, " + 
					     "POSTALCODE CHAR(7) NOT NULL, " + 
					     "PHONENUMBER CHAR(12) NOT NULL," +
					     "CLIENTTYPE CHAR(1) NOT NULL," +
					     "PRIMARY KEY ( id ))";
			try{
				preparedStatement = jdbc_connection.prepareStatement(sql);
				preparedStatement.executeUpdate();
				System.out.println("Created Table " + tableName);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		// Removes the data table from the database (and all the data held within it!)
		public void removeTable()
		{
			String sql = "DROP TABLE " + tableName;
			try{
				preparedStatement = jdbc_connection.prepareStatement(sql);
				preparedStatement.executeUpdate();
				System.out.println("Removed Table " + tableName);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}	
		
		// Fills the data table with all the tools from the text file 'clients.txt' if found
		public void fillTable()
		{
			try{
				Scanner sc = new Scanner(new FileReader(dataFile));
				while(sc.hasNext())
				{
					String clientInfo[] = sc.nextLine().split(";");
					addItem( new User(clientInfo[0], clientInfo[1], clientInfo[2], clientInfo[3],
							clientInfo[4], clientInfo[5]));
 
				}
				sc.close();
			}
			catch(FileNotFoundException e)
			{
				System.err.println("File " + dataFile + " Not Found!");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		// Add a client to the database table
		public void addItem(User client)
		{
			String sql = "INSERT INTO " + tableName +
					" VALUES ( NULL, ?, ?, ?, ?, ?, ?);";
			try{
				preparedStatement = jdbc_connection.prepareStatement(sql);
				preparedStatement.setString(1, client.getFirstName());
				preparedStatement.setString(2, client.getLastName());
				preparedStatement.setString(3, client.getAddress());
				preparedStatement.setString(4, client.getPostalCode());
				preparedStatement.setString(5, client.getPhoneNumber());
				preparedStatement.setString(6, client.getClientType());
				
				preparedStatement.executeUpdate();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		// This method should search the database table for a tool matching the toolID parameter and return that tool.
		// It should return null if no tools matching that ID are found.
		public User searchClient(String firstName)
		{
			String sql = "SELECT * FROM " + tableName + " WHERE FIRSTNAME = ?";
			ResultSet client;
			try {
				preparedStatement = jdbc_connection.prepareStatement(sql);
				preparedStatement.setString(1, firstName);
				client = preparedStatement.executeQuery();
				if(client.next())
				{
					return new User(client.getString("FIRSTNAME"),
									client.getString("LASTNAME"),		
									client.getString("ADDRESS"),
									client.getString("POSTALCODE"),
									client.getString("PHONENUMBER"),
									client.getString("CLIENTTYPE"));
				}
			
			} catch (SQLException e) { e.printStackTrace(); }
			
			return null;
		}
		
		// Prints all the items in the database to console
		public void printTable()
		{
			try {
				String sql = "SELECT * FROM " + tableName;
				preparedStatement = jdbc_connection.prepareStatement(sql);
				ResultSet clients = preparedStatement.executeQuery();
				System.out.println("Clients:");
				while(clients.next())
				{
					System.out.println(clients.getString("FIRSTNAME") + " " + 
									   clients.getString("LASTNAME") + ", " + 
									   clients.getString("ADDRESS") + ", " + 
									   clients.getString("POSTALCODE") + ", " + 
									   clients.getString("PHONENUMBER") + ", " + 
									   clients.getString("CLIENTTYPE"));
				}
				clients.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
		public static void main(String args[])
		{
			ClientManager clientInfo = new ClientManager();
			
			// You should comment this line out once the first database is created (either here or in MySQL workbench)
			//clientInfo.createDB();
	
			clientInfo.createTable();
			
			System.out.println("\nFilling the table with clients");
			clientInfo.fillTable();

			System.out.println("Reading all clients from the table:");
			clientInfo.printTable();
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			clientInfo.removeTable();
			
			try {
				clientInfo.preparedStatement.close();
				clientInfo.jdbc_connection.close();
			} 
			catch (SQLException e) { e.printStackTrace(); }
			finally
			{
				System.out.println("\nThe program is finished running");
			}
		
		}
	
}
