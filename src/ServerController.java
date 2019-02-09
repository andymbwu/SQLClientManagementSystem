import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerController implements Runnable {

	DBController db = null;
	Socket socket = null;
	ObjectOutputStream objOut = null;
	ObjectInputStream objIn = null;

	public ServerController (DBController db, Socket socket) {
		this.socket = socket;
		this.db = db;
		try {
			this.objOut = new ObjectOutputStream(socket.getOutputStream());
			this.objIn = new ObjectInputStream(socket.getInputStream());  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
	
		while (true) {
			objIn = (UserWrapper)objIn.readLine();
            if (xInput.startsWith("PLAYERNAME")) {
                xInput = xInput.replace("PLAYERNAME", "");
                xPlayer = new Player(xInput, LETTER_X, xSocketIn, xSocketOut);
                xPlayer.setOpponent(oPlayer);
                xPlayer.setBoard(this.getTheBoard());
                theBoard.showToAllPlayers("ANNOUNCE" + xPlayer.getName() + "(Player X) has entered the game");
                System.out.println(xPlayer.getName() + " has entered the game");
            } else if (xInput.startsWith("PLAYERMOVE")) {
                int num_square = Integer.parseInt(xInput.replace("PLAYERMOVE", ""));
                switch (num_square) {
                    case 1:
                        theBoard.addMark(0, 0, LETTER_X);
                        theBoard.updateMarks(LETTER_X, num_square);
                        break;
                    case 2:
                        theBoard.addMark(0, 1, LETTER_X);
                        theBoard.updateMarks(LETTER_X, num_square);
                        break;
                    case 3:
                        theBoard.addMark(0, 2, LETTER_X);
                        theBoard.updateMarks(LETTER_X, num_square);
                        break;
                    case 4:
                        theBoard.addMark(1, 0, LETTER_X);
                        theBoard.updateMarks(LETTER_X, num_square);
                        break;
                    case 5:
                        theBoard.addMark(1, 1, LETTER_X);
                        theBoard.updateMarks(LETTER_X, num_square);
                        break;
                    case 6:
                        theBoard.addMark(1, 2, LETTER_X);
                        theBoard.updateMarks(LETTER_X, num_square);
                        break;
                    case 7:
                        theBoard.addMark(2, 0, LETTER_X);
                        theBoard.updateMarks(LETTER_X, num_square);
                        break;
                    case 8:
                        theBoard.addMark(2, 1, LETTER_X);
                        theBoard.updateMarks(LETTER_X, num_square);
                        break;
                    case 9:
                        theBoard.addMark(2, 2, LETTER_X);
                        theBoard.updateMarks(LETTER_X, num_square);
                        break;
                }
                if(theBoard.xWins()) {
                    theBoard.showToAllPlayers(xPlayer.getName() + " is the Winner!");
                    theBoard.endGame();
                } else if (theBoard.isFull()) {
                    theBoard.showToAllPlayers("The game is a TIE!");
                    theBoard.endGame();
                } else {
                    theBoard.disableXButtons();
                    theBoard.enableOButtons();
                }
            }
	
	}

	
	
}
