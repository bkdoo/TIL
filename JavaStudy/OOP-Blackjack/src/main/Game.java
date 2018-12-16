package main;

import java.util.Scanner;

public class Game {

	public void play() {
		System.out.println("========= Play Blackjack =========");
		Scanner sc = new Scanner(System.in);
		Dealer dealer = new Dealer();
		Gamer gamer = new Gamer();
		Rule rule = new Rule();
		CardDeck cardDeck = new CardDeck();
		
		System.out.println(cardDeck.toString());
	}
}
