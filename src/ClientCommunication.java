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

public class ClientCommunication {

    private Socket aSocket;
    private ObjectOutputStream objOut = null;
    private ObjectInputStream objIn = null;
    Scanner stdIn = null;

    private UserController controller;

    public void communicate (){
        try {
            aSocket = new Socket("localhost",4444);
            System.out.println("Client connected");
            objOut = new ObjectOutputStream(aSocket.getOutputStream());
            UserModel model = new UserModel();
            objOut.writeObject(model);

            objIn = new ObjectInputStream(aSocket.getInputStream());
            UserModel
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserModel model = new UserModel();
        objOut.writeObject(model))



    }

    /**
     * Opens a file input stream, using the data field textFileIn
     * @param textFileName name of text file to open
     */
    public void openFileInputStream(String textFileName) {
        try {
            textFileIn = new Scanner(new File(textFileName));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("File not found.");
        }
        // TO BE COMPLETED BY THE STUDENTS
    }

    /**
     * Opens an ObjectOutputStream using objectOut data field
     * @param objectFileName name of the object file to be created
     */
    public void openObjectOutputStream(String objectFileName) {
        try {
            objectOut = new ObjectOutputStream(new FileOutputStream(objectFileName));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TO BE COMPLETED BY THE STUDENTS

    }


    /**
     * Reads records from given text file, fills the blank MusicRecord
     * created by the constructor with the existing data in the text
     * file and serializes each record object into a binary file
     */
    public void createObjectFile() {

        while (textFileIn.hasNext()) // loop until end of text file is reached
        {
            int year = Integer.parseInt(textFileIn.nextLine());
            System.out.print(year + "  ");       // echo data read from text file

            String songName = textFileIn.nextLine();
            System.out.print(songName + "  ");  // echo data read from text file

            String singerName = textFileIn.nextLine();
            System.out.print(singerName + "  "); // echo data read from text file

            double price = Double.parseDouble(textFileIn.nextLine());
            System.out.println(price + "  ");    // echo data read from text file

            setRecord(year, songName, singerName, price);
            textFileIn.nextLine();   // read the dashed lines and do nothing

            // THE REST OF THE CODE TO BE COMPLETED BY THE STUDENTS
            try {
                objectOut.writeObject(record);
                objectOut.reset();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchElementException n) {
                n.printStackTrace();
            }


        }

        textFileIn.close();

        // YOUR CODE GOES HERE
        try {
            if(objectOut != null)
                objectOut.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("Error closing '.ser' file");
        }
    }

    /**
     * Main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        ClientCommunication cm = new ClientCommunication();

        String textFileName = "someSongs.txt"; // Name of a text file that contains
        // song records

        String objectFileName = "mySongs.ser"; // Name of the binary file to
        // serialize record objects

        cm.openFileInputStream(textFileName);   // open the text file to read from

        cm.openObjectOutputStream(objectFileName); // open the object file to
        // write music records into it

        cm.createObjectFile();   // read records from opened text file, and write
        // them into the object file.
    }

}
