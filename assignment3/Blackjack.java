package edu.nyu.cs.assignment3;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    // Method to draw a card between 2 and 11
    public static int drawCard(Random r) {
        return r.nextInt(10) + 2;
    }

    // Method to calculate the total of a player's hand
    public static int calculateTotal(int[] cards, int cardCount) {
        int total = 0;
        for (int i = 0; i < cardCount; i++) {
            total += cards[i];
        }
        return total;
    }

    // Method to show a player's cards
    public static void showCards(String player, int[] cards, int cardCount) {
        System.out.print(player + " cards are: ");
        for (int i = 0; i < cardCount; i++) {
            System.out.print(cards[i]);
            if (i < cardCount - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    // Check if a player has busted
    public static boolean isBusted(int[] cards, int cardCount) {
        return calculateTotal(cards, cardCount) > 21;
    }

    // Main game logic
    public static void main(String[] args) throws Exception {
        Random r = initRandom(args); // Initialize random object
        Scanner scanner = new Scanner(System.in); // For user input

        // Initialize hands for the user and the dealer with a maximum of 11 cards
        int[] userCards = new int[11];
        int[] dealerCards = new int[11];
        int userCardCount = 0;
        int dealerCardCount = 0;

        System.out.println("Welcome to Blackjack!");

        // Step 1: Deal two cards to the user
        userCards[userCardCount++] = drawCard(r);
        userCards[userCardCount++] = drawCard(r);
        showCards("Your", userCards, userCardCount);

        // Step 2: Deal two cards to the dealer (but only show one of them)
        dealerCards[dealerCardCount++] = drawCard(r);
        dealerCards[dealerCardCount++] = drawCard(r);

        // Step 3: User's turn
        boolean userBusted = false;
        while (true) {
            System.out.println("Would you like to hit or stand?");
            String userInput = scanner.nextLine().trim().toLowerCase();
            
            if (userInput.equals("hit")) {
                userCards[userCardCount++] = drawCard(r);
                showCards("Your", userCards, userCardCount);
                
                if (isBusted(userCards, userCardCount)) {
                    System.out.println("You have bust!");
                    showCards("Your", userCards, userCardCount);
                    userBusted = true;
                    break;
                }
            } else if (userInput.equals("stand")) {
                break;
            } else {
                System.out.println("Invalid input. Please type 'hit' or 'stand'.");
            }
        }

        // Step 4: Dealer's turn (only if user hasn't busted)
        boolean dealerBusted = false;
        boolean dealerChoice = r.nextBoolean();
        
        if (!userBusted) {
            while (dealerChoice) {
                dealerCards[dealerCardCount++] = drawCard(r);
                System.out.println("The dealer hits.");
                
                if (isBusted(dealerCards, dealerCardCount)) {
                    System.out.println("The dealer has bust!");
                    dealerBusted = true;
                    break;
                }
                
                dealerChoice = r.nextBoolean();
            }
            
            if (!dealerBusted) {
                System.out.println("The dealer stands.");
            }
        }

        // Step 5: Determine winner (only print totals in scenarios where necessary)
        if (userBusted) {
            showCards("The dealer's", dealerCards, dealerCardCount); // Show dealer's cards after user bust
            System.out.println("Dealer wins!");
        } else if (dealerBusted) {
            showCards("Your", userCards, userCardCount);
            showCards("The dealer's", dealerCards, dealerCardCount);
            System.out.println("You win!");
        } else {
            int userTotal = calculateTotal(userCards, userCardCount);
            int dealerTotal = calculateTotal(dealerCards, dealerCardCount);
            
            showCards("Your", userCards, userCardCount);
            showCards("The dealer's", dealerCards, dealerCardCount);
            
            // Print totals only in tie and win/loss cases where no bust occurs
            if (userTotal == dealerTotal) {
                System.out.println("Tie!");
            } else if (userTotal > dealerTotal) {
                System.out.println("You win!");
            } else {
                System.out.println("Dealer wins!");
            }
        }
        
        scanner.close();
    }

    /* Do not modify this method */
    public static Random initRandom(String[] args) {
        if (args.length >= 1) {
            return new Random(Long.parseLong(args[0]));
        } else {
            return new Random();
        }
    }
}
