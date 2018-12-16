package main;

import java.util.LinkedList;
import java.util.List;

public class CardDeck {
	private List<Card> cards;

	public Card getCard() {
		return null;
	}

	private static final String[] PATTERNS = { "spade", "diamond", "heart", "club" };

	private static int CARD_COUNT = 13;

	public CardDeck() {
		cards = this.generateCards();
	}

	private String numberToDenomination(int number) {
		if (number == 1) {
			return "A";
		} else if (number == 11) {
			return "J";
		} else if (number == 12) {
			return "Q";
		} else if (number == 13) {
			return "K";
		}
		return String.valueOf(number);
	}

	private List<Card> generateCards() {
		List<Card> cards = new LinkedList<>();

		for (String pattern : PATTERNS) {
			for (int i = 1; i <= CARD_COUNT; i++) {
				String denomination = this.numberToDenomination(i);
				Card card = new Card(pattern, denomination);
				cards.add(card);
			}
		}
		return cards;

	}
	
	public Card draw() {
		
		Card selectedCard = getRandomCard();
		cards.remove(selectedCard);
		
		return selectedCard;
	}
	
	private Card getRandomCard() {
		int size = cards.size();
		int select = (int) (Math.random()*size);
		return cards.get(select);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Card card : cards) {
			sb.append(card.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
