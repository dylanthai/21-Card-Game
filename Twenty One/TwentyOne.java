/** Twenty One.java
  * Create the card game 21 in Java
  * @author Dylan Thai
  * @version Date: 5/1/2019
 */

import java.io.*; 
import java.util.Scanner;

public class TwentyOne {
   
  public static void printScreen(char [] enteredPlayer1Hand, char [] enteredPlayer1Suits, int [] enteredPlayer1Value,
                                 char [] enteredPlayer2Hand, char [] enteredPlayer2Suits, int [] enteredPlayer2Value,
                                 char [] enteredPlayer3Hand, char [] enteredPlayer3Suits, int [] enteredPlayer3Value) {    
    System.out.println("Player 1: ");
    for (int x = 0; x < 11; x++){
      System.out.print(enteredPlayer1Hand[x] + " ");
    }
    System.out.println("\n");
    for (int x = 0; x < 11; x++){
      System.out.print(enteredPlayer1Suits[x] + " ");
    }
    
    int p1Sum = 0;
    for(int x = 0; x < 11; x++){
      p1Sum = p1Sum + enteredPlayer1Value[x];
    }
    System.out.println("\n");
    System.out.print("Player 1 Hand Value: " + p1Sum);
    System.out.println("\n");
    
    System.out.println("Player 2: ");
   for (int x = 0; x < 11; x++){
      System.out.print(enteredPlayer2Hand[x] + " ");
    }
    System.out.println("\n");
    for (int x = 0; x < 11; x++){
      System.out.print(enteredPlayer2Suits[x] + " ");
    }
    int p2Sum = 0;
    for(int x = 0; x < 11; x++){
      p2Sum = p2Sum + enteredPlayer2Value[x];
    }
    
    System.out.println("\n");
    System.out.print("Player 2 Hand Value: " + p2Sum);
    System.out.println("\n");
    
    System.out.println("Player 3: ");
    for (int x = 0; x < 11; x++){
      System.out.print(enteredPlayer3Hand[x] + " ");
    }
    System.out.println("\n");
    for (int x = 0; x < 11; x++){
      System.out.print(enteredPlayer3Suits[x] + " ");
    }
    int p3Sum = 0;
    for(int x = 0; x < 11; x++){
      p3Sum = p3Sum + enteredPlayer3Value[x];
    }
    
    System.out.println("\n");
    System.out.print("Player 3 Hand Value: " + p3Sum);
    System.out.println("\n");
    
    return;
  } // end printScreen Method
  
  // for loop while can't find 0, next index. When find zero, capture index number and thats where new card will be
  
  public static String giveCard(int [] enteredCardUsed, char [] enteredPlayerHand, char [] enteredPlayerSuits, 
                             int [] enteredPlayerDeckValue, char [] enteredCardDeck, char [] enteredSuitDeck, 
                             int [] enteredValueDeck){
    
    int randCard = (int) (Math.random()*52) + 0;
    do{
      randCard = (int) (Math.random()*52) + 0;
    }while(enteredCardUsed [randCard] == -1);
    
    int emptyIndex = -1;
    
    for (int x = 0; x < 11; x++){
      if (enteredPlayerHand[x] == 0){
        emptyIndex = x;
        x = 100; // end the for loop
      } // end if
    } // end for loop
  
    enteredPlayerHand [emptyIndex] =  enteredCardDeck[randCard];
    enteredPlayerSuits [emptyIndex] = enteredSuitDeck[randCard];
    enteredPlayerDeckValue [emptyIndex] = enteredValueDeck [randCard];
    enteredCardUsed[randCard] = -1;
      
    String message = "A card has been given";
    return  message;
    
  } // end giveCard Method
  
  public static int valueOfHand(int [] enteredValueOfPlayerHand){
    int value = 0;
    for(int index = 0; index < enteredValueOfPlayerHand.length; index++){
      value += enteredValueOfPlayerHand[index];
    }
    return value;
  } // end valueOfHand

  // End of Methods //////////////////////////////////////////////////////////////
  
  public static void main(String[] args) throws Exception {
    Scanner keyboard = new Scanner(System.in);
    
    // Open a file called 'Rules.txt'
    File rulesFile = new File("Rules.txt");
    
    // Create a Scanner and associate it with the file
    Scanner rules = new Scanner(rulesFile); 
     
    // Read and print rules from file
    while (rules.hasNext()) {
      System.out.println(rules.nextLine());
    }
    System.out.println();
    rules.close();
    
    // Create new file to save scores
    File scoresFile = new File("Scores.txt");
    
    // Create a PrinterWriter and associate it with the file
    PrintWriter savedScores = new PrintWriter(scoresFile);   
    
    char newGame = 'Y';
    char contPlaying = 'Y';
    
    int [] scores = new int [3];
    String [] players = new String [3];
    
    System.out.println("Welcome! Let's start with your names: ");
    
    for (int x = 0; x <= 2; x++){
      System.out.print("Enter player " + (x+1) + "'s name");
      players[x] = keyboard.nextLine();
    }
    System.out.println("Welcome " + players[0] + ", " + players[1] + ", and " + players[2] + "!");
    
    while (newGame == 'Y'){
      // The Ordered Deck
      
      // Three components: suits, value, card
      
      int [] value = new int [52];
      char [] card = new char [52];
      char [] suit = new char [52];

      // enter the suits
      
      for (int sIndex = 0; sIndex < 52; sIndex++ ){
        if (sIndex%4 == 0){
          suit[sIndex] = 'S';
        }
        else if (sIndex%4 == 1){
          suit[sIndex] = 'C';
        }
        else if (sIndex%4 == 2){
          suit[sIndex] = 'D';
        }
        else{
          suit[sIndex] = 'H';
        }
      } // end suits for loop
   
      // Give a value of each card and give a symbol for each chard (set card)
      
      int [] setValue = {1,2,3,4,5,6,7,8,9,10,10,10,10};
      char [] setCard = {'1','2','3','4','5','6','7','8','9','T','J','Q','K'};
      
      for (int i = 0;  i < 13; i++){
        
        // setting the value of each card
        
        value [i] = setValue[i];
        value [i+13] = setValue[i];
        value [i+26] = setValue[i];
        value [i+39]= setValue[i];
        
        // setting the Symbol of each card
        
        card [i] = setCard[i];
        card [i+13] = setCard[i];
        card [i+26] = setCard[i];
        card [i+39]= setCard[i];
      }
    
      // index of the deck (used to check if card has been used)
      // if value = -1, then card has been drawn
      
      int [] cardUsedCheck = new int [52];
    
      // Note: the most amount of card you can hold (to get 21) is 11 and more than 11 cards then player is over 21
      
      char [] player1Hand = new char[11];
      char [] player1Suits = new char[11];
      int[] player1Value = new int[11];
      
      char [] player2Hand = new char[11];
      char [] player2Suits = new char[11];
      int[] player2Value = new int[11];
      
      char [] player3Hand = new char[11];
      char [] player3Suits = new char[11];
      int[] player3Value = new int[11];
      
      int player1TotalValue = 0;
      int player2TotalValue = 0;
      int player3TotalValue = 0;
      
      //printRules();
    
      while (contPlaying == 'Y' && player1TotalValue < 21 && player2TotalValue < 21 && player3TotalValue < 21) {
        char p1HitOrEnd = '0';
        char p2HitOrEnd = '0';
        char p3HitOrEnd = '0';
        
        // Print screen
        
        printScreen(player1Hand, player1Suits, player1Value, player2Hand, player2Suits, player2Value, player3Hand, player3Suits, player3Value);
        
        // Player 1's turn
        
        while(p1HitOrEnd != 'E'){
          if(valueOfHand(player1Value) > 21){
            p1HitOrEnd = 'E';
          }else{
            System.out.println("Player 1, its your turn:");
            System.out.println("Enter (H) to pick up a Card and (E) to end your turn");
            p1HitOrEnd = Character.toUpperCase(keyboard.next().charAt(0));
            if(p1HitOrEnd == 'H'){ // If H is entered, give the player a card  and reprint screen
              giveCard(cardUsedCheck, player1Hand, player1Suits, player1Value, card, suit, value); // last 3 parameters are the deck
              printScreen(player1Hand, player1Suits, player1Value, player2Hand, player2Suits, player2Value, player3Hand, player3Suits, player3Value);
            } // end if
          } // end else
        } // end player 1's turn
        
        // Player 2's turn
        
        while(p2HitOrEnd != 'E'){
          if(valueOfHand(player2Value) > 21){
            p2HitOrEnd = 'E';
          }else{
            System.out.println("Player 2, its your turn:");
            System.out.println("Enter (H) to pick up a Card and (E) to end your turn");
            p2HitOrEnd = Character.toUpperCase(keyboard.next().charAt(0));
            if(p2HitOrEnd == 'H'){ // If H is entered, give the player a card  and reprint screen
              giveCard(cardUsedCheck, player2Hand, player2Suits, player2Value, card, suit, value); // last 3 parameters are the deck
              printScreen(player1Hand, player1Suits, player1Value, player2Hand, player2Suits, player2Value, player3Hand, player3Suits, player3Value);
            } // end if
          } // end else
        } // end player 2's turn
        
        // Player 3's turn
        
        while(p3HitOrEnd != 'E'){
          if(valueOfHand(player3Value) > 21){
            p3HitOrEnd = 'E';
          }else{
            System.out.println("Player 3, its your turn:");
            System.out.println("Enter (H) to pick up a Card and (E) to end your turn");
            p3HitOrEnd = Character.toUpperCase(keyboard.next().charAt(0));
            if(p3HitOrEnd == 'H'){ // If H is entered, give the player a card  and reprint screen
              giveCard(cardUsedCheck, player3Hand, player3Suits, player3Value, card, suit, value); // last 3 parameters are the deck
              printScreen(player1Hand, player1Suits, player1Value, player2Hand, player2Suits, player2Value, player3Hand, player3Suits, player3Value);
            } // end if
          } // end else
        } // end player 3's turn
        
        if (p1HitOrEnd == 'E' && p2HitOrEnd == 'E' && p3HitOrEnd == 'E'){ // if all is done picking up cards then end round
          contPlaying = 'N';
        }
      } // end while loop (Finish the round)
      
      ////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      // Do Scoring
        
      int [] playerTotalValues = {valueOfHand(player1Value),valueOfHand(player2Value), valueOfHand(player3Value)};
      
      int [] differences = new int [3]; // how far they are from 21 will be stored here
      
      for(int x = 0; x < 3; x++){
        differences[x] = 21 - playerTotalValues[x];
      }
      
    
      for (int x = 0; x <3; x++){
        
        // list of scores with  postive differences ((21 - value) > -1) // 100 is going to be its default value
        
        int [] posDiff = {100,100,100}; 
        for (int i = 0; i < 3; i++){
          if(differences[i]> -1){
            posDiff[i] = differences[i]; 
          }  
        }
        
        // if over 21 player gets 0 points
        if (differences[x] < 0){
          scores[x] = scores[x] +0;
        }
        else{
          // winner who has 21 or who ever is closest (has smallest postive difference after subtract value of hand from 21)
          if(differences[x] == (Math.min(Math.min(posDiff[0],posDiff[1]),posDiff[2])) ){
            scores[x] = scores[x] +2; // winner is awarded two points
          }
          // else if not over 21 and not the winner they only get 1 point
          else{
            scores[x] = scores[x] + 1;
          } // end else
        } // end else
      } // end for
      
      for(int x = 0; x < 3; x++){
        System.out.println("Scores player " + x + ":" + scores[x]);
      }
      String playAgain = "YN";
     
      do{
        System.out.println("Do you want to play another round? (names and scores will resume) Enter (Y) for yes and (N) for no: ");
        newGame = Character.toUpperCase(keyboard.next().charAt(0));
      } while(playAgain.indexOf(newGame) == -1);
      
      if(newGame == 'Y'){
        cardUsedCheck = new int [52]; // set used card checker to default value [all indexes = 0] / none of cards are used again
        contPlaying = 'Y';
        
        // Empty all of the players hands and value of their hand
        
        player1Hand = new char[11];
        player1Suits = new char[11];
        player1Value = new int[11];
        
        player2Hand = new char[11];
        player2Suits = new char[11];
        player2Value = new int[11];
        
        player3Hand = new char[11];
        player3Suits = new char[11];
        player3Value = new int[11];
        
        player1TotalValue = 0;
        player2TotalValue = 0;
        player3TotalValue = 0;
        
      } // end if
      
      if(newGame == 'N'){
        contPlaying = 'N';
        System.out.println("Thanks for playing!");
      }  
    } // end while loop (stop playing)
    
    savedScores.printf("%-20s %-20s\n", "Player Name", "Player Score");
    for(int i = 0; i < 3; i++){
      savedScores.printf("%-20s %-20d\n", players[i], scores[i]);
    }
    keyboard.close();
    savedScores.close();
    

  } // end main()
} //end class
