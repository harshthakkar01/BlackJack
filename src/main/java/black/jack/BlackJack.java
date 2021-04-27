package black.jack;

import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		
		
		System.out.println("Welcome to BlackJack");
		Deck playingDeck = new Deck();
		playingDeck.createDeck();
		playingDeck.shuffleDeck();
		
		Deck dealerDeck = new Deck();
		Deck playerDeck = new Deck();
		
		double playerMoney = 100.00;
		Scanner input = new Scanner(System.in);
		
		while(playerMoney > 0) {
			boolean endRound = false;
			System.out.println("You have $" + playerMoney + " left. How much will you bet ?");
			double bet = input.nextDouble();
			if(bet > playerMoney) {
				System.out.println("You cannot bet more than you have. Please leave.");
				break;
			}
			
			// Give two cards to player.
			playerDeck.drawDeck(playingDeck);
			playerDeck.drawDeck(playingDeck);
			
			
			// Give two cards to dealer.
			dealerDeck.drawDeck(playingDeck);
			dealerDeck.drawDeck(playingDeck);
			
			// Play
			while(true) {
				System.out.println("------------------------------------------");
				System.out.print("Player hand : ");
				System.out.println(playerDeck.toString());
				System.out.print("Player hand value : ");
				System.out.println(playerDeck.getValue());
				System.out.println();
				
				System.out.println("------------------------------------------");
				System.out.print("Dealer hand : ");
				System.out.println(dealerDeck.getCard(0).toString() + " and a [Hidden] card");
				System.out.println();
				
				System.out.println("Would you like to [1] Hit or [2] Stand ? ");
				int response = input.nextInt();
				
				if(response == 1) {
					playerDeck.drawDeck(playingDeck);
					System.out.println("You draw a  : " + playerDeck.getCard(playerDeck.getSize()-1).toString());
					
					if(playerDeck.getValue() > 21) {
						System.out.println("Busted. Currenly player value is : " + playerDeck.getValue());
						playerMoney -= bet;
						endRound = true;
						break;
					}
					
				} else if(response == 2) {
					break;
				}
			}
			
			System.out.println("------------------------------------------");
			System.out.println("Dealer cards : " + dealerDeck.toString());
			if(dealerDeck.getValue() > playerDeck.getValue() && !endRound) {
				System.out.println("Dealer won. !!!");
				System.out.println("********************");
				playerMoney -= bet;
				endRound = true;
			}
			
			while(dealerDeck.getValue() < 17 && !endRound) {
				dealerDeck.drawDeck(playingDeck);
				System.out.println("Dealer draws a  : " + dealerDeck.getCard(dealerDeck.getSize()-1).toString());
			}
			
			System.out.println("------------------------------------------");
			System.out.print("Dealer hand value : ");
			System.out.println(dealerDeck.getValue());
			
			if(dealerDeck.getValue() > 21 && !endRound) {
				System.out.println("Dealer busted. You won.");
				System.out.println("********************");
				playerMoney += bet;
				endRound = true;
			}
			
			if(dealerDeck.getValue() == playerDeck.getValue() && !endRound) {
				System.out.println("Push");
				System.out.println("********************");
				endRound = true;
			}
			
			if(playerDeck.getValue() > dealerDeck.getValue() && !endRound) {
				System.out.println("Congratulations. You won.");
				System.out.println("********************");
				playerMoney += bet;
				endRound = true;
			} else if(!endRound){
				System.out.println("Dealer won. ");
				System.out.println("********************");
				endRound = true;
				playerMoney -= bet;
			}
			
			playerDeck.moveToDeck(playingDeck);
			dealerDeck.moveToDeck(playingDeck);
			System.out.println("End of hand !");
			System.out.println("********************");
		}
		
		System.out.println("Game over ! You are out of money. :(");
		System.out.println("********************");
	}

}
