/**
 * This class stores the information on a move made in the
 * game of Othello.
 * It stores the source row and column, and the destination
 * row and column, and provides methods to test for a jump
 * and whether or not it is a special case move.
 *
 */

public class OthelloMove {
    // A OthelloMove object represents a move in the game of Othello.
    // It holds the row and column of the piece that is to be moved
    // and the row and column of the square to which it is to be moved.
    // (This class makes no guarantee that the move is legal.)
  
	private int toRow, toCol;      // Square it is to move to.
	private boolean noMove = false;
	private boolean gameOver = false;
	/**
	 * Empty class constructor
	 * 
	 */
	public OthelloMove() {}
	
	/**
	 * Class constructor. Populates the data
	 * 
	 * @param r An int that is the row of the source of the move
	 * @param c An int that is the column of the source of the move
	 */
	public OthelloMove(int r, int c) {
		// Constructor.  Just set the values of the instance variables.
		toRow = r;
		toCol = c;
	}
	
	/**
	 * Copy constructor. Populates the data
	 * 
	 * @param c A OthelloMove operator to be copied
	 */
	public OthelloMove(OthelloMove c) {

		toRow = c.getRow();
		toCol = c.getCol();
	}
	
	/**
	 * Returns the row of the destination of the move
	 * @return An int that is the row of the destination of the move
	 */
	public int getRow() {return toRow;}
	/**
	 * Returns the column of the destination of the move
	 * @return An int that is the column of the destination of the move
	 */
	public int getCol() {return toCol;}
	
	public void concede() {
		gameOver = true;
		noMove = true;
	}
	
	public void notAmove() {
		noMove = true;
	}
	
	public boolean gameOver() {
		return gameOver;
	}
	
	/**
	 * Returns the no more moves flag
	 * @return 'True' if there are no more moves <BR>
	 * 			'False' otherwise
	 */
	public boolean noMoves() {
		return noMove;
	}
	
} // end class OthelloMove.