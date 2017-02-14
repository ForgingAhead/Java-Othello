
import java.util.Scanner;

public class Test {

	public static void main(String args[]) {
		
		Player p1 = new HumanPlayer("Human");
		Player p2 = new WeightedSquaresPlayer("WeightedSquares");
		int first,second,ply1=4,ply2=4;
		boolean flag = true;
		String astr = "";
		
		astr += "\nPlease choose the following number to specify the players: \n" 
			  + "1. HumanPlayer\n"
			  + "2. FrontierDisksPlayer, heuristic: \n"
			  + "      using the feature number of disks adjacent to empty squares.\n"
			  + "3. WeightedSquaresPlayer, heuristic: \n"
			  + "      using the feature weighted squares value for one player.\n"
			  + "4. CurrentMobilityPlayer, heuristic: \n"
			  + "      using the feature number of legal moves for one player.\n"
			  + "5. PotentialMobilityPlayer, heuristic: \n"
			  + "      combine two features: number of frontier discs \n"
			  +	"        and number of empty discs adjacent to the opponent's discs.\n"
			  + "\n";
		System.out.println(astr);
		
		/* set the two player according to user's choices */
		Scanner scanner = new Scanner(System.in);
		while(flag) {
			flag = false;
			System.out.print("Please choose for the first player: ");
			first = scanner.nextInt();
			if(first!=1) {
				System.out.print("Please specify the ply: ");
				ply1 = scanner.nextInt();
			}
			switch(first) {
			case 1:
				p1 = new HumanPlayer("Human"); break;
			case 2:
				p1 = new FrontierDisksPlayer("FrontierDisks", ply1); break;
			case 3:
				p1 = new WeightedSquaresPlayer("WeightedSquares", ply1); break;
			case 4:
				p1 = new CurrentMobilityPlayer("CurrentMobility",ply1); break;
			case 5:
				p1 = new PotentialMobilityPlayer("PotentialMobility",ply1); break;
			default :
				flag = true;
				System.out.print("Invalid input! Please input again!\n");
			}
		}
		flag = true;
		while(flag) {
			flag = false;
			System.out.print("Please choose for the second player: ");
			second = scanner.nextInt();
			if(second!=1) {
				System.out.print("Please specify the ply: ");
				ply2 = scanner.nextInt();
			}
			switch(second) {
			case 1:
				p2 = new HumanPlayer("Human"); break;
			case 2:
				p2 = new FrontierDisksPlayer("FrontierDisks", ply2); break;
			case 3:
				p2 = new WeightedSquaresPlayer("WeightedSquares", ply2); break;
			case 4:
				p2 = new CurrentMobilityPlayer("CurrentMobility",ply2); break;
			case 5:
				p2 = new PotentialMobilityPlayer("PotentialMobility",ply2); break;
			default :
				flag = true;
				System.out.print("Invalid input! Please input again!\n");
			}
		}
	
		Othello game = new Othello();
		
		game.playOneGame(p1, p2, true); //begin to play the game.
	}
}
