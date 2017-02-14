# Java-Othello
A Java implementation for the game Othello. You can play it with the computer! 
Different difficulty levels are devised based on MiniMax.
This is an assignment I did for my Artifical Intelligence module in 2011.
To play this game, go to the directory where you put those source files:

1. Compile:
javac Test.java Player.java Othello.java OthelloMove.java HumanPlayer.java MinimaxPlayer.java WeightedSquaresPlayer.java FrontierDisksPlayer.java CurrentMobilityPlayer.java  PotentialMobilityPlayer.java
2. Run:
java Test

Research into GA to improve the Othello program

Some important features that contribute to the winning of the game like the static  weighted valuation of each position on board, the number of frontier discs and number of empty squares adjacent to opponent's discs, are the parameters that we should tune properly. When we consider these evaluation methods individually, they might not have high precision. But there is some accuracy for each one. When combining them altogether for the evaluation, it’s possible that a move marked as a “good move” by parameter A is unfortunately a "bad move" by parameter B. This varies when different parameters and corresponding coefficients applied. So we need to optimize the parameters and also their weighted coefficients.
