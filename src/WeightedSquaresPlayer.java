
public class WeightedSquaresPlayer extends MinimaxPlayer{

	public WeightedSquaresPlayer(String pName) {
		super(pName);
	}
	
	public WeightedSquaresPlayer(String pName, int ply) {
		super(pName);
		PLY = ply;
	}
	
	/**
	 * The heuristic evaluation function 
	 * use the feature the number of possible legal moves for a player as a heuristic
	 * to choose the best move
	 * @param game the current Othello game
	 */
	public int evaluationFn(Othello game) {
		return game.weightedSquares(colour) - game.weightedSquares(game.opponent(colour));
	}
}
