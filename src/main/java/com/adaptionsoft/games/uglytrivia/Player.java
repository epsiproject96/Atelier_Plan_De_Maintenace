package com.adaptionsoft.games.uglytrivia;

public class Player {
	
	private String name;
	
	private boolean jokerDisponible;
	
	public Player(String name) {
		this.name = name;
		this.jokerDisponible = true;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setString(String newName) {
		this.name = newName;
	}
	
	public boolean isJokerDisponible() {
		return this.jokerDisponible;
	}
	
	public void setIsJokerDisponible(boolean isJokerDisponible) {
		this.jokerDisponible = isJokerDisponible;
	}

}
