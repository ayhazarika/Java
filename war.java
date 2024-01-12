/*
 * Name: Ayan Hazarika 
 * Date: 2/7/2023
 * Program: War Game V3
 */

//importing methods
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;  

public class MyProgram{
    
    /*
    FIRST METHOD: Welcome user and display rules of game
    Precondition: 
    Postcondition:
    */
    public static void welcome(){
        //create scanner object
        Scanner in = new Scanner(System.in);
        
        System.out.println("WELCOME TO THE GAME OF WAR!");
        System.out.println("---------------------------");
        System.out.println("\nHere are the rules...");
        System.out.println("The goal of the game is to attain ALL 52 cards in the deck.");
        System.out.println("The first player to do so wins the game! \n");
        System.out.println("Each player turns up a card at the same time and the player with the higher card takes both cards and puts them, face down, on the bottom of their stack. \n");
        System.out.println("If both the cards are the same rank, it is a WAR!");
        System.out.println("In this scenario, both players must place down the next three cards in their deck face down.");
        System.out.println("Then, both players must play a fourth card, but facing up. Whichever player has a greater fourth card, wins the war.");
        System.out.println("The winning player then acquires ALL cards involved in the war.");
        System.out.println("If a war is initiated again, repeat this process until a player wins the war. \n\n\n");
        
        //waits 10 seconds for user to read the rules
        System.out.println("\nPress enter to play :)!!");
        in.nextLine();
        
        
    }
    
    /*
    SECOND METHOD: Creating a shuffled deck of cards
    Precondition: double array for the full deck of cards
    Postcondition: method DOES NOT RETURN ANYTHING, but it creates and shuffles the deck
    */
    public static void deckShuffler (double[] deck){
        
        int randomIndex;
        double randomCard;
        
        Random rand = new Random();
        
        //Fill Deck with Cards 2-14
        //the value before the decimal represents the card #, the value  
        //after the decimal represents the suit
        int startingIndex = 0;
        
        for(int i = 2; i < 15; i++){
            deck[startingIndex] = i;
            deck[startingIndex+1] = i+0.1;
            deck[startingIndex+2] = i+0.2;
            deck[startingIndex+3] = i+0.3;
            
            startingIndex+=4;
        }
        

        //loop through each index and swap it with a random value
        for (int i = 0; i < deck.length; i++){
            randomIndex = rand.nextInt(deck.length);
            randomCard = deck[randomIndex];
            
            deck[randomIndex] = deck[i];
            deck[i] = randomCard; 
        }
    
    }
    
    
    /*
    THIRD METHOD: Seperating shuffled deck into two piles
    Precondition: double array user's pile, double array opponent's pile, double array for the full deck of cards
    Postcondition: method DOES NOT RETURN ANYTHING, but it creates two piles
    */
    public static void deckSplitter(double[] pileUser, double[] pileOpponent, double[] deck){
        //loop to put first 26 cards from deck into user's pile 
        for(int i = 0; i < 26; i++){
            pileUser[i] = deck[i];
        }
        
        //loop to put last 26 cards from deck into opponent's pile 
        for(int i = 0; i < 26; i++){
            pileOpponent[i] = deck[i+26];
        }

    }
        
    /*
    FOURTH METHOD: Counting number of cards that each pile has
    Precondition: double array user's pile, double array opponent's pile, int variable for user card count, int variable for opponent card count
    Postcondition: returns the number of cards present in the pile
    */
    public static int cardCounter(double[] pile, int numCards){
        //counting number of cards in user's pile
        for(int i = 0; i < pile.length; i++){
            if(pile[i] != 0.0){
                numCards++;
            }
        }
        return numCards;
    }

    /*
    FIFTH METHOD: Determines what card to display
    Precondition: double variable of current user card, double variable of current opponent card
    Postcondition: method DOES NOT RETURN ANYTHING, but it prints out the card at hand for the user and opponent
    */
    public static void displayCard(double currentUserCard, double currentOpponentCard){
        //initializing variables
        char spade = '\u2660';
        char heart = '\u2665';
        char diamond = '\u2666';
        char club = '\u2663';
    
        char userSuit;
        char opponentSuit;

        double userCardValue = Math.floor(currentUserCard);
        double opponentCardValue = Math.floor(currentOpponentCard); 

        double diffUser = currentUserCard - userCardValue;
        double diffOpponent = currentOpponentCard - opponentCardValue;

        //determining suit for user
        if(diffUser == 0){
            userSuit = spade;
        }
        else if(diffUser < 0.1){
            userSuit = heart;
        }
        else if(diffUser < 0.2){
            userSuit = diamond;
        }
        else{
            userSuit = club;
        }

        //determining suit for opponent
        if(diffOpponent == 0){
            opponentSuit = spade;
        }
        else if(diffOpponent < 0.1){
            opponentSuit = heart;
        }
        else if(diffOpponent < 0.2){
            opponentSuit = diamond;
        }
        else{
            opponentSuit = club;
        }
    

        //checking if user card is face card
        if(userCardValue == 14.0){
            System.out.println("You drew an: A" + userSuit);
        }    
        else if (userCardValue == 11.0){
            System.out.println("You drew a: J" + userSuit);
        }
        else if (userCardValue == 12.0){
            System.out.println("You drew a: Q" + userSuit);
        }
        else if (userCardValue == 13.0){
            System.out.println("You drew a: K" + userSuit);
        }
        else{
            System.out.println("You drew a: " + (int)userCardValue + userSuit);
        }

        //checking if opponent card is face card
        if(opponentCardValue == 14.0){
            System.out.println("The opponent drew an: A" + opponentSuit);
        }    
        else if (opponentCardValue == 11.0){
            System.out.println("The opponent drew a: J" + opponentSuit);
        }
        else if (opponentCardValue == 12.0){
            System.out.println("The opponent drew a: Q" + opponentSuit);
        }
        else if (opponentCardValue == 13.0){
            System.out.println("The opponent drew a: K" + opponentSuit);
        }
        else{
            System.out.println("The opponent drew a: " + (int)opponentCardValue + opponentSuit);
        }
    }    

    /*
    SIXTH METHOD: Adding and Removing Cards in piles based on winner
    Precondition:
    Postcondition: 
    */
    public static void cardOrganizer(double[] pileWinner, double[] pileLoser, double winCard, double lossCard, int numWinnerCards, int numLoserCards){
        //step 1: set index 0 in pileLoser to 0
        pileLoser[0] = 0.0;
        
        //step 2: shift all pileLoser cards ONE INDEX LEFT
        for(int i = 0; i < numLoserCards; i++){
            pileLoser[i] = pileLoser[i+1];
        }
        
        //step 3: add acquired opponent card to next empty index in pileWinner
        pileWinner[numWinnerCards] = winCard;

        //step 4: set index 0 in pileWinner to 0
        pileWinner[0] = 0.0;
        
        //step 5: shift all pileWinner cards ONE INDEX LEFT
        for(int i = 0; i < numWinnerCards; i++){
            pileWinner[i] = pileWinner[i+1];
        }
        
        //step 6: delete card in index 0 from pileWinner + move it behind opponent's newly added card
        pileWinner[numWinnerCards-1] = lossCard;
    }
    
    /*
    SEVENTH METHOD: War Scenario
    Precondition: accounts for when both players draw the same 1st card.
    This method then compares the 5th card in the pile to see who wins the war.
    Postcondition: 
    */
    public static void warCase(double[] pileUser, double[] pileOpponent, double warUserCard, double warOpponentCard, int numUserCards, int numOpponentCards){
        double userCardValue = Math.floor(warUserCard);
        double oppCardValue = Math.floor(warOpponentCard);
    
        //determine which war card is greater
        if(userCardValue > oppCardValue){
            System.out.println("\nYou played a higher card. You win the war and collect all 10 cards!");
            System.out.println("\nYou now have: " + (numUserCards+5) + " cards left");
            System.out.println("The opponent now has: " + (numOpponentCards-5) + " cards left");
            
            //step 1: store all of the won cards to a separate array
            double[] wonCards = new double [10];
            
            for(int i = 0; i < 5; i++){
                wonCards[i] = pileUser[i];
            }
            
            for(int i = 5; i < 10; i++){
                wonCards[i] = pileOpponent[i-5];
            }

            //step 2: delete all cards involved in war from both piles
            for(int i = 0; i < 5; i++){
                pileUser[i] = 0.0;
            }
            
            for(int i = 0; i < 5; i++){
                pileOpponent[i] = 0.0;
            }
            

            //step 3: move user and opponent's cards FIVE indexes left
            for(int i = 0; i < (numUserCards); i++){
                pileUser[i] = pileUser[i+5];
            }
            
            for(int i = 0; i < (numOpponentCards); i++){
                pileOpponent[i] = pileOpponent[i+5];
            }
            
            //step 4: add all of the won cards to the user's pile
            for(int i = 0; i < 10; i++){
                pileUser[(numUserCards-5)+i] = wonCards[i];
            }
        }
        else if(userCardValue < oppCardValue){
            System.out.println("\nThe opponent played a higher card. They win the war and collect all 10 cards.");   
            System.out.println("\nYou now have: " + (numUserCards-5) + " cards left");
            System.out.println("The opponent now has: " + (numOpponentCards+5) + " cards left");
            
            //step 1: add all of the won cards to a separate array
            double[] wonCards = new double [10];
            
            for(int i = 0; i < 5; i++){
                wonCards[i] = pileOpponent[i];
            }
            
            for(int i = 5; i < 10; i++){
                wonCards[i] = pileUser[i-5];
            }

            //step 2: delete all cards involved in war from both piles
            for(int i = 0; i < 5; i++){
                pileUser[i] = 0.0;
            }
            
            for(int i = 0; i < 5; i++){
                pileOpponent[i] = 0.0;
            }
            
            //step 3: move user and opponent's cards FIVE indexes left
            for(int i = 0; i < (numOpponentCards); i++){
                pileOpponent[i] = pileOpponent[i+5];
            }
            
            for(int i = 0; i < (numUserCards); i++){
                pileUser[i] = pileUser[i+5];
            }
            
            //step 4: add all of the won cards to the opponent's pile
            for(int i = 0; i < 10; i++){
                pileOpponent[(numOpponentCards-5)+i] = wonCards[i];
            }

        }
        else{
            System.out.println("\nAnother war has been initiated");
            
            //determining which player wins based on who has greater # of cards at that point
            if(numUserCards > numOpponentCards){
                System.out.println("\nYou currently have more cards, so YOU win the game!\nThank you for playing!");
                System.exit(0);
            }
            else if(numUserCards < numOpponentCards){
                System.out.println("\nThe opponent currently has more cards, they win the game.\nThank you for playing!");
                System.exit(0);
            }
            else{
                System.out.println("\nBoth players currently have an equal number of cards.\nThe game ends in a stalemate.\nThank you for playing!");
                System.exit(0);
            }
        }
    }
    
    
    

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    //MAIN METHOD
    public static void main(String[] args){
        //initializing variables
        //initializing two piles and deck array
            double [] deck = new double [52];
            double [] pileUser = new double [52];
            double [] pileOpponent = new double [52];
            
        //welcome user and describe rules of game
        welcome();
            
        //create and shuffle deck
        deckShuffler(deck);
            
        //split deck into two piles
        deckSplitter(pileUser, pileOpponent, deck);
            
        //variables that keep track of number of cards each player has left in pile
        int numUserCards = 26;
        int numOpponentCards = 26;
        int round = 1;
            
        //loop that continues drawing cards until one pile is empty
        while(numUserCards != 0 && numOpponentCards != 0){
            
            //initializing variables
            double currentUserCard = pileUser[0];
            double currentOpponentCard = pileOpponent[0];
            double warUserCard;
            double warOpponentCard;
            
            //TESTING PURPOSES
            //System.out.println("USERDECK: " + Arrays.toString(pileUser));
            //System.out.println("OPPDECK: " + Arrays.toString(pileOpponent));
            
            //method that checks how many cards each deck has
            //initializing to 0 so that it begins counting from 0
            numUserCards = 0;
            numOpponentCards = 0;
            
            //counting number of cards before the round
            numUserCards = cardCounter(pileUser, numUserCards);
            numOpponentCards = cardCounter(pileOpponent, numOpponentCards);
            

            //method that displays the card at hand to the user
            System.out.println("\n\nRound " + round + "\n--------");
            displayCard(currentUserCard, currentOpponentCard);
            round++;
            
            
            //determining which player played the higher card
            if(Math.floor(currentUserCard) > Math.floor(currentOpponentCard)){
                System.out.println("\nYou won this battle! You take both cards"); 
                System.out.println("\nYou now have: " + (numUserCards+1) + " cards left");
                System.out.println("The opponent now has: " + (numOpponentCards-1) + " cards left");
                
                //add both cards to users deck + shuffle all elements of BOTH piles over one index
                cardOrganizer(pileUser, pileOpponent, currentOpponentCard, currentUserCard, numUserCards, numOpponentCards);
            }
            else if(Math.floor(currentUserCard) < Math.floor(currentOpponentCard)){
                System.out.println("\nThe opponent wins this battle. They take both cards.");
                System.out.println("\nYou now have: " + (numUserCards-1) + " cards left");
                System.out.println("The opponent now has: " + (numOpponentCards+1) + " cards left");
            
                //add both cards to opponents deck + shuffle all elements of BOTH piles over one index
                cardOrganizer(pileOpponent, pileUser, currentUserCard, currentOpponentCard, numOpponentCards, numUserCards);
            }
            else{
                System.out.println("\nYou both drew the same card... it's a WAR!");
                
                //check if each player has enough cards to contest the war. If not, person that cannot contest loses
                if(numUserCards < 5){
                    System.out.println("You do not have enough cards to contest the war...you lose the game.\nThanks for playing!");
                    System.exit(0);
                }
                else if(numOpponentCards < 5){
                    System.out.println("The opponent does not have enough cards to contest the war...you win the game!\nThanks for playing!");
                    System.exit(0);
                }
                else{
                }
                
                //displays fourth card of user and opponent
                System.out.println("\nYou and your opponent place three cards down, and draw a fourth card...");
                
                warUserCard = pileUser[4];
                warOpponentCard = pileOpponent[4];
                
                displayCard(warUserCard, warOpponentCard);
                
                //method that accounts for WAR
                warCase(pileUser, pileOpponent, warUserCard, warOpponentCard, numUserCards, numOpponentCards);
            }
            
            //recounting cards after round
            numUserCards = 0;
            numOpponentCards = 0;
            numUserCards = cardCounter(pileUser, numUserCards);
            numOpponentCards = cardCounter(pileOpponent, numOpponentCards);
        }

        if(numUserCards == 0){
            System.out.println("\nThe opponent wins :(\nThanks for playing!");
        }
        else if(numOpponentCards == 0){
            System.out.println("\nYOU won the game :)\nThanks for playing!");
        }
        
    }
}