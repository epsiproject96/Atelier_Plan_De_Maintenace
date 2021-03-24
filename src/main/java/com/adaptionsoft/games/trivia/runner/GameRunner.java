
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;
import java.util.Scanner;

import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("R pour rock et T pour techno");
        String s = in.nextLine();
        System.out.println("You entered string "+s);
        
		Game aGame = new Game(s);

		Player playerA = new Player("A");
		Player playerB = new Player("B");
		Player playerC = new Player("C");
		Player playerD = new Player("D");
		Player playerE = new Player("E");
		
		aGame.add(playerA);
		aGame.add(playerB);
		aGame.add(playerC);
		aGame.add(playerD);
		aGame.add(playerE);
		if(aGame.isPlayable()) {
			
			Random rand = new Random();
			
			do {
				
				aGame.roll(rand.nextInt(5) + 1);
				
				System.out.println(aGame.getPlayers().get(aGame.getCurrentPlayer()) + ", voulez vous utiliser votre Joker ? Oui/Non");
				String jokerUtilise = in.nextLine();
				
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
