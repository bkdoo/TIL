package main;

import java.util.List;

public interface Player {
	void receiveCard(Card card);
	
	void showCards();
	
	List<Card> openCards();
	
	void turnOn();
	
	void turnOff();
	
	boolean isTurn();
}
