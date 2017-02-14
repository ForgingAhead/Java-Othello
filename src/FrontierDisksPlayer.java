
public class FrontierDisksPlayer extends MinimaxPlayer {
	
	public FrontierDisksPlayer(String pName) {
		super(pName);
	}
	
	public FrontierDisksPlayer(String pName, int ply) {
		super(pName);
		PLY = ply;
	}
	
	/**
	 * The heuristic evaluation function 
	 * use the feature the number of frontier discs as a heuristic
	 * to choose the best move
	 * @param game the current Othello game
	 */
	public int evaluationFn(Othello game) {
		return game.frontierDisks(colour) - game.frontierDisks(game.opponent(colour));
	}
}
