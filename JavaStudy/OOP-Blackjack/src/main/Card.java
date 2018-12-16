package main;

public class Card {
	private String pattern;
	private String denomination;

	public Card(String pattern, String denomination) {
		this.pattern = pattern;
		this.denomination = denomination;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomiation) {
		this.denomination = denomiation;
	}
	
	@Override
	public String toString() {
		return "Card{" + "pattern = " + pattern + ", denomiation = " + denomination +"}";
	}

}
