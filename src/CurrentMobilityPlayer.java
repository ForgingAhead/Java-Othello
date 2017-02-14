
public class CurrentMobilityPlayer extends MinimaxPlayer{
	
	public CurrentMobilityPlayer(String pName) {
		super(pName);
	}
	
	/**
	 * Constructor that uses the name from the superclass
	 * @param pName A String with the name of the player
	 * @param ply the depth for the minimax algorithm
	 */
	public CurrentMobilityPlayer(String pName, int ply) {
		super(pName);
		PLY = ply;
	}

	/**
	 * The heuristic evaluation function 
	 * measure the relative mobility by counting the number of 
	 * legal moves for both sides and combine the values by a 
	 * rational function 100*((a-b)/(a+b+2)
	 * 
	 * @param game the current Othello game
	 */
	public int evaluationFn(Othello game) {
		int myMoves = game.generateMoves(colour).size();
		int opMoves = game.generateMoves(game.opponent(colour)).size();
		return 100*((myMoves - opMoves)/(myMoves+opMoves+2));
	}

}
