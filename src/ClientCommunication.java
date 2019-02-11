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

    public void communicate (){
//    	UserWrapper response
    	
            while(true){
            	try {
					theWrapper = (UserWrapper)objIn.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	int action = theWrapper.getAction();
            	
            	switch(action) {
            	case DELETE_SUCCESS:
            		JOptionPane.showMessageDialog(null,
                            "User was deleted successfully", "Error",
                            JOptionPane.WARNING_MESSAGE);
            	case DELETE_FAIL:
               		JOptionPane.showMessageDialog(null,
                            "User was not deleted successfully", "Error",
                            JOptionPane.WARNING_MESSAGE);
            	case UPDATE_SUCCESS:
            		JOptionPane.showMessageDialog(null,
                            "User was updated successfully", "Error",
                            JOptionPane.WARNING_MESSAGE);
            	case UPDATE_FAIL:
            		JOptionPane.showMessageDialog(null,
                            "User was not updated successfully", "Error",
                            JOptionPane.WARNING_MESSAGE);
            	case ADD_SUCCESS:
            		JOptionPane.showMessageDialog(null,
                            "User was added successfully", "Error",
                            JOptionPane.WARNING_MESSAGE);
            	case ADD_FAIL:
            		JOptionPane.showMessageDialog(null,
                            "User was not added successfully", "Error",
                            JOptionPane.WARNING_MESSAGE);
            		
            		
            		
            		
            		
            	}
            	
            	


            }
//            objOut.writeObject(model);
//
//            objIn = new ObjectInputStream(aSocket.getInputStream());
//


    }

//    /**
//     * Opens a file input stream, using the data field textFileIn
//     * @param textFileName name of text file to open
//     */
//    public void openFileInputStream(String textFileName) {
//        try {
//            textFileIn = new Scanner(new File(textFileName));
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            System.err.println("File not found.");
//        }
//        // TO BE COMPLETED BY THE STUDENTS
//    }

//    /**
//     * Opens an ObjectOutputStream using objectOut data field
//     * @param objectFileName name of the object file to be created
//     */
//    public void openObjectOutputStream(String objectFileName) {
//        try {
//            objectOut = new ObjectOutputStream(new FileOutputStream(objectFileName));
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        // TO BE COMPLETED BY THE STUDENTS
//
//    }


//    /**
//     * Reads records from given text file, fills the blank MusicRecord
//     * created by the constructor with the existing data in the text
//     * file and serializes each record object into a binary file
//     */
//    public void createObjectFile() {
//
//        while (textFileIn.hasNext()) // loop until end of text file is reached
//        {
//            int year = Integer.parseInt(textFileIn.nextLine());
//            System.out.print(year + "  ");       // echo data read from text file
//
//            String songName = textFileIn.nextLine();
//            System.out.print(songName + "  ");  // echo data read from text file
//
//            String singerName = textFileIn.nextLine();
//            System.out.print(singerName + "  "); // echo data read from text file
//
//            double price = Double.parseDouble(textFileIn.nextLine());
//            System.out.println(price + "  ");    // echo data read from text file
//
//            setRecord(year, songName, singerName, price);
//            textFileIn.nextLine();   // read the dashed lines and do nothing
//
//            // THE REST OF THE CODE TO BE COMPLETED BY THE STUDENTS
//            try {
//                objectOut.writeObject(record);
//                objectOut.reset();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (NoSuchElementException n) {
//                n.printStackTrace();
//            }
//
//
//        }
//
//        textFileIn.close();
//
//        // YOUR CODE GOES HERE
//        try {
//            if(objectOut != null)
//                objectOut.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            System.err.println("Error closing '.ser' file");
//        }
//    }

    /**
     * Main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        ClientCommunication cm = new ClientCommunication("localhost",9899);

//        String textFileName = "someSongs.txt"; // Name of a text file that contains
//        // song records
//
//        String objectFileName = "mySongs.ser"; // Name of the binary file to
//        // serialize record objects
//
//        cm.openFileInputStream(textFileName);   // open the text file to read from
//
//        cm.openObjectOutputStream(objectFileName); // open the object file to
//        // write music records into it
//
//        cm.createObjectFile();   // read records from opened text file, and write
//        // them into the object file.
    }

	public void write(UserWrapper out) {
		// TODO Auto-generated method stub
		
		try {
			objOut.writeObject(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
