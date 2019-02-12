package Client;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Scanner;
import Model.UserModel;
import Model.UserWrapper;
import Model.Constants;

public class ClientCommunication implements Constants{

    private Socket aSocket;
    private ObjectOutputStream objOut = null;
    private ObjectInputStream objIn = null;

    private UserView theView;
    private UserModel theModel;
    private UserWrapper theWrapper;

    private UserController theController;

    public ClientCommunication(String serverName,int portNumber){
      
        try {
            aSocket = new Socket(serverName,portNumber);
            objOut = new ObjectOutputStream(aSocket.getOutputStream());
            objIn = new ObjectInputStream(aSocket.getInputStream());
            
            theModel = new UserModel();
            theView = new UserView();
            theController = new UserController(theView,theModel,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void write(UserWrapper out) {
		// TODO Auto-generated method stub
		
		try {
			objOut.writeObject(out);
			objOut.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void communicate(UserWrapper u) {
		try {
			objOut.writeObject(u);
			objOut.reset();
			
			try {
				theWrapper = (UserWrapper)objIn.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        	int action = theWrapper.getAction();
        	System.out.println("Action is " + action);
        	
        	switch(action) {
        	case DELETE_SUCCESS:
        		JOptionPane.showMessageDialog(null,
                        "User was deleted successfully", "Error",
                        JOptionPane.WARNING_MESSAGE);
        		break;
        		
        	case DELETE_FAIL:
           		JOptionPane.showMessageDialog(null,
                        "User was not deleted successfully", "Error",
                        JOptionPane.WARNING_MESSAGE);
           		break;
           		
        	case UPDATE_SUCCESS:
        		JOptionPane.showMessageDialog(null,
                        "User was updated successfully", "Error",
                        JOptionPane.WARNING_MESSAGE);
        		break;
        		
        	case UPDATE_FAIL:
        		JOptionPane.showMessageDialog(null,
                        "User was not updated successfully", "Error",
                        JOptionPane.WARNING_MESSAGE);
        		break;
        		
        	case ADD_SUCCESS:
        		JOptionPane.showMessageDialog(null,
                        "User was added successfully", "Error",
                        JOptionPane.WARNING_MESSAGE);
        		break;
        		
        	case ADD_FAIL:
        		JOptionPane.showMessageDialog(null,
                        "User was not added successfully", "Error",
                        JOptionPane.WARNING_MESSAGE);
        		break;
        		
            case SEARCH_SUCCESS:
                theView.appendScrollPaneTextArea(theWrapper.getUserList());
                break;                
        	
        	case SEARCH_FAIL:
        	    JOptionPane.showMessageDialog(null,
                        "No users matching the search term were found","Error",
                        JOptionPane.WARNING_MESSAGE);
        	    break;
        	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {

		ClientCommunication cm = new ClientCommunication("localhost",9809);
	}

}
