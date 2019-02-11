/** This interface contains the constants for use by the game. Each time the game is played a new board
 * (2D array of characters) is created and initialized with spaces, the SPACE_CHAR constant. The X player
 * and O player are only allowed to mark the board with the LETTER_X ('X') and LETTER_O ('O') respectively. */
public interface Constants {
	static final int SEARCH_LAST_NAME = 1;
	static final int SEARCH_USER_ID = 2;
	static final int SEARCH_USER_TYPE = 3;
	static final int SEARCH_FAIL = 4;
	static final int SEARCH_SUCCESS = 5;
	static final int DELETE_USER = 6;
	static final int ADD_USER = 7;
	static final int UPDATE_USER = 8;
	static final int ADD_SUCCESS = 9;
	static final int ADD_FAIL = 10;
	static final int DELETE_SUCCESS = 11;
	static final int DELETE_FAIL = 12;
	static final int UPDATE_SUCCESS = 13;
	static final int UPDATE_FAIL = 14;
}
