
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
		Player playerF = new Player("F");
		Player playerG = new Player("G");
		
		aGame.add(playerA);
		aGame.add(playerB);
		aGame.add(playerC);
		aGame.add(playerD);
		aGame.add(playerE);
		aGame.add(playerF);
		//aGame.add(playerG);
		
		if(aGame.isPlayable()) {
			
			Random rand = new Random();
			
			do {
				
				aGame.roll(rand.nextInt(5) + 1);
				if(aGame.getPlayers().get(aGame.getCurrentPlayer()).isJokerDisponible()){
					System.out.println(aGame.getPlayers().get(aGame.getCurrentPlayer()).getName() + ", voulez vous utiliser votre Joker ? Oui/Non");
					String jokerUtilise = in.nextLine();
					if(jokerUtilise.equals("Oui")) {
						aGame.usedJoker(aGame.getPlayers().get(aGame.getCurrentPlayer()));
						//notAWinner = false;
						
					} else if(jokerUtilise.equals("Non")) {
						if (rand.nextInt(9) == 7) {
							notAWinner = aGame.wrongAnswer();
						} else {
							notAWinner = aGame.wasCorrectlyAnswered();
						}
					}
					
				} else {
					if (rand.nextInt(9) == 7) {
						notAWinner = aGame.wrongAnswer();
					} 
					
					else {
						notAWinner = aGame.wasCorrectlyAnswered();
					}
				}	
				
			} while (notAWinner);
		} else {
			System.out.println("Le nombre de joueur n'est pas correct");
		}
		
	}
}
