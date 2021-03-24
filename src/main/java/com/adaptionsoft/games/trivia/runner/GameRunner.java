
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;
import java.util.Scanner;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("R pour rock et T pour techno");
        String s = in.nextLine();
        System.out.println("You entered string "+s);
        
		Game aGame = new Game(s);
		
		aGame.add("Chet");
		aGame.add("Pa");
		aGame.add("Qu");
		aGame.add("yrteth");
		aGame.add("fe");
		aGame.add("az");
		aGame.add("gzee");
		
		if(aGame.isPlayable()) {
			
			Random rand = new Random();
			
			do {
				
				aGame.roll(rand.nextInt(5) + 1);
				
				if (rand.nextInt(9) == 7) {
					notAWinner = aGame.wrongAnswer();
				} else {
					notAWinner = aGame.wasCorrectlyAnswered();
				}
				
				
				
			} while (notAWinner);
		} else {
			System.out.println("Le nombre de joueur n'est pas correct");
		}
		
	}
}
