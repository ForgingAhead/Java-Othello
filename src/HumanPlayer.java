
import java.util.Scanner;
import java.lang.String;

	/**
	 * This extends the abstract player class defined earlier. It is
	 * an implementation that prompts a human player for a move. 
	 *
	 */
	public class HumanPlayer extends Player {
		
		/**
		 * Blank class constructor
		 *
		 */
		public HumanPlayer() {}
		
		/**
		 * Constructor that uses the name from the superclass
		 * @param pName A String with the name of the player
		 */
		public HumanPlayer(String pName) {super(pName);}
		
		/**
		 * Extended initialize function. Takes a side
		 * for the player 
		 * @param pColour A char that sets the side the player is on. 'b' for black, and 'w' for white
		 */
		public void initialize(char pColour) {
			colour = pColour;
		}
		
		/**
		 * Makes a move. In this implementation, this just picks
		 * a random move and makes it.
		 */
		public OthelloMove makeMove(Othello game) {
			
			boolean validInput = false;
			OthelloMove humanMove = null;
			Scanner scanner = new Scanner(System.in);
			int col, row;
			if(game.anyLegalMove(colour) == false) {
				OthelloMove noMove = new OthelloMove(0,0);
				noMove.notAmove();
				System.out.println("Here!! no valid moves!\n");///////////////////////////////////
				return noMove;
			}
			// repeat until the player enters a valid move
			while (!validInput) {
				System.out.println("Enter row  col (or -1 to concede): ");		
				try{
					row = scanner.nextInt();
					// If the game is conceded
					if (row == -1) {
						OthelloMove gameOver = new OthelloMove(0, 0);
						gameOver.concede();
						return gameOver;
					}
					else {
						col = scanner.nextInt();
						// if we reach here the player has entered valid input
						// we now need to check if this is a valid move
						if (game.validMove(colour,row,col))	{
							humanMove = new OthelloMove(row,col);
							validInput = true;
						}
						else
							System.out.println("Invalid Move!");
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid Input!");
				}
			};			
			return humanMove;
		
		}

	}