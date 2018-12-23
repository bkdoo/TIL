package main;

public class Card {
	private String pattern;
	private String denomination;
	private int point;

	public Card(String pattern, int index) {
		this.pattern = pattern;
		this.denomination = this.numberToDenomination(index);
		this.point = this.numberToPoint(index);
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

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Card{" + "pattern = " + pattern + ", denomiation = " + denomination + "}";
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

	private int numberToPoint(int number) {
		if (number >= 11) {
			return 10;
		}
		return number;
	}

}
