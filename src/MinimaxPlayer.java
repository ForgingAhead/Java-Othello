import java.util.ArrayList;

public abstract class MinimaxPlayer extends Player{

	protected int PLY = 4; //the depth of the minimax
	/**
	 * Class constructor for the abstract class Player
	 * @param pName The name of the player
	 */
	public MinimaxPlayer(String pName) {
		name = pName;
		colour = '.';
	}

	/**
	 * initialize the colour of the player.
	 */
	public void initialize(char pColour) {
		colour = pColour;
	}

	/**
	 * Calculate a valid move for the player's next move
	 */
	public OthelloMove makeMove(Othello game) {
		ArrayList<OthelloMove> possibleMoves = game.generateMoves(colour);
		if(possibleMoves.size() == 0) {
			OthelloMove noMoves = new OthelloMove(0,0);
			noMoves.notAmove();
			possibleMoves.add(noMoves);
		}
		
		int best = 0;
		int utility = -32768;
		for(int i=0; i<possibleMoves.size(); i++) {
			Othello newGame = new Othello(game);
			newGame.makeMove(colour, possibleMoves.get(i));
			int tmp = abMin(newGame,utility,32768,PLY);
			if(tmp>utility) {
				utility = tmp;
				best = i;
			}
		}
		
		return possibleMoves.get(best);	
	}
	
	public int abMax(Othello game, int a, int b, int cutOff) {
		if(--cutOff == 0) 
			return evaluationFn(game);
		ArrayList<OthelloMove> possibleMoves = game.generateMoves(colour);
		int utility = -32768;
		for(int i=0; i<possibleMoves.size(); i++) {
			Othello newGame = new Othello(game);
			newGame.makeMove(colour, possibleMoves.get(i));
			int tmp = abMin(newGame,a,b,cutOff);
			if(utility < tmp) {
				utility = tmp;
			}
			if(utility >= b)
				return utility;
			a = utility;
		}
		return utility;
	}
	
	public int abMin(Othello game, int a, int b, int cutOff) {
		if(--cutOff == 0) 
			return evaluationFn(game);
		ArrayList<OthelloMove> possibleMoves = game.generateMoves(game.opponent(colour));
		int utility = 32767;
		for(int i=0; i<possibleMoves.size(); i++) {
			Othello newGame = new Othello(game);
			newGame.makeMove(game.opponent(colour), possibleMoves.get(i));
			int tmp = abMax(newGame,a,b,cutOff);
			if(utility > tmp)
				utility = tmp;
			if(utility <= a)
				return utility;
			b = utility;
		}
		return utility;
	}
	
	public abstract int evaluationFn(Othello game);
}
