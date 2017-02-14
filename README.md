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

Basically the genetic algorithm applied in this case will be described as following. Apply the algorithm to each parameter( the feature contributes to the heuristic evaluation function) that we take into consideration to optimize them. Afterwards, we combine those parameters and give each of them a weighted coefficient and optimize these coefficients by GA again.

First randomly generate a population for those parameters or coefficients in the state space. Here each element is like a chromosome which may be values for the static weighted squares, usually ten integers due to symmetric feature of the game, or coefficients values for those combined patameters. Then, calculate the value which measures how good the element is, called fitness, for each population element. To compute the fitness, one simple way is to let one population element play against a reference program (the original one) and use the result as fitness. Afterwards, classical operators of crossover and mutation are applied to diversify the population (they are applied with respective probabilities). As better candidates should have a better chance of reproducing and due to the increasing generation population, the computation will take more time, here we use an enhancement to optimize the computation. Simulated Annealing process can be used here to choose better candidate for reproduction. For example, (same as reference 2)after applying the crossover operator, there are four individuals (two parents P1,P2 and two children C1,C2) with their respective fitness. Afterward, those four individuals compete in a tournament. The two winners are then inserted in the next generation. if C1 is better than P1 then C1 is selected. Else C1 will be selected according to a probability which decreases with the generation number. At this step a new population has been created and the GA process is then iterated. After many times of evolution, it has a big chance that the parameter assignment in the system can have an excellent ability.


References:
1. Applying Genetic Algorithm and Self-Learning on computer Othello, doi=10.1.1.117.9089

2. A genetic algorithm to improve an Othello program, doi=10.1.1.16.1218
