
public class PotentialMobilityPlayer extends MinimaxPlayer{
	
	public PotentialMobilityPlayer(String pName) {
		super(pName);
	}
	
	/**
	 * Constructor that uses the name from the superclass
	 * @param pName A String with the name of the player
	 * @param ply the depth for the minimax algorithm
	 */
	public PotentialMobilityPlayer(String pName,int ply) {
		super(pName);
		PLY = ply;
	}

	/**
	 * 	The heuristic evaluation function 
	 *  combines two features: the number of frontier discs, the number of
	 *  empty squares adjacent to opponent's discs. And then sum the two values computed by each measure 
	 *  by a rational function 100*((a-b)/(a+b+2)
	 * 
	 * @param game the current Othello game
	 */
	public int evaluationFn(Othello game) {
		int myEmpty = game.emptySquares(game.opponent(colour));
		int opEmpty = game.emptySquares(colour);
		int myFrontier = game.frontierDisks(colour);
		int opFrontier = game.frontierDisks(game.opponent(colour));
		
		return 100*((myEmpty-opEmpty)/(myEmpty+opEmpty+2)) 
				+ 100*((myFrontier-opFrontier)/(myFrontier+opFrontier+2));
	}

	
}
