import java.lang.String;

/**
 * This is the abstract superclass for all Player types.
 * 
 * Any and all player implementations should extend this class.
 */

public abstract class Player {
	public String name = "Player";
	public char colour ='.';
	
	/**
	 * Blank class constructor
	 *
	 */
	public Player() {
		name = "Player";
		colour = '.';
	}
	
	/**
	 * Class constructor for the abstract class Player
	 * @param pName The name of the player
	 */
	public Player(String pName) {
		name = pName;
		colour = '.';
	}

	/**
	 * Abstract method that needs to be extended. 
	 */
	public abstract void initialize(char pColour);

	/**
	 * Abstract method that calculates a valid move.
	 */
	public abstract OthelloMove makeMove(Othello game);
	
}
