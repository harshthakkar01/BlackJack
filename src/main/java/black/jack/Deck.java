package black.jack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards;
	public Deck() {
		cards = new ArrayList<Card>();
	}
	
	public void createDeck() {
		for(Suit cardSuit : Suit.values()){
			for(Face cardFace : Face.values()) {
				this.cards.add(new Card(cardSuit, cardFace));
			}
		}
	}
	
	public void shuffleDeck() {
		ArrayList<Card> tempDeck = new ArrayList<Card>();
		Random random = new Random();
		int randomIndex = 0;
		int size = cards.size();
		for(int i=0; i<size; i++) {
			randomIndex = random.nextInt((this.cards.size()-1-0)+1)+0;
			tempDeck.add(this.cards.get(randomIndex));
			this.cards.remove(randomIndex);
		}
		this.cards = tempDeck;
	}
	
	
	public void moveToDeck(Deck deck) {
		int thisDeckSize = this.cards.size();
		
		for(int i=0; i<thisDeckSize; i++) {
			deck.addCard(this.getCard(i));
		}
		
		for(int i=0; i<thisDeckSize; i++)
			this.removeCard(0);
	}
	public int getSize() {
		return this.cards.size();
	}
	
	public int getValue() {
		int value = 0;
		int ace = 0;
		
		for(Card card : this.cards) {
			switch(card.getFace()) {
				case TWO: value += 2; break;
				case THREE: value += 3; break;
				case FOUR: value += 4; break;
				case FIVE: value += 5; break;
				case SIX: value += 6; break;
				case SEVEN: value += 7; break;
				case EIGHT: value += 8; break;
				case NINE: value += 9; break;
				case TEN: value += 10; break;
				case JACK: value += 10; break;
				case QUEEN: value += 10; break;
				case KING: value += 10; break;
				case ACE: ace += 1; break;
				
			}
		}
		
		for(int i=0; i<ace; i++) {
			if(value > 10) {
				value += 1;
			} else {
				value += 11;
			}
		}
		
		return value;
	}
	
	public String toString() {
		String cardsList = "";
		for(Card card : cards) {
			cardsList += "\n" + card.toString();
		}
		return cardsList;
	}
	
	public void removeCard(int index) {
		this.cards.remove(index);
	}
	
	public Card getCard(int index) {
		return this.cards.get(index);	
	}
	
	public void addCard(Card card) {
		this.cards.add(card);
	}
	
	public void drawDeck(Deck comingDeck) {
		this.cards.add(comingDeck.getCard(0));
		comingDeck.removeCard(0);
	}
}
