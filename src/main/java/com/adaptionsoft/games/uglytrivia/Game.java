package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    private ArrayList<Player> players = new ArrayList<Player>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
    String choixCategory = "R";
    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();
    LinkedList technoQuestions = new LinkedList();
    
    private int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    
    public Game(String choixQuestion){
    	this.choixCategory = choixQuestion;
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			if(choixQuestion.contains("R")) {
				rockQuestions.addLast(createRockQuestion(i));
			}
			if(choixQuestion.contains("T")) {
				technoQuestions.addLast(createTechnoQuestion(i));
			}
			
    	}
    }

    public String createRockQuestion(int index){
		return "Rock Question " + index;
	}
    
    public String createTechnoQuestion(int index){
		return "Techno Question " + index;
	}
	
	public boolean isPlayable() {
		return (howManyPlayers() >= 2 && howManyPlayers() <= 6);
	}

	public boolean add(Player player ) {
		
		if(howManyPlayers() <= 5 ) {
			getPlayers().add(player);
		    places[howManyPlayers() -1] = 0;
		    purses[howManyPlayers() -1] = 0;
		    inPenaltyBox[howManyPlayers() -1] = false;
		    
		    System.out.println(player.getName() + " was added");
		    System.out.println("They are player number " + getPlayers().size());
			return true;
		} else {
			System.out.println(player.getName() + " not added, too much player");
			Player overflowPlayer = new Player("");
		    getPlayers().add(overflowPlayer);
		    System.out.println("They are player number " + getPlayers().size());
			return false;
		}
		
	    
	}
	
	public int howManyPlayers() {
		return getPlayers().size();
	}

	public void roll(int roll) {
		System.out.println(getPlayers().get(currentPlayer).getName() + " is the current player");
		System.out.println("They have rolled a " + roll);
		
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				
				System.out.println(getPlayers().get(currentPlayer).getName() + " is getting out of the penalty box");
				places[currentPlayer] = places[currentPlayer] + roll;
				if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
				
				System.out.println(getPlayers().get(currentPlayer).getName() 
						+ "'s new location is " 
						+ places[currentPlayer]);
				System.out.println("The category is " + currentCategory(this.choixCategory));
				askQuestion();
			} else {
				System.out.println(getPlayers().get(currentPlayer).getName() + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
			inPenaltyBox[currentPlayer] = !isGettingOutOfPenaltyBox;

		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll;
			if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;
			
			System.out.println(getPlayers().get(currentPlayer).getName() 
					+ "'s new location is " 
					+ places[currentPlayer]);
			System.out.println("The category is " + currentCategory(this.choixCategory));
			askQuestion();
		}
		
	}

	private void askQuestion() {
		if (currentCategory(this.choixCategory) == "Pop")
			System.out.println(popQuestions.removeFirst());
		if (currentCategory(this.choixCategory) == "Science")
			System.out.println(scienceQuestions.removeFirst());
		if (currentCategory(this.choixCategory) == "Sports")
			System.out.println(sportsQuestions.removeFirst());
		if (currentCategory(this.choixCategory) == "Rock")
			System.out.println(rockQuestions.removeFirst());
		if (currentCategory(this.choixCategory) == "Techno")
			System.out.println(technoQuestions.removeFirst());	
	}
	
	
	private String currentCategory(String choixCategory) {
		if (places[currentPlayer] == 0) return "Pop";
		if (places[currentPlayer] == 4) return "Pop";
		if (places[currentPlayer] == 8) return "Pop";
		if (places[currentPlayer] == 1) return "Science";
		if (places[currentPlayer] == 5) return "Science";
		if (places[currentPlayer] == 9) return "Science";
		if (places[currentPlayer] == 2) return "Sports";
		if (places[currentPlayer] == 6) return "Sports";
		if (places[currentPlayer] == 10) return "Sports";
		if(choixCategory.equals("R")) {
			return "Rock";
		}
		
		else if(choixCategory.equals("T")) {
			return "Techno";
		} else {
			return "Default";
		}
		
	}

	public boolean wasCorrectlyAnswered() {
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				purses[currentPlayer]++;
				System.out.println(getPlayers().get(currentPlayer).getName() 
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				
				boolean winner = didPlayerWin();
				currentPlayer++;
				if (currentPlayer == getPlayers().size()) currentPlayer = 0;
				
				return winner;
			} else {
				currentPlayer++;
				if (currentPlayer == getPlayers().size()) currentPlayer = 0;
				return true;
			}
			
			
			
		} else {
		
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(getPlayers().get(currentPlayer).getName() 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			
			boolean winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == getPlayers().size()) currentPlayer = 0;
			
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(getPlayers().get(currentPlayer).getName() + " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == getPlayers().size()) currentPlayer = 0;
		return true;
	}


	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void usedJoker(Player player) {
		// TODO Auto-generated method stub
		player.setIsJokerDisponible(false);
		currentPlayer++;
		if (currentPlayer == getPlayers().size()) currentPlayer = 0;
		System.out.println("Le joker de " + getPlayers().get(currentPlayer).getName() + "a été utilisé, aucun golds n'a été gagné et le joker ne sera plus disponible pour aucune autre question.");
	}
}
