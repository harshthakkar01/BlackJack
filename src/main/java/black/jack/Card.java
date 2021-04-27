package black.jack;

public class Card {
	
	private Suit suit;
	private Face face;
	
	public Card(Suit _suit, Face _face) {
		suit = _suit;
		face = _face;
	}
	
	public String toString() {
		return this.suit.toString() + " - " + this.face.toString();
	}
	
	public Face getFace() {
		return this.face;
	}
	
	public Suit getSuit() {
		return this.suit;
	}
}
