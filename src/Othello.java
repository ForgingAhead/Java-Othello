import java.lang.Character;
import java.lang.String;
import java.util.ArrayList;

/**
 *	The board is represented as a two-dimensional list. Each
 *	location on the board contains one of the following symbols:
 *	<UL>
 *		<LI>'<code>b</code>' for a piece with dark side up
 *		<LI>'<code>w</code>' for a piece with light side up
 *		<LI>'<code>.</code>' for an empty location
 *		<LI>'<code>*</code>' for a location outside of the playing board
 *	</ul>
 *  The game begins with four markers placed in a square in the 
 *  middle of the grid, two facing light-up, two pieces with the dark side up.
 *  The dark player makes the first move. Dark must place a piece with the dark side 
 *  up on the board, in such a position that there exists at least 
 *  one straight (horizontal, vertical, or diagonal) occupied line between the new piece 
 *  and another dark piece, with one or more contiguous light pieces between them. 
 *	
 */
public class Othello {
	
	private static final int size = 8;
	private char cBoard[][];
	public static String newline = System.getProperty("line.separator");
	
	/**
	 * Blank class constructor.
	 * The playing board size is 8*8 but to make the programming easy, here use a 10*10 2D array 
	 * and the four edge is outside of the playing board and will be set to '*'
	 */
	public Othello() {
		cBoard = new char[size+2][size+2];
		reset();
	}
	
	/**
	 * Class constructor allowing the addition of a board. This special
	 * constructor allows the board state to be loaded as-is. There are
	 * no players attached, so lists of moves can be generated from the
	 * current board state.
	 *
	 * @param n	An int that determines the size of the board.
	 * @param workingBoard A 2 Dimensional array of chars describing a board
	 * @throws OthelloException
	 */
	public Othello(int n, char[][] workingBoard){
		if (n != size+2) {
			System.out.println(" Invalid game board!");
		}
		else 
			cBoard = workingBoard;
	} // end of constructor
	
	/**
	 * Copy Constructor takes an object of type Othello and makes a copy
	 * 
	 * @param k An object of type Othello to be copied.
	 */
	public Othello(Othello k) {
		cBoard = k.getBoardCopy();
	}
	
	/**
	 * This resets the board to its start state
	 */
	public void reset() {
		
		// Define two counters and set them to zero
		int i,j;
		for(i=1; i<size+1; i++)
			for(j=1; j<size+1; j++)
				cBoard[i][j] = '.';
		for(i=0; i<size+2; i++) 
			cBoard[0][i] = cBoard[i][0] = cBoard[size+1][i] = cBoard[i][size+1] = '*';
		i = size/2;
		cBoard[i][i] = cBoard[i+1][i+1] = 'w';
		cBoard[i][i+1] = cBoard[i+1][i] = 'b';
	} // End of reset
	
	/**
	 * This returns a char representing the occupant of the specified
	 * position on the board.
	 * 
	 * @param r An int specifying the row to return the value from
	 * @param c	An int specifying the column to return the value from
	 * @return 	A char that conforms to the following
	 * 			'<code>b</code>' if the position is occupied by the Black player<BR>
	 * 		 	'<code>w</code>' if the position is occupied by the White player<BR>
	 * 			'<code>.</code>' if the position is unoccupied<BR>
	 * 			'<code>*</code>' if the position is outside the 8*8 board<BR>
	 */
	public char positionOccupant(int r, int c) {
		// return the specified value
		return cBoard[r][c];
		
	} // End of Position Occupant
	
	/**
	 * This checks whether or not the specified position is within the bounds
	 * of the board as specified in the initialization
	 * 
	 * @param c An int specifying the column that the position being checked is on
	 * @param r An int specifying the row that the position being checked is on
	 * @return 	'<code>True</code>' if the position is within the bounds of the board <BR>
	 * 			'<code>False</code>' if the position is outside the bounds of the board<BR>
	 */
	private boolean validPosition(int r, int c){
		
		boolean valid = true;
		// If x or y is less than 0 or greater than or equal to iSize
		// then it's invalid
		if ((c <= 0 || c > size) || (r <= 0 || r > size))
			valid = false;
		
		return valid;
	} // End of ValidPosition
	
	/**
	 * This returns whether the specified symbol occupies the specified square
	 * 
	 * @param r An int specifying the row to check for the specified symbol
	 * @param c An int specifying the column to check for the specified symbol
	 * @param symbol A char representing a symbol to check for <BR>
	 * @return 	'<code>True</code>' if the specified symbol is in the specified location <BR>
	 * 			'<code>False</code>' if the specified symbol is not in the specified location<BR>
	 */
	private boolean contains(int r, int c, char symbol){
		
		boolean bContains = false;
		
		// If the position is valid
		if (validPosition(r, c)) {
			// if the location on the board matches the symbol
			// Contains is true.
			//System.out.println("Row: " + r + ", Col: "+ c + ", symbol: " + symbol);
			if (cBoard[r][c] == Character.toLowerCase(symbol))
				bContains = true;
		}	
		return bContains;
	} // End of contains
	
	/**
	 * This returns whether the specified symbol occupies the specified square
	 * 
	 * @param r An int specifying the row to check for the specified symbol
	 * @param c An int specifying the column to check for the specified symbol
	 * @param symbol A char representing a symbol to check for <BR>
	 * @param workingBoard A 2 dimensional array of type char that describes a board
	 * @return 	'<code>True</code>' if the specified symbol is in the specified location <BR>
	 * 			'<code>False</code>' if the specified symbol is not in the specified location<BR>
	 */
	public boolean contains(int r, int c, char symbol, char[][] workingBoard){
		
		boolean bContains = false;
		
		// If the position is valid
		if (validPosition(r, c)) {
			// if the location on the board matches the symbol
			// Contains is true.
			//System.out.println("Row: " + r + ", Col: "+ c + ", symbol: " + symbol);
			if (workingBoard[r][c] == Character.toLowerCase(symbol))
				bContains = true;
		}	
		return bContains;
	} // End of contains
	
	/**
	 * Counts the number of times the specified symbol occurs.
	 * Can be used as a score counter
	 * 
	 * @param symbol A char that specifies the symbol to search for
	 * @return An int with the number of occurences of the symbol
	 */
	public int countSymbol(char symbol) {
		
		// convert to lower case if upper case passed
		if (Character.isUpperCase(symbol)) {
			symbol = Character.toLowerCase(symbol);
		}
		// set the count to zero
		int count = 0;
		// check all squares and count the symbol
		for (int c = 1 ; c <= size; c++) {
			for (int r = 1; r <= size; r++) {
				if (cBoard[c][r] == symbol) {
					count++;
				}
			}
		}
		// return the count
		return count;
	} // end of countSymbol class
	
	
	/**
	 * sum of the weights of the parameter specified player on board                           
	 * @return
	 */
	public int weightedSquares(char player) {
		int weights[][] = {{0,0,0,0,0,0,0,0,0,0},
					  {0,120,-20,20,5,5,20,-20,120,0},
					  {0,-20,-40,-5,-5,-5,-5,-40,-20,0},
					  {0,20,-5,15,3,3,15,-5,20,0},
					  {0,5,-5,3,3,3,3,-5,5,0},
					  {0,5,-5,3,3,3,3,-5,5,0},
					  {0,20,-5,15,3,3,15,-5,20,0},
					  {0,-20,-40,-5,-5,-5,-5,-40,-20,0},
					  {0,120,-20,20,5,5,20,-20,120,0},
					  {0,0,0,0,0,0,0,0,0,0}};
		int sum = 0;
		for(int i=1; i<=size; i++)
			for(int j=1; j<=size; j++) {
				if(cBoard[i][j] == player)
					sum += weights[i][j];
			}
		return sum;
	}
	
	/**
	 * This calculates the number of the parameter specified player adjacent to empty squares.
	 * @param player
	 * @return
	 */
	public int frontierDisks(char player) {
		int dirI[] = {-1,1,0,0,1,-1,1,-1};
		int dirJ[] = {0,0,1,-1,1,-1,-1,1};
		int sum = 0;
		for(int i=1; i<=size; i++) 
			for(int j=1; j<=size; j++) {
				if(cBoard[i][j] == player) {
					for(int k=0; k<8; k++)
						if(cBoard[i+dirI[k]][j+dirJ[k]] == '.'){
							sum++;
							break;
						}
				}
			}
		return sum;
	}
	
	/**
	 * This calculates the number of empty squares adjacent to the parameter specified player.
	 * @param player
	 * @return
	 */
	public int emptySquares(char player) {
		int dirI[] = {-1,1,0,0,1,-1,1,-1};
		int dirJ[] = {0,0,1,-1,1,-1,-1,1};
		int sum = 0;
		for(int i=1; i<=size; i++) 
			for(int j=1; j<=size; j++) {
				if(cBoard[i][j] == '.')
					for(int k=0; k<8; k++) 
						if(cBoard[i+dirI[k]][j+dirJ[k]] == player) {
							sum++;
							break;
						}
			}
		return sum;
	}
	
	
	
	/**
	 * Returns the opponent of the specified player
	 * 
	 * @param player A char describing the current player. Valid chars are 'b' or 'w'
	 * @return 'b' if player is 'w', or 'w' if player is 'b'
	 */
	public char opponent(char player) {
		if (player == 'b')
			return 'w';
		else
			return 'b';
	}// end of opponent
	
	/**
	 * check whether this move would result in any flips in this direction.
	 * if so, return the square number(r*10+c) of the bracket piece, otherwise return 0.
	 * @param player the current player who is making this move
	 * @param r the row number of the move square
	 * @param c the column of the move square
	 * @param dir the direction that we are checking
	 * @return
	 */
	public boolean wouldFlip(char player, int r, int c, int dir) {
		int dirI[] = {-1,1,0,0,1,-1,1,-1};
		int dirJ[] = {0,0,1,-1,1,-1,-1,1};
		int row = r, col = c;
		boolean flag = false;
		for(int i=0; i<8; i++) {
			row+=dirI[dir];
			col+=dirJ[dir];
			if(cBoard[row][col] == opponent(player)){
				flag = true;
			}
			else if(cBoard[row][col] == player){
				if(flag)
					return true;
				else return false;
			}
			else return false;
		}
		return false;
	}
	
	/**
	 * Make any flips in the given direction
	 * @param player the current player who is making this move
	 * @param r the row number of the move square
	 * @param c the column of the move square
	 * @param dir the direction that we are checking
	 * @return
	 */
	public void makeFlip(char player, int r, int c, int dir) {
		int dirI[] = {-1,1,0,0,1,-1,1,-1};
		int dirJ[] = {0,0,1,-1,1,-1,-1,1};
		if(wouldFlip(player,r,c,dir)) {
			r+=dirI[dir];
			c+=dirJ[dir];
			while(cBoard[r][c] != player) {
				cBoard[r][c] = player;
				r+=dirI[dir];
				c+=dirJ[dir];				
			}
		}
	}
	
	public boolean validMove(char player,int r, int c) {
		if(cBoard[r][c] == '.') {
			for(int k=0; k<8; k++)
				if(wouldFlip(player,r,c,k)) {
					return true;
				}
		}
		return false;
	}
	
	public boolean anyLegalMove(char player) {
		for(int i=1; i<=size; i++)
			for(int j=1; j<=size; j++)
				if(validMove(player,i,j))
					return true;
		return false;
	}
	
	/**
	 * This makes a copy of the current board, tries the move
	 * on it, and if the move is valid, copies the board back
	 * over the game-board
	 * 
	 * @param player A char noting the player making the move
	 * @param m A OthelloMove containing the details of the move
	 * @throws OthelloException
	 */
	public void makeMove(char player, OthelloMove m) {
		int r = m.getRow();
		int c = m.getCol();
		if(validMove(player,r,c)) {
			cBoard[r][c] = player;
			for(int i=0; i<size; i++) {
				makeFlip(player,r,c,i);
			}			
		}
	}
	
	/**
	 * This makes a copy of the current board, tries the move
	 * on it, and if the move is valid, copies the board back
	 * over the game-board 
	 */
	public void makeMove(char player, int r, int c) {
		if(validMove(player,r,c)) {
			cBoard[r][c] = player;
			for(int i=0; i<size; i++) {
				makeFlip(player,r,c,i);
			}
		}
		
	}// End of makeMove
	
	
	/**
	 * This returns a string that describes the board
	 * @return A string that describes the board
	 */
	public String boardToString() {
		
		String result = new String();
		
		// initialise as  two spaces
		result = "  ";
		
		// Now print out the numbers on the top row
		for (int i = 1; i <= size; i++) {
			result += Integer.toString(i) + " ";
		}
		result += newline;
		// Now, print out the current state of the table
		for (int y = 1; y <= size; y++) {
			result += Integer.toString(y) + " ";
			for (int x = 1; x <= size; x++) {
				result += Character.toString(cBoard[y][x]) + " ";
			}
			// insert a newline
			result += newline;
		}
		
		return result;
	}
	
	/**
	 * This returns a string that describes a board
	 * @param board A 2 dimensional array of chars describing the board
	 * @return A string that describes the board
	 */
	static public String boardToString(char board[][], int size) {
		
		String result = new String();
		
		// initialise as  two spaces
		result = "  ";
		
		// Now print out the numbers on the top row
		for (int i=1; i <= size; i++) {
			result += Integer.toString(i) + " ";
		}
		result += newline;
		// Now, print out the current state of the table
		for (int y = 1; y <= size; y++) {
			result += Integer.toString(y) + " ";
			for (int x = 1; x <= size; x++) {
				result += Character.toString(board[y][x]) + " ";
			}
			// insert a newline
			result += newline;
		}
		
		return result;
	}
	

	
	/**
	 * This generates a list of all possible moves from the current position
	 * 
	 * @param player A char, either 'b' or 'w' to identify the player taking the move
	 * @return An ArrayList of type OthelloMove with the list of all possible
	 * 			moves
	 */
	public ArrayList<OthelloMove> generateMoves(char player) {
		ArrayList<OthelloMove> possibleMoves = new ArrayList<OthelloMove>();
		for(int i=1; i<=size; i++) {
			for(int j=1; j<=size; j++) {
				if(validMove(player,i,j)) {
					OthelloMove aMove = new OthelloMove(i,j);
					possibleMoves.add(aMove);
				}
			}
		}
		return possibleMoves;
	} // end of generateMoves
	
	/**
	 * This creates a copy of the current state of the board
	 * and returns it as a 2 dimensional array of type int[row][col].
	 * Useful for manipulation of the board and checking of possibilities
	 * 
	 * @return A 2 dimensional array of type char[row][col]
	 */
	public char[][] getBoardCopy() {
		
		// copy of the board state
		char newBoard[][] = new char[size+2][size+2];
		
		// replicate the board in a new array. Must be done
		// because arrayClone does not deep copy multi-
		// dimensional arrays
		for (int i = 0; i < size+2; i++)
			System.arraycopy(cBoard[i], 0, newBoard[i], 0, size);
		
		return newBoard;
	} // end of getBoardCopy
	
	/**
	 * Plays one game of Othello with the specified two players
	 * 
	 * @param p1 A Player object that will be player 1
	 * @param p2 A Player object that will be player 2
	 * @param show A boolean switch allowing the game to be shown visually
	 * @return A char that names the winner. 'b' for black win, 'w' for white win.
	 */
	public void playOneGame(Player p1, Player p2, boolean show) {
		
		p1.initialize('b');
		p2.initialize('w');
		char currentMove = 'b';
		
		while (true) {
			
			OthelloMove move;
			
			if (currentMove != 'w') {

				// Show the board
				System.out.println(boardToString());	
				
				move = p1.makeMove(this);
				if (move.noMoves()) {
					if(anyLegalMove(p2.colour)) {
						System.out.println(p1.name + "'s (Black) move.");
						if(move.gameOver()) {
							System.out.println(p1.name + " concedes. Game Over!\n");
							break;
						}
						else {
							System.out.println("No valid moves. "+ p1.name + " must pass.");
							currentMove = 'w';
						}
					}
					else {
						System.out.println("Game over!\n");
						int difference = countSymbol(p1.colour) - countSymbol(p2.colour);
						if(difference<0)
							System.out.println(p2.name + " is the winner.");
						else System.out.println(p1.name + " is the winner.");
						break;
					}
				}
				else {
					// Make the move
					System.out.println(p1.name + "'s (Black) move.");
					makeMove(p1.colour, move);
					// Show the board
					System.out.println(newline);
					String moveString = "The move is    " + Integer.toString(move.getRow()) + ", " + 
										Integer.toString(move.getCol());
					// Print the move
					System.out.println(moveString);
					System.out.println(newline);

					currentMove = 'w';
					continue;
				}
			}
			if (currentMove != 'b') {
					
				System.out.println(boardToString());
				move = p2.makeMove(this);
				
				/* handle the situation where human player concede or no valid moves and must pass or game over*/
				if (move.noMoves()) {
					if(anyLegalMove(p1.colour)) {
						System.out.println(p2.name + "'s (White) move.");
						if(move.gameOver()) {
							System.out.println(p1.name + " concedes. Game Over!\n");
							break;
						}
						else {
							System.out.println("No valid moves." + p2.name + " must pass. ");
							currentMove = 'b';
						}
					}
					else {
						System.out.println("Game over!\n");
						int difference = countSymbol(p1.colour) - countSymbol(p2.colour);
						if(difference<0)
							System.out.println(p2.name + " is the winner.");
						else System.out.println(p1.name + "is the winner.");
						break;
					}
				}
				else {
					// Make the move
					System.out.println(p2.name + "'s (White) move.");
					makeMove(p2.colour, move);
					currentMove = 'b';
					// Show the board
					System.out.println(newline);
					String moveString = "The move is " + Integer.toString(move.getRow()) + ", " + 
										Integer.toString(move.getCol());
					// Print the move
					System.out.println(moveString);
					System.out.println(newline);
					continue;
				}
			}
			
		}

	} // end of playOneGame
	
} // End of Othello Class
